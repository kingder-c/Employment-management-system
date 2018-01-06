package com.weixin.returninfo.view;

import com.weixin.returninfo.form.ReturnInfo;

public class ReturnInfoView  extends ReturnInfo{
	/**
	 * 学生号
	 */
	private Long  stu_id;
	/**
	 * 学生姓名
	 */
	private String stu_name;
	/**
	 * 班级姓名
	 */
	private String cla_name;
	/**
	 * 班级id
	 */
	private Long cla_id;
	
	
	/**
	 * 班级id
	 * @return
	 */
	public Long getCla_id() {
		return cla_id;
	}

	/**
	 * 班级id
	 * @return
	 */
	public void setCla_id(Long cla_id) {
		this.cla_id = cla_id;
	}
	/**
	 * 学生号
	 * @return
	 */
	public Long  getStu_id() {
		return stu_id;
	}
	/**
	 * 学生号
	 * @return
	 */
	public void setStu_id(Long  stu_id) {
		this.stu_id = stu_id;
	}
	/**
	 * 学生姓名
	 * @return
	 */
	public String getStu_name() {
		return stu_name;
	}
	/**
	 * 学生姓名
	 * @return
	 */
	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}
	/**
	 * 班级名称
	 * @return
	 */
	public String getCla_name() {
		return cla_name;
	}
	/**
	 * 班级名称
	 * @return
	 */
	public void setCla_name(String cla_name) {
		this.cla_name = cla_name;
	}
}
