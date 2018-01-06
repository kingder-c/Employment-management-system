package com.weixin.interviewinfo.service;

import java.sql.SQLException;
import java.util.List;

import com.weixin.company.dao.CompanyDao;
import com.weixin.interviewinfo.dao.InterviewInfoDao;
import com.weixin.interviewinfo.view.InterviewInfoView;
import com.yyy.student.dao.StudentDao;

/**
 * 面试信息service层
 */
public class InterviewInfoService {

	private static final InterviewInfoDao idao = new InterviewInfoDao();
	
	private static final StudentDao sdao = new StudentDao();
	private static final CompanyDao cdao = new CompanyDao();
	/**
	 * 添加
	 * @param iv
	 * @return
	 * @throws SQLException
	 */
	public long insertOrUpdate(InterviewInfoView iv) throws SQLException{
		if(iv.getInterview_id()!=null && iv.getInterview_id().longValue()>0){
			return idao.updateInterviewInfo(iv);
		}else{
			return idao.addInterviewInfo(iv);
		}
	}
	/**
	 * 删除
	 * @param interview_id
	 * @return
	 * @throws SQLException
	 */
	public long delete(Long interview_id) throws SQLException{
		return idao.deleteInterviewInfo(interview_id);
	}
	/**
	 * 根据ID查找
	 * @param interview_id
	 * @return
	 * @throws SQLException
	 */
	public InterviewInfoView getById(Long interview_id) throws SQLException{
		return idao.getById(interview_id);
	}
	/**
	 * 查询结果
	 * @param iv
	 * @param stu_name
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public List query(InterviewInfoView iv,String stu_name,int page) throws SQLException {
		return idao.query(iv, stu_name, page);
	}
	/**
	 * 记录总数
	 * @param iv
	 * @param stu_name
	 * @return
	 * @throws SQLException
	 */
	public int getCount(InterviewInfoView iv,String stu_name) throws SQLException {
		return idao.getCount(iv, stu_name);
	}
	
	/**
	 * 得到学员
	 * @return
	 * @throws SQLException 
	 */
	@SuppressWarnings("rawtypes")
	public List getSelectStudent() throws SQLException {
		return sdao.getName();
	}
	/**
	 * 得到企业
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public List getSelectCompany() throws SQLException {
		return cdao.getName();
	}
}
