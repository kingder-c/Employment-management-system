package com.weixin.course.service;

import java.sql.SQLException;
import java.util.List;

import com.weixin.course.dao.CourseDao;
import com.weixin.course.form.Course;
/**
 * 课程管理service层
 */
public class CourseService {
	/**
	 * 创建dao层对象
	 */
	private static final CourseDao dao = new CourseDao();
	
	/**
	 * 插入或修改课程信息
	 * @param obj
	 * @return
	 * @throws SQLException
	 */
	public long insertOrUpdate(Course obj) throws SQLException {
		if(obj.getCou_id() != null && obj.getCou_id().longValue() > 0){
			return dao.update(obj);
		}else {
			return dao.insert(obj);
		}
	}
	
	
	
	/**
	 * 删除一条记录
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public int delete(Long id) throws SQLException {
		return dao.delete(id);
	}
	
	/**
	 * 根据ID查询一条课程信息
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Course getById(Long id) throws SQLException {
		return dao.getById(id);
	}
	
	/**
	 * 分页查询课程信息
	 * @param obj
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public List query(Course obj, int page) throws SQLException {
		return dao.query(obj, page);
	}
	
	/**
	 * 条件查询课程记录总数
	 * @param obj
	 * @return
	 * @throws SQLException
	 */
	public int getCount(Course obj) throws SQLException {
		return dao.getCount(obj);
	}
}
