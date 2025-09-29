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
@TableName("体外类骨磷灰石形成表_体外类骨磷灰石形成_表面成分表")
public class ExternalSurfaceFormationAEntity implements Serializable {
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
	private String accumulationpoint;
	/**
	 * 
	 */
	private Double surfaceconstitutionserial;
	/**
	 * 
	 */
	private String surfaceelementcharacterization;
	/**
	 * 
	 */
	private String surfaceelementname;
	/**
	 * 
	 */
	private String surfaceelementrepresenttype;
	/**
	 * 
	 */
	private Double surfaceelementdate;
	/**
	 * 
	 */
	private Double surfaceelementerrorrange;

}
