package com.weixin.auth.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.weixin.auth.form.Auth;
import com.weixin.utils.BaseDao;
import com.weixin.utils.DBConnection;

/**
 * @注释 ：
 */
public class AuthDao extends BaseDao{

	@Override
	/**
	 * 获取表名
	 */
	public String getTableName() {
		return "auth_info";
	}
	/**
	 * 返回数据库结果集
	 */

	@Override
	public Object rs2Object(ResultSet rs) throws SQLException {
		Auth obj = new Auth();
		obj.setAuth_description(rs.getString("auth_description"));
		obj.setAuth_id(rs.getLong("auth_id"));
		obj.setAuth_name(rs.getString("auth_name"));
		obj.setAuth_parentid(rs.getString("auth_parentid"));
		obj.setAuth_path(rs.getString("auth_path"));
		obj.setAuth_state(rs.getInt("auth_state"));
		return obj;
	}
	/**
	 * 插入一条记录
	 * @param obj
	 * @return
	 * @throws SQLException
	 */
	public long insert(Auth obj) throws SQLException{
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		String sql = "insert into "+this.getTableName()+" (auth_name,auth_path,auth_parentid,auth_description,auth_state ) values (?,?,?,?,?)";
		int flag = 0;
		long id=1;
		int i = 1;
		try {
			pstmt = conn.prepareStatement(sql);
			//id = this.getSequence();
			//pstmt.setLong(i++, id);
			pstmt.setString(i++, obj.getAuth_name());
			pstmt.setString(i++, obj.getAuth_path());
			pstmt.setString(i++, obj.getAuth_parentid());
			pstmt.setString(i++, obj.getAuth_description());
			pstmt.setInt(i++, obj.getAuth_state());
			flag = pstmt.executeUpdate();
		} finally{
			DBConnection.closeAll(pstmt, conn);
		}
		return flag;
	}
	/**
	 * 删除一条记录
	 * @param id
	 * @return
	 * @throws SQLException
	 */

public int delete(long id) throws SQLException{
	Connection conn = DBConnection.getConnection();
	PreparedStatement pstmt = null;
	String sql = "delete from "+this.getTableName()+
			" where auth_id = ?";
	int flag = 0;
	try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setLong(1, id);
		flag = pstmt.executeUpdate();
	} finally{
		DBConnection.closeAll(pstmt, conn);
	}
	return flag;
    }
/**
 * 更新一条记录
 * @param obj
 * @return
 * @throws SQLException
 */

public int update(Auth obj) throws SQLException{
	int flag = 0;
	Connection conn = DBConnection.getConnection();
	PreparedStatement pstmt = null;
	String sql = "update "+this.getTableName()+" set auth_name=?,auth_path=?,auth_parentid=?,auth_description=?,auth_state=? where auth_id=?"; 
	try{int i = 1;
	pstmt = conn.prepareStatement(sql);
	pstmt.setString(i++, obj.getAuth_name());
	pstmt.setString(i++, obj.getAuth_path());
	pstmt.setString(i++, obj.getAuth_parentid());
	pstmt.setString(i++, obj.getAuth_description());
	pstmt.setInt(i++, obj.getAuth_state());
	pstmt.setLong(i++, obj.getAuth_id());
	flag = pstmt.executeUpdate();
	}finally{
		DBConnection.closeAll(pstmt, conn);
	}
	return flag;
    }
/**
 *  根据ID查询一条权限记录
 * @param auth_id
 * @return
 * @throws SQLException
 */
public Auth getAuthById(Long auth_id) throws SQLException{
	Auth obj = new Auth();
	Connection conn = DBConnection.getConnection();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "select auth_id,auth_name,auth_path,auth_parentid,auth_description,auth_state from "+this.getTableName()+" where auth_id=?";
	try{
		pstmt = conn.prepareStatement(sql);
		pstmt.setLong(1, auth_id);
		rs = pstmt.executeQuery();
		if(rs.next()){
			obj = (Auth)this.rs2Object(rs);
		}
	}finally{
		DBConnection.closeAll(rs, pstmt, conn);
	}
	return obj;
    }
/**
 * 返回集合父
 * @return
 * @throws SQLException
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public List getAuthParent() throws SQLException{
	List list = new ArrayList();
	Connection conn = DBConnection.getConnection();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "select auth_id,auth_name,auth_path,auth_parentid,auth_description,auth_state,auth_delete from " + this.getTableName()+" where auth_parentid is null";
	try{
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while(rs.next()){
//			Auth obj = new Auth();
//			obj = (Auth)this.rs2Object(rs);
//			list.add(obj);
			list.add(this.rs2Object(rs));
		}
		
	}finally{
		DBConnection.closeAll(rs, pstmt, conn);
	}
	return list;
}
/**
 * 返回集合子
 * @return
 * @throws SQLException
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public List getAuthChild() throws SQLException{
	List list = new ArrayList();
	Connection conn = DBConnection.getConnection();
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "select auth_id,auth_name,auth_path,auth_parentid,auth_description,auth_state,auth_delete from " + this.getTableName()+" where auth_parentid is not null";
	try{
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while(rs.next()){
			list.add(this.rs2Object(rs));
		}
		
	}finally{
		DBConnection.closeAll(rs, pstmt, conn);
	}
	return list;
}
public static void main(String[] args) throws SQLException{
//	Auth auth = new AuthDao().getAuthById(38);
	
//	System.out.println(auth.getAuth_name());
}
}


