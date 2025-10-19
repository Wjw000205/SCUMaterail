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

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

@Service
public class DynamicTableServiceImp implements DynamicTableService {

    @Autowired
    private ModuleLinkTableDao moduleLinkTableDao;

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
        Map<String, Boolean> isSuccessMap = new HashMap<>();

        // 1️⃣ 解析主信息
        String baseName = jsonData.getString("name");
        JSONObject columns = jsonData.getJSONObject("columns");

        String objectTableName = "";
        String operationTableName = "";
        String resultTableName = "";

        // 2️⃣ 校验表名安全性
        String safeBaseName = sanitizeTableName(baseName);

        // 3️⃣ 唯一后缀，避免重名
        String uniqueSuffix = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
                + "_" + UUID.randomUUID().toString().substring(0, 6);

        // 4️⃣ 遍历 object / operation / result
        for (String key : columns.keySet()) {
            JSONArray arr = columns.getJSONArray(key);
            String tableName = safeBaseName + "_" + sanitizeTableName(key) + "_" + uniqueSuffix;

            boolean ok = createSafeTable(key, tableName, arr);
            if (ok) {
                isSuccessMap.put(tableName, true);
            }

            switch (key) {
                case "object":
                    objectTableName = tableName;
                    break;
                case "operation":
                    operationTableName = tableName;
                    break;
                case "result":
                    resultTableName = tableName;
                    break;
                default:
                    break;
            }
        }

        // 5️⃣ 插入表映射记录
        ModuleLinkTableEntity link = new ModuleLinkTableEntity();
        link.setOperationTableName(operationTableName);
        link.setObjectTableName(objectTableName);
        link.setResultTableName(resultTableName);
        link.setModuleId(jsonData.getInteger("moduleId"));
        moduleLinkTableDao.insert(link);

        // 6️⃣ 所有表都成功创建则更新模块状态
        if (isSuccessMap.size() == 3) {
            LambdaUpdateWrapper<ModuleEntity> wrapper = new LambdaUpdateWrapper<>();
            wrapper.eq(ModuleEntity::getId, jsonData.getInteger("moduleId"));
            wrapper.set(ModuleEntity::getState, 1);
            moduleService.update(wrapper);
        } else {
            // 删除已创建的表
            for (String t : isSuccessMap.keySet()) {
                try {
                    jdbcTemplate.execute("DROP TABLE IF EXISTS `" + t + "`");
                    System.out.println("⚠️ 已删除不完整表：" + t);
                } catch (Exception e) {
                    System.err.println("删除表失败: " + t + " 错误：" + e.getMessage());
                }
            }
            throw new RuntimeException("部分表创建失败");
        }
    }

    /**
     * 动态建表方法：
     * object 表不自动生成 sample_serial；
     * operation/result 表仍保留 sample_serial 和其他逻辑。
     */
    private boolean createSafeTable(String type, String tableName, JSONArray arr) {
        List<String> columnDefs = new ArrayList<>();

        // ✅ 固定主键
        columnDefs.add("id BIGINT AUTO_INCREMENT PRIMARY KEY");

        // ✅ 根据表类型动态添加 sample_serial
        if (!"object".equalsIgnoreCase(type)) {
            columnDefs.add("sample_serial VARCHAR(255) NOT NULL COMMENT '样品编号'");
        }

        // ✅ 如果是 result 表，额外添加 operation_id
        if ("result".equalsIgnoreCase(type)) {
            columnDefs.add("operation_id BIGINT DEFAULT NULL COMMENT '对应操作表ID'");
        }

        // ✅ 前端定义列
        for (Object o : arr) {
            if (!(o instanceof JSONObject)) continue;
            JSONObject col = (JSONObject) o;
            String colName = sanitizeTableName(col.getString("columnName"));
            String colType = sanitizeType(col.getString("columnContribution"));
            columnDefs.add("`" + colName + "` " + colType);
        }

        // ✅ 创建时间
        columnDefs.add("create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'");

        // ✅ 拼接 SQL
        String sql = "CREATE TABLE IF NOT EXISTS `" + tableName + "` (\n  "
                + String.join(",\n  ", columnDefs)
                + "\n) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";

        System.out.println("✅ 正在创建表: " + tableName);
        try {
            jdbcTemplate.execute(sql);
            return true;
        } catch (Exception e) {
            System.err.println("❌ 创建表失败: " + tableName + " 错误：" + e.getMessage());
            return false;
        }
    }

    /**
     * 校验表名合法性
     */
    private String sanitizeTableName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("字段名不能为空");
        }
        String safe = name.replaceAll("[^a-zA-Z0-9_\\u4e00-\\u9fa5]", "_");
        return safe.length() > 64 ? safe.substring(0, 64) : safe;
    }

    /**
     * 类型校验
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

