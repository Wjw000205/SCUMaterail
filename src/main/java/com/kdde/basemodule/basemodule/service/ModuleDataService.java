package com.kdde.basemodule.basemodule.service;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ModuleDataService {
    List<Map<String,Object>> getModuleList(int moduleId);

    JSONObject getModuleDetailInfo(JSONObject jsonObject);

    boolean insertModuleData(JSONObject jsonObject);

    JSONObject getColumnInfo(int moduleId);

    byte[] generateTemplate(int moduleId);

    Map<String, Object> batchUpload(int moduleId, MultipartFile file);

}
