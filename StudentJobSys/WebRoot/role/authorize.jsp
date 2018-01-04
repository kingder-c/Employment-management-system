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
    
    <title>My JSP 'authorize.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="<%=basePath%>js/jquery-1.4.4.min.js" type="text/javascript"></script>
	<script src="<%=basePath%>js/grant.js" type="text/javascript"></script>	
	
	<script type="text/javascript">
	$(function(){
		var str = $("#checkbox_check").val();
			for(var i = 0;i<str.length;i++){
				if(str[i]=="1"){
					$(":checkbox:eq("+i+")").attr("checked", true);
				}
			}
	
	})
	function parent(){
		var student = document.getElementsByName("学员管理");
		if(student[0].checked==true){
			student[1].checked = true;
			student[2].checked = true;
		}
		if(student[0].checked==false){
			student[1].checked = false;
			student[2].checked = false;
		}
		var teacher = document.getElementsByName("教师管理");
		if(teacher[0].checked==true){
			teacher[1].checked = true;
		}
		if(teacher[0].checked==false){
			teacher[1].checked = false;
		}
		var classes = document.getElementsByName("班级管理");
		if(classes[0].checked==true){
			classes[1].checked = true;
		}
		if(classes[0].checked==false){
			classes[1].checked = false;
		}
		var course = document.getElementsByName("课程管理");
		if(course[0].checked==true){
			course[1].checked = true;
		}
		if(course[0].checked==false){
			course[1].checked = false;
		}
		var company = document.getElementsByName("企业管理");
		if(company[0].checked==true){
			company[1].checked = true;
			company[2].checked = true;
		}
		if(company[0].checked==false){
			company[1].checked = false;
			company[2].checked = false;
		}
		var returninfo = document.getElementsByName("学员回访信息管理");
		if(returninfo[0].checked==true){
			returninfo[1].checked=true;
		}
		if(returninfo[0].checked==false){
			returninfo[1].checked=false;
		}
		var jobinfo = document.getElementsByName("学员就业信息管理");
		if(jobinfo[0].checked==true){
			jobinfo[1].checked=true;
		}
		if(jobinfo[0].checked==false){
			jobinfo[1].checked=false;
		}
		var interviewinfo = document.getElementsByName("学员面试信息管理");
		if(interviewinfo[0].checked==true){
			interviewinfo[1].checked=true;
		}
		if(interviewinfo[0].checked==false){
			interviewinfo[1].checked=false;
		}
		var user = document.getElementsByName("用户管理");	
		if(user[0].checked==true){
			user[1].checked=true;
			user[2].checked=true;
			user[3].checked=true;
		}
		if(user[0].checked==false){
			user[1].checked=false;
			user[2].checked=false;
			user[3].checked=false;
		}
		var role = document.getElementsByName("角色管理");
		if(role[0].checked==true){
			role[1].checked=true;
		}
		if(role[0].checked==false){
			role[1].checked=false;
		}
		var auth = document.getElementsByName("用户权限管理");
		if(auth[0].checked==true){
			auth[1].checked=true;
		}
		if(auth[0].checked==false){
			auth[1].checked=false;
		}
		var data = document.getElementsByName("数据字典维护");
		if(data[0].checked==true){
			data[1].checked=true;
		}
		if(data[0].checked==false){
			data[1].checked=false;
		}
	}
	function child(){
		var student = document.getElementsByName("学员管理");
		if(student[1].checked==false&&student[2].checked==false){
			student[0].checked = false;
		}
		if(student[1].checked==true||student[2].checked==true){
			student[0].checked = true;
		}
		var teacher = document.getElementsByName("教师管理");
		if(teacher[1].checked==false){
			teacher[0].checked = false;
		}
		if(teacher[1].checked==true){
			teacher[0].checked = true;
		}
		var classes = document.getElementsByName("班级管理");
		if(classes[1].checked==false){
			classes[0].checked = false;
		}
		if(classes[1].checked==true){
			classes[0].checked = true;
		}
		var course = document.getElementsByName("课程管理");
		if(course[1].checked==false){
			course[0].checked = false;
		}
		if(course[1].checked==true){
			course[0].checked = true;
		}
		var company = document.getElementsByName("企业管理");
		if(company[1].checked==false&&company[2].checked == false){
			company[0].checked = false;				
		}
		if(company[1].checked==true||company[2].checked == true){
			company[0].checked = true;				
		}
		var interviewinfo = document.getElementsByName("学员面试信息管理");
		if(interviewinfo[1].checked==false){
			interviewinfo[0].checked=false;
		}
		if(interviewinfo[1].checked==true){
			interviewinfo[0].checked=true;
		}
		var jobinfo = document.getElementsByName("学员就业信息管理");
		if(jobinfo[1].checked==false){
			jobinfo[0].checked=false;
		}
		if(jobinfo[1].checked==true){
			jobinfo[0].checked=true;
		}
		var returninfo = document.getElementsByName("学员面试信息管理");
		if(returninfo[1].checked==false){
			returninfo[0].checked=false;
		}
		if(returninfo[1].checked==true){
			returninfo[0].checked=true;
		}
		var user = document.getElementsByName("用户角色权限");	
		if(user[1].checked==false&&user[2].checked==false&&user[3].checked==false){
			user[0].checked=false;
		}
		if(user[1].checked==true||user[2].checked==true||user[3].checked==true){
			user[0].checked=true;
		}
		var data = document.getElementsByName("数据字典维护");
		if(data[1].checked==false){
			data[0].checked=false;
		}
		if(data[1].checked==true){
			data[0].checked=true;
		}
	}
	$("#grant").click(function(){
			var role_id = $("#role_id").val();
			var checkbox = $(":checkbox");
			var auth_ids = "";
			for(var i = 0;i<checkbox.length;i++){
				if($(":checkbox:eq("+i+")").attr("checked")==true){
					auth_ids = auth_ids + "1";
				}else{
					auth_ids = auth_ids + "0";
				}
				
			}
			window.location.href = "<%=path%>/RoleServlet?method=grantSave&role_id="+role_id + "&auth_ids="+auth_ids;					
		})
		$("#allCheck").click(function(){
			var a = $(":checkbox");
			for(var i = 0;i<a.length;i++){
				$(":checkbox:eq("+i+")").attr("checked",true);
			}
		})

	
	</script>

  </head>
  
  <body>
    <!-- 顶部路径栏 -->
	<div>
			<li><a href="javascript:void(0);">权限列表</a></li>
	</div>
	<input type = "hidden" name = "role_id" id = "role_id" value = "${role_id}">
 	 <input type = "hidden" name = "checkbox_check" id = "checkbox_check" value = "${str}">
  	<button value = "全选" id = "allCheck">全选</button>
 	
	<table class="tablelist" style="table-layout:fixed;word-wrap:break-word;word-break:break-all;">
		<tr>
			<th width="3%">选择</th>
			<th width="3%">编号</th>
			<th width="15%">名称</th>
			
		</tr>
	<c:forEach items = "${parentlist }" var = "list">
		<tr>
			<td><input type = "checkbox" class = "${list.auth_name}" name = "${list.auth_name}"  value = "${list.auth_name}" onclick = "parent()"></td>
			<td>${list.auth_id}</td>
			<td><img src = "images/fAuth.jpg">${list.auth_name}</td>
			
	   </tr> 
		<c:forEach items="${childlist }" var="sonList">
			<c:if test="${sonList.auth_parentid == list.auth_name  }">
				<tr>
					<td><input type = "checkbox" class = "${sonList.auth_name}" name = "${sonList.auth_name}"  value = "${sonList.auth_name}" onclick = "child()"></td>
					<td>${sonList.auth_id}</td>
					<td><img src = "images/sAuth.gif">${sonList.auth_name}</td>
					
				</tr>  	
			</c:if>
		</c:forEach>
	 	
  </c:forEach>
	</table>
	<button id = "grant" onclick = "doGrant()">授权</button>
  </body>
</html>
