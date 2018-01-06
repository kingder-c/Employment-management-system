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
    
    <title>老师信息列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
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
			window.location.href = "<%=basePath%>TeacherServlet?method=edit";
		}
		function doDelete(){
			var radioes = $("input[name='tea_id']:checked").val();
			if (radioes == null || radioes == undefined){
				alert("请选中要删除的纪录！");return;
			}else if (confirm("确定要删除选定的记录吗？\n")){
				window.location.href = "<%=basePath%>TeacherServlet?method=dele&tea_id="+radioes;
			}
		}
		function doUpdate(){
			var radioes = $("input[name='tea_id']:checked").val();
			if (radioes == null || radioes == undefined){
				alert("请选中要修改的纪录！");return;
			}else{
				window.location.href = "<%=basePath%>TeacherServlet?method=edit&tea_id="+radioes;
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
			<li><a href="javascript:void(0);">老师信息管理</a></li>
		</ul>
	</div>
	<div class="rightinfo">
		<form action="<%=basePath%>TeacherServlet?method=query" method="post" id="search" name="search">
			<!-- 隐藏域 -->
			<div>
				<input type="hidden" name="pageIndex" id="pageIndex" value="${currentPage }">
				<input type="hidden" name=totalPage id="totalPage" value="${totalPage }">
			</div>

			<ul class="seachform">
				<li><label>根据教师名称查询</label>
					<input class="scinput" name="tea_name" type="text" value="${tea_name }" /></li>
				<%-- <li><label>根据工号查询</label>
					<input class="scinput" name="weixin_workid" type="text" value="${weixin_workid }" /></li> --%>
				<li><label>根据教师状态查询</label>
					    <select class="scinput" name="tea_state" type="text">  
					      <option value="">--请选择--</option>  
					      <option value="离职">离职</option>  
					      <option value="休假">休假</option>  
					      <option value="在职">在职</option>  
					    </select> </li>
				<input type="button" class="scbtn" value="查询" onClick="doSubmit(1)" />
			</ul>
			<ul style="text-align: right;" class="toolbar">
				<li><a href="javascript:void(0);" onclick="doInsert()">
				    <img alt="添加" src="images/t01.png"><span style="margin-top: -2px;">添加</span> </a></li><li><a
					href="javascript:void(0);" onclick="doUpdate()">
					<img alt="修改" src="images/t02.png"><span style="margin-top: -2px;">修改</span></a></li> <li><a
					href="javascript:void(0);" onclick="doDelete()">
					<img alt="删除" src="images/t03.png"><span style="margin-top: -2px;">删除</span></a></li>

			</ul>
		</form>
		<table class="tablelist"
			style="table-layout:fixed;word-wrap:break-word;word-break:break-all;">
			<thead>
				<tr>
					<th width="5%">选择</th>
				    <!-- <th width="15%">工号</th>  -->
					<th width="15%">名字</th>
					<th width="15%">老师电话</th>
					<th width="15%">状态</th>
					<!-- <th width="auto">老师邮件</th> -->
				</tr>
			</thead>
			<!-- EL表达式中 empty为关键字 用来判断对象是否为空 -->
			<c:if test="${empty list}">
				<tr class="odd">
					<td colspan="5" align="center">没有查询到相关记录！</td>
				</tr>
			</c:if>
			<c:forEach items="${list }" var="teacherBean">
				<tr class="odd">
					<td><input type="radio" name="tea_id" value="${teacherBean.tea_id }"></td>
					<%-- <td>${teacherBean.weixin_workid }</td> --%>
					<td>
						<a href="TeacherServlet?method=view&tea_id=${teacherBean.tea_id } " >
							${teacherBean.tea_name }
						</a>
					</td>
					<td>${teacherBean.tea_tel }</td>
					<td>${teacherBean.tea_state }</td>
					<%-- <td>${teacherBean.tea_mail }</td> --%>
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
