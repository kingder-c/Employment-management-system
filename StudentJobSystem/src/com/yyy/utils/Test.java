package com.yyy.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.yyy.student.dao.StudentDao;
import com.yyy.student.view.StudentView;
import com.yyy.utils.DBConnection;

/**
 * 测试类
 */
public class Test {
	/**
	 * 程序入口
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException
	{
		Connection con = DBConnection.getConnection();
		System.out.println(con);
		
		
		
		StudentDao  stuDao = new StudentDao();
		
		StudentView stuv = new StudentView();
		//stuv.setStu_id(3L);
		stuv.setCla_id(100L);
		stuv.setStu_name("djm_");
		stuv.setStu_sex(0L);
		stuv.setStu_native("山西");
		stuv.setStu_address("中北");
		stuv.setStu_email("www.444859418@qq.com");
		stuv.setStu_phone("18435185817");

		stuv.setStu_graduation("本科");
		stuv.setStu_major("软件");
		stuv.setStu_into_time("2013-09-01");
		stuv.setStu_state("在读");
		stuv.setStu_check(0L);
		stuv.setStu_note("开发与测试........note");
		stuv.setStu_adder("admin_djm");
		stuv.setStu_addtime("2016-09-12");
		stuv.setStu_delete(0L);
		
		
		//stuDao.addStudent(stuv);
		
		long flag = stuDao.addStudent(stuv);
		System.out.println(flag);
		
		flag = stuDao.deleteStudent(1005L);
		System.out.println(flag);
		
		flag =stuDao.updateStudent(stuv);
		System.out.println(flag);
		
		
	}

}
