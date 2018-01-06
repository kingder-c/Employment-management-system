package com.yyy.student.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.weixin.course.form.Course;
import com.yyy.student.form.Student;
import com.yyy.student.view.StudentView;
import com.yyy.teacher.form.Teacher;
import com.yyy.utils.BaseDao;
import com.yyy.utils.DBConnection;

/**
 *  ：连接数据库 并且对 数据库 进行具体的操作
 */
public class StudentDao extends BaseDao {

	/**
	 * ：方法的重写
	 */
	@Override
	public String getTableName() {
		return "student_info";
	}

	/**
	 * ：方法的重写   返回：学生的窗口视图
	 */
	@Override
	public Object rs2Object(ResultSet rs) throws SQLException {
		StudentView stuView = new StudentView();

		stuView.setStu_id(rs.getLong("stu_id"));
		stuView.setCla_id(rs.getLong("cla_id"));
		stuView.setStu_name(rs.getString("stu_name"));
		stuView.setStu_sex(rs.getLong("stu_sex"));
		stuView.setStu_native(rs.getString("stu_native"));
		stuView.setStu_address(rs.getString("stu_address"));
		stuView.setStu_email(rs.getString("stu_email"));
		stuView.setStu_phone(rs.getString("stu_phone"));
		stuView.setStu_graduation(rs.getString("stu_graduation"));
		stuView.setStu_major(rs.getString("stu_major"));
		stuView.setStu_into_time((rs.getDate("stu_into_time")).toString());

		// stuView.setStu_into_time(rs.getString("stu_into_time"));
		stuView.setStu_state(rs.getString("stu_state"));
		stuView.setStu_check(rs.getLong("stu_check"));
		stuView.setStu_note(rs.getString("stu_note"));
		stuView.setStu_adder(rs.getString("stu_adder"));
		stuView.setStu_addtime(rs.getDate("stu_addtime").toString());
		stuView.setStu_delete(rs.getLong("stu_delete"));

		return stuView;
	}

	/**
	 * 返回StudentView 的封装对象  相当于封装 查询视图的一条记录
	 * @param rs
	 * @return 返回StudentView 的封装对象  相当于封装 查询视图的一条记录
	 * @throws SQLException
	 */
	public Object rs2ObjectView(ResultSet rs) throws SQLException {
		StudentView stuView = new StudentView();

		stuView.setStu_id(rs.getLong("stu_id"));
		stuView.setCla_id(rs.getLong("cla_id"));
		stuView.setStu_name(rs.getString("stu_name"));
		stuView.setStu_sex(rs.getLong("stu_sex"));
		stuView.setStu_native(rs.getString("stu_native"));
		stuView.setStu_address(rs.getString("stu_address"));
		stuView.setStu_email(rs.getString("stu_email"));
		stuView.setStu_phone(rs.getString("stu_phone"));
		stuView.setStu_graduation(rs.getString("stu_graduation"));
		stuView.setStu_major(rs.getString("stu_major"));
		stuView.setStu_into_time((rs.getDate("stu_into_time")).toString());

		// stuView.setStu_into_time(rs.getString("stu_into_time"));
		stuView.setStu_state(rs.getString("stu_state"));
		stuView.setStu_check(rs.getLong("stu_check"));
		stuView.setStu_note(rs.getString("stu_note"));
		stuView.setStu_adder(rs.getString("stu_adder"));
		stuView.setStu_addtime(rs.getDate("stu_addtime").toString());
		stuView.setStu_delete(rs.getLong("stu_delete"));
		stuView.setCla_name(rs.getString("cla_name"));
		stuView.setCla_starttime(rs.getDate("cla_starttime").toString());
		stuView.setCou_name(rs.getString("cou_name"));
		// stuView.setUser_id(rs.getLong("user_id"));
		return stuView;
	}

