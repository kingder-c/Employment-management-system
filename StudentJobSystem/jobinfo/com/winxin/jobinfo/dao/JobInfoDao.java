package com.winxin.jobinfo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.winxin.jobinfo.form.JobInfo;
import com.winxin.jobinfo.view.JobInfoView;
import com.yyy.utils.BaseDao;
import com.yyy.utils.DBConnection;

/**
 * 继承basedao 获取当前的表名
 * @注释 ：
 */

@SuppressWarnings("unused")
public class JobInfoDao  extends BaseDao{
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "job_info";
	}
/**
 * 实体表的结果集
 */
	@Override
	public Object rs2Object(ResultSet rs) throws SQLException {
		JobInfoView  jobInfoview = new JobInfoView();
		jobInfoview.setJob_id(rs.getLong("job_id"));
		jobInfoview.setCom_id(rs.getLong("com_id"));
		jobInfoview.setStu_id(rs.getLong("stu_id"));
		jobInfoview.setJob_company(rs.getString("job_company"));
		jobInfoview.setJob_job(rs.getString("job_job"));
		jobInfoview.setJob_city(rs.getString("job_city"));
		jobInfoview.setJob_startdate(rs.getDate("job_startdate").toString());
		
		jobInfoview.setJob_basesalary(rs.getLong("job_basesalary"));
		
		jobInfoview.setJob_three(rs.getLong("job_three"));
		
		jobInfoview.setJob_addtime(rs.getDate("job_addtime").toString());
		jobInfoview.setJob_adder(rs.getString("job_adder"));
		//jobInfoview.setJob_delete(rs.getLong("job_delete"));
		return  jobInfoview;
	}
	/**
	 * 视图的结果集
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	public Object rs2Object2(ResultSet rs) throws SQLException {
		JobInfoView  jobInfoview = new JobInfoView();
		jobInfoview.setJob_id(rs.getLong("job_id"));
		jobInfoview.setCom_id(rs.getLong("com_id"));
		jobInfoview.setCom_name(rs.getString("com_name"));
		jobInfoview.setStu_id(rs.getLong("stu_id"));
		jobInfoview.setStu_native(rs.getString("stu_native"));
		jobInfoview.setStu_name(rs.getString("stu_name"));
		jobInfoview.setCla_name(rs.getString("cla_name"));
		jobInfoview.setCou_name(rs.getString("cou_name"));
		jobInfoview.setJob_company(rs.getString("job_company"));
		jobInfoview.setJob_job(rs.getString("job_job"));
		jobInfoview.setJob_city(rs.getString("job_city"));
		jobInfoview.setJob_startdate(rs.getDate("job_startdate").toString());
		
		jobInfoview.setJob_basesalary(rs.getLong("job_basesalary"));
		
		jobInfoview.setJob_three(rs.getLong("job_three"));
		
		jobInfoview.setJob_addtime(rs.getDate("job_addtime").toString());
		jobInfoview.setJob_adder(rs.getString("job_adder"));
		jobInfoview.setStu_name(rs.getString("stu_name"));
	//	jobInfoview.setJob_delete(rs.getLong("job_delete"));
		return  jobInfoview;
	}
	
		/***
		 * 向数据库中插入一条就业信息
		 * @param jobinfo
		 * @return
		 * @throws SQLException 
		 */
		public  long insert(JobInfoView jobinfoview) throws SQLException{
			    
				Connection conn =DBConnection.getConnection();
				PreparedStatement  pstmt = null;
				String sql = "INSERT INTO "
						+ this.getTableName()
						
						+ "(stu_id,"
						+ "com_id,"
						+ "job_company,"
						+ "job_job,"
						+ "job_city,"
						+ "job_startdate,"
						+ "job_basesalary,"
						+ "job_three,"
						+ "job_comment,"
						+ "job_addtime,"
						+ "job_adder)"
						+ " VALUES(?,?,?,?,?,?,?,?,?,now(),?)";
				long id=1;
				int i = 1;
				try{
						pstmt=conn.prepareStatement(sql);
						//id = this.getSequence();
						
						
						
						//pstmt.setLong(i++, id);
						pstmt.setLong(i++,jobinfoview.getStu_id());
						pstmt.setLong(i++,jobinfoview.getCom_id());
						pstmt.setString(i++,jobinfoview.getJob_company());
						pstmt.setString(i++, jobinfoview.getJob_job());
						pstmt.setString(i++, jobinfoview.getJob_city());
						pstmt.setString(i++,jobinfoview.getJob_startdate());
						pstmt.setLong(i++, jobinfoview.getJob_basesalary());
						pstmt.setLong(i++, jobinfoview.getJob_three());
						pstmt.setString(i++, jobinfoview.getJob_comment());
						pstmt.setString(i++, jobinfoview.getJob_adder());
						pstmt.executeUpdate();
				}finally{
					DBConnection.closeAll(pstmt, conn);
				}
				
				return id;
			
	}
		/***
		 * 删除就业信息
		 * @param id
		 * @return
		 * @throws SQLException
		 */
		
		public int delete(Long id) throws SQLException {
			Connection conn = DBConnection.getConnection();
			PreparedStatement ptmt = null;
			String sql = "DELETE FROM " + getTableName()
					+ " WHERE job_id = ?";
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
		 *就业信息的修改
		 * @param jobInfo
		 * @return
		 * @throws SQLException 
		 */
	public  int update(	JobInfoView jobinfoview) throws SQLException{
		Connection conn =DBConnection.getConnection();
		PreparedStatement  pstmt = null;
		
		String sql = "UPDATE  "
				               +this.getTableName()
				               +" SET job_company=?,"
				               + "job_city=?,"
				               + "job_job=?,"
				               + "job_startdate=?,"
				               + "job_basesalary=?,"
				               + "job_three=?,"
				               + "job_comment=?,"
				               + "job_addtime=now(),"
				               + "job_adder=?"
				               + "  where job_id = ? ";
		
		int flag = 0;
		int i = 1;
	    try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(i++,jobinfoview.getJob_company());
	
			pstmt.setString(i++, jobinfoview.getJob_city());
			pstmt.setString(i++, jobinfoview.getJob_job());
			pstmt.setString(i++, jobinfoview.getJob_startdate());
			pstmt.setLong(i++, jobinfoview.getJob_basesalary());
			pstmt.setLong(i++, jobinfoview.getJob_three());
			pstmt.setString(i++, jobinfoview.getJob_comment());
			//pstmt.setString(i++, jobinfoview.getJob_addtime());
			pstmt.setString(i++, jobinfoview.getJob_adder());
			//pstmt.setLong(i++, jobinfoview.getJob_delete());
		    pstmt.setLong(i++, jobinfoview.getJob_id());
			flag=pstmt.executeUpdate();
		} finally{
			DBConnection.closeAll(pstmt, conn);
		   }
	    return flag;
	}
	    
	
	/**
	 * 根据id查询信息
	 * @return
	 * @throws SQLException 
	 */
		public   JobInfoView getById(Long id) throws SQLException{
			JobInfoView jobinfoview =null;
			Connection conn =DBConnection.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "SELECT   t.job_id,t.com_id,t.stu_id,t.job_company,t.job_city,t.job_job,"
								+ "t.job_startdate,t.job_basesalary,t.job_three,"
								+ "t.job_comment,t.job_addtime,t.job_adder ,"
								+"cl.cla_name,"
								+"s.stu_name,s.stu_native,"
								+ "com.com_id,com.com_name,"
								+"co.cou_name "
								+ "FROM "
								+ this.getTableName() + " t,student_info s,classes_info cl,course_info co,company_info com "
								+ "WHERE 1=1 and s.stu_id=t.stu_id and s.cla_id=cl.cla_id and cl.cou_id=co.cou_id and com.com_name = t.job_company and t.job_id=?";
		
		try {
			    pstmt = conn.prepareStatement(sql);
				pstmt.setLong(1, id);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					  jobinfoview = (JobInfoView)this.rs2Object2(rs);
				}
			} finally{
				DBConnection.closeAll(rs, pstmt, conn);
			}
			
			return jobinfoview;
	}
		
		/**
		 * 根据分页条件查询就业信息
		 * @param obj
		 * @param page
		 * @return
		 * @throws SQLException
		 */
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public List query(JobInfoView jobinfoview,String job_startdate1,String job_startdate2, int page) throws SQLException {
			List list = new ArrayList();
			Connection conn = DBConnection.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT  t.job_id,t.com_id,t.stu_id,t.job_company,t.job_job,"
								+ "t.job_city,t.job_startdate,"
								+ "t.job_basesalary,t.job_three,"
								+ "t.job_comment,t.job_addtime,t.job_adder ,"
								+"cl.cla_name,"
								+"s.stu_name,s.stu_native,"
								+"com.com_name,"
								+"co.cou_name "
								+ "FROM "
								+ this.getTableName() + "  t,student_info s  ,classes_info cl,course_info co,company_info com "
								+ "WHERE 1=1 and s.stu_id=t.stu_id and s.cla_id=cl.cla_id and com.com_id = t.com_id and cl.cou_id=co.cou_id");
			if (jobinfoview!= null) {
				if (StringUtils.isNotBlank(jobinfoview.getStu_name())) {
					sql.append(" AND s.Stu_name LIKE '%"
							+ jobinfoview.getStu_name()+ "%' ");
				}
				if (StringUtils.isNotBlank(jobinfoview.getCla_name())) {
					sql.append(" AND cl.Cla_name LIKE '%"
							+ jobinfoview.getCla_name()+ "%' ");
				}
				
				if (StringUtils.isNotBlank(jobinfoview.getStu_native())) {
					sql.append(" AND s.Stu_native LIKE '%"
							+ jobinfoview.getStu_native()+ "%' ");
				}
				if (StringUtils.isNotBlank(jobinfoview.getCou_name())) {
					sql.append(" AND co.Cou_name LIKE '%"
							+ jobinfoview.getCou_name()+ "%' ");
				}
				if (StringUtils.isNotBlank(jobinfoview.getJob_company())) {
					sql.append(" AND  t.job_company LIKE '%"
							+ jobinfoview.getJob_company()+ "%' ");
				}
			}
			if( job_startdate1 !=null){
				if (StringUtils.isNotBlank(job_startdate1)) {
					sql.append(" AND t.job_startdate > to_date(' "+ job_startdate1 + "','yyyy-MM-dd' ) ")
					.append(" or t.job_startdate = to_date(' "+ job_startdate1 + "','yyyy-MM-dd')  " );
				}
			}
			if( job_startdate2 !=null){
				if (StringUtils.isNotBlank(job_startdate2)) {
					sql.append(" AND t.job_startdate > to_date(' "+ job_startdate2 + "','yyyy-MM-dd' ) ")
					.append(" or t.job_startdate = to_date(' "+ job_startdate2 + "','yyyy-MM-dd')  " );
				}
			}
			sql.append(" ORDER BY t.job_id DESC");
			String s = getMysqlCurPageSql(sql.toString(), page);
			try {
				pstmt = conn.prepareStatement(s);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					list.add((JobInfoView)rs2Object2(rs));
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
  public    int getCount(JobInfoView jobinfoview,String job_startdate1,String job_startdate2,int page) throws SQLException{
	  Connection conn = DBConnection.getConnection();
	  PreparedStatement ptmt =null;
	  ResultSet rs =null;
	  int count =0;
	  StringBuffer sql = new StringBuffer();

		sql.append("SELECT  COUNT(*) FROM "+this.getTableName() + "  t,student_info s,classes_info cl,course_info co "
				+ "WHERE 1=1 and s.stu_id=t.stu_id and s.cla_id=cl.cla_id and cl.cou_id=co.cou_id");
		if (jobinfoview!= null) {
			if (StringUtils.isNotBlank(jobinfoview.getStu_name())) {
				sql.append(" AND s.Stu_name LIKE '%"
						+ jobinfoview.getStu_name()+ "%' ");
			}
			if (StringUtils.isNotBlank(jobinfoview.getCla_name())) {
				sql.append(" AND cl.Cla_name LIKE '%"
						+ jobinfoview.getCla_name()+ "%' ");
			}
			
			if (StringUtils.isNotBlank(jobinfoview.getStu_native())) {
				sql.append(" AND s.Stu_native LIKE '%"
						+ jobinfoview.getStu_native()+ "%' ");
			}
			if (StringUtils.isNotBlank(jobinfoview.getCou_name())) {
				sql.append(" AND co.Cou_name LIKE '%"
						+ jobinfoview.getCou_name()+ "%' ");
			}
			if (StringUtils.isNotBlank(jobinfoview.getJob_company())) {
				sql.append(" AND  t.job_company LIKE '%"
						+ jobinfoview.getJob_company()+ "%' ");
			}
		}
		if( job_startdate1 !=null){
			if (StringUtils.isNotBlank(job_startdate1)) {
				sql.append(" AND t.job_startdate > to_date(' "+ job_startdate1 + "','yyyy-MM-dd' ) ")
				.append(" or t.job_startdate = to_date(' "+ job_startdate1 + "','yyyy-MM-dd')  " );
			}
		}
		if( job_startdate2 !=null){
			if (StringUtils.isNotBlank(job_startdate2)) {
				sql.append(" AND t.job_startdate > to_date(' "+ job_startdate2 + "','yyyy-MM-dd' ) ")
				.append(" or t.job_startdate = to_date(' "+ job_startdate2 + "','yyyy-MM-dd')  " );
			}
		}
		sql.append(" ORDER BY t.job_id DESC");
		String s = getOrclCurPageSql(sql.toString(), page);
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
   * 获取其他模块的部分信息用于list页面的下拉显示
   * 获取  cla_name
   * @param cla_name
   * @return
   * @throws SQLException
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
public List getCla_name() throws SQLException{
	  List list =new ArrayList();
	  Connection conn = DBConnection.getConnection();
	  PreparedStatement ptmt = null;
	  ResultSet  rs = null;
	  String sql = "SELECT distinct cla_name  from classes_info ";

			
	  try{
			  ptmt =conn.prepareStatement(sql);
			  rs = ptmt.executeQuery();
			  while(rs.next()){
				  JobInfoView jobv = new JobInfoView();
				  jobv.setCla_name(rs.getString("cla_name"));
				   list.add(jobv);
			  }
	  }finally{
		  DBConnection.closeAll(rs, ptmt, conn);
	  }
	return list;
  }
  
  
  /***
   * 获取 Cou_name
   * @return
   * @throws SQLException
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
public List getCou_name() throws SQLException{
	  List list =new ArrayList();
	  Connection conn = DBConnection.getConnection();
	  PreparedStatement ptmt = null;
	  ResultSet  rs = null;
	  String sql = "SELECT distinct cou_name from course_info ";

			
	  try{
			  ptmt =conn.prepareStatement(sql);
		
			  rs = ptmt.executeQuery();
			  while(rs.next()){
				  JobInfoView jobv = new JobInfoView();
				  jobv.setCou_name(rs.getString("cou_name"));
				   list.add(jobv);
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
  @SuppressWarnings({ "unchecked", "rawtypes" })
public List getStu_name() throws SQLException{
	  List list =new ArrayList();
	  Connection conn = DBConnection.getConnection();
	  PreparedStatement ptmt = null;
	  ResultSet  rs = null;
	  String sql = "SELECT distinct stu_name  from student_info ";

			
	  try{
			  ptmt =conn.prepareStatement(sql);
			  rs = ptmt.executeQuery();
			  while(rs.next()){
				  JobInfoView jobv = new JobInfoView();
				  jobv.setStu_name(rs.getString("stu_name"));
				   list.add(jobv);
			  }
	  }finally{
		  DBConnection.closeAll(rs, ptmt, conn);
	  }
	return list;
  }
  
  /**
   * 获取公司表中的com_id
   * @return
   * @throws SQLException
   */
  
  @SuppressWarnings({ "rawtypes", "unchecked" })
public List getCom_id() throws SQLException{
	  List list =new ArrayList();
	  Connection conn = DBConnection.getConnection();
	  PreparedStatement ptmt = null;
	  ResultSet  rs = null;
	  String sql = "SELECT distinct com_id,com_name  from company_info ";

			
	  try{
			  ptmt =conn.prepareStatement(sql);
			  rs = ptmt.executeQuery();
			  while(rs.next()){
				  JobInfoView jobv = new JobInfoView();
				  jobv.setCom_id(rs.getLong("com_id"));
				  jobv.setCom_name(rs.getString("com_name"));
				  list.add(jobv);
			  }
	  }finally{
		  DBConnection.closeAll(rs, ptmt, conn);
	  }
	return list;
  }
  
}

