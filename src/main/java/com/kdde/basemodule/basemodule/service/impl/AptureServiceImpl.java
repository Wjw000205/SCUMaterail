package com.kdde.basemodule.basemodule.service.impl;

import com.kdde.basemodule.basemodule.common.utils.PageUtils;
import com.kdde.basemodule.basemodule.common.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.kdde.basemodule.basemodule.dao.AptureDao;
import com.kdde.basemodule.basemodule.entity.AptureEntity;
import com.kdde.basemodule.basemodule.service.AptureService;


@Service("aptureService")
public class AptureServiceImpl extends ServiceImpl<AptureDao, AptureEntity> implements AptureService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AptureEntity> page = this.page(
                new Query<AptureEntity>().getPage(params),
                new QueryWrapper<AptureEntity>()
        );

        return new PageUtils(page);
    }

}