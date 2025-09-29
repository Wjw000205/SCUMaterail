package com.kdde.basemodule.basemodule.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author lzl
 * @email lzl@gmail.com
 * @date 2025-04-17 16:34:41
 */
@Data
@TableName("孔径相关性能表")
public class AptureEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
	@TableId(value = "SampleSerial", type = IdType.INPUT)
    private String sampleserial;
    /**
     *
     */
    private String samplename;
    /**
     *
     */
    @TableField("`SpecificSurfaceArea-TestMethod`")
    private String specificsurfaceareaTestmethod;
    /**
     *
     */
    private String specificsurfacearea;
    /**
     *
     */
    @TableField("`SpecificSurfaceArea-Unit`")
    private String specificsurfaceareaUnit;
    /**
     *
     */
    @TableField("`AveragePoreDiameter-TestMethod`")
    private String averageporediameterTestmethod;
    /**
     *
     */
    private Double averageporediameter;
    /**
     *
     */
    @TableField("`AveragePoreDiameter-SD`")
    private Double averageporediameterSd;
    /**
     *
     */
    @TableField("`AveragePoreDiameter-Unit`")
    private String averageporediameterUnit;
    /**
     *
     */
    @TableField("`Porosity-TestMethod`")
    private String porosityTestmethod;
    /**
     *
     */
    private Double porosity;
    /**
     *
     */
    @TableField("`Porosity-SD`")
    private Double porositySd;
    /**
     *
     */
    @TableField("`Porosity-Unit`")
    private String porosityUnit;
    /**
     *
     */
    private Double totalporearea;
    /**
     *
     */
    @TableField("`TotalPoreArea-SD`")
    private String totalporeareaSd;
    /**
     *
     */
    @TableField("`TotalPoreArea-Unit`")
    private String totalporeareaUnit;
    /**
     *
     */
    @TableField("`MicroporeDiameter-Min`")
    private Double microporediameterMin;
    /**
     *
     */
    @TableField("`MicroporeDiameter-Max`")
    private Double microporediameterMax;
    /**
     *
     */
    @TableField("`MicroporeDiameter-unit`")
    private String microporediameterUnit;
    /**
     *
     */
    @TableField("`MacroporeDiameter-Min`")
    private Double macroporediameterMin;
    /**
     *
     */
    @TableField("`MacroporeDiameter-Max`")
    private Double macroporediameterMax;
    /**
     *
     */
    @TableField("`MacroporeDiameter-unit`")
    private String macroporediameterUnit;
	/**
	 * 
	 */
    @TableField("PoreSizeDistributionPhotos")
	private String poresizedistributionphotos;
	/**
	 * 
	 */
//    @TableField("'f24'")
	private String f24;
	/**
	 * 
	 */
//    @TableField("'f25'")
	private String f25;
	/**
	 * 
	 */
//    @TableField("'f26'")
	private String f26;
	/**
	 * 
	 */
//    @TableField("'f27'")
	private String f27;
	/**
	 * 
	 */
//    @TableField("'f28'")
	private String f28;
	/**
	 * 
	 */
//    @TableField("'f29'")
	private String f29;
	/**
	 * 
	 */
//    @TableField("'f30'")
	private String f30;
	/**
	 * 
	 */
//    @TableField("'f31'")
	private String f31;
	/**
	 * 
	 */
//    @TableField("'f32'")
	private String f32;
	/**
	 * 
	 */
//    @TableField("'f33'")
	private String f33;
	/**
	 * 
	 */
//    @TableField("'f34'")
	private String f34;
	/**
	 * 
	 */
//    @TableField("'f35'")
	private String f35;
	/**
	 * 
	 */
//    @TableField("'f36'")
	private String f36;
	/**
	 * 
	 */
//    @TableField("'f37'")
	private String f37;
	/**
	 * 
	 */
//    @TableField("'f38'")
	private String f38;
	/**
	 * 
	 */
//    @TableField("'f39'")
	private String f39;

}
