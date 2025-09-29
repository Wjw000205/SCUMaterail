package com.kdde.basemodule.basemodule.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kdde.basemodule.basemodule.common.utils.PageUtils;
import com.kdde.basemodule.basemodule.entity.UploadEntity;

import java.util.Map;

/**
 * 
 *
 * @author lzl
 * @email lzl@gmail.com
 * @date 2025-04-17 16:34:41
 */
public interface UploadService extends IService<UploadEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

