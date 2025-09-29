package com.kdde.basemodule.basemodule.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kdde.basemodule.basemodule.common.utils.EntityColumnUtil;
import com.kdde.basemodule.basemodule.dao.HteVitroDao;
import com.kdde.basemodule.basemodule.entity.HteVitroEntity;
import com.kdde.basemodule.basemodule.entity.HteVitroEntity;
import com.kdde.basemodule.basemodule.service.HteVitroService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zxx
 * @Date 2025/5/24 23:40
 */
@Service("hteVitroService")
public class HteVitroServiceImpl extends ServiceImpl<HteVitroDao, HteVitroEntity> implements HteVitroService {

    @Override
    public List<HteVitroEntity> searchAnyCol(String keyword, List<String> colName) {
        QueryWrapper<HteVitroEntity> queryWrapper = new QueryWrapper<>();
        if(colName != null && !colName.isEmpty()){
            for (String column : colName) {
                queryWrapper.or().like(column, keyword);
            }
        }else{
            List<String> colNames = EntityColumnUtil.getTableColumns(HteVitroEntity.class);
            String concatExpression = colNames.stream()
                    .map(col -> "IFNULL(" + col + ",'')")
                    .collect(Collectors.joining(", ", "CONCAT_WS('|', ", ")"));
            queryWrapper.apply(concatExpression + " LIKE CONCAT('%', {0}, '%')", keyword);
        }

        return this.list(queryWrapper);
    }
}
