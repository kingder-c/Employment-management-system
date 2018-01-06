package com.weixin.student.service;

import java.sql.SQLException;
import java.util.List;

import com.weixin.classes.dao.ClassesDao;
import com.weixin.data.dao.DataDao;
import com.weixin.student.dao.StudentDao;
import com.weixin.student.view.StudentView;


/**
 * @注释 ：service 层
 */
public class StudentService {
	private static final StudentDao dao = new StudentDao();
	private static final DataDao datadao = new DataDao();
	private static final ClassesDao classdao = new ClassesDao();

	/**
	 * 添加后修改 一条数据  如果传入的对象中 没有编号  那么 就是 添加
	 * @param stuv
	 * @return 修改或添加的 编号
	 * @throws SQLException
	 */
	public long insertOrUpdate(StudentView stuv) throws SQLException{
		if(stuv.getStu_id()!=null && stuv.getStu_id().longValue()>0){
			return dao.updateStudent(stuv);
		}else{
			return dao.addStudent(stuv);
		}
	}
	
	/**
	 * 删除数据
	 * @param stu_id
	 * @return 删除了一条数据
	 * @throws SQLException
	 */
	public long delete(Long stu_id) throws SQLException{
		return dao.deleteStudent(stu_id);
	}
	
	/**
	 * 通过stu_id  找到的对象
	 * @param stu_id
	 * @return 通过stu_id  找到的对象
	 * @throws SQLException
	 */
	public StudentView getById(Long stu_id) throws SQLException{
		return dao.getById(stu_id);
	}
	
	/**
	 * 通过封装的 查询参数  得到 查询后的集合  page 是查询 查到的第N页
	 * @param stuv
	 * @param page
	 * @return 通过封装的 查询参数  得到 查询后的集合  page 是查询 查到的第N页
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public List query(StudentView stuv,String cla_starttime1,String cla_starttime2, int page) throws SQLException {
		return dao.query(stuv, cla_starttime1,cla_starttime2,page);
	}
	
	/**
	 * 这个是  审核页面的  查询页通过封装的 查询参数  得到 查询后的集合  page 是查询 查到的第N页
	 * @param stuv
	 * @param cla_starttime1
	 * @param cla_starttime2
	 * @param page
	 * @return  这个是  审核页面的  查询页通过封装的 查询参数  得到 查询后的集合  page 是查询 查到的第N页
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public List query2(StudentView stuv,String cla_starttime1,String cla_starttime2,String stu_checkStr, int page) throws SQLException {
		return dao.query2(stuv, cla_starttime1,cla_starttime2,stu_checkStr,page);
	}
	/**
	 * 统计 数据的个数  方便分页
	 * @param stuv
	 * @return 统计 数据的个数  方便分页
	 * @throws SQLException
	 */
	public int getCount(StudentView stuv,String cla_starttime1,String cla_starttime2) throws SQLException {
		return dao.getCount(stuv,cla_starttime1,cla_starttime2);
	}
	
	/**
	 * 这是 通过 datadao 的已有的方法 的到某个类型的 参数
	 * @param dataType
	 * @return List 这是 通过 datadao 的已有的方法 的到某个类型的 参数
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public List getSelectData(String dataType) throws SQLException{
		return datadao.getByType(dataType);
	}
	
	/**
	 * 这是 通过 classdao 的已有的方法 的到  数据表中  某一列 的 数据
	 * @param type
	 * @return List 这是 通过 classdao 的已有的方法 的到  数据表中  某一列 的 数据
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public List getSelectClasses() throws SQLException{
		return classdao.getName();
	}
	
	/**
	 * 通过 dao 的getTeacherName 的到  数据表中TeacherName 的 数据
	 * @param type
	 * @returnList 这是 通过 dao 的getTeacherName 的到  数据表中TeacherName 的 数据
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public List getSelectTeacher(String type) throws SQLException{
		return dao.getTeacherName();
	}
	

	/**
	 * getSelectStudent 表单  和 查询页面 需要的下拉选项
	 * @return getSelectStudent 表单  和 查询页面 需要的下拉选项
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public List getSelectStudent() throws SQLException {
		return dao.getByType();
	}
	/**
	 * getSelectCourse 表单  和 查询页面 需要的下拉选项
	 * @return getSelectCourse 表单  和 查询页面 需要的下拉选项
	 * @throws SQLException
	 */
	@SuppressWarnings("rawtypes")
	public List getSelectCourse() throws SQLException {
		return dao.getCourse();
	}
	
	/**
	 * 审核
	 * @param stuv
	 * @return 审核
	 * @throws SQLException
	 */
	public long checkUpdate(StudentView stuv) throws SQLException {
		// TODO Auto-generated method stub
		return dao.checkStudent(stuv);
	}
}
