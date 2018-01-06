package com.weixin.classes.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.weixin.classes.view.ClassesView;
import com.yyy.utils.BaseDao;
import com.yyy.utils.DBConnection;
/**
 * 班级Dao层
 */
public class ClassesDao extends BaseDao {
	/**
	 * 获取班级表名
	 */
	public String getTableName() {
		return "CLASSES_INFO";
	}
	/**
	 * 返回结果集
	 */
	public Object rs2Object(ResultSet rs) throws SQLException {
		ClassesView cla = new ClassesView();

		cla.setCla_id(rs.getLong("cla_id"));
		cla.setCou_id(rs.getLong("cou_id"));
		cla.setTea_id(rs.getLong("tea_id"));
		cla.setCla_name(rs.getString("cla_name"));
		cla.setCla_nature(rs.getString("cla_nature"));
		cla.setCla_num(rs.getInt("cla_num"));
		cla.setCla_starttime(rs.getDate("cla_starttime").toString());
		cla.setCla_note(rs.getString("cla_note"));
		cla.setCla_adder(rs.getString("cla_adder"));
		cla.setCla_delete(rs.getInt("cla_delete"));
		return cla;
	}
	/**
	 * 返回结果集
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public Object rs2ObjectView(ResultSet rs) throws SQLException {
		ClassesView cla = new ClassesView();

		cla.setCla_id(rs.getLong("cla_id"));
		cla.setCou_id(rs.getLong("cou_id"));
		cla.setTea_id(rs.getLong("tea_id"));
		cla.setCla_name(rs.getString("cla_name"));
		cla.setCla_nature(rs.getString("cla_nature"));
		cla.setCla_num(rs.getInt("cla_num"));
		cla.setCla_starttime(rs.getDate("cla_starttime").toString());
		cla.setCla_note(rs.getString("cla_note"));
		cla.setCla_adder(rs.getString("cla_adder"));
		cla.setCla_delete(rs.getInt("cla_delete"));
		
		cla.setCou_name(rs.getString("cou_name"));
		cla.setTea_name(rs.getString("tea_name"));
		return cla;
	}
	
	
	
	
	/**
	 * 添加班级
	 * @param cla
	 * @return
	 * @throws SQLException 
	 */
	public long addClasses(ClassesView cla) throws SQLException{
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "insert into "
				+ this.getTableName()
				+ " (cou_id,tea_id,cla_name,"
				+ "cla_nature,"
				+ "cla_num,cla_starttime,"
				+ "cla_note,cla_adder,cla_addtime,"
				+ "cla_delete) "
				+ "values(?,?,?,?,?,?,?,?,now(),?) ";
		long id=1;
		int i = 1;
		try {
			pstmt = conn.prepareStatement(sql);
			
			//id = this.getSequence();
			//pstmt.setLong(i++, id);
			pstmt.setLong(i++, cla.getCou_id());
			pstmt.setLong(i++, cla.getTea_id());
			pstmt.setString(i++, cla.getCla_name());
			pstmt.setString(i++, cla.getCla_nature());
			pstmt.setInt(i++, cla.getCla_num());
			pstmt.setDate(i++, Date.valueOf(cla.getCla_starttime()));
			pstmt.setString(i++, cla.getCla_note());
			pstmt.setString(i++, cla.getCla_adder());
			
			pstmt.setInt(i++, cla.getCla_delete());

			pstmt.executeUpdate();
		} finally {
			DBConnection.closeAll(pstmt, conn);
		}
		return id;
	}
	
