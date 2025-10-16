package com.kdde.basemodule.basemodule.service;

import java.util.List;

public interface DynamicTableService {
    public void createTable(String tableName, List<String> columnNames, List<String> columnTypes);
}
