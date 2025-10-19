package com.kdde.basemodule.basemodule.service;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kdde.basemodule.basemodule.entity.ModuleEntity;

import java.util.List;

public interface ModuleService extends IService<ModuleEntity> {
    List<ModuleEntity> getAllModule(int parentId);

    int createModule(JSONObject module);

    boolean deleteModules(List<Integer> moduleNames);

    boolean updateModule(ModuleEntity module);
}
