package com.kdde.basemodule.basemodule.service.impl;

import com.kdde.basemodule.basemodule.common.utils.EntityColumnUtil;
import com.kdde.basemodule.basemodule.common.utils.PageUtils;
import com.kdde.basemodule.basemodule.entity.ExternalExperimentAEntity;
import com.kdde.basemodule.basemodule.entity.SurfaceOfMaterialsAEntity;
import com.kdde.basemodule.basemodule.entity.SurfaceOfMaterialsAEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kdde.basemodule.basemodule.common.utils.Query;
import com.kdde.basemodule.basemodule.dao.SurfaceOfMaterialsADao;
import com.kdde.basemodule.basemodule.entity.SurfaceOfMaterialsAEntity;
import com.kdde.basemodule.basemodule.service.SurfaceOfMaterialsAService;


@Service("surfaceOfMaterialsAService")
public class SurfaceOfMaterialsAServiceImpl extends ServiceImpl<SurfaceOfMaterialsADao, SurfaceOfMaterialsAEntity> implements SurfaceOfMaterialsAService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SurfaceOfMaterialsAEntity> page = this.page(
                new Query<SurfaceOfMaterialsAEntity>().getPage(params),
                new QueryWrapper<SurfaceOfMaterialsAEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public int getCount() {
        return this.count();
    }

    @Override
    public List<SurfaceOfMaterialsAEntity> searchAnyCol(String keyword, List<String> colName) {
        QueryWrapper<SurfaceOfMaterialsAEntity> queryWrapper = new QueryWrapper<>();
        if(colName != null && !colName.isEmpty()){
            for (String column : colName) {
                queryWrapper.or().like(column, keyword);
            }
        }else{
            List<String> colNames = EntityColumnUtil.getTableColumns(SurfaceOfMaterialsAEntity.class);
            String concatExpression = colNames.stream()
                    .map(col -> "IFNULL(" + col + ",'')")
                    .collect(Collectors.joining(", ", "CONCAT_WS('|', ", ")"));
            queryWrapper.apply(concatExpression + " LIKE CONCAT('%', {0}, '%')", keyword);
        }

        return this.list(queryWrapper);
    }

    @Override
    public List<Map<String, Object>> queryTable(String tableName, List<String> fieldNames) {
        // 安全校验：确保表名和字段名合法，防止SQL注入
//        validateTableName(tableName);
//        validateFieldNames(fieldNames);

        // 转换为数据库列名（处理包含特殊字符的字段）
        List<String> dbFieldNames = fieldNames.stream()
                .map(field -> EntityColumnUtil.convertToDatabaseColumnName(field))
                .collect(Collectors.toList());

        // 构建查询条件
        QueryWrapper<SurfaceOfMaterialsAEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(dbFieldNames.toArray(new String[0]));

        // 执行查询并返回结果
        return this.baseMapper.selectMaps(queryWrapper);
    }
}