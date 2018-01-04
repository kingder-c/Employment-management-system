package com.weixin.course.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.weixin.course.form.Course;
import com.weixin.course.utils.PageUtil;
import com.weixin.utils.BaseDao;
import com.weixin.utils.DBConnection;
/**
 * 课程信息管理DAO层
 */
public class CourseDao extends BaseDao{
	/**
	 * 获取表名
	 */
	public String getTableName() {
		return "COURSE_INFO";
	}
	/**
	 * 返回结果集
	 */
	public Object rs2Object(ResultSet rs) throws SQLException {
		Course obj = new Course();
		obj.setCou_id(rs.getLong("cou_id"));
		obj.setCou_name(rs.getString("cou_name"));
		obj.setCou_note(rs.getString("cou_note"));
		obj.setCou_logo(rs.getString("cou_logo"));
		obj.setCou_adder(rs.getString("cou_adder"));
		obj.setCou_date(rs.getDate("cou_date").toString());
		obj.setCou_delete(rs.getInt("cou_delete"));
		return obj;
	}
	
	/**
	 * 添加课程信息
	 * @param obj
	 * @return
	 * @throws SQLException
	 */
	public long insert(Course obj) throws SQLException {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO "
				+ this.getTableName()
				+ "( cou_name, cou_note, cou_logo,cou_adder,cou_date,cou_delete) "
				+ "VALUES(?,?,?,?,?,?)";
		long id=1;
		int i = 1;
		try {
			pstmt = conn.prepareStatement(sql);
			//id = this.getSequence();
			
			//pstmt.setLong(i++, id);
			pstmt.setString(i++, obj.getCou_name());
			pstmt.setString(i++, obj.getCou_note());
			pstmt.setString(i++, obj.getCou_logo());
			pstmt.setString(i++, obj.getCou_adder());
			pstmt.setDate(i++, Date.valueOf(obj.getCou_date()));
			pstmt.setInt(i++, obj.getCou_delete());
			pstmt.executeUpdate();
		} finally {
			DBConnection.closeAll(pstmt, conn);
		}
		return id;
	}

	/**
	 * 删除课程记录
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int delete(Long id) throws SQLException {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM " + getTableName()
				+ " WHERE cou_id = ?";
		int flag = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			flag = pstmt.executeUpdate();
		} finally {
			DBConnection.closeAll(pstmt, conn);
		}
		return flag;
	}
	
	/**
	 * 根据ID查询课程信息
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Course getById(Long id) throws SQLException {
		Course obj = null;
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + this.getTableName()
				+ " WHERE cou_id = ?";
		pstmt = conn.prepareStatement(sql);
		try {
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				obj = (Course) this.rs2Object(rs);
			}
		} finally {
			DBConnection.closeAll(rs, pstmt, conn);
		}
		return obj;
	}
	
	/**
	 * 修改课程信息
	 * @param obj
	 * @return
	 * @throws SQLException
	 */
	public int update(Course obj) throws SQLException {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		String sql = "UPDATE "
				+ this.getTableName()
				+ " SET cou_name=?, cou_note=?, cou_logo=?, cou_adder=?, cou_date=?, cou_delete=? where cou_id=?";
		int flag = 0;
		int i = 1;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(i++, obj.getCou_name());
			pstmt.setString(i++, obj.getCou_note());
			pstmt.setString(i++, obj.getCou_logo());
			pstmt.setString(i++, obj.getCou_adder());
			pstmt.setDate(i++, Date.valueOf(obj.getCou_date()));
			pstmt.setInt(i++, obj.getCou_delete());
			pstmt.setLong(i++, obj.getCou_id());
			flag = pstmt.executeUpdate();
		} finally {
			DBConnection.closeAll(pstmt, conn);
		}
		return flag;
	}
	
	/**
	 * 分页查询课程信息
	 * @param obj
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List query(Course obj, int page) throws SQLException {
		List list = new ArrayList();
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT cou_id,cou_name,cou_note,cou_logo,cou_adder,cou_date,cou_delete FROM "
				+ this.getTableName() + " WHERE 1=1");
		if (obj != null) {
			if (StringUtils.isNotBlank(obj.getCou_name())) {
				sql.append(" AND cou_name LIKE '%"
						+ obj.getCou_name() + "%' ");
			}
		}
		sql.append(" ORDER BY cou_id DESC");
		String s = getMysqlCurPageSql(sql.toString(), page);
		
		try {
			pstmt = conn.prepareStatement(s);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(this.rs2Object(rs));
			}
		} finally {
			DBConnection.closeAll(rs, pstmt, conn);
		}
		return list;
	}
	
	/**
	 * 条件查询课程记录数
	 * @param obj
	 * @return
	 * @throws SQLException
	 */
	public int getCount(Course obj) throws SQLException {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(*) FROM " + this.getTableName() + " WHERE 1=1");
		if (obj != null) {
			if (StringUtils.isNotBlank(obj.getCou_name())) {
				sql.append(" AND cou_name LIKE '%"
						+ obj.getCou_name() + "%' ");
			}
		}
		try {
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} finally {
			DBConnection.closeAll(rs, pstmt, conn);
		}
		return count;
	}
	
	
	
	
	
	
	
	
	/**
	 * 分页查询语句
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
	 * 课程下拉列表
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getName() throws SQLException{
		Connection conn =DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List selectList = new ArrayList();

		String sql= "select cou_id, cou_name from "+this.getTableName();
		try {
			pstmt= conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Course cou = new Course();
				cou.setCou_id(rs.getLong("cou_id"));
				cou.setCou_name(rs.getString("cou_name"));
				selectList.add(cou);
			}
		} finally{
			DBConnection.closeAll(rs, pstmt, conn);
		}
		return selectList;
	}
	
}
