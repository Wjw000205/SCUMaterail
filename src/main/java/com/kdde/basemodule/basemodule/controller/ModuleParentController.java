package com.kdde.basemodule.basemodule.controller;

import com.kdde.basemodule.basemodule.common.utils.R;
import com.kdde.basemodule.basemodule.service.ModuleParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basemodule/moduleparent")
public class ModuleParentController {
    @Autowired
    private ModuleParentService moduleParentService;

    @GetMapping("/list")
    public R getModuleParentList(){
        return R.ok().put("moduleParentList", moduleParentService.getModuleParentList());
    }
}
