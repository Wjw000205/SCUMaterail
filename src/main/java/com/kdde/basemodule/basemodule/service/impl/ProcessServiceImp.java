package com.kdde.basemodule.basemodule.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.kdde.basemodule.basemodule.dao.ModuleDao;
import com.kdde.basemodule.basemodule.dao.ModuleLinkTableDao;
import com.kdde.basemodule.basemodule.dao.ModuleStructureDao;
import com.kdde.basemodule.basemodule.entity.ModuleEntity;
import com.kdde.basemodule.basemodule.entity.ModuleLinkTableEntity;
import com.kdde.basemodule.basemodule.entity.ModuleStructureEntity;
import com.kdde.basemodule.basemodule.service.DynamicTableService;
import com.kdde.basemodule.basemodule.service.ProcessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ProcessServiceImp implements ProcessService {
    @Autowired
    private ModuleDao moduleDao;

    @Autowired
    private ModuleStructureDao moduleStructureDao;

    @Autowired
    private DynamicTableService dynamicTableService;

    @Override
    public List<ModuleEntity> getModules() {
        QueryWrapper<ModuleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("state", 0);
        return moduleDao.selectList(queryWrapper);
    }

    @Override
    public JSONObject getModuleStructure(int moduleId) {
        JSONObject res = new JSONObject();
        QueryWrapper<ModuleStructureEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("module_id", moduleId);
        List<ModuleStructureEntity> moduleStructureEntities = moduleStructureDao.selectList(queryWrapper);
        JSONArray objectArray = new JSONArray();
        JSONArray operationArray = new JSONArray();
        JSONArray resultArray = new JSONArray();
        for (ModuleStructureEntity moduleStructureEntity : moduleStructureEntities) {
            if (moduleStructureEntity.getBelong().equals("object")) {
                objectArray.add(moduleStructureEntity);
            }
            if (moduleStructureEntity.getBelong().equals("operation")) {
                operationArray.add(moduleStructureEntity);
            }
            if (moduleStructureEntity.getBelong().equals("result")) {
                resultArray.add(moduleStructureEntity);
            }
        }
        res.put("object", objectArray);
        res.put("operation", operationArray);
        res.put("result", resultArray);

        return res;
    }

    @Override
    public boolean createModuleTable(JSONObject jsonObject) {
        try {
            dynamicTableService.createTablesFromJson(jsonObject);
            //建表成功后更新结构表
            JSONArray objectArray = jsonObject.getJSONArray("object");
            JSONArray operationArray = jsonObject.getJSONArray("operation");
            JSONArray resultArray = jsonObject.getJSONArray("result");

            updateInfo(objectArray);
            updateInfo(operationArray);
            updateInfo(resultArray);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    private void updateInfo(JSONArray operationArray) {
        for (int i = 0; i < operationArray.size(); i++) {
            JSONObject obj = operationArray.getJSONObject(i);
            UpdateWrapper<ModuleStructureEntity> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("id", obj.getInteger("id"))
                    .set("column_name", obj.getString("columnName"))
                    .set("column_contribution", obj.getString("columnContribution"));
            moduleStructureDao.update(null, updateWrapper);
        }
    }
}
