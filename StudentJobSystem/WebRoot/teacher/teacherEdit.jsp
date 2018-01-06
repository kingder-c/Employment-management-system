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
    
    <title>老师编辑页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css">
	<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
	<script type="text/javascript">
		function check(){
			var tea_name = trim($("#tea_name").val());
			if(tea_name != ""){
				$("#tea_nameMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
				return true;
			}else{
				$("#tea_nameMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>分类名称不能为空！</font>");
				$("#tea_name").focus();
				return false;
			}
			<%-- var weixin_workid = trim($("#weixin_workid").val());
			if(weixin_workid != ""){
				$("#weixin_workidMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
				return true;
			}else{
				$("#weixin_workidMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>分类名称不能为空！</font>");
				$("#weixin_workid").focus();
				return false;
			}
			
			var tea_mail = trim($("#tea_mail").val());
			if(tea_mail != ""){
				$("#tea_mailMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
				return true;
			}else{
				$("#tea_mailMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>分类名称不能为空！</font>");
				$("#tea_mail").focus();
				return false;
			} --%>
			var tea_tel = trim($("#tea_tel").val());
			if(tea_tel != ""){
				$("#tea_telMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
				return true;
			}else{
				$("#tea_telMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>电话号码不能为空！</font>");
				$("#tea_tel").focus();
				return false;
			}
			var tea_state = trim($("#tea_state").val());
			if(tea_state != ""){
				$("#tea_stateMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
				return true;
			}else{
				$("#tea_stateMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>教师状态不能为空！</font>");
				$("#tea_state").focus();
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
	</script>


  </head>
  
  <body>
     <!-- 顶部路径栏 -->
	<div class="place"><span>位置：</span>
    	<ul class="placeul">
        	<li><a href="javascript:void(0)">首页</a></li>
        	<c:choose>
				<c:when test="${teacher != null }">
					<li><a href="javascript:void(0)">修改老师信息</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="javascript:void(0)">新增老师信息</a></li> 
				</c:otherwise>
			</c:choose>
    	</ul>
	</div>
	<div align="left" style="margin-top: 5px;">
		<form action="TeacherServlet?method=save" id="edit" name="edit" method="post">
			<table border="0">
				<tr>
					<td colspan="3" class="formtitle">老师信息编辑</td>
				</tr>
				<tr>
					<td>
						<input type="hidden" id="tea_id" name="tea_id" value="${teacher.tea_id }">
					</td>
				</tr>
				<tr>
					<td width="150" class="tdrsname" align="right">老师姓名：</td>
					<td width="600" class="tdrsname">
						<input class="dfinput" type="text" id="tea_name" name="tea_name" value="${teacher.tea_name }" style="width: 300px;"> 
					</td>
					<td width="auto" class="tdrsname"><span id="tea_nameMsg"></span></td>
					
				</tr>
				<%-- <tr>
					<td class="tdrsname" align="right">老师工号：</td>
					<td  class="tdrsname">
						<input class="dfinput" type="text" id="weixin_workid" name="weixin_workid" value="${teacher.weixin_workid }" style="width: 100px;"> 
					</td>
					<td width="auto" class="tdrsname"><span id="weixin_workidMsg"></span></td>
				</tr> --%>
				
				<%-- <tr>
					<td class="tdrsname" align="right">老师邮件：</td>
					<td  class="tdrsname">
						<input class="dfinput" type="text" id="tea_mail" name="tea_mail" value="${teacher.tea_mail }" style="width: 100px;"> 
					</td>
					<td width="auto" class="tdrsname"><span id="tea_mailMsg"></span></td>
				</tr> --%>
				<tr>
					<td class="tdrsname" align="right">老师电话：</td>
					<td  class="tdrsname">
						<input class="dfinput" type="text" id="tea_tel" name="tea_tel" value="${teacher.tea_tel }" style="width: 300px;"> 
					</td>
					<td width="auto" class="tdrsname"><span id="tea_telMsg"></span></td>
				</tr>
 				<tr>
					<td class="tdrsname" align="right">老师状态：</td>
					<td class="tdrsname">
						<select class="dfinput" name="tea_state" type="text" style="width: 300px;>  
					      <option value ="">请选择</option>  
					      <option value ="在职">在职</option>  
					      <option value="休假">休假</option>  
					      <option value="离职">离职</option>  
					    </select> 
					<td width="auto" class="tdrsname"><span id="tea_stateMsg"></span></td>
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
