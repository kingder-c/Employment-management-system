package com.weixin.user.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.weixin.company.form.Company;
import com.weixin.company.service.CompanyService;
import com.weixin.data.service.DataService;
import com.weixin.role.form.Role;
import com.weixin.role.service.RoleService;
import com.weixin.user.dao.UserDao;
import com.weixin.user.form.User;
import com.weixin.user.service.UserService;
import com.yyy.student.service.StudentService;
import com.yyy.student.view.StudentView;
import com.yyy.teacher.form.Teacher;
import com.yyy.teacher.service.TeacherService;
import com.yyy.utils.BaseRequestUtil;
import com.yyy.utils.PageUtil;

/**
 *  处理关于user的控制中心
 */
public class UserServlet extends BaseRequestUtil {

	/**
	 * UUID
	 */
	private static final long serialVersionUID = 1L;
	private static final UserService service = new UserService();//设置成静态类 直接可以用 类名.方法名() 的方式调用
	private static final RoleService roleservice = new RoleService();
	private static final DataService dataservice = new DataService();
	private static final StudentService studentservice = new StudentService();
	private static final CompanyService companyservice = new CompanyService();
	private static final TeacherService teacherservice = new TeacherService();
	/**
	 * Constructor of the object.
	 */
	public UserServlet() {
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

		doPost(request, response);
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

		request.setCharacterEncoding("utf-8");      //汉字转码
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String method = request.getParameter("method");
		if ("edit".equals(method)) { // 编辑
			this.edit(request, response);
		}
		if("addStudent".equals(method)){
			this.addStudent(request, response);
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
		if("login".equals(method)){//登录
			this.login(request, response);
			
		}
		if("clean".equals(method)){//退出登录
			this.clean(request,response);
		}
		if("tiaozhuan".equals(method)){
			this.tiaozhuan(request,response);
		}
		if("register".equals(method)){//注册
			this.register(request, response);
			
		}
		
		out.flush();
		out.close();
	}

	/**
	 * 添加学生用户
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	private void addStudent(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Long stu_id = getLong(request, "stu_id");
		
		try {
			List select_user_check = dataservice.getByType("审核状态");
			request.setAttribute("select_user_check", select_user_check);
			
			
			List select_user_Type = service.getUserType();
			request.setAttribute("select_user_Type", select_user_Type);
			
			
			//用户类型
		} catch (SQLException e) {
			//System.out.println("SQL错误");
			//e.printStackTrace();
			request.setAttribute("errorMsg", "SQL错误"+e.getMessage());
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		} 
		request.setAttribute("stu_id", stu_id);
		request.getRequestDispatcher("user/userStudentEdit.jsp").forward(
				request, response);

		
		
	}

	/**
	 * 新用户的注册
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 * @throws SQLException 
	 */
	private void register(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		String user_name = request.getParameter("user_name");
		String user_password = request.getParameter("user_password");
		String user_typestr = request.getParameter("user_type");
		String user_desc = request.getParameter("user_desc");
		
		Long user_type = Long.parseLong(user_typestr);
		String user_checkstatustr = request.getParameter("user_checkstatus");
		Integer user_checkstatus = Integer.parseInt(user_checkstatustr);
		User user = new User();
		user.setUser_name(user_name);
		user.setUser_password(user_password);
		user.setUser_type(user_type);
		user.setUser_checkstatus(user_checkstatus);
		
		user.setUser_desc(user_desc);
		
		
		boolean flag = service.checkUserIsExist(user_name);
		if(flag){
			request.setAttribute("message","数据已经存在");
			response.sendRedirect("register.jsp");//用response返回到页面。然后页面用request获取这个message
		}else{
			UserDao ud = new UserDao();
		    try {
				ud.insert(user);
			} catch (SQLException e) {
				//e.printStackTrace();
				request.setAttribute("errorMsg", "SQL错误"+e.getMessage());
				request.getRequestDispatcher("error.jsp")
						.forward(request, response);
			}
		    response.sendRedirect("login.html");
		}
	}

	/**
	 * 执行前台页面的跳转验证
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void tiaozhuan(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		HttpSession  session =request.getSession();
		User user=(User) session.getAttribute("user");
		//Role role = (Role)session.getAttribute("role");
		String URL = getString(request, "URL");
		String tea_idstr = getString(request, "tea_id");
		long stu_id = getLong(request, "stu_id");
		if(tea_idstr !=null &&tea_idstr.length()>0){
			URL = URL+"&tea_id="+tea_idstr;
		}
		String com_idstr = getString(request, "com_id");
		if(com_idstr !=null &&com_idstr.length()>0){
			URL = URL+"&com_id="+com_idstr;
		}
		if(user==null){
			session.setAttribute("errorMsg","没有登录，请登录！！" );
			response.sendRedirect("error_login.jsp");
		}if(stu_id != 0){
			response.sendRedirect(URL+"&stu_id="+stu_id);
		}
		else{
			response.sendRedirect(URL);
		}
	}
	/**
	 * 清除session
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void clean(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession  session =request.getSession();
		User user=(User) session.getAttribute("user");
		if(user!=null){
			session.removeAttribute("user");
			session.removeAttribute("role");
			session.removeAttribute("company");
			session.removeAttribute("stuv");
			session.removeAttribute("select_stu_sex");
			session.removeAttribute("select_stu_check");
			session.removeAttribute("select_cla_idList");
			
		}
		response.sendRedirect("index.jsp");
	}

	/**
	 * 登录验证
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_name = getString(request, "user_name");
		String user_password = getString(request, "user_password");
		long user_type = getLong(request,"user_type");
		
		User user = null;
		Role role = null;
		StudentView student =null;
		Company company = null;
		Teacher teacher=null;
		Long user_desc=null;
		if("".equals(user_name)){
			response.sendRedirect("login.html");       //跳转到登陆页面 respnse是跳转
			return;
		}
		try {
			user = service.getByName(user_name);
			role = roleservice.getById(user.getUser_type());
			
			user_desc=Long.parseLong(user.getUser_desc());
		} catch (SQLException e) {
			//System.out.println("SQL错误");
			//e.printStackTrace();
			response.sendRedirect("login.html");
			return;
		
		} catch (Exception e) {
			request.setAttribute("errorMsg", "没有这个用户 ，请先注册！！");
			
			//request.getRequestDispatcher("error_login.jsp").forward(request, response);
			response.sendRedirect("error_login.jsp");
			return;
		}
		try {
			if(user.getUser_type()==20L){
				student = studentservice.getById(user_desc);
			}
			if(user.getUser_type()==22L){
				company = companyservice.getById(user_desc);
			}
			if(user.getUser_type()==28L){
				teacher = teacherservice.getById(user_desc);
			}
		} catch (NumberFormatException e1) {
			response.sendRedirect("login.html");
			return;
		} catch (SQLException e) {
			response.sendRedirect("login.html");
			return;
		}
		if(user_password.equals(user.getUser_password())){         //存到session里面的数据 所有的页面都可以调用
			HttpSession  session =request.getSession();
			session.setAttribute("user", user);
			session.setAttribute("role", role);
			session.setAttribute("stuv", student);
			session.setAttribute("company", company);
			session.setAttribute("teacher", company);
			try {
				List select_stu_sex = studentservice.getSelectData("性别");
				session.setAttribute("select_stu_sex", select_stu_sex);
				
				
				List select_stu_check = studentservice.getSelectData("审核状态");
				session.setAttribute("select_stu_check", select_stu_check);
				
				List select_cla_idList = studentservice.getSelectClasses();
				session.setAttribute("select_cla_idList", select_cla_idList);
				
				
			} catch (SQLException e) {
				//System.out.println("SQL错误");
				//e.printStackTrace();
				
				response.sendRedirect("login.html");
				return;
			} 
			
			
		/*	session.setAttribute("role",role );	*/	
			if(user.getUser_type()==28){
				response.sendRedirect("main.html");
			}else{
				response.sendRedirect("index.jsp");
			}
			
			return;
		}else{
			response.sendRedirect("login.html");
			return;
		}
		
	}
	/**
	 * 查询用户
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_name = getString(request, "userName");
		String user_typeStr = getString(request, "userType");
		Long user_type;
		if("".equals(user_typeStr)){
			user_type =0L;
		}else{
			user_type =Long.parseLong(user_typeStr);
		}
		String user_checkstatusStr = getString(request, "userCheck");
		Integer user_checkstatus;
		if("".equals(user_checkstatusStr)){
			user_checkstatus = 0;
		}else{
			user_checkstatus = Integer.parseInt(user_checkstatusStr);
		}
	    User user = new User();
	    user.setUser_name(user_name);
		user.setUser_type(user_type);
		user.setUser_checkstatus(user_checkstatus);
		
		
		// 为了 将list页面的 类型值 换成 对应的字段
		try {
			List select_user_check = dataservice.getByType("审核状态");
			request.setAttribute("select_user_check", select_user_check);
			
			
			List select_user_Type = service.getUserType();
			request.setAttribute("select_user_Type", select_user_Type);
			
			
			//用户类型
		} catch (SQLException e) {
			//System.out.println("SQL错误");
			//e.printStackTrace();
			request.setAttribute("errorMsg", "SQL错误"+e.getMessage());
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		}
				
		int page = getInt(request, "pageIndex", 1);
		List userList = new ArrayList();
		try {
			userList = service.query(user, page);
			int totalNum = service.getCount(user);
			int totalPage = PageUtil.getTotalPage(totalNum);
			int currentPage = page;

			if(user_type==0){
				request.setAttribute("userType", "");
			}else{
				request.setAttribute("userType", user_type);
			}
			request.setAttribute("userName", user_name);
			if(user_checkstatus==0){
				request.setAttribute("userCheck", "");
			}else{
				request.setAttribute("userCheck", user_checkstatus);
			}
			request.setAttribute("userList", userList);
			request.setAttribute("totalNum", totalNum);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("currentPage", currentPage);

			request.getRequestDispatcher("user/userList.jsp")
					.forward(request, response);
		} catch (SQLException e) {
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		}
		
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
		long user_id = getLong(request, "user_id");
		
		try {
			List select_user_check = dataservice.getByType("审核状态");
			request.setAttribute("select_user_check", select_user_check);
			
			
			List select_user_Type = service.getUserType();
			request.setAttribute("select_user_Type", select_user_Type);
			
			
			//用户类型
		} catch (SQLException e) {
			//System.out.println("SQL错误");
			//e.printStackTrace();
			request.setAttribute("errorMsg", "SQL错误"+e.getMessage());
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		} 
		
		
		if (user_id > 0) {
			User user = new User();
			try {
				user = service.getById(user_id);
			} catch (SQLException e) {
				//e.printStackTrace();
				request.setAttribute("errorMsg", e.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			}
			request.setAttribute("thisUser", user);
		}
		request.getRequestDispatcher("user/userEdit.jsp").forward(request, response);
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
		Long user_id = getLong(request, "user_id");
		String user_name = getString(request, "user_name");
		String user_password  = getString(request, "user_password");
		Long user_type = getLong(request, "user_type");
		String user_desc = getString(request, "user_desc");
		Integer user_checkstatus = getInt(request, "user_checkstatus");
		
		User user = new User();
		user.setUser_name(user_name);
		user.setUser_type(user_type);
		user.setUser_password(user_password);
		user.setUser_desc(user_desc);
		user.setUser_checkstatus(user_checkstatus);
		
		if (user_id != null && user_id.longValue() > 0) {
			user.setUser_id(user_id);
		}
		long flag = 0;
		try {
			flag = service.insertOrUpdate(user);
			if (flag > 0) {
				out.println("<script language='javascript'>alert('更新成功！');window.location.href='UserServlet?method=query';</script>");
			} else {
				out.println("<script language='javascript'>alert('更新失败！');window.location.href='UserServlet?method=query';</script>");
			}
		} catch (SQLException e) {
			//e.printStackTrace();
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

		Long user_id = getLong(request, "user_id");
		if (user_id != null && user_id.longValue() > 0) {
			try {
				service.delete(user_id);
			} catch (SQLException e) {
				request.setAttribute("errorMsg", e.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			}
			this.query(request, response);
		} else {
			out.println("<script language='javascript'>alert('删除失败！');window.location.href='UserServlet?method=query';</script>");
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
