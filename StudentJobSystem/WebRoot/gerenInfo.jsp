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
    
    <title>个人信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="css/style2.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="<%=basePath%>js/show.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		$("#showstudent").hide();
  		$("#student").mouseover(function(){
    		$("#showstudent").show();
  		});
  		$("#showstudent").mouseover(function(){
    		$("#showstudent").show();
  		});
  		$("#student").mouseout(function(){
    		$("#showstudent").hide();
  		});
	 	$("#showstudent").mouseout(function(){
    		$("#showstudent").hide();
  		});
	});
  /*teacher*/
  $(document).ready(function(){
		$("#showteacher").hide();
  		$("#teacher").mouseover(function(){
    		$("#showteacher").show();
  		});
  		$("#showteacher").mouseover(function(){
    		$("#showteacher").show();
  		});
  		$("#teacher").mouseout(function(){
    		$("#showteacher").hide();
  		});
	 	$("#showteacher").mouseout(function(){
    		$("#showteacher").hide();
  		});
	});
  /*company*/
  $(document).ready(function(){
		$("#showcompany").hide();
  		$("#company").mouseover(function(){
    		$("#showcompany").show();
  		});
  		$("#showcompany").mouseover(function(){
    		$("#showcompany").show();
  		});
  		$("#company").mouseout(function(){
    		$("#showcompany").hide();
  		});
	 	$("#showcompany").mouseout(function(){
    		$("#showcompany").hide();
  		});
	});
  
	function doUpdate(){
		window.location.href = "<%=basePath%>StudentServlet?method=edit&stu_id=${stuv.stu_id }";
	}
	function addJobInfo(){
		window.location.href = "<%=basePath%>JobInfoServlet?method=edit&stu_id=${stuv.stu_id }";
	}

	</script>
  </head>
  
  <body>
    <!-- 顶部-->
    <div id="head_top">
        <div id="head_top_section">
            <div id="head_top_left">软件工程7班最棒小组作品</div>
            <div id="head_top_right"><img src="img/nav-icon.png">全国咨询热线：0351-100000000 设为首页 / 联系我们</div>
        </div>
    </div>

    <div id="head_bottom">
        <div id="head_bottom_section">
            <img src="img/logo.png">
            <div class="zb_logoC">
            <dl>
            <dd><a href="index.jsp">网站首页</a></dd>
            <dd id="student"><a href="student.jsp">学员展示</a></dd>
	            	<ul id="showstudent">
	            	<c:choose>
                		<c:when test="${role.role_id == 20 }">
	                		<li><a href="UserServlet?method=tiaozhuan&URL=gerenInfo.jsp">个人信息</a></li>
	            			<!-- <li><a href="UserServlet?method=tiaozhuan&URL=find_student.jsp">查询学员</a></li> -->
	            			<li><a href="UserServlet?method=tiaozhuan&URL=StudentServlet?method=getByStuId&stu_id=${stuv.stu_id }">个人就业</a></li>
						
						
						</c:when>
						<c:otherwise>
							<!-- <li><a href="login.html">个人信息</a></li>
	            			<li><a href="login.html">查询学员</a></li>
	            			<li><a href="login.html">学员查询</a></li> -->
	            		</c:otherwise>
					</c:choose>	
	            	</ul>
            <dd id="teacher"><a href="teacher.jsp">教师展示</a></dd>
            	<%-- <ul id="showteacher">
            		<c:choose>
                		<c:when test="${role.role_id == 22||role.role_id == 26||role.role_id == 28 }">
	                		<li><a href="UserServlet?method=tiaozhuan&URL=TeacherServlet?method=view&tea_id=${user.user_desc }">教师个人信息</a></li>
	            			<li><a href="UserServlet?method=tiaozhuan&URL=TeacherServlet?method=query">查询教师</a></li>
						
						</c:when>
						<c:otherwise>
							<li><a href="login.html">教师个人信息</a></li>
	            			<li><a href="login.html">查询教师</a></li>
						</c:otherwise>
					</c:choose>	
	            		
	            	</ul> --%>
            <dd id="company"><a href="company.jsp">企业展示</a></dd>
            	<ul id="showcompany">
            		<c:choose>
                		<c:when test="${role.role_id == 22 }">
	                		<li><a href="UserServlet?method=tiaozhuan&URL=CompanyServlet?method=view&com_id=${user.user_desc }">企业信息</a></li>
	            			<!-- <li><a href="UserServlet?method=tiaozhuan&URL=CompanyServlet?method=selectCompany">查询企业</a></li>
	            		 -->
						</c:when>
						<c:otherwise>
							<li><a href="login.html">企业信息</a></li>
	            			<!-- <li><a href="login.html">查询企业</a></li> -->
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
			  <!-- <dd><a href="register.jsp">注册</a></dd> -->
			
            </dl>
            </div>
        </div>
    </div>
     
     <div id="center">   
     <div id="main" >
        <div style="text-align: center;padding: 15px;font-family:'宋体';font-size: 20px;color:black;">学员详情</div>
		<div style="border-bottom: 1px solid black;"></div>
		<div style="left:460px;position:absolute"te >
		<div class="item-section " style="font-size: 20px;color:black;">
			<div class="mask-area" >
	    	<h3><span class="b f14">基本信息</span></h3>
			<div class="item-bd">
				<div id="base-info" class="info-area clearfix" style="width: 100% ">
					<div style="float:left;width: 80% ">
						<table style="left:200px">
						<colgroup><col width="160"><col width="*"></colgroup>
						<tbody>
						<tr><th>姓名：</th>
								<td>
								${stuv.stu_name }
									</td>
							</tr>
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
							<%-- <tr><th>出生日期：</th><td>${stuv.stu_birth }</td></tr> --%>
							<tr><th>籍贯：</th><td>${stuv.stu_native }</td></tr>
							<tr><th>住址：</th><td>${stuv.stu_address }</td></tr>
							<%-- <tr><th>家长姓名：</th><td>${stuv.stu_pare_name }</td></tr>
							<tr><th>家长联系方式：</th><td>${stuv.stu_pare_phone }</td></tr>
							<tr><th>紧急联系人电话：</th><td>${stuv.stu_urg_phone }</td></tr> --%>
							<tr><th>当前状态：</th>
								<td>
								${stuv.stu_state }
									</td>
							</tr>
	    				</tbody>
						</table>
						<div class="mask-area">
	    	<h3><span class="b f14">联系方式</span></h3>
			<div class="item-bd">
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
							<%-- <tr><th>院系：</th><td>${stuv.stu_department }</td></tr> --%>
							<tr><th>专业：</th><td>${stuv.stu_major }</td></tr>
							<%-- <tr><th>学历：</th><td>${stuv.stu_education }</td></tr> --%>
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
		
			<br>
	
		<div style="padding-bottom: 20px; text-align: center;position: relative;">
			<input type="button" value="修改" onclick="doUpdate();"/>
			<input type="button" value="添加就业信息" onclick="addJobInfo();"/>
			<input type="button" value="返回" onclick="javascript:history.back();"/>
		</div>
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
		


	</div>
	</div>

	</div>
	</div>
	
  </body>
</html>
