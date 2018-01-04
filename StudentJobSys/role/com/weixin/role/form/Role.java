package com.weixin.role.form;
/**
 * @注释 ：
 */
public class Role {
	/**
	 * 角色ID
	 */
	private Long role_id;
	/**
	 * 角色名称
	 */
	private String role_name;
	/**
	 * 权限集合
	 */
	private String auth_ids;
	/**
	 * 角色描述
	 */
	private String role_desc;
	/**
	 * 是否删除角色
	 */
	private int role_delete;
	public Long getRole_id() {
		return role_id;
	}
	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getAuth_ids() {
		return auth_ids;
	}
	public void setAuth_ids(String auth_ids) {
		this.auth_ids = auth_ids;
	}
	public String getRole_desc() {
		return role_desc;
	}
	public void setRole_desc(String role_desc) {
		this.role_desc = role_desc;
	}
	public int getRole_delete() {
		return role_delete;
	}
	public void setRole_delete(int role_delete) {
		this.role_delete = role_delete;
	}
	
	

}
