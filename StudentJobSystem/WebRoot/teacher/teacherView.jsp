<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>合作老师信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css">
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
			<li><a href="javascript:void(0)">老师信息详情</a></li>
		</ul>
	</div>
	
	<div style="text-align: center;padding: 15px;font-family:'微软雅黑';font-size: 20px;">老师详情</div>
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
							<tr><th>老师名称：</th>
								<td>
									${tea.tea_name }
								</td>
							</tr>
							<tr><th>老师性别：</th>
								<td>
									${tea.tea_sex }
								</td>
							</tr>
							<%-- <tr><th>出生年日：</th><td>${tea.tea_birth }</td></tr> --%>
							<tr><th>毕业院校：</th><td>${tea.tea_school }</td></tr>
							<tr><th>老师专业：</th><td>${tea.tea_major }</td></tr>
							<%-- <tr><th>老师学历：</th><td>${tea.tea_education }</td></tr> --%>
							<%-- <tr><th>老师职称：</th><td>${tea.tea_title }</td></tr> --%>
							<%-- <tr><th>老师籍贯：</th><td>${tea.tea_native }</td></tr> --%>
							<%-- <tr><th>老师住址：</th><td>${tea.tea_address }</td></tr> --%>
							<tr><th>老师电话：</th><td>${tea.tea_tel }</td></tr>
							<%-- <tr><th>老师QQ：</th><td>${tea.tea_QQ }</td></tr> --%>
							<%-- <tr><th>老师工号：</th><td>${tea.weixin_workid }</td></tr> --%>
							<%-- <tr><th>老师邮箱：</th><td>${tea.tea_mail }</td></tr>
							<tr><th>从业经历：</th><td>${tea.tea_workexp }</td></tr>
							<tr><th>从业年限：</th><td>${tea.tea_workyear }</td></tr>
							<tr><th>经验和成就：</th><td>${tea.tea_projectexp }</td></tr> --%>
							
	    				</tbody>
						</table>
					</div>
			</div>
		</div>
		<div class="mask-area">
	    	<h3><span class="b f14">老师状况：</span></h3>
			<div class="item-bd">
				<div class="info-area clearfix" id="base-info">
					<table>
						<colgroup><col width="160"><col width="*"></colgroup>
						<tbody>
							<tr><th>老师状态：</th><td>${tea.tea_state }</td></tr>
							<%-- <tr><th>审核状态：</th><td>${tea.tea_checkstate }</td></tr> --%>
	    				</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="mask-area">
	    	<h3><span class="b f14">备注</span></h3>
			<div class="item-bd">
				<div class="info-area clearfix" id="base-info">
					<table>
						<colgroup><col width="160"><col width="*"></colgroup>
						<tbody>
							<tr><th>老师录入人：</th><td>${tea.tea_adder }</td></tr>
							<tr><th>老师录入时间：</th>
								<td>
									${tea.tea_addtime }
									</td>
							</tr>
							<tr><th>老师备注：</th><td>${tea.tea_note }</td></tr>
	    				</tbody>
					</table>
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

 