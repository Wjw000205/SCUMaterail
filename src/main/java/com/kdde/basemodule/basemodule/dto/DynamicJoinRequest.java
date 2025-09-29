package com.kdde.basemodule.basemodule.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class DynamicJoinRequest {
    /**
     * 表名和字段的映射
     * key: 表名(使用TABLE_MAPPING_A/B中的value)
     * value: 需要查询的字段列表
     */
    private Map<String, List<String>> tables;

    /**
     * 材料类型(A/B)
     */
    private String materialType;
}
