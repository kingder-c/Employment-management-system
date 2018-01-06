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
    
    <title>首页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="css/style2.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/show.js"></script>
	
    <style>
        index.jspcase2{
            padding-top: 50px;
            height: 1500px;
        }
    </style>

</head>

<body>
    <!-- 顶部-->
    <div id="head_top">
        <div id="head_top_section">
            <div id="head_top_left">嗨，欢迎来到我们！7年来我们一直专注网络品牌设计！</div>
            <div id="head_top_right"><img src="img/nav-icon.png">全国咨询热线：0351-100000000 设为首页 / 联系我们
            <c:if test="${role.role_id == 26||role.role_id == 28 }">
            <a href="main.html" style=" text-decoration:none;color:#fff;">登录后台</a>
            </c:if>
            </div>
        </div>
    </div>

    <div id="head_bottom">
        <div id="head_bottom_section">
            <img src="img/logo.png">
            <div class="zb_logoC">
            <dl>
            <dd><a href="index.jsp">网站首页</a></dd>
            <dd id="student"><a href="StudentServlet?method=query&indexRole=com">学员展示</a></dd>
	            	<ul id="showstudent">
	            	<c:choose>
                		<c:when test="${role.role_id == 20 }">
	                		<li><a href="UserServlet?method=tiaozhuan&URL=gerenInfo.jsp">个人信息</a></li>
	            			<!-- <li><a href="UserServlet?method=tiaozhuan&URL=find_student.jsp">查询学员</a></li> -->
	            			<li><a href="UserServlet?method=tiaozhuan&URL=StudentServlet?method=getByStuId&stu_id=${stuv.stu_id }">个人就业</a></li>
						
						
						</c:when>
						<c:otherwise>
						<!-- 	<li><a href="login.html">个人信息</a></li>
	            			<li><a href="login.html">查询学员</a></li>
	            			<li><a href="login.html">学员查询</a></li> -->
	            		</c:otherwise>
					</c:choose>	
	            	</ul>
            <dd id="teacher"><a href="teacher.jsp">教师展示</a></dd>
            	<ul id="showteacher">
            		<c:choose>
                		<c:when test="${role.role_id == 28 }">
	                		<li><a href="UserServlet?method=tiaozhuan&URL=TeacherServlet?method=view&tea_id=${user.user_desc }">教师个人信息</a></li>
	            			<!-- <li><a href="UserServlet?method=tiaozhuan&URL=TeacherServlet?method=query">查询教师</a></li> -->
						
						</c:when>
						<c:otherwise>
							<!-- <li><a href="login.html">教师个人信息</a></li> -->
	            		<!-- 	<li><a href="login.html">查询教师</a></li> -->
						</c:otherwise>
					</c:choose>	
	            		
	            	</ul>
            <dd id="company"><a href="company.jsp">企业展示</a></dd>
            	<ul id="showcompany">
            		<c:choose>
                		<c:when test="${user.user_type == 22}">
	                		<li><a href="UserServlet?method=tiaozhuan&URL=CompanyServlet?method=view&com_id=${user.user_desc }">企业信息</a></li>
	                		<li><a href="UserServlet?method=tiaozhuan&URL=CompanyServlet?method=selectCompany">查询企业</a></li>
	        	        </c:when >
	                	<c:when test="${user.user_type == 20}">	
	            			<li><a href="UserServlet?method=tiaozhuan&URL=CompanyServlet?method=selectCompany">查询企业</a></li>
	            		 
						</c:when>
						<c:otherwise>
							<!-- <li><a href="login.html">企业信息</a></li> -->
	            			 <li><a href="login.html">查询企业</a></li> 
							</c:otherwise>
					</c:choose>	
	            		
	            	</ul>
            <dd><a href="course.jsp">课程展示</a></dd>
            <dd><a href="aboutus.jsp">联系我们</a></dd>

             <c:choose>
                <c:when test="${user == null }">
	                <dd><a href="login.html">登录</a></dd>
				</c:when>
				<c:otherwise>
					<dd><a href="UserServlet?method=clean">退出登录</a></dd>
				</c:otherwise>
			</c:choose>
			 <!--  <dd><a href="register.jsp">注册</a></dd> -->
			
            </dl>
            </div>
        </div>
    </div>
    
    <!--banner-->
    <!--
    <div id="banner">
    </div>
    -->
	<div id="img" >
    	<script language="javascript" src="js/pic.js"></script>
   
	</div>

    <!-- 底部-->
    <div id="foot">
        <img src="img/footer-icon.png"/>
        <span>咨询热线：18435186359|邮箱：yannyuyao@163.com</span>
        <span>地址：太原市尖草坪区学院路3号中北大学   网址：WWW.yanyuyao.COM</span>
        <span>COPYRGHT ©2008-2015 THINKARTCO,LTD.ALL RIGHTS RESERVED 网站备案号：晋ICP2015897554号</span>
    </div>

</body>
</html>