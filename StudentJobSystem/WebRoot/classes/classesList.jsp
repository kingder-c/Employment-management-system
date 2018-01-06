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
    
    <title>班级信息列表</title>
    
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
		
		function doInsert(){
			window.location.href = "<%=basePath%>ClassesServlet?method=edit";
		}
		
		function doDelete(){
			var radioes = $("input[name='cla_id']:checked").val();
			if (radioes == null || radioes == undefined){
				alert("请选中要删除的纪录！");return;
			}else if (confirm("确定要删除选定的记录吗？\n")){
				window.location.href = "<%=basePath%>ClassesServlet?method=dele&cla_id="+radioes;
			}
		}
		function doUpdate(){
			var radioes = $("input[name='cla_id']:checked").val();
			if (radioes == null || radioes == undefined){
				alert("请选中要修改的纪录！");return;
			}else{
				window.location.href = "<%=basePath%>ClassesServlet?method=edit&cla_id="+radioes;
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
			<li><a href="javascript:void(0);">班级信息管理</a></li>
		</ul>
	</div>
	
	<div class="rightinfo">
		<form action="<%=basePath%>ClassesServlet?method=query" method="post" id="search" name="search">
			<!-- 隐藏域 -->
			<div>
				<input type="hidden" name="pageIndex" id="pageIndex" value="${currentPage }">
				<input type="hidden" name=totalPage id="totalPage" value="${totalPage }">
			</div>

			<div class="tools">
				<ul class="seachform">
					<li><label>根据课程名称查询</label>
						<input class="scinput" name="cou_name" type="text" value="${cou_name }" />
					</li>
					<li><label>根据授课教师查询</label>
						<input class="scinput" name="tea_name" type="text" value="${tea_name }" />
					</li>
					<%-- <li><label>根据班级状态查询</label>
						<input class="scinput" name="cla_state" type="text" value="${cla_state }" />
					</li> --%>
				</ul>
				<ul class="seachform">
					<li><label>根据班级性质查询</label>
						<input class="scinput" name="cla_nature" type="text" value="${cla_nature }" />
					</li>	
					<%-- <li><label>根据班级特情查询</label>
						<input class="scinput" name="cla_info" type="text" value="${cla_info }" />
					</li> --%>
					<%-- <li><label>根据班级类型查询</label>
						<input class="scinput" name="cla_kind" type="text" value="${cla_kind }" />
					</li> --%>
				</ul>
				<ul class="seachform">
					<li><label>根据班级名称查询</label>
						<input class="scinput" name="cla_name" type="text" value="${cla_name }" />
					</li>
					<li><label>根据开班时间查询查询</label>
						从：<input class="Wdate" type="text" id="cla_starttime1" name="cla_starttime1" value="${cla_starttime1 }" onClick="WdatePicker({skin:'whyGreen',minDate:'1980-01-1',maxDate:'2200-12-31'})" style="height :30px;width:150px" > 
						到：<input class="Wdate" type="text" id="cla_starttime2" name="cla_starttime2" value="${cla_starttime2 }" onClick="WdatePicker({skin:'whyGreen',minDate:'1980-01-1',maxDate:'2200-12-31'})" style="height :30px;width:150px" > 
					</li>	
					<li><input type="button" class="scbtn" value="查询" onClick="doSubmit(1)" /></li>
				</ul>

				<ul class="toolbar">
			        <a href="javascript:void(0);" onclick="doInsert()"><li><span><img src="images/t01.png" /></span>添加</li></a>
			        <a href="javascript:void(0);" onclick="doUpdate()"><li class="click"><span><img src="images/t02.png" /></span>修改</li></a>
			        <a href="javascript:void(0);" onclick="doDelete()"><li><span><img src="images/t03.png" /></span>删除</li></a>
			    </ul> 

			</div>
		</form>
		
		<table class="tablelist"
			style="table-layout:fixed;word-wrap:break-word;word-break:break-all;">
			<thead>
				<tr>
					<th width="4%">选择</th>
					<th width="9%">名称</th>
					<th width="9%">课程</th>
					<th width="9%">教师</th>
					<th width="5%">人数</th>
					<th width="11%">开班时间</th>
					<!-- <th width="11%">预计结课时间</th>
					<th width="11%">实际节课时间</th> -->
					<th width="8%">班级性质</th>
					<!-- <th width="8%">班级特情</th>
					<th width="8">班级类型</th>
					<th width="8">班级状态</th> -->
				</tr>
			</thead>
			<!-- EL表达式中 empty为关键字 用来判断对象是否为空 -->
			<c:if test="${empty claList}">
				<tr class="odd">
					<td colspan="12" align="center">没有查询到相关记录！</td>
				</tr>
			</c:if>
			<c:forEach items="${claList }" var="claBean">
				<tr class="odd">
					<td><input type="radio" name="cla_id" value="${claBean.cla_id }"></td>
					<td onclick="window.location.href='ClassesServlet?method=view&cla_id=${claBean.cla_id }';" style="cursor:pointer;" title="点击查看详情";">
					<a>${claBean.cla_name }</a>
					</td>
					<td>${claBean.cou_name }</td>
					<td>${claBean.tea_name }</td>
					<td>${claBean.cla_num }</td>
					<td>${claBean.cla_starttime }</td>
					<%-- <td>${claBean.cla_wantendtime }</td>
					<td>${claBean.cla_trueendtime }</td> --%>
					<td>${claBean.cla_nature }</td>
					<%-- <td>${claBean.cla_info }</td>
					<td>${claBean.cla_kind }</td>
					<td>${claBean.cla_state }</td> --%>
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
