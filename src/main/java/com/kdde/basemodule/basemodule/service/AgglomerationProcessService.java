package com.kdde.basemodule.basemodule.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kdde.basemodule.basemodule.entity.AgglomerationProcessEntity;

import java.util.List;

/**
 * @author zxx
 * @Date 2025/5/24 23:27
 */
public interface AgglomerationProcessService extends IService<AgglomerationProcessEntity> {
    List<AgglomerationProcessEntity> searchAnyCol(String select3, List<String> select4);
}
