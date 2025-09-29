package com.kdde.basemodule.basemodule.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kdde.basemodule.basemodule.common.utils.PageUtils;
import com.kdde.basemodule.basemodule.common.utils.Query;
import com.kdde.basemodule.basemodule.dao.CellEnergyDao;
import com.kdde.basemodule.basemodule.entity.CellEnergyEntity;
import com.kdde.basemodule.basemodule.service.CellEnergyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static com.kdde.basemodule.basemodule.common.utils.camelToUnderline.camelToUnder;

@Service
public class CellEnergyServiceImp extends ServiceImpl<CellEnergyDao, CellEnergyEntity> implements CellEnergyService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CellEnergyEntity> page = this.page(
                new Query<CellEnergyEntity>().getPage(params),
                new QueryWrapper<CellEnergyEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public CellEnergyEntity queryBySerial(String sampleSerial) {
        return this.getOne(new QueryWrapper<CellEnergyEntity>().eq("sample_serial", sampleSerial));
    }

    @Override
    public int saveCellEnergy(CellEnergyEntity cellEnergyEntity) {
        String sampleSerial = cellEnergyEntity.getSampleSerial();
        int count = this.count(
                new QueryWrapper<CellEnergyEntity>().eq("sample_serial", sampleSerial)
        );
        if(count > 0){
            return -1;  //代表表中已经有该序列号
        }
        else {
            save(cellEnergyEntity);
            return 1;
        }
    }

    @Override
    public boolean removeCellEnergy(List<String> sampleSerials) {
        return this.remove(
                new QueryWrapper<CellEnergyEntity>().in("sample_serial", sampleSerials)
        );
    }

    @Override
    public boolean updateCellEnergy(String sampleSerial,Map<String,Object> body) {
        UpdateWrapper<CellEnergyEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("sample_serial", sampleSerial);
        body.forEach((k, v) -> updateWrapper.set(camelToUnder(k), v));
        return this.update(null,updateWrapper);
    }
}
