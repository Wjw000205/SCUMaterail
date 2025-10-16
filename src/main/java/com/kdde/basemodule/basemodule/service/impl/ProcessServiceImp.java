package com.kdde.basemodule.basemodule.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kdde.basemodule.basemodule.dao.ModuleDao;
import com.kdde.basemodule.basemodule.dao.ModuleStructureDao;
import com.kdde.basemodule.basemodule.entity.ModuleEntity;
import com.kdde.basemodule.basemodule.entity.ModuleStructureEntity;
import com.kdde.basemodule.basemodule.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessServiceImp implements ProcessService {
    @Autowired
    private ModuleDao moduleDao;

    @Autowired
    private ModuleStructureDao moduleStructureDao;

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
            if(moduleStructureEntity.getBelong().equals("object")){
                objectArray.add(moduleStructureEntity);
            }
            if(moduleStructureEntity.getBelong().equals("operation")){
                operationArray.add(moduleStructureEntity);
            }
            if(moduleStructureEntity.getBelong().equals("result")){
                resultArray.add(moduleStructureEntity);
            }
        }
        res.put("object", objectArray);
        res.put("operation", operationArray);
        res.put("result", resultArray);

        return res;
    }
}
