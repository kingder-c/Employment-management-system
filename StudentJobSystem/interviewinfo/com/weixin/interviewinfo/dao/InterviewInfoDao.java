package com.weixin.interviewinfo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.weixin.interviewinfo.view.InterviewInfoView;
import com.yyy.student.dao.StudentDao;
import com.yyy.utils.BaseDao;
import com.yyy.utils.DBConnection;

/**
 * 面试信息DAO层
 */
public class InterviewInfoDao extends BaseDao{
	/**
	 * 得到表名
	 */
	public String getTableName() {
		return "g5_interview";
	}
	/**
	 * 返回结果集
	 */
	public Object rs2Object(ResultSet rs) throws SQLException {
		InterviewInfoView iv = new InterviewInfoView();
		
		iv.setInterview_id(rs.getLong("interview_id"));
		iv.setInterview_company(rs.getString("interview_company"));
		iv.setInterview_time(rs.getDate("interview_time").toString());
		iv.setInterview_type(rs.getString("interview_type"));
		iv.setInterview_job(rs.getString("interview_job"));
		iv.setInterview_salary(rs.getInt("interview_salary"));
		iv.setInterview_weal(rs.getString("interview_weal"));
		iv.setInterview_result(rs.getString("interview_result"));
		iv.setInterview_tickling(rs.getString("interview_tickling"));
		iv.setInterviewer_tickling(rs.getString("interviewer_tickling"));
		iv.setInterview_failreason(rs.getString("interview_failreason"));
		iv.setInterview_manexplain(rs.getString("interview_manexplain"));
		iv.setInterview_note(rs.getString("interview_note"));
		iv.setInterview_addtime(rs.getString("interview_addtime"));
		iv.setInterview_adder(rs.getString("interview_adder"));
		iv.setInterview_delete(rs.getInt("interview_delete"));
		
		iv.setStu_id(rs.getLong("stu_id"));
		iv.setCom_id(rs.getLong("com_id"));
		
		return iv;
	}
	/**
	 * 返回结果集
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unused")
	public Object rs2ObjectView(ResultSet rs) throws SQLException {
		InterviewInfoView iv = new InterviewInfoView();
		StudentDao sd = new StudentDao();
		
		iv.setInterview_id(rs.getLong("interview_id"));
		iv.setInterview_company(rs.getString("interview_company"));
		iv.setInterview_time(rs.getDate("interview_time").toString());
		iv.setInterview_type(rs.getString("interview_type"));
		iv.setInterview_job(rs.getString("interview_job"));
		iv.setInterview_salary(rs.getInt("interview_salary"));
		iv.setInterview_weal(rs.getString("interview_weal"));
		iv.setInterview_result(rs.getString("interview_result"));
		iv.setInterview_tickling(rs.getString("interview_tickling"));
		iv.setInterviewer_tickling(rs.getString("interviewer_tickling"));
		iv.setInterview_failreason(rs.getString("interview_failreason"));
		iv.setInterview_manexplain(rs.getString("interview_manexplain"));
		iv.setInterview_note(rs.getString("interview_note"));
		iv.setInterview_addtime(rs.getString("interview_addtime"));
		iv.setInterview_adder(rs.getString("interview_adder"));
		iv.setInterview_delete(rs.getInt("interview_delete"));
		
		iv.setStu_id(rs.getLong("stu_id"));
		iv.setCom_id(rs.getLong("com_id"));
		iv.setStu_name(rs.getString("stu_name"));
		iv.setCom_name(rs.getString("com_name"));
		iv.setCla_name(rs.getString("cla_name"));
		
		return iv;
	}
	
	
	
	
	
	/**
	 * 添加面试信息
	 * @param iv
	 * @return
	 * @throws SQLException 
	 */
	public long addInterviewInfo(InterviewInfoView iv) throws SQLException{
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		
		String sql = "insert into "
				+ this.getTableName()
				+ " (interview_id,stu_id,com_id,interview_company,"
				+ "interview_time,interview_type,interview_job,interview_salary,"
				+ "interview_weal,interview_result,interview_tickling,interviewer_tickling,"
				+ "interview_failreason,interview_manexplain,interview_note,"
				+ "interview_adder,interview_addtime,interview_delete) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		long id;
		int i = 1;
		try {
			pstmt = conn.prepareStatement(sql);
			
			id = this.getSequence();
			pstmt.setLong(i++, id);
			pstmt.setLong(i++, iv.getStu_id());
			pstmt.setLong(i++, iv.getCom_id());
			pstmt.setString(i++, iv.getInterview_company());
			pstmt.setDate(i++, Date.valueOf(iv.getInterview_time()));
			pstmt.setString(i++, iv.getInterview_type());
			pstmt.setString(i++, iv.getInterview_job());
			pstmt.setInt(i++, iv.getInterview_salary());
			pstmt.setString(i++, iv.getInterview_weal());
			pstmt.setString(i++, iv.getInterview_result());
			pstmt.setString(i++, iv.getInterview_tickling());
			pstmt.setString(i++, iv.getInterviewer_tickling());
			pstmt.setString(i++, iv.getInterview_failreason());
			pstmt.setString(i++, iv.getInterview_manexplain());
			pstmt.setString(i++, iv.getInterview_note());
			pstmt.setString(i++, iv.getInterview_adder());
			pstmt.setString(i++, iv.getInterview_addtime());
			pstmt.setInt(i++, iv.getInterview_delete());

			pstmt.executeUpdate();
		} finally {
			DBConnection.closeAll(pstmt, conn);
		}
		return id;
	}
	
