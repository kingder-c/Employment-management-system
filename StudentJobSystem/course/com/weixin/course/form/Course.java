package com.weixin.course.form;
/**
 * 课程实体类
 */
public class Course {
	/**
	 * 课程ID
	 */
	private Long cou_id;
	/**
	 * 课程名称
	 */
	private String cou_name;
	/**
	 * 课程介绍
	 */
	private String cou_note;
	/**
	 * 课程展示图
	 */
	private String cou_logo;
	/**
	 * 课程录入人
	 */
	private String cou_adder;
	/**
	 * 课程录入时间
	 */
	private String cou_date;
	/**
	 * 是否删除课程
	 */
	private int cou_delete;
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
	 * 课程名称 get方法
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
	 * 课程介绍get方法
	 * @return
	 */
	public String getCou_note() {
		return cou_note;
	}
	/**
	 * 课程介绍set方法
	 * @param cou_note
	 */
	public void setCou_note(String cou_note) {
		this.cou_note = cou_note;
	}
	/**
	 * 课程展示图get方法
	 * @return
	 */
	public String getCou_logo() {
		return cou_logo;
	}
	/**
	 * 课程展示图set方法
	 * @param cou_logo
	 */
	public void setCou_logo(String cou_logo) {
		this.cou_logo = cou_logo;
	}
	/**
	 * 录入人get方法
	 * @return
	 */
	public String getCou_adder() {
		return cou_adder;
	}
	/**
	 * 录入人set方法
	 * @param cou_adder
	 */
	public void setCou_adder(String cou_adder) {
		this.cou_adder = cou_adder;
	}
	/**
	 * 添加日期get方法
	 * @return
	 */
	public String getCou_date() {
		return cou_date;
	}
	/**
	 * 添加日期set方法
	 * @param cou_date
	 */
	public void setCou_date(String cou_date) {
		this.cou_date = cou_date;
	}
	/**
	 * 是否删除get方法
	 * @return
	 */
	public int getCou_delete() {
		return cou_delete;
	}
	/**
	 * 是否删除set方法
	 * @param cou_delete
	 */
	public void setCou_delete(int cou_delete) {
		this.cou_delete = cou_delete;
	}

}
