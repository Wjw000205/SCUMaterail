package com.kdde.basemodule.basemodule.service.impl;

import com.kdde.basemodule.basemodule.dao.DynamicTableDao;
import com.kdde.basemodule.basemodule.service.DynamicTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DynamicTableServiceImp implements DynamicTableService {
    @Autowired
    private DynamicTableDao dynamicTableDao;

    @Override
    public void createTable(String tableName, List<String> columnNames, List<String> columnTypes) {
        if (columnNames == null || columnTypes == null || columnNames.size() != columnTypes.size()) {
            throw new IllegalArgumentException("列名和列类型数量不一致！");
        }

        // 1️⃣ 拼接 CREATE TABLE SQL
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS ").append(tableName).append(" (\n");

        for (int i = 0; i < columnNames.size(); i++) {
            sql.append("  ").append(columnNames.get(i))
                    .append(" ").append(columnTypes.get(i));
            if (i != columnNames.size() - 1) sql.append(",\n");
        }
        sql.append("\n);");

        // 2️⃣ 打印调试 SQL
        System.out.println("执行 SQL:\n" + sql);

        // 3️⃣ 执行 SQL
        dynamicTableDao.createTable(sql.toString());
    }
}
