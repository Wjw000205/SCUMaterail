package com.kdde.basemodule.basemodule.service;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kdde.basemodule.basemodule.entity.ModuleEntity;

import java.util.List;

public interface ModuleService extends IService<ModuleEntity> {
    public List<ModuleEntity> getAllModule(int parentId);

    public int createModule(JSONObject module);

    public boolean deleteModules(List<Integer> moduleNames);

    public boolean updateModule(ModuleEntity module);
}
