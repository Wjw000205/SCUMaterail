package com.kdde.basemodule.basemodule.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kdde.basemodule.basemodule.common.utils.PageUtils;
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
public interface SourceLiteratureService extends IService<SourceLiteratureEntity> {

    PageUtils queryPage(Map<String, Object> params);


    Integer sum();


    int getCount();

    List<SourceLiteratureEntity> searchAnyCol(String keyword, List<String> col);

    List<Map<String, Object>> queryTable(String tableName, List<String> fieldNames);
}

