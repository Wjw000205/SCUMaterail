package com.kdde.basemodule.basemodule.controller;

import com.kdde.basemodule.basemodule.common.utils.PageUtils;
import com.kdde.basemodule.basemodule.common.utils.R;
import com.kdde.basemodule.basemodule.entity.CellEnergyEntity;
import com.kdde.basemodule.basemodule.service.CellEnergyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/basemodule/cellenergy")
public class CellEnergyController {
    @Autowired
    private CellEnergyService cellEnergyService;

    @PostMapping("/list")
    public R getCellEnergyList(@RequestBody Map<String, Object> params) {
        PageUtils page = cellEnergyService.queryPage(params);
        return R.ok().put("page", page);
    }

    @GetMapping("/info/{sampleserial}")
    public R getBySerial(@PathVariable("sampleserial") String sampleSerial){
        return R.ok().put("data",cellEnergyService.queryBySerial(sampleSerial));
    }

    @PostMapping("/save")
    public R save(@RequestBody CellEnergyEntity cellEnergyEntity){
        try{
            int state = cellEnergyService.saveCellEnergy(cellEnergyEntity);
            if(state == 1){
                return R.ok("保存成功");
            }
            else{
                return R.ok("序列号已存在，请重新输入");
            }
        }catch (Exception e){
            log.error(e.getMessage());
            return R.error("系统错误，请联系管理员处理");
        }
    }

    @PostMapping("/delete")
    public R delete(@RequestBody List<String> sampleSerials){
        if(cellEnergyService.removeCellEnergy(sampleSerials)){
            return R.ok("删除成功");
        }
        else {
            return R.ok("删除失败，请稍后重试");
        }
    }

    @PostMapping("/update/{sampleserial}")
    public R update(@PathVariable("sampleserial")String sampleSerial,@RequestBody Map<String,Object> body){
        if(cellEnergyService.updateCellEnergy(sampleSerial,body)){
            return R.ok("更新成功");
        }
        else{
            return R.ok("更新失败，请稍后重试");
        }
    }
}
