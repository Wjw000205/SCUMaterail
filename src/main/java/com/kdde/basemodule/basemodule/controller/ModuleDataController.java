package com.kdde.basemodule.basemodule.controller;

import com.alibaba.fastjson2.JSONObject;
import com.kdde.basemodule.basemodule.common.utils.R;
import com.kdde.basemodule.basemodule.service.ModuleDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RequestMapping("/basemodule/moduledata")
@RestController
@Tag(name = "模板数据插入接口",description = "模板数据的增删查改和批量上传等")
public class ModuleDataController {
    @Autowired
    private ModuleDataService moduleDataService;

    @Operation(summary = "获取模板对应数据接口", description = "获取模板对应数据")
    @GetMapping("/list/{moduleId}")
    public R getModuleData(@PathVariable int moduleId) {
        return R.ok().put("moduleData", moduleDataService.getModuleList(moduleId));
    }

    @Operation(summary = "获取模板对应数据详情接口",description = "根据模板对应数据的id获取模板的所有信息")
    @PostMapping("/getdetail")
    public R getModuleDetail(@RequestBody JSONObject jsonObject) {
        return R.ok().put("moduleData", moduleDataService.getModuleDetailInfo(jsonObject));
    }

    @Operation(summary = "获取列名和列属性接口",description = "获取Object、Operation和Result表的列名和属性")
    @GetMapping("/getColumnInfo/{moduleId}")
    public R getColumnInfo(@PathVariable int moduleId) {
        return R.ok().put("columnInfo", moduleDataService.getColumnInfo(moduleId));
    }

    @Operation(summary = "插入数据接口",description = "插入一条数据")
    @PostMapping("/insertsingle")
    public R insertSingle(@RequestBody JSONObject jsonObject) {
        moduleDataService.insertModuleData(jsonObject);
        return R.ok();
    }

    @Operation(summary = "下载数据模板接口",description = "获取需要批量上传数据的模板样例")
    @GetMapping("/downloadmodule/{moduleId}")
    public void downloadModule(@PathVariable int moduleId, HttpServletResponse response){
        try {
            byte[] res = moduleDataService.generateTemplate(moduleId);
            if (res == null || res.length == 0) {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                return;
            }

            // ✅ 关键：重置响应，防止被Spring默认缓存header
            response.reset();

            // ✅ 设置文件类型为 Excel
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

            // ✅ 设置下载文件名（URL 编码，防止中文乱码）
            String fileName = URLEncoder.encode("模板文件.xlsx", "UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

            // ✅ 设置长度
            response.setContentLength(res.length);

            // ✅ 写入输出流
            response.getOutputStream().write(res);
            response.flushBuffer();

        } catch (Exception e) {
            e.printStackTrace();
            // ⚠️ 不要调用 sendError，否则会触发“response committed”异常
        }
    }
    @Operation(summary = "批量上传模块数据", description = "上传 Excel 模板文件进行批量入库")
    @PostMapping(path = "/upload/{moduleId}",value = "/upload/{moduleId}", consumes = "multipart/form-data")
    public R uploadExcel(
            @Parameter(description = "模块 ID", required = true)
            @PathVariable int moduleId,

            @Parameter(
            description = "上传的 Excel 文件",
            required = true,
            content = @Content(mediaType = "multipart/form-data",
                    schema = @Schema(type = "string", format = "binary"))
            )
            @RequestParam("file") MultipartFile file) {
        return R.ok().put("message",moduleDataService.batchUpload(moduleId, file));
    }
}
