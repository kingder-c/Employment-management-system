package com.weixin.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
	/**
	 * 读取属性文件
	 * @return 属性文件载入内存
	 */
	public Properties getProperties()
	{
		
		InputStream in = getClass().getResourceAsStream("/dao.properties");
		Properties p = new Properties();
		try {
			p.load(in);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return p;
		
	}
	/**
	 * 从内存中的属性文件中读取需要的值
	 * @return 得到连接  Connection
	 */
	public static Connection getConnection(){
		DBConnection dbc = new DBConnection();
		Properties p = dbc.getProperties();
		String driverName = p.getProperty("driverName");	
		String url = p.getProperty("url");
		String user = p.getProperty("user");
		String password = p.getProperty("password");
		Connection con = null;
		try {
			Class.forName(driverName);
			
			con = DriverManager.getConnection(url,user,password);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	/**
	 * 关闭下面三个接口
	 * @param rs
	 * @param pstmt
	 * @param con
	 */
	public static void closeAll(ResultSet rs,PreparedStatement pstmt,Connection con)
	{
		if(rs != null)
		{
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(pstmt != null)
		{
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(con != null)
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * 关闭下面两个接口
	 * @param pstmt
	 * @param con
	 */
	public static void closeAll(PreparedStatement pstmt,Connection con)
	{
		if(pstmt != null)
		{
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(con != null)
		{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 关闭下面一个连接
	 * @param con
	 */
	public static void closeAll(Connection con)
	{
		if(con != null)
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
