package com.weixin.data.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;

import com.weixin.data.form.Data;
import com.yyy.utils.BaseDao;
import com.yyy.utils.DBConnection;

/**
 *  DataDao  数据持久化层
 */
public class DataDao extends BaseDao {
	
	@Override
	public String getTableName() {
		
		return "DATA_INFO";
	}

	@Override
	public Object rs2Object(ResultSet rs) throws SQLException {
		Data data = new Data();
		data.setData_key(rs.getLong("data_key"));
		data.setData_name(rs.getString("data_name"));
		data.setData_num(rs.getString("data_num"));
		data.setData_type(rs.getString("data_type"));
		data.setData_delete(rs.getInt("data_delete"));
		
		return data;
	}
	
	/**
	 * 封装 视图查询 后的对象
	 * @param rs
	 * @return 封装 视图查询 后的对象
	 * @throws SQLException
	 */
	public Object rs2Select(ResultSet rs) throws SQLException {
		Data data = new Data();
		data.setData_name(rs.getString("data_name"));
		data.setData_num(rs.getString("data_num"));
		return data;
	}
	/**
	 *  添加   返回 添加的id
	 * @param data
	 * @return id 添加后添加数据的id
	 * @throws SQLException
	 */
	public long insert(Data  data ) throws SQLException{
		Connection connection=DBConnection.getConnection();
		PreparedStatement pstmt = null;
		String sql = "insert into "+this.getTableName()+" (data_name,data_num,data_type,data_delete) values(?,?,?,?)";
		long id=1;
		try {
			pstmt = connection.prepareStatement(sql);
			int i=1;
			//id = this.getSequence();
			//pstmt.setLong(i++, id);
			pstmt.setString(i++, data.getData_name());
			pstmt.setString(i++, data.getData_num());
			pstmt.setString(i++, data.getData_type());
			pstmt.setInt(i++, data.getData_delete());
			
			pstmt.executeUpdate();
		} finally{
			DBConnection.closeAll(pstmt, connection);
		}
		return id;
	}
	
	/**
	 *    删除
	 * @param data_key
	 * @return int 影响的行数      删除
	 * @throws SQLException
	 */
	public int delete(Long data_key) throws SQLException{
		Connection connection=DBConnection.getConnection();
		PreparedStatement pstmt = null;
		String sql = "delete from "+this.getTableName()+" where data_key = ?";
		int flag = 0;
		try {
			pstmt=connection.prepareStatement(sql);
			pstmt.setLong(1, data_key);
			
			flag=pstmt.executeUpdate();
		} finally{
			DBConnection.closeAll(pstmt, connection);
		}
		return flag;
	}
	
	/**
	 * 更新
	 * @param data
	 * @return num 更新影响的行数   
	 * @throws SQLException
	 */
	public int update(Data data ) throws SQLException{
		Connection connection=DBConnection.getConnection();
		PreparedStatement pstmt = null;
		int num=0;
		String sql = "update "+this.getTableName()+" set data_name =?,data_num = ?,data_type = ? where data_key = ?";
		try {
			pstmt= connection.prepareStatement(sql);
			int i =1;
			pstmt.setString(i++,data.getData_name());
			pstmt.setString(i++, data.getData_num());
			pstmt.setString(i++, data.getData_type());
			pstmt.setLong(i++,data.getData_key());
			
			num=pstmt.executeUpdate();
		}finally{
			DBConnection.closeAll(pstmt, connection);
		}
		return num;
	}
	
	/**
	 * 通过 id  查询
	 * @param id
	 * @return Data 查询到的对象
	 * @throws SQLException
	 */
	public Data getById(Long id) throws SQLException{
		Data data = null;
		Connection connection=DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select data_key,data_name,data_num,data_type,data_delete from "+this.getTableName()+" where data_key=? and data_delete = 0";
		try {
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setLong(1, id);
			rs =pstmt.executeQuery();
			if(rs.next()){
				data=(Data)this.rs2Object(rs);
			}
		} finally{
			DBConnection.closeAll(rs, pstmt, connection);
		}
		return data;	
	}
	/**
	 * 查询
	 * @param data
	 * @param page
	 * @return Data 对象集合
	 * @throws SQLException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List query(Data data,int page) throws SQLException{
		List list = new ArrayList();
		Connection connection=DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT t.data_key,t.data_name,t.data_num,t.data_type,data_delete FROM "
				+ this.getTableName() + " t WHERE 1=1") ;
		if (data != null) {
			if (StringUtils.isNotBlank(data.getData_type())) {
				sql.append(" AND t.data_type LIKE '%"
						+ data.getData_type() + "%' ");
			}
		}
		sql.append(" order by t.data_type");
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
	 * 统计查询到的数据条数
	 * @param data
	 * @return 有多少数据
	 * @throws SQLException
	 */
	public int getCount(Data data) throws SQLException{
		Connection conn =DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count=0;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) FROM "+ this.getTableName() +" t WHERE 1=1") ;
		if (data != null) {
			if (StringUtils.isNotBlank(data.getData_type())) {
				sql.append(" AND t.data_type LIKE '%"+ data.getData_type() + "%' ");
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
	 * 根据 dataType 得到的对象的集合
	 * @param dataType
	 * @return 根据 dataType 得到的对象的集合
	 * @throws SQLException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getByType(String dataType) throws SQLException{
		List list = new ArrayList();
		Connection connection=DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		String sql = "select data_key,data_name,data_num,data_type,data_delete from "
		+this.getTableName()
		+" where data_type=? and data_delete = 0 order by data_key desc";
		try {
			pstmt = connection.prepareStatement(sql);
			//System.out.println(dataType);
			pstmt.setString(1, dataType);
			rs =pstmt.executeQuery();
			while(rs.next()){
				list.add(this.rs2Object(rs));
			}
		} finally{
			DBConnection.closeAll(rs, pstmt, connection);
		}
		return list;
	}

	/**
	 * 通过  字典类型 查找   并 返回
	 * @param dataType
	 * @return  通过  字典类型 查找   并 返回
	 * @throws SQLException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List findByType(String dataType) throws SQLException{
		List list = new ArrayList();
		Connection connection=DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		String sql = "select data_name,data_num form "
		+this.getTableName()
		+" where data_type=? and data_delete = 0 order by data_key desc";
		try {
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, dataType);
			rs =pstmt.executeQuery();
			while(rs.next()){
				list.add(this.rs2Select(rs));
			}
		} finally{
			DBConnection.closeAll(rs, pstmt, connection);
		}
		return list;
	}

}
