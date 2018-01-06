package com.weixin.interviewinfo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weixin.course.form.Course;
import com.weixin.interviewinfo.service.InterviewInfoService;
import com.weixin.interviewinfo.view.InterviewInfoView;
import com.yyy.utils.BaseRequestUtil;
import com.yyy.utils.PageUtil;
/**
 * 面试信息servlet层
 */
public class InterviewInfoServlet extends BaseRequestUtil {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final InterviewInfoService service = new InterviewInfoService();
	/**
	 * Constructor of the object.
	 */
	public InterviewInfoServlet() {
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
		if ("view".equals(method)){		//详情
			this.view(request,response);
		}
		out.flush();
		out.close();
	}
	/**
	 * 详情
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long interview_id = getLong(request, "interview_id");
		
		if(interview_id != null &&interview_id.longValue()>0){
			InterviewInfoView interview = new InterviewInfoView();
			try {
				interview = service.getById(interview_id);
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("errorMsg", e.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
			}
			request.setAttribute("interview", interview);
		}
		request.getRequestDispatcher("interviewinfo/interviewView.jsp").forward(
				request, response);
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
		
		String stu_name = getString(request, "stu_name");
		
		InterviewInfoView iv = new InterviewInfoView();
		iv.setStu_name(stu_name);
		
		int page = getInt(request, "pageIndex", 1);
		List interviewList = new ArrayList();
		try {
			interviewList = service.query(iv,stu_name, page);
			int totalNum = service.getCount(iv,stu_name);
			int totalPage = PageUtil.getTotalPage(totalNum);
			int currentPage = page;

			request.setAttribute("interviewList", interviewList);
			request.setAttribute("totalNum", totalNum);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("stu_name", stu_name);
			request.getRequestDispatcher("interviewinfo/interviewList.jsp")
					.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
			request.getRequestDispatcher("error.jsp")
					.forward(request, response);
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
		
		Long interview_id = getLong(request, "interview_id");
		Long stu_id = getLong(request,"stu_id");
		Long com_id = getLong(request,"com_id");
		String interview_company = getString(request,"interview_company");
		String interview_time = getString(request,"interview_time");
		String interview_type = getString(request,"interview_type");
		String interview_job = getString(request,"interview_job");
		int interview_salary = getInt(request,"interview_salary");
		String interview_weal = getString(request,"interview_weal");
		String interview_result = getString(request,"interview_result");
		String interview_tickling = getString(request,"interview_tickling");
		String interviewer_tickling = getString(request,"interviewer_tickling");
		String interview_failreason = getString(request,"interview_failreason");
		String interview_manexplain = getString(request,"interview_manexplain");
		String interview_note = getString(request,"interview_note");
		String interview_addtime = getString(request,"interview_addtime");
		String interview_adder = getString(request,"interview_adder");
		int interview_delete = getInt(request,"interview_delete");
		
		InterviewInfoView iv = new InterviewInfoView();
		iv.setInterview_id(interview_id);
		iv.setStu_id(stu_id);
		iv.setCom_id(com_id);
		iv.setInterview_company(interview_company);
		iv.setInterview_time(interview_time);
		iv.setInterview_type(interview_type);
		iv.setInterview_job(interview_job);
		iv.setInterview_salary(interview_salary);
		iv.setInterview_weal(interview_weal);
		iv.setInterview_result(interview_result);
		iv.setInterview_tickling(interview_tickling);
		iv.setInterview_tickling(interviewer_tickling);
		iv.setInterview_failreason(interview_failreason);
		iv.setInterview_manexplain(interview_manexplain);
		iv.setInterview_note(interview_note);
		iv.setInterview_addtime(interview_addtime);
		iv.setInterview_adder(interview_adder);
		iv.setInterview_delete(interview_delete);
		
		if (interview_id != null && interview_id.longValue() > 0) {
			iv.setInterview_id(interview_id);
		}
		long flag = 0;
		try {
			flag = service.insertOrUpdate(iv);
			if (flag > 0) {
				out.println("<script language='javascript'>alert('更新成功！');window.location.href='InterviewInfoServlet?method=query';</script>");
			} else {
				out.println("<script language='javascript'>alert('更新失败！');window.location.href='InterviewInfoServlet?method=query';</script>");
			}
		} catch (SQLException e) {
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

		Long interview_id =getLong(request, "interview_id");
		if (interview_id != null && interview_id.longValue() > 0) {
			try {
				service.delete(interview_id);
			} catch (SQLException e) {
				request.setAttribute("errorMsg", e.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,response);
			}
			this.query(request, response);
		} else {
			out.println("<script language='javascript'>alert('删除失败！');window.location.href='InterviewInfoServlet?method=query';</script>");
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

		Long interview_id = getLong(request, "interview_id");
		if(interview_id != null &&interview_id.longValue()>0){
			InterviewInfoView iv = new InterviewInfoView();
			try {
				iv = service.getById(interview_id);
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("errorMsg", e.getMessage());
				request.getRequestDispatcher("error.jsp").forward(request,response);
			}
			request.setAttribute("interview", iv);
		}
		
		try {
			List select_stu_idList = service.getSelectStudent();
			request.setAttribute("select_stu_idList", select_stu_idList);
			
			List select_com_idList = service.getSelectCompany();
			request.setAttribute("select_com_idList", select_com_idList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("interviewinfo/interviewEdit.jsp").forward(
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
