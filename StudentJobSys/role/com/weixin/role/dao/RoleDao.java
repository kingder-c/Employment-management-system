package com.weixin.role.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;








import org.apache.commons.lang.StringUtils;

import com.weixin.role.form.Role;
import com.weixin.utils.BaseDao;
import com.weixin.utils.DBConnection;
/**
 * @注释 ：
 */
public class RoleDao extends BaseDao {
	@Override
	public String getTableName() {
		return "role_info";
	}
/**
 * 返回结果集
 */
	@Override
	public Object rs2Object(ResultSet rs) throws SQLException {
		Role obj = new Role();
		obj.setRole_id(rs.getLong("role_id"));
		obj.setRole_desc(rs.getString("role_desc"));
		obj.setRole_name(rs.getString("role_name"));
		obj.setRole_delete(rs.getInt("role_delete"));
		obj.setAuth_ids(rs.getString("auth_ids"));
		return obj;
	}
	/**
	 * 插入一条数据
	 * @param obj
	 * @return
	 * @throws SQLException
	 */
	public long insert(Role obj) throws SQLException{
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		String sql = "insert into "+this.getTableName()
				+" ( role_name,auth_ids,role_desc ) values (?,?,?)";
		long id=1;
		int i = 1;
		try {
			pstmt = conn.prepareStatement(sql);
			//id = this.getSequence();
			
			//pstmt.setLong(i++, id);
			pstmt.setString(i++,obj.getRole_name());
			pstmt.setString(i++,obj.getAuth_ids());
			pstmt.setString(i++,obj.getRole_desc());
			
			pstmt.executeUpdate();
		} finally {
			DBConnection.closeAll(pstmt, conn);
		}
		
		
		return id;
	}
	/**
	 * 删除一条数据
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int delete(Long id) throws SQLException {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM " + getTableName()
				+ " WHERE role_id = ?";
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
	 * 根据ID来查询role
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Role getById(Long id) throws SQLException{
		Role obj = null;
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from "+this.getTableName()+" where role_id = ?";
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setLong(1, id);
		rs = pstmt.executeQuery();
		if(rs.next()){
			obj = (Role) this.rs2Object(rs);
		}
		DBConnection.closeAll(rs, pstmt, conn);
		return obj;
 	}
	/**
	 * 更新数据
	 * @param obj
	 * @return
	 * @throws SQLException
	 */
	public int update(Role obj) throws SQLException{
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		String sql = "update "+this.getTableName()
				+" set role_name=?,auth_ids=?,role_desc=? where role_id=? ";
		int flag = 0;
		int i = 1;
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(i++, obj.getRole_name());
		pstmt.setString(i++, obj.getAuth_ids());
		pstmt.setString(i++, obj.getRole_desc());
		pstmt.setLong(i++, obj.getRole_id() );
		flag = pstmt.executeUpdate();
		DBConnection.closeAll(pstmt, conn);
		return flag;
	}
	/**
	 * 条件查询总记录
	 * @param obj
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	public int getCount(Role obj) throws SQLException{
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) from "+this.getTableName()+" r where 1=1");
		if (obj != null) {
			if (StringUtils.isNotEmpty(obj.getRole_name())) {
				sql.append(" and r.role_name like '%"
						+obj.getRole_name()+"%' ");
			}
			sql.append(" order by r.role_id desc ");
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
	 * 
	 * @param obj
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List query(Role obj,int page) throws SQLException{
		List list = new ArrayList();
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select * from "+this.getTableName()+" r where 1=1");
		if (obj != null) {
			if (StringUtils.isNotEmpty(obj.getRole_name())) {
				sql.append(" and r.role_name like '%"
						+obj.getRole_name()+"%' ");
			}
			sql.append(" order by r.role_id desc ");
		}
		String s = getMysqlCurPageSql(sql.toString(), page);
		try {
			pstmt = conn.prepareStatement(s);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(this.rs2Object(rs));
			}
		} finally {
			DBConnection.closeAll(rs, pstmt, conn);
		}
		return list;
	}
	
	public String getGrant(long role_id)throws SQLException{
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String str = "";
		String sql = "select auth_ids from "+ this.getTableName() + " where role_id = ?";
		try{
			pstmt = conn.prepareStatement(sql);			
			pstmt.setLong(1,role_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				str = rs.getString(1);
			}
		}finally{
			DBConnection.closeAll(rs, pstmt, conn);
		}
		return str;
	}
	
	public int grant(long role_id,String role_ids)throws SQLException{
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		int flag = 0;
		String sql = "update " + this.getTableName() + " set role_ids = ? where role_id = ?";
		try{
			pstmt = conn.prepareStatement(sql);		
			pstmt.setString(1, role_ids);
			pstmt.setLong(2, role_id);
			flag = pstmt.executeUpdate();
		}finally{
			DBConnection.closeAll(pstmt, conn);
		}
		return flag;
	}
}
