package com.weixin.user.form;

/**
 *  用户  的各项属性
 */
public class User {

	/**
	 * 用户id
	 */
	private Long user_id;
	/**
	 * 用户名
	 */
	private String user_name;
	/**
	 * 用户密码
	 */
	private String  user_password;
	/**
	 * 用户类型
	 */
	private Long  user_type;
	/**
	 * 用户描述
	 */
	private String user_desc;
	/**
	 * 用户审核状态
	 */
	private Integer user_checkstatus;
	/**
	 * 用户是否删除  冻结
	 */
	private Integer user_delete;
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public Long getUser_type() {
		return user_type;
	}
	public void setUser_type(Long user_type) {
		this.user_type = user_type;
	}
	public String getUser_desc() {
		return user_desc;
	}
	public void setUser_desc(String user_desc) {
		this.user_desc = user_desc;
	}
	public Integer getUser_checkstatus() {
		return user_checkstatus;
	}
	public void setUser_checkstatus(Integer user_checkstatus) {
		this.user_checkstatus = user_checkstatus;
	}
	public Integer getUser_delete() {
		return user_delete;
	}
	public void setUser_delete(Integer user_delete) {
		this.user_delete = user_delete;
	}

	
}
