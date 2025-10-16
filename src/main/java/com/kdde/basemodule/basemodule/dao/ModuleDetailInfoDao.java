package com.kdde.basemodule.basemodule.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface ModuleDetailInfoDao {
    @Select("select * from ${tableName} where ")
    public Map<String, Object> getObjectInfo(int moduleId,String tableName);
}
