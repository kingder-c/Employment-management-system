package com.weixin.company.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.weixin.company.form.Company;
import com.weixin.company.service.CompanyService;
import com.weixin.company.view.CompanyView;
import com.yyy.student.view.StudentView;
import com.yyy.utils.BaseRequestUtil;
import com.yyy.utils.PageUtil;

public class CompanyServlet extends BaseRequestUtil {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final CompanyService service = new CompanyService();

	/**
	 * Constructor of the object.
	 */
	public CompanyServlet() {
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
		if ("query2".equals(method)) { // 查询
			this.query2(request, response);
		}
		if ("view".equals(method)) { // 查询
			this.view(request, response);
		}
		if ("selectCompany".equals(method)){//其他模块使用查询页面
			this.selectCompany(request, response);
		}
		if("getCompanyByAjax".equals(method)){
			this.getCompanyByAjax(request,response);
		}
		out.flush();
		out.close();
		
	}
	/**
	 * 在页面上输出企业id、企业名称
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void getCompanyByAjax(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Long com_id = getLong(request, "com_id");
		String s = "####";
		if(com_id != null &&com_id.longValue()>0){
			Company com = new Company();
			try {
				com = service.getById(com_id);
				s = com_id+"####"+com.getCom_name()+"####"+24;
			} catch (SQLException e) {
				request.setAttribute("errorMsg", e.getMessage());
				s = s+e.getMessage();
			}
			
		}
		System.out.println(s);
		PrintWriter pw = response.getWriter();
		pw.write(s);
	}
	/**
	 * 前台页面查询
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void selectCompany(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String com_name = getString(request, "com_name");
		String com_city = getString(request, "com_city");
		String com_status = getString(request, "com_status");
		
		String com_checkstatus = getString(request, "com_checkstatus");
		
		Company company = new Company();
		
		company.setCom_name(com_name);
		company.setCom_city(com_city);
		if(StringUtils.isNotBlank(com_status)){
			if (com_status.equals("0")) {
				company.setCom_status(0);
			}else {
				company.setCom_status(1);
			}
		}
		company.setCom_checkstatus(com_checkstatus);
		int page = getInt(request, "pageIndex", 1);
		List companyList = new ArrayList();
		try {
			companyList = service.query(company, page);
			int totalNum = service.getCount(company);
			int totalPage = PageUtil.getTotalPage(totalNum);
			int currentPage = page;
			
			List select_com_status = service.getSelectData("合作企业状态");
			request.setAttribute("select_com_status", select_com_status);

			request.setAttribute("com_name", com_name);
			request.setAttribute("com_city", com_city);
			request.setAttribute("com_status", com_status);
			request.setAttribute("com_checkstatus", com_checkstatus);
			request.setAttribute("list", companyList);
			request.setAttribute("totalNum", totalNum);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("currentPage", currentPage);
			request.getRequestDispatcher("company/companyList3.jsp")
					.forward(request, response);
		} catch (SQLException e) {
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		}
		
	}
	/**
	 * 企业详情页面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	private void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long com_id = getLong(request, "com_id");
		if(com_id != null &&com_id.longValue()>0){
			Company company = new Company();
		
			try {
				company = service.getById(com_id);
				
				List select_com_status = service.getSelectData("合作企业状态");
				request.setAttribute("select_com_status", select_com_status);

				request.setAttribute("com_name", company.getCom_name());
				request.setAttribute("com_city", company.getCom_city());
				request.setAttribute("com_person", company.getCom_person());
				request.setAttribute("com_phone", company.getCom_phone());
				request.setAttribute("com_email", company.getCom_email());
				request.setAttribute("com_direction", company.getCom_direction());
				request.setAttribute("com_status", company.getCom_status());
				request.setAttribute("com_level", company.getCom_level());
				request.setAttribute("com_note", company.getCom_note());
				request.setAttribute("com_checkstatus", company.getCom_checkstatus());
				request.setAttribute("com", company);
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("errorMsg", e.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			}
		}
		
		request.getRequestDispatcher("company/companyView.jsp").forward(
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
		Long  com_id = getLong(request, "com_id");
		if (com_id != null && com_id.longValue() > 0) {
			Company company = new Company();
			try {
				company = service.getById(com_id);
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("errorMsg", e.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			}
			request.setAttribute("company", company);
		}
		request.getRequestDispatcher("company/companyEdit.jsp").forward(
				request, response);

	}
	/**
	 * 删除
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		Long com_id = getLong(request, "com_id");
		if (com_id != null && com_id.longValue() > 0) {
			try {
				service.delete(com_id);
			} catch (SQLException e) {
				request.setAttribute("errorMsg", e.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			}
			this.query(request, response);
		} else {
			out.println("<script language='javascript'>alert('删除失败！');window.location.href='DictionaryServlet?method=query';</script>");
		}
	}
	/**
	 * 保存
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void save(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Long com_id = getLong(request, "com_id");
		String com_name = getString(request, "com_name");
		String com_city = getString(request, "com_city");
		String com_person = getString(request, "com_person");
		String com_phone = getString(request, "com_phone");
		String com_email = getString(request, "com_email");
		String com_direction = getString(request, "com_direction");
		String com_status = getString(request, "com_status");
		String com_level = getString(request, "com_level");
		String com_note = getString(request, "com_note");
		String com_checkstatus = getString(request, "com_checkstatus");

		Company company = new Company();
		company.setCom_id(com_id);
		company.setCom_name(com_name);
		company.setCom_city(com_city);
		company.setCom_person(com_person);
		company.setCom_phone(com_phone);
		company.setCom_email(com_email);
		company.setCom_direction(com_direction);
		
		company.setCom_level(com_level);
		company.setCom_note(com_note);
		
		if(StringUtils.isNotBlank(com_status)){
			if (com_status.equals("0")) {
				company.setCom_status(0);
			}else {
				company.setCom_status(1);
			}
		}
		company.setCom_checkstatus(com_checkstatus);
		if (com_id != null &&com_id.longValue() > 0) {
			company.setCom_id(com_id);
		}
		long flag = 0;
		try {
			flag = service.insertOrUpdate(company);
			if (flag > 0) {
				out.println("<script language='javascript'>alert('更新成功！');window.location.href='CompanyServlet?method=query';</script>");
			} else {
				out.println("<script language='javascript'>alert('更新失败！');window.location.href='CompanyServlet?method=query';</script>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
		String com_name = getString(request, "com_name");
		String com_city = getString(request, "com_city");
		String com_person = getString(request, "com_person");
		String com_phone = getString(request, "com_phone");
		String com_email = getString(request, "com_email");
		String com_direction = getString(request, "com_direction");
		String com_status = getString(request, "com_status");
		String com_level = getString(request, "com_level");
		String com_note = getString(request, "com_note");
		String com_checkstatus = getString(request, "com_checkstatus");

		
		Company company = new Company();
		
		company.setCom_name(com_name);
		company.setCom_city(com_city);
		company.setCom_city(com_person);
		company.setCom_city(com_phone);
		company.setCom_city(com_email);
		company.setCom_city(com_direction);
		company.setCom_city(com_level);
		company.setCom_city(com_note);
	
		if("".equals(com_status)||com_status==null){
			company.setCom_status(5);
		}
		if(StringUtils.isNotBlank(com_status)){
			if (com_status.equals("0")) {
				company.setCom_status(0);
			}else {
				company.setCom_status(1);
			}
		}
		company.setCom_checkstatus(com_checkstatus);
		int page = getInt(request, "pageIndex", 1);
		List companyList = new ArrayList();
		try {
			companyList = service.query(company, page);
			int totalNum = service.getCount(company);
			int totalPage = PageUtil.getTotalPage(totalNum);
			int currentPage = page;
			
			List select_com_status = service.getSelectData("合作企业状态");
			request.setAttribute("select_com_status", select_com_status);

			request.setAttribute("com_name", company.getCom_name());
			request.setAttribute("com_city", company.getCom_city());
			request.setAttribute("com_person", company.getCom_person());
			request.setAttribute("com_phone", company.getCom_phone());
			request.setAttribute("com_email", company.getCom_email());
			request.setAttribute("com_direction", company.getCom_direction());
			request.setAttribute("com_status", company.getCom_status());
			request.setAttribute("com_level", company.getCom_level());
			request.setAttribute("com_note", company.getCom_note());
			request.setAttribute("com_checkstatus", company.getCom_checkstatus());
			request.setAttribute("list", companyList);
			request.setAttribute("totalNum", totalNum);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("currentPage", currentPage);
			request.getRequestDispatcher("company/companyList.jsp")
					.forward(request, response);
		} catch (SQLException e) {
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		}
	}
	
	
	/**
	 * 开户查询列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	public void query2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String com_name = getString(request, "com_name");
		String com_city = getString(request, "com_city");
		String com_status = getString(request, "com_status");
		String com_checkstatus = getString(request, "com_checkstatus");
		
		Company company = new Company();
		company.setCom_name(com_name);
		if(StringUtils.isNotBlank(com_status)){
			if (com_status.equals("0")) {
				company.setCom_status(0);
			}else {
				company.setCom_status(1);
			}
		}
		company.setCom_city(com_city);
		company.setCom_checkstatus(com_checkstatus);
		int page = getInt(request, "pageIndex", 1);
		List companyList = new ArrayList();
		try {
			companyList = service.query(company, page);
			int totalNum = service.getCount(company);
			int totalPage = PageUtil.getTotalPage(totalNum);
			int currentPage = page;
			
			List select_com_status = service.getSelectData("合作企业状态");
			request.setAttribute("select_com_status", select_com_status);


			request.setAttribute("com_name", com_name);
			request.setAttribute("com_city", com_city);
			request.setAttribute("com_status", com_status);
			request.setAttribute("com_checkstatus", com_checkstatus);
			request.setAttribute("list", companyList);
			request.setAttribute("totalNum", totalNum);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("currentPage", currentPage);
			request.getRequestDispatcher("company/companyList2.jsp")
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
