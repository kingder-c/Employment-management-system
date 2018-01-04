<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>合作企业信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/viewStyle.css">
	<script type="text/javascript" src="<%=basePath%>/js/jquery-1.4.4.min.js">
	
	</script>
	<script type="text/javascript">
	function doUpdate(){
		window.location.href = "<%=basePath%>CompanyServlet?method=edit&com_id=${com.com_id }";
	}
	</script>
</head>
<body>
	<!-- 顶部路径栏 -->
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="javascript:void(0)">首页</a></li>
			<li><a href="javascript:void(0)">合作企业信息详情</a></li>
		</ul>
	</div>
	
	<div style="text-align: center;padding: 15px;font-family:'微软雅黑';font-size: 20px;">企业详情</div>
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
							<tr><th>企业名称：</th>
								<td>
									${com.com_name }
								</td>
							</tr>
							<%-- <tr><th>企业规模：</th>
								<td>
									${com.com_size }
								</td>
							</tr> --%>
							<tr><th>所在城市：</th><td>${com.com_city }</td></tr>
							<%-- <tr><th>企业地址：</th><td>${com.com_address }</td></tr> --%>
							<tr><th>企业联系人：</th><td>${com.com_person }</td></tr>
							<tr><th>企业电话：</th><td>${com.com_phone }</td></tr>
							<%-- <tr><th>企业QQ：</th><td>${com.com_qq }</td></tr> --%>
							<%-- <tr><th>企业微信：</th><td>${com.com_weixin }</td></tr> --%>
							<tr><th>电子邮件：</th>
								<td>
								${com.com_email }
								</td>
							</tr>
	    				</tbody>
						</table>
					</div>
			</div>
		</div>
		<div class="mask-area">
	    	<h3><span class="b f14">企业状况：</span></h3>
			<div class="item-bd">
				<div class="info-area clearfix" id="base-info">
					<table>
						<colgroup><col width="160"><col width="*"></colgroup>
						<tbody>
							<tr><th>招聘方向：</th><td>${com.com_direction }</td></tr>
							<tr><th>招聘状态：</th><td>${com.com_status }</td></tr>
							<tr><th>合作等级：</th><td>${com.com_level }</td></tr>
							<tr><%-- <th>开始合作日期：</th><td>${com.com_date }</td></tr>
							<tr><th>是否订单培养：</th><td>${com.com_order }</td></tr> --%>
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
							<tr><th>是否删除企业：</th><td>${com.com_delete }</td></tr>
							<tr><th>企业审核状态：</th>
								<td>
									${com.com_checkstatus }
									</td>
							</tr>
							<tr><th>企业备注：</th><td>${com.com_note }</td></tr>
	    				</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<div
		style="padding-bottom: 20px; text-align: center;position: relative;">
		<input type="button" value="修改" onclick="doUpdate();"/>
		<input type="button" value="返回" onclick="javascript:history.back();"/>
	</div>
</body>
</html>

 