package com.yyy.teacher.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yyy.teacher.form.Teacher;
import com.yyy.utils.BaseDao;
import com.yyy.utils.DBConnection;


/**
 * 老师持久化
 */
public class TeacherDao extends BaseDao {
	
	@Override
	public String getTableName() {
		return "teacher_info";
	}

	@Override
	public Object rs2Object(ResultSet rs) throws SQLException {
		Teacher obj = new Teacher();
		obj.setTea_id(rs.getLong("tea_id"));
		obj.setTea_name(rs.getString("tea_name"));
		obj.setTea_sex(rs.getInt("tea_sex"));
		obj.setTea_major(rs.getString("tea_major"));
		obj.setTea_state(rs.getString("tea_state"));
		obj.setTea_tel(rs.getString("tea_tel"));
		return obj;
	}

	/**.
	 * 插入一条老师记录
	 * 
	 * @param obj
	 * @return
	 * @throws SQLException
	 */
	public long insert(Teacher obj) throws SQLException {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO "
				+ this.getTableName()
				+ " (tea_name,tea_tel,tea_state) VALUES (?,?,?)";
		long id=1;
		int i = 1;
		try {
			pstmt = conn.prepareStatement(sql);
		    //id = this.getSequence();

			//pstmt.setLong(i++, id);
			pstmt.setString(i++, obj.getTea_name());
			pstmt.setString(i++, obj.getTea_tel());
			pstmt.setString(i++, obj.getTea_state());
			
			pstmt.executeUpdate();
		} finally {
			DBConnection.closeAll(pstmt, conn);
		}
		return id;
	}

	/**
	 * 删除一条老师记录
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int delete(Long id) throws SQLException {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM " + this.getTableName()
				+ " WHERE tea_id = ?";
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
	 * 根据ID查询一条老师记录
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Teacher getById(Long id) throws SQLException {
		Teacher obj = null;
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + this.getTableName()
				+ " WHERE tea_id = ?";
		pstmt = conn.prepareStatement(sql);
		try {
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				obj = (Teacher) this.rs2Object(rs);
			}
		} finally {
			DBConnection.closeAll(rs, pstmt, conn);
		}
		return obj;
	}

	/**
	 * 修改一条老师信息
	 * 
	 * @param obj
	 * @throws SQLException
	 */
	public int update(Teacher obj) throws SQLException {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		String sql = "UPDATE "
				+ this.getTableName()
				+ " SET tea_name=?,tea_tel=?,tea_state=? where tea_id=?";
		int flag = 0;
		int i = 1;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(i++, obj.getTea_name());
			pstmt.setString(i++, obj.getTea_tel());
			pstmt.setString(i++, obj.getTea_state());
			pstmt.setLong(i++, obj.getTea_id());
			flag = pstmt.executeUpdate();
		} finally {
			DBConnection.closeAll(pstmt, conn);
		}
		return flag;
	}

	/**
	 * 分页条件查询老师
	 * 
	 * @param obj
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List query(Teacher obj, int page) throws SQLException {
		List list = new ArrayList();
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM " + this.getTableName() + " t WHERE 1=1");
		if (obj != null) {
			if (StringUtils.isNotBlank(obj.getTea_name())) {
				sql.append(" AND t.tea_name LIKE '%"
						+ obj.getTea_name() + "%' ");
			}if(StringUtils.isNotBlank(obj.getTea_state())){
				sql.append(" AND tea_state LIKE'%"
						+obj.getTea_state()+"%'");
			}
		sql.append(" ORDER BY t.tea_id DESC");
		}
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
	 * 条件查询总记录数
	 * 
	 * @param obj
	 * @return
	 * @throws SQLException
	 */
	public int getCount(Teacher obj) throws SQLException {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) FROM " + this.getTableName() + " t WHERE 1=1");
		if (obj != null) {
			if (StringUtils.isNotBlank(obj.getTea_name())) {
				sql.append(" AND tea_name LIKE '%"
						+ obj.getTea_name() + "%' ");
			}if(StringUtils.isNotBlank(obj.getTea_state())){
				sql.append(" AND tea_state LIKE'%"
						+obj.getTea_state()+"%'");
			}
			sql.append(" ORDER BY t.tea_id DESC");
			
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getName() throws SQLException{
		Connection conn =DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List selectList = new ArrayList();

		String sql= "select tea_id, tea_name from "+this.getTableName();
		try {
			pstmt= conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Teacher tea = new Teacher();
				tea.setTea_id(rs.getLong("tea_id"));
				tea.setTea_name(rs.getString("tea_name"));
				selectList.add(tea);
			}
		} finally{
			DBConnection.closeAll(rs, pstmt, conn);
		}
		return selectList;
	}

}
