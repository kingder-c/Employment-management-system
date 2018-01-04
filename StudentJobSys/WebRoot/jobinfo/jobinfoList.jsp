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
    
    <title>就业信息管理列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css">
	<script src="<%=basePath%>js/jquery.js" type="text/javascript"></script>
	<script src="<%=basePath%>My97DatePicker/WdatePicker.js" type="text/javascript"></script><!-- 时间插件的添加 -->
	
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
			window.location.href = "<%=basePath%>JobInfoServlet?method=edit";
		}
		
		function doDelete(){
			var radioes = $("input[name='job_id']:checked").val();
			if (radioes == null || radioes == undefined){
				alert("请选中要删除的纪录！");return;
			}else if (confirm("确定要删除选定的记录吗？\n")){
				window.location.href = "<%=basePath%>JobInfoServlet?method=delete&job_id="+radioes;
			}
		}
		function doUpdate(){
			var radioes = $("input[name='job_id']:checked").val();
			if (radioes == null || radioes == undefined){
				alert("请选中要修改的纪录！");return;
			}else{
				window.location.href = "<%=basePath%>JobInfoServlet?method=edit&job_id="+radioes;
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
			<li><a href="javascript:void(0);">就业信息管理</a></li>
		</ul>
	</div>
	<div class="rightinfo">
		<form action="<%=basePath%>JobInfoServlet?method=query" method="post" id="search" name="search">
			<!-- 隐藏域 -->
			<div>
				<input type="hidden" name="pageIndex" id="pageIndex" value="${currentPage }">
				<input type="hidden" name=totalPage id="totalPage" value="${totalPage }">
			</div>

			<div class="tools" >
			<ul class="seachform">
				<li><label>根据学员姓名</label>
					<input class="scinput" name="stu_name" type="text" value="${stu_name}" /></li>
		        <li><label>根据课程方向</label>
			
						<select class="scinput" name="cou_name" />		
						<c:choose>
							<c:when test="${not empty cou_name }">
								<option value=${cou_name } checked>${cou_name }</option>
								<option value="">--清除条件--</option>
								<c:forEach items="${select_cou_name }" var="cou">
									<option value=${cou.cou_name } >-${cou.cou_name }-</option>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<option value="">--请选择--</option>
								<c:forEach items="${select_cou_name }" var="cou">
									<option value=${cou.cou_name } >-${cou.cou_name }-</option>
								</c:forEach>
							</c:otherwise>
						</c:choose>
 					</select>
				
				<%-- <li><label>根据就业方式 </label>
					<select class="scinput" name="job_type" />		
						<c:choose>
							<c:when test="${not empty job_type}">
								<option value=${job_type } checked>${job_type }</option>
								<option value="">--清除条件--</option>
								<c:forEach items="${select_job_type }" var="job">
									<option value=${job.job_type } >-${job.job_type }-</option>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<option value="">--请选择--</option>
								<c:forEach items="${select_job_type }" var="job">
									<option value=${job.job_type } >-${job.job_type }-</option>
								</c:forEach>
							</c:otherwise>
						</c:choose>
 					</select>
				</li> --%>
			</ul>
						
			<ul class="seachform">
				<%-- <li><label>根据工作年限  </label>
				<select class="scinput" name="job_long" type="text"/> 			
								<option value="<c:out value="十年以上"/>"><c:out value="十年以上" /></option>
								<option value="<c:out value="五年以上"/>"><c:out value="五年以上" /></option>
								<option value="<c:out value="四年以下"/>"><c:out value="四年以下" /></option>
								<option value="<c:out value="三年以下"/>"><c:out value="三年以下" /></option>
								<option value="<c:out value="二年以下"/>"><c:out value="二年以下" /></option>
								<option value="<c:out value="一年以下"/>"><c:out value="一年以下" /></option>
						   </select>
				 --%>
				<li><label>根据学员籍贯</label>
									<input class="scinput" name="stu_native" type="text" value="${stu_native }" /></li>
				
				<li><label>根据就业单位</label>
									<input class="scinput" name="job_company" type="text" value="${job_company}" /></li>
					</ul>
				<ul class="seachform">				
				<li><label>根据时间范围</label>
										&nbsp;从&nbsp;<input class="scinput"  id="job_startdate1" name="job_startdate1" type="text" value="${job_startdate1}" />
										<img onclick="WdatePicker({el:'job_startdate1'})" src="My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
									    &nbsp;到&nbsp;&nbsp;&nbsp;&nbsp;<input class="scinput" id="job_startdate2" name="job_startdate2" type="text" value="${job_startdate2}" />
									    <img onclick="WdatePicker({el:'job_startdate2'})" src="My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
			    </li>
			</ul>
			<ul class="seachform">
				<li><input type="button" class="scbtn" value="查询" onClick="doSubmit(1)" /></li>
			</ul>
			<ul style="text-align: right;" class="toolbar" >
			<hr>
			<hr>
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
			<thead>
				<tr>
					<th width="8%">选择</th>
					<th width="10%">学员</th>
					<th width="10%">班级</th>
					<th width="10%">课程</th>
					<th width="20%">就业单位</th>
					<th width="15%">岗位</th>
					<th width="20%">入职日期</th>
					<!-- <th width="8%">就业方式</th> -->
					<th width="auto%">转正基本月薪</th>
					<!-- <th width="15%">福利待遇</th>
					<th width="5%">合同期</th> -->
					
				</tr>
			</thead>
			<!-- EL表达式中 empty为关键字 用来判断对象是否为空 -->
			<c:if test="${empty jobinfoList}">
				<tr class="odd">
					<td colspan="11" align="center">没有查询到相关记录！</td>
				</tr>
			</c:if>
			<c:forEach items="${jobinfoList}" var="JobBean">
				<tr class="odd">
					<td><input type="radio" name="job_id" value="${JobBean.job_id }"></td>
             		<td>
						<a href="JobInfoServlet?method=view&job_id=${JobBean.job_id } " >
							${JobBean.stu_name }
						</a>
					</td>
					<td>${JobBean.cla_name }</td>
					<td>${JobBean.cou_name }</td>
					<c:if test="${empty JobBean.job_company }">
						<td>${JobBean.com_name }</td>
					</c:if>
					<c:if test="${not empty JobBean.job_company }">
						<td>${JobBean.job_company }</td>
					</c:if>
					<td>${JobBean.job_job }</td>
					<td>${JobBean.job_startdate }</td>
					<%-- <td>${JobBean.job_type}</td> --%>
					<td>${JobBean.job_basesalary }</td>
					<%-- <td>${JobBean.job_weal }</td>
					<td>${JobBean.job_contractlength}</td> --%>
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
