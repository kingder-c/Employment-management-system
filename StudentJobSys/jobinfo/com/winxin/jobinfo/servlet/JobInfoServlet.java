package com.winxin.jobinfo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;













import com.weixin.company.form.Company;
import com.weixin.company.service.CompanyService;
import com.weixin.utils.BaseRequestUtil;
import com.weixin.utils.PageUtil;
import com.winxin.jobinfo.form.JobInfo;
import com.winxin.jobinfo.service.JobInfoService;
import com.winxin.jobinfo.view.JobInfoView;
/***
 * 继承BaseRequestUtil，简化request方法
 * @注释 ：
 */
@SuppressWarnings("unused")
public class JobInfoServlet extends BaseRequestUtil {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JobInfoService service = new JobInfoService();
	CompanyService service2 = new CompanyService();

	/**
	 * Constructor of the object.
	 */
	public JobInfoServlet() {
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
	 *get方法
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
	 * post方法
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
		if("save".equals(method)){//保存
			 this.save(request,response);
		}	
		if("query".equals(method)){//查询
			 this.query(request,response);
		}
		if ("view".equals(method)) { // 查看学生的具体信息
			this.view(request, response);
		}
		out.flush();
		out.close();
	}

	/**
	 * 展示个人就业信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long job_id = getLong(request, "job_id");
		if(job_id != null &&job_id.longValue()>0){
			JobInfoView jobinfoview = new JobInfoView();
			try {
				jobinfoview = service.getById(job_id);
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("errorMsg", e.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			}
			request.setAttribute("jobinfoview", jobinfoview);
		}
		request.getRequestDispatcher("jobinfo/jobinfoView2.jsp").forward(
				request, response);
		
	}

	
	
	/**
	 * 编辑个人信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long job_id = getLong(request, "job_id");
		try {
			@SuppressWarnings("rawtypes")
			List select_cla_name  = service.getCla_name();
			request.setAttribute("select_cla_name", select_cla_name);
			
			@SuppressWarnings("rawtypes")
			List select_cou_name  = service.getCou_name();
			request.setAttribute("select_cou_name", select_cou_name);
			
			@SuppressWarnings("rawtypes")
			List select_stu_name  = service.getStu_name();
			request.setAttribute("select_stu_name", select_stu_name);
			
			@SuppressWarnings("rawtypes")
			List select_com_id  = service.getCom_id();
			request.setAttribute("select_com_id", select_com_id);
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		
		
		if (job_id != null && job_id.longValue() > 0) {
			JobInfoView jobinfoview = new JobInfoView();
			try {
				jobinfoview = (JobInfoView) service.getById(job_id);

			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("errorMsg", e.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
				return ;
			}
			request.setAttribute("jobinfoview", jobinfoview);
		}
		request.getRequestDispatcher("jobinfo/jobinfoEdit.jsp").forward(
				request, response);
       return;
	}
	/**
	 * 删除个人就业的信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		Long job_id = getLong(request, "job_id");
		if (job_id != null && job_id.longValue() > 0) {
			try {
				service.delete(job_id);
			} catch (SQLException e) {
				request.setAttribute("errorMsg", e.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			}
			this.query(request, response);
		} else {
			out.println("<script language='javascript'>alert('删除失败！');window.location.href='JonInfoServlet?method=query';</script>");
		}
	}
 
	/***
	 * 添加或者修改信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws SQLException 
	 */
	public void save(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Long com_id = getLong(request, "com_id");
		Long job_id = getLong(request, "job_id");
		Long stu_id = getLong(request, "stu_id");
		
		/*测试*/
		if(com_id==0||com_id==null){//没有进行选择下拉菜单   没有和维信合作的企业
			String job_company = getString(request, "job_company");//得到现在的签约单位名称
			String job_city = getString(request, "job_city");//现在的企业 所在城市
			Company  com = new Company();
			com.setCom_name(job_company);
			com.setCom_city(job_city);
			com.setCom_checkstatus("未通过");
			com.setCom_status(1);
			try {
				com_id = service2.insertOrUpdate(com)+1;
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		String job_company = getString(request, "job_company");
		String job_job = getString(request, "job_job");
		String job_city = getString(request, "job_city");
		String job_startdate = getString(request, "job_startdate");
		Long job_basesalary = getLong(request, "job_basesalary");
		Long job_three = getLong(request, "job_three");
		String job_comment = getString(request, "job_comment");
		String job_adder = getString(request, "job_adder");
		String job_addtime = getString(request, "job_addtime");
		
		JobInfoView jobinfoview = new JobInfoView();
        jobinfoview.setCom_id (com_id );
		jobinfoview.setStu_id(stu_id);
		jobinfoview.setStu_id(stu_id);
		jobinfoview.setJob_company(job_company);
		jobinfoview.setJob_job(job_job);
		jobinfoview.setJob_city(job_city);
		jobinfoview.setJob_startdate(job_startdate);
		jobinfoview.setJob_basesalary(job_basesalary);
	    jobinfoview.setJob_three(job_three);
		jobinfoview.setJob_comment(job_comment);
		jobinfoview.setJob_adder(job_adder);
		jobinfoview.setJob_addtime(job_addtime);
		if (job_id != null && job_id.longValue() > 0) {
			jobinfoview.setJob_id(job_id);
		}
		long flag = 0;
		try {
			flag = service.insertOrUpdate(jobinfoview);
			if (flag > 0) {
				out.println("<script language='javascript'>alert('更新成功！');window.location.href='JobInfoServlet?method=query';</script>");
			} else {
				out.println("<script language='javascript'>alert('更新失败！');window.location.href='JobInfoServlet?method=query';</script>");
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
			List select_cla_name  = service.getCla_name();
			request.setAttribute("select_cla_name", select_cla_name);
			
			List select_cou_name  = service.getCou_name();
			request.setAttribute("select_cou_name", select_cou_name);
			
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		//得到输入的条件
		String stu_name = getString(request, "stu_name");
		String cla_name = getString(request, "cla_name");
	/*	String job_type = getString(request, "job_type");*/
		String stu_native = getString(request, "stu_native");
		String cou_name = getString(request, "cou_name");
		String job_company= getString(request, "job_company");
		String job_startdate1 = getString(request, "job_startdate1");
		String job_startdate2 = getString(request, "job_startdate2");
		JobInfoView jobinfoview= new JobInfoView();
		jobinfoview.setStu_name(stu_name);
		jobinfoview.setCla_name(cla_name);
		jobinfoview.setStu_native(stu_native);
		jobinfoview.setCou_name(cou_name);
		jobinfoview.setJob_company(job_company);
	

		int page = getInt(request, "pageIndex", 1);
		List jobinfoList = new ArrayList();
		try {
			jobinfoList = service.query(jobinfoview,job_startdate1,job_startdate2, page);//获取数据库中的数据
			int totalNum = service.getCount(jobinfoview,job_startdate1,job_startdate2, page);
			int totalPage = PageUtil.getTotalPage(totalNum);
			int currentPage = page;

			request.setAttribute("stu_name", stu_name);
			request.setAttribute("cla_name",cla_name);
			/*request.setAttribute("job_type", job_type);*/
			request.setAttribute("stu_native", stu_native);
			request.setAttribute("cou_name", cou_name );
			request.setAttribute("job_company", job_company);
			request.setAttribute("job_startdate1", job_startdate1);
			request.setAttribute(" job_startdate2",  job_startdate2);
		
			request.setAttribute("jobinfoList", jobinfoList);
			request.setAttribute("totalNum", totalNum);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("currentPage", currentPage);
			request.getRequestDispatcher("jobinfo/jobinfoList.jsp")
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
	 *初始化入口
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
