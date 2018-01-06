package com.weixin.interviewinfo.form;
/**
 * 面试信息实体类
 */
public class InterviewInfo {
	private Long interview_id;				//面试id
	private String interview_company;		//面试单位
	private String interview_time;			//面试时间
	private String interview_type;			//面试方式
	private String interview_job;			//面试岗位
	private int interview_salary;			//薪资
	private String interview_weal;			//福利待遇
	private String interview_result;		//面试结果
	private String interview_tickling;		//学员面试反馈
	private String interviewer_tickling;	//面试官反馈
	private String interview_failreason;	//未被录用原因
	private String interview_manexplain;	//就业主管详细说明
	private String interview_note;			//面试备注
	private String interview_addtime;		//录入时间
	private String interview_adder;			//录入人
	private int interview_delete;			//是否删除
	/**
	 * 面试信息id get方法
	 * @return
	 */
	public Long getInterview_id() {
		return interview_id;
	}
	/**
	 * 面试信息id set方法
	 * @param interview_id
	 */
	public void setInterview_id(Long interview_id) {
		this.interview_id = interview_id;
	}
	/**
	 * 面试单位get方法
	 * @return
	 */
	public String getInterview_company() {
		return interview_company;
	}
	/**
	 * 面试单位set方法
	 * @param interview_company
	 */
	public void setInterview_company(String interview_company) {
		this.interview_company = interview_company;
	}
	/**
	 * 面试时间get方法
	 * @return
	 */
	public String getInterview_time() {
		return interview_time;
	}
	/**
	 * 面试时间set方法
	 * @param interview_time
	 */
	public void setInterview_time(String interview_time) {
		this.interview_time = interview_time;
	}
	/**
	 * 面试方式get方法
	 * @return
	 */
	public String getInterview_type() {
		return interview_type;
	}
	/**
	 * 面试方式set方法
	 * @param interview_type
	 */
	public void setInterview_type(String interview_type) {
		this.interview_type = interview_type;
	}
	/**
	 * 面试岗位get方法
	 * @return
	 */
	public String getInterview_job() {
		return interview_job;
	}
	/**
	 * 面试岗位set方法
	 * @param interview_job
	 */
	public void setInterview_job(String interview_job) {
		this.interview_job = interview_job;
	}
	/**
	 * 面试薪资get方法
	 * @return
	 */
	public int getInterview_salary() {
		return interview_salary;
	}
	/**
	 * 面试薪资set方法
	 * @param interview_salary
	 */
	public void setInterview_salary(int interview_salary) {
		this.interview_salary = interview_salary;
	}
	/**
	 * 福利待遇get方法
	 * @return
	 */
	public String getInterview_weal() {
		return interview_weal;
	}
	/**
	 * 福利待遇set方法
	 * @param interview_weal
	 */
	public void setInterview_weal(String interview_weal) {
		this.interview_weal = interview_weal;
	}
	/**
	 * 面试结果get方法
	 * @return
	 */
	public String getInterview_result() {
		return interview_result;
	}
	/**
	 * 面试结果set方法
	 * @param interview_result
	 */
	public void setInterview_result(String interview_result) {
		this.interview_result = interview_result;
	}
	/**
	 * 学员反馈get方法
	 * @return
	 */
	public String getInterview_tickling() {
		return interview_tickling;
	}
	/**
	 * 学员反馈set方法
	 * @param interview_tickling
	 */
	public void setInterview_tickling(String interview_tickling) {
		this.interview_tickling = interview_tickling;
	}
	/**
	 * 面试官反馈get方法
	 * @return
	 */
	public String getInterviewer_tickling() {
		return interviewer_tickling;
	}
	/**
	 * 面试官反馈set方法
	 * @param interviewer_tickling
	 */
	public void setInterviewer_tickling(String interviewer_tickling) {
		this.interviewer_tickling = interviewer_tickling;
	}
	/**
	 * 面试未通过原因get方法
	 * @return
	 */
	public String getInterview_failreason() {
		return interview_failreason;
	}
	/**
	 * 面试未通过原因set方法
	 * @param interview_failreason
	 */
	public void setInterview_failreason(String interview_failreason) {
		this.interview_failreason = interview_failreason;
	}
	/**
	 * 就业主管详细说明get方法
	 * @return
	 */
	public String getInterview_manexplain() {
		return interview_manexplain;
	}
	/**
	 * 就业主管详细说明set方法
	 * @param interview_manexplain
	 */
	public void setInterview_manexplain(String interview_manexplain) {
		this.interview_manexplain = interview_manexplain;
	}
	/**
	 * 备注get方法
	 * @return
	 */
	public String getInterview_note() {
		return interview_note;
	}
	/**
	 * 备注set方法
	 * @param interview_note
	 */
	public void setInterview_note(String interview_note) {
		this.interview_note = interview_note;
	}
	/**
	 * 录入时间get方法
	 * @return
	 */
	public String getInterview_addtime() {
		return interview_addtime;
	}
	/**
	 * 录入时间set方法
	 * @param interview_addtime
	 */
	public void setInterview_addtime(String interview_addtime) {
		this.interview_addtime = interview_addtime;
	}
	/**
	 * 录入人get方法
	 * @return
	 */
	public String getInterview_adder() {
		return interview_adder;
	}
	/**
	 * 录入人set方法
	 * @param interview_adder
	 */
	public void setInterview_adder(String interview_adder) {
		this.interview_adder = interview_adder;
	}
	/**
	 * 是否删除get方法
	 * @return
	 */
	public int getInterview_delete() {
		return interview_delete;
	}
	/**
	 * 是否删除set方法
	 * @param interview_delete
	 */
	public void setInterview_delete(int interview_delete) {
		this.interview_delete = interview_delete;
	}

	
}
