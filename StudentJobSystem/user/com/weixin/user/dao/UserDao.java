package com.weixin.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.weixin.role.dao.RoleDao;
import com.weixin.role.form.Role;
import com.weixin.user.form.User;
import com.yyy.utils.BaseDao;
import com.yyy.utils.DBConnection;

/**
 *  关于用户的一系列 对于数据库的操作
 */
public class UserDao extends BaseDao {

	@Override
	public String getTableName() {
		return "user_info";
	}

	@Override
	public Object rs2Object(ResultSet rs) throws SQLException {
		User user = new User();
		user.setUser_id(rs.getLong("user_id"));
		user.setUser_name(rs.getString("user_name"));
		user.setUser_password(rs.getString("user_password"));
		user.setUser_desc(rs.getString("user_desc"));
		user.setUser_type(rs.getLong("user_type"));
		user.setUser_checkstatus(rs.getInt("user_checkstatus"));
		user.setUser_delete(rs.getInt("user_delete"));
		return user;
	}

	/**
	 * 添加用户
	 * @param user
	 * @return  添加用户
	 * @throws SQLException
	 */
	public long insert(User user ) throws SQLException{
		Connection connection=DBConnection.getConnection();
		PreparedStatement pstmt = null;
		String sql = "insert into "+this.getTableName()+" (user_name,user_password,user_desc,user_type,user_checkstatus,user_delete) values(?,?,?,?,?,0)";
		long id=1;
		try {
			pstmt = connection.prepareStatement(sql);
			int i=1;
			//id = this.getSequence();
			//pstmt.setLong(i++, id);
			pstmt.setString(i++, user.getUser_name());
			pstmt.setString(i++, user.getUser_password());
			pstmt.setString(i++, user.getUser_desc());
			pstmt.setLong(i++, user.getUser_type());
			pstmt.setInt(i++, user.getUser_checkstatus());
			
			pstmt.executeUpdate();
		} finally{
			DBConnection.closeAll(pstmt, connection);
		}
		return id;
		
	}
	
	/**
	 * 通过 用户id 删除用户
	 * @param user_id
	 * @return 通过 用户id 删除用户
	 * @throws SQLException
	 */
	public int delete(Long user_id) throws SQLException{
		Connection connection=DBConnection.getConnection();
		PreparedStatement pstmt = null;
		int num=0;
		String sql = "update "+this.getTableName()+" set user_delete=?   where user_id = ?";
		try {
			pstmt= connection.prepareStatement(sql);
			int i =1;
			pstmt.setInt(i++, 1);
			pstmt.setLong(i++, user_id);
			num=pstmt.executeUpdate();
		}finally{
			DBConnection.closeAll(pstmt, connection);
		}
		return num;
		
	}
	
