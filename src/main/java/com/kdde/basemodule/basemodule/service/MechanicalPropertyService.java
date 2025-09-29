package com.kdde.basemodule.basemodule.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kdde.basemodule.basemodule.entity.MechanicalPropertyEntity;

import java.util.List;

/**
 * @author zxx
 * @Date 2025/5/24 23:30
 */
public interface MechanicalPropertyService extends IService<MechanicalPropertyEntity> {

    List<MechanicalPropertyEntity> searchAnyCol(String select3, List<String> select4);
}
