package com.kdde.basemodule.basemodule.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.kdde.basemodule.basemodule.common.utils.ExcelTemplateUtil;
import com.kdde.basemodule.basemodule.dao.ModuleDataDao;
import com.kdde.basemodule.basemodule.service.ModuleDataService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Slf4j
@Service
public class ModuleDataServiceImp implements ModuleDataService {

    @Autowired
    private ModuleDataDao moduleDataDao;

    @Override
    public List<Map<String, Object>> getModuleList(int moduleId) {
        String objectTableName = moduleDataDao.getTableName("object_table_name", moduleId);
        return moduleDataDao.getModuleList(objectTableName);
    }

    @Override
    public JSONObject getModuleDetailInfo(JSONObject jsObject) {
        int moduleId = jsObject.getIntValue("moduleId");
        String sampleSerial = jsObject.getString("sampleSerial");
        String objectTableName = moduleDataDao.getTableName("object_table_name", moduleId);
        String operationTableName = moduleDataDao.getTableName("operation_table_name", moduleId);
        String resultTableName = moduleDataDao.getTableName("result_table_name", moduleId);

        List<Map<String,Object>> objectList = moduleDataDao.getModuleDetailInfo(objectTableName,sampleSerial);
        List<Map<String,Object>> operationList = moduleDataDao.getModuleDetailInfo(operationTableName,sampleSerial);
        List<Map<String,Object>> resultList = moduleDataDao.getModuleDetailInfo(resultTableName,sampleSerial);

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

        if (!objectMap.containsKey("sample_serial")){
            objectMap.put("sample_serial",sampleSerial);
        }
        operationMap.put("sample_serial",sampleSerial);
        resultMap.put("sample_serial",sampleSerial);

        String objectTableName = moduleDataDao.getTableName("object_table_name", moduleId);
        String operationTableName = moduleDataDao.getTableName("operation_table_name", moduleId);
        String resultTableName = moduleDataDao.getTableName("result_table_name", moduleId);
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

        List<Map<String,Object>> objectMap = moduleDataDao.getColumnInfo(moduleId,"object");
        List<Map<String,Object>> operationMap = moduleDataDao.getColumnInfo(moduleId,"operation");
        List<Map<String,Object>> resultMap = moduleDataDao.getColumnInfo(moduleId,"result");

        res.put("object",objectMap);
        res.put("operation",operationMap);
        res.put("result",resultMap);
        return res;
    }

    @Override
    public byte[] generateTemplate(int moduleId) {
        List<String> objectList = moduleDataDao.getColumnNameList(moduleId,"object");
        List<String> operationList = moduleDataDao.getColumnNameList(moduleId,"operation");
        List<String> resultList = moduleDataDao.getColumnNameList(moduleId,"result");
        try {
            return ExcelTemplateUtil.generateTemplateFromLists(objectList, operationList, resultList);
        } catch (IOException e) {
            throw new RuntimeException("生成模板文件失败：" + e.getMessage(), e);
        }
    }

    /**
     * 通用数据插入逻辑
     * @param tableName 表名
     * @param dataMap   键值对形式数据
     * @return object/operation 表返回插入或已存在的 id；result 表返回 null
     */
    public Long insertJsonData(String tableName, Map<String, Object> dataMap) {
        Long id = null;
        String lower = tableName.toLowerCase();

        // ✅ Object 表（按 sample_serial）
        if (lower.contains("object")) {
            String sampleSerial = (String) dataMap.get("sample_serial");
            List<Long> ids = moduleDataDao.findIdBySampleSerial(tableName, sampleSerial);
            if (ids != null && !ids.isEmpty()) {
                System.out.println("✅ Object 已存在，id=" + ids.get(0));
                return ids.get(0);
            }
            moduleDataDao.insertDynamic(tableName, dataMap);
            ids = moduleDataDao.findIdBySampleSerial(tableName, sampleSerial);
            if (ids != null && !ids.isEmpty()) id = ids.get(0);
        }

        // ✅ Operation 表
        else if (lower.contains("operation")) {
            List<Long> ids = moduleDataDao.findMatchingRowId(tableName, dataMap);
            if (ids != null && !ids.isEmpty()) {
                System.out.println("✅ Operation 已存在，id=" + ids.get(0));
                return ids.get(0);
            }
            moduleDataDao.insertDynamic(tableName, dataMap);
            ids = moduleDataDao.findMatchingRowId(tableName, dataMap);
            if (ids != null && !ids.isEmpty()) id = ids.get(0);
        }

        // ✅ Result 表
        else if (lower.contains("result")) {
            List<Long> ids = moduleDataDao.findMatchingRowId(tableName, dataMap);
            if (ids != null && !ids.isEmpty()) {
                System.out.println("✅ Result 已存在，跳过插入");
                return null;
            }
            moduleDataDao.insertDynamic(tableName, dataMap);
        }

        return id;
    }


