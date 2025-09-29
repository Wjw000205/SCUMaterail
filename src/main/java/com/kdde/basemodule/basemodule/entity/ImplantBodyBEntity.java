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
@TableName("05_体内植入实验表")
public class ImplantBodyBEntity implements Serializable {
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
	private String implantsampleshape;
	/**
	 * 
	 */
	private Double implantsamplesizediameter;
	/**
	 * 
	 */
	private Double implantsamplesizethickness;
	/**
	 * 
	 */
	private String implantsamplesizelength;
	/**
	 * 
	 */
	private String implantsamplesizewidth;
	/**
	 * 
	 */
	private String implantsamplesizeheight;
	/**
	 * 
	 */
	@TableField("`ImplantSampleSize-Unit`")
	private String implantsamplesizeUnit;
	/**
	 * 
	 */
	private String implantsamplepreparation;
	/**
	 * 
	 */
	private Double implantsamplenumber;
	/**
	 * 
	 */
	private String controlsample;
	/**
	 * 
	 */
	private String controlsamplenumber;
	/**
	 * 
	 */
	private String animalspecies;
	/**
	 * 
	 */
	private String animalspeciessex;
	/**
	 * 
	 */
	private String animalspeciesage;
	/**
	 * 
	 */
	private String animalspeciesweight;
	/**
	 * 
	 */
	private String implantsite;
	/**
	 * 
	 */
	private Double implantduration;
	/**
	 * 
	 */
	@TableField("`ImplantDuration-Unit`")
	private String implantdurationUnit;
	/**
	 * 
	 */
	private String surgicalcondition;
	/**
	 * 
	 */
	private String tissuesampleprocessing;
	/**
	 * 
	 */
	private String implantsitetissuestructurechange;
	/**
	 * 
	 */
	private String implantsitetissuereactionproperty;
	/**
	 * 
	 */
	private String implantsitetissuereactionlevel;
	/**
	 * 
	 */
	private String existenceofimplant;
	/**
	 * 
	 */
	private String shapeofimplant;
	/**
	 * 
	 */
	private String positionofimplant;
	/**
	 * 
	 */
	private String degradationresidue;
	/**
	 * 
	 */
	private String fibrationlevel;
	/**
	 * 
	 */
	private String inflammationlevel;
	/**
	 * 
	 */
	private String inflammatorycelltype;
	/**
	 * 
	 */
	private String inflammatorycellreaction;
	/**
	 * 
	 */
	private String necrosistype;
	/**
	 * 
	 */
	private String necrosislevel;
	/**
	 * 
	 */
	private String othertissuechange;
	/**
	 * 
	 */
	private Integer osteogenesis;
	/**
	 * 
	 */
	private String osteogenesiscondition;
	/**
	 * 
	 */
	private Double osteogenesisareapercentage;
	/**
	 * 
	 */
	@TableField("`OsteogenesisAreaPercentage-SD`")
	private Double osteogenesisareapercentageSd;
	/**
	 * 
	 */
	private Double materialsresorption;
	/**
	 * 
	 */
	@TableField("`MaterialsResorption-SD`")
	private Double materialsresorptionSd;
	/**
	 * 
	 */
	private String tissueslice;
	/**
	 * 
	 */
	private String newboneelasticmodulus;
	/**
	 * 
	 */
	@TableField("`NewboneElasticModulus-Unit`")
	private String newboneelasticmodulusUnit;
	/**
	 * 
	 */
	private String newbonetensilestrength;
	/**
	 * 
	 */
	@TableField("`NewboneTensileStrength-Unit`")
	private String newbonetensilestrengthUnit;
	/**
	 * 
	 */
	private String newbonecompressivestrength;
	/**
	 * 
	 */
	@TableField("`NewboneCompressiveStrength-Unit`")
	private String newbonecompressivestrengthUnit;

}
