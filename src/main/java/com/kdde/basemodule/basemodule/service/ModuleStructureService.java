package com.kdde.basemodule.basemodule.service;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kdde.basemodule.basemodule.entity.ModuleStructureEntity;

import java.util.List;

public interface ModuleStructureService extends IService<ModuleStructureEntity> {
    boolean insertModuleStructure(JSONObject columnData,int moduleId);
}
