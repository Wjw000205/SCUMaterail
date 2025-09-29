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

import com.kdde.basemodule.basemodule.entity.ExternalXrdAEntity;
import com.kdde.basemodule.basemodule.service.ExternalXrdAService;



/**
 * 
 *
 * @author lzl
 * @email lzl@gmail.com
 * @date 2025-04-17 16:34:41
 */
@RestController
@RequestMapping("basemodule/externalxrda")
public class ExternalXrdAController {
    @Autowired
    private ExternalXrdAService externalXrdAService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = externalXrdAService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{sampleserial}")
    public R info(@PathVariable("sampleserial") String sampleserial){
		ExternalXrdAEntity externalXrdA = externalXrdAService.getById(sampleserial);

        return R.ok().put("externalXrdA", externalXrdA);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ExternalXrdAEntity externalXrdA){
		externalXrdAService.save(externalXrdA);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody ExternalXrdAEntity externalXrdA){
		externalXrdAService.updateById(externalXrdA);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody String[] sampleserials){
		externalXrdAService.removeByIds(Arrays.asList(sampleserials));

        return R.ok();
    }

}
