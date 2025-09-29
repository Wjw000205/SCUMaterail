package com.kdde.basemodule.basemodule.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kdde.basemodule.basemodule.common.utils.EntityColumnUtil;
import com.kdde.basemodule.basemodule.dao.HteVivoDao;
import com.kdde.basemodule.basemodule.dao.ImplantBodyADao;
import com.kdde.basemodule.basemodule.entity.HteVivoEntity;
import com.kdde.basemodule.basemodule.entity.HteVivoEntity;
import com.kdde.basemodule.basemodule.service.HteVivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zxx
 * @Date 2025/5/24 22:30
 */
@Service("hteVivoService")
public class HteVivoServiceImpl extends ServiceImpl<HteVivoDao, HteVivoEntity> implements HteVivoService {


    @Override
    public List<HteVivoEntity> searchAnyCol(String keyword, List<String> colName) {
        QueryWrapper<HteVivoEntity> queryWrapper = new QueryWrapper<>();
        if(colName != null && !colName.isEmpty()){
            for (String column : colName) {
                queryWrapper.or().like(column, keyword);
            }
        }else{
            List<String> colNames = EntityColumnUtil.getTableColumns(HteVivoEntity.class);
            String concatExpression = colNames.stream()
                    .map(col -> "IFNULL(" + col + ",'')")
                    .collect(Collectors.joining(", ", "CONCAT_WS('|', ", ")"));
            queryWrapper.apply(concatExpression + " LIKE CONCAT('%', {0}, '%')", keyword);
        }

        return this.list(queryWrapper);
    }
}
