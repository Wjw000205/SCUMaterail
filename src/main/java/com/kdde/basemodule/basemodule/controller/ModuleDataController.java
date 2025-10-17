package com.kdde.basemodule.basemodule.controller;

import com.alibaba.fastjson2.JSONObject;
import com.kdde.basemodule.basemodule.common.utils.R;
import com.kdde.basemodule.basemodule.service.ModuleDataInsertService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/basemodule/moduledata")
@RestController
@Tag(name = "模板数据插入接口",description = "模板数据的增删查改和批量上传等")
public class ModuleDataController {
    @Autowired
    private ModuleDataInsertService moduleDataInsertService;

    @Operation(summary = "获取模板对应数据接口", description = "获取模板对应数据")
    @GetMapping("/list/{moduleId}")
    public R getModuleData(@PathVariable int moduleId) {
        return R.ok().put("moduleData", moduleDataInsertService.getModuleList(moduleId));
    }

    @Operation(summary = "获取模板对应数据详情接口",description = "根据模板对应数据的id获取模板的所有信息")
    @PostMapping("/getdetail")
    public R getModuleDetail(@RequestBody JSONObject jsonObject) {
        return R.ok().put("moduleData", moduleDataInsertService.getModuleDetailInfo(jsonObject));
    }

    @Operation(summary = "获取列名和列属性接口",description = "获取Object、Operation和Result表的列名和属性")
    @GetMapping("/getColumnInfo/{moduleId}")
    public R getColumnInfo(@PathVariable int moduleId) {
        return R.ok().put("columnInfo", moduleDataInsertService.getColumnInfo(moduleId));
    }

    @Operation(summary = "插入数据接口",description = "插入一条数据")
    @PostMapping("/insertsingle")
    public R insertSingle(@RequestBody JSONObject jsonObject) {
        moduleDataInsertService.insertModuleData(jsonObject);
        return R.ok();
    }
}
