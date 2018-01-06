package com.weixin.data.form;

/**
 *data  是 对应G5_data的实体类
 */
public class Data {

	/**
	 * 字典key	data_key	NUMBER(11)	11		TRUE	FALSE	TRUE
	 *字典名称	data_name	VARCHAR2(100)	100		FALSE	FALSE	FALSE
	 *字典值	data_num	VARCHAR2(100)	100		FALSE	FALSE	FALSE
	 *字典类型	data_type	VARCHAR2(100)	100		FALSE	FALSE	FALSE
	 *是否删除数据	data_delete	NUMBER(1)	1		FALSE	FALSE	FALSE
	 */
	/**
	 * 字典key
	 */
	private Long data_key;
	/**
	 * 字典名称
	 */
	private String data_name;	
	/**
	 * 字典值	
	 */
	private String data_num;
	/**
	 * 字典类型
	 */
	private String data_type;
	/**
	 * 是否删除数据
	 */
	private int data_delete;
	public Long getData_key() {
		return data_key;
	}
	public void setData_key(Long data_key) {
		this.data_key = data_key;
	}
	public String getData_name() {
		return data_name;
	}
	public void setData_name(String data_name) {
		this.data_name = data_name;
	}
	public String getData_num() {
		return data_num;
	}
	public void setData_num(String data_num) {
		this.data_num = data_num;
	}
	public String getData_type() {
		return data_type;
	}
	public void setData_type(String data_type) {
		this.data_type = data_type;
	}
	public int getData_delete() {
		return data_delete;
	}
	public void setData_delete(int data_delete) {
		this.data_delete = data_delete;
	}
	
	
	
}
