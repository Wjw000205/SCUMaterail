package com.kdde.basemodule.basemodule.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.kdde.basemodule.basemodule.common.utils.PageUtils;
import com.kdde.basemodule.basemodule.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.kdde.basemodule.basemodule.entity.AptureBEntity;
import com.kdde.basemodule.basemodule.service.AptureBService;



/**
 * 
 *
 * @author lzl
 * @email lzl@gmail.com
 * @date 2025-04-17 16:34:41
 */
@RestController
@RequestMapping("basemodule/aptureb")
public class AptureBController {
    @Autowired
    private AptureBService aptureBService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = aptureBService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{sampleserial}")
    public R info(@PathVariable("sampleserial") String sampleserial){
		AptureBEntity aptureB = aptureBService.getById(sampleserial);

        return R.ok().put("aptureB", aptureB);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AptureBEntity aptureB){
		aptureBService.save(aptureB);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AptureBEntity aptureB){
		aptureBService.updateById(aptureB);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody String[] sampleserials){
		aptureBService.removeByIds(Arrays.asList(sampleserials));

        return R.ok();
    }




}
