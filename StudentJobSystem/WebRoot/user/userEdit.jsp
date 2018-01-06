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
    
    <title>My JSP 'userEdit.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css">
	<script type="text/javascript" src="ckeditor/ckeditor.js"></script>  
	<script type="text/javascript" src="ckeditor/config.js"></script>
	
	
	<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
	<script type="text/javascript">
		function check(){
			var user_name = trim($("#user_name").val());
			if(user_name != ""){
				$("#user_nameMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
			}else{
				$("#user_nameMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>用户名不能为空！</font>");
				$("#user_nameMsg").focus();
				return false;
			}
			var user_password = trim($("#user_password").val());
			if(user_password.length>4 && user_password.length<21){
				$("#user_passwordMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
			}else{
				$("#user_passwordMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>密码为5~20位！！</font>");
				$("#user_passwordMsg").focus();
				return false;
			}
			if(isNaN(user_password)){
				$("#user_passwordMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
			}else{
				$("#user_passwordMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>密码为非纯数字！！</font>");
				$("#user_passwordMsg").focus();
				return false;
			}
			var user_repassword = trim($("#user_repassword").val());
			if(user_repassword == user_password){
				$("#user_repasswordMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
			}else{
				$("#user_repasswordMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>密码与确认密码不相同！</font>");
				$("#user_repasswordMsg").focus();
				return false;
			}
			return true;
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
    <!-- 顶部路径栏 -->
	<div class="place"><span>位置：</span>
    	<ul class="placeul">
        	<li><a href="javascript:void(0)">首页</a></li>
        	<c:choose>
				<c:when test="${thisUser !=null }">
					<li><a href="javascript:void(0)">修改用户信息</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="javascript:void(0)">新增用户信息</a></li> 
				</c:otherwise>
			</c:choose>
    	</ul>
	</div>
	<div align="left" style="margin-top: 5px;">
		<form action="UserServlet?method=save" id="edit" name="edit" method="post">
			<table border="0">
				<tr>
					<td colspan="3" class="formtitle">用户信息编辑</td>
				</tr>
				<tr>
					<td>
						<input type="hidden" id="user_id" name="user_id" value="${thisUser.user_id }">
					</td>
				</tr>
				<tr>
					<c:if test="${not empty thisUser.user_checkstatus }">
						<td>
							<input type="hidden" id="user_checkstatus" name="user_checkstatus" value="${thisUser.user_checkstatus }">
						</td>
						<td>
							<input type="hidden" id="user_name" name="user_name" value="${thisUser.user_name }">
						</td>
						<td>
							<input type="hidden" id="user_type" name="user_type" value="${thisUser.user_type }">
						</td>
					</c:if>
					<c:if test="${ empty thisUser.user_checkstatus }">
						<td>
							<input type="hidden" id="user_checkstatus" name="user_checkstatus" value="3">
						</td>
					</c:if>
				</tr>
				<tr>
					<td width="150" class="tdrsname" align="right">用户名：</td>
					<td width="600" class="tdrsname" style="height:35px;">
					<c:choose>
					<c:when test="${thisUser != null }">
						<font  color="red">&nbsp;&nbsp;&nbsp;${thisUser.user_name }</font>
					</c:when>
					<c:otherwise>
						<input class="dfinput" type="text" id="user_name" name="user_name" value="${thisUser.user_name }" style="width: 300px;"> 
					</c:otherwise>
					</c:choose>
					</td>
					<td width="auto" class="tdrsname"><span id="user_nameMsg"></span></td>
					
				</tr>
				<tr>
					<td class="tdrsname" align="right">用户密码：</td>
					<td  class="tdrsname">
						<input class="dfinput"  id="user_password" name="user_password" value="${thisUser.user_password }" style="width: 300px;"> 
					</td>
					<td width="auto" class="tdrsname"><span id="user_passwordMsg"></span></td>
				</tr>
				<tr>
					<td class="tdrsname" align="right">确认密码：</td>
					<td  class="tdrsname">
						<input class="dfinput"  id="user_repassword" name="user_repassword" value="${thisUser.user_password }" style="width: 300px;"> 
					</td>
					<td width="auto" class="tdrsname"><span id="user_repasswordMsg"></span></td>
				</tr>
 				<tr>
					<td class="tdrsname" align="right">用户类型：</td>
					<c:choose>
					<c:when test="${thisUser != null }">
						
						<c:forEach items="${select_user_Type }" var="role">
							<c:choose>
								<c:when test="${thisUser.user_type==role.role_id }">
								<td style="height:35px;"><font  color="red">&nbsp;&nbsp;&nbsp;${role.role_name }</font></td>
								</c:when>
							</c:choose>
						</c:forEach>
						
					</c:when>
					<c:otherwise>
						<td class="tdrsname">
						<select class="dfinput" id="user_type" name="user_type"  style="width: 300px;"> 
						<c:forEach items="${select_user_Type }" var="role">
							<c:choose>
							<c:when test="${role.role_id ==user.user_type }">
								<option value=${thisUser.user_type } checked>${role.role_name }</option>
							</c:when>
							<c:otherwise>
								<option value=${role.role_id } >${role.role_name }</option>
							</c:otherwise>
							</c:choose>
						</c:forEach>
 						</select>
 						</td>
					</c:otherwise>
					</c:choose>
					
					
					
					<td width="auto" class="tdrsname"><span id="user_typeMsg"></span></td>
				</tr>
				<tr>
					<td class="tdrsname" align="right">用户描述：</td>
					<td class="tdrsname">
						
						<input class="dfinput" type="text" id="user_desc" name="user_desc" value="${thisUser.user_desc }" style="width: 300px;"> 
					<td width="auto" class="tdrsname">
						<span id="user_descMsg"></span>
					</td>
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