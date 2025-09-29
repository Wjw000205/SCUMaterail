package com.kdde.basemodule.basemodule.controller;

import com.kdde.basemodule.basemodule.common.utils.R;
import com.kdde.basemodule.basemodule.dto.DynamicJoinRequest;
import com.kdde.basemodule.basemodule.dto.SearchDTO;
import com.kdde.basemodule.basemodule.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.kdde.basemodule.basemodule.common.constant.TableMappingConstant.*;

/**
 * @author zxx
 * @Date 2025/5/20 19:56
 */
@RestController
@RequestMapping("/basemodule/search")
@RequiredArgsConstructor
public class RecordSearchController {

    private final SurfaceOfMaterialsBService surfaceOfMaterialsBService;

    private final BasicPyhsicsBService basicPyhsicsBService;

    private final AptureBService aptureBService;

    private final ImplantBodyBService implantBodyBService;

    private final SourceLiteratureService sourceLiteratureService;

    private final LiteratureSampleService literatureSampleService;

    private final SurfaceOfMaterialsAService surfaceOfMaterialsAService;

    private final BasicPyhsicsAService basicPyhsicsAService;

    private final ExternalSurfaceFormationAService externalSurfaceFormationAService;

    private final ExternalXrdAService externalXrdAService;

    private final ExternalExperimentAService externalExperimentAService;

    private final DynamicJoinService dynamicJoinService;

    private final HteVivoService hteVivoService;

    private final HteVitroService hteVitroService;

    private final AgglomerationProcessService agglomerationProcessService;

    private final GeometryService geometryService;

    private final MechanicalPropertyService mechanicalPropertyService;

    @PostMapping("/query")
    public R searchAnyColomn(@RequestBody SearchDTO searchDTO){
        String select1 = searchDTO.getSelect1();
        String select2 = searchDTO.getSelect2();
        String select3 = searchDTO.getSelect3();
        List<String> select4 = searchDTO.getSelect4();


        if(select1.equals(MATERIAL_A)){
            String tableName = TABLE_MAPPING_A.get(select2);
            switch (tableName){
                case "材料表面性能表":
                    return handleSearchResult(surfaceOfMaterialsAService.searchAnyCol(select3, select4));
                case "基本物理性能表_new":
                    return handleSearchResult(basicPyhsicsAService.searchAnyCol(select3, select4));
                case "体外类骨磷灰石形成表_体外类骨磷灰石形成_表面成分表":
                    return handleSearchResult(externalSurfaceFormationAService.searchAnyCol(select3, select4));
                case "体外类骨磷灰石形成表_体外类骨磷灰石形成_XRD":
                    return handleSearchResult(externalXrdAService.searchAnyCol(select3, select4));
                case "体外类骨磷灰石形成表_体外类骨磷灰石形成表":
                    return handleSearchResult(externalExperimentAService.searchAnyCol(select3, select4));
                case "文献数据来源信息表":
                    return handleSearchResult(sourceLiteratureService.searchAnyCol(select3, select4));
                case "文献样品基本信息表":
                    return handleSearchResult(literatureSampleService.searchAnyCol(select3, select4));
            }
        } else if(select1.equals(MATERIAL_B)){
            String tableName = TABLE_MAPPING_B.get(select2);
            switch (tableName){
                case "02_材料组成成分表":
                    return handleSearchResult(surfaceOfMaterialsBService.searchAnyCol(select3, select4));
                case "03_基本物理性能表":
                    return handleSearchResult(basicPyhsicsBService.searchAnyCol(select3, select4));
                case "04_孔径相关性能表":
                    return handleSearchResult(aptureBService.searchAnyCol(select3, select4));
                case "05_体内植入实验表":
                    return handleSearchResult(implantBodyBService.searchAnyCol(select3, select4));
                case "07_文献数据来源信息表":
                    return handleSearchResult(sourceLiteratureService.searchAnyCol(select3, select4));
                case "08_文献样品基本信息表":
                    return handleSearchResult(literatureSampleService.searchAnyCol(select3, select4));
            }
        } else if(select1.equals(MATERIAL_C)){
            String tableName = TABLE_MAPPING_C.get(select2);
            switch (tableName){
                case "hte_vivo":
                    return handleSearchResult(hteVivoService.searchAnyCol(select3, select4));
                case "hte_vitro":
                    return handleSearchResult(hteVitroService.searchAnyCol(select3, select4));
            }
        }else{
            String tableName = TABLE_MAPPING_D.get(select2);
            switch (tableName){
                case "agglomerationprocess":
                    return handleSearchResult(agglomerationProcessService.searchAnyCol(select3, select4));
                case "geometry":
                    return handleSearchResult(geometryService.searchAnyCol(select3, select4));
                case "mechanicalproperty":
                    return handleSearchResult(mechanicalPropertyService.searchAnyCol(select3, select4));
            }

        }

        return R.error("表不存在!");
    }

