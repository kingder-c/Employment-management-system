package com.weixin.student.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weixin.student.service.StudentService;
import com.weixin.student.view.StudentView;
import com.weixin.utils.BaseRequestUtil;
import com.weixin.utils.PageUtil;
/**
 * student的Servlet
 */
public class StudentServlet extends BaseRequestUtil {

	/**
	 * UUID
	 */
	private static final long serialVersionUID = 1L;

	private static final StudentService service = new StudentService();
	/**
	 * Constructor of the object.
	 */
	public StudentServlet() {
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

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String method = request.getParameter("method");
		if ("edit".equals(method)) { // 编辑 //插入或 更新
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
		if ("check".equals(method)) { // 审核查询
			this.check(request, response);
		}
		if ("checkUp".equals(method)) { // 审核修改（保存）
			this.checkUp(request, response);
		}
		if ("view".equals(method)) { // 查看学生的具体信息
			this.view(request, response);
		}
		if ("selectStudent".equals(method)){//其他模块使用查询页面
			this.query(request, response);
		}
		if ("selectStudent2".equals(method)){//其他模块使用查询页面
			this.query(request, response);
		}
		if ("getStudentByAjax".equals(method)){//其他模块使用查询页面
			this.getStudentByAjax(request, response);
		}
		
		out.flush();
		out.close();
	}

	/**
	 * @注释  选择学生的回调函数
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void getStudentByAjax(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Long stu_id = getLong(request, "stu_id");
		String s = "####";
		if(stu_id != null &&stu_id.longValue()>0){
			StudentView stuv = new StudentView();
			try {
				stuv = service.getById(stu_id);
				s = stu_id+"####"+stuv.getStu_name()+"####"+20;
			} catch (SQLException e) {
				request.setAttribute("errorMsg", e.getMessage());
				s = s+e.getMessage();
			}
			
		}
		//System.out.println(s);
		PrintWriter pw = response.getWriter();
		pw.write(s);
		
	}

	/**
	 *  点击  名字 后 返回 详细的 学生页面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	private void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long stu_id = getLong(request, "stu_id");
		
		try {
			List select_stu_sex = service.getSelectData("性别");
			request.setAttribute("select_stu_sex", select_stu_sex);
			
			
			List select_stu_check = service.getSelectData("审核状态");
			request.setAttribute("select_stu_check", select_stu_check);
			
			List select_cla_idList = service.getSelectClasses();
			request.setAttribute("select_cla_idList", select_cla_idList);
			
			
		} catch (SQLException e) {
			//System.out.println("SQL错误");
			//e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request,
					response);
		} 
		
		
		if(stu_id != null &&stu_id.longValue()>0){
			StudentView stuv = new StudentView();
			try {
				stuv = service.getById(stu_id);
			} catch (SQLException e) {
				//e.printStackTrace();
				request.setAttribute("errorMsg", e.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			}
			request.setAttribute("stuv", stuv);
		}
		request.getRequestDispatcher("student/studentView2.jsp").forward(
				request, response);
		
	}

	/**
	 *  这个是审核页面的提交
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	@SuppressWarnings("unused")
	private void checkUp(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		Long stu_id =getLong(request, "stu_id");
		
	    Long stu_check =getLong(request,"stu_check");
	    StudentView stuv = new StudentView();
		stuv.setStu_check(stu_check);
		if (stu_id != null && stu_id.longValue() > 0) {
			stuv.setStu_id(stu_id);
		}
		long flag = 0;
		try {
			flag = service.checkUpdate(stuv);
			if(flag>0){
				request.getRequestDispatcher("StudentServlet?method=check").forward(
						request, response);
			}else{
				//System.out.println("ERROR");
				request.getRequestDispatcher("StudentServlet?method=check").forward(
						request, response);
			}
			
		} catch (SQLException e) {
			//e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		}
		
		
	}

	/**
	 *  这个是 审核页面的查询
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	private void check(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到查询输入的条件	
				String stu_name = getString(request, "stu_name");
				String stu_graduation = getString(request, "stu_graduation");
				String cla_name = getString(request, "cla_name");
			/*	String cla_master = getString(request, "cla_master");*/
				String cou_name = getString(request, "cou_name");
				String stu_checkStr = getString(request, "stu_check");
				
				String cla_starttime1 = getString(request, "cla_starttime1");
				String cla_starttime2 = getString(request, "cla_starttime2");
				StudentView stuv = new StudentView();
				stuv.setStu_graduation(stu_graduation);
				stuv.setStu_name(stu_name);
				stuv.setCla_name(cla_name);
				stuv.setCou_name(cou_name);
				
				try {
					List select_stu_sex = service.getSelectData("性别");
					request.setAttribute("select_stu_sex", select_stu_sex);
					
					
					List select_stu_check = service.getSelectData("审核状态");
					request.setAttribute("select_stu_check", select_stu_check);
					
					List select_cla_idList = service.getSelectClasses();
					request.setAttribute("select_cla_idList", select_cla_idList);
					
					List select_stu_graduation = service.getSelectStudent();
					request.setAttribute("select_stu_graduation", select_stu_graduation);
					
					List select_tea_name = service.getSelectTeacher("select_tea_name");
					request.setAttribute("select_tea_name", select_tea_name);
					
					List select_cou_name = service.getSelectCourse();
					request.setAttribute("select_cou_name", select_cou_name);
					
				} catch (SQLException e) {
					//System.out.println("SQL错误");
					//e.printStackTrace();
					request.setAttribute("errorMsg", e.getMessage());
					request.getRequestDispatcher("error.jsp").forward(request,
							response);
				} 
				
				
				
				int page = getInt(request, "pageIndex", 1);
				List stuvList = new ArrayList();
				try {
					stuvList = service.query2(stuv,cla_starttime1,cla_starttime2,stu_checkStr, page);
					int totalNum = service.getCount(stuv,cla_starttime1,cla_starttime2);
					int totalPage = PageUtil.getTotalPage(totalNum);
					int currentPage = page;

					request.setAttribute("stuvList", stuvList);
					request.setAttribute("totalNum", totalNum);
					request.setAttribute("totalPage", totalPage);
					request.setAttribute("currentPage", currentPage);
					request.setAttribute("stu_name", stu_name);
					request.setAttribute("stu_graduation", stu_graduation);
					request.setAttribute("cla_name", cla_name);
					/*request.setAttribute("cla_master", cla_master);*/
					request.setAttribute("cou_name", cou_name);
					request.setAttribute("stu_check", stu_checkStr);
					request.setAttribute("cla_starttime1", cla_starttime1);
					request.setAttribute("cla_starttime2", cla_starttime2);
					request.getRequestDispatcher("student/studentList2.jsp")
							.forward(request, response);
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
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到查询输入的条件	
		String stu_name = getString(request, "stu_name");
		String stu_graduation = getString(request, "stu_graduation");
		String cla_name = getString(request, "cla_name");
		/*String cla_master = getString(request, "cla_master");*/
		String cou_name = getString(request, "cou_name");
		String stu_state = getString(request, "stu_state");
		String cla_starttime1 = getString(request, "cla_starttime1");
		String cla_starttime2 = getString(request, "cla_starttime2");
		StudentView stuv = new StudentView();
		stuv.setStu_graduation(stu_graduation);
		stuv.setStu_name(stu_name);
		stuv.setStu_state(stu_state);
		stuv.setCla_name(cla_name);
		stuv.setCou_name(cou_name);
		try {
			List select_stu_sex = service.getSelectData("性别");
			request.setAttribute("select_stu_sex", select_stu_sex);
			
			List select_stu_state = service.getSelectData("学员状态");
			request.setAttribute("select_stu_state", select_stu_state);
			
			List select_cla_idList = service.getSelectClasses();
			request.setAttribute("select_cla_idList", select_cla_idList);
			
			List select_stu_graduation = service.getSelectStudent();
			request.setAttribute("select_stu_graduation", select_stu_graduation);
			
			List select_tea_name = service.getSelectTeacher("select_tea_name");
			request.setAttribute("select_tea_name", select_tea_name);
			
			List select_cou_name = service.getSelectCourse();
			request.setAttribute("select_cou_name", select_cou_name);
			
		} catch (SQLException e) {
			//System.out.println("SQL错误");
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request,
					response);
		} 
		
		
		
		int page = getInt(request, "pageIndex", 1);
		List stuvList = new ArrayList();
		try {
			stuvList = service.query(stuv,cla_starttime1,cla_starttime2, page);
			int totalNum = service.getCount(stuv,cla_starttime1,cla_starttime2);
			int totalPage = PageUtil.getTotalPage(totalNum);
			int currentPage = page;

			request.setAttribute("stuvList", stuvList);
			request.setAttribute("totalNum", totalNum);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("stu_name", stu_name);
			request.setAttribute("stu_graduation", stu_graduation);
			request.setAttribute("cla_name", cla_name);
			/*request.setAttribute("cla_master", cla_master);*/
			request.setAttribute("cou_name", cou_name);
			request.setAttribute("stu_state", stu_state);
			request.setAttribute("cla_starttime1", cla_starttime1);
			request.setAttribute("cla_starttime2", cla_starttime2);
			
			String method = getString(request, "method");
			//System.out.println("Servlet/query: get method :"+method);
			if("query".equals(method)){
				request.setAttribute("method", method);
			}
			if("selectStudent".equals(method)){
				request.setAttribute("method", method);
			}
			if("selectStudent2".equals(method)){
				request.setAttribute("method", method);
			}
			request.getRequestDispatcher("student/studentList.jsp")
					.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		}
		
	}

