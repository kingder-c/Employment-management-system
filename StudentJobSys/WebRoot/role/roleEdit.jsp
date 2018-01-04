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
    
    <title>角色编辑页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css">
	<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
	<script type="text/javascript" src="<%=basePath%>ckeditor/ckeditor.js"></script>
	<script type="text/javascript">
		function check(){
			var role_name = trim($("#role_name").val());
			if(role_name != ""){
				$("#role_nameMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
				return true;
			}else{
				$("#role_nameMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>分类名称不能为空！</font>");
				$("#role_name").focus();
				return false;
			}
			var auth_ids = trim($("#auth_ids").val());
			if(auth_ids != ""){
				$("#auth_idsMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
				return true;
			}else{
				$("#auth_idsMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>分类名称不能为空！</font>");
				$("#auth_ids").focus();
				return false;
			}
			
			var role_desc = trim($("#role_desc").val());
			if(role_desc != ""){
				$("#role_descMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
				return true;
			}else{
				$("#role_descMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>分类名称不能为空！</font>");
				$("#role_desc").focus();
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
     <!-- 顶部路径栏 -->
	<div class="place"><span>位置：</span>
    	<ul class="placeul">
        	<li><a href="javascript:void(0)">首页</a></li>
        	<c:choose>
				<c:when test="${role != null }">
					<li><a href="javascript:void(0)">修改角色信息</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="javascript:void(0)">新增角色信息</a></li> 
				</c:otherwise>
			</c:choose>
    	</ul>
	</div>
	<div align="left" style="margin-top: 5px;">
		<form action="RoleServlet?method=save" id="edit" name="edit" method="post">
			<table border="0">
				<tr>
					<td colspan="3" class="formtitle">角色信息编辑</td>
				</tr>
				<tr>
					<td>
						<input type="hidden" id="role_id" name="role_id" value="${role.role_id }">
					</td>
				</tr>
				<tr>
					<td width="150" class="tdrsname" align="right">角色名字：</td>
					<td width="600" class="tdrsname">
						<input class="dfinput" type="text" id="role_name" name="role_name" value="${role.role_name }" style="width: 450px;"> 
					</td>
					<td width="auto" class="tdrsname"><span id="role_nameMsg"></span></td>
					
				</tr>
				<%-- <tr>
					<td class="tdrsname" align="right">角色集合：</td>
					<td  class="tdrsname">
						<input class="dfinput" type="text" id="auth_ids" name="auth_ids" value="${role.auth_ids }" style="width: 100px;"> 
					</td>
					<td width="auto" class="tdrsname"><span id="auth_idsMsg"></span></td>
				</tr>--%>
				
				<tr>
					<td class="tdrsname" align="right">角色描述：</td>
					<td  class="tdrsname">
						<textarea rows="5" cols="20" class="ckeditor" type="text" id="role_desc" name="role_desc" >${role.role_desc }</textarea>
						<%-- <input class="dfinput" type="text" id="role_desc" name="role_desc" value="${role.role_desc }"> --%>
					</td>
					<td width="auto" class="tdrsname"><span id="role_descMsg"></span></td>
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
