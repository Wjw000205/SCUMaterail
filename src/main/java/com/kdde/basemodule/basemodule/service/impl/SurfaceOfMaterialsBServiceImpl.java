package com.kdde.basemodule.basemodule.service.impl;

import com.kdde.basemodule.basemodule.common.utils.EntityColumnUtil;
import com.kdde.basemodule.basemodule.common.utils.PageUtils;
import com.kdde.basemodule.basemodule.entity.AptureBEntity;
import com.kdde.basemodule.basemodule.entity.ExternalExperimentAEntity;
import com.kdde.basemodule.basemodule.entity.SurfaceOfMaterialsAEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kdde.basemodule.basemodule.common.utils.Query;
import com.kdde.basemodule.basemodule.dao.SurfaceOfMaterialsBDao;
import com.kdde.basemodule.basemodule.entity.SurfaceOfMaterialsBEntity;
import com.kdde.basemodule.basemodule.service.SurfaceOfMaterialsBService;


@Service("surfaceOfMaterialsBService")
public class SurfaceOfMaterialsBServiceImpl extends ServiceImpl<SurfaceOfMaterialsBDao, SurfaceOfMaterialsBEntity> implements SurfaceOfMaterialsBService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SurfaceOfMaterialsBEntity> page = this.page(
                new Query<SurfaceOfMaterialsBEntity>().getPage(params),
                new QueryWrapper<SurfaceOfMaterialsBEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public int getCount() {
        return this.count();
    }


    @Override
    public List<SurfaceOfMaterialsBEntity> searchAnyCol(String keyword, List<String> colName) {
        QueryWrapper<SurfaceOfMaterialsBEntity> queryWrapper = new QueryWrapper<>();
        if(colName != null && !colName.isEmpty()){
            for (String column : colName) {
                queryWrapper.or().like(column, keyword);
            }
        }else{
            List<String> colNames = EntityColumnUtil.getTableColumns(SurfaceOfMaterialsBEntity.class);
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
        QueryWrapper<SurfaceOfMaterialsBEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(dbFieldNames.toArray(new String[0]));

        // 执行查询并返回结果
        return this.baseMapper.selectMaps(queryWrapper);
    }

//    private SurfaceOfMaterialsBEntityDTO convertToDTO(SurfaceOfMaterialsBEntity entity) {
//        SurfaceOfMaterialsBEntityDTO dto = new SurfaceOfMaterialsBEntityDTO();
//
//        // 使用反射自动映射
//        BeanUtils.copyProperties(entity, dto);
//
//        return dto;
//    }
}