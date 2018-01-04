package com.weixin.course.utils;

public class PageUtil {
	/**
	 * 每页显示多少条记录
	 * （项目中固定的常量一般用final static public修饰符来修饰，
	 * 并且常量名命名规范 一般采用全部大写）
	 */
	final static public int PERPAGECOUNT = 10;

	/**
	 * 第page页的开始记录数
	 * @param page
	 * @return
	 */
	public static int getBegin(int page) {
		return (page - 1) * PERPAGECOUNT + 1;
	}

	/**
	 * 第page页的最后记录数
	 * @param page
	 * @return
	 */
	public static int getEnd(int page) {
		return page * PERPAGECOUNT;
	}

	/**
	 * 总页数。参数totalCount为总记录数
	 * @param totalCount
	 * @return
	 */
	public static int getTotalPage(int totalCount) {
		int totalPage = 1;
		if(totalCount > 0){
			totalPage = (int) Math.ceil((double) totalCount / PERPAGECOUNT);
		}
		return totalPage;
	}
}
