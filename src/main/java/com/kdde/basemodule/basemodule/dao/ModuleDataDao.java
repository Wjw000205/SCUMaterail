package com.kdde.basemodule.basemodule.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ModuleDataDao {

    @Select("select ${tableName} from module_conflict_table where module_id = #{moduleId}")
    String getTableName(String objectTableName,int moduleId);

    @Select("select * from module_conflict_table where module_id = #{moduleId}")
    Map<String,String> getModuleTableName(int moduleId);

    @Select("SELECT * " +
            "FROM ${objectTableName} ot " +
            "LEFT JOIN ${operationTableName} opt " +
            "  ON ot.sample_serial = opt.sample_serial " +
            "LEFT JOIN ${resultTableName} rt " +
            "  ON rt.operation_id = opt.id")
    List<Map<String, Object>> getModuleList(
            @Param("objectTableName") String objectTableName,
            @Param("resultTableName") String resultTableName,
            @Param("operationTableName") String operationTableName
    );

    @Select("select * from ${tableName} where sample_serial = #{sampleSerial}")
    List<Map<String,Object>> getModuleDetailInfo(String tableName,String sampleSerial);

    @Select("select column_name,column_contribution from module_structure where module_id = #{moduleId} and belong = #{belong}")
    List<Map<String,Object>> getColumnInfo(int moduleId,String belong);

    /** 通用动态插入 */
    int insertDynamic(@Param("tableName") String tableName, @Param("dataMap") Map<String, Object> dataMap);

    /** 查询 object 表是否存在 sample_serial */
    List<Long> findIdBySampleSerial(@Param("tableName") String tableName, @Param("sampleSerial") String sampleSerial);

    /** 查询 operation/result 表中是否存在完全匹配的数据 */
    List<Long> findMatchingRowId(@Param("tableName") String tableName, @Param("dataMap") Map<String, Object> dataMap);

    @Select("select column_name,column_contribution from module_structure where module_id = #{moduleId} and belong = #{belong}")
    List<String> getColumnNameList(int moduleId,String belong);
}
