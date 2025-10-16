package com.kdde.basemodule.basemodule.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kdde.basemodule.basemodule.dao.CellEnergyDao;
import com.kdde.basemodule.basemodule.dao.ModuleDao;
import com.kdde.basemodule.basemodule.dao.ModuleStructureDao;
import com.kdde.basemodule.basemodule.entity.CellEnergyEntity;
import com.kdde.basemodule.basemodule.entity.ModuleEntity;
import com.kdde.basemodule.basemodule.entity.ModuleStructureEntity;
import com.kdde.basemodule.basemodule.service.ModuleService;
import com.kdde.basemodule.basemodule.service.ModuleStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModuleServiceImp extends ServiceImpl<ModuleDao, ModuleEntity> implements ModuleService {
    @Autowired
    private ModuleDao moduleDao;
    @Autowired
    private CellEnergyDao cellEnergyDao;
    @Autowired
    private ModuleStructureService moduleStructureService;

    @Override
    public List<ModuleEntity> getAllModule(int parentId) {
        return moduleDao.selectList(
                new QueryWrapper<ModuleEntity>().eq("parent",parentId).eq("state",1)
        );
    }

    @Override
    public int createModule(JSONObject moduleInfo) {
        String moduleName = moduleInfo.getString("name");

        int count = this.count(
                new QueryWrapper<ModuleEntity>().eq("name", moduleName)
        );
        if(count > 0){
            return -1;  //代表表中已经有该表名
        }
        else {
            ModuleEntity moduleEntity = new ModuleEntity();
            moduleEntity.setName(moduleName);
            moduleEntity.setState(0);
            moduleEntity.setParent(moduleInfo.getInteger("parent"));
            moduleEntity.setDescription(moduleInfo.getString("description"));
            moduleEntity.setCreator(moduleInfo.getString("creator"));
            this.save(moduleEntity);
            int moduleId = moduleEntity.getId();    //获取生成的id
            //根据id将表结构插入模板结构表中
            boolean res = moduleStructureService.insertModuleStructure(moduleInfo.getJSONObject("columns"),moduleId);
            if(res){
                return 1;
            }
            else {
                moduleDao.deleteById(moduleId);
                return 0;
            }
        }
    }

    @Override
    public boolean deleteModules(List<Integer> moduleId) {
        cellEnergyDao.delete(new UpdateWrapper<CellEnergyEntity>().in("module_id", moduleId));
        return moduleDao.deleteBatchIds(moduleId) > 0;
    }

    @Override
    public boolean updateModule(ModuleEntity module) {
        UpdateWrapper<ModuleEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name", module.getName());
        updateWrapper.set("description", module.getDescription());
        return this.update(null,updateWrapper);
    }
}
