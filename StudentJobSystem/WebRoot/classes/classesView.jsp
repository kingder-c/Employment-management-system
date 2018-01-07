<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'classesView.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/viewStyle.css">
	<script type="text/javascript" src="<%=basePath%>/js/jquery-1.4.4.min.js">
	</script>
  </head>
  
  <body>
    <!-- 顶部路径栏 -->
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="javascript:void(0)">首页</a></li>
			<li><a href="javascript:void(0)">班级详情</a></li>
		</ul>
	</div>
	<div style="text-align: center;padding: 15px;font-family:'微软雅黑';font-size: 20px;">班级详情</div>
  	<div style="border-bottom: 1px solid rgba(242, 40, 17, 1);"></div>
  	<div class="item-section">
		<div class="mask-area">
	    	<h3><span class="b f14">基本信息</span></h3>
			<div class="item-bd">
				<div id="base-info" class="info-area clearfix" style="width: 100%">
					<div style="float:left;width: 80%">
				  		<table>
						<colgroup><col width="160"><col width="*"></colgroup>
						<tbody>
				  		<tr><th>班级名称：</th><td>${cla.cla_name }</td></tr>
						<tr><th>开设课程：</th><td>${cla.cou_id }</td></tr>
						<tr><th>授课教师：</th><td>${cla.tea_id }</td></tr>
						<tr><th>人数：</th><td>${cla.cla_num }</td></tr>
						<%-- <tr><th>授课地点：</th><td>${cla.cla_address }</td></tr>
						<tr><th>班主任：</th><td>${cla.cla_master }</td></tr>
						<tr><th>班长：</th><td>${cla.cla_head }</td></tr>
						<tr><th>班级QQ群：</th><td>${cla.cla_QQ }</td></tr> --%>
						<tr><th>班级性质：</th><td>${cla.cla_nature }</td></tr>
						<%-- <tr><th>班级详情：</th><td>${cla.cla_info }</td></tr>
						<tr><th>班级类型：</th><td>${cla.cla_kind }</td></tr>
						 --%>
						 <tr><th>开班时间：</th><td>${cla.cla_starttime }</td></tr>
						<%-- <tr><th>预计结课时间：</th><td>${cla.cla_wantendtime }</td></tr>
						<tr><th>实际结课时间：</th><td>${cla.cla_trueendtime }</td></tr>
						<tr><th>班级状态：</th><td>${cla.cla_state }</td></tr>
						 --%>
						 <tr><th>备注：</th><td>${cla.cla_note }</td></tr>
						
						</tbody>
						</table>
					</div>
  				</div>
			</div>
		</div>
	</div>
  	
  	<div
		style="padding-bottom: 20px; text-align: center;position: relative;">
		<input type="button" value="返回" onclick="javascript:history.back();"/>
	</div>
  </body>
</html>
