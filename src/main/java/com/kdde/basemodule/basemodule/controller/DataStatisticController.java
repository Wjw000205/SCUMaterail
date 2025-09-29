package com.kdde.basemodule.basemodule.controller;

import com.baomidou.mybatisplus.extension.service.additional.query.impl.QueryChainWrapper;
import com.kdde.basemodule.basemodule.common.utils.PageUtils;
import com.kdde.basemodule.basemodule.common.utils.R;
import com.kdde.basemodule.basemodule.entity.SourceLiteratureEntity;
import com.kdde.basemodule.basemodule.service.ImplantBodyAService;
import com.kdde.basemodule.basemodule.service.ImplantBodyBService;
import com.kdde.basemodule.basemodule.service.SourceLiteratureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/count")
public class DataStatisticController {

    @Autowired
    SourceLiteratureService sourceLiteratureService;

    @Autowired
    ImplantBodyBService implantBodyBService;

    @Autowired
    ImplantBodyAService implantBodyAService;


    /**
     * 文献数据来源
     */
    @GetMapping("/literature")
    public R literature(){
////        Integer cnt = sourceLiteratureService.getsum();
//        QueryChainWrapper<SourceLiteratureEntity> query = sourceLiteratureService.query();
//        Integer count = query.count();
//        return R.ok().put("sum",count);
        Integer sum = sourceLiteratureService.sum();
        return R.ok().put("sum",sum);
    }

    /**
     * 【生物学评价】的记录条数
     * @return
     */
    @GetMapping("/bio")
    public R bio(){
        Integer sumA = implantBodyAService.sum();
        Integer sumB = implantBodyBService.sum();
        return R.ok().put("sum",sumA+sumB);
    }




}
