package com.winxin.jobinfo.service;

import java.sql.SQLException;
import java.util.List;

import com.winxin.jobinfo.dao.JobInfoDao;
import com.winxin.jobinfo.form.JobInfo;
import com.winxin.jobinfo.view.JobInfoView;

public class JobInfoService {

		private static  final JobInfoDao dao = new JobInfoDao();
	
		
		/***
		 * 插入或者修改一条就业记录
		 * @param jobinfo
		 * @return
		 */
		public long insertOrUpdate(JobInfoView jobinfoview) throws SQLException{
			if(jobinfoview.getJob_id()  !=null   &&  jobinfoview.getJob_id().longValue()>0){
				
				return dao.update(jobinfoview);
			}else{
				return dao.insert(jobinfoview);
			}
		}
		
		/**
		 * 删除一条就业记录
		 * @param id
		 * @return
		 * @throws SQLException
		 */
		public  int delete(long id) throws SQLException {
			return dao.delete(id);
		}
		
		/**
		 * 根据id查询一条就业信息
		 * @param id
		 * @return
		 * @throws SQLException
		 */
		public JobInfoView getById(Long id) throws SQLException{
			return dao.getById(id);
		}
		
		/**
		 * 分页条件查询就业信息
		 * @param jobinfo
		 * @param page
		 * @return
		 * @throws SQLException
		 */
		public  List query(JobInfoView jobinfoview,String job_startdate1,String job_startdate2, int page) throws SQLException{
			return dao.query(jobinfoview, job_startdate1,job_startdate2,page);
		}
		
		/**
		 * 条件查询就业信息总记录数
		 * @param jobinfo
		 * @return
		 * @throws SQLException
		 */
		public int getCount(JobInfoView jobinfoview,String job_startdate1,String job_startdate2, int page) throws SQLException{
			return dao.getCount(jobinfoview, job_startdate1,job_startdate2,page);
		}
		
	
		/**
		 * 获取班级名称的部分信息用于前台页面的下拉列表
		 * @param cla_name
		 * @return
		 * @throws SQLException
		 */
		@SuppressWarnings("rawtypes")
		public List getCla_name() throws SQLException{
			return dao.getCla_name();
		}
		/**
		 * 获取课程名称的部分信息用于前台页面的下拉列表
		 * @return
		 * @throws SQLException
		 */
		@SuppressWarnings("rawtypes")
		public List getCou_name() throws SQLException{
			return dao.getCou_name();
		}
		
		//获取学生姓名
		@SuppressWarnings("rawtypes")
		public List getStu_name() throws SQLException{
			return dao.getStu_name();
		}
		//获取公司id
		@SuppressWarnings("rawtypes")
		public List getCom_id() throws SQLException{
			return dao.getCom_id();
		}
}
