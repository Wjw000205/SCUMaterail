package com.kdde.basemodule.basemodule.controller;

import java.util.Arrays;
import java.util.Map;

import com.kdde.basemodule.basemodule.common.utils.PageUtils;
import com.kdde.basemodule.basemodule.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kdde.basemodule.basemodule.entity.ExternalSurfaceFormationAEntity;
import com.kdde.basemodule.basemodule.service.ExternalSurfaceFormationAService;



/**
 * 
 *
 * @author lzl
 * @email lzl@gmail.com
 * @date 2025-04-17 16:34:41
 */
@RestController
@RequestMapping("basemodule/externalsurfaceformationa")
public class ExternalSurfaceFormationAController {
    @Autowired
    private ExternalSurfaceFormationAService externalSurfaceFormationAService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = externalSurfaceFormationAService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{sampleserial}")
    public R info(@PathVariable("sampleserial") String sampleserial){
		ExternalSurfaceFormationAEntity externalSurfaceFormationA = externalSurfaceFormationAService.getById(sampleserial);

        return R.ok().put("externalSurfaceFormationA", externalSurfaceFormationA);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ExternalSurfaceFormationAEntity externalSurfaceFormationA){
		externalSurfaceFormationAService.save(externalSurfaceFormationA);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ExternalSurfaceFormationAEntity externalSurfaceFormationA){
		externalSurfaceFormationAService.updateById(externalSurfaceFormationA);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody String[] sampleserials){
		externalSurfaceFormationAService.removeByIds(Arrays.asList(sampleserials));

        return R.ok();
    }

}
