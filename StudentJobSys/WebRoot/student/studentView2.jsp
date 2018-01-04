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
    
    <title>My JSP 'studentView2.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="css/enjoy.css" rel="stylesheet" type="text/css"/>
  	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/viewStyle.css">
	<script type="text/javascript" src="<%=basePath%>/js/jquery-1.4.4.min.js">
	</script>
</head>
<body>
	<!-- 顶部路径栏 -->
  <div  id="head_bottom">
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="javascript:void(0)">首页</a></li>
			<li><a href="javascript:void(0)">学生信息详情</a></li>
		</ul>
	</div>
	<div style="text-align: center;padding: 15px;font-family:'微软雅黑';font-size: 20px;">学员详情</div>
	
	<div style="border-bottom: 1px solid rgba(242, 40, 17, 1);"></div>
	
	<div class="item-section">
		<div class="mask-area">
	    	<h3><span class="b f14">基本信息</span></h3>
			<div class="item-bd">
				<div id="base-info" class="info-area clearfix" style="width: 100%" >
					<div style="float:left;width: 80%">
						<table>
						<colgroup><col width="160"><col width="*"></colgroup>
						<tbody>
							<tr><th>班级：</th>
								<c:forEach items="${select_cla_idList }" var="cla">
								<c:choose>
									<c:when test="${stuv.cla_id==cla.cla_id }">
										<td>${cla.cla_name }</td>
									</c:when>
								</c:choose>
								</c:forEach>
							</tr>
							<tr><th>性别：</th>
								
								<c:forEach items="${select_stu_sex }" var="data">
								<c:choose>
									<c:when test="${stuv.stu_sex == data.data_num }">
									<td>${data.data_name }</td>
									</c:when>
								</c:choose>
								</c:forEach>
							</tr>
						<%-- 	<tr><th>出生日期：</th><td>${stuv.stu_birth }</td></tr> --%>
							<tr><th>籍贯：</th><td>${stuv.stu_native }</td></tr>
							<tr><th>住址：</th><td>${stuv.stu_address }</td></tr>
						<%-- 	<tr><th>家长姓名：</th><td>${stuv.stu_pare_name }</td></tr>
							<tr><th>家长联系方式：</th><td>${stuv.stu_pare_phone }</td></tr>
							<tr><th>紧急联系人电话：</th><td>${stuv.stu_urg_phone }</td></tr> --%>
							<tr><th>当前状态：</th>
								<td>
								${stuv.stu_state }
									</td>
							</tr>
	    				</tbody>
						</table>
					</div>
				<%-- 	<div class="u-photo">
						<div class="photo-area">
								<img alt="${stuv.stu_name }" src="${stuv.stu_img }" width="222" height="305">
							
						</div>
						<div class="showphoto">
					        <label>${stuv.stu_name }</label>
					    </div>
					</div> --%>
				</div>
			</div>
		</div>
		<div class="mask-area">
	    	<h3><span class="b f14">联系方式</span></h3>
			<div class="item-bd" id="bottom">
				<div class="info-area clearfix" id="base-info">
					<table>
						<colgroup><col width="160"><col width="*"></colgroup>
						<tbody>
							<tr><th>手机：</th><td>${stuv.stu_phone }</td></tr>
							<tr><th>E-mail：</th><td>${stuv.stu_email }</td></tr>
							<%-- <tr><th>Tencent QQ：</th><td>${stuv.stu_qq }</td></tr>
							<tr><th>微信：</th><td>${stuv.stu_weixin }</td></tr> --%>
	    				</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="mask-area">
	    	<h3><span class="b f14">教育经历</span></h3>
			<div class="item-bd">
				<div class="info-area clearfix" id="base-info">
					<table>
						<colgroup><col width="160"><col width="*"></colgroup>
						<tbody>
							<tr><th>毕业学校：</th><td>${stuv.stu_graduation }</td></tr>
						<%-- 	<tr><th>院系：</th><td>${stuv.stu_department }</td></tr> --%>
							<tr><th>专业：</th><td>${stuv.stu_major }</td></tr>
					<%-- 		<tr><th>学历：</th><td>${stuv.stu_education }</td></tr> --%>
							<tr><th>入学年份：</th><td>${stuv.stu_into_time }</td></tr>
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
							<tr><th>录入人：</th><td>${stuv.stu_adder }</td></tr>
							<tr><th>录入时间：</th><td>${stuv.stu_addtime }</td></tr>
							<tr><th>审核状态：</th>
								<c:forEach items="${select_stu_check }" var="data">
								<c:choose>
									<c:when test="${stuv.stu_check == data.data_num }">
									<td>${data.data_name }</td>
									</c:when>
								</c:choose>
								</c:forEach>
							
							</tr>
							<tr><th>备注：</th><td>${stuv.stu_note }</td></tr>
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
</div>

</body>
</html>

 