package com.weixin.auth.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.weixin.auth.form.Auth;
import com.weixin.auth.service.AuthService;
import com.yyy.utils.BaseRequestUtil;

public class AuthServlet extends BaseRequestUtil  {

	private static final long serialVersionUID = 1L;
	private static final AuthService service = new AuthService();

	/**
	 * Constructor of the object.
	 */
	public AuthServlet() {
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

		this.doPost(request,response);
	}

	/**
	 *dopost方法（）
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
		if("query".equals(method)){
			this.query(request, response);
		}
		if("check".equals(method)){
			this.check(request, response);
		}
		if("edit".equals(method)){
			this.edit(request, response);
		}
		if("save".equals(method)){
			this.save(request, response);
		}
		if("dele".equals(method)){
			this.delete(request, response);
		}
		out.flush();
		out.close();
	}
	/**
	 * 编辑
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		Long auth_id = getLong(request, "auth_id");
		if(auth_id !=null && auth_id.longValue() > 0){
			Auth auth = new Auth();
			try{
				auth = service.getAuthById(auth_id);
			}catch(SQLException e){
				e.printStackTrace();
				request.setAttribute("errorMsg", e.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			}
		request.setAttribute("auth", auth);
		}
		request.getRequestDispatcher("auth/authEdit.jsp")
		.forward(request, response);
		
	}
	/**
	 * 保存
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void save(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		PrintWriter out = response.getWriter();
		Auth auth = new Auth();
		String auth_name = getString(request, "auth_name");
		String auth_path = getString(request, "auth_path");
		int auth_state = getInt(request, "auth_state");
		String auth_description = getString(request, "auth_description");
		String auth_parentid = getString(request, "auth_parentid");

		
		auth.setAuth_name(auth_name);
		auth.setAuth_path(auth_path);
		auth.setAuth_state(auth_state);
		auth.setAuth_description(auth_description);
		auth.setAuth_parentid(auth_parentid);
		Long auth_id = getLong(request, "auth_id");
		
		if(auth_id!=null &&auth_id.longValue() > 0){
			auth.setAuth_id(auth_id);
		}
		long flag = 0;
		try{
			flag = service.insertOrUpdate(auth);				
			if(flag > 0){
				out.println("<script language='javascript'>alert('更新成功！');window.location.href='AuthServlet?method=query';opener.location.reload();this.window.opener = null;window.close();</script>");
			}
		}catch(SQLException e){
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request,
					response);
		}
	
	}
	/**
	 * 删除
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		PrintWriter out = response.getWriter();
		long auth_id = getLong(request,"auth_id");
		try{
			int flag = 0;
			flag = service.delete(auth_id);
			if(flag > 0){
				out.println("<script language='javascript'>alert('删除成功！');window.location.href='AuthServlet?method=query';</script>");
			}
		}catch(SQLException e){
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request,
					response);
		}
		
	
	}
	
	@SuppressWarnings({ "unused", "rawtypes" })
	public void check(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		PrintWriter out = response.getWriter();
		List childlist;
		try{
			childlist = service.getAuthChild();
			String childlist1 = JSONArray.fromObject(childlist).toString();
			out.print(childlist);
		}catch(SQLException e){
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request,
					response);
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
	public void query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException,IOException{
		try{
		List parentlist = service.getAuthParent();
		List childlist = service.getAuthChild();
		request.setAttribute("parentlist", parentlist);
		request.setAttribute("childlist", childlist);
		request.getRequestDispatcher("auth/authList.jsp").forward(request,response);
		}catch(SQLException e){
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("error.jsp").forward(request,
					response);
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
