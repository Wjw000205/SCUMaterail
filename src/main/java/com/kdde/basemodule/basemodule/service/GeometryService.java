package com.kdde.basemodule.basemodule.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kdde.basemodule.basemodule.entity.GeometryEntity;

import java.util.List;

/**
 * @author zxx
 * @Date 2025/5/24 23:28
 */
public interface GeometryService extends IService<GeometryEntity> {

    List<GeometryEntity> searchAnyCol(String select3, List<String> select4);
}
