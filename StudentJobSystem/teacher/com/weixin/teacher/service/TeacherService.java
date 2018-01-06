package com.weixin.teacher.service;

import java.sql.SQLException;
import java.util.List;

import com.weixin.data.dao.DataDao;
import com.weixin.teacher.dao.TeacherDao;
import com.weixin.teacher.form.Teacher;



public class TeacherService {
private static final TeacherDao dao = new TeacherDao();
private static final DataDao datadao = new DataDao();	
	/**
	 * 插入或者修改一条老师记录
	 * 
	 * @param obj
	 * @return
	 * @throws SQLException
	 */
	public long insertOrUpdate(Teacher obj) throws SQLException {
		if(obj.getTea_id() != null && obj.getTea_id().longValue() > 0){
			return dao.update(obj);
		}else {
			return dao.insert(obj);
		}
	}

	/**
	 * 删除一条老师记录
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int delete(Long id) throws SQLException {
		return dao.delete(id);
	}

	/**
	 * 根据ID查询一条老师记录
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Teacher getById(Long id) throws SQLException {
		return dao.getById(id);
	}

	/**
	 * 分页条件查询老师信息
	 * 
	 * @param obj
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public List query(Teacher obj, int page) throws SQLException {
		return dao.query(obj, page);
	}

	/**
	 * 条件查询老师信息总记录数
	 * 
	 * @param obj
	 * @return
	 * @throws SQLException
	 */
	public int getCount(Teacher obj) throws SQLException {
		return dao.getCount(obj);
	}
	/**
	 * 
	 * @param dataType
	 * @return List 这是 通过 datadao 的已有的方法 的到某个类型的 参数
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public List getSelectData(String dataType) throws SQLException{
		return datadao.getByType(dataType);
	}
	/**
	 * 
	 * @param type
	 * @return List 这是 通过 classdao 的已有的方法 的到  数据表中  某一列 的 数据
	 * @throws SQLException
	 */
}
