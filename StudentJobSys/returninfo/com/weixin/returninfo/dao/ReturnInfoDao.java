package com.weixin.returninfo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.weixin.classes.dao.ClassesDao;
import com.weixin.returninfo.view.ReturnInfoView;
import com.weixin.student.dao.StudentDao;
import com.weixin.student.form.Student;
import com.weixin.student.view.StudentView;
import com.weixin.utils.BaseDao;
import com.weixin.utils.DBConnection;
import com.winxin.jobinfo.view.JobInfoView;

public class ReturnInfoDao extends BaseDao {

	@Override
	public String getTableName() {
		
		return "returninfo_info";
	}

	@Override
	public Object rs2Object(ResultSet rs) throws SQLException {
		
		ReturnInfoView  riv = new ReturnInfoView();
		riv.setReturn_id(rs.getLong("return_id"));
		riv.setStu_id(rs.getLong("stu_id"));
		riv.setReturn_city(rs.getString("return_city"));
		riv.setReturn_company(rs.getString("return_company"));
		riv.setReturn_post(rs.getString("return_post"));
		riv.setReturn_salary(rs.getString("return_salary"));
		riv.setReturn_satisfy(rs.getString("return_satisfy"));
		riv.setReturn_phone(rs.getLong("return_phone"));
		riv.setReturn_content(rs.getString("return_content"));
		riv.setReturn_note(rs.getString("return_note"));
		riv.setReturn_time(rs.getDate("return_time").toString());
		riv.setReturn_adder(rs.getString("return_adder"));
		riv.setReturn_delete(rs.getLong("return_delete"));
		
		return riv;
	}
	/**
	 * 视图的结果集
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
public Object rs2Objectview(ResultSet rs) throws SQLException {
		
		ReturnInfoView  riv = new ReturnInfoView();
		riv.setReturn_id(rs.getLong("return_id"));
		riv.setStu_id(rs.getLong("stu_id"));
		riv.setStu_name(rs.getString("stu_name"));
		riv.setCla_name(rs.getString("cla_name"));
		riv.setReturn_city(rs.getString("return_city"));
		riv.setReturn_company(rs.getString("return_company"));
		riv.setReturn_post(rs.getString("return_post"));
		riv.setReturn_salary(rs.getString("return_salary"));
		riv.setReturn_satisfy(rs.getString("return_satisfy"));
		riv.setReturn_phone(rs.getLong("return_phone"));
		//riv.setReturn_content(rs.getString("return_content"));
		riv.setReturn_note(rs.getString("return_note"));
		riv.setReturn_time(rs.getDate("return_time").toString());
		riv.setReturn_adder(rs.getString("return_adder"));
		riv.setReturn_delete(rs.getLong("return_delete"));
		
		return riv;
	}


/**
 * 插入回访信息
 * @param riv
 * @return
 * @throws SQLException
 */
public  long insert(ReturnInfoView  riv) throws SQLException{
	Connection conn =DBConnection.getConnection();
	PreparedStatement  pstmt = null;
	String sql = "INSERT INTO "
			+ this.getTableName()
			//+ " (return_id,"
			+ " (stu_id,"
			+ "return_city,"
			+ "return_company,"
			+ "return_post,"
			+ "return_salary,"
			+ "return_satisfy,"
			+ "return_phone,"
			+ "return_content,"
			+ "return_note,"
			+ "return_time,"
			+ "return_adder)"
			
			+ " VALUES(?,?,?,?,?,?,?,?,?,?,now(),?)";
	long id=1;
	int i = 1;
	try{
			pstmt=conn.prepareStatement(sql);
			//id = this.getSequence();
			
			//pstmt.setLong(i++, id);
			pstmt.setLong(i++, riv.getStu_id());
			pstmt.setString(i++,riv.getReturn_city());
			pstmt.setString(i++, riv.getReturn_company());
			pstmt.setString(i++, riv.getReturn_post());
			pstmt.setString(i++, riv.getReturn_salary());
			pstmt.setString(i++, riv.getReturn_satisfy());
			pstmt.setLong(i++, riv.getReturn_phone());
			pstmt.setString(i++, riv.getReturn_content());
			pstmt.setString(i++, riv.getReturn_note());
			pstmt.setString(i++, riv.getReturn_adder());
			pstmt.executeUpdate();
	}finally{
		DBConnection.closeAll(pstmt, conn);
	}
	return id;
}
/***
* 删除回访信息
* @param id
* @return
* @throws SQLException
*/

public int delete(Long id) throws SQLException {
Connection conn = DBConnection.getConnection();
PreparedStatement ptmt = null;
String sql = "DELETE FROM " + getTableName()
		+ " WHERE return_id = ?";
int flag = 0;
try {
	ptmt = conn.prepareStatement(sql);
	ptmt.setLong(1, id);
	flag = ptmt.executeUpdate();
} finally {
	DBConnection.closeAll(ptmt, conn);
}
return flag;
}


/**
 * 根据return_id查询信息
 * @return
 * @throws SQLException 
 */
	public   ReturnInfoView  getById(long id) throws SQLException{
		ReturnInfoView  riv =null;
		Connection conn =DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT  r.return_id,r.stu_id,r.return_city,r.return_company,"
								+"r.return_post,r.return_salary,r.return_satisfy,"
								+"r.return_phone,r.return_content,r.return_note,"
								+"r.return_time,r.return_adder,r.return_delete,cl.cla_name,s.stu_name "
								+ "FROM "
								+ this.getTableName() + "  r,student_info s ,classes_info cl "
								+"WHERE 1=1 and s.stu_id=r.stu_id and s.cla_id=cl.cla_id and r.return_id=?";
				
				
	
	try {
		    pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				  riv = (ReturnInfoView)this.rs2Objectview(rs);
			}
		} finally{
			DBConnection.closeAll(rs, pstmt, conn);
		}
		
