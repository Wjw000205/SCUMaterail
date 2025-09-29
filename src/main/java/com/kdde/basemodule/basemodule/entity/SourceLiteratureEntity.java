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
@TableName("文献数据来源信息表")
public class SourceLiteratureEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String documentserial;
	/**
	 * 
	 */
	private String datasourceliteraturetype;
	/**
	 * 
	 */
	private String datasourcepublicationname;
	/**
	 * 
	 */
	private String datasourcetitle;
	/**
	 * 
	 */
	private String datasourceauthors;
	/**
	 * 
	 */
	private String datasourceyear;
	/**
	 * 
	 */
	private String datasourcevolume;
	/**
	 * 
	 */
	private String datasourceissue;
	/**
	 * 
	 */
	private String datasourcestartpage;
	/**
	 * 
	 */
	private String datasourceendpage;
	/**
	 * 
	 */
	private String datasourcearticleno;
	/**
	 * 
	 */
	private String doi;
	/**
	 * 
	 */
	private String wosnumber;
	/**
	 * 
	 */
	private String issn;
	/**
	 * 
	 */
	private String isbn;
	/**
	 * 
	 */
	private String datasourceurl;
	/**
	 * 
	 */
	private String proceedingspapermeeting;
	/**
	 * 
	 */
	private String proceedingspaperyear;
	/**
	 * 
	 */
	private String proceedingspaperlocation;
	/**
	 * 
	 */
	private String datasourceabstract;
	/**
	 * 
	 */
	private String datasourcefulltext;

}
