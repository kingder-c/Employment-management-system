package com.weixin.utils.upload;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lxh.smart.SmartUpload;
import org.lxh.smart.SmartUploadException;

import com.weixin.utils.BaseRequestUtil;

/**
 * 图片上传的公共代码
 */
public class ImgCutUploadServlet extends  BaseRequestUtil{

	/**
	 * UUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public ImgCutUploadServlet() {
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
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String method = request.getParameter("method");
		
		if("getPathParam".equals(method)){
			this.getPathParam(request,response);
		}
		if("uploadFile".equals(method)){//上传图片
			this.uploadFile(request, response);
		}
		if("uploadSmallImg".equals(method)){
			this.uploadSmallImg(request, response);
		}
		out.flush();
		out.close();
	}

	

	/**
	 * 得到图片  图片的选择页面
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void getPathParam(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//ImgCutUploadServlet?method=getPathParam&pathParam=studentImg&picWidth=222&picHeight=305
		String pathParam =getString(request, "pathParam");
		request.setAttribute("pathParam", pathParam);
		request.setAttribute("picWidth", getString(request, "picWidth"));
		request.setAttribute("picHeight", getString(request, "picHeight"));
		request.setAttribute("method", getString(request, "method"));
		if(pathParam.equals("studentImg")){
			request.getRequestDispatcher("student/studentView.jsp").forward(
					request, response);
		}
		if(pathParam.equals("jobinfophoto")){
			request.getRequestDispatcher("jobinfo/jobinfoView.jsp").forward(
					request, response);
		}
		if(pathParam.equals("courseLogo")){
			request.getRequestDispatcher("course/courseView.jsp").forward(
					request, response);
		}
		if(pathParam.equals("courseLogo")){
			request.getRequestDispatcher("course/courseList.jsp").forward(
					request, response);
		}
	}

	/**
	 * 图片的 上传页面
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */

	private void uploadFile(HttpServletRequest request, HttpServletResponse response) throws IOException,  ServletException {
		
		//创建了一个smartupload对象，为我们实现获取页面内容
		//做准备
		SmartUpload smartupload = new SmartUpload();
		// 清除以前设置
		try{
			Calendar calendar=Calendar.getInstance();
			smartupload.setTotalMaxFileSize(4000*1024);       // 设置上传的文件大小
			smartupload.setMaxFileSize(4000*1024);
			
			//this.getServletConfig()获取到应用的上下文配置
			smartupload.initialize(this.getServletConfig(),request,response) ;	// 初始化上传
			
			
			smartupload.upload();				// 准备上传
				
			String picName=""; // 设置完整的图片名称
			String flag = String.valueOf(calendar.getTime().getTime());
			
			//组织1.png
			if(smartupload.getFiles().getSize()>0){
				
				picName = flag + "." + smartupload.getFiles().getFile(0).getFileExt() ;
				
				/*String fileName = this.getServletContext().getRealPath("\\") + "images\\touxiang\\" + picName ;*/
				String fileName = "images\\touxiang\\" + picName ;
				String cropbox = "./images/touxiang/" + picName;
				
				
				//String fileName =  "d:\\a\\" + picName ;
				
				//this.getServletContext().getRealPath("/")
				//相当于获取到了你当前应用所在容器的物理硬盘的地址
				//fileName
				//C:\Program Files\Apache Software Foundation\Tomcat 6.0\webapps\NewsSystem/manager/images/upload/123.png
				for(int i = 0 ;i<smartupload.getFiles().getCount() ;i++){
					smartupload.getFiles().getFile(i).saveAs(fileName) ;
				}
				
				//smartupload.getFiles().getFile(0).saveAs(fileName);
				request.setAttribute("fileName", fileName);
				request.setAttribute("cropbox", cropbox);
				request.setAttribute("stuImg", cropbox);
				request.getRequestDispatcher("student/studentView.jsp").forward(
						request, response);
			}
		}catch (SmartUploadException e){
			e.printStackTrace();
			System.out.println("图片上传错误");
		}
		
	}
	
	/**
	 * 回调 将传入的 getQuery传入的值  封装
	 * @param request
	 * @param response
	 * @throws ServletException 
	 * @throws IOException 
	 */
	private void uploadSmallImg(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String s = getString(request, "arg");

		
		
		PrintWriter pw = response.getWriter();
		pw.write(s);
	}
	
	
	/**
	 * Initialization of the servlet. <br>
	 * @注释：初始化servlet
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
