package com.yyy.teacher.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yyy.teacher.form.Teacher;
import com.yyy.teacher.service.TeacherService;
import com.yyy.utils.BaseRequestUtil;
import com.yyy.utils.PageUtil;

public class TeacherServlet extends BaseRequestUtil {

	/**
	 * 序列
	 */
	private static final long serialVersionUID = 1L;
	private static final TeacherService service = new TeacherService();
	/**
	 * Constructor of the object.
	 */
	public TeacherServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

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

		this.doPost(request, response);
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
		response.setContentType("text/html; charset=utf-8");
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
		if ("view".equals(method)) { // 查询
			this.view(request, response);
		}
		out.flush();
		out.close();
		
	}
	
	@SuppressWarnings("rawtypes")
	private void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long tea_id = getLong(request, "tea_id");
		if(tea_id != null &&tea_id.longValue()>0){
			Teacher teacher = new Teacher();
		
			try {
				teacher = service.getById(tea_id);
				
				List select_com_status = service.getSelectData("合作企业状态");
				request.setAttribute("select_com_status", select_com_status);

//				request.setAttribute("com_status", com_status);
				request.setAttribute("tea", teacher);
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("errorMsg", e.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			}
		}
		
		request.getRequestDispatcher("teacher/teacherView.jsp").forward(
				request, response);
		
	}
	
	/**
	 * 编辑
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long  tea_id= getLong(request, "tea_id");
		if (tea_id != null && tea_id.longValue() > 0) {
			Teacher teacher = new Teacher();
			try {
				teacher = service.getById(tea_id);
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("errorMsg", e.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			}
			request.setAttribute("teacher", teacher);
		}
		request.getRequestDispatcher("teacher/teacherEdit.jsp").forward(
				request, response);

	}

	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		Long tea_id = getLong(request, "tea_id");
		if (tea_id != null && tea_id.longValue() > 0) {
			try {
				service.delete(tea_id);
			} catch (SQLException e) {
				request.setAttribute("errorMsg","该老师已有班级！！！ 有关联");
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			}
			this.query(request, response);
		} else {
			out.println("<script language='javascript'>alert('删除失败！');window.location.href='TeacherServlet?method=query';</script>");
		}
	}

	public void save(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Long tea_id = getLong(request, "tea_id");
		String tea_name = getString(request, "tea_name");
		String tea_state = getString(request, "tea_state");
		String tea_tel = getString(request, "tea_tel");

		Teacher teacher = new Teacher();
		teacher.setTea_id(tea_id);
		teacher.setTea_name(tea_name);
		teacher.setTea_state(tea_state);
		teacher.setTea_tel(tea_tel);
		if (tea_id != null &&tea_id.longValue() > 0) {
			teacher.setTea_id(tea_id);
		}
		long flag = 0;
		try {
			flag = service.insertOrUpdate(teacher);
			if (flag > 0) {
				out.println("<script language='javascript'>alert('更新成功！');window.location.href='TeacherServlet?method=query';</script>");
			} else {
				out.println("<script language='javascript'>alert('更新失败！');window.location.href='TeacherServlet?method=query';</script>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		}

	}

	@SuppressWarnings("rawtypes")
	public void query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tea_name = getString(request, "tea_name");
		String tea_state = getString(request, "tea_state");
		String tea_tel = getString(request, "tea_tel");
		/*String tea_mail = getString(request, "tea_mail");*/
		Teacher teacher = new Teacher();
		teacher.setTea_name(tea_name);
		teacher.setTea_state(tea_state);
		teacher.setTea_tel(tea_tel);
		int page = getInt(request, "pageIndex", 1);
		List teacherList = new ArrayList();
		try {
			teacherList = service.query(teacher, page);
			int totalNum = service.getCount(teacher);
			int totalPage = PageUtil.getTotalPage(totalNum);
			int currentPage = page;
		

			request.setAttribute("tea_name", tea_name);
			request.setAttribute("select_tea_state", tea_state);
			request.setAttribute("tea_tel", tea_tel);
			request.setAttribute("list", teacherList);
			request.setAttribute("totalNum", totalNum);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("currentPage", currentPage);
			request.getRequestDispatcher("teacher/teacherList.jsp")
					.forward(request, response);
		} catch (SQLException e) {
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
