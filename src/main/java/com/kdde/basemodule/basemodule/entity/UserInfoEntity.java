package com.kdde.basemodule.basemodule.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

/**
 * for connection test
 * 
 * @author lzl
 * @email lzl@gmail.com
 * @date 2025-04-17 16:34:41
 */
@Data
@ToString
//@TableName("user_info")
@TableName("test0405")
public class UserInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Getter
	private String username;
	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String password;
	/**
	 * 
	 */
	private String email;

	/**
	 * 学生名
	 */
	private String name;

}
