
package com.yyy.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class TeacherBaseDao {
	/**
	 * 获取表名称
	 * @return
	 */
	public abstract String getTableName();
	/**
	 * 数据库查询结果集转换为实例对象
	 * @param rs
	 * @return 
	 * @throws SQLException
	 */
	public abstract Object rsObject(ResultSet rs) throws SQLException;
	/**
	 * 获取序列名称
	 * @return
	 */
	public String getSequenceName() {
		return "SEQ_" + getTableName();
	}

	/**
	 * ORCALE中获取当前序列值
	 * 
	 * @return
	 * @throws SQLException
	 */
	public long getSequence() throws SQLException {
		long seq = 0L;

		String sql = "SELECT " + this.getSequenceName() + ".NEXTVAL FROM dual ";

		PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(
				sql);

		ResultSet rs = pstmt.executeQuery();
		if (rs.next())
			seq = rs.getLong(1);
		rs.close();
		pstmt.close();
		return seq;
	}

	/**
	 * ORCALE中分页查询语句
	 * 
	 * @param sql
	 * @param page
	 * @return
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
	 * @return
	 */
	public String getMysqlCurPageSql(String sql, int page) {
		String s = " SELECT t.* FROM (" + sql + ") t LIMIT "
				+ (PageUtil.getBegin(page) - 1) + ", " + PageUtil.PERPAGECOUNT;
		return s;
	}
}

