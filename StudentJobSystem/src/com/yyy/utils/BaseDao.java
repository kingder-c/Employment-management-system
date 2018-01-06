package com.yyy.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *  数据库的一些公用的基本操作
 */
public abstract class BaseDao  {

	/**
	 * 获取表名称
	 * @return  对应表的名称
	 */
	public abstract String getTableName();
	/**
	 * 数据库查询结果集转换为实例对象
	 * @param rs
	 * @return 相应对象的封装后的实例
	 * @throws SQLException
	 */
	public abstract Object rs2Object(ResultSet rs) throws SQLException;
	/**
	 * 获取序列名称
	 * @return 序列名称
	 */
	public String getSequenceName() {
		return "SEQ_" + getTableName();
	}

	/**
	 * ORCALE中获取当前序列值
	 * 
	 * @return ORCALE中获取当前序列值
	 * @throws SQLException
	 */
	public long getSequence() throws SQLException {
		long seq = 0L;

		String sql = "SELECT " + this.getSequenceName() + ".NEXTVAL FROM dual ";

		PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);

		ResultSet rs = pstmt.executeQuery();
		if (rs.next())
			seq = rs.getLong(1);
		rs.close();
		pstmt.close();
		return seq;
	}

	/**
	 * 分页查询语句
	 * 
	 * @param sql
	 * @param page
	 * @return 完整的 分页语句
	 */
	public String getOrclCurPageSql(String sql, int page) {
		String s = "SELECT t2.* FROM(SELECT t1.*, rownum num FROM (" + sql
				+ ") t1 WHERE rownum <= " + PageUtil.getEnd(page)
				+ ") t2 WHERE num >= " + PageUtil.getBegin(page) + "";
		return s;
	}

	/**
	 * MYSQL中分页查询语句
	 * 
	 * @param sql
	 * @param page
	 * @return 完整的 MySql 中的分页语句
	 */
	public String getMysqlCurPageSql(String sql, int page) {
		String s = " SELECT t.* FROM (" + sql + ") t LIMIT "
				+ (PageUtil.getBegin(page) - 1) + ", " + PageUtil.PERPAGECOUNT;
		return s;
	}
}

