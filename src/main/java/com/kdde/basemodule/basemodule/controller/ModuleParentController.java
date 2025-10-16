package com.kdde.basemodule.basemodule.controller;

import com.kdde.basemodule.basemodule.common.utils.R;
import com.kdde.basemodule.basemodule.service.ModuleParentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "模板大类管理接口",description = "模板大类的增删查改")
@RestController
@RequestMapping("/basemodule/moduleparent")
public class ModuleParentController {
    @Autowired
    private ModuleParentService moduleParentService;

    @Operation(summary = "获取模板大类列表")
    @GetMapping("/list")
    public R getModuleParentList(){
        return R.ok().put("moduleParentList", moduleParentService.getModuleParentList());
    }
}
