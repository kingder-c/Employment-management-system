package com.weixin.role.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.weixin.auth.service.AuthService;
import com.weixin.role.form.Role;
import com.weixin.role.service.RoleService;
import com.weixin.utils.BaseRequestUtil;
import com.weixin.utils.PageUtil;
/**
 * @注释 ：
 */
public class RoleServlet extends BaseRequestUtil {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final RoleService service = new RoleService();


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
		if ("authorize".equals(method)) { // 查询
			this.authorize(request, response);
		}
		if ("grantSave".equals(method)) { // 查询
			this.grantSave(request, response);
		}
		
		out.flush();
		out.close();
		
	}
	/**
	 * 保存修改
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void grantSave(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		PrintWriter out = response.getWriter();
		long role_id = getLong(request,"role_id");
		String role_ids = getString(request,"role_ids");
		try {
			int flag = 0;
			flag = service.grant(role_id,role_ids);
			if(flag == 0){
					out.println("<script language='javascript'>alert('授权成功！');window.location.href='RoleServlet?method=query';</script>");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("errorMsg", e.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
	}
	/**
	 * 设置权限列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
		
	@SuppressWarnings({ "unused", "rawtypes" })
	public void authorize(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		AuthService as = new AuthService();
		
		try {
			List parentlist = as.getAuthParent();
			List childlist = as.getAuthChild();
			long role_id = getLong(request,"role_id");
			String str = service.getGrant(role_id);
			request.setAttribute("parentlist", parentlist);
			request.setAttribute("childlist", childlist);
			HttpSession session = request.getSession();
			session.setAttribute("role_id", role_id);
			session.setAttribute("str", str);
			request.getRequestDispatcher("role/authorize.jsp").forward(
					request, response);	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("errorMsg", e.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
			

		
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
		Long  role_id= getLong(request, "role_id");
		if (role_id != null && role_id.longValue() > 0) {
			Role role = new Role();
			try {
				role = service.getById(role_id);
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("errorMsg", e.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			}
			request.setAttribute("role", role);
		}
		request.getRequestDispatcher("role/roleEdit.jsp").forward(
				request, response);

	}
	/**
	 * 删除角色列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */

	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		Long role_id = getLong(request, "role_id");
		if (role_id != null && role_id.longValue() > 0) {
			try {
				service.delete(role_id);
			} catch (SQLException e) {
				request.setAttribute("errorMsg", e.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			}
			this.query(request, response);
		} else {
			out.println("<script language='javascript'>alert('删除失败！');window.location.href='RoleServlet?method=query';</script>");
		}
	}
	/**
	 * 保存修改角色列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */

	public void save(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Long role_id = getLong(request, "role_id");
		String role_name = getString(request, "role_name");
		String auth_ids = getString(request, "auth_ids");
		String role_desc = getString(request, "role_desc");

		Role role = new Role();
		role.setRole_id(role_id);
		role.setRole_name(role_name);
		role.setAuth_ids(auth_ids);
		role.setRole_desc(role_desc);
		if (role_id != null &&role_id.longValue() > 0) {
			role.setRole_id(role_id);
		}
		long flag = 0;
		try {
			flag = service.insertOrUpdate(role);
			if (flag > 0) {
				out.println("<script language='javascript'>alert('更新成功！');window.location.href='RoleServlet?method=query';</script>");
			} else {
				out.println("<script language='javascript'>alert('更新失败！');window.location.href='RoleServlet?method=query';</script>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		}

	}
	@SuppressWarnings("rawtypes")
	public void query2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String role_name = getString(request, "role_name");
		String auth_ids = getString(request, "auth_ids");
		String role_desc = getString(request, "role_desc");
		Role role = new Role();
		role.setRole_name(role_name);
		role.setAuth_ids(auth_ids);
		role.setRole_desc(role_desc);
		int page = getInt(request, "pageIndex", 1);
		List roleList = new ArrayList();
		try {
			roleList = service.query(role, page);
			int totalNum = service.getCount(role);
			int totalPage = PageUtil.getTotalPage(totalNum);
			int currentPage = page;

			request.setAttribute("role_name", role_name);
			request.setAttribute("auth_ids", auth_ids);
			request.setAttribute("role_desc", role_desc);
			request.setAttribute("list", roleList);
			request.setAttribute("totalNum", totalNum);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("currentPage", currentPage);
			request.getRequestDispatcher("role/roleList.jsp")
					.forward(request, response);
		} catch (SQLException e) {
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		}
	}

	/**
	 * 查询列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	public void query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String role_name = getString(request, "role_name");
		String auth_ids = getString(request, "auth_ids");
		String role_desc = getString(request, "role_desc");
		Role role = new Role();
		role.setRole_name(role_name);
		role.setAuth_ids(auth_ids);
		role.setRole_desc(role_desc);
		int page = getInt(request, "pageIndex", 1);
		List roleList = new ArrayList();
		try {
			roleList = service.query(role, page);
			int totalNum = service.getCount(role);
			int totalPage = PageUtil.getTotalPage(totalNum);
			int currentPage = page;

			request.setAttribute("role_name", role_name);
			request.setAttribute("auth_ids", auth_ids);
			request.setAttribute("role_desc", role_desc);
			request.setAttribute("list", roleList);
			request.setAttribute("totalNum", totalNum);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("currentPage", currentPage);
			request.getRequestDispatcher("role/roleList.jsp")
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
