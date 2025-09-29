package com.kdde.basemodule.basemodule.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kdde.basemodule.basemodule.entity.HteVivoEntity;

import java.util.List;

/**
 * @author zxx
 * @Date 2025/5/24 22:27
 */
public interface HteVivoService extends IService<HteVivoEntity> {
    List<HteVivoEntity> searchAnyCol(String select3, List<String> select4);
}