	/**
	 * 恢复账户   解冻
	 * @param user_id
	 * @return 恢复账户   解冻
	 * @throws SQLException
	 */
	public int dischange(Long user_id) throws SQLException{
		Connection connection=DBConnection.getConnection();
		PreparedStatement pstmt = null;
		int num=0;
		String sql = "update "+this.getTableName()+" set user_delete=?   where user_id = ?";
		try {
			pstmt= connection.prepareStatement(sql);
			int i =1;
			pstmt.setInt(i++, 0);
			pstmt.setLong(i++, user_id);
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
	public int update(User user ) throws SQLException{
		Connection connection=DBConnection.getConnection();
		PreparedStatement pstmt = null;
		int num=0;
		String sql = "update "+this.getTableName()+" set user_name =?,user_password = ?,user_desc = ?,user_type=?,user_checkstatus =?  where user_id = ?";
		try {
			pstmt= connection.prepareStatement(sql);
			int i =1;
			pstmt.setString(i++, user.getUser_name());
			pstmt.setString(i++, user.getUser_password());
			pstmt.setString(i++, user.getUser_desc());
			pstmt.setLong(i++, user.getUser_type());
			pstmt.setInt(i++, user.getUser_checkstatus());
			pstmt.setLong(i++, user.getUser_id());
			num=pstmt.executeUpdate();
		}finally{
			DBConnection.closeAll(pstmt, connection);
		}
		return num;
	}
	/**
	 * 查找用户 通过user_id
	 * @param user_id
	 * @return User
	 * @throws SQLException
	 */
	public User getById(Long user_id) throws SQLException{
		User user = null;
		Connection connection=DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select user_id,user_name,user_password,user_desc,user_type,user_checkstatus,user_delete from "+this.getTableName()+" where user_id=? ";
		try {
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setLong(1, user_id);
			rs =pstmt.executeQuery();
			if(rs.next()){
				user=(User)this.rs2Object(rs);
			}
		} finally{
			DBConnection.closeAll(rs, pstmt, connection);
		}
		return user;	
	}
	/**
	 * 分页显示 用户信息
	 * @param user
	 * @param page
	 * @return 分页显示 用户信息
	 * @throws SQLException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List query(User user,int page) throws SQLException{
		List list = new ArrayList();
		Connection connection=DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT t.user_id,t.user_name,t.user_password,t.user_desc,t.user_type,t.user_checkstatus,t.user_delete FROM "
				+ this.getTableName() + " t WHERE 1=1") ;
		if (user != null) {
			if (user.getUser_type()!=0) {
				sql.append(" AND t.user_type LIKE "
						+ user.getUser_type() + " ");
			}
			if (StringUtils.isNotBlank(user.getUser_name())){
				sql.append(" and t.user_name like '%"+user.getUser_name()+"%'");
			}
			if (user.getUser_checkstatus()!=0){
				sql.append(" and t.user_checkstatus like "+user.getUser_checkstatus()+"  ");
			}
		}
		sql.append(" order by t.user_type");
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
	 * 统计查询到的个数
	 * @param user
	 * @return 统计查询到的个数
	 * @throws SQLException
	 */
	public int getCount(User user) throws SQLException{
		Connection conn =DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count=0;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) FROM "+ this.getTableName() +" t WHERE 1=1") ;
		if (user != null) {
			if (user.getUser_type()!=0) {
				sql.append(" AND t.user_type LIKE '%"
						+ user.getUser_type() + "%' ");
			}
			if (StringUtils.isNotBlank(user.getUser_name())){
				sql.append(" and t.user_name like '% "+user.getUser_name()+"%'");
			}
			if (user.getUser_checkstatus()!=0){
				sql.append(" and t.user_checkstatus like '%"+user.getUser_checkstatus()+" %' ");
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
	
	
	/*
	 * 拓展功能
	 */
	/**
	 * 得到用户类型
	 * @return 这里的UserType  是为了 user_type   而 用户类型 应该是角色 id
	 * @throws SQLException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getUserType() throws SQLException{
		List list = new ArrayList();
		Connection connection=DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select role_id,role_name from role_info ";
		try {
			pstmt = connection.prepareStatement(sql);
			rs =pstmt.executeQuery();
			while(rs.next()){
				Role role = new Role();
				role.setRole_id(rs.getLong("role_id"));
				role.setRole_name(rs.getString("role_name"));
				list.add(role);
			}
		} finally{
			DBConnection.closeAll(rs, pstmt, connection);
		}
		return list;
	}

	/**
	 * 通过用户名得到用户
	 * @param user_name
	 * @return User 
	 * @throws SQLException 
	 */
	public User getByName(String user_name) throws SQLException {
		User user = null;
		Connection connection=DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;//结果集
		
		String sql = "select user_id,user_name,user_password,user_desc,user_type,user_checkstatus,user_delete from "+this.getTableName()+" where user_name=? ";
		try {
			pstmt = connection.prepareStatement(sql);//预编译
			
			pstmt.setString(1, user_name);
			rs =pstmt.executeQuery();//执行查询
			if(rs.next()){//遍历结果集
				user=(User)this.rs2Object(rs);
			}
		} finally{
			DBConnection.closeAll(rs, pstmt, connection);
		}
		return user;	
	}
	/*
	 * 为了使用roleDao 而引入
	 */
	private static final RoleDao roledao = new RoleDao();
	/**
	 * 通过User_id得到Role
	 * @param user_id
	 * @return 通过User_id得到Role
	 * @throws SQLException
	 */
	public List<Role> getUserRole(Long user_id) throws SQLException{
		Role role =null;
		List<Role> rolelist=new ArrayList<Role>();
		Connection connection=DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Long role_id =null;
		String sql = "select role_id from relation_info where user_id=? ";
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, user_id);
			rs =pstmt.executeQuery();
			if(rs.next()){
				role_id =rs.getLong("role_id");
				role=roledao.getById(role_id);
				rolelist.add(role);
			}
		} finally{
			DBConnection.closeAll(rs, pstmt, connection);
		}

		return rolelist;
	}
	/**
	 * 检查user_name是否存在
	 * @param user_name
	 * @return 检查user_name是否存在
	 */
	public boolean checkUserIsExist(String user_name) {
		Connection connection=DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql= "select count(*) from user_info where user_name = ?";
		try {
			pstmt =connection.prepareStatement(sql);
			pstmt.setString(1, user_name);
			rs = pstmt.executeQuery();
			if(rs.next()){
				if(rs.getInt(1)!=0){
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
}
