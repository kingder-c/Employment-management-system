<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
			<li><a href="javascript:void(0)">就业信息详情</a></li>
		</ul>
	</div>
	
	<div style="text-align: center;padding: 15px;font-family:'微软雅黑';font-size: 20px;">学员详情</div>
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
							<tr><th>学生姓名：</th>
								<td>
									${jobinfoview.stu_name }
								</td>
							</tr>
							<tr><th>班级：</th>
								<td>
									${jobinfoview.cla_name }
								</td>
							</tr>
							<tr><th>就业单位：</th><td>${jobinfoview.job_company }</td></tr>
							<tr><th>岗位：</th><td>${jobinfoview.job_job }</td></tr>
							<tr><th>就业城市：</th><td>${jobinfoview.job_city }</td></tr>
							<tr><th>入职日期：</th><td>${jobinfoview.job_startdate }</td></tr>
							<%-- <tr><th>合同期：</th><td>${jobinfoview.job_contractlength }</td></tr> --%>
						<%-- 	<tr><th>实习基本月薪：</th><td>${jobinfoview.job_practicesalary }</td></tr> --%>
							<tr><th>转正基本月薪：</th>
								<td>
								${jobinfoview.job_basesalary }
									</td>
							</tr>
	    				</tbody>
						</table>
					</div>
					<div class="u-photo">
						<div class="photo-area">
								<img alt="" src="http://localhost:8080/StudentJobSys/img/student/yancongcong.jpg" width="222" height="305">
							
						</div>
						<div class="showphoto">
					        <label>${jobinfoview.stu_name }</label>
					    </div>
					</div>
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
							<tr><th>录入人：</th><td>${jobinfoview.job_adder }</td></tr>
							<tr><th>录入时间：</th><td>${jobinfoview.job_addtime }</td></tr>
							
							<tr><th>备注：</th><td></td></tr>
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

 