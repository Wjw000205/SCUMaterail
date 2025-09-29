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
@TableName("文献样品基本信息表")
public class LiteratureSampleEntity implements Serializable {
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
	private String materialname;
	/**
	 * 
	 */
	private String sampleshape;
	/**
	 * 
	 */
	private Double samplesizelength;
	/**
	 * 
	 */
	private Double samplesizewidth;
	/**
	 * 
	 */
	private Double samplesizeheight;
	/**
	 * 
	 */
	private Double samplesizediameter;
	/**
	 * 
	 */
	private Double samplesizethickness;
	/**
	 * 
	 */
	@TableField("`SampleSize-Unit`")
	private String samplesizeUnit;
	/**
	 * 
	 */
	private String mattype;
	/**
	 * 
	 */
	private String matspecies;
	/**
	 * 
	 */
	private String documentserial;

}
