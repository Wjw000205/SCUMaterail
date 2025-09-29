package com.kdde.basemodule.basemodule.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kdde.basemodule.basemodule.common.utils.EntityColumnUtil;
import com.kdde.basemodule.basemodule.dao.AgglomerationProcessDao;
import com.kdde.basemodule.basemodule.entity.AgglomerationProcessEntity;
import com.kdde.basemodule.basemodule.entity.AgglomerationProcessEntity;
import com.kdde.basemodule.basemodule.service.AgglomerationProcessService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zxx
 * @Date 2025/5/24 23:31
 */
@Service("agglomerationProcessService")
public class AgglomerationProcessServiceImpl extends ServiceImpl<AgglomerationProcessDao, AgglomerationProcessEntity> implements AgglomerationProcessService {
    
    @Override
    public List<AgglomerationProcessEntity> searchAnyCol(String keyword, List<String> colName) {
        QueryWrapper<AgglomerationProcessEntity> queryWrapper = new QueryWrapper<>();
        if(colName != null && !colName.isEmpty()){
            for (String column : colName) {
                queryWrapper.or().like(column, keyword);
            }
        }else{
            List<String> colNames = EntityColumnUtil.getTableColumns(AgglomerationProcessEntity.class);
            String concatExpression = colNames.stream()
                    .map(col -> "IFNULL(" + col + ",'')")
                    .collect(Collectors.joining(", ", "CONCAT_WS('|', ", ")"));
            queryWrapper.apply(concatExpression + " LIKE CONCAT('%', {0}, '%')", keyword);
        }

        return this.list(queryWrapper);
    }
}
