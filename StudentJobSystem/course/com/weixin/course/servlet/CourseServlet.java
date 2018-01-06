package com.weixin.course.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weixin.course.form.Course;
import com.weixin.course.service.CourseService;
import com.weixin.course.utils.PageUtil;
import com.weixin.course.utils.RequestUtil;
import com.yyy.student.view.StudentView;
/**
 * 课程管理servlet层
 */
@SuppressWarnings("unused")
public class CourseServlet extends RequestUtil {

	/**
	 * Constructor of the object.
	 */
	public CourseServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	private static final long serialVersionUID = 1L;
	
	private static final CourseService service = new CourseService();
	
	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String method = request.getParameter("method");
		if ("edit".equals(method)) { // 编辑
			this.edit(request, response);
		}
		if ("dele".equals(method)) { // 删除
			this.delete(request, response);
		}
		if ("save".equals(method)) { // 保存
			this.save(request, response);
		}
		if ("query".equals(method)) { // 查询
			this.query(request, response);
		}
		if ("view".equals(method)) { // 查看详情
			this.view(request, response);
		}
		out.flush();
		out.close();
	}
	/**
	 * 详情页面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Long cou_id = getLong(request, "cou_id");
		
		if(cou_id != null &&cou_id.longValue()>0){
			Course cou = new Course();
			try {
				cou = service.getById(cou_id);
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("errorMsg", e.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			}
			request.setAttribute("cou", cou);
		}
		request.getRequestDispatcher("course/courseView.jsp").forward(
				request, response);
	}

	/**
	 * 编辑
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Long courseId = getLong(request, "courseId");
		if (courseId != null && courseId.longValue() > 0) {
			Course course = new Course();
			
			try {
				course = service.getById(courseId);
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("errorMsg", e.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,response);
			}
			request.setAttribute("course", course);
		}
		
		request.getRequestDispatcher("course/courseEdit.jsp").forward(request, response);
	}

	/**
	 * 删除
	 * @param request
	 * @param response
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		Long courseId = getLong(request, "courseId");
		if (courseId != null && courseId.longValue() > 0) {
			try {
				service.delete(courseId);
			} catch (SQLException e) {
				request.setAttribute("errorMsg", e.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			}
			this.query(request, response);
		} else {
			out.println("<script language='javascript'>alert('删除失败！');window.location.href='CourseServlet?method=query';</script>");
		}
	}

	/**
	 * 保存
	 * @param request
	 * @param response
	 */
	private void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Long courseId = getLong(request, "courseId");
		String courseName = getString(request, "courseName");
		String courseNote = getString(request, "courseNote");
		String courseLogo = getString(request, "courseLogo");
		String courseAdder = getString(request, "courseAdder");
		String courseDate = getString(request, "courseDate");
		int courseDelete = getInt(request, "courseDelete");

		Course course = new Course();
		course.setCou_name(courseName);
		course.setCou_note(courseNote);
		course.setCou_logo(courseLogo);
		course.setCou_adder(courseAdder);
		course.setCou_date(courseDate);
		course.setCou_delete(courseDelete);
		if (courseId != null && courseId.longValue() > 0) {
			course.setCou_id(courseId);
		}
		long flag = 0;
		try {
			flag = service.insertOrUpdate(course);
			if (flag > 0) {
				out.println("<script language='javascript'>alert('更新成功！');window.location.href='CourseServlet?method=query';</script>");
			} else {
				out.println("<script language='javascript'>alert('更新失败！');window.location.href='CourseServlet?method=query';</script>");
			}
		} catch (SQLException e) {
			//e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		}
	}

	/**
	 * 查询
	 * @param request
	 * @param response
	 */
	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String courseName = getString(request, "courseName");
		Course course = new Course();
		course.setCou_name(courseName);
		int page = getInt(request, "pageIndex", 1);
		@SuppressWarnings("rawtypes")
		List courseList = new ArrayList();
		try {
			courseList = service.query(course, page);
			int totalNum = service.getCount(course);
			int totalPage = PageUtil.getTotalPage(totalNum);
			int currentPage = page;

			request.setAttribute("courseName", courseName);
			request.setAttribute("list", courseList);
			request.setAttribute("totalNum", totalNum);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("currentPage", currentPage);
			request.getRequestDispatcher("course/courseList.jsp")
					.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
