<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'courseView.jsp' starting page</title>
    
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
			<li><a href="javascript:void(0)">课程详情</a></li>
		</ul>
	</div>
	<div style="text-align: center;padding: 15px;font-family:'微软雅黑';font-size: 20px;">课程详情</div>
  	<div style="border-bottom: 1px solid rgba(242, 40, 17, 1);"></div>
  	<div class="item-section">
		<div class="mask-area">
	    	<h3><span class="b f14">课程介绍</span></h3>
			<div class="item-bd">
				<div id="base-info" class="info-area clearfix" style="width: 100%">
					<div style="float:left;width: 80%">
				  		<table>
						<colgroup><col width="100%"><col width="*"></colgroup>
						<tbody>
  						<tr><td>${cou.cou_note }</td></tr>
  						</tbody>
						</table>
					</div>
  				</div>
			</div>
		</div>
	</div>
  	<div class="item-section">
		<div class="mask-area">
	    	<h3><span class="b f14">课程展示图</span></h3>
			
			<div class="item-bd">
				<div id="base-info" class="info-area clearfix" style="width: 100%">
					<div style="float:left;width: 80%">
				  		<table>
						<colgroup><col width="100%"><col width="*"></colgroup>
							<tbody>
		  						<div class="photo-area" lang="">
									<img alt="${cou.cou_logo }" src="${cou.cou_logo }" width="500" height="250">
								</div>
	  						</tbody>
						</table>
					</div>
  				</div>
			</div>
		</div>
	</div>
  
  
    <div class="item-section">
		<div class="mask-area">
	    	<h3><span class="b f14">其他信息</span></h3>
			<div class="item-bd">
				<div id="base-info" class="info-area clearfix" style="width: 100%">
					<div style="float:left;width: 80%">
				  		<table>
						<colgroup><col width="100%"><col width="*"></colgroup>
						<tbody>
  							<tr><td>录入人：&nbsp;&nbsp;&nbsp;&nbsp;${cou.cou_adder }</td></tr>
							<tr><td>录入时间：&nbsp;&nbsp;&nbsp;&nbsp;${cou.cou_date }</td></tr>
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
