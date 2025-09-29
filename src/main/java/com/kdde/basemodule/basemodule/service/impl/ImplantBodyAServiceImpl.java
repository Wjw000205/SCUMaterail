package com.kdde.basemodule.basemodule.service.impl;

import com.kdde.basemodule.basemodule.common.utils.PageUtils;
import com.kdde.basemodule.basemodule.dao.ImplantBodyBDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kdde.basemodule.basemodule.common.utils.Query;
import com.kdde.basemodule.basemodule.dao.ImplantBodyADao;
import com.kdde.basemodule.basemodule.entity.ImplantBodyAEntity;
import com.kdde.basemodule.basemodule.service.ImplantBodyAService;

import javax.xml.ws.Action;


@Service("implantBodyAService")
public class ImplantBodyAServiceImpl extends ServiceImpl<ImplantBodyADao, ImplantBodyAEntity> implements ImplantBodyAService {

    @Autowired
    ImplantBodyADao implantBodyADao;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ImplantBodyAEntity> page = this.page(
                new Query<ImplantBodyAEntity>().getPage(params),
                new QueryWrapper<ImplantBodyAEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public Integer sum() {
        QueryWrapper<ImplantBodyAEntity> queryWrapper = new QueryWrapper<>();
        return implantBodyADao.selectCount(queryWrapper);
    }


    @Override
    public int getCount() {
        return this.count();
    }
}