    /**
     * 批量上传模块数据（Excel）
     * @param moduleId 模块ID
     * @param file Excel文件
     * @return 上传结果（成功数 / 失败行）
     */
    public Map<String, Object> batchUpload(int moduleId, MultipartFile file) {
        Map<String, Object> result = new HashMap<>();
        int successCount = 0;
        int failCount = 0;
        List<Integer> failRows = new ArrayList<>();

        try (InputStream is = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(is)) {

            Sheet sheet = workbook.getSheetAt(0);
            if (sheet == null) throw new IllegalArgumentException("Excel 文件内容为空");

            // 获取第二行字段名（Object、Operation、Result）
            Row headerRow = sheet.getRow(1);
            if (headerRow == null) throw new IllegalArgumentException("Excel 文件格式错误（缺少字段行）");

            // 分块索引区间
            Map<String, List<Integer>> sectionMap = getSectionRanges(sheet.getRow(0));

            // 遍历每一行（从第3行开始）
            for (int i = 2; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                try {
                    JSONObject json = new JSONObject();
                    json.put("module_id", moduleId);

                    Map<String, Object> objectMap = extractSection(row, headerRow, sectionMap.get("Object"));
                    Map<String, Object> operationMap = extractSection(row, headerRow, sectionMap.get("Operation"));
                    Map<String, Object> resultMap = extractSection(row, headerRow, sectionMap.get("Result"));

                    // 提取样品编号
                    String sampleSerial = (String) objectMap.getOrDefault("sample_serial", "sample_" + i);
                    json.put("sample_serial", sampleSerial);
                    json.put("object", objectMap);
                    json.put("operation", operationMap);
                    json.put("result", resultMap);

                    boolean ok = insertModuleData(json);
                    if (ok) successCount++;
                    else failCount++;

                } catch (Exception e) {
                    log.error("❌ 第 {} 行导入失败: {}", i + 1, e.getMessage());
                    failRows.add(i + 1);
                    failCount++;
                }
            }

        } catch (Exception e) {
            log.error("Excel 解析失败: {}", e.getMessage());
            throw new RuntimeException("文件解析错误: " + e.getMessage());
        }

        result.put("success", successCount);
        result.put("fail", failCount);
        result.put("failRows", failRows);
        return result;
    }

    /**
     * 根据第一行（Object / Operation / Result）计算每部分的列区间
     */
    private Map<String, List<Integer>> getSectionRanges(Row titleRow) {
        Map<String, List<Integer>> map = new HashMap<>();
        if (titleRow == null) throw new IllegalArgumentException("标题行为空");

        String currentSection = null;
        for (int i = 0; i < titleRow.getLastCellNum(); i++) {
            Cell cell = titleRow.getCell(i);
            String val = (cell == null) ? "" : cell.getStringCellValue().trim();
            if (!val.isEmpty()) {
                currentSection = val;
                map.putIfAbsent(currentSection, new ArrayList<>());
            }
            if (currentSection != null) {
                map.get(currentSection).add(i);
            }
        }
        return map;
    }

    /**
     * 提取行中某个部分（Object / Operation / Result）的键值对
     */
    private Map<String, Object> extractSection(Row dataRow, Row headerRow, List<Integer> indices) {
        Map<String, Object> map = new LinkedHashMap<>();
        if (indices == null) return map;

        for (int col : indices) {
            Cell header = headerRow.getCell(col);
            Cell value = dataRow.getCell(col);
            if (header == null) continue;
            String key = header.getStringCellValue().trim();
            String val = getCellValue(value);
            if (!key.isEmpty() && !val.isEmpty()) {
                map.put(key, val);
            }
        }
        return map;
    }

    /**
     * 获取单元格值为字符串
     */
    private String getCellValue(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING: return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell))
                    return cell.getDateCellValue().toString();
                else
                    return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
            case FORMULA: return cell.getCellFormula();
            default: return "";
        }
    }
}
