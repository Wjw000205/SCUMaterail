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

import com.kdde.basemodule.basemodule.entity.LiteratureSampleEntity;
import com.kdde.basemodule.basemodule.service.LiteratureSampleService;



/**
 * 
 *
 * @author lzl
 * @email lzl@gmail.com
 * @date 2025-04-17 16:34:41
 */
@RestController
@RequestMapping("basemodule/literaturesample")
public class LiteratureSampleController {
    @Autowired
    private LiteratureSampleService literatureSampleService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = literatureSampleService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{sampleserial}")
    public R info(@PathVariable("sampleserial") String sampleserial){
		LiteratureSampleEntity literatureSample = literatureSampleService.getById(sampleserial);

        return R.ok().put("literatureSample", literatureSample);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody LiteratureSampleEntity literatureSample){
		literatureSampleService.save(literatureSample);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody LiteratureSampleEntity literatureSample){
		literatureSampleService.updateById(literatureSample);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody String[] sampleserials){
		literatureSampleService.removeByIds(Arrays.asList(sampleserials));

        return R.ok();
    }

}