	/**
	 * ：添加学员
	 * @param stuv
	 * @return 添加学员后生成 的 学员id
	 * @throws SQLException
	 */
	public long addStudent(StudentView stuv) throws SQLException {
		// 连接数据库
		Connection conn = DBConnection.getConnection();
		PreparedStatement prestmt = null;// 预处理
		long id = 0;
		long flag = 0;
		StringBuffer sql = new StringBuffer();
		sql.append(
				"insert into " + this.getTableName()
						+ "(cla_id,stu_name,")
				.append("stu_sex,stu_native,stu_address,")
				.append("stu_email,stu_phone,stu_graduation,")
				.append("stu_major,stu_into_time,")
				.append("stu_note,stu_adder,stu_addtime,stu_delete) ")
				.append("values (?,?,?,?,?,?,?,?,?,")
				.append("?,?,?,now(),?) ");
		try {
			// 对 sql 进行预处理 插入
			prestmt = conn.prepareStatement(sql.toString());
			// 代替？中的值
			// prestmt.setLong(1,stuv.getStu_id());
			int i = 1;
			//id = this.getSequence();
			//prestmt.setLong(i++, id);
			prestmt.setLong(i++, stuv.getCla_id());
			prestmt.setString(i++, stuv.getStu_name());
			/**
			 * public static Date valueOf(String s)将 JDBC 日期转义形式的字符串转换成 Date 值。
			 * 参数： s - 表示 "yyyy-mm-dd" 形式的日期的 String 对象 返回： 表示给定日期的
			 * java.sql.Date 对象 抛出： IllegalArgumentException - 如果给定日期不是 JDBC
			 * 日期转义形式 (yyyy-mm-dd)
			 */
			prestmt.setLong(i++, stuv.getStu_sex());
			prestmt.setString(i++, stuv.getStu_native());
			prestmt.setString(i++, stuv.getStu_address());
			prestmt.setString(i++, stuv.getStu_email());
			prestmt.setString(i++, stuv.getStu_phone());
			prestmt.setString(i++, stuv.getStu_graduation());
			prestmt.setString(i++, stuv.getStu_major());
			prestmt.setDate(i++, Date.valueOf(stuv.getStu_into_time()));
			//prestmt.setString(i++, stuv.getStu_state());
			//prestmt.setLong(i++, stuv.getStu_check());
			prestmt.setString(i++, stuv.getStu_note());
			prestmt.setString(i++, stuv.getStu_adder());
			prestmt.setLong(i++, 0l);

			flag = prestmt.executeUpdate();

		} 
		finally {
			DBConnection.closeAll(prestmt, conn);
		}
		return flag;
	}

	/**
	 * ：删除学员
	 * @param stu_id
	 * @return 删除学员的id
	 * @throws SQLException
	 */
	public long deleteStudent(Long stu_id) throws SQLException {
		// 连接数据库
		Connection conn = DBConnection.getConnection();
		PreparedStatement prestmt = null;// 预处理
		// sql 语句
		String sql = "delete from " + this.getTableName() + " where stu_id = ?";
		int flag = 0;
		// 对 sql 进行预处理 插入
		try {
			prestmt = conn.prepareStatement(sql);
			// 替换？中的值
			prestmt.setLong(1, stu_id);
			flag = prestmt.executeUpdate();

		} finally {
			DBConnection.closeAll(prestmt, conn);
		}

		return flag;
	}

