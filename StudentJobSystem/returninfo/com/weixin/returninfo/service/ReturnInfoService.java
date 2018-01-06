package com.weixin.returninfo.service;

import java.sql.SQLException;
import java.util.List;

import com.weixin.returninfo.dao.ReturnInfoDao;
import com.weixin.returninfo.view.ReturnInfoView;

public class ReturnInfoService {
	private static  final ReturnInfoDao dao = new ReturnInfoDao();
	/***
	 * 插入或者修改一条回访记录
	 * @param jobinfo
	 * @return
	 */
	public long insertOrUpdate(ReturnInfoView riv) throws SQLException{
		/*if(riv.getReturn_id()  !=null   &&  riv.getReturn_id().longValue()>0){
			
			return dao.update(riv);
		}else{    */
			return dao.insert(riv);
		
	}
	
	/**
	 * 删除一条回访记录
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public  int delete(long id) throws SQLException {
		return dao.delete(id);
	}
	
	/**
	 * 根据id查询一条回访信息
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public ReturnInfoView getById(long id) throws SQLException{
		return dao.getById(id);
	}
	/**
	 * 根据stu_id 查询信息用于统计
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public List getByStu_id(long id) throws SQLException{
		return dao.getByStu_id(id);
	}
	/**
	 * 分页条件查询回访信息
	 * @param jobinfo
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public  List query(ReturnInfoView riv, int page) throws SQLException{
		return dao.query(riv, page);
	}
	/**
	 * 统计中的查询
	 * @param riv
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	public  List query2(ReturnInfoView riv,int page) throws SQLException{
		return dao.query2(riv,page);
	}
	/**
	 * 条件查询就业信息总记录数
	 * @param jobinfo
	 * @return
	 * @throws SQLException
	 */
	public int getCount(ReturnInfoView riv) throws SQLException{
		return dao.getCount(riv);
	}

	/**
	 *获取班级名称
	 * @return
	 * @throws SQLException
	 */
	public List getCla_name() throws SQLException{
		return dao.getCla_name();
	}
	/**
	 * 获取学生姓名
	 * @return
	 * @throws SQLException
	 */
	public List getStu(Long cla_id) throws SQLException{
		return dao.getStu(cla_id);
	}
	
}
