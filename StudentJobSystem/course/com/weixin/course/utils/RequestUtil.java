package com.weixin.course.utils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

@SuppressWarnings("serial")
public class RequestUtil extends HttpServlet{

	public static String getString(HttpServletRequest request, String name,
			String defult) {
		String value = request.getParameter(name);
		if (StringUtils.isNotBlank(value)) {
			return value;
		}
		return defult;
	}

	public static String getString(HttpServletRequest request, String name) {
		return getString(request, name, "");
	}

	public static long getLong(HttpServletRequest request, String name,
			long defult) {
		String value = request.getParameter(name);
		if (StringUtils.isNotBlank(value)) {
			return Long.parseLong(value);
		}
		return defult;
	}

	public static long getLong(HttpServletRequest request, String name) {
		return getLong(request, name, 0L);
	}

	public static int getInt(HttpServletRequest request, String name, int defult) {
		String value = request.getParameter(name);
		if (StringUtils.isNotBlank(value)) {
			return Integer.parseInt(value);
		}
		return defult;
	}

	public static int getInt(HttpServletRequest request, String name) {
		return getInt(request, name, 0);
	}

	public static double getDouble(HttpServletRequest request, String name,
			double defult) {
		String value = request.getParameter(name);
		if (StringUtils.isNotBlank(value)) {
			return Double.parseDouble(value);
		}
		return defult;
	}

	public static double getDouble(HttpServletRequest request, String name) {
		return getDouble(request, name, 0);
	}

	public static void main(String[] args) {
		System.out.println(StringUtils.isNotBlank("   "));
		System.out.println(StringUtils.isNotBlank(""));
		System.out.println(StringUtils.isNotBlank(null));

		System.out.println(Double.parseDouble("  12.12"));
	}
}
