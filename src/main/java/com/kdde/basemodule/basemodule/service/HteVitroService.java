package com.kdde.basemodule.basemodule.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kdde.basemodule.basemodule.entity.HteVitroEntity;

import java.util.List;

/**
 * @author zxx
 * @Date 2025/5/24 23:29
 */
public interface HteVitroService extends IService<HteVitroEntity> {
    List<HteVitroEntity> searchAnyCol(String select3, List<String> select4);
}
