package com.weixin.student.form;

/**
 * @注释 ：学员的实体类  只包含基本信息
 * 
 *   stu_id	Number (11)   学员 id
	*stu_name	Variable characters (30) 学员姓名
	*stu_sex	Number (1)  学员性别
	*stu_native	Variable characters (50)  籍贯
	*stu_address	Variable characters (100)  住址
	*stu_email	Variable characters (50)  邮箱
	*stu_phone	Characters (11) 手机号  
	*stu_graduation	Variable characters (20) 毕业院校
	*stu_major	Variable characters (30) 专业
	*stu_into_time	Date  大学入学年份
	*stu_state	Variable characters (20)  学员状态
	*stu_check	Number (1)  学员审核状态
	*stu_note	Variable characters (500)  学员备注
	*stu_adder	Variable characters (30)  学员录入人
	*stu_addtime	Date  学员录入时间
	*Stu_delete	Number(1)  是否删除学员
	*
 */
public class Student {
	/*
	 * 属性区
	 */
	/**
	 * 学员 id
	 */
    private	Long stu_id;
    /**
     * 学员姓名
     */
    private	String stu_name;
    /**
     * 学员性别
     */
    private	Long stu_sex;
    /**
     * 学员籍贯
     */
    private	String stu_native;
    /**
     * 学员住址
     */
    private	String stu_address;
    /**
     * 学员邮箱
     */
    private	String stu_email;
    /**
     * 学员手机
     */
    private	String stu_phone;
    
    /**
     * 学员毕业院校
     */
    private	String stu_graduation;
    
    /**
     * 学员专业
     */
    private	String stu_major;
    /**
     * 学员入学时间
     */
    private	String stu_into_time;
    /**
     * 学员状态
     */
    private	String stu_state;
    /**
     * 学员审核状态
     */
    private	Long stu_check;
    /**
     * 备注
     */
    private	String stu_note;
    /**
     * 添加人
     */
    private	String stu_adder;
    /**
     * 添加人
     */
    private	String stu_addtime;
    /**
     * 是否删除
     */
    private	Long stu_delete;
	public Long getStu_id() {
		return stu_id;
	}
	public void setStu_id(Long stu_id) {
		this.stu_id = stu_id;
	}
	public String getStu_name() {
		return stu_name;
	}
	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}
	public Long getStu_sex() {
		return stu_sex;
	}
	public void setStu_sex(Long stu_sex) {
		this.stu_sex = stu_sex;
	}
	public String getStu_native() {
		return stu_native;
	}
	public void setStu_native(String stu_native) {
		this.stu_native = stu_native;
	}
	public String getStu_address() {
		return stu_address;
	}
	public void setStu_address(String stu_address) {
		this.stu_address = stu_address;
	}
	public String getStu_email() {
		return stu_email;
	}
	public void setStu_email(String stu_email) {
		this.stu_email = stu_email;
	}
	public String getStu_phone() {
		return stu_phone;
	}
	public void setStu_phone(String stu_phone) {
		this.stu_phone = stu_phone;
	}
	public String getStu_graduation() {
		return stu_graduation;
	}
	public void setStu_graduation(String stu_graduation) {
		this.stu_graduation = stu_graduation;
	}
	public String getStu_major() {
		return stu_major;
	}
	public void setStu_major(String stu_major) {
		this.stu_major = stu_major;
	}
	public String getStu_into_time() {
		return stu_into_time;
	}
	public void setStu_into_time(String stu_into_time) {
		this.stu_into_time = stu_into_time;
	}
	public String getStu_state() {
		return stu_state;
	}
	public void setStu_state(String stu_state) {
		this.stu_state = stu_state;
	}
	public Long getStu_check() {
		return stu_check;
	}
	public void setStu_check(Long stu_check) {
		this.stu_check = stu_check;
	}
	public String getStu_note() {
		return stu_note;
	}
	public void setStu_note(String stu_note) {
		this.stu_note = stu_note;
	}
	public String getStu_adder() {
		return stu_adder;
	}
	public void setStu_adder(String stu_adder) {
		this.stu_adder = stu_adder;
	}
	public String getStu_addtime() {
		return stu_addtime;
	}
	public void setStu_addtime(String stu_addtime) {
		this.stu_addtime = stu_addtime;
	}
	public Long getStu_delete() {
		return stu_delete;
	}
	public void setStu_delete(Long stu_delete) {
		this.stu_delete = stu_delete;
	}
    
    

}
