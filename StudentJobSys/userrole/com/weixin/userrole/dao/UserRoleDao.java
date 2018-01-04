package com.weixin.userrole.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.weixin.userrole.form.UserRole;
import com.weixin.userrole.view.UserRoleView;
import com.weixin.utils.BaseDao;
import com.weixin.utils.DBConnection;

/**
 *  用户角色的中间联系的数据控制层
 */
public class UserRoleDao extends BaseDao {

	@Override
	public String getTableName() {
		return "relation_info";
	}

	@Override
	public Object rs2Object(ResultSet rs) throws SQLException {
		UserRoleView userrole = new UserRoleView();
		userrole.setRelation_id(rs.getLong("relation_id"));
		userrole.setUser_id(rs.getLong("user_id"));
		userrole.setRole_id(rs.getLong("role_id"));
		userrole.setUser_role_id(rs.getLong("user_role_id"));
		userrole.setRelation_type(rs.getString("relation_type"));
		return userrole;
	}
	/**
	 * 封装userrole的视图层
	 * @param rs
	 * @return 封装userrole的视图层
	 * @throws SQLException
	 */
	public Object rs2Object2(ResultSet rs) throws SQLException {
		UserRoleView userrole = new UserRoleView();
		userrole.setRelation_id(rs.getLong("relation_id"));
		userrole.setUser_id(rs.getLong("user_id"));
		userrole.setRole_id(rs.getLong("role_id"));
		userrole.setUser_role_id(rs.getLong("user_role_id"));
		userrole.setRelation_type(rs.getString("relation_type"));
		
		userrole.setRole_name(rs.getString("role_name"));
		userrole.setAuth_ids(rs.getString("auth_ids"));
		return userrole;
	}

		/**
		 * 添加
		 * @param userrole
		 * @return  添加
		 * @throws SQLException
		 */
		public long insert(UserRole userrole ) throws SQLException{
			Connection connection=DBConnection.getConnection();
			PreparedStatement pstmt = null;
			String sql = "insert into "+this.getTableName()+" (user_role_id,user_id,role_id,relation_id,relation_type) values(?,?,?,?,?)";
			long id;
			try {
				pstmt = connection.prepareStatement(sql);
				int i=1;
				id = this.getSequence();
				pstmt.setLong(i++, id);
				pstmt.setLong(i++, userrole.getUser_id());
				pstmt.setLong(i++, userrole.getRole_id());
				pstmt.setLong(i++, id);
				pstmt.setString(i++, userrole.getRelation_type());
				
				pstmt.executeUpdate();
			} finally{
				DBConnection.closeAll(pstmt, connection);
			}
			return id;
			
		}
		
		/**
		 * 通过 user_role_id 删除用户
		 * @param user_role_id
		 * @return 通过 user_role_id 删除用户
		 * @throws SQLException
		 */
		public int delete(Long user_role_id) throws SQLException{
			Connection connection=DBConnection.getConnection();
			PreparedStatement pstmt = null;
			int num=0;
			String sql = "delete from "+this.getTableName()+" where user_id = ?";
			try {
				pstmt= connection.prepareStatement(sql);
				int i =1;
				pstmt.setLong(i++, user_role_id);
				num=pstmt.executeUpdate();
			}finally{
				DBConnection.closeAll(pstmt, connection);
			}
			return num;
			
		}
		
		
		/**
		 * 更新数据库
		 * @param user
		 * @return 更新数据库
		 * @throws SQLException
		 */
		public int update(UserRole userrole ) throws SQLException{
			Connection connection=DBConnection.getConnection();
			PreparedStatement pstmt = null;
			int num=0;
			String sql = "update "+this.getTableName()+" set user_id =?,role_id = ?,relation_id = ?,relation_type=? where user_role_id = ?";
			try {
				pstmt= connection.prepareStatement(sql);
				int i =1;
				pstmt.setLong(i++, userrole.getUser_id());
				pstmt.setLong(i++, userrole.getRole_id());
				pstmt.setLong(i++, userrole.getRelation_id());
				pstmt.setString(i++, userrole.getRelation_type());
				pstmt.setLong(i++, userrole.getUser_role_id());
				num=pstmt.executeUpdate();
			}finally{
				DBConnection.closeAll(pstmt, connection);
			}
			return num;
		}
		
