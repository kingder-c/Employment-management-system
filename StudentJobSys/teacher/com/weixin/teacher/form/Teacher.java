package com.weixin.teacher.form;
/**
 * 
 * @注释 ：
 */
public class Teacher {
	/**
	 *教师ID
	 */
	private Long tea_id;
	/**
	 * 教师姓名
	 */
	private String tea_name;
	/**
	 * 教师性别
	 */
	private int tea_sex;
	
	/**
	 * 毕业院校
	 */
	private String tea_school;
	/**
	 * 教师专业
	 */
	private String tea_major;	
	/**
	 * 教师电话
	 */
	private String tea_tel;
	/**
	 * 教师状态
	 */
	private String tea_state;
	
	/**
	 * 教师备注
	 */
	private String tea_note;
	/**
	 * 教师录入人
	 */
	private String tea_adder;
	/**
	 * 教师录入时间
	 */
	private String tea_addtime;
	/**
	 * 是否删除教师
	 */
	private String tea_delete;
	
	public Long getTea_id() {
		return tea_id;
	}
	public void setTea_id(Long tea_id) {
		this.tea_id = tea_id;
	}
	public String getTea_name() {
		return tea_name;
	}
	public void setTea_name(String tea_name) {
		this.tea_name = tea_name;
	}
	public int getTea_sex() {
		return tea_sex;
	}
	public void setTea_sex(int tea_sex) {
		this.tea_sex = tea_sex;
	}
	public String getTea_school() {
		return tea_school;
	}
	public void setTea_school(String tea_school) {
		this.tea_school = tea_school;
	}
	public String getTea_major() {
		return tea_major;
	}
	public void setTea_major(String tea_major) {
		this.tea_major = tea_major;
	}
	public String getTea_tel() {
		return tea_tel;
	}
	public void setTea_tel(String tea_tel) {
		this.tea_tel = tea_tel;
	}
	public String getTea_state() {
		return tea_state;
	}
	public void setTea_state(String tea_state) {
		this.tea_state = tea_state;
	}
	public String getTea_note() {
		return tea_note;
	}
	public void setTea_note(String tea_note) {
		this.tea_note = tea_note;
	}
	public String getTea_adder() {
		return tea_adder;
	}
	public void setTea_adder(String tea_adder) {
		this.tea_adder = tea_adder;
	}
	public String getTea_addtime() {
		return tea_addtime;
	}
	public void setTea_addtime(String tea_addtime) {
		this.tea_addtime = tea_addtime;
	}
	public String getTea_delete() {
		return tea_delete;
	}
	public void setTea_delete(String tea_delete) {
		this.tea_delete = tea_delete;
	}
	
	
}
