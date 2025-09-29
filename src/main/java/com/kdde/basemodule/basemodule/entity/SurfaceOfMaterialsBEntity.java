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
@TableName("02_材料组成成分表")
public class SurfaceOfMaterialsBEntity implements Serializable {
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
	private String matconstitutionserial;
	/**
	 * 
	 */
	private String matconstitutionconstname;
	/**
	 * 
	 */
	private String matconstitutionconstrepresenttype;
	/**
	 * 
	 */
	private Double matconstitutionconstdate;
	/**
	 * 
	 */
	private String matconstitutionconsterrorrange;
	/**
	 * 
	 */
	private String matelementname;
	/**
	 * 
	 */
	private String matelementrepresenttype;
	/**
	 * 
	 */
	private String matelementdate;
	/**
	 * 
	 */
	private String matelementerrorrange;

}
