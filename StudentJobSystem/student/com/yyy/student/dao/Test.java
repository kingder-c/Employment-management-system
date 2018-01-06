package com.yyy.student.dao;

import java.sql.SQLException;

public class Test {
  public static void main(String[] args) throws SQLException {
	  StudentDao d= new StudentDao();
	  d.deleteStudent(111L);
  }
}
