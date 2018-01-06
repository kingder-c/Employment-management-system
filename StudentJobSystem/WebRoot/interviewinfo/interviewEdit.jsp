<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>面试信息编辑</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css">
	<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
	<script src="<%=basePath%>My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	
	<script type="text/javascript">
		
		function check(){
			var stu_id = trim($("#stu_id").val());
			if(stu_id != ""){
				$("#stu_idMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
				
			}else{
				$("#stu_idMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>学员不能为空！</font>");
				$("#stu_idMsg").focus();
				return false;
			}
			
			var interview_time = trim($("#interview_time").val());
			if(interview_time != ""){
				$("#interview_timeMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
				
			}else{
				$("#interview_timeMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>面试日期不能为空！</font>");
				$("#interview_timeMsg").focus();
				return false;
			}
			return true;
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
		
		
		
		function doSubmit(){
			if(check()){
				document.edit.submit();
			}
		}
		function doReset(){
			document.edit.reset();
		}
		//去前后空格
		function trim(text){
			return text.replace(/^\s+|\s+$/,"");
		}

	</script>

  </head>
  
  <body>
    <!-- 顶部路径栏 -->
	<div class="place"><span>位置：</span>
    	<ul class="placeul">
        	<li><a href="javascript:void(0)">首页</a></li>
        	<c:choose>
				<c:when test="${interview != null }">
					<li><a href="javascript:void(0)">修改面试信息</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="javascript:void(0)">新增面试信息</a></li> 
				</c:otherwise>
			</c:choose>
    	</ul>
	</div>
	<div align="left" style="margin-top: 5px;">
		<form action="InterviewInfoServlet?method=save" id="edit" name="edit" method="post">
			<table border="0">
				<tr>
					<td colspan="3" class="formtitle">面试信息编辑</td>
				</tr>
				
				<tr>
					<td>
						<input type="hidden" id="interview_id" name="interview_id" value="${interview.interview_id }">
					</td>
				</tr>
				
				
	 			
	 			<tr>
					<tr>
							<td class="tdrsname" align="right">选择学员：</td>
							<td width="300" class="tdrsname">
								<input type="hidden" id="stu_id" name="stu_id" value="${interview.stu_id }"/>
								<input class="dfinput" type="text" id="stu_name" name="stu_name" value="${interview.stu_name }" style="width: 140px;float: left; text-align: center;" readonly="readonly"><span style="width: 5px;float: left;"></span>
								<c:if test="${empty  interview.stu_id}">
									<img alt="点击添加学员" src="<%=basePath%>images/addStudent.gif" style="float: left;cursor: pointer;" onclick="selectStudent()"/>
								</c:if>
								<span class="redXing">*</span>
							</td>
							<td width="auto" class="tdrsname"><span id="stu_idMsg"></span></td>
						</tr> 
				</tr>
	 			
	 			
 				<tr>
 					<c:choose>
						<c:when test="${interview != null }">
							<td>
							<input type="hidden" id="com_id" name="com_id" value="${interview.com_id }">
							</td>
						</c:when>
						<c:otherwise>
							<tr>
							<td width="150" class="tdrsname" align="right">面试单位：</td>
							<td width="360" class="tdrsname">
								<select class="dfinput" id="com_id" name="com_id"  style="width: 360px;"> 		
									<c:forEach items="${select_com_idList }" var="company">
										<c:choose>
										<c:when test="${company.com_id==interview.com_id }">
											<option value=${interview.com_id } checked>${company.com_name }-</option>
											<%-- <option value=${course.cou_id } checked>${course.cou_name }</option> --%>
										</c:when>
										<c:otherwise>
											<option value=${company.com_id }>${company.com_name }</option>
										</c:otherwise>
										</c:choose>
									</c:forEach>
		 						</select>
							</td>
							<td>
							<td width="auto" class="tdrsname"><span id="com_idMsg"></span></td>
							</tr>
						</c:otherwise>
					</c:choose>
 				</tr>
 				<%--
 				<tr>
					<td class="tdrsname" align="right">面试单位：</td>
					<td  class="tdrsname">
						<input class="dfinput" type="text" id="interview_company" name="interview_company" value="${interview.interview_company }" style="width: 360px;"> 
					</td>
					<td width="auto" class="tdrsname"><span id="interview_companyMsg"></span></td>
				</tr>
 				--%>
				<tr>
					<td class="tdrsname" align="right">面试岗位：</td>
					<td  class="tdrsname">
						<input class="dfinput" type="text" id="interview_job" name="interview_job" value="${interview.interview_job }" style="width: 360px;"> 
					</td>
					<td width="auto" class="tdrsname"><span id="interview_jobMsg"></span></td>
				</tr>
				
				<tr>
					<td class="tdrsname" align="right">面试日期：</td>
					<td  class="tdrsname">
						<input class="Wdate" type="text" id="interview_time" name="interview_time" value="${interview.interview_time }" onClick="WdatePicker({skin:'whyGreen',minDate:'1980-01-1',maxDate:'2200-12-31'})" style="height :30px;width:360px" > 
					</td>
					<td width="auto" class="tdrsname"><span id="interview_timeMsg"></span></td>
				</tr>
				
				<tr>
					<td class="tdrsname" align="right">面试方式：</td>
					<td  class="tdrsname">
						<select class="dfinput" type="text" id="interview_type" name="interview_type" value="${interview.interview_type }" style="width: 360px;">
							<option value="">--请选择--</option>
							<option value="现场">现场</option>
					   		<option value="电话">电话</option>
					   		<option value="视频">视频</option>
					   		<option value="其他">其他</option>
						</select>
					</td>
					<td width="auto" class="tdrsname"><span id="interview_typeMsg"></span></td>
				</tr>
			
				<tr>
					<td class="tdrsname" align="right">面试薪资：</td>
					<td  class="tdrsname">
						<input class="dfinput" type="text" id="interview_salary" name="interview_salary" value="${interview.interview_salary }" style="width: 360px;">
					</td>
					<td width="auto" class="tdrsname"><span id="interview_salaryMsg"></span></td>
				</tr>
				
				<tr>
					<td class="tdrsname" align="right">福利待遇：</td>
					<td  class="tdrsname">
						<input class="dfinput" type="text" id="interview_weal" name="interview_weal" value="${interview.interview_weal }" style="width: 360px;">
					</td>
					<td width="auto" class="tdrsname"><span id="interview_wealMsg"></span></td>
				</tr>
				
				<tr>
					<td class="tdrsname" align="right">面试结果：</td>
					<td  class="tdrsname">
						<select class="dfinput" type="text" id="interview_result" name="interview_result" value="${interview.interview_result }" style="width: 360px;">
							<option value="">--请选择--</option>
							<option value="通过">通过</option>
					   		<option value="未通过">未通过</option>
					   		<option value="其他">其他</option>
						</select>
					</td>
					<td width="auto" class="tdrsname"><span id="interview_resultMsg"></span></td>
				</tr>
				
				
				
				<tr>
					<td class="tdrsname" align="right">学员面试反馈：</td>
					<td class="tdrsname"><textarea id="interview_tickling" name="interview_tickling"style="width:360px; height:100px; border:solid 1px #CDBE70;  resize:none;"></textarea></td>
					<td class="tdrsname"><span id="interview_ticklingMsg"></span></td>
				</tr>
				
				<tr>
					<td class="tdrsname" align="right">面试官反馈：</td>
					<td class="tdrsname"><textarea id="interviewer_tickling" name="interviewer_tickling"style="width:360px; height:100px; border:solid 1px #CDBE70;  resize:none;"></textarea></td>
					<td class="tdrsname"><span id="interviewer_ticklingMsg"></span></td>
				</tr>
				
				<tr>
					<td class="tdrsname" align="right">未被录用原因：</td>
					<td  class="tdrsname">
						<input class="dfinput" type="text" id="interview_failreason" name="interview_failreason" value="${interview.interview_failreason }" style="width: 360px;"> 
					</td>
					<td width="auto" class="tdrsname"><span id="interview_failreasonMsg"></span></td>
				</tr>
				
				<tr>
					<td class="tdrsname" align="right">就业主管详细说明：</td>
					<td  class="tdrsname">
						<input class="dfinput" type="text" id="interview_manexplain" name="interview_manexplain" value="${interview.interview_manexplain }" style="width: 360px;"> 
					</td>
					<td width="auto" class="tdrsname"><span id="interview_manexplainMsg"></span></td>
				</tr>
				
				<tr>
					<td class="tdrsname" align="right">备注：</td>
					<td  class="tdrsname">
						<input class="dfinput" type="text" id="interview_note" name="interview_note" value="${interview.interview_note }" style="width: 360px;"> 
					</td>
					<td width="auto" class="tdrsname"><span id="interview_noteMsg"></span></td>
				</tr>
				
				<tr>
					<td class="tdrsname" align="right">录入人：</td>
					<td  class="tdrsname">
						<input class="dfinput" type="text" id="interview_adder" name="interview_adder" value="${interview.interview_adder }" style="width: 360px;"> 
					</td>
					<td width="auto" class="tdrsname"><span id="interview_adderMsg"></span></td>
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
