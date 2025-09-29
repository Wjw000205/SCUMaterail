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

import com.kdde.basemodule.basemodule.entity.SourceLiteratureEntity;
import com.kdde.basemodule.basemodule.service.SourceLiteratureService;



/**
 * 
 *
 * @author lzl
 * @email lzl@gmail.com
 * @date 2025-04-17 16:34:41
 */
@RestController
@RequestMapping("/sourceliterature")
public class SourceLiteratureController {
    @Autowired
    private SourceLiteratureService sourceLiteratureService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sourceLiteratureService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{documentserial}")
    public R info(@PathVariable("documentserial") String documentserial){
		SourceLiteratureEntity sourceLiterature = sourceLiteratureService.getById(documentserial);

        return R.ok().put("sourceLiterature", sourceLiterature);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody SourceLiteratureEntity sourceLiterature){
		sourceLiteratureService.save(sourceLiterature);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SourceLiteratureEntity sourceLiterature){
		sourceLiteratureService.updateById(sourceLiterature);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody String[] documentserials){
		sourceLiteratureService.removeByIds(Arrays.asList(documentserials));

        return R.ok();
    }

}
