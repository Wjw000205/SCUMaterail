package com.kdde.basemodule.basemodule.service;

import com.alibaba.fastjson2.JSONObject;
import com.kdde.basemodule.basemodule.entity.ModuleEntity;
import com.kdde.basemodule.basemodule.entity.ModuleStructureEntity;

import java.util.List;

public interface ProcessService {
    List<ModuleEntity> getModules();

    JSONObject getModuleStructure(int moduleId);

    boolean createModuleTable(JSONObject jsonObject);
}
