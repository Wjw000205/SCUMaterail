package com.kdde.basemodule.basemodule.service;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public interface DynamicTableService {
    void createTablesFromJson(JSONObject jsonData);
}
