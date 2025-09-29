package com.kdde.basemodule.basemodule.common.constant;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zxx
 * @Date 2025/5/20 19:20
 */
public class TableMappingConstant {
    public static final Map<String, String> TABLE_MAPPING_A;

    // 第二个字典：表名称映射2
    public static final Map<String, String> TABLE_MAPPING_B;

    public static final Map<String, String> TABLE_MAPPING_C;

    public static final Map<String, String> TABLE_MAPPING_D;

    public static final String MATERIAL_A = "类骨磷灰石";

    public static final String MATERIAL_B = "骨诱导";

    public static final String MATERIAL_C = "高通量实验";

    public static final String MATERIAL_D = "工艺优化";

    static {
        // 初始化 类骨磷灰石
        Map<String, String> mapA = new HashMap<>();
        mapA.put("材料表面性能", "材料表面性能表");
        mapA.put("基本物理性能", "基本物理性能表_new");
        mapA.put("体外表面形成", "体外类骨磷灰石形成表_体外类骨磷灰石形成_表面成分表");
        mapA.put("体外XRD", "体外类骨磷灰石形成表_体外类骨磷灰石形成_XRD");
        mapA.put("体外实验", "体外类骨磷灰石形成表_体外类骨磷灰石形成表");
        mapA.put("文献数据来源", "文献数据来源信息表");
        mapA.put("文献样品信息", "文献样品基本信息表");
        TABLE_MAPPING_A = Collections.unmodifiableMap(mapA);

        // 初始化 骨诱导
        Map<String, String> mapB = new HashMap<>();
        mapB.put("材料组成成分", "02_材料组成成分表");
        mapB.put("基本物理性能", "03_基本物理性能表");
        mapB.put("孔径相关性能", "04_孔径相关性能表");
        mapB.put("体内植入实验", "05_体内植入实验表");
        mapB.put("文献数据来源", "07_文献数据来源信息表");
        mapB.put("文献样品信息", "08_文献样品基本信息表");
        TABLE_MAPPING_B = Collections.unmodifiableMap(mapB);

        Map<String, String> mapC = new HashMap<>();
        mapC.put("体内", "hte_vivo");
        mapC.put("体外", "hte_vitro");
        TABLE_MAPPING_C = Collections.unmodifiableMap(mapC);

        Map<String, String> mapD = new HashMap<>();
        mapD.put("烧结工艺", "agglomerationprocess");
        mapD.put("几何结构", "geometry");
        mapD.put("力学性能评价", "mechanicalproperty");
        TABLE_MAPPING_D = Collections.unmodifiableMap(mapD);
    }

    // 私有构造函数防止实例化
    private TableMappingConstant() {
        throw new AssertionError("不能实例化常量类");
    }

}
