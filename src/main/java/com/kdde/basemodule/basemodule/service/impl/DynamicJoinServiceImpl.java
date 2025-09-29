package com.kdde.basemodule.basemodule.service.impl;

import com.kdde.basemodule.basemodule.dto.DynamicJoinRequest;
import com.kdde.basemodule.basemodule.service.DynamicJoinService;
import com.kdde.basemodule.basemodule.service.DynamicJoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;
import java.util.stream.Collectors;

import static com.kdde.basemodule.basemodule.common.constant.TableMappingConstant.*;

@Service
@RequiredArgsConstructor
public class DynamicJoinServiceImpl implements DynamicJoinService {

    @PersistenceContext
    private final EntityManager entityManager;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> joinTablesBySampleSerial(DynamicJoinRequest joinDTO) {
        // 1. 构建动态SQL
        String sql = buildJoinSql(joinDTO);

        // 2. 执行查询
        Query query = entityManager.createNativeQuery(sql);

        // 3. 获取结果并转换
        List<Object[]> resultList = query.getResultList();
        return convertResult(resultList, joinDTO.getTables());
    }


    private String buildJoinSql(DynamicJoinRequest joinDTO) {
        StringBuilder sql = new StringBuilder("SELECT ");

        // 构建SELECT子句
        int tableIndex = 0;
        for (Map.Entry<String, List<String>> entry : joinDTO.getTables().entrySet()) {
            String tableName = getActualTableName(entry.getKey(), joinDTO.getMaterialType());
            String tableAlias = "t" + tableIndex;

            for (String column : entry.getValue()) {
                sql.append(tableAlias).append(".").append(column)
                        .append(" AS ").append(entry.getKey()).append("_").append(column)
                        .append(", ");
            }
            tableIndex++;
        }
        sql.delete(sql.length() - 2, sql.length()); // 移除最后的", "

        // 构建FROM和JOIN子句
        sql.append(" FROM ");

        tableIndex = 0;
        boolean firstTable = true;
        for (String tableKey : joinDTO.getTables().keySet()) {
            String tableName = getActualTableName(tableKey, joinDTO.getMaterialType());
            String tableAlias = "t" + tableIndex;

            if (!firstTable) {
                sql.append(" LEFT JOIN ");
            }
            firstTable = false;

            sql.append(tableName).append(" ").append(tableAlias);

            if (tableIndex > 0) {
                sql.append(" ON t0.sampleserial = ").append(tableAlias).append(".sampleserial");
            }

            tableIndex++;
        }

        return sql.toString();
    }

    private String getActualTableName(String tableKey, String materialType) {
        if (MATERIAL_A.equals(materialType)) {
            return TABLE_MAPPING_A.get(tableKey);
        } else {
            return TABLE_MAPPING_B.get(tableKey);
        }
    }

    private List<Map<String, Object>> convertResult(List<Object[]> resultList,
                                                    Map<String, List<String>> tables) {
        List<Map<String, Object>> result = new ArrayList<>();

        // 构建列名列表
        List<String> columnNames = tables.entrySet().stream()
                .flatMap(entry -> entry.getValue().stream()
                        .map(column -> entry.getKey() + "_" + column))
                .collect(Collectors.toList());

        // 转换结果
        for (Object[] row : resultList) {
            Map<String, Object> rowMap = new HashMap<>();
            for (int i = 0; i < columnNames.size(); i++) {
                rowMap.put(columnNames.get(i), row[i]);
            }
            result.add(rowMap);
        }

        return result;
    }
}