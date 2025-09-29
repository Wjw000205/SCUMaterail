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
@TableName("基本物理性能表")
public class BasicPyhsicsEntity implements Serializable {
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
	private String contactangle;
	/**
	 * 
	 */
	@TableField("`ContactAngle-Unit`")
	private String contactangleUnit;
	/**
	 * 
	 */
	@TableField("`Cap-Ratio`")
	private String capRatio;
	/**
	 * 
	 */
	private String granularity;
	/**
	 * 
	 */
	@TableField("`Granularity-SD`")
	private String granularitySd;
	/**
	 * 
	 */
	@TableField("`Granularity-Unit`")
	private String granularityUnit;
	/**
	 * 
	 */
	private String zetapotential;
	/**
	 * 
	 */
	@TableField("`ZetaPotential-Unit`")
	private String zetapotentialUnit;
	/**
	 * 
	 */
	private String surfacepotential;
	/**
	 * 
	 */
	@TableField("`SurfacePotential-Unit`")
	private String surfacepotentialUnit;
	/**
	 * 
	 */
	private String surfaceenergy;
	/**
	 * 
	 */
	@TableField("`SurfaceEnergy-Unit`")
	private String surfaceenergyUnit;
	/**
	 * 
	 */
	private String roughnessra;
	/**
	 * 
	 */
	@TableField("`RoughnessRa-SD`")
	private String roughnessraSd;
	/**
	 * 
	 */
	@TableField("`RoughnessRa-Unit`")
	private String roughnessraUnit;
	/**
	 * 
	 */
	private String roughnessrarms;
	/**
	 * 
	 */
	@TableField("`RoughnessRaRMS-SD`")
	private String roughnessrarmsSd;
	/**
	 * 
	 */
	@TableField("`RoughnessRaRMS-Unit`")
	private String roughnessrarmsUnit;

}