		return riv;
}
	/**
	 * 根据stu_id查询信息，用于统计
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	public   List  getByStu_id(long id) throws SQLException{
		
		List list = new ArrayList();
		Connection conn =DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT  r.return_id,r.stu_id,r.return_city,r.return_company,"
								+"r.return_post,r.return_salary,r.return_satisfy,"
								+"r.return_phone,r.return_content,r.return_note,"
								+"r.return_time,r.return_adder,r.return_delete,cl.cla_name,s.stu_name "
								+ "FROM "
								+ this.getTableName() + "  r,student_info s ,classes_info cl "
								+"WHERE 1=1 and s.stu_id=r.stu_id and s.cla_id=cl.cla_id and r.stu_id=?";
				
				
	
	try {
		    pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				  ReturnInfoView  riv =null;
				  riv = (ReturnInfoView)this.rs2Objectview(rs);
				  list.add(riv);
			}
		} finally{
			DBConnection.closeAll(rs, pstmt, conn);
		}
		
		return list;
}
	
	/**
	 * 查询
	 * @param riv
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public List query(ReturnInfoView  riv , int page) throws SQLException {
		List list = new ArrayList();
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT  r.return_id,r.stu_id,r.return_city,r.return_company,"
								+"r.return_post,r.return_salary,r.return_satisfy,"
								+"r.return_phone,r.return_note,"
								+"r.return_time,r.return_adder,r.return_delete,cl.cla_name,s.stu_name "
								+ "FROM "
								+ this.getTableName() + "  r,student_info s ,classes_info cl "
								+"WHERE 1=1 and s.stu_id=r.stu_id and s.cla_id=cl.cla_id");
		if (riv!= null) {
			if (StringUtils.isNotBlank(riv.getStu_name())) {
				sql.append(" AND s.Stu_name LIKE '%"
						+ riv.getStu_name()+ "%' ");
			 }
			if (StringUtils.isNotBlank(riv.getCla_name())) {
				sql.append(" AND cl.cla_name LIKE '%"
						+ riv.getCla_name()+ "%' ");
			 }
		}
		sql.append(" ORDER BY r.return_id DESC");
		String s = getMysqlCurPageSql(sql.toString(), page);
		try {
			pstmt = conn.prepareStatement(s);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(this.rs2Objectview(rs));
			}
		} finally {
			
			DBConnection.closeAll(rs, pstmt, conn);
		}
		return list;
	}
	//用于统计访问次数
	public List query2(ReturnInfoView  riv ,int page) throws SQLException {
		List list = new ArrayList();
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		
		sql.append("select count(return_id) as num1 from ("
								+"SELECT r.stu_id, "
								+"cl.cla_name,s.stu_name,r.return_id  "
								+ "FROM "
								+ this.getTableName() + "  r,student_info s ,classes_info cl "
								+"WHERE 1=1 and s.stu_id=r.stu_id and s.cla_id=cl.cla_id) u " );
		if (riv!= null) {
			if (StringUtils.isNotBlank(riv.getCla_name())) {
				sql.append(" AND cl.cla_name LIKE '%"
						+ riv.getCla_name()+ "%' ");
			 }
		}
		//sql.append(" group by r.stu_id,cl.cla_name,s.stu_name");
		
		String s = getOrclCurPageSql(sql.toString(), page);
		try {
			pstmt = conn.prepareStatement(s);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReturnInfoView riv2 = new ReturnInfoView();
				riv2.setStu_id(rs.getLong("stu_id"));
				riv2.setCla_name(rs.getString("cla_name"));
				riv2.setStu_name(rs.getString("stu_name"));
				riv2.setReturn_delete(rs.getLong("num1"));
				list.add(riv2);
			}
		} finally {
			
			DBConnection.closeAll(rs, pstmt, conn);
		}
		return list;
	}
	
	/****
	 * 获取总的记录数
	 * @param jobinfo
	 * @return
	 * @throws SQLException 
	 */
