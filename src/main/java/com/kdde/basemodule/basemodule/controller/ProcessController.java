package com.kdde.basemodule.basemodule.controller;

import com.kdde.basemodule.basemodule.common.utils.R;
import com.kdde.basemodule.basemodule.service.ProcessService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "模板审核接口")
@RestController
@RequestMapping("/basemodule/process")
public class ProcessController {
    @Autowired
    private ProcessService processService;

    @Operation(summary = "获取需要审核列表接口")
    @GetMapping("/list")
    public R getProcessList(){
        return R.ok().put("data",processService.getModules());
    }

    @Operation(summary = "获取详细信息接口 ")
    @GetMapping("/detail/{id}")
    public R getProcessDetail(@PathVariable("id") int id){
        return R.ok().put("data",processService.getModuleStructure(id));
    }
}
