package com.winxin.jobinfo.form;
/**
 * 就业信息的实体类
 * @注释 ：
 */

public class JobInfo {
	
	 //就业号
	
	private 	Long  	    job_id;
	/**
	 * 就业公司
	 */
	private    String 	job_company;
	/**
	 * 就业城市
	 */
	private		String	 job_city;
	/**
	 * 就业岗位
	 */
	private		String 	 job_job;
	/**
	 * 开始日期
	 */
	private		String    job_startdate;
	

	
	/**
	 * 基本工资
	 */
	private		Long      job_basesalary;

	/**
	 * 是否签订三方
	 */
	private    Long     job_three;
	
	/**
	 * 备注
	 */
	private		String    job_comment;
	
	
	
	/**
	 * 添加时间
	 */
	private		String   job_addtime;
	/**
	 * 添加人
	 */
	private    String   job_adder;
	/**
	 * 审核状态
	 */
	private    Long    job_delete;
	public Long getJob_id() {
		return job_id;
	}
	public void setJob_id(Long job_id) {
		this.job_id = job_id;
	}
	public String getJob_company() {
		return job_company;
	}
	public void setJob_company(String job_company) {
		this.job_company = job_company;
	}
	public String getJob_city() {
		return job_city;
	}
	public void setJob_city(String job_city) {
		this.job_city = job_city;
	}
	public String getJob_job() {
		return job_job;
	}
	public void setJob_job(String job_job) {
		this.job_job = job_job;
	}
	public String getJob_startdate() {
		return job_startdate;
	}
	public void setJob_startdate(String job_startdate) {
		this.job_startdate = job_startdate;
	}
	public Long getJob_basesalary() {
		return job_basesalary;
	}
	public void setJob_basesalary(Long job_basesalary) {
		this.job_basesalary = job_basesalary;
	}
	public Long getJob_three() {
		return job_three;
	}
	public void setJob_three(Long job_three) {
		this.job_three = job_three;
	}
	public String getJob_comment() {
		return job_comment;
	}
	public void setJob_comment(String job_comment) {
		this.job_comment = job_comment;
	}
	public String getJob_addtime() {
		return job_addtime;
	}
	public void setJob_addtime(String job_addtime) {
		this.job_addtime = job_addtime;
	}
	public String getJob_adder() {
		return job_adder;
	}
	public void setJob_adder(String job_adder) {
		this.job_adder = job_adder;
	}
	public Long getJob_delete() {
		return job_delete;
	}
	public void setJob_delete(Long job_delete) {
		this.job_delete = job_delete;
	}

	
	

}