public    int getCount(ReturnInfoView  riv) throws SQLException{
  Connection conn = DBConnection.getConnection();
  PreparedStatement ptmt =null;
  ResultSet rs =null;
  int count =0;
  StringBuffer sql = new StringBuffer();
	sql.append("SELECT COUNT(*) FROM " + this.getTableName() + "  t ,Student_info s WHERE 1=1 and t. stu_id = s.stu_id ");
	if (riv != null) {
		if (StringUtils.isNotBlank(riv .getStu_name())) {
			sql.append(" AND s.stu_name LIKE '%"
					+ riv .getStu_name() + "%' ");
		}
	}
	try {
		ptmt = conn.prepareStatement(sql.toString());
		rs = ptmt.executeQuery();
		if (rs.next()) {
			count = rs.getInt(1);
		}
	} finally {
		DBConnection.closeAll(rs, ptmt, conn);
	}
	return count;
}
	
/**
 * 获取所有班级信息用于下拉列表
 * @return
 * @throws SQLException
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public List getCla_name() throws SQLException{
	  List list =new ArrayList();
	  Connection conn = DBConnection.getConnection();
	  PreparedStatement ptmt = null;
	  ResultSet  rs = null;
	  String sql = "SELECT * from classes_info ";
	  ClassesDao cd = new ClassesDao();
			
	  try{
			  ptmt =conn.prepareStatement(sql);
			  rs = ptmt.executeQuery();
			  while(rs.next()){
				   list.add(cd.rs2Object(rs));
			  }
	  }finally{
		  DBConnection.closeAll(rs, ptmt, conn);
	  }
	return list;
}

/**
 * 获取学生姓名用于下拉列表
 * @return
 * @throws SQLException
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public List getStu(Long cla_id) throws SQLException{
	  List list =new ArrayList();
	  Connection conn = DBConnection.getConnection();
	  PreparedStatement ptmt = null;
	  ResultSet  rs = null;
	  String sql = "SELECT * from student_info where cla_id = "+ cla_id;
	  StudentDao sd = new StudentDao();
	  try{
			  ptmt =conn.prepareStatement(sql);
			  rs = ptmt.executeQuery();
			  while(rs.next()){
				  
				  list.add((StudentView)sd.rs2Object(rs));
			  }
	  }finally{
		  DBConnection.closeAll(rs, ptmt, conn);
	  }
	return list;
}

}
