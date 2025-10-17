package com.kdde.basemodule.basemodule.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ModuleDataInsertDao {

    @Select("select ${tableName} from module_conflict_table where module_id = #{moduleId}")
    public String getTableName(String tableName,int moduleId);

    @Select("select *  from ${tableName}")
    public List<Map<String,Object>> getModuleList(String tableName);

    @Select("select * from ${tableName} where sample_serial = #{sampleSerial}")
    public List<Map<String,Object>> getModuleDetailInfo(String tableName,String sampleSerial);

    @Select("select column_name,column_contribution from module_structure where module_id = ${moduleId} and belong = #{belong}")
    public List<Map<String,Object>> getColumnInfo(int moduleId,String belong);

    /** 通用动态插入 */
    int insertDynamic(@Param("tableName") String tableName, @Param("dataMap") Map<String, Object> dataMap);

    /** 查询 object 表是否存在 sample_serial */
    Long findIdBySampleSerial(@Param("tableName") String tableName, @Param("sampleSerial") String sampleSerial);

    /** 查询 operation/result 表中是否存在完全匹配的数据 */
    Long findMatchingRowId(@Param("tableName") String tableName, @Param("dataMap") Map<String, Object> dataMap);
}
