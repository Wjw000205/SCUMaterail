package com.kdde.basemodule.basemodule.entity;

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
@TableName("04_孔径相关性能表")
public class AptureBEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
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
	private String poresizedistributionphotos;

}
