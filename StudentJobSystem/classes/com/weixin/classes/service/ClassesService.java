package com.weixin.classes.service;

import java.sql.SQLException;
import java.util.List;

import com.weixin.classes.dao.ClassesDao;
import com.weixin.classes.view.ClassesView;
import com.weixin.course.dao.CourseDao;
import com.yyy.teacher.dao.TeacherDao;
/**
 * 班级service层
 */
public class ClassesService {
	
	private static final ClassesDao dao = new ClassesDao();
	
	private static final CourseDao cd = new CourseDao();
	private static final TeacherDao td = new TeacherDao();
	/**
	 * 添加
	 * @param cla
	 * @return
	 * @throws SQLException
	 */
	public long insertOrUpdate(ClassesView cw) throws SQLException{
		if(cw.getCla_id()!=null && cw.getCla_id().longValue()>0){
			return dao.updateClasses(cw);
		}else{
			return dao.addClasses(cw);
		}
	}
	
	/**
	 * 删除
	 * @param cla_id
	 * @return
	 * @throws SQLException
	 */
	public long delete(Long cla_id) throws SQLException{
		return dao.deleteClasses(cla_id);
	}
	/**
	 * 通过ID查找
	 * @param cla_id
	 * @return
	 * @throws SQLException
	 */
	public ClassesView getById(Long cla_id) throws SQLException{
		return dao.getById(cla_id);
	}
	/**
	 * 查询结果
	 * @param cla
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public List query(ClassesView cw,String cou_name,String tea_name,
			String cla_starttime1,String cla_starttime2,int page) throws SQLException {
		return dao.query(cw,cou_name,tea_name,cla_starttime1,cla_starttime2,page);
	}
	/**
	 * 记录总数
	 * @param cla
	 * @return
	 * @throws SQLException
	 */
	public int getCount(ClassesView cla,String cou_name,String tea_name,
			String cla_starttime1,String cla_starttime2) throws SQLException {
		return dao.getCount(cla,cou_name,tea_name,cla_starttime1,cla_starttime2);
	}

	/**
	 * 得到课程数据
	 * @return
	 * @throws SQLException 
	 */
	@SuppressWarnings("rawtypes")
	public List getSelectCourse() throws SQLException {
		return cd.getName();
	}
	/**
	 * 得到教师数据
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public List getSelectTeacher() throws SQLException {
		return td.getName();
	}
	
}