	/**
	 * ：修改学员信息
	 * @param stu_id
	 * @param stuv
	 * @return 修改信息影响的行数
	 * @throws SQLException
	 */
	public int updateStudent(StudentView stuv) throws SQLException {
		// 连接数据库
		Connection conn = DBConnection.getConnection();
		PreparedStatement prestmt = null;// 预处理
		int num = 0;// 修改的行数
		StringBuffer sb = new StringBuffer();
		// sql语句
		String sql = sb
				.append("update " + this.getTableName()
						+ " set cla_id=?,stu_name=?,")
				.append("stu_sex=?, stu_native = ? , stu_address= ? ,  ")
				.append("stu_email = ? , stu_phone = ?,stu_graduation = ?, ")
				.append("stu_major = ?,stu_into_time = ?, ")
				.append("stu_note = ?,stu_adder = ?,stu_addtime = now(),stu_delete = ? where stu_id = ?")
				.toString();
		// 对 sql 进行预处理 插入
		try {
			prestmt = conn.prepareStatement(sql);
			// 替换？中的值
			int i = 1;
			prestmt.setLong(i++, stuv.getCla_id());
			prestmt.setString(i++, stuv.getStu_name());
			/**
			 * public static Date valueOf(String s)将 JDBC 日期转义形式的字符串转换成 Date 值。
			 * 参数： s - 表示 "yyyy-mm-dd" 形式的日期的 String 对象 返回： 表示给定日期的
			 * java.sql.Date 对象 抛出： IllegalArgumentException - 如果给定日期不是 JDBC
			 * 日期转义形式 (yyyy-mm-dd)
			 */
			prestmt.setLong(i++, stuv.getStu_sex());
			prestmt.setString(i++, stuv.getStu_native());
			prestmt.setString(i++, stuv.getStu_address());
			prestmt.setString(i++, stuv.getStu_email());
			prestmt.setString(i++, stuv.getStu_phone());
			prestmt.setString(i++, stuv.getStu_graduation());
			prestmt.setString(i++, stuv.getStu_major());
			prestmt.setString(i++, stuv.getStu_into_time());
			//prestmt.setString(i++, stuv.getStu_state());
			//prestmt.setLong(i++, stuv.getStu_check());
			prestmt.setString(i++, stuv.getStu_note());
			prestmt.setString(i++, stuv.getStu_adder());
			prestmt.setLong(i++, stuv.getStu_delete());
			prestmt.setLong(i++, stuv.getStu_id());
			num = prestmt.executeUpdate();
		} finally {
			DBConnection.closeAll(prestmt, conn);
		}

		return num;
	}

	/**
	 *  通过stu_id 查询到 对应的 StudentView
	 * @param stu_id
	 * @return StudentView 的实例 具体的一个对象
	 * @throws SQLException
	 */
	public StudentView getById(Long stu_id) throws SQLException {
		// 连接数据库
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StudentView stuView = null;

		StringBuffer sql = new StringBuffer();
		sql.append("select stu_id,cla_id,stu_name,")
				.append("stu_sex,stu_native,stu_address,")
				.append("stu_email,stu_phone,stu_graduation,")
				.append("stu_major,stu_into_time,stu_state,")
				.append("stu_check,stu_note,stu_adder,stu_addtime,stu_delete from ")
				.append(this.getTableName()).append(" where stu_id=? ");
		try {
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setLong(1, stu_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				stuView = (StudentView) this.rs2Object(rs);    //将查出的值存入stuView（是个对象）
			}
		} finally {
			DBConnection.closeAll(rs, pstmt, conn);
		}
		return stuView;
	}

