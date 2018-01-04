package com.weixin.classes.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weixin.classes.service.ClassesService;
import com.weixin.classes.view.ClassesView;
import com.weixin.course.form.Course;
import com.weixin.utils.BaseRequestUtil;
import com.weixin.utils.PageUtil;
/**
 * 班级信息servlet层
 */
public class ClassesServlet extends BaseRequestUtil  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final ClassesService service = new ClassesService();
	
	/**
	 * Constructor of the object.
	 */
	public ClassesServlet() {
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
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String method = request.getParameter("method");
		if ("edit".equals(method)) {	 	// 编辑
			this.edit(request, response);
		}
		if ("dele".equals(method)) { 		// 删除
			this.delete(request, response);
		}
		if ("save".equals(method)) {		// 保存
			this.save(request, response);
		}
		if ("query".equals(method)) { 		// 查询
			this.query(request, response);
		}
		if ("view".equals(method)) { 		// 查看详情
			this.view(request, response);
		}
		out.flush();
		out.close();
	}
	/**
	 * 班级详情
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long cla_id = getLong(request,"cla_id");
		if(cla_id != null &&cla_id.longValue()>0){
			ClassesView cla = new ClassesView();
			try {
				cla = service.getById(cla_id);
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("errorMsg", e.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			}
			request.setAttribute("cla", cla);
		}
		request.getRequestDispatcher("classes/classesView.jsp").forward(
				request, response);
	}

	/**
	 * 编辑
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Long cla_id = getLong(request, "cla_id");
		if(cla_id != null &&cla_id.longValue()>0){
			ClassesView cla = new ClassesView();
			try {
				cla = service.getById(cla_id);
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("errorMsg", e.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,response);
				return;
			}
			request.setAttribute("cla", cla);
		}
		
		try {
			List select_cou_idList = service.getSelectCourse();
			request.setAttribute("select_cou_idList", select_cou_idList);
			
			List select_tea_idList = service.getSelectTeacher();
			request.setAttribute("select_tea_idList", select_tea_idList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("classes/classesEdit.jsp").forward(
				request, response);
		return;
	}

	/**
	 * 删除
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		PrintWriter out = response.getWriter();

		Long cla_id =getLong(request, "cla_id");
		if (cla_id != null && cla_id.longValue() > 0) {
			try {
				service.delete(cla_id);
			} catch (SQLException e) {
				request.setAttribute("errorMsg", e.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			}
			this.query(request, response);
		} else {
			out.println("<script language='javascript'>alert('删除失败！');window.location.href='ClassesServlet?method=query';</script>");
		}
	}

	/**
	 * 保存
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void save(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		PrintWriter out = response.getWriter();
		
		Long cla_id = getLong(request, "cla_id");
		Long cou_id = getLong(request,"cou_id");
		Long tea_id = getLong(request,"tea_id");
		String cla_name = getString(request,"cla_name");
		String cla_nature = getString(request,"cla_nature");
		int cla_num = getInt(request,"cla_num");
		String cla_starttime = getString(request,"cla_starttime");
		String cla_note = getString(request,"cla_note");
		String cla_adder = getString(request,"cla_adder");
		String cla_addtime = getString(request,"cla_addtime");
		int cla_delete = getInt(request,"cla_delete");
		
		ClassesView cv = new ClassesView();
		cv.setCla_id(cla_id);
		cv.setCou_id(cou_id);
		cv.setTea_id(tea_id);
		cv.setCla_name(cla_name);
		cv.setCla_nature(cla_nature);
		cv.setCla_num(cla_num);
		cv.setCla_starttime(cla_starttime);
		cv.setCla_note(cla_note);
		cv.setCla_adder(cla_adder);
		cv.setCla_addtime(cla_addtime);
		cv.setCla_delete(cla_delete);
		
		if (cla_id != null && cla_id.longValue() > 0) {
			cv.setCla_id(cla_id);
		}
		long flag = 0;
		try {
			flag = service.insertOrUpdate(cv);
			if (flag > 0) {
				out.println("<script language='javascript'>alert('更新成功！');window.location.href='ClassesServlet?method=query';</script>");
			} else {
				out.println("<script language='javascript'>alert('更新失败！');window.location.href='ClassesServlet?method=query';</script>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		}
		
	}

	/**
	 * 查询
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cou_name = getString(request, "cou_name");
		String tea_name = getString(request, "tea_name");
		String cla_nature = getString(request, "cla_nature");
		String cla_name = getString(request, "cla_name");
		String cla_starttime1 = getString(request, "cla_starttime1");
		String cla_starttime2 = getString(request, "cla_starttime2");
		
		ClassesView cv = new ClassesView();
		cv.setCla_name(cla_name);
		cv.setCla_nature(cla_nature);
		cv.setCou_name(cou_name);
		cv.setTea_name(tea_name);
		int page = getInt(request, "pageIndex", 1);
		@SuppressWarnings("rawtypes")
		List claList = new ArrayList();
		try {
			claList = service.query(cv,cou_name,tea_name,cla_starttime1,cla_starttime2,page);
			int totalNum = service.getCount(cv,cou_name,tea_name,cla_starttime1,cla_starttime2);
			int totalPage = PageUtil.getTotalPage(totalNum);
			int currentPage = page;

			request.setAttribute("claList", claList);
			request.setAttribute("totalNum", totalNum);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("cou_name", cou_name);
			request.setAttribute("tea_name", tea_name);
			request.setAttribute("cla_nature", cla_nature);
			request.setAttribute("cla_name", cla_name);
			request.setAttribute("cla_starttime1", cla_starttime1);
			request.setAttribute("cla_starttime2", cla_starttime2);
			request.getRequestDispatcher("classes/classesList.jsp")
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
