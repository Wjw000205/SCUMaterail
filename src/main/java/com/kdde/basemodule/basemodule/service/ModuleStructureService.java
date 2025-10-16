package com.kdde.basemodule.basemodule.service;

import com.alibaba.fastjson2.JSONObject;
import com.kdde.basemodule.basemodule.entity.ModuleStructureEntity;

import java.util.List;

public interface ModuleStructureService {
    public boolean insertModuleStructure(JSONObject columnData,int moduleId);
}
