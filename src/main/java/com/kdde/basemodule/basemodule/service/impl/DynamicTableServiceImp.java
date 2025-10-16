package com.kdde.basemodule.basemodule.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONArray;
import com.kdde.basemodule.basemodule.dao.ModuleLinkTableDao;
import com.kdde.basemodule.basemodule.entity.ModuleLinkTableEntity;
import com.kdde.basemodule.basemodule.service.DynamicTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.regex.Pattern;

@Service
public class DynamicTableServiceImp implements DynamicTableService {
    @Autowired
    ModuleLinkTableDao moduleLinkTableDao;

    private final JdbcTemplate jdbcTemplate;

    public DynamicTableServiceImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 根据前端传入 JSON 数据动态创建三张表：
     * name_object、name_operation、name_result
     */
    public void createTablesFromJson(JSONObject jsonData) {
        // 1️⃣ 解析主信息
        String baseName = jsonData.getString("name");
        JSONObject columns = jsonData.getJSONObject("columns");
        String objectTableName = "";
        String operationTableName = "";
        String resultTableName = "";

        // 2️⃣ 校验表名安全性
        String safeBaseName = sanitizeTableName(baseName);

        // 3️⃣ 遍历三个类型
        for (String key : columns.keySet()) {
            JSONArray arr = columns.getJSONArray(key);

            String tableName = safeBaseName + "_" + sanitizeTableName(key);
            createSafeTable(tableName, arr);
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
    }

    /**
     * 实际建表逻辑（带防注入）
     */
    private void createSafeTable(String tableName, JSONArray arr) {
        List<String> columnDefs = new ArrayList<>();
        columnDefs.add("id BIGINT AUTO_INCREMENT PRIMARY KEY");

        for (Object o : arr) {
            JSONObject col = (JSONObject) o;
            String colName = sanitizeTableName(col.getString("columnName"));
            String colType = sanitizeType(col.getString("columnContribution"));
            columnDefs.add("`" + colName + "` " + colType);
        }

        String sql = "CREATE TABLE IF NOT EXISTS `" + tableName + "` (\n  "
                + String.join(",\n  ", columnDefs)
                + "\n) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";

        jdbcTemplate.execute(sql);
    }


    /**
     * 校验表名和列名合法性（防 SQL 注入）
     */
    private String sanitizeTableName(String name) {
        if (name == null) throw new IllegalArgumentException("表名不能为空");
        // 中文名或其他字符转为拼音或安全格式
        String safe = name.replaceAll("[^a-zA-Z0-9_\\u4e00-\\u9fa5]", "_");
        if (safe.length() > 64) {
            safe = safe.substring(0, 64);
        }
        return safe;
    }

    private String sanitizeType(String type) {
        // 仅允许白名单类型
        List<String> allowed = Arrays.asList(
                "INT", "BIGINT", "VARCHAR(50)", "VARCHAR(100)", "VARCHAR(255)",
                "TEXT", "DATETIME", "DOUBLE"
        );
        if (allowed.contains(type.toUpperCase())) return type;
        return "VARCHAR(255)";
    }

}
