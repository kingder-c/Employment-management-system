package com.weixin.interviewinfo.view;

import com.weixin.interviewinfo.form.InterviewInfo;

/**
 * 拓展类
 */
public class InterviewInfoView extends InterviewInfo {
	/**
	 * 学员id
	 */
	private Long stu_id;
	/**
	 * 学员姓名
	 */
	private String stu_name;
	/**
	 * 班级id
	 */
	private Long cla_id;
	/**
	 * 班级名称
	 */
	private String cla_name;
	/**
	 * 企业id
	 */
	private Long com_id;
	/**
	 * 企业名称
	 */
	private String com_name;
	
	/**
	 * 学员id get方法
	 * @return
	 */
	public Long getStu_id() {
		return stu_id;
	}
	/**
	 * 学员id set方法
	 * @param stu_id
	 */
	public void setStu_id(Long stu_id) {
		this.stu_id = stu_id;
	}
	/**
	 * 学员姓名get方法
	 * @return
	 */
	public String getStu_name() {
		return stu_name;
	}
	/**
	 * 学员姓名set方法
	 * @param stu_name
	 */
	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}
	/**
	 * 班级id get方法
	 * @return
	 */
	public Long getCla_id() {
		return cla_id;
	}
	/**
	 * 班级id set方法
	 * @param cla_id
	 */
	public void setCla_id(Long cla_id) {
		this.cla_id = cla_id;
	}
	/**
	 * 班级名称get方法
	 * @return
	 */
	public String getCla_name() {
		return cla_name;
	}
	/**
	 * 班级名称set方法
	 * @param cla_name
	 */
	public void setCla_name(String cla_name) {
		this.cla_name = cla_name;
	}
	/**
	 * 企业id get方法
	 * @return
	 */
	public Long getCom_id() {
		return com_id;
	}
	/**
	 * 企业id set方法
	 * @param com_id
	 */
	public void setCom_id(Long com_id) {
		this.com_id = com_id;
	}
	/**
	 * 企业名称get方法
	 * @return
	 */
	public String getCom_name() {
		return com_name;
	}
	/**
	 * 企业名称set方法
	 * @param com_name
	 */
	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}
}
