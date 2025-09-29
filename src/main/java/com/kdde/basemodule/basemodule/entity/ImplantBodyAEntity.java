package com.kdde.basemodule.basemodule.entity;

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
@TableName("体内植入实验表")
public class ImplantBodyAEntity implements Serializable {
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
	private String implantsamplesizediameter;
	/**
	 * 
	 */
	private String implantsamplesizethickness;
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
	private String implantsamplesizeUnit;
	/**
	 * 
	 */
	private String implantsamplepreparation;
	/**
	 * 
	 */
	private String implantsamplenumber;
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
	private String implantduration;
	/**
	 * 
	 */
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
	private String osteogenesis;
	/**
	 * 
	 */
	private String osteogenesiscondition;
	/**
	 * 
	 */
	private String osteogenesisareapercentage;
	/**
	 * 
	 */
	private String osteogenesisareapercentageSd;
	/**
	 * 
	 */
	private String materialsresorption;
	/**
	 * 
	 */
	private String materialsresorptionSd;
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
	private String newboneelasticmodulusUnit;
	/**
	 * 
	 */
	private String newbonetensilestrength;
	/**
	 * 
	 */
	private String newbonetensilestrengthUnit;
	/**
	 * 
	 */
	private String newbonecompressivestrength;
	/**
	 * 
	 */
	private String newbonecompressivestrengthUnit;

}
