package com.kdde.basemodule.basemodule.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kdde.basemodule.basemodule.dao.ModuleStructureDao;
import com.kdde.basemodule.basemodule.entity.ModuleStructureEntity;
import com.kdde.basemodule.basemodule.service.ModuleStructureService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleStructureServiceImp extends ServiceImpl<ModuleStructureDao, ModuleStructureEntity> implements ModuleStructureService {
    @Override
    public boolean insertModuleStructure(JSONObject columnData,int moduleId) {
        List<ModuleStructureEntity> objectList = JSON.parseArray(columnData.getString("object"), ModuleStructureEntity.class);
        List<ModuleStructureEntity> operationList = JSON.parseArray(columnData.getString("operation"), ModuleStructureEntity.class);
        List<ModuleStructureEntity> resultList = JSON.parseArray(columnData.getString("result"), ModuleStructureEntity.class);
        try {
            for (ModuleStructureEntity moduleStructureEntity : objectList) {
                moduleStructureEntity.setModuleId(moduleId);
                moduleStructureEntity.setBelong("object");
                save(moduleStructureEntity);
            }
            for (ModuleStructureEntity moduleStructureEntity : operationList) {
                moduleStructureEntity.setModuleId(moduleId);
                moduleStructureEntity.setBelong("operation");
                save(moduleStructureEntity);
            }
            for (ModuleStructureEntity moduleStructureEntity : resultList) {
                moduleStructureEntity.setModuleId(moduleId);
                moduleStructureEntity.setBelong("result");
                save(moduleStructureEntity);
            }
            return true;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }
}
