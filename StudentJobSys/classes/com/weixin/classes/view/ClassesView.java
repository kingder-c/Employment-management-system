package com.weixin.classes.view;

import com.weixin.classes.form.Classes;
/**
 * 班级拓展类
 */
public class ClassesView extends Classes{
	private Long cou_id;
	private Long tea_id;
	private String cou_name;
	private String tea_name;
	
	/**
	 * 课程名称get方法
	 * @return
	 */
	public String getCou_name() {
		return cou_name;
	}
	/**
	 * 课程名称set方法
	 * @param cou_name
	 */
	public void setCou_name(String cou_name) {
		this.cou_name = cou_name;
	}
	/**
	 * 教师姓名get方法
	 * @return
	 */
	public String getTea_name() {
		return tea_name;
	}
	/**
	 * 教师姓名set方法
	 * @param tea_name
	 */
	public void setTea_name(String tea_name) {
		this.tea_name = tea_name;
	}
	/**
	 * 课程id get方法
	 * @return
	 */
	public Long getCou_id() {
		return cou_id;
	}
	/**
	 * 课程id set方法
	 * @param cou_id
	 */
	public void setCou_id(Long cou_id) {
		this.cou_id = cou_id;
	}
	/**
	 * 教师id get方法
	 * @return
	 */
	public Long getTea_id() {
		return tea_id;
	}
	/**
	 * 教师id set方法
	 * @param tea_id
	 */
	public void setTea_id(Long tea_id) {
		this.tea_id = tea_id;
	}
	
}
