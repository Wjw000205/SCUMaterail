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
@TableName("03_基本物理性能表")
public class BasicPyhsicsBEntity implements Serializable {
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
	private String matspecies;
	/**
	 * 
	 */
	private String density;
	/**
	 * 
	 */
	@TableField("`Density-Unit`")
	private String densityUnit;
	/**
	 * 
	 */
	private String meltingpoint;
	/**
	 * 
	 */
	@TableField("`MeltingPoint-Unit`")
	private String meltingpointUnit;
	/**
	 * 
	 */
	private String phasetransitiontemperature;
	/**
	 * 
	 */
	@TableField("`PhaseTransitionTemperature-Unit`")
	private String phasetransitiontemperatureUnit;
	/**
	 * 
	 */
	private String crystallinity;
	/**
	 * 
	 */
	private String degreeofpolymerization;
	/**
	 * 
	 */
	private String phvalue;
	/**
	 * 
	 */
	private String color;
	/**
	 * 
	 */
	@TableField("`CaP-Ratio`")
	private Double capRatio;
	/**
	 * 
	 */
	private Double granularity;
	/**
	 * 
	 */
	@TableField("`Granularity-SD`")
	private Double granularitySd;
	/**
	 * 
	 */
	@TableField("`Granularity-Unit`")
	private String granularityUnit;

}
