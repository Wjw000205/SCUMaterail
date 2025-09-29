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
@TableName("材料表面性能表")
public class SurfaceOfMaterialsAEntity implements Serializable {
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
	private String surfacetreatmentmethods;
	/**
	 * 
	 */
	private Double contactangle;
	/**
	 * 
	 */
	@TableField("`ContactAngle-SD`")
	private Double contactangleSd;
	/**
	 * 
	 */
	@TableField("`ContactAngle-Unit`")
	private String contactangleUnit;
	/**
	 * 
	 */
	@TableField("`ContactAngle-LiquidType`")
	private String contactangleLiquidtype;
	/**
	 * 
	 */
	private String surfacetension;
	/**
	 * 
	 */
	private String surfacetensionunit;
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
	private String surfacepotentialsign;
	/**
	 * 
	 */
	private Double surfacepotential;
	/**
	 * 
	 */
	@TableField("`SurfacePotential-Unit`")
	private String surfacepotentialUnit;
	/**
	 * 
	 */
	private Double surfaceenergy;
	/**
	 * 
	 */
	@TableField("`SurfaceEnergy-Unit`")
	private String surfaceenergyUnit;
	/**
	 * 
	 */
	private String roughnesscharacterization;
	/**
	 * 
	 */
	private Double roughnessra;
	/**
	 * 
	 */
	@TableField("`RoughnessRa-SD`")
	private Double roughnessraSd;
	/**
	 * 
	 */
	@TableField("`RoughnessRa-Unit`")
	private String roughnessraUnit;
	/**
	 * 
	 */
	private Double roughnessrarms;
	/**
	 * 
	 */
	@TableField("`RoughnessRaRMS-SD`")
	private Double roughnessrarmsSd;
	/**
	 * 
	 */
	@TableField("`RoughnessRaRMS-Unit`")
	private String roughnessrarmsUnit;
	/**
	 * 
	 */
	private Double maximumroughnessdepth;
	/**
	 * 
	 */
	@TableField("`MaximumRoughnessDepth-SD`")
	private Double maximumroughnessdepthSd;
	/**
	 * 
	 */
	@TableField("`MaximumRoughnessDepth-Unit`")
	private String maximumroughnessdepthUnit;
	/**
	 * 
	 */
	private Double surfacemicrohardness;
	/**
	 * 
	 */
	@TableField("`SurfaceMicrohardness-SD`")
	private Double surfacemicrohardnessSd;
	/**
	 * 
	 */
	@TableField("`SurfaceMicrohardness-Unit`")
	private String surfacemicrohardnessUnit;
	/**
	 * 
	 */
	private Double coatingadhesionstrength;
	/**
	 * 
	 */
	@TableField("`CoatingAdhesionStrength-SD`")
	private Double coatingadhesionstrengthSd;
	/**
	 * 
	 */
	@TableField("`CoatingAdhesionStrength-Unit`")
	private String coatingadhesionstrengthUnit;
	/**
	 * 
	 */
	private String surfacemorphologycharacterization;
	/**
	 * 
	 */
	private String surfacemorphology;
	/**
	 * 
	 */
	private String surfacemorphologyphoto;

}
