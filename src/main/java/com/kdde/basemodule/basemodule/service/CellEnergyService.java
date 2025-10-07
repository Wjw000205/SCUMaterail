package com.kdde.basemodule.basemodule.service;


import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kdde.basemodule.basemodule.common.utils.PageUtils;
import com.kdde.basemodule.basemodule.entity.CellEnergyEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
/**
 *
 *
 * @author wjw
 * @email wujiawei093@gmail.com
 * @date 2025-10-5 17:11:19
 */
public interface CellEnergyService extends IService<CellEnergyEntity> {
    PageUtils queryPage(Map<String, Object> params);

    CellEnergyEntity queryBySerial(String sampleSerial);

    int saveCellEnergy(CellEnergyEntity cellEnergyEntity);

    boolean removeCellEnergy(List<String> sampleSerial);

    boolean updateCellEnergy(String sampleSerial,Map<String,Object> body);

    String importFromExcel(MultipartFile file) throws Exception;
}
