
package com.weixin.utils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

/**
 * 将request.getParameter("") 方法简化
 */
@SuppressWarnings("serial")
public class BaseRequestUtil extends HttpServlet {
	
	/**
	 * return String类型
	 * @param request
	 * @param name
	 * @param defult（初始值）
	 * @return String类型
	 */
	public static String getString(HttpServletRequest request, String name,
			String defult) {
		String value = request.getParameter(name);
		if (StringUtils.isNotBlank(value)) {
			return value;
		}
		return defult;
	}
	/**
	 * return String类型
	 * @param request
	 * @param name
	 * @return String类型
	 */
	public static String getString(HttpServletRequest request, String name) {
		return getString(request, name, "");
	}

	/**
	 * 有初始值的Long 类型
	 * @param request
	 * @param name
	 * @param defult
	 * @return 有初始值的Long 类型
	 */
	public static long getLong(HttpServletRequest request, String name,
			long defult) {
		String value = request.getParameter(name);
		if (StringUtils.isNotBlank(value)) {
			return Long.parseLong(value);
		}
		return defult;
	}
	/**
	 * return Long类型
	 * @param request
	 * @param name
	 * @return Long类型
	 */
	public static long getLong(HttpServletRequest request, String name) {
		return getLong(request, name, 0L);
	}

	/**
	 * return 有初始值的Int
	 * @param request
	 * @param name
	 * @param defult
	 * @return 有初始值的Int
	 */
	public static int getInt(HttpServletRequest request, String name, int defult) {
		String value = request.getParameter(name);
		if (StringUtils.isNotBlank(value)) {
			return Integer.parseInt(value);
		}
		return defult;
	}

	/**
	 * return Int类型
	 * @param request
	 * @param name
	 * @return Int类型
	 */
	public static int getInt(HttpServletRequest request, String name) {
		return getInt(request, name, 0);
	}

	/**
	 * return 有初始值的 Double类型
	 * @param request
	 * @param name
	 * @param defult
	 * @return 有初始值的 Double类型
	 */
	public static double getDouble(HttpServletRequest request, String name,
			double defult) {
		String value = request.getParameter(name);
		if (StringUtils.isNotBlank(value)) {
			return Double.parseDouble(value);
		}
		return defult;
	}

	/**
	 * return double
	 * @param request
	 * @param name
	 * @return double
	 */
	public static double getDouble(HttpServletRequest request, String name) {
		return getDouble(request, name, 0);
	}

	/**
	 * 测试 该类
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(StringUtils.isNotBlank("   "));
		System.out.println(StringUtils.isNotBlank(""));
		System.out.println(StringUtils.isNotBlank(null));

		System.out.println(Double.parseDouble("  12.12"));
	}
}
