package com.kdde.basemodule.basemodule.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONArray;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.kdde.basemodule.basemodule.dao.ModuleDao;
import com.kdde.basemodule.basemodule.dao.ModuleLinkTableDao;
import com.kdde.basemodule.basemodule.entity.ModuleEntity;
import com.kdde.basemodule.basemodule.entity.ModuleLinkTableEntity;
import com.kdde.basemodule.basemodule.entity.ModuleStructureEntity;
import com.kdde.basemodule.basemodule.service.DynamicTableService;
import com.kdde.basemodule.basemodule.service.ModuleService;
import com.kdde.basemodule.basemodule.service.ModuleStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.regex.Pattern;

@Service
public class DynamicTableServiceImp implements DynamicTableService {
    @Autowired
    ModuleLinkTableDao moduleLinkTableDao;

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private ModuleStructureService moduleStructureService;

    private final JdbcTemplate jdbcTemplate;

    public DynamicTableServiceImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 根据前端传入 JSON 数据动态创建三张表：
     * name_object、name_operation、name_result
     */
    public void createTablesFromJson(JSONObject jsonData) {
//        LambdaUpdateWrapper<ModuleStructureEntity> wrapper = new LambdaUpdateWrapper<>();
//        wrapper.eq(ModuleStructureEntity::getModuleId, jsonData.getInteger("moduleId"));
//        List<ModuleStructureEntity> list = moduleStructureService.list(wrapper);


        // 表名：该表是否创建成功。
        Map<String,Boolean> isSuccessMap = new HashMap<>();
        // 1️⃣ 解析主信息
        String baseName = jsonData.getString("name");
        JSONObject columns = jsonData.getJSONObject("columns");

        String objectTableName = "";//object表名
        String operationTableName = "";//operation表名
        String resultTableName = "";//result表名

        // 2️⃣ 校验表名安全性
        String safeBaseName = sanitizeTableName(baseName);

        // 3️⃣ 遍历三个类型
        for (String key : columns.keySet()) {
            JSONArray arr = columns.getJSONArray(key);
            //表名
            String tableName = safeBaseName + "_" + sanitizeTableName(key);
            //创建表
            boolean isSuccess=createSafeTable(tableName, arr);
            //添加成功
            if (isSuccess) {
                isSuccessMap.put(tableName,isSuccess);
            }
            //设置相应表名
            if(key.equals("object")){
                objectTableName = tableName;
            }
            if(key.equals("operation")){
                operationTableName = tableName;
            }
            if(key.equals("result")){
                resultTableName = tableName;
            }
        }
        //将表格名插入映射表
        ModuleLinkTableEntity moduleLinkTableEntity = new ModuleLinkTableEntity();
        moduleLinkTableEntity.setOperationTableName(operationTableName);
        moduleLinkTableEntity.setObjectTableName(objectTableName);
        moduleLinkTableEntity.setResultTableName(resultTableName);
        moduleLinkTableEntity.setTableId(jsonData.getInteger("moduleId"));
        moduleLinkTableDao.insert(moduleLinkTableEntity);
        //3个拆分后的子表都创建成功，更新模块状态
        if (isSuccessMap.size() == 3){
            LambdaUpdateWrapper<ModuleEntity> wrapper = new LambdaUpdateWrapper<>();
            wrapper.eq(ModuleEntity::getId, jsonData.getInteger("moduleId"));
            wrapper.set(ModuleEntity::getState, 1);
            moduleService.update(wrapper);
        }else {
            // 删除已创建的表
            for (String tableName : isSuccessMap.keySet()) {
                try {
                    String dropSql = "DROP TABLE IF EXISTS `" + tableName + "`";
                    jdbcTemplate.execute(dropSql);
                    System.out.println("已删除不完整的表: " + tableName);
                } catch (Exception e) {
                    System.err.println("删除表失败: " + tableName + " 错误：" + e.getMessage());
                }
            }
            throw new RuntimeException("表创建失败");
        }
    }

    /**
     * 动态安全建表方法：
     * 自动包含 id、sample_serial、object_id、create_time 等字段
     */
private boolean createSafeTable(String tableName, JSONArray arr) {
        List<String> columnDefs = new ArrayList<>();

        // ✅ 固定主键
        columnDefs.add("id BIGINT AUTO_INCREMENT PRIMARY KEY");

        // ✅ 样品编号
        columnDefs.add("sample_serial VARCHAR(255) NOT NULL");

        // ✅ 对应 object 表的关联 ID
        columnDefs.add("object_id BIGINT DEFAULT NULL");

        // ✅ 前端动态列
        for (Object o : arr) {
            if (!(o instanceof JSONObject)) continue;
            JSONObject col = (JSONObject) o;

            String colName = sanitizeTableName(col.getString("columnName"));
            String colType = sanitizeType(col.getString("columnContribution"));
            columnDefs.add("`" + colName + "` " + colType);
        }

        // ✅ 额外字段
        columnDefs.add("create_time DATETIME DEFAULT CURRENT_TIMESTAMP");

        // ✅ 拼接 SQL
        String sql = "CREATE TABLE IF NOT EXISTS `" + tableName + "` (\n  "
                + String.join(",\n  ", columnDefs)
                + "\n) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";

        System.out.println("✅ 正在创建表：" + tableName);
        try {
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            System.err.println("创建表失败：" + tableName + " 错误：" + e.getMessage());
            return false;
        }
    }




    /**
     * 校验字段名合法性，防止 SQL 注入
     */
    private String sanitizeTableName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("字段名不能为空");
        }
        // 允许中英文、数字、下划线
        String safe = name.replaceAll("[^a-zA-Z0-9_\\u4e00-\\u9fa5]", "_");
        if (safe.length() > 64) {
            safe = safe.substring(0, 64);
        }
        return safe;
    }

    /**
     * 限定允许的类型，防止 SQL 注入
     */
    private String sanitizeType(String type) {
        List<String> allowed = Arrays.asList(
                "VARCHAR(50)", "VARCHAR(100)", "VARCHAR(255)",
                "INT", "BIGINT", "DOUBLE", "TEXT", "DATETIME"
        );
        if (type == null || !allowed.contains(type.toUpperCase())) {
            return "VARCHAR(255)";
        }
        return type.toUpperCase();
    }


}
