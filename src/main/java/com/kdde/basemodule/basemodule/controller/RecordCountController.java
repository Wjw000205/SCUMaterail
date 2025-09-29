package com.kdde.basemodule.basemodule.controller;

import com.kdde.basemodule.basemodule.common.utils.R;
import com.kdde.basemodule.basemodule.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zxx
 * @Date 2025/4/18 18:34
 */

@RestController
@RequestMapping("/basemodule/count")
@RequiredArgsConstructor
public class RecordCountController {

    private final AptureBService aptureBService;

    private final BasicPyhsicsAService basicPyhsicsAService;

    private final BasicPyhsicsBService basicPyhsicsBService;

    private final ExternalExperimentAService externalExperimentAService;

    private final ExternalSurfaceFormationAService externalSurfaceFormationAService;

    private final ExternalXrdAService externalXrdAService;

    private final ImplantBodyAService implantBodyAService;

    private final ImplantBodyBService implantBodyBService;

    private final LiteratureSampleService literatureSampleService;

    private final SourceLiteratureService sourceLiteratureService;

    private final SurfaceOfMaterialsAService surfaceOfMaterialsAService;

    private final SurfaceOfMaterialsBService surfaceOfMaterialsBService;

    /**
     * 【文献】记录条数
     * @return 数量int
     */
    @GetMapping("/1")
    public R countLiterature(){
        int sum = literatureSampleService.getCount() + sourceLiteratureService.getCount();

        return R.ok().put("data", sum);
    }


    /**
     * 【计算】记录条数
     * @return
     */
    @GetMapping("/2")
    public R countCalculation(){
        return R.ok().put("data", 0);
    }


    /**
     * 【生产】记录条数
     * @return
     */
    @GetMapping("/3")
    public R countProduce(){
        return R.ok().put("data", 0);
    }

    /**
     * 【实验】记录条数
     * @return
     */
    @GetMapping("/4")
    public R countExperiment(){
        int sum = 0;
        sum += implantBodyAService.getCount();
        sum += implantBodyBService.getCount();
        sum += externalSurfaceFormationAService.getCount();
        sum += externalExperimentAService.getCount();
        sum += externalXrdAService.getCount();

        return R.ok().put("data", sum);
    }

    /**
     * 【材料制备】记录条数;
     * @return
     */
    @GetMapping("/5")
    public R countMaterialPreparation(){

        return R.ok().put("data", surfaceOfMaterialsBService.getCount());
    }

    /**
     * 【理化性质】记录条数
     * @return
     */
    @GetMapping("/6")
    public R countPhysicalAndChemical(){
        int sum = 0;
        sum += basicPyhsicsAService.getCount();
        sum += basicPyhsicsBService.getCount();
        sum += aptureBService.getCount();
        sum += surfaceOfMaterialsAService.getCount();
        sum += externalSurfaceFormationAService.getCount();
        sum += externalExperimentAService.getCount();
        sum += externalXrdAService.getCount();

        return R.ok().put("data", sum);
    }

    /**
     * 【生物学评价】记录条数
     * @return
     */
    @GetMapping("/7")
    public R countBioEvaluation(){

        return R.ok().put("data", implantBodyAService.getCount() + implantBodyBService.getCount());
    }


    /**
     * 【临床评价】记录条数
     * @return
     */
    @GetMapping("/8")
    public R countClinical(){
        return R.ok().put("data", 0);
    }


}
