package com.kdde.basemodule.basemodule.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.kdde.basemodule.basemodule.dao.ModuleDataInsertDao;
import com.kdde.basemodule.basemodule.service.ModuleDataInsertService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ModuleDataInsertServiceImp implements ModuleDataInsertService {

    @Autowired
    private ModuleDataInsertDao moduleDataInsertDao;

    @Override
    public List<Map<String, Object>> getModuleList(int moduleId) {
        String objectTableName = moduleDataInsertDao.getTableName("object_table_name", moduleId);
        return moduleDataInsertDao.getModuleList(objectTableName);
    }

    @Override
    public JSONObject getModuleDetailInfo(JSONObject jsObject) {
        int moduleId = jsObject.getIntValue("moduleId");
        String sampleSerial = jsObject.getString("sampleSerial");
        String objectTableName = moduleDataInsertDao.getTableName("object_table_name", moduleId);
        String operationTableName = moduleDataInsertDao.getTableName("operation_table_name", moduleId);
        String resultTableName = moduleDataInsertDao.getTableName("result_table_name", moduleId);

        List<Map<String,Object>> objectList = moduleDataInsertDao.getModuleDetailInfo(objectTableName,sampleSerial);
        List<Map<String,Object>> operationList = moduleDataInsertDao.getModuleDetailInfo(operationTableName,sampleSerial);
        List<Map<String,Object>> resultList = moduleDataInsertDao.getModuleDetailInfo(resultTableName,sampleSerial);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("objectList", objectList);
        jsonObject.put("operationList", operationList);
        jsonObject.put("resultList", resultList);

        return jsonObject;
    }

    @Override
    public boolean insertModuleData(JSONObject jsonObject) {
        int moduleId = jsonObject.getIntValue("module_id");
        String sampleSerial = jsonObject.getString("sample_serial");

        Map<String,Object> objectMap = jsonObject.getJSONObject("object");
        Map<String,Object> operationMap = jsonObject.getJSONObject("operation");
        Map<String,Object> resultMap = jsonObject.getJSONObject("result");

        objectMap.put("sample_serial",sampleSerial);
        operationMap.put("sample_serial",sampleSerial);
        resultMap.put("sample_serial",sampleSerial);

        String objectTableName = moduleDataInsertDao.getTableName("object_table_name", moduleId);
        String operationTableName = moduleDataInsertDao.getTableName("operation_table_name", moduleId);
        String resultTableName = moduleDataInsertDao.getTableName("result_table_name", moduleId);
        try{
            insertJsonData(objectTableName,objectMap);
            Long operationId = insertJsonData(operationTableName,operationMap);
            resultMap.put("operation_id",operationId);
            insertJsonData(resultTableName,resultMap);
            return true;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }

    }

    @Override
    public JSONObject getColumnInfo(int moduleId) {
        JSONObject res = new JSONObject();

        List<Map<String,Object>> objectMap = moduleDataInsertDao.getColumnInfo(moduleId,"object");
        List<Map<String,Object>> operationMap = moduleDataInsertDao.getColumnInfo(moduleId,"operation");
        List<Map<String,Object>> resultMap = moduleDataInsertDao.getColumnInfo(moduleId,"result");

        res.put("object",objectMap);
        res.put("operation",operationMap);
        res.put("result",resultMap);
        return res;
    }

    /**
     * 通用数据插入逻辑
     * @param tableName 表名
     * @param dataMap   键值对形式数据
     * @return object/operation 表返回插入或已存在的 id；result 表返回 null
     */
    public Long insertJsonData(String tableName, Map<String, Object> dataMap) {
        if (dataMap == null || dataMap.isEmpty()) {
            throw new IllegalArgumentException("dataMap 不能为空");
        }

        // ✅ 表名安全校验
        if (!tableName.matches("^[a-zA-Z0-9_]+$")) {
            throw new IllegalArgumentException("非法表名：" + tableName);
        }

        Long id = null;

        // ✅ 1️⃣ 如果是 object 表
        if (tableName.toLowerCase().contains("object")) {
            String sampleSerial = (String) dataMap.get("sample_serial");
            if (sampleSerial == null) {
                throw new IllegalArgumentException("object表必须包含 sample_serial");
            }

            // 查询是否已存在
            id = moduleDataInsertDao.findIdBySampleSerial(tableName, sampleSerial);
            if (id != null) {
                System.out.println("✅ 已存在相同样品编号，id=" + id);
                return id;
            }

            // 插入数据
            int inserted = moduleDataInsertDao.insertDynamic(tableName, dataMap);
            if (inserted > 0) {
                id = moduleDataInsertDao.findIdBySampleSerial(tableName, sampleSerial);
                System.out.println("✅ 新插入object记录，id=" + id);
            }
        }

        // ✅ 2️⃣ 如果是 operation 表
        else if (tableName.toLowerCase().contains("operation")) {
            // 查询是否存在完全匹配的行
            id = moduleDataInsertDao.findMatchingRowId(tableName, dataMap);
            if (id != null) {
                System.out.println("✅ 已存在相同 operation 记录，id=" + id);
                return id;
            }

            // 不存在则插入
            int inserted = moduleDataInsertDao.insertDynamic(tableName, dataMap);
            if (inserted > 0) {
                id = moduleDataInsertDao.findMatchingRowId(tableName, dataMap);
                System.out.println("✅ 新插入 operation 记录，id=" + id);
            }
        }

        // ✅ 3️⃣ 如果是 result 表（不返回 id）
        else if (tableName.toLowerCase().contains("result")) {
            Long existId = moduleDataInsertDao.findMatchingRowId(tableName, dataMap);
            if (existId != null) {
                System.out.println("✅ result 表存在相同记录，跳过插入");
                return null;
            }

            // 插入
            moduleDataInsertDao.insertDynamic(tableName, dataMap);
            System.out.println("✅ 新插入 result 记录");
        }

        return id;
    }
}