	/**
	 * 删除班级信息
	 * @param cla_id
	 * @return
	 * @throws SQLException 
	 */
	public long deleteClasses(Long cla_id) throws SQLException{
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		
		String  sql = "delete from "+this.getTableName()+" where cla_id = ?";
		
		int flag = 0;
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, cla_id);
			flag = pstmt.executeUpdate();
		}
		finally{
			DBConnection.closeAll( pstmt, conn);
		}
		return flag;
	}
	
	/**
	 * 修改班级信息
	 * @param cla_id
	 * @param cla
	 * @return
	 */
	public int updateClasses(ClassesView cla){
		//连接数据库
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		
		int num = 0;
		
		StringBuffer sb = new StringBuffer();
		//sql语句
		String  sql = sb.append("update classes_info set cou_id=?,tea_id=?,cla_name= ? , ")
						.append("cla_nature = ?,cla_num = ?,cla_starttime = ?,")
						.append("cla_note = ?,cla_adder = ?,cla_addtime = now(),cla_delete=? ")
						.append("where cla_id = ? ")
						.toString();
		//对 sql  进行预处理  插入
		int i=1;
		try {
			pstmt = conn.prepareStatement(sql);
			//替换？中的值
			pstmt.setLong(i++, cla.getCou_id());
			pstmt.setLong(i++, cla.getTea_id());
			pstmt.setString(i++, cla.getCla_name());
			pstmt.setString(i++, cla.getCla_nature());
			pstmt.setInt(i++, cla.getCla_num());
			pstmt.setDate(i++, Date.valueOf(cla.getCla_starttime()));
			pstmt.setString(i++, cla.getCla_note());
			pstmt.setString(i++, cla.getCla_adder());
			pstmt.setInt(i++, cla.getCla_delete());
			pstmt.setLong(i++, cla.getCla_id());
			
			num = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBConnection.closeAll(pstmt, conn);
		}
		return num;
	}
	
	/**
	 * 根据ID查询班级
	 * @param cla_id
	 * @return
	 * @throws SQLException
	 */
	public ClassesView getById(Long cla_id) throws SQLException{

		Connection conn  = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ClassesView cla =null;

		StringBuffer sql = new StringBuffer();
		
		sql.append("select cla_id,cou_id,tea_id,cla_name,")
		   .append("cla_nature,cla_num,cla_starttime,")
		   .append("cla_note,cla_adder,cla_addtime,")
		   .append("cla_delete from ")
		   .append( this.getTableName())
		   .append(" where cla_id=? ");
		try {
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setLong(1, cla_id);
			rs =pstmt.executeQuery();
			if(rs.next()){
				cla=(ClassesView)this.rs2Object(rs);
			}
		} finally{
			DBConnection.closeAll(rs, pstmt, conn);
		}
		return cla;
	}
	
	/**
	 * 分页查询
	 * @param cla
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List query(ClassesView cla,String cou_name,String tea_name,
			String cla_starttime1,String cla_starttime2, int page) throws SQLException{
		List list = new ArrayList();
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select c.cla_id,c.cou_id,c.tea_id,c.cla_name,")
		   .append("c.cla_num,c.cla_nature,c.cla_starttime,")
		   .append("c.cla_note,c.cla_adder,c.cla_addtime,")
		   .append("c.cla_delete,")
		   .append("cou.cou_name,")
		   .append("tea.tea_name ")
		   .append("from " + this.getTableName() + " c")
		   .append(",course_info cou,teacher_info tea")
		   .append(" where 1=1 and c.cou_id=cou.cou_id and c.tea_id=tea.tea_id");
		
		if (cla != null) {
			
			//班级性质
			if (StringUtils.isNotBlank(cla.getCla_nature())) {
				sql.append(" AND c.cla_nature LIKE '%"
						+ cla.getCla_nature() + "%' ");
			}
			//班级名称
			if (StringUtils.isNotBlank(cla.getCla_name())) {
				sql.append(" AND c.cla_name LIKE '%" + cla.getCla_name() + "%' ");
			}
		}
		//开班时间
		if (cla_starttime1 != null) {
			if (StringUtils.isNotBlank(cla_starttime1)) {
				sql.append(" AND to_date('" + cla_starttime1 + "','yyyy-MM-dd') <= c.cla_starttime ");
			}
		}
		if (cla_starttime2 != null) {
			if (StringUtils.isNotBlank(cla_starttime1)) {
				sql.append(" AND to_date('" + cla_starttime2 + "','yyyy-MM-dd') >= c.cla_starttime ");
			}
		}
		//课程名称
		if(cou_name != null){
			if (StringUtils.isNotBlank(cou_name)) {
				sql.append(" AND cou.cou_name LIKE '%" + cou_name + "%' ");
			}
		}
		//授课教师
		if(tea_name != null){
			if (StringUtils.isNotBlank(tea_name)) {
				sql.append(" AND tea.tea_name LIKE '%" + tea_name + "%' ");
			}
		}
		
		
		sql.append(" order by c.cla_id desc");
		String s = getMysqlCurPageSql(sql.toString(), page);
		try {
			pstmt = connection.prepareStatement(s);
			rs = pstmt.executeQuery();
			while(rs.next()){
				list.add(this.rs2ObjectView(rs));
			}
		} finally{
			DBConnection.closeAll(rs, pstmt, connection);
		}
		
		return list;
	}
	
	/**
	 * 得到数据总数
	 * @param cla
	 * @return
	 * @throws SQLException
	 */
	public int getCount(ClassesView cla,String cou_name,String tea_name,
			String cla_starttime1,String cla_starttime2) throws SQLException{
		Connection conn =DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count=0;
		StringBuffer sql = new StringBuffer();
		
		sql.append("select count(*) ")
		   .append("from " + this.getTableName() + " c")
		   .append(",course_info cou,teacher_info tea")
		   .append(" where 1=1 and c.cou_id=cou.cou_id and c.tea_id=tea.tea_id");
		
		if (cla != null) {
			
			//班级性质
			if (StringUtils.isNotBlank(cla.getCla_nature())) {
				sql.append(" AND c.cla_nature LIKE '%"
						+ cla.getCla_nature() + "%' ");
			}
			
			//班级名称
			if (StringUtils.isNotBlank(cla.getCla_name())) {
				sql.append(" AND c.cla_name LIKE '%" + cla.getCla_name() + "%' ");
			}
		}
		//开班时间
		if (cla_starttime1 != null) {
			if (StringUtils.isNotBlank(cla_starttime1)) {
				sql.append(" AND to_date('" + cla_starttime1 + "','yyyy-MM-dd') <= c.cla_starttime ");
			}
		}
		if (cla_starttime2 != null) {
			if (StringUtils.isNotBlank(cla_starttime1)) {
				sql.append(" AND to_date('" + cla_starttime2 + "','yyyy-MM-dd') <= c.cla_starttime ");
			}
		}
		//课程名称
		if(cou_name != null){
			if (StringUtils.isNotBlank(cou_name)) {
				sql.append(" AND cou.cou_name LIKE '%" + cou_name + "%' ");
			}
		}
		//授课教师
		if(tea_name != null){
			if (StringUtils.isNotBlank(tea_name)) {
				sql.append(" AND tea.tea_name LIKE '%" + tea_name + "%' ");
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
	 * @注释： 这个是 其他模块 使用——直接查询某一列 的数据 （其他模块的关于课程的下拉列表） 
	 * @param type
	 * @return selectList   注意 如果没有查询到 返回的是 一个 空的集合  集合
	 * @throws SQLException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getName() throws SQLException{
		Connection conn =DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List selectList = new ArrayList();

		String sql= "select cla_id, cla_name from "+this.getTableName();
		try {
			pstmt= conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				ClassesView cla = new ClassesView();
				cla.setCla_id(rs.getLong("cla_id"));
				cla.setCla_name(rs.getString("cla_name"));
				selectList.add(cla);
			}
		} finally{
			DBConnection.closeAll(rs, pstmt, conn);
		}
		return selectList;
	}
}