		/**
		 * 通过 user_role_id 得到具体的数据
		 * @param user_role_id
		 * @return 通过 user_role_id 得到具体的数据
		 * @throws SQLException
		 */
		public UserRole getById(Long user_role_id) throws SQLException{
			UserRole userrole = null;
			Connection connection=DBConnection.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql = "select user_role_id,user_id,role_id,relation_id,relation_type from "+this.getTableName()+" where user_role_id=? ";
			try {
				pstmt = connection.prepareStatement(sql);
				
				pstmt.setLong(1, user_role_id);
				rs =pstmt.executeQuery();
				if(rs.next()){
					userrole=(UserRole)this.rs2Object(rs);
				}
			} finally{
				DBConnection.closeAll(rs, pstmt, connection);
			}
			return userrole;	
		}
		/**
		 * 查询    分页显示 用户信息
		 * @param user
		 * @param page
		 * @return 分页显示 用户信息
		 * @throws SQLException
		 */
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public List query(UserRole userrole,int page) throws SQLException{
			List list = new ArrayList();
			Connection connection=DBConnection.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT t.user_role_id,t.user_id,t.role_id,t.relation_id,t.relation_type FROM "
					+ this.getTableName() + " t WHERE 1=1") ;
			if (userrole != null) {
				if (userrole.getUser_id()!=0) {
					sql.append(" AND t.user_id LIKE "
							+ userrole.getUser_id() + " ");
				}
				if (userrole.getRole_id()!=0){
					sql.append(" and t.role_id like "+userrole.getRole_id()+"  ");
				}
			}
			sql.append(" order by t.user_id");
			String s = getMysqlCurPageSql(sql.toString(), page);
			try {
				pstmt = connection.prepareStatement(s);
				rs = pstmt.executeQuery();
				while(rs.next()){
					list.add(this.rs2Object(rs));
				}
			} finally{
				DBConnection.closeAll(rs, pstmt, connection);;
			}
			
			return list;
		}
		
		/**
		 *  统计查询到的个数
		 * @param user
		 * @return 统计查询到的个数
		 * @throws SQLException
		 */
		public int getCount(UserRole userrole) throws SQLException{
			Connection conn =DBConnection.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			int count=0;
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT count(*) FROM "+ this.getTableName() +" t WHERE 1=1") ;
			if (userrole != null) {
				if (userrole.getUser_id()!=0) {
					sql.append(" AND t.user_id LIKE "
							+ userrole.getUser_id() + " ");
				}
				if (userrole.getRole_id()!=0){
					sql.append(" and t.role_id like "+userrole.getRole_id()+"  ");
				}
			}
			
			try {
				pstmt = conn.prepareStatement(sql.toString());
				rs= pstmt.executeQuery();
				if(rs.next()){
					count = rs.getInt(1);
				}
			} finally{
				DBConnection.closeAll(rs, pstmt, conn);
			}
			
			return count;
		}
		
		
		

		/**
		 * 
		 * @param user_name
		 * @return User 
		 * @throws SQLException 
		 *//*
		public User getByName(String user_name) throws SQLException {
			User user = null;
			Connection connection=DBConnection.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql = "select user_id,user_name,user_password,user_desc,user_type,user_checkstatus,user_delete from "+this.getTableName()+" where user_name=? ";
			try {
				pstmt = connection.prepareStatement(sql);
				
				pstmt.setString(1, user_name);
				rs =pstmt.executeQuery();
				if(rs.next()){
					user=(User)this.rs2Object(rs);
				}
			} finally{
				DBConnection.closeAll(rs, pstmt, connection);
			}
			return user;	
		}
*/
		
		


}