    private R handleSearchResult(List<?> data) {
        if (data == null || data.isEmpty()) {
            return R.error("没有找到匹配的记录");
        }
        return R.ok().put("data", data);
    }


    @PostMapping("/querycolums")
    public R query(@RequestBody Map<String, Object> params) {
        String tableName = (String) params.get("tableName");
        @SuppressWarnings("unchecked")
        List<String> fieldNames = (List<String>) params.get("fieldNames");
        String choice = (String) params.get("choice");

        if(choice.equals(MATERIAL_A)){
            String table = TABLE_MAPPING_A.get(tableName);
            switch (table){
                case "材料表面性能表":
                    return R.ok().put("data", surfaceOfMaterialsAService.queryTable(tableName,fieldNames));
                case "基本物理性能表_new":
                    return R.ok().put("data", basicPyhsicsAService.queryTable(tableName,fieldNames));
                case "体外类骨磷灰石形成表_体外类骨磷灰石形成_表面成分表":
                    return R.ok().put("data", externalSurfaceFormationAService.queryTable(tableName,fieldNames));
                case "体外类骨磷灰石形成表_体外类骨磷灰石形成_XRD":
                    return R.ok().put("data", externalXrdAService.queryTable(tableName,fieldNames));
                case "体外类骨磷灰石形成表_体外类骨磷灰石形成表":
                    return R.ok().put("data", externalExperimentAService.queryTable(tableName,fieldNames));
                case "文献数据来源信息表":
                    return R.ok().put("data", sourceLiteratureService.queryTable(tableName,fieldNames));
                case "文献样品基本信息表":
                    return R.ok().put("data", literatureSampleService.queryTable(tableName,fieldNames));
            }
        }else{
            String table = TABLE_MAPPING_B.get(tableName);
            switch (table){
                case "02_材料组成成分表":
                    return R.ok().put("data", surfaceOfMaterialsBService.queryTable(tableName,fieldNames));
                case "03_基本物理性能表":
                    return R.ok().put("data", basicPyhsicsBService.queryTable(tableName,fieldNames));
                case "04_孔径相关性能表":
                    return R.ok().put("data", aptureBService.queryTable(tableName,fieldNames));
                case "05_体内植入实验表":
                    return R.ok().put("data", implantBodyBService.queryTable(tableName,fieldNames));
                case "07_文献数据来源信息表":
                    return R.ok().put("data", sourceLiteratureService.queryTable(tableName,fieldNames));
                case "08_文献样品基本信息表":
                    return R.ok().put("data", literatureSampleService.queryTable(tableName,fieldNames));
            }
        }

        return R.ok().put("data", null);
    }

    /**
     * 动态多表联合查询
     * @param request 包含表名和字段名的请求体
     * @return 联合查询结果
     */
    @PostMapping("/dynamic")
    public R dynamicJoinQuery(@RequestBody DynamicJoinRequest request) {
        return R.ok().put("data", dynamicJoinService.joinTablesBySampleSerial(request));
    }






}
