package com.kdde.basemodule.basemodule.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kdde.basemodule.basemodule.common.utils.EntityColumnUtil;
import com.kdde.basemodule.basemodule.dao.MechanicalPropertyDao;
import com.kdde.basemodule.basemodule.entity.MechanicalPropertyEntity;
import com.kdde.basemodule.basemodule.entity.MechanicalPropertyEntity;
import com.kdde.basemodule.basemodule.service.MechanicalPropertyService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zxx
 * @Date 2025/5/24 23:42
 */
@Service("mechanicalPropertyService")
public class MechanicalPropertyServiceImpl extends ServiceImpl<MechanicalPropertyDao, MechanicalPropertyEntity> implements MechanicalPropertyService {


    @Override
    public List<MechanicalPropertyEntity> searchAnyCol(String keyword, List<String> colName) {
        QueryWrapper<MechanicalPropertyEntity> queryWrapper = new QueryWrapper<>();
        if(colName != null && !colName.isEmpty()){
            for (String column : colName) {
                queryWrapper.or().like(column, keyword);
            }
        }else{
            List<String> colNames = EntityColumnUtil.getTableColumns(MechanicalPropertyEntity.class);
            String concatExpression = colNames.stream()
                    .map(col -> "IFNULL(" + col + ",'')")
                    .collect(Collectors.joining(", ", "CONCAT_WS('|', ", ")"));
            queryWrapper.apply(concatExpression + " LIKE CONCAT('%', {0}, '%')", keyword);
        }

        return this.list(queryWrapper);
    }
}
