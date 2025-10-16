package com.kdde.basemodule.basemodule.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * 用于动态创建数据库表
 */
@Mapper
public interface DynamicTableDao {
    @Update("${sql}")
    void createTable(@Param("sql") String sql);
}