	/**
	 * 删除面试信息
	 * @param interview_id
	 * @return
	 * @throws SQLException 
	 */
	public long deleteInterviewInfo(Long interview_id) throws SQLException{
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		
		String  sql = "delete from "+this.getTableName()+" where interview_id = ?";
		
		int flag = 0;
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, interview_id);
			flag = pstmt.executeUpdate();
		}
		finally{
			DBConnection.closeAll( pstmt, conn);
		}
		return flag;
	}
	
	
	/**
	 * 修改面试信息
	 * @param interview_id
	 * @param iv
	 * @return
	 */
	public int updateInterviewInfo(InterviewInfoView iv){
		//连接数据库
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		
		int num = 0;
		
		StringBuffer sb = new StringBuffer();
		
		String sql = sb.append("update ")
				       .append(this.getTableName())
				       .append(" set stu_id=?,com_id=?,interview_company=?,")
				       .append("interview_time=?,interview_type=?,interview_job=?,interview_salary=?,")
				       .append("interview_weal=?,interview_result=?,interview_tickling=?,interviewer_tickling=?,")
				       .append("interview_failreason=?,interview_manexplain=?,interview_note=?,")
				       .append("interview_addtime=?,interview_adder=?,interview_delete=? ")
				       .append("where interview_id=? ")
				       .toString();
		try {
			pstmt = conn.prepareStatement(sql);
			//替换？中的值
			int i=1;
			pstmt.setLong(i++, iv.getStu_id());
			pstmt.setLong(i++, iv.getCom_id());
			pstmt.setString(i++, iv.getInterview_company());
			pstmt.setDate(i++, Date.valueOf(iv.getInterview_time()));
			pstmt.setString(i++, iv.getInterview_type());
			pstmt.setString(i++, iv.getInterview_job());
			pstmt.setLong(i++, iv.getInterview_salary());
			pstmt.setString(i++, iv.getInterview_weal());
			pstmt.setString(i++, iv.getInterview_result());
			pstmt.setString(i++, iv.getInterview_tickling());
			pstmt.setString(i++, iv.getInterviewer_tickling());
			pstmt.setString(i++, iv.getInterview_failreason());
			pstmt.setString(i++, iv.getInterview_manexplain());
			pstmt.setString(i++, iv.getInterview_note());
			pstmt.setString(i++, iv.getInterview_addtime());
			pstmt.setString(i++, iv.getInterview_adder());
			pstmt.setInt(i++, iv.getInterview_delete());
			pstmt.setLong(i++, iv.getInterview_id());
			num = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBConnection.closeAll(pstmt, conn);
		}
		return num;
	}
	
	
	/**
	 * 根据ID获取面试信息
	 * @param interview_id
	 * @return
	 * @throws SQLException
	 */
	public InterviewInfoView getById(Long interview_id) throws SQLException{

		Connection conn  = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		InterviewInfoView iv =null;

		StringBuffer sql = new StringBuffer();
		sql.append("select ")
		   .append("interview_id,stu_id,com_id,interview_company,")
		   .append("interview_time,interview_type,interview_job,interview_salary,")
		   .append("interview_weal,interview_result,interview_tickling,interviewer_tickling,")
		   .append("interview_failreason,interview_manexplain,interview_note,")
		   .append("interview_addtime,interview_adder,interview_delete from ")
		   .append( this.getTableName())
		   .append(" where interview_id=? ");
		try {
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setLong(1, interview_id);
			rs =pstmt.executeQuery();
			if(rs.next()){
				iv=(InterviewInfoView)this.rs2Object(rs);
			}
		} finally{
			DBConnection.closeAll(rs, pstmt, conn);
		}
		return iv;
	}
	
	
	/**
	 * 条件分页查询
	 * @param iv
	 * @param stu_name
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List query(InterviewInfoView iv,String stu_name, int page) throws SQLException {
		List list = new ArrayList();
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		
		sql.append("select i.interview_id,i.stu_id,i.com_id,i.interview_company,")
		   .append("i.interview_time,i.interview_type,i.interview_job,i.interview_salary,")
		   .append("i.interview_weal,i.interview_result,i.interview_tickling,i.interviewer_tickling,")
		   .append("i.interview_failreason,i.interview_manexplain,i.interview_note,")
		   .append("i.interview_addtime,i.interview_adder,i.interview_delete,")
		   .append("s.stu_name,c.com_name,cla.cla_name ")
		   .append("from " + this.getTableName() + " i")
		   .append(",g5_student s,g5_company c,g5_classes cla")
		   .append(" where i.stu_id=s.stu_id ")
		   .append("and i.com_id=c.com_id ")
		   .append("and cla.cla_id=s.cla_id ");
		
		if(iv != null){
			
		}
		if(stu_name != null){
			if (StringUtils.isNotBlank(stu_name)) {
				sql.append(" AND s.stu_name LIKE '%" + stu_name + "%' ");
			}
		}
		String s = getMysqlCurPageSql(sql.toString(), page);
		try {
			pstmt = conn.prepareStatement(s);
			rs = pstmt.executeQuery();
			while(rs.next()){
				list.add(this.rs2ObjectView(rs));
			}
		} finally{
			DBConnection.closeAll(rs, pstmt, conn);
		}
		return list;
	}
	
	/**
	 * 获取记录数
	 * @param iv
	 * @param stu_name
	 * @return
	 * @throws SQLException
	 */
	public int getCount(InterviewInfoView iv,String stu_name) throws SQLException{
		Connection conn =DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int count=0;
		StringBuffer sql = new StringBuffer();
		
		sql.append("select count(*) ")
		   .append("from " + this.getTableName() + " i")
		   .append(",g5_student s")
		   .append(" where 1=1 and i.stu_id=s.stu_id");
		
		if(stu_name != null){
			if (StringUtils.isNotBlank(stu_name)) {
				sql.append(" AND s.stu_name LIKE '%" + stu_name + "%' ");
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
	
}
