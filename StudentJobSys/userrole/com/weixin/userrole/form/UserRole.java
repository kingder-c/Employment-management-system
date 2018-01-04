package com.weixin.userrole.form;
/**
 * userrole的实体类
 */
public class UserRole {
	/**
	 * 中间关系的id号
	 */
	private Long user_role_id;
	/**
	 * 相联系的user_id
	 */
	private Long user_id;
	/**
	 * 相联系的role_id
	 */
	private Long role_id;
	/**
	 * 关系种类编号
	 */
	private Long relation_id;
	/**
	 * 关系类型
	 */
	private String relation_type;
	public Long getUser_role_id() {
		return user_role_id;
	}
	public void setUser_role_id(Long user_role_id) {
		this.user_role_id = user_role_id;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public Long getRole_id() {
		return role_id;
	}
	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}
	public Long getRelation_id() {
		return relation_id;
	}
	public void setRelation_id(Long relation_id) {
		this.relation_id = relation_id;
	}
	public String getRelation_type() {
		return relation_type;
	}
	public void setRelation_type(String relation_type) {
		this.relation_type = relation_type;
	}
	
	
}
