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
    
    <title>学员信息列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css">
	<script src="<%=basePath%>js/jquery.js" type="text/javascript"></script>
	<script src="<%=basePath%>My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	
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
		
		function callback(datas){
			window.opener.getStudentInfo(datas);
            window.self.close();
		}
		function cancel(){
            window.self.close();
        }
		
	</script>
			
  </head>
  
  <body>
    <!-- 顶部路径栏 -->
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="javascript:void(0)">首页</a></li>
			<li><a href="javascript:void(0);">学员信息管理</a></li>
		</ul>
	</div>
	<div class="rightinfo">	
		<c:if test="${method =='selectStudent' || method=='query' }">
			<table class="tablelist"
			style="table-layout:fixed;word-wrap:break-word;word-break:break-all;">
			<thead>
				<tr>
					<th width="4%">选择</th>
					<th width="8%">班级</th>
					<th width="10%">姓名</th>
					<th width="5%">性别</th>
					<th width="20%">毕业院校</th>
					<th width="20%">专业</th>
					<th width="10%">电话</th>
					<th width="auto">入班时间</th>
				</tr>
			</thead>
			<!-- EL表达式中 empty为关键字 用来判断对象是否为空 -->
			<c:if test="${empty stuvList}">
				<tr class="odd">
					<td colspan="11" align="center">没有查询到相关记录！</td>
				</tr>
			</c:if>
			<c:forEach items="${stuvList }" var="stuvBean">
				<tr class="odd">
					<td><input type="radio" name="stu_id" value="${stuvBean.stu_id }"></td>
					<td>${stuvBean.cla_name }</td>
					<td>
						<a href="StudentServlet?method=view&stu_id=${stuvBean.stu_id } " >
							${stuvBean.stu_name }
						</a>
					</td>
					<c:forEach items="${select_stu_sex }" var="data">
					<c:choose>
								<c:when test="${stuvBean.stu_sex==data.data_num }">
								<td>${data.data_name }</td>
								</c:when>
					</c:choose>
					</c:forEach>
					<td>${stuvBean.stu_graduation }</td>
					<td>${stuvBean.stu_major }</td>
					<%-- <td>${stuvBean.stu_education }</td> --%>
					<td>${stuvBean.stu_phone }</td>
					<td>${stuvBean.cla_starttime }</td>
				</tr>
			</c:forEach>
		</table>	
			
		</c:if>
		

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
		<c:if test="${method =='selectStudent' }">
				<table>
				<tr style="line-height:50px; ">
					<!-- 按钮换成背景图片 -->
					<td>&nbsp;</td>
					<td colspan="1" align="center"><input class="button"
						type="button" name="确认" value="确认" onClick="doAdd()" />
					&nbsp;&nbsp;&nbsp;&nbsp;<input class="button"
						type="button" name="返回" value="返回" onClick="cancel()"/>
					</td><td colspan="3">&nbsp;</td>
				</tr>
				</table>
			
		</c:if>
	</div>
  </body>
</html>
