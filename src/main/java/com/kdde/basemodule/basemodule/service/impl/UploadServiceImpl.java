package com.kdde.basemodule.basemodule.service.impl;

import com.kdde.basemodule.basemodule.common.utils.PageUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kdde.basemodule.basemodule.common.utils.Query;
import com.kdde.basemodule.basemodule.dao.UploadDao;
import com.kdde.basemodule.basemodule.entity.UploadEntity;
import com.kdde.basemodule.basemodule.service.UploadService;


@Service("uploadService")
public class UploadServiceImpl extends ServiceImpl<UploadDao, UploadEntity> implements UploadService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UploadEntity> page = this.page(
                new Query<UploadEntity>().getPage(params),
                new QueryWrapper<UploadEntity>()
        );

        return new PageUtils(page);
    }

}