	/**
	 * 保存修改
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Long stu_id =getLong(request, "stu_id");
		Long cla_id =getLong(request, "cla_id");
	    String stu_name = getString(request, "stu_name");
	    Long stu_sex = getLong(request, "stu_sex");
	    String stu_native =getString(request, "stu_native");
	    String stu_address =getString(request,"stu_address");
	    String stu_email =getString(request,"stu_email");
	    String stu_phone =getString(request,"stu_phone");
	    String stu_graduation =getString(request,"stu_graduation");
	    String stu_major =getString(request,"stu_major");
	    String stu_into_time =getString(request,"stu_into_time");
	    String stu_state =getString(request,"stu_state");
	    Long stu_check =getLong(request,"stu_check");
	    String stu_note =getString(request,"stu_note") ;
	    String stu_adder =getString(request,"stu_adder");
	    String stu_addtime =getString(request,"stu_addtime");
	    Long stu_delete =getLong(request, "stu_delete");
		

	    StudentView stuv = new StudentView();

		stuv.setStu_id(stu_id);
		stuv.setCla_id(cla_id);		
		stuv.setStu_name(stu_name);
		stuv.setStu_sex(stu_sex);
		stuv.setStu_native(stu_native);
		stuv.setStu_address(stu_address);
		stuv.setStu_email(stu_email);
		stuv.setStu_phone(stu_phone);
		stuv.setStu_graduation(stu_graduation);
		stuv.setStu_major(stu_major);
		stuv.setStu_into_time(stu_into_time);
		stuv.setStu_state(stu_state);
		stuv.setStu_check(stu_check);
		stuv.setStu_note(stu_note);
		stuv.setStu_adder(stu_adder);
		stuv.setStu_addtime(stu_addtime);
		stuv.setStu_delete(stu_delete);
		
		if (stu_id != null && stu_id.longValue() > 0) {
			stuv.setStu_id(stu_id);
		}
		long flag = 0;
		try {
			flag = service.insertOrUpdate(stuv);
			if (flag > 0) {
				out.println("<script language='javascript'>alert('更新成功！');window.location.href='StudentServlet?method=query';</script>");
			} else {
				out.println("<script language='javascript'>alert('更新失败！');window.location.href='StudentServlet?method=query';</script>");
			}
		}catch(SQLIntegrityConstraintViolationException e){
			request.setAttribute("errorMsg", "请添加相应班级");
		}
		catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		}
		
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

		Long stu_id =getLong(request, "stu_id");
		if (stu_id != null && stu_id.longValue() > 0) {
			try {
				service.delete(stu_id);
			} catch (SQLException e) {
				request.setAttribute("errorMsg", e.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			}
			this.query(request, response);
		} else {
			out.println("<script language='javascript'>alert('删除失败！');window.location.href='StudentServlet?method=query';</script>");
		}
		
	}

	/**
	 * 添加 或修改
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException 
	 */
	@SuppressWarnings("rawtypes")
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long stu_id = getLong(request, "stu_id");
		if(stu_id != null &&stu_id.longValue()>0){
			StudentView stuv = new StudentView();
			try {
				stuv = service.getById(stu_id);
			} catch (SQLException e) {
				//e.printStackTrace();
				request.setAttribute("errorMsg", e.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			}
			request.setAttribute("stuv", stuv);
		}
			try {
				List select_stu_sex;
				select_stu_sex = service.getSelectData("性别");
				request.setAttribute("select_stu_sex", select_stu_sex);
				
				List select_stu_state = service.getSelectData("学员状态");
				request.setAttribute("select_stu_state", select_stu_state);
				
				List select_stu_check = service.getSelectData("审核状态");
				request.setAttribute("select_stu_check", select_stu_check);
				
				List select_cla_idList = service.getSelectClasses();
				request.setAttribute("select_cla_idList", select_cla_idList);
			} catch (SQLException e) {
				//System.out.println("SQL错误");
				//e.printStackTrace();
				request.setAttribute("errorMsg", e.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			} 
		request.getRequestDispatcher("student/studentEdit.jsp").forward(
				request, response);
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
