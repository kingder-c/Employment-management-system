package com.weixin.auth.form;

public class Auth {
/**
 * 权限ID
 */
	private Long auth_id;
	/**
	 * Variable characters (30)	30	FALSE	权限名
	 */
	private String auth_name;
	/**
	 * 	Variable characters (100)	100	FALSE	路径
	 */
	private String auth_path;
	/**
	 * 	Number (11)	11	FALSE	父类ID
	 */
	private String auth_parentid;
	/**
	 * 	Variable characters (500)	500	FALSE	描述
	 */
	private String auth_description;
	/**
	 * 	Number (1)	1	FALSE	权限状态
	 */
	private int auth_state;
	/**
	 * 	Number (1)	1	FALSE	是否删除权限
	 */
	private int auth_delete;
	public Long getAuth_id() {
		return auth_id;
	}
	public void setAuth_id(Long auth_id) {
		this.auth_id = auth_id;
	}
	public String getAuth_name() {
		return auth_name;
	}
	public void setAuth_name(String auth_name) {
		this.auth_name = auth_name;
	}
	public String getAuth_path() {
		return auth_path;
	}
	public void setAuth_path(String auth_path) {
		this.auth_path = auth_path;
	}
	public String getAuth_parentid() {
		return auth_parentid;
	}
	public void setAuth_parentid(String auth_parentid) {
		this.auth_parentid = auth_parentid;
	}
	public String getAuth_description() {
		return auth_description;
	}
	public void setAuth_description(String auth_description) {
		this.auth_description = auth_description;
	}
	public int getAuth_state() {
		return auth_state;
	}
	public void setAuth_state(int auth_state) {
		this.auth_state = auth_state;
	}
	public int getAuth_delete() {
		return auth_delete;
	}
	public void setAuth_delete(int auth_delete) {
		this.auth_delete = auth_delete;
	}
	
	
}
