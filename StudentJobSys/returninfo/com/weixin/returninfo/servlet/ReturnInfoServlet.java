package com.weixin.returninfo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weixin.returninfo.service.ReturnInfoService;
import com.weixin.returninfo.view.ReturnInfoView;
import com.weixin.student.form.Student;
import com.weixin.student.view.StudentView;
import com.weixin.utils.BaseRequestUtil;
import com.weixin.utils.PageUtil;
import com.winxin.jobinfo.service.JobInfoService;
import com.winxin.jobinfo.view.JobInfoView;

public class ReturnInfoServlet extends BaseRequestUtil {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public ReturnInfoServlet() {
		super();
	}

	/**
	 * 销毁方法
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *doGet方法
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
	 * dopost方法
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
	    
	    if("edit".equals(method)){ //编辑
		   this.edit(request,response);
		   }
		if("delete".equals(method)){//删除
			 this.delete(request,response);
		}
		if("save".equals(method)){//提交保存
			 this.save(request,response);
		}	
		if("query".equals(method)){//修改
			 this.query(request,response);
		}
		if("query2".equals(method)){
			 this.query2(request,response);
		}
		if ("showStu".equals(method)) { // 查看学生的具体信息
			this.showStu(request, response);
		}
		if ("selectStu".equals(method)) { // 获取学生的姓名（跟班级关联）
			try {
				this.selectStu(request, response);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		out.flush();
		out.close();
		
	}

	ReturnInfoService  service= new ReturnInfoService();
	/**
	 * 展示学生信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void showStu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long stu_id = getLong(request, "stu_id");
		if(stu_id != null &&stu_id.longValue()>0){
			ReturnInfoView riv = new ReturnInfoView();
			List list = new ArrayList();
			try {
				list = service.getByStu_id(stu_id);
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("errorMsg", e.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			}
			request.setAttribute("list",list);
		}
		request.getRequestDispatcher("returninfo/returninfoView2.jsp").forward(
				request, response);
		
	}
	/**
	 * 编辑方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long return_id = getLong(request, "return_id");
		
		try {
	     	@SuppressWarnings("rawtypes")
			List select_cla_name = service.getCla_name();
			request.setAttribute("select_cla_name", select_cla_name);
		
		} catch (SQLException e1) {
	
			e1.printStackTrace();
		}
		
		if (return_id != null && return_id.longValue() > 0) {
			ReturnInfoView riv = new ReturnInfoView();
			try {
				riv= (ReturnInfoView) service.getById(return_id);

			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("errorMsg", e.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			}
			request.setAttribute("riv",riv);
		}
		request.getRequestDispatcher("returninfo/returninfoEdit.jsp").forward(
				request, response);

	}
	/**
	 * 删除记录
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		Long return_id = getLong(request, "return_id");
		if (return_id != null && return_id.longValue() > 0) {
			try {
				service.delete(return_id);
			} catch (SQLException e) {
				request.setAttribute("errorMsg", e.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			}
			this.query(request, response);
		} else {
			out.println("<script language='javascript'>alert('删除失败！');window.location.href='ReturnInfoServlet?method=query';</script>");
		}
	}
 
	/***
	 * 添加回访的信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void save(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Long return_id = getLong(request, "return_id");
		@SuppressWarnings("unused")
		Long stu_id = getLong(request, "stu_id");
		String stu_name= getString(request, "stu_name");
		String return_city = getString(request, "return_city");
		String return_company = getString(request, "return_company");
		String return_post = getString(request, "return_post");
		String return_salary = getString(request, "return_salary");
		String return_satisty = getString(request, "return_satisty");
		Long return_phone = getLong(request, "return_phone");
		String return_adder = getString(request, "return_adder");
		String return_content = getString(request, "return_content");
		String return_note = getString(request, "return_note");
		ReturnInfoView riv = new ReturnInfoView();
        riv.setReturn_id(return_id);
	    riv.setStu_id(stu_id);
	    riv.setStu_name(stu_name);
	    riv.setReturn_city(return_city);
	    riv.setReturn_company(return_company);
	    riv.setReturn_post(return_post);
	    riv.setReturn_salary(return_salary);
	    riv.setReturn_satisfy(return_satisty);
	    riv.setReturn_phone(return_phone);
	    riv.setReturn_adder(return_adder);
	    riv.setReturn_content(return_content);
	    riv.setReturn_note(return_note);
		if (return_id != null && return_id.longValue() > 0) {
			riv.setReturn_id(return_id);
		}
		long flag = 0;
		try {
			flag = service.insertOrUpdate(riv);
			if (flag > 0) {
				out.println("<script language='javascript'>alert('更新成功！');window.location.href='ReturnInfoServlet?method=query';</script>");
			} else {
				out.println("<script language='javascript'>alert('更新失败！');window.location.href='ReturnServlet?method=query';</script>");
			}
		} catch (Exception e) {
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
	@SuppressWarnings("rawtypes")
	public void query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
	     	List select_cla_name = service.getCla_name();
			request.setAttribute("select_cla_name", select_cla_name);
		
			
		} catch (SQLException e1) {
	
			e1.printStackTrace();
		}
		//得到输入的条件
		String stu_name = getString(request, "stu_name");
        ReturnInfoView  riv = new ReturnInfoView();
		riv.setStu_name(stu_name);
		int page = getInt(request, "pageIndex", 1);
		List returninfoList = new ArrayList();
		try {
			returninfoList = service.query(riv,page);
			int totalNum = service.getCount(riv);
			int totalPage = PageUtil.getTotalPage(totalNum);
			int currentPage = page;

			request.setAttribute("stu_name", stu_name);
			request.setAttribute("returninfoList", returninfoList);
			request.setAttribute("totalNum", totalNum);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("currentPage", currentPage);
			request.getRequestDispatcher("returninfo/returninfoList.jsp")
					.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		}
	}
  /**
   * 统计页面的查询
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
	@SuppressWarnings({ "rawtypes" })
	public void query2(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
	     	List select_cla_name = service.getCla_name();
			request.setAttribute("select_cla_name", select_cla_name);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		//得到输入的条件
		String cla_name = getString(request, "cla_name");
        ReturnInfoView  riv = new ReturnInfoView();
        riv.setCla_name(cla_name);
        
		
		int page = getInt(request, "pageIndex", 1);
		@SuppressWarnings("rawtypes")
		List returninfoList = new ArrayList();
		try {
			returninfoList = service.query2(riv,page);
			int totalNum = service.getCount(riv);
			int totalPage = PageUtil.getTotalPage(totalNum);
			int currentPage = page;

			request.setAttribute("cla_name", cla_name);
			request.setAttribute("returninfoList", returninfoList);
			request.setAttribute("totalNum", totalNum);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("currentPage", currentPage);
			request.getRequestDispatcher("returninfo/returninfoList2.jsp")
					.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
		}
	}
/**
 *  获取班级关联的学生的姓名
 * @param request
 * @param response
 * @throws ServletException
 * @throws IOException
 * @throws SQLException
 */
	@SuppressWarnings("rawtypes")
	public void selectStu(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		 response.setContentType("application/x-javascript;charset=UTF-8");
	        PrintWriter out = response.getWriter();
		
		BaseRequestUtil br = new BaseRequestUtil();
		@SuppressWarnings("static-access")
		Long cla_id = br.getLong(request, "cla_id");
		List list = new ArrayList();
		list = service.getStu(cla_id);
		  if (list != null && list.size() > 0) {
	            
	            StringBuffer json = new StringBuffer();
	            json.append("[");
	            Iterator it = list.iterator();
	            while(it.hasNext()) {
	            	StudentView model = (StudentView)it.next();
	                json.append('{');
	                json.append("id:").append(model.getStu_id()).append(",");
	                json.append("name:").append("'").append(model.getStu_name().trim()).append("'");
	                json.append("},");
	            }
	            json.deleteCharAt(json.length() - 1);
	            json.append("]");
	            out.println(json.toString());
	            out.close();
	        } else {
	            out.println("[{id:0,name:''}]");
	            out.close();
	        }
		
	}
	
	
	
	/**
	 * Initialization of the servlet. <br>
	 *初始化方法
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
