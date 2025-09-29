package com.kdde.basemodule.basemodule.service.impl;

import com.kdde.basemodule.basemodule.common.utils.EntityColumnUtil;
import com.kdde.basemodule.basemodule.common.utils.PageUtils;
import com.kdde.basemodule.basemodule.common.utils.Query;
import com.kdde.basemodule.basemodule.entity.AptureBEntity;
import com.kdde.basemodule.basemodule.entity.AptureBEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.kdde.basemodule.basemodule.dao.AptureBDao;
import com.kdde.basemodule.basemodule.entity.AptureBEntity;
import com.kdde.basemodule.basemodule.service.AptureBService;
import com.kdde.basemodule.basemodule.common.utils.EntityColumnUtil;


@Service("aptureBService")
public class AptureBServiceImpl extends ServiceImpl<AptureBDao, AptureBEntity> implements AptureBService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AptureBEntity> page = this.page(
                new Query<AptureBEntity>().getPage(params),
                new QueryWrapper<AptureBEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public int getCount() {
        return this.count();
    }

    @Override
    public List<AptureBEntity> searchAnyCol(String keyword, List<String> colName) {
        QueryWrapper<AptureBEntity> queryWrapper = new QueryWrapper<>();
        if(colName != null && !colName.isEmpty()){
            for (String column : colName) {
                queryWrapper.or().like(column, keyword);
            }
        }else{
            List<String> colNames = EntityColumnUtil.getTableColumns(AptureBEntity.class);
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
        QueryWrapper<AptureBEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(dbFieldNames.toArray(new String[0]));

        // 执行查询并返回结果
        return this.baseMapper.selectMaps(queryWrapper);
    }



//    /**
//     * 将实体类属性名转换为数据库列名
//     */
//    private String convertToDatabaseColumnName(String fieldName) {
//        // 使用工具类获取实体类字段对应的数据库列名
//        String columnName = EntityColumnUtil.getColumnName(AptureBEntity.class, fieldName);
//
//        if (columnName != null) {
//            return columnName;
//        }
//
//        // 如果找不到映射，默认使用字段名（不推荐，仅作为后备）
//        return fieldName;
//    }
}