package com.kdde.basemodule.basemodule.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kdde.basemodule.basemodule.common.utils.PageUtils;
import com.kdde.basemodule.basemodule.entity.LiteratureSampleEntity;
import com.kdde.basemodule.basemodule.entity.SourceLiteratureEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author lzl
 * @email lzl@gmail.com
 * @date 2025-04-17 16:34:41
 */
public interface LiteratureSampleService extends IService<LiteratureSampleEntity> {

    PageUtils queryPage(Map<String, Object> params);

    int getCount();

    List<LiteratureSampleEntity> searchAnyCol(String keyword, List<String> col);

    List<Map<String, Object>> queryTable(String tableName, List<String> fieldNames);
}

