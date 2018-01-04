package com.weixin.userrole.view;

import com.weixin.userrole.form.UserRole;
/**
 * userrole的拓展视图
 */
public class UserRoleView extends UserRole {
	/**
	 * 用户名
	 */
	private String role_name;
	/**
	 * 权限集合
	 */
	private String auth_ids;
	/**
	 * 
	 * @return role_name
	 */
	public String getRole_name() {
		return role_name;
	}
	/**
	 * 
	 * @param role_name
	 */
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	/**
	 * 
	 * @return auth_ids
	 */
	public String getAuth_ids() {
		return auth_ids;
	}
	/**
	 * 
	 * @param auth_ids
	 */
	public void setAuth_ids(String auth_ids) {
		this.auth_ids = auth_ids;
	}

	
}
