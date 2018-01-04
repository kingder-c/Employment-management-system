package com.weixin.company.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.weixin.company.form.Company;
import com.weixin.student.view.StudentView;
import com.weixin.teacher.form.Teacher;
import com.weixin.utils.BaseDao;
import com.weixin.utils.DBConnection;

/**
 * @注释 ：
 */
public class CompanyDao extends BaseDao{
	/**
	 * 获取表名
	 */
	@Override
	public String getTableName() {
		return "COMPANY_INFO";
	}
	/**
	 * 返回结果集
	 */
	@Override
	public Object rs2Object(ResultSet rs) throws SQLException {
		Company obj = new Company();
		obj.setCom_id(rs.getLong("com_id"));
		obj.setCom_name(rs.getString("com_name"));
		obj.setCom_city(rs.getString("com_city"));
		obj.setCom_status(rs.getInt("com_status"));
		obj.setCom_person(rs.getString("com_person"));
		obj.setCom_phone(rs.getString("com_phone"));
		obj.setCom_email(rs.getString("com_email"));
		obj.setCom_note(rs.getString("com_note"));
		obj.setCom_level(rs.getString("com_level"));
		obj.setCom_direction(rs.getString("com_direction"));
		obj.setCom_checkstatus(rs.getString("com_checkstatus"));
		return obj;
	}
	/**
	 * 插入一条记录
	 * @param obj
	 * @return
	 * @throws SQLException
	 */
	public long insert(Company obj)throws SQLException{
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO "
				+ this.getTableName()
				+ " (com_name, com_city,com_person,com_phone,com_email,com_direction,com_status,com_level,com_note,com_checkstatus) VALUES(?,?,?,?,?,?,?,?,?,?)";
		long id=1;
		int i = 1;
		try {
			pstmt = conn.prepareStatement(sql);
			//id = this.getSequence();

			//pstmt.setLong(i++, id);
			pstmt.setString(i++, obj.getCom_name());
			pstmt.setString(i++, obj.getCom_city());
			pstmt.setString(i++, obj.getCom_person());
			pstmt.setString(i++, obj.getCom_phone());
			pstmt.setString(i++, obj.getCom_email());
			pstmt.setString(i++, obj.getCom_direction());
			pstmt.setInt(i++, obj.getCom_status());
			pstmt.setString(i++, obj.getCom_level());
			pstmt.setString(i++, obj.getCom_note());
			pstmt.setString(i++, obj.getCom_checkstatus());

			pstmt.executeUpdate();
		} finally {
			DBConnection.closeAll(pstmt, conn);
		}
		return id;
	}
	/**
	 * 删除一条记录
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int delete(Long id) throws SQLException{
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM " + getTableName()
				+ " WHERE com_id = ?";
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
	 * 根据id查询一条合作企业记录
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Company getById(Long id) throws SQLException {
		Company obj = null;
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM " + this.getTableName()
				+ " WHERE com_id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				obj = (Company) this.rs2Object(rs);
			}
		} finally {
			DBConnection.closeAll(rs, pstmt, conn);
		}
		return obj;
	}
	/**
	 * 根据id修改一条合作企业信息
	 * @param obj
	 * @return
	 * @throws SQLException
	 */
	public int update(Company obj) throws SQLException{
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		String sql = "UPDATE "
				+ this.getTableName()
				+ " SET com_name=?, com_city=?, com_person=?, com_phone=?, com_email=?, com_direction=?, com_status=?, com_level=?, com_note=?, com_checkstatus=? where com_id=?";
		int flag = 0;
		int i = 1;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(i++, obj.getCom_name());
			pstmt.setString(i++, obj.getCom_city());
			pstmt.setString(i++, obj.getCom_person());
			pstmt.setString(i++, obj.getCom_phone());
			pstmt.setString(i++, obj.getCom_email());
			pstmt.setString(i++, obj.getCom_direction());
			pstmt.setInt(i++, obj.getCom_status());
			pstmt.setString(i++, obj.getCom_level());
			pstmt.setString(i++, obj.getCom_note());
			pstmt.setString(i++, obj.getCom_checkstatus());
			pstmt.setLong(i++, obj.getCom_id());
			flag = pstmt.executeUpdate();
		} finally {
			DBConnection.closeAll(pstmt, conn);
		}
		return flag;
	}
	/**
	 * 分页条件查询合作企业信息
	 * @param obj
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List query(Company obj,int page) throws SQLException{
		List list = new ArrayList();
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM " + this.getTableName() + " t WHERE 1=1");
		if (obj != null) {
			if (StringUtils.isNotBlank(obj.getCom_name())) {
				sql.append(" AND t.com_name LIKE '%"
						+ obj.getCom_name() + "%' ");
			}
			if (StringUtils.isNotBlank(obj.getCom_city())){
				sql.append(" AND com_city LIKE'%"
						+obj.getCom_city()+"%'");
			}if (StringUtils.isNotBlank(obj.getCom_direction())){
				sql.append(" AND com_direction LIKE'%"
						+obj.getCom_direction()+"%'");
			}if(obj.getCom_status()!=5){
				sql.append(" AND com_status = "+obj.getCom_status() );
			}
		sql.append(" ORDER BY t.com_id DESC");
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
	public int getCount(Company obj) throws SQLException {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) FROM " + this.getTableName() + " t WHERE 1=1 ");
		if (obj != null) {
			if (StringUtils.isNotBlank(obj.getCom_name())) {
				sql.append(" AND com_name LIKE '%"
						+ obj.getCom_name() + "%' ");
			}if (StringUtils.isNotBlank(obj.getCom_city())){
				sql.append("AND com_city LIKE'%"
						+obj.getCom_city()+"%'");
			}if(obj.getCom_status()!=5){
				sql.append("AND com_status = "+obj.getCom_status() );
			}
			sql.append(" ORDER BY t.com_id DESC");
			
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
	 * 供其他模块查询企业
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getName() throws SQLException {
		Connection conn =DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List selectList = new ArrayList();

		String sql= "select com_id, com_name from "+this.getTableName();
		try {
			pstmt= conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Company com = new Company();
				com.setCom_id(rs.getLong("com_id"));
				com.setCom_name(rs.getString("com_name"));
				selectList.add(com);
			}
		} finally{
			DBConnection.closeAll(rs, pstmt, conn);
		}
		return selectList;
	}
}
