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
@TableName("体外类骨磷灰石形成表_体外类骨磷灰石形成_XRD")
public class ExternalXrdAEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String sampleserial;
	/**
	 * 
	 */
	private String typeofsbf;
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
	private String typeofxrd;
	/**
	 * 
	 */
	private String equipmentxrd;
	/**
	 * 
	 */
	private String xrdcharacterization;
	/**
	 * 
	 */
	private String detectedphase;
	/**
	 * 
	 */
	private String jcpdsnumber;
	/**
	 * '2ThetaDegree' is changed to 'ThetaDegree' for requiremments
	 */
	private Double thetadegree;
	/**
	 * 
	 */
	@TableField("`Miller indices-hkl`")
	private String millerIndicesHkl;
	/**
	 * 
	 */
	private String spectraxrd;

}
