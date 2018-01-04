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
    
    <title>My JSP 'authEdit.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css">
	<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
	<script type="text/javascript">
		
		function check(){
			var auth_name = trim($("#auth_name").val());
			if(auth_name != ""){
				$("#auth_nameMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
				return true;
			}else{
				$("#auth_nameMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>分类名称不能为空！</font>");
				$("#auth_name").focus();
				return false;
			}
			var auth_path = trim($("#auth_path").val());
			if(auth_path != ""){
				$("#auth_pathMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
				return true;
			}else{
				$("#auth_pathMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>分类名称不能为空！</font>");
				$("#auth_path").focus();
				return false;
			}
			var auth_parentid = trim($("#auth_parentid").val());
			if(auth_parentid != ""){
				$("#auth_parentidMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
				return true;
			}else{
				$("#auth_parentidMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>分类名称不能为空！</font>");
				$("#auth_parentid").focus();
				return false;
			}
			var auth_description = trim($("#auth_description").val());
			if(auth_description != ""){
				$("#auth_descriptionMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
				return true;
			}else{
				$("#auth_descriptionMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>分类名称不能为空！</font>");
				$("#auth_description").focus();
				return false;
			}
			var auth_state = trim($("#auth_state").val());
			if(auth_state != ""){
				$("#auth_stateMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
				return true;
			}else{
				$("#auth_stateMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>分类名称不能为空！</font>");
				$("#auth_state").focus();
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
  <div class="place"><span>位置：</span>
    	<ul class="placeul">
        	<li><a href="javascript:void(0)">首页</a></li>
        	<c:choose>
				<c:when test="${auth != null }">
					<li><a href="javascript:void(0)">修改菜单信息</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="javascript:void(0)">新增菜单信息</a></li> 
				</c:otherwise>
			</c:choose>
    	</ul>
	</div>
	<div align="left" style="margin-top: 5px;">
  <form action = "AuthServlet?method=save" id="edit" name="edit" method = "post">
	<table>
	    <tr>
			<td colspan="3" class="formtitle">权限信息编辑</td>
		</tr>
		<tr>
			<td><input class="dfinput" type = "hidden" name = "auth_id" value = "${auth.auth_id}" ></td>
		</tr>
		<tr>
			<td width="150" class="tdrsname" align="right">名称：</td>
			<td width="600" class="tdrsname">
				<input class="dfinput" type="text" id="auth_name" name="auth_name" value="${auth.auth_name }" style="width: 400px;"> 
			</td>
			<td width="auto" class="tdrsname"><span id="auth_nameMsg"></span></td>
		</tr>
		<tr>
			<td width="150" class="tdrsname" align="right">链接路径：</td>
			<td width="600" class="tdrsname">
				<input class="dfinput" type="text" id="auth_path" name="auth_path" value="${auth.auth_path }" style="width: 400px;"> 
			</td>
			<td width="auto" class="tdrsname"><span id="auth_pathMsg"></span></td>
			
		</tr>
		<tr>
			<td class="tdrsname" align="right">父项：</td>
			<td class="tdrsname">
				<select class="dfinput" name="auth_parentid" type="text" style="width: 400px;">  
			        <option value = "">请选择</option>  
					<option value = "学员管理">学员管理</option>
					<option value = "教师管理">教师管理</option>
					<option value = "班级管理">班级管理</option>
			        <option value = "课程管理">课程管理</option>
					<option value = "企业管理">企业管理</option>
					<option value = "学员面试信息管理">学员面试信息管理</option>
					<option value = "学院就业信息管理">学院就业信息管理</option>
					<option value = "学员回访信息管理">学员回访信息管理</option>
					<option value = "用户管理">用户管理</option>
					<option value = "用户角色管理">用户角色管理</option>
					<option value = "用户权限管理">用户权限管理</option>
					<option value = "数据字典维护">数据字典维护</option>  
			    </select> 
			<td width="auto" class="tdrsname"><span id="auth_parentidMsg"></span></td>
		</tr>
		
		<tr>
			<td width="150" class="tdrsname" align="right">描述：</td>
			<td width="600" class="tdrsname">
				<textarea rows = "10" cols = "64" name = "auth_description">${auth.auth_description}</textarea>
			</td>
			<td width="auto" class="tdrsname"><span id="auth_descriptionMsg"></span></td>
		</tr>
		<tr>
			<td class="tdrsname" align="right">状态：</td>
			<td class="tdrsname">
				<select class="dfinput" id="auth_state" name="auth_state" type="text" style="width: 400px;">  
			        <option value = "">请选择</option>
					<option value = "0">正常</option>
					<option value = "1">关闭</option>
			    </select> 
			<td width="auto" class="tdrsname"><span id="com_statusMsg"></span></td>
		</tr>
		<tr style="line-height:80px; ">
			<!-- 按钮换成背景图片 -->
			<td>&nbsp;</td>
			<td colspan="1" align="center"><input class="button"
				type="button" name="提交" value="提交" onClick="doSubmit()" />
			&nbsp;&nbsp;&nbsp;&nbsp;<input class="button"
				type="button" name="返回" value="返回" onClick="javascript:history.back();"/>
			</td><td colspan="3">&nbsp;</td>
		</tr>
		
	</table>
	</form>
	</div>
  </body>
</html>
