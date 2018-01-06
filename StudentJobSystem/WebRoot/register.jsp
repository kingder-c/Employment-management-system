<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
 <base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<title>欢迎来到注册页面</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>
<script src="js/cloud.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
<script type="text/javascript">
	$(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){  
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    }); 
});  
   function check(){
	   var flag = true;
			var user_name = trim($("#user_name").val());
			if(user_name != ""&& user_name != null){
				$("#user_nameMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
			}else{
				$("#user_nameMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>用户名不能为空！</font>");
				$("#user_name").focus();
				flag = false;
			}
			var user_password = trim($("#user_password").val());
			if(user_password.length>4 && user_password.length<21){
				$("#user_passwordMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
			}else{
				$("#user_passwordMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>密码为5~20位！！</font>");
				$("#user_password").focus();
				flag = false;
			}
			if(isNaN(user_password)){
				$("#user_passwordMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
			}else{
				$("#user_passwordMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>密码为非纯数字！！</font>");
				$("#user_password").focus();
				flag = false;
			}
			var user_pass = trim($("#user_pass").val());
			if(user_pass == user_password){
				$("#user_passMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
			}else{
				$("#user_passMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>密码与确认密码不相同！</font>");
				$("#user_pass").focus();
				flag = false;
			}
			var user_type = trim($("#user_type").val());
			if(user_type != ""){
				$("#user_typeMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
			}else{
				$("#user_typeMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>类型不能为空！</font>");
				$("#user_type").focus();
				flag = false;
			}
			return flag;
		}
		function doSubmit(){
			if(check()){
				document.register.submit();
			}
		}
		function doReset(){
			document.register.reset();
		}
		//去前后空格
		function trim(text){
			return text.replace(/^\s+|\s+$/,"");
		}
		function selectStudent(){
			var url = "StudentServlet?method=selectStudent";
			window.open(url, "选择学员", "height=650,width=1200,top=10,left=75,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no", "");
		}
		
		function getStudentInfo(arg){
			var student = arg.split("####");
			if(student != "" && student != null && student != "undefined"){
				$("#user_desc").val(student[0]);
				$("#stu_name").val(student[1]);
				$("#user_type").val(student[2]);
			}
		}
		function selectCompany(){
			var url = "CompanyServlet?method=selectCompany";
			window.open(url, "选择企业", "height=650,width=1200,top=10,left=75,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no", "");
		}
		
		function getCompanyInfo(arg){
			var company = arg.split("####");
			if(company != "" && company != null && company != "undefined"){
				$("#user_desc").val(company[0]);
				$("#stu_name").val(company[1]);
				$("#user_type").val(company[2]);
			}
		}
		
		
		
</script> 
<style>
dl,dt,dd,span{margin:0;padding:0;display:inline-block;}
</style>
</head>

<body style="background-color:#df7611; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">



    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  


<div class="logintop" >    
    <span>精彩之旅注册后马上呈现</span>    
    <ul >
    <li><a href="index.jsp">回首页</a></li>
    <li><a href="#">帮助</a></li>
    <li><a href="#">关于</a></li>
    </ul>    
    </div>
    
    <div class="loginbody">
    
    <span class="systemlogo"></span> 
       
    <div class="loginbox">
    <form action="UserServlet?method=register" method="post" name="register" id="register">
	    <ul style="margin-top:35px; margin-left:240px;width:600px;">
	   
	   
	    <%--获取账户名 --%>
	    <class="tdrsname" align="left"></class>
	    <li style ="margin-bottom:20px;margin-top:10px;">&nbsp;&nbsp;账&nbsp;号:&nbsp;&nbsp;<input id="user_name" name="user_name" type="text" class="loginuser"  value='请输入您的昵称' onclick="JavaScript:this.value=''"/><span id="user_nameMsg"></span></li>
	    
	    
	    <%--获取账户密码 --%>
	    <li style ="margin-bottom:20px;margin-top:10px;">&nbsp;&nbsp;密&nbsp;码:&nbsp;&nbsp;<input id="user_password" name="user_password" type="text" class="loginpwd" value="密码长度 4~21" onclick="JavaScript:this.value=''" height=20px /><span id="user_passwordMsg"></span></li>
	    
	    <%--获取确认的账户密码 --%>
	         <li style="margin-top:20px;margin-bottom:20px;">&nbsp;&nbsp;密&nbsp;码:&nbsp;&nbsp;<input id="user_pass" name="user_pass" type="text" class="loginpwd" value="保持与密码一致" onclick="JavaScript:this.value=''"/><span id="user_passMsg"></span></li>
	
	    <li>
	    	<input type="hidden" name="user_checkstatus" id ="user_checkstatus" value="3"></input>
	    	<input type="hidden" name="user_type" id="user_type">
			<input type="hidden" id="user_desc" name="user_desc" value="${user.user_desc }"/>
			<img alt="点击添加学员" src="<%=basePath%>images/icon12.png" style="float: left;cursor: pointer;" onclick="selectStudent()"/>
			<img alt="点击添加企业" src="<%=basePath%>images/icon13.png" style="float: left;cursor: pointer;" onclick="selectCompany()"/>
		 	<input class="dfinput" type="text" id="stu_name" name="stu_name" value="${user.user_name }" style="width: 140px;float: left; text-align: center;" readonly="readonly"/>
			<span style="width: 5px;float: left;"></span>
		 </li>
		<li><input  name="注册" type="button" class="loginbtn" value="注册"  onclick="doSubmit()"  />
		
	    </ul>
    </form>
    
    </div>
    
    </div>
    
    
    
    <div class="loginbm">版权所有  2017  <a href="http://www.uimaker.com">《《赶快注册开启新的人生篇章》》</赶快注册开启新的人生篇章></a>  </div>
	
    
</body>

</html>