	/**
	 * 传入 查询条件（封装到stuv） 得到瞒住条件的对象的集合
	 * @param stuv
	 * @param page
	 * @return 传入 查询条件（封装到stuv） 得到瞒住条件的对象的集合
	 * @throws SQLException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List query(StudentView stuv, String cla_starttime1,
			String cla_starttime2, int page) throws SQLException {
		List list = new ArrayList();
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer sql = new StringBuffer();
		// student 部分
		sql.append("select t.stu_id,t.cla_id,t.stu_name,")
				.append("t.stu_sex,t.stu_native,t.stu_address,")
				.append("t.stu_email,t.stu_phone,t.stu_graduation,")
				.append("t.stu_major,t.stu_into_time,stu_state,")
				.append("t.stu_check,t.stu_note,t.stu_adder,t.stu_addtime,t.stu_delete ,")
				// class 部分
				.append("c.cla_name ,c.cla_starttime, ")
				// course 部分
				.append(" cou.cou_name ")
				// user 部分
				// .append(",u.user_id ")
				// from 表名
				.append(" from " + this.getTableName() + " t ")
				.append(" , classes_info c ,course_info cou")
				// .append(",user_info u" )
				// 链接条件
				.append(" WHERE 1=1 and  t.cla_id = c.cla_id and c.cou_id = cou.cou_id ");
		// 扩展 外加条件
		if (stuv != null) {//模糊查询
			if (StringUtils.isNotBlank(stuv.getStu_graduation())) {
				sql.append(" AND t.stu_graduation LIKE '%"
						+ stuv.getStu_graduation() + "%' ");
			}
			if (StringUtils.isNotBlank(stuv.getStu_name())) {
				sql.append(" AND t.stu_name LIKE '%" + stuv.getStu_name()
						+ "%' ");
			}
			if (StringUtils.isNotBlank(stuv.getCla_name())) {
				sql.append(" AND c.cla_name LIKE '%" + stuv.getCla_name()
						+ "%' ");
			}
			if (StringUtils.isNotBlank(stuv.getStu_state())) {
				sql.append(" AND t.stu_state LIKE '%" + stuv.getStu_state()
						+ "%' ");
			}
			
			if (StringUtils.isNotBlank(stuv.getCou_name())) {
				sql.append(" AND cou.cou_name LIKE '%" + stuv.getCou_name()
						+ "%' ");
			}
		}
		if (cla_starttime1 != null||cla_starttime1 !="") {
			if (StringUtils.isNotBlank(cla_starttime1)) {
				sql.append(" AND c.cla_starttime > to_date(' "+ cla_starttime1 + "','yyyy-MM-dd' ) ")
					.append(" or c.cla_starttime = to_date(' "+ cla_starttime1 + "','yyyy-MM-dd')  " );
				
			}
		}
		if (cla_starttime2 != null||cla_starttime2 !="") {
			if (StringUtils.isNotBlank(cla_starttime2)) {
				sql.append(" AND c.cla_starttime < to_date(' " + cla_starttime2 + "','yyyy-MM-dd')  ")
				.append(" or c.cla_starttime = to_date(' "+ cla_starttime2 + "','yyyy-MM-dd')  " );
			}
		}
		sql.append("order by t.stu_id desc");
		String s = getMysqlCurPageSql(sql.toString(), page);
		try {
			pstmt = connection.prepareStatement(s);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(this.rs2ObjectView(rs));
			}
		} finally {
			DBConnection.closeAll(rs, pstmt, connection);
		}

		return list;
	}

	/**
	 * 实现 审核 页面的  查询    由于 check 是  Long  如果是空  则封装时 赋值 0 与  已有状态冲突  需要单独的传入
	 * @param stuv
	 * @param cla_starttime1
	 * @param cla_starttime2
	 * @param stu_checkStr
	 * @param page
	 * @return 实现 审核 页面的  查询    由于 check 是  Long  如果是空  则封装时 赋值 0 与  已有状态冲突  需要单独的传入
	 * @throws SQLException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List query2(StudentView stuv, String cla_starttime1,
			String cla_starttime2,String stu_checkStr, int page) throws SQLException {
		List list = new ArrayList();
		Connection connection = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer sql = new StringBuffer();
		// student 部分
		sql.append("select t.stu_id,t.cla_id,t.stu_name,")
				.append("t.stu_sex,t.stu_native,t.stu_address,")
				.append("t.stu_email,t.stu_phone,t.stu_graduation,")
				.append("t.stu_major,t.stu_into_time,t.stu_state,")
				.append("t.stu_check,t.stu_note,t.stu_adder,t.stu_addtime,t.stu_delete ,")
				// class 部分
				.append("c.cla_name ,c.cla_starttime, ")
				// course 部分
				.append(" cou.cou_name ")
				// user 部分
				// .append(",u.user_id ")
				// from 表名
				.append(" from " + this.getTableName() + " t ")
				.append(" ,classes_info c ,course_info cou")
				// .append(",user_info u" )
				// 链接条件
				.append(" WHERE 1=1 and  t.cla_id = c.cla_id and c.cou_id = cou.cou_id ");
		// 扩展 外加条件
		if (stuv != null) {
			if (StringUtils.isNotBlank(stuv.getStu_graduation())) {
				sql.append(" AND t.stu_graduation LIKE '%"
						+ stuv.getStu_graduation() + "%' ");
			}
			if (StringUtils.isNotBlank(stuv.getStu_name())) {
				sql.append(" AND t.stu_name LIKE '%" + stuv.getStu_name()
						+ "%' ");
			}
			if (StringUtils.isNotBlank(stuv.getCla_name())) {
				sql.append(" AND c.cla_name LIKE '%" + stuv.getCla_name()
						+ "%' ");
			}
			
			if(stu_checkStr==null|| stu_checkStr.equals("")){
				sql.append(" AND t.stu_check LIKE '%" + stu_checkStr
						+ "%' ");
			}else{
				sql.append(" AND t.stu_check LIKE '%" + stu_checkStr
							+ "%' ");
			}
			
			
			if (StringUtils.isNotBlank(stuv.getCou_name())) {
				sql.append(" AND cou.cou_name LIKE '%" + stuv.getCou_name()
						+ "%' ");
			}
		}
		if (cla_starttime1 != null||cla_starttime1 !="") {
			if (StringUtils.isNotBlank(cla_starttime1)) {
				sql.append(" AND c.cla_starttime > to_date(' "+ cla_starttime1 + "','yyyy-MM-dd' ) ")
					.append(" or c.cla_starttime = to_date(' "+ cla_starttime1 + "','yyyy-MM-dd')  " );
				
			}
		}
		if (cla_starttime2 != null||cla_starttime2 !="") {
			if (StringUtils.isNotBlank(cla_starttime2)) {
				sql.append(" AND c.cla_starttime < to_date(' " + cla_starttime2 + "','yyyy-MM-dd')  ")
				.append(" or c.cla_starttime = to_date(' "+ cla_starttime2 + "','yyyy-MM-dd')  " );
			}
		}
		sql.append("order by t.stu_id desc");
		String s = getMysqlCurPageSql(sql.toString(), page);
		try {
			pstmt = connection.prepareStatement(s);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(this.rs2ObjectView(rs));
			}
		} finally {
			DBConnection.closeAll(rs, pstmt, connection);
		}

		return list;
	}
	/***
	 * 得到数据的总数 为了分页 和跳转
	 * @param stuv
	 * @return 得到数据的总数 为了分页 和跳转
	 * @throws SQLException
	 */
	public int getCount(StudentView stuv, String cla_starttime1,
			String cla_starttime2) throws SQLException {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int count = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) ")
				// from 表名
				.append(" from " + this.getTableName() + " t ")
				.append(" , classes_info c ")
				// 链接条件
				.append(" WHERE 1=1 and t.cla_id = c.cla_id ");
		// 扩展 外加条件
		if (stuv != null) {
			if (StringUtils.isNotBlank(stuv.getStu_graduation())) {
				sql.append(" AND t.stu_graduation LIKE '%"
						+ stuv.getStu_graduation() + "%' ");
			}
			if (StringUtils.isNotBlank(stuv.getStu_name())) {
				sql.append(" AND t.stu_name LIKE '%" + stuv.getStu_name()
						+ "%' ");
			}
			if (StringUtils.isNotBlank(stuv.getCla_name())) {
				sql.append(" AND c.cla_name LIKE '%" + stuv.getCla_name()
						+ "%' ");
			}
			if (StringUtils.isNotBlank(stuv.getStu_state())) {
				sql.append(" AND t.stu_state LIKE '%" + stuv.getStu_state()
						+ "%' ");
			}
			try {
				boolean flag = StringUtils.isNotBlank(stuv.getStu_check().toString());
				if (flag) {
					sql.append(" AND t.stu_check LIKE '%"+stuv.getStu_check().toString()+"%' ");
				}
			} catch (Exception e) {
				
					sql.append(" AND t.stu_check LIKE '%%' ");
				
			}
			
		}
		if (cla_starttime1 != null||cla_starttime1 !="") {
			if (StringUtils.isNotBlank(cla_starttime1)) {
				sql.append(" AND c.cla_starttime > to_date(' "+ cla_starttime1 + "','yyyy-MM-dd' ) ")
					.append(" or c.cla_starttime = to_date(' "+ cla_starttime1 + "','yyyy-MM-dd')  " );
				
			}
		}
		if (cla_starttime2 != null||cla_starttime2 !="") {
			if (StringUtils.isNotBlank(cla_starttime2)) {
				sql.append(" AND c.cla_starttime < to_date(' " + cla_starttime2 + "','yyyy-MM-dd')  ")
				.append(" or c.cla_starttime = to_date(' "+ cla_starttime2 + "','yyyy-MM-dd')  " );
			}
		}
		try {
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} finally {
			DBConnection.closeAll(rs, pstmt, conn);
		}

