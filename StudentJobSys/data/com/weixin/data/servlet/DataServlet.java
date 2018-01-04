package com.weixin.data.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weixin.data.form.Data;
import com.weixin.data.service.DataService;
import com.weixin.utils.BaseRequestUtil;
import com.weixin.utils.PageUtil;

/**
 *  对页面传入的值进行处理
 */
public class DataServlet extends BaseRequestUtil {

	/**
	 * UUID
	 */
	private static final long serialVersionUID = 1L;

	private static final DataService service = new DataService();
	/**
	 * Constructor of the object.
	 */
	public DataServlet() {
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
		out.flush();
		out.close();
	}

	/**
	 * 查询
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data_type = getString(request, "dataType");
		Data data = new Data();
		data.setData_type(data_type);
		int page = getInt(request, "pageIndex", 1);
		@SuppressWarnings("rawtypes")
		List dataList = new ArrayList();
		try {
			dataList = service.query(data, page);
			int totalNum = service.getCount(data);
			int totalPage = PageUtil.getTotalPage(totalNum);
			int currentPage = page;

			request.setAttribute("data_type", data_type);
			request.setAttribute("dataList", dataList);
			request.setAttribute("totalNum", totalNum);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("dataType", data_type);
			request.getRequestDispatcher("data/dataList.jsp")
					.forward(request, response);
		} catch (SQLException e) {
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		}
	}

	/**
	 * 更新
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Long data_key = getLong(request, "data_key");
		String data_name = getString(request, "data_name");
		String data_num = getString(request, "data_num");
		String data_type = getString(request, "data_type");
		int data_delete = getInt(request, "data_delete");

		Data data = new Data();
		data.setData_name(data_name);
		data.setData_num(data_num);
		data.setData_type(data_type);
		data.setData_delete(data_delete);
		if (data_key != null && data_key.longValue() > 0) {
			data.setData_key(data_key);
		}
		try {
			service.insertOrUpdate(data);
		} catch (SQLException e) {
			out.println("<script language='javascript'>alert('更新失败！');window.location.href='DataServlet?method=query';</script>");
			//e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		}finally{
			out.println("<script language='javascript'>alert('更新成功！');window.location.href='DataServlet?method=query';</script>");
		}
	}

	/**
	 * 删除
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		Long data_key = getLong(request, "data_key");
		if (data_key != null && data_key.longValue() > 0) {
			try {
				service.delete(data_key);
			} catch (SQLException e) {
				request.setAttribute("errorMsg", e.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			}
			this.query(request, response);
		} else {
			out.println("<script language='javascript'>alert('删除失败！');window.location.href='DataServlet?method=query';</script>");
		}
	}

	/**
	 * 查询 一条数据  为添加或 修改服务
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long data_key = getLong(request, "data_key");
		if (data_key != null && data_key.longValue() > 0) {
			Data data = new Data();
			try {
				data = service.getById(data_key);
			} catch (SQLException e) {
				//e.printStackTrace();
				request.setAttribute("errorMsg", e.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			}
			request.setAttribute("data", data);
		}
		request.getRequestDispatcher("data/dataEdit.jsp").forward(
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
