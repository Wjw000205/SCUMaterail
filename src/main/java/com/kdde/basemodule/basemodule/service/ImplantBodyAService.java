package com.kdde.basemodule.basemodule.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kdde.basemodule.basemodule.common.utils.PageUtils;
import com.kdde.basemodule.basemodule.entity.ImplantBodyAEntity;

import java.util.Map;

/**
 * 
 *
 * @author lzl
 * @email lzl@gmail.com
 * @date 2025-04-17 16:34:41
 */
public interface ImplantBodyAService extends IService<ImplantBodyAEntity> {

    PageUtils queryPage(Map<String, Object> params);

    Integer sum();

    int getCount();
}

