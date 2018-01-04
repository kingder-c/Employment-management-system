package com.weixin.student.view;

import com.weixin.student.form.Student;

/**
 * StudentView 是 student的  扩展类  将Student的 扩展属性放在这里    相当于   数据 库中的  g5_student 表   和  查询视图   
 */

public class StudentView extends Student{

	/**
	 * 班级id
	 */
	private Long cla_id;
	/**
	 * 班级名称
	 */
	private String cla_name;
	/**
	 * 入班时间
	 */
	private String cla_starttime;
	
	/**
	 * 绑定的账户id
	 */
	private Long user_id;
	/**
	 * 课程名称
	 */
	private String cou_name;
	/**
	 * @return the cou_name
	 */
	public String getCou_name() {
		return cou_name;
	}


	/**
	 * @param cou_name the cou_name to set
	 */
	public void setCou_name(String cou_name) {
		this.cou_name = cou_name;
	}


	/**
	 * @return the cla_name
	 */
	public String getCla_name() {
		return cla_name;
	}


	/**
	 * @param cla_name the cla_name to set
	 */
	public void setCla_name(String cla_name) {
		this.cla_name = cla_name;
	}


	/**
	 * @return the cla_starttime
	 */
	public String getCla_starttime() {
		return cla_starttime;
	}


	/**
	 * @param cla_starttime the cla_starttime to set
	 */
	public void setCla_starttime(String cla_starttime) {
		this.cla_starttime = cla_starttime;
	}


	/**
	 * @return the user_id
	 */
	public Long getUser_id() {
		return user_id;
	}


	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}


	/**
	 * 构造方法
	 */
	public StudentView(){
		
	}
	
	/**
	 * 
	 * @return cla_id
	 */
	public Long getCla_id() {
		return cla_id;
	}

	/**
	 * 
	 * @param cla_id
	 */
	public void setCla_id(Long cla_id) {
		this.cla_id = cla_id;
	}


	
	
}
