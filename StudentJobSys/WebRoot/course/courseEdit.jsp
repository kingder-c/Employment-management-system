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
    
    <title>课程信息编辑页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css">
	
	<script type="text/javascript" src="ckeditor/ckeditor.js"></script>  
	<script type="text/javascript" src="ckeditor/config.js"></script>
	
	<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
	<script src="<%=basePath%>My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script type="text/javascript">
		function check(){
			var courseName = trim($("#courseName").val());
			if(courseName != ""){
				$("#courseNameMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
				return true;
			}else{
				$("#courseNameMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>课程名称不能为空！</font>");
				$("#courseNameMsg").focus();
				return false;
			}
			var courseDate = trim($("#courseDate").val());
			if(courseDate != ""){
				$("#courseDateMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
				return true;
			}else{
				$("#courseDateMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>录入时间不能为空！</font>");
				$("#courseDateMsg").focus();
				return false;
			}
			var courseNote = trim($("#courseNote").val());
			if(courseNote != ""){
				$("#courseNoteMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
				return true;
			}else{
				$("#courseNoteMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>课程介绍不能为空！</font>");
				$("#courseNoteMsg").focus();
				return false;
			}
			var courseLogo = trim($("#courseLogo").val());
			if(courseLogo != ""){
				$("#courseLogoMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
				return true;
			}else{
				$("#courseLogoMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>课程展示图不能为空！</font>");
				$("#courseLogoMsg").focus();
				return false;
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
		
		function doUploadImg(){
			var url = "ImgCutUploadServlet?method=getPathParam&pathParam=studentImg&picWidth=222&picHeight=305";
			window.open(url, "uploadFiles", "height=425, width=1000, top=120, left=200, toolbar=no, menubar=no, scrollbars=yes, resizable=no, location=no, status=no");
		}
		
		function callbackPfn(data){
			var couImg = data.arg.src;
		   	if(couImg != "" && couImg != null && couImg != "undefined"){
				$("#courseLogo").val(couImg);
				$("#couImg").attr("src", couImg);
			}
		}
		
		
	</script>
	
  </head>
  
  <body>
  	<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
  	<div class="place"><span>位置：</span>
    	<ul class="placeul">
        	<li><a href="javascript:void(0)">首页</a></li>
        	<c:choose>
				<c:when test="${course != null }">
					<li><a href="javascript:void(0)">修改课程信息</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="javascript:void(0)">新增课程信息</a></li> 
				</c:otherwise>
			</c:choose>
    	</ul>
	</div>
	<div align="left" style="margin-top: 5px;">
		<form action="CourseServlet?method=save" id="edit" name="edit" method="post">
			<table border="0">
				<tr>
					<td colspan="3" class="formtitle">课程编辑</td>
				</tr>
				<tr>
					<td>
						<input type="hidden" id="courseId" name="courseId" value="${course.cou_id }">
					</td>
					<td>
						<input type="hidden" id="courseDelete" name="courseDelete" value="${course.cou_delete }">
					</td>
				</tr>
				<tr>
					<td width="150" class="tdrsname" align="right">课程名称：</td>
					<td width="360" class="tdrsname">
						<input class="dfinput" type="text" id="courseName" name="courseName" value="${course.cou_name }" style="width: 360px;"> 
					</td>
					<td width="auto" class="tdrsname"><span id="courseNameMsg"></span></td>
					
				</tr>
				
				
				<tr>
					<td class="tdrsname" align="right">课程介绍：</td>
					<td  class="tdrsname">
						<textarea class="ckeditor" id="courseNote" name="courseNote" value="${course.cou_note }">${course.cou_note }</textarea>
					</td>
					<td width="auto" class="tdrsname"><span id="courseNoteMsg"></span></td>
				</tr>
				
				
				<%-- <tr>
					<td class="tdrsname" align="right">课程展示图：</td>
					<td  class="tdrsname">
						
						<input type="hidden"  id="courseLogo" name="courseLogo" value="${course.cou_logo } "/>
        					<c:if test="${not empty course.cou_logo}">
	        					<img id="couImg" src="${course.cou_logo }" width="100" width="100" height="137" title="点此处上传图片" style="cursor:pointer;" onclick="doUploadImg()"/><br>
	        					<span style="color:red">&nbsp;点击图片上传图片，建议图片大小为222*305</span>
        					</c:if>
        					<c:if test="${empty course.cou_logo }">
	        					<img id="couImg" src="<%=basePath%>images/touxiang/touxiang.jpg" width="100" width="100" height="137" title="点此处上传图片" style="cursor:pointer;" onclick="doUploadImg()"/><br>
	        					<span style="color:red">&nbsp;点击图片上传图片，建议图片大小为222*305</span>
        					</c:if>
        					
					</td>
					<td width="auto" class="tdrsname"><span id="courseLogoMsg"></span></td>
				</tr> --%>
				
				
				<tr>
					<td class="tdrsname" align="right">录入人：</td>
					<td class="tdrsname">
						<input class="dfinput" type="text" id="courseAdder" name="courseAdder" value="${course.cou_adder }" style="width: 360px;"> 
					<td width="auto" class="tdrsname"><span id="courseAdderMsg"></span></td>
				</tr>
				<tr>
					<td  class="tdrsname">
						<input class="dfinput" type="hidden" id="courseDate" name="courseDate" value=<fmt:formatDate value="<%=new Date() %>" pattern="yyyy-MM-dd"/>   style="height :30px;width:360px" > 
					</td>
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