		return count;
	}

	// 为其他模块而 编写的 查询模块

	/**
	 * 修改学员的 审核状态
	 * @param stu_id
	 * @param stu_check
	 * @return  修改学员的 审核状态
	 * @throws SQLException
	 */
	public int checkStudent(StudentView stuv) throws SQLException {
		// 连接数据库
		Connection conn = DBConnection.getConnection();
		PreparedStatement prestmt = null;// 预处理
		int num = 0;// 修改的行数
		StringBuffer sb = new StringBuffer();
		// sql语句
		String sql = sb
				.append("update " + this.getTableName()
						+ " set ")
				.append("stu_check = ? where stu_id = ?")
				.toString();
		// 对 sql 进行预处理 插入
		try {
			prestmt = conn.prepareStatement(sql);
			// 替换？中的值
			int i = 1;
			prestmt.setLong(i++, stuv.getStu_check());	
			prestmt.setLong(i++, stuv.getStu_id());
			num = prestmt.executeUpdate();
		} finally {
			DBConnection.closeAll(prestmt, conn);
		}

		return num;
	} 
	
	/*
	 * 以下模块 是为了 服务  表单页面的直接查询  或 其他模块 关于 student 模块的查询使用
	 */
	
	
	/**
	 * 返回 毕业院校
	 * @return   返回 毕业院校
	 * @throws SQLException
	 */
	@SuppressWarnings({  "unchecked", "rawtypes" })
	public List getByType() throws SQLException{
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List list =new ArrayList();
		String sql = "select distinct stu_graduation from "+this.getTableName();
		try {
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){ 
				Student stu = new Student();
				stu.setStu_graduation(rs.getString(1)); 
				list.add(stu);
			}
		} finally{
			DBConnection.closeAll(rs, pstmt, conn);
		}
		return list;
	}

	/**
	 * 返回 代课老师
	 * @return 返回 代课老师
	 * @throws SQLException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getTeacherName() throws SQLException {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List list =new ArrayList();
		String sql = "select distinct tea_name from teacher_info ";
		try {
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){ 
				Teacher tea = new Teacher();
				tea.setTea_name(rs.getString(1)); 
				list.add(tea);
			}
		} finally{
			DBConnection.closeAll(rs, pstmt, conn);	
		}
		return list;
	}

	/**
	 * 返回 课程
	 * @return 返回 课程
	 * @throws SQLException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getCourse() throws SQLException {
		Connection conn = DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List list =new ArrayList();
		String sql = "select distinct cou_name from course_info ";
		try {
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){ 
				Course cou = new Course();
				cou.setCou_name(rs.getString(1));
				list.add(cou);
			}
		} finally{
			DBConnection.closeAll(rs, pstmt, conn);	
		}
		return list;
	}

	/**
	 * 得到   stu_id 和  stu_name 的集合
	 * @return 得到   stu_id 和  stu_name 的 绑定值  通过 选择 姓名 而传入  stu_id
	 * @throws SQLException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getName() throws SQLException {
		Connection conn =DBConnection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List selectList = new ArrayList();

		String sql= "select stu_id, stu_name from "+this.getTableName();
		try {
			pstmt= conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				StudentView stu = new StudentView();
				stu.setStu_id(rs.getLong("stu_id"));
				stu.setStu_name(rs.getString("stu_name"));
				selectList.add(stu);
			}
		} finally{
			DBConnection.closeAll(rs, pstmt, conn);
		}
		return selectList;
	}
}
