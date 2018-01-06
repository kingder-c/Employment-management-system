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
    
    <title>数据字典编辑页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css">
	<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
	<script type="text/javascript">
		function check(){
			var data_name = trim($("#data_name").val());
			if(data_name != ""){
				$("#data_nameMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
			}else{
				$("#data_nameMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>字典名称不能为空！</font>");
				$("#data_nameMsg").focus();
				return false;
			}
			var data_num = trim($("#data_num").val());
			if(data_num != ""){
				$("#data_numMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
			}else{
				$("#data_numMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>字典值不能为空！</font>");
				$("#data_numMsg").focus();
				return false;
			}
			var data_type = trim($("#data_type").val());
			if(data_type != ""){
				$("#data_typeMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>&nbsp;");
			}else{
				$("#data_typeMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>分类名称不能为空！</font>");
				$("#data_typeMsg").focus();
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
				<c:when test="${data != null }">
					<li><a href="javascript:void(0)">修改数据字典</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="javascript:void(0)">新增数据字典</a></li> 
				</c:otherwise>
			</c:choose>
    	</ul>
	</div>
	<div align="left" style="margin-top: 5px;">
		<form action="DataServlet?method=save" id="edit" name="edit" method="post">
			<table border="0">
				<tr>
					<td colspan="3" class="formtitle">数据字典编辑</td>
				</tr>
				<tr>
					<td>
						<input type="hidden" id="data_key" name="data_key" value="${data.data_key }">
					</td>
				</tr>
				<tr>
					<td width="150" class="tdrsname" align="right">字典名称：</td>
					<td width="600" class="tdrsname">
						<input class="dfinput" type="text" id="data_name" name="data_name" value="${data.data_name }" style="width: 600px;"> 
					</td>
					<td width="auto" class="tdrsname"><span id="data_nameMsg"></span></td>
					
				</tr>
				<tr>
					<td class="tdrsname" align="right">字典值：</td>
					<td  class="tdrsname">
						<input class="dfinput" type="text" id="data_num" name="data_num" value="${data.data_num }" style="width: 600px;"> 
					</td>
					<td width="auto" class="tdrsname"><span id="data_numMsg"></span></td>
				</tr>
 				<tr>
					<td class="tdrsname" align="right">字典类型：</td>
					<td class="tdrsname">
						<input class="dfinput" type="text" id="data_type" name="data_type" value="${data.data_type }" style="width: 600px;"> 
					<td width="auto" class="tdrsname"><span id="data_typeMsg"></span></td>
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
