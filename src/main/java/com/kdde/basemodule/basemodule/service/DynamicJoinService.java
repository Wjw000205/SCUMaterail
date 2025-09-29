package com.kdde.basemodule.basemodule.service;

import com.kdde.basemodule.basemodule.dto.DynamicJoinRequest;

import java.util.List;
import java.util.Map;

public interface DynamicJoinService {

    /**
     * 动态多表联合查询
     * @param
     * @return 查询结果
     */
    List<Map<String, Object>> joinTablesBySampleSerial(DynamicJoinRequest joinDTO);

    /**
     * 获取表的所有列名
     * @param tableName 表名
     * @return 列名列表
     */
//    List<String> getTableColumns(String tableName);
}
