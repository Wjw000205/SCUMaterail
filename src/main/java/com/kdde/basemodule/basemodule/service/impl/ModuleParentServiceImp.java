package com.kdde.basemodule.basemodule.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kdde.basemodule.basemodule.dao.ModuleDao;
import com.kdde.basemodule.basemodule.dao.ModuleParentDao;
import com.kdde.basemodule.basemodule.entity.ModuleEntity;
import com.kdde.basemodule.basemodule.entity.ModuleParentEntity;
import com.kdde.basemodule.basemodule.service.ModuleParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleParentServiceImp extends ServiceImpl<ModuleParentDao, ModuleParentEntity> implements ModuleParentService {
    @Autowired
    private ModuleParentDao moduleParentDao;

    @Override
    public List<ModuleParentEntity> getModuleParentList() {
        return moduleParentDao.selectList(null);
    }
}
