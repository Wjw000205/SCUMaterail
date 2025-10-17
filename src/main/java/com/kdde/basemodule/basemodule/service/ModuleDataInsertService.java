package com.kdde.basemodule.basemodule.service;

import com.alibaba.fastjson2.JSONObject;

import java.util.List;
import java.util.Map;

public interface ModuleDataInsertService {
    public List<Map<String,Object>> getModuleList(int moduleId);

    public JSONObject getModuleDetailInfo(JSONObject jsonObject);

    public boolean insertModuleData(JSONObject jsonObject);

    public JSONObject getColumnInfo(int moduleId);
}
