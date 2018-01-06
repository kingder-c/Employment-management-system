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
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<title>登录错误</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery.js"></script>

<script language="javascript">
	$(function(){
    	$('.error').css({'position':'absolute','left':($(window).width()-490)/2});
	$(window).resize(function(){  
    	$('.error').css({'position':'absolute','left':($(window).width()-490)/2});
    }) ; 
});  
</script> 

</head>


<body style="background:#FFF8ED;">

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="index.jsp">首页</a></li>
    <li><a href="error_login.jsp">登录错误</a></li>
    </ul>
    </div>
    
    <div class="error">
    
    <h2>非常遗憾，您没有登录或登录错误！</h2>
    <p>看到这个提示，就自认倒霉吧!</p>
    <div class="reindex"><a href="index.jsp">返回首页</a></div>
    	<h1>错误信息： ${ errorMsg}</h1>
    </div>


</body>

</html>
