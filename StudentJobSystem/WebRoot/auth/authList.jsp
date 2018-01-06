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
    
    <title>权限信息列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css">
	<meta http-equiv="description" content="This is my page">
	<script src="<%=basePath%>js/jquery-1.4.4.min.js" type="text/javascript"></script>
	<script type="text/javascript">
		
		function doInsert(){
				window.location.href = "<%=basePath%>AuthServlet?method=edit";
		}
		
		function doDelete(){
		var radioes = $("input[name='auth_id']:checked").val();
			if (radioes == null || radioes == undefined){
				alert("请选中要删除的纪录！");return;
			}else if (confirm("确定要删除选定的记录吗？\n")){
				window.location.href = "<%=basePath%>AuthServlet?method=dele&auth_id="+radioes;
			}

		}
		function doUpdate(){
			var radioes = $("input[name='auth_id']:checked").val();
			if (radioes == null || radioes == undefined){
				alert("请选中要修改的纪录！");return;
			}else{
				window.location.href = "<%=basePath%>AuthServlet?method=edit&auth_id="+radioes;
			}
		}
	</script>

  </head>
  
  <body>
    <!-- 顶部路径栏 -->
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="javascript:void(0)">首页</a></li>
			<li><a href="javascript:void(0);">权限管理</a></li>
		</ul>
	</div>
   	<input type = "hidden" id = "childlist" value = "${childlist }">
 	<ul class="toolbar">
		        <a href="javascript:void(0);" onclick="doInsert()"><li><span><img src="images/t01.png" /></span>添加</li></a>
		        <a href="javascript:void(0);" onclick="doUpdate()"><li><span><img src="images/t02.png" /></span>修改</li></a>
		        <a href="javascript:void(0);" onclick="doDelete()"><li><span><img src="images/t03.png" /></span>删除</li></a>
		    </ul>
	<table class="tablelist" style="table-layout:fixed;word-wrap:break-word;word-break:break-all;">
		<tr>
			<th width="4%">选择</th>
			<th width="4%">编号</th>
			<th width="15%">名称</th>
			<th>链接路径</th>
			<th>描述</th>
			<th width="15%">状态</th>
		</tr>
	<c:forEach items = "${parentlist }" var = "list">
		<tr>
			<td><input type = "radio" name = "auth_id" value = "${list.auth_id}"></td>
			<td>${list.auth_id}</td>
			<td><img src = "images/fAuth.jpg">${list.auth_name}</td>
			<td>${list.auth_path}</td>
			<td>${list.auth_description}</td>
			<td>${list.auth_state}</td>
	   </tr> 
		<c:forEach items="${childlist }" var="sonList">
			<c:if test="${sonList.auth_parentid == list.auth_name  }">
				<tr>
					<td><input type = "radio" name="auth_id" value="${sonList.auth_id}"></td>
					<td>${sonList.auth_id}</td>
					<td><img src = "images/sAuth.gif">${sonList.auth_name}</td>
					<td>${sonList.auth_path}</td>
					<td>${sonList.auth_description}</td>
					<td>${sonList.auth_state}</td>
				</tr>  	
			</c:if>
		</c:forEach>
	 	
  </c:forEach>
	</table>
  </body>
</html>
