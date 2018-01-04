package com.winxin.jobinfo.view;

import com.winxin.jobinfo.form.JobInfo;

public class JobInfoView  extends JobInfo{
      
	 /**
	  * 企业号
	  */
	 private Long  com_id;
	 /**
	  * 学生号
	  */
      private Long stu_id;
      /**
       * 班级名
       */
      private String cla_name;
      /**
       * 学生姓名
       */
	  private String stu_name;
	  /**
	   * 课程名
	   */
      private String cou_name;
      /**
       * 学生籍贯
       */
      private String stu_native;
      /**
       * 公司 名称
       */
      private String com_name;
      /**
       * 公司名的get方法
       */
      public String getCom_name() {
		return com_name;
	}
      /**
       * 公司名的set方法
       */
	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}
	/**
     * 学生籍贯的get方法
     */
	public String getStu_native() {
		return stu_native;
	}
	/**
     * 学生籍贯的set方法
     */
	public void setStu_native(String stu_native) {
		this.stu_native = stu_native;
	}
	/**
     * 班级名程的get方法
     */
	public String getCla_name() {
  		return cla_name;
  	}
	/**
     * 班级名程的set方法
     */
  	public void setCla_name(String cla_name) {
  		this.cla_name = cla_name;
  	}
  	/**
     * 课程名程的get方法
     */
	public String getCou_name() {
		return cou_name;
	}
	/**
     * 课程名程的set方法
     */
	public void setCou_name(String cou_name) {
		this.cou_name = cou_name;
	}
	/**
	 * 公司号的get方法
	 * @return
	 */
	public Long getCom_id() {
		return com_id;
	}
	/**
     * 公司号的set方法
     */
	public void setCom_id(Long com_id) {
		this.com_id = com_id;
	}
	/**
	 * 学号的get方法
	 * @return
	 */
	public Long getStu_id() {
		return stu_id;
	}
	/**
	 * 学号的get方法
	 * @return
	 */
	public String getStu_name() {
		return stu_name;
	}
	/**
	 * 学生姓名set方法
	 * @return
	 */
	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}
	/**
	 * 学号的set方法
	 * @return
	 */
	public void setStu_id(Long stu_id) {
		this.stu_id = stu_id;
	}
	
		
		
	
}
