<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>就业信息编辑页面</title>
    <meta content="text/html" charset="utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css">
	<script type="text/javascript" src="ckeditor/ckeditor.js"></script>  
	<script type="text/javascript" src="ckeditor/config.js"></script> 
	<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
	<script src="<%=basePath%>My97DatePicker/WdatePicker.js" type="text/javascript"></script><!-- 时间插件的添加 -->
	<script type="text/javascript">
	function check(){
			
			
			 
			var job_startdate = trim($("#job_startdate").val());
			if(job_startdate != ""){
					$("#job_startdateMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
						
			}else{
					$("#job_startdateMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>分类名称不能为空！</font>");
					$("#job_startdate").focus();
				return false;
			}	
			
		  	var job_job = trim($("#job_job").val());
			if(job_job != ""){
					$("#job_jobMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
					
			}else{
					$("#job_jobMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>分类名称不能为空！</font>");
					$("#job_job").focus();
				return false;
			  }
		  return true;
		  }
		  
		function doSubmit(){
			if(check()){
				document.edit.submit();
			}
		}
		function doReset(){
			document.edit.reset();
		}
		function doUploadImg(){
			var url = "ImgCutUploadServlet?method=getPathParam&pathParam=jobinfophoto&picWidth=222&picHeight=305";
			window.open(url, "uploadFiles", "height=425, width=1000, top=120, left=200, toolbar=no, menubar=no, scrollbars=yes, resizable=no, location=no, status=no");
		}
		function callbackPfn(data){
			var jobphoto = data.arg.src;
			if(jobphoto != "" && jobphoto != null && jobphoto != "undefined"){
				$("#job_photo").val(jobphoto);
				$("#jobphoto").attr("src", jobphoto);
			}
		}
		function selectStudent(){
			var url = "StudentServlet?method=selectStudent";
			window.open(url, "选择学员", "height=650,width=1200,top=10,left=75,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no", "");
		}
		
		function getStudentInfo(arg){
			var student = arg.split("####");
			if(student != "" && student != null && student != "undefined"){
				$("#stu_id").val(student[0]);
				$("#stu_name").val(student[1]);
			}
		}
		
		//去前后空格
		function trim(arg){
			return arg.replace(/^\s+|\s+$/,"");
		}
	</script>

  </head>
  
  <body>
    <!-- 顶部路径栏 -->
	<div class="place"><span>位置：</span>
    	<ul class="placeul">
        	<li><a href="javascript:void(0)">首页</a></li>
        	<c:choose>
				<c:when test="${jobinfoview != null }">
					<li><a href="javascript:void(0)">修改就业信息</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="javascript:void(0)">新增就业信息</a></li> 
				</c:otherwise>
			</c:choose>
    	</ul>
	</div>
	<div align="left" style="margin-top: 5px;">
		<form action="JobInfoServlet?method=save" id="edit" name="edit" method="post">
			<table border="0">
				<tr>
					<td colspan="3" class="formtitle">就业信息编辑</td>
				</tr>
				<tr>
					<td>
						<input type="hidden" id="job_id" name="job_id" value="${jobinfoview.job_id }">
					</td>
				</tr>
				
				<tr>
					<tr>
							<td class="tdrsname" align="right">选择学员：</td>
							<td width="300" class="tdrsname">
								<input type="hidden" id="stu_id" name="stu_id" value="${jobinfoview.stu_id }"/>
								<input class="dfinput" type="text" id="stu_name" name="stu_name" value="${jobinfoview.stu_name }" style="width: 140px;float: left; text-align: center;" readonly="readonly"><span style="width: 5px;float: left;"></span>
								<c:if test="${empty  jobinfoview.stu_id}">
									<img alt="点击添加学员" src="<%=basePath%>images/addStudent.gif" style="float: left;cursor: pointer;" onclick="selectStudent()"/>
								</c:if>
								<span class="redXing">*</span>
							</td>
							<td width="auto" class="tdrsname"><span id="stu_idMsg"></span></td>
						</tr> 
				</tr>
 				<tr>
					<td class="tdrsname" align="right">就业单位：</td>
					<td  width="300" class="tdrsname">
						<select class="dfinput" id="job_company" name="job_company"  style="width: 140px;"> 		
						 	<c:choose>
						 		 <c:when test = "${empty jobinfoview } ">
						 		 <option value = "">--请选择--</option>
							     <c:forEach items="${select_com_id }" var="com">
									<option value="${com.com_id }" >-${com.com_name }</option>
								 </c:forEach>	
								</c:when>
								<c:otherwise>
								 <option value = "${jobinfoview.com_id }" selected>${jobinfoview.com_id }</option>
								 <c:forEach items="${select_com_id }" var="com">
									<option value="${com.com_id }" >-${com.com_name }--</option>
								</c:forEach>	
								</c:otherwise>
							</c:choose>
	 					</select>
						&nbsp;&nbsp;其它：<input class="dfinput" type="text" id="job_company" name="job_company" value="${jobinfoview.job_company}" style="width: 168px;"> 
					<td width="auto" class="tdrsname"><span id="com_idMsg"></span> <span class="redXing">*</span></td>
				</tr>
				<tr>
					<td class="tdrsname" align="right">就业城市：</td>
					<td width="300" class="tdrsname">
						<input class="dfinput" type="text" id="job_city" name="job_city" value="${jobinfoview.job_city}" style="width: 356px;"> 
					<td width="auto" class="tdrsname"><span id="job_cityMsg"></span></td>
				</tr>
				<tr>
					<td class="tdrsname" align="right">岗位：</td>
					<td width="300" class="tdrsname">
						<input class="dfinput" type="text" id="job_job" name="job_job" value="${jobinfoview.job_job} "style="width: 356px;"> 
					<td width="auto" class="tdrsname"><span id="job_jobMsg"></span> <span class="redXing">*</span></td>
				</tr>
				<tr>
					<td class="tdrsname" align="right">入职日期：</td>
					<td width="300"  class="tdrsname">
						<input class="Wdate" type="text" id="job_startdate" name="job_startdate" value="${jobinfoview.job_startdate}" onClick="WdatePicker({skin:'whyGreen',minDate:'1980-01-1',maxDate:'2200-12-31'})" style="height: 30px;width:356px"> 
					</td>
					
						
					<td width="auto" class="tdrsname"><span id="job_startdateMsg"></span></span> <span class="redXing">*</span></td>
				</tr>
				<%--  <tr>
					<td class="tdrsname" align="right">合同期：</td>
					<td width="300"  class="tdrsname">
						<input class="dfinput" type="text" id="job_contractlength" name="job_contractlength" value="${jobinfoview.job_contractlength}" style="width: 356px;"> 
					<td width="auto" class="tdrsname"><span id="job_contractlengthMsg"></span></td>
				</tr> 
				<tr>
					<td class="tdrsname" align="right">工作性质：</td>
					<td width="300"  class="tdrsname">
						<input class="dfinput" type="text" id="job_nature" name="job_nature" value="${jobinfoview.job_nature}" style="width: 356px;"> 
					<td width="auto" class="tdrsname"><span id="job_natureMsg"></span></td>
				</tr>
				<tr>
					<td class="tdrsname" align="right">实习薪资与福利：</td>
					<td width="300"  class="tdrsname">
						<input class="dfinput" type="text" id="job_practicesalary" name="job_practicesalary" value="${jobinfoview.job_practicesalary}" style="width: 356px;"> 
					<td width="auto" class="tdrsname"><span id="job_practicesalaryMsg"></span></td>
				</tr> --%>
				<tr>
					<td class="tdrsname" align="right">转正基本月薪：</td>
					<td width="300"  class="tdrsname">
						<input class="dfinput" type="text" id="job_basesalary" name="job_basesalary" value="${jobinfoview.job_basesalary}" style="width: 356px;"> 
					<td width="auto" class="tdrsname"><span id="job_basesalaryMsg"></span></td>
				</tr>
				<%-- <tr>
					<td class="tdrsname" align="right">转正综合月薪：</td>
					<td width="300"  class="tdrsname">
						<input class="dfinput" type="text" id="job_comprehensive_salary" name="job_comprehensive_salary" value="${jobinfoview.job_comprehensive_salary}" style="width: 356px;"> 
					<td width="auto" class="tdrsname"><span id="job_comprehensive_salaryMsg"></span></td>
				</tr> --%>
				<%-- <tr>
					<td class="tdrsname" align="right">就业福利待遇：</td>
					<td width="300"  class="tdrsname">
						<input class="dfinput" type="text" id="job_weal" name="job_weal" value="${jobinfoview.job_weal}" style="width: 356px;"> 
					<td width="auto" class="tdrsname"><span id="job_wealMsg"></span></td>
				</tr> --%>
				<tr>
					<td class="tdrsname" align="right">是否签订三方：</td>
					<td width="300"  class="tdrsname">
						<input class="dfinput" type="text" id="job_three" name="job_three" value="${jobinfoview.job_three}" style="width: 356px;"> 
					<td width="auto" class="tdrsname"><span id="job_threeMsg"></span></td>
				</tr>
				<%-- <tr>
					<td class="tdrsname" align="right">是否签订劳动合同：</td>
					<td width="300"  class="tdrsname">
						<input class="dfinput" type="text" id="job_compact" name="job_compact" value="${jobinfoview.job_compact}" style="width: 356px;"> 
					<td width="auto" class="tdrsname"><span id="ob_compactMsg"></span></td>
				</tr> --%>
				<tr>
					<td class="tdrsname" align="right">就业备注：</td>
					<td width="300"  class="tdrsname">
						<input class="dfinput" type="text" id="job_comment" name="job_comment" value="${jobinfoview.job_comment}" style="width: 356px;"> 
					<td width="auto" class="tdrsname"><span id="job_commentMsg"></span></td>
				</tr>
				<%-- <tr>
					<td class="tdrsname" align="right">就业方式：</td>
					<td width="300" class="tdrsname">
						<select class="dfinput" id="job_type" name="job_type"  style="width: 356px;"> 		
						 <c:forEach items="${select_job_type }" var="job">
							<c:choose>
							<c:when test="${job.job_type == jobinfoview.job_type }">
								<option value=${jobinfoview.job_type } checked>${job.job_type }</option>
							</c:when>
							<c:otherwise>
								<option value=${job.job_type } >--${job.job_type }--</option>
							</c:otherwise>
							</c:choose>
						</c:forEach> 
 						</select>
 						</td>
					<td width="auto" class="tdrsname"><span id="job_typeMsg"></span></td>
				</tr> --%>
				<%-- <tr>
					<td class="tdrsname" align="right">企业推荐人：</td>
					<td width="300"  class="tdrsname">
						<input class="dfinput" type="text" id="job_recommend" name="job_recommend" value="${jobinfoview.job_recommend}" style="width: 356px;"> 
					<td width="auto" class="tdrsname"><span id="job_recommendMsg"></span></td>
				</tr> --%>
				<%-- <tr>
					<td class="tdrsname" align="right">学员工作照</td>
					<td width="300"   class="tdrsname">
						
						<input type="hidden"  id="job_photo" name="job_photo" value="${jobinfoview.job_photo }"/>
        				<c:if test="${not empty jobinfoview.job_photo }">	
        					<img id="jobphoto" src="${jobinfoview.job_photo }width="100" width="100" height="137" title="点此处上传图片" style="cursor:pointer;" onclick="doUploadImg()"/><br>
        					<span style="color:red">&nbsp;点击图片上传头像，建议图片大小为222*305</span>
        				</c:if>
        				<c:if test="${empty jobinfoview.job_photo }">
	        				<img id="jobphoto" src="<%=basePath%>images/touxiang/touxiang.jpg" width="100" width="100" height="137" title="点此处上传图片" style="cursor:pointer;" onclick="doUploadImg()"/><br>
	        				<span style="color:red">&nbsp;点击图片上传头像，建议图片大小为222*305</span>
        				</c:if>
					</td>
					<td width="auto" class="tdrsname"><span id="job_photoMsg"></span></td>
				</tr> --%>
				
				<tr>
					<td class="tdrsname" align="right">信息录入人：</td>
					<td width="50"  class="tdrsname">
						<input class="dfinput" type="text" id="job_adder" name="job_adder"  value="${jobinfoview.job_adder}" style="width: 356px;"> 
					<td width="auto" class="tdrsname"><span id="job_adderMsg"></span></td>
				</tr>
				
				
				<tr style="line-height:80px; ">
					<!-- 按钮换成背景图片 -->
					<td>&nbsp;</td>
					<td colspan="1" align="center"><input class="button"
						type="button" name="确认" value="确认" onClick="doSubmit()" />
					&nbsp;&nbsp;&nbsp;&nbsp;<input class="button"
						type="button" name="返回" value="返回" onClick="javascript:history.back();"/>
					</td><td colspan="3">&nbsp;</td>
				</tr>
			</table>
		</form>
	</div>
  </body>
</html>
