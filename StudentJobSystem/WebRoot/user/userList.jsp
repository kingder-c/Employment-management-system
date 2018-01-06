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
    
    <title>My JSP 'userList.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css">
	<script src="<%=basePath%>js/jquery.js" type="text/javascript"></script>
	
	<script type="text/javascript">
		function doSubmit(currentPage) {
			$("#pageIndex").val(currentPage);
			$("#search").submit();
		}
		
		function goPage() {
			var goPage = $("#goPage").val();
			var totalPage = $("#totalPage").val();
			if(totalPage == 0){
				$("#pageIndex").val(1);
			}else {
				if (goPage > totalPage) {
					$("#pageIndex").val(totalPage);
				} else {
					$("#pageIndex").val(goPage);
				}
			}
			$("#search").submit();
		}
		
		function doInsert(){
			window.location.href = "<%=basePath%>UserServlet?method=edit";
		}
		
		function doDelete(){
			var radioes = $("input[name='user_id']:checked").val();
			if (radioes == null || radioes == undefined){
				alert("请选中要删除的纪录！");return;
			}else if (confirm("确定要删除选定的记录吗？\n")){
				window.location.href = "<%=basePath%>UserServlet?method=dele&user_id="+radioes;
			}
		}
		function doUpdate(){
			var radioes = $("input[name='user_id']:checked").val();
			if (radioes == null || radioes == undefined){
				alert("请选中要修改的纪录！");return;
			}else{
				window.location.href = "<%=basePath%>UserServlet?method=edit&user_id="+radioes;
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
			<li><a href="javascript:void(0);">用户信息管理</a></li>
		</ul>
	</div>
	<div class="rightinfo">
		<form action="<%=basePath%>UserServlet?method=query" method="post" id="search" name="search">
			<!-- 隐藏域 -->
			<div>
				<input type="hidden" name="pageIndex" id="pageIndex" value="${currentPage }">
				<input type="hidden" name=totalPage id="totalPage" value="${totalPage }">
			</div>

			<div class="tools" >
			<ul class="seachform">
				<li>
					<label>根据用户名查询</label>
					<input class="scinput" name="userName" type="text" value="${userName }" />
				</li>
				<li>
					<label>根据用户类型查询</label>
					<select class="scinput" name="userType" type="text"/> 			
						<c:choose>
							<c:when test="${not empty userType }">
								<option value=${userType } checked>${userType }</option>
								<option value="">--清除条件--</option>
							</c:when>
							<c:otherwise>
								<option value="">--请选择--</option>
							</c:otherwise>
						</c:choose>
						<c:forEach items="${select_user_Type }" var="role">
							<c:choose>
							<c:when test="${not empty userType }">
								<option value=${role.role_id } >-${role.role_name }-</option>
							</c:when>
							<c:otherwise>
								<option value=${role.role_id } >-${role.role_name }-</option>
							</c:otherwise>
							</c:choose>
						</c:forEach>
 					</select>
				</li>
				<%-- <li>
					<label>根据用户审核状态查询</label>
					<select class="scinput" name="userCheck" />		
						<c:choose>
							<c:when test="${not empty userCheck }">
								<option value=${userCheck } checked>${userCheck }</option>
								<option value="">--清除条件--</option>
								<c:forEach items="${select_user_check }" var="data">
									<option value=${data.data_num } >-${data.data_name }-</option>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<option value="">--请选择--</option>
								<c:forEach items="${select_user_check }" var="data">
									<option value=${data.data_num } >-${data.data_name }-</option>
								</c:forEach>
							</c:otherwise>
						</c:choose>
 					</select>
				</li> --%>
				<li><input type="button" class="scbtn" value="查询" onClick="doSubmit(1)" /></li>
			</ul>
			<ul style="text-align: right;" class="toolbar" >
				<li>
					<a href="javascript:void(0);" onclick="doInsert()">
					<img alt="添加" src="images/t01.png"><span style="margin-top: -2px;">添加</span></a>
				</li>	 
				<li>	
					<a href="javascript:void(0);" onclick="doUpdate()">
					<img alt="修改" src="images/t02.png"><span style="margin-top: -2px;">修改</span></a>
				</li>	 
				<li>	
					<a href="javascript:void(0);" onclick="doDelete()">
					<img alt="删除" src="images/t03.png"><span style="margin-top: -2px;">删除</span></a>
				</li>

			</ul>
			</div>
		</form>
		<table class="tablelist"
			style="table-layout:fixed;word-wrap:break-word;word-break:break-all;">
			<thead align="center">
				<tr>
					<th width="10%">选择</th>
					<th width="10%">用户id</th>
					<th width="15%">用户名</th>
					<th width="15%">用户密码</th>
					<th width="15%">用户类型</th>
					<th width="20%">用户描述</th>
					<!-- <th width="15%">用户审核状态</th> -->
				</tr>
			</thead>
			<!-- EL表达式中 empty为关键字 用来判断对象是否为空 -->
			<c:if test="${empty userList}">
				<tr class="odd">
					<td colspan="7" align="center">没有查询到相关记录！</td>
				</tr>
			</c:if>
			<c:forEach items="${userList }" var="user">
				<tr class="odd">
					<td><input type="radio" name="user_id" value="${user.user_id }"></td>
					<td>${user.user_id }</td>
					<td>${user.user_name }</td>
					<td>${user.user_password }</td>
					<c:forEach items="${select_user_Type }" var="role">
						<c:choose>
							<c:when test="${user.user_type==role.role_id }">
							<td>${role.role_name }</td>
							</c:when>
						</c:choose>
					</c:forEach>
					<td>${user.user_desc }</td>
					
					<%-- <c:forEach items="${select_user_check }" var="data">
						<c:choose>
							<c:when test="${user.user_checkstatus==data.data_num }">
							<td>${data.data_name }</td>
							</c:when>
						</c:choose>
					</c:forEach> --%>
				</tr>
			</c:forEach>
		</table>

		<!-- 分页功能 开始 -->
		<div class="pagin">
			<div>
				共&nbsp;<i class="blue">${totalNum }</i>&nbsp;条记录，当前显示第&nbsp;<i
					class="blue">${currentPage }&nbsp;</i>页&nbsp;/&nbsp;共&nbsp;<i
					class="blue">${totalPage }&nbsp;</i>页&nbsp;&nbsp;
				跳到&nbsp;第&nbsp;<input class="goPage" type="text" id="goPage"
					name="goPage" value="${currentPage }">&nbsp;页&nbsp;&nbsp; <input
					type="button" value="跳转" onclick="goPage()">
			</div>
			<div class="paginList">
				<c:if test="${currentPage == 1 }">
					<a href="javascript:void(0);">首页</a>&nbsp;
					<a href="javascript:void(0);">上一页</a>&nbsp;
				</c:if>
				<c:if test="${currentPage > 1 }">
					<a class="blue" href="javascript:void(0);" onClick="doSubmit(1)">首页</a>&nbsp;
					<a class="blue" href="javascript:void(0);"
						onClick="doSubmit(${currentPage-1})">上一页</a>&nbsp;
				</c:if>
				<c:if test="${currentPage >= totalPage }">
					<a href="javascript:void(0);">下一页</a>&nbsp;
					<a href="javascript:void(0);">尾页</a>&nbsp;
				</c:if>
				<c:if test="${currentPage < totalPage }">
					<a class="blue" href="javascript:void(0);"
						onClick="doSubmit(${currentPage+1 })">下一页</a>&nbsp;
					<a class="blue" href="javascript:void(0);"
						onClick="doSubmit(${totalPage})">尾页</a>&nbsp;
				</c:if>
			</div>
		</div>
		<!-- 分页功能 开始 -->

	</div>
  </body>
</html>
