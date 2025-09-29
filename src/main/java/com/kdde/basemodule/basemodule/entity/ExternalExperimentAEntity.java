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
@TableName("体外类骨磷灰石形成表_体外类骨磷灰石形成表")
public class ExternalExperimentAEntity implements Serializable {
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
	private String nucleationsite;
	/**
	 * 
	 */
	private String typeofsbf;
	/**
	 * 
	 */
	private String componentofsbf;
	/**
	 * 
	 */
	private String sbfflowspeed;
	/**
	 * 
	 */
	@TableField("`SBFFlowSpeed-Unit`")
	private String sbfflowspeedUnit;
	/**
	 * 
	 */
	private Double immersiontime;
	/**
	 * 
	 */
	@TableField("`ImmersionTime-Unit`")
	private String immersiontimeUnit;
	/**
	 * 
	 */
	@TableField("`FormationofBonelikeApatite-Characterization`")
	private String formationofbonelikeapatiteCharacterization;
	/**
	 * 
	 */
	private String semresult;
	/**
	 * 
	 */
	@TableField("`SEM-Photos`")
	private String semPhotos;
	/**
	 * 
	 */
	@TableField("`XRD-Phase`")
	private String xrdPhase;
	/**
	 * 
	 */
	@TableField("`XRD-PhaseComposition`")
	private String xrdPhasecomposition;
	/**
	 * 
	 */
	@TableField("`XRD-Photos`")
	private String xrdPhotos;
	/**
	 * 
	 */
	private String ftirresult;
	/**
	 * 
	 */
	private String growthofapatite;
	/**
	 * 
	 */
	private String gragegrowthofapatite;

}
