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
    
    <title>合作企业编辑页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css">
	<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
	<script type="text/javascript">
		function check(){
			var com_name = trim($("#com_name").val());
			if(com_name != ""){
				$("#com_nameMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
				return true;
			}else{
				$("#com_nameMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>分类名称不能为空！</font>");
				$("#com_name").focus();
				return false;
			}
			var com_city = trim($("#com_city").val());
			if(com_city != ""){
				$("#com_cityMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
				return true;
			}else{
				$("#com_cityMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>分类名称不能为空！</font>");
				$("#com_city").focus();
				return false;
			}
			var com_checkstatus = trim($("#com_checkstatus").val());
			if(com_checkstatus != ""){
				$("#com_checkstatusMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
				return true;
			}else{
				$("#com_checkstatusMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>分类名称不能为空！</font>");
				$("#com_checkstatus").focus();
				return false;
			}
			var com_status = trim($("#com_status").val());
			if(com_state != ""){
				$("#com_statusMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
				return true;
			}else{
				$("#com_statusMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>查询名称不能为空！</font>");
				$("#com_status").focus();
				return false;
			}
		}
		function doSubmit(){
			if(check()){
				document.edit.submit();
			}
		}
		function doReset(){
			document.edit.reset();
		}
		//去前后空格
		function trim(text){
			return text.replace(/^\s+|\s+$/,"");
		}
	</script>

  </head>
  
  <body>
    <div class="place"><span>位置：</span>
    	<ul class="placeul">
        	<li><a href="javascript:void(0)">首页</a></li>
        	<c:choose>
				<c:when test="${company != null }">
					<li><a href="javascript:void(0)">修改企业信息</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="javascript:void(0)">新增企业信息</a></li> 
				</c:otherwise>
			</c:choose>
    	</ul>
	</div>
	<div align="left" style="margin-top: 5px;">
		<form action="CompanyServlet?method=save" id="edit" name="edit" method="post">
			<table border="0">
				<tr>
					<td colspan="3" class="formtitle">企业信息编辑</td>
				</tr>
				<tr>
					<td>
						<input type="hidden" id="com_id" name="com_id" value="${company.com_id }">
					</td>
				</tr>
				<tr>
					<td width="150" class="tdrsname" align="right">企业名字：</td>
					<td width="600" class="tdrsname">
						<input class="dfinput" type="text" id="com_name" name="com_name" value="${company.com_name }" style="width: 300px;"> 
					</td>
					<td width="auto" class="tdrsname"><span id="com_nameMsg"></span></td>
					
				</tr>
				<tr>
					<td class="tdrsname" align="right">企业城市：</td>
					<td  class="tdrsname">
						<input class="dfinput" type="text" id="com_city" name="com_city" value="${company.com_city }" style="width: 300px;"> 
					</td>
					<td width="auto" class="tdrsname"><span id="com_cityMsg"></span></td>
				</tr>
				
				<tr>
					<td width="150" class="tdrsname" align="right">企业负责人：</td>
					<td width="600" class="tdrsname">
						<input class="dfinput" type="text" id="com_person" name="com_person" value="${company.com_person }" style="width: 300px;"> 
					</td>
					<td width="auto" class="tdrsname"><span id="com_personMsg"></span></td>
					
				</tr>
				<tr>
					<td width="150" class="tdrsname" align="right">企业联系电话：</td>
					<td width="600" class="tdrsname">
						<input class="dfinput" type="text" id="com_phone" name="com_phone" value="${company.com_phone }" style="width: 300px;"> 
					</td>
					<td width="auto" class="tdrsname"><span id="com_phoneMsg"></span></td>
					
				</tr>
				<tr>
					<td width="150" class="tdrsname" align="right">企业邮箱：</td>
					<td width="600" class="tdrsname">
						<input class="dfinput" type="text" id="com_email" name="com_email" value="${company.com_email }" style="width: 300px;"> 
					</td>
					<td width="auto" class="tdrsname"><span id="com_emailMsg"></span></td>
					
				</tr>
				<tr>
					<td width="150" class="tdrsname" align="right">企业方向：</td>
					<td width="600" class="tdrsname">
						<input class="dfinput" type="text" id="com_direction" name="com_direction" value="${company.com_name }" style="width: 300px;"> 
					</td>
					<td width="auto" class="tdrsname"><span id="com_directionMsg"></span></td>
					
				</tr>
				<tr>
					<td width="150" class="tdrsname" align="right">合作等级：</td>
					<td width="600" class="tdrsname">
						<input class="dfinput" type="text" id="com_level" name="com_level" value="${company.com_level }" style="width: 300px;"> 
					</td>
					<td width="auto" class="tdrsname"><span id="com_levelMsg"></span></td>
					
				</tr>
				<tr>
					<td width="150" class="tdrsname" align="right">备注：</td>
					<td width="600" class="tdrsname">
						<input class="dfinput" type="text" id="com_note" name="com_com_note" value="${company.com_note }" style="width: 300px;"> 
					</td>
					<td width="auto" class="tdrsname"><span id="com_note Msg"></span></td>
					
				</tr>
				<tr>
					<td class="tdrsname" align="right">审核状态：</td>
					<td class="tdrsname">
						<select class="dfinput" id="com_checkstatus" name="com_checkstatus" type="text" style="width: 300px;">  
					      <option value ="">请选择</option>  
					      <option value ="已审核">已审核</option>  
					      <option value ="审核中">审核中</option>
					      <option value="未通过">未通过</option>  
					    </select> 
					<td width="auto" class="tdrsname"><span id="com_checkstatusMsg"></span></td>
				</tr>
 				<tr>
					<td class="tdrsname" align="right">企业状态：</td>
					<td class="tdrsname">
						<select class="dfinput" id="com_status" name="com_status" type="text" style="width: 300px;">  
					      <option value ="5">请选择</option>  
					      <option value ="0">招聘中</option>  
					      <option value="1">不招聘</option>  
					    </select> 
					<td width="auto" class="tdrsname"><span id="com_statusMsg"></span></td>
				</tr>
				<tr style="line-height:80px; ">
					<!-- 按钮换成背景图片 -->
					<td>&nbsp;</td>
					<td colspan="1" align="center"><input class="button"
						type="button" name="确认" value="确认" onClick="doSubmit()" />
					&nbsp;&nbsp;&nbsp;&nbsp;<input class="button"
						type="button" name="返回" value="返回" onClick="javascript:history.back();"/>
					</td><td colspan="3">&nbsp;</td>
				</tr>
			</table>
		</form>
	</div>
  </body>
</html>
