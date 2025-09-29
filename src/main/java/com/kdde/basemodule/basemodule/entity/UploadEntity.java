package com.kdde.basemodule.basemodule.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
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
@TableName("upload")
public class UploadEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String description;
	/**
	 * 
	 */
	private Date createdAt;
	/**
	 * 
	 */
	private Date updatedAt;
	/**
	 * 
	 */
	private String createdBy;
	/**
	 * 
	 */
	private String updatedBy;
	/**
	 * 
	 */
	private BigDecimal ncOrder;
	/**
	 * 
	 */
	private String attachment;

}
