package com.kdde.basemodule.basemodule.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("cell_energy")
public class CellEnergyEntity {
//    private static final long serialVersionUID = 1L;
    /**
    虚拟id，自增字段
     */
    @TableId(type = IdType.AUTO)
    int id;
    /**
     * 样品编号
     */
    String sampleSerial;
    /**
     * 实验方法
     */
    String testMethod;
    /**
     * 细胞类型
     */
    String cellType;
    /**
     * 细胞种属
     */
    String cellSpecies;
    /**
     * 细胞来源
     */
    String cellSource;
    /**
     * 细胞代数
     */
    int passageNumber;
    /**
     * 培养方式
     */
    String cellCultureMethod;
    /**
     * 评价类型
     */
    String evaluationType;
    /**
     * 孔板类型
     */
    String plateType;
    /**
     * 接种密度
     */
    String seedingDensity;
    /**
     * 培养介质-培养基
     */
    String cultureMedium;
    /**
     * 培养基生产厂家
     */
    String cultureMediumSupplier;
    /**
     * 血清
     */
    String serumConcentration;
    /**
     * 血清厂家
     */
    String serumSupplier;
    /**
     * 血清浓度
     */
    String serum;
    /**
     * 成骨诱导介质
     */
    String osteogenicInductionMedium;
    /**
     * 双抗
     */
    String penStrep;
    /**
     * 培养气氛
     */
    float cellCultureAtmosphere;
    /**
     * 培养温度
     */
    String cellCultureTemperature;
    /**
     * 培养pH值
     */
    String cellCulturePh;
    /**
     * 试验样品形态
     */
    String testSampleShape;
    /**
     * 样品形态
     */
    String sampleMorpholog;
    /**
     * 样品尺寸-块状长度
     */
    float sampleSizeLength;
    /**
     * 样品尺寸-块状宽度
     */
    float sampleSizeWidth;
    /**
     * 样品尺寸-块状高度
     */
    float sampleSizeHeight;
    /**
     * 样品尺寸-圆柱状直径
     */
    float sampleSizeDiameter;
    /**
     * 样品尺寸-圆柱状厚度
     */
    float sampleSizeThickness;
    /**
     * 样品尺寸-单位
     */
    String sampleSizeUnit;
    /**
     * 浸提介质
     */
    String extractionMedium;
    /**
     * 浸提条件
     */
    String extractionCondition;
    /**
     * 灭菌方式
     */
    String sterilizationMethod;
    /**
     * 阳性对照样品
     */
    String positiveControl;
    /**
     * 阴性对照样品
     */
    String negativeControl;
    /**
     * 平行样数
     */
    int parallelSampleNumber;
    /**
     * 试验样品无菌性
     */
    String testSampleSterility;

    /**
     * 琼脂扩散试验
     */
    String agarDiffusionTest;
    /**
     * 滤膜扩散试验
     */
    String filterDiaphragmDiffusionTest;
    /**
     * 细胞培养时间
     */
    float cellCultureTime;
    /**
     * 细胞培养时间单位
     */
    String cellCultureTimeUnit;
    /**
     * 细胞活力结果文字描述
     */
    String viabilityQualitativeDescription;
    /**
     * 细胞活力结果图片
     */
    String viabilityPicture;
}
