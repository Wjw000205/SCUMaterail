package com.kdde.basemodule.basemodule.common.utils;

import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.kdde.basemodule.basemodule.entity.AptureBEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zxx
 * @Date 2025/5/20 19:50
 */
public class EntityColumnUtil {

    /**
     * 获得实体类对应的表中的所有字段
     * @param entityClass
     * @return
     */
    public static List<String> getTableColumns(Class<?> entityClass) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(entityClass);
        if (tableInfo == null) {
            throw new RuntimeException("无法获取表信息，请检查实体类配置");
        }

        return tableInfo.getFieldList().stream()
                .map(field -> field.getColumn())
                .collect(Collectors.toList());
    }

    public static String getColumnName(Class<?> entityClass, String fieldName) {
//        try {
//            Field field = entityClass.getDeclaredField(fieldName);
//            TableField tableField = field.getAnnotation(TableField.class);
//
//            if (tableField != null && !tableField.value().isEmpty()) {
//                return tableField.value();
//            }
//
//            // 默认使用驼峰转下划线
//            return camelToUnderline(fieldName);
//        } catch (NoSuchFieldException e) {
//            return null;
//        }
        return fieldName;
    }

    public static String convertToDatabaseColumnName(String fieldName) {
        // 使用工具类获取实体类字段对应的数据库列名
        String columnName = EntityColumnUtil.getColumnName(AptureBEntity.class, fieldName);

        if (columnName != null) {
            return columnName;
        }

        // 如果找不到映射，默认使用字段名（不推荐，仅作为后备）
        return fieldName;
    }

}
