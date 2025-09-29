package com.kdde.basemodule.basemodule.service.impl;

import com.kdde.basemodule.basemodule.common.utils.PageUtils;
import com.kdde.basemodule.basemodule.common.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.kdde.basemodule.basemodule.dao.BasicPyhsicsDao;
import com.kdde.basemodule.basemodule.entity.BasicPyhsicsEntity;
import com.kdde.basemodule.basemodule.service.BasicPyhsicsService;


@Service("basicPyhsicsService")
public class BasicPyhsicsServiceImpl extends ServiceImpl<BasicPyhsicsDao, BasicPyhsicsEntity> implements BasicPyhsicsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BasicPyhsicsEntity> page = this.page(
                new Query<BasicPyhsicsEntity>().getPage(params),
                new QueryWrapper<BasicPyhsicsEntity>()
        );

        return new PageUtils(page);
    }

}