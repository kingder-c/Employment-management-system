<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>个人就业信息</title>
    
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
			<li><a href="javascript:void(0)">信息详情</a></li>
		</ul>
	</div>
	
	<div style="text-align: center;padding: 15px;font-family:'微软雅黑';font-size: 20px;">学员详情</div>
	<div style="border-bottom: 1px solid rgba(242, 40, 17, 1);"></div>
	<c:forEach items = "${list }"  var = "riv">
	<div class="item-section">
		<div class="mask-area">
	    	
	    	<h3><span class="b f14">基本信息</span></h3>
			<div class="item-bd">
				<div id="base-info" class="info-area clearfix" style="width: 100%">
					<div style="float:left;width: 80%">
						<table>
						<colgroup><col width="160"><col width="*"></colgroup>
						<tbody>
							<tr><th>学生姓名：</th>
								<td>
									${riv.stu_name }
								</td>
							</tr>
							<tr><th>班级：</th>
								<td>
									${riv.cla_name }
								</td>
							</tr>
							<tr><th>学生所在城市：</th><td>${riv.return_city }</td></tr>
							<tr><th>学生所在单位：</th><td>${riv.return_company }</td></tr>
							<tr><th>学员职务：</th><td>${riv.return_post}</td></tr>
							<%-- <tr><th>学员岗位：</th><td>${riv.return_satation }</td></tr> --%>
							<tr><th>工作满意度：</th><td>${riv.return_satisfy }</td></tr>
							<%-- <tr><th>未来发展城市：</th><td>${riv.return_futurecity }</td></tr> --%>
							
							<tr><th>学员手机号：</th><td>${riv.return_phone }</td></tr>
							<%-- <tr><th>回访内容：</th><td>${riv.return_content}</td></tr> --%>
							
							
							
	    				</tbody>
						</table>
					</div>
				
				</div>
			</div>
		
		</div>
		
		
		<div class="mask-area">
		
	    	<h3><span class="b f14">回访内容</span></h3>
			<div class="item-bd">
				<div class="info-area clearfix" id="base-info">
					<table>
						<colgroup><col width="160"><col width="*"></colgroup>
						<tbody>
							<tr><th>录入人：</th><td>${riv.return_adder }</td></tr>
							<tr><th>录入时间：</th><td>${riv.return_time }</td></tr>
							
							<tr><th>备注：</th><td>${riv.return_note }</td></tr>
	    				</tbody>
					</table>
				</div>
			</div>
		
		</div>
	</div>
	</c:forEach>
	<div
		style="padding-bottom: 20px; text-align: center;position: relative;">
		<input type="button" value="返回" onclick="javascript:history.back();"/>
	</div>
</body>
</html>

