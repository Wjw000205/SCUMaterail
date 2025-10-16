package com.kdde.basemodule.basemodule.controller;

import com.alibaba.fastjson2.JSONObject;
import com.kdde.basemodule.basemodule.common.utils.R;
import com.kdde.basemodule.basemodule.entity.ModuleEntity;
import com.kdde.basemodule.basemodule.service.ModuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Tag(name = "模板操作接口",description = "用于模板的获取和申请创建等")
@RequestMapping("/basemodule/module")
public class ModuleController {
    @Autowired
    private ModuleService moduleService;

    @Operation(summary = "获取模板列表",description = "根据父目录的id获取该目录下的所有模板列表")
    @GetMapping("/getmodules/{parent}")
    public R getModuleList(@PathVariable int parent) {
        return R.ok().put("module_list",moduleService.getAllModule(parent));
    }

    @Operation(summary = "申请创建新模板接口",description = "用户发起新模板创建请求")
    @PostMapping("/create")
    public R createModule(@RequestBody JSONObject moduleInfo){
        int result = moduleService.createModule(moduleInfo);
        if(result==1){
            return R.ok("保存成功");
        }
        else if(result==-1){
            return R.ok("模板名已存在");
        }
        else{
            return R.ok("系统错误，请联系管理员处理");
        }
    }

    @Operation(summary = "删除模板接口",description = "用户发起删除模板请求，需等待管理员审核")
    @PostMapping("/delete")
    public R delete(@RequestBody List<Integer> moduleNames) {
        boolean result = moduleService.deleteModules(moduleNames);
        if (result) {
            return R.ok("删除成功");
        } else {
            return R.ok("删除失败");
        }
    }

    @Operation(summary = "用户更新模板信息接口",description = "更新结果需要等待管理员审核")
    @PostMapping("/update")
    public R update(@RequestBody ModuleEntity module){
        boolean result = moduleService.updateModule(module);
        if (result) {
            return R.ok("更新成功");
        }
        else {
            return R.ok("更新失败，请稍后重试");
        }
    }
}
