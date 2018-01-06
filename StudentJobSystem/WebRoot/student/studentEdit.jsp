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
    
    <title>学员信息编辑页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css">
	
	<script type="text/javascript" src="ckeditor/ckeditor.js"></script>  
	<script type="text/javascript" src="ckeditor/config.js"></script>

	
	<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
	<script src="<%=basePath%>My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script type="text/javascript">
	
	
		function check(){
			var stu_name = trim($("#stu_name").val());
			if(stu_name != ""){
				$("#stu_nameMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
			}else{
				$("#stu_nameMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>学员姓名不能为空！</font>");
				$("#stu_nameMsg").focus();
				return false;
			}
			
			
			<%-- var stu_birth = trim($("#stu_birth").val());
			if(stu_birth != ""){
				$("#stu_birthMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
			}else{
				$("#stu_birthMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>学员生日不能为空！</font>");
				$("#stu_birthMsg").focus();
				return false;
			}
			
			var stu_qq = trim($("#stu_qq").val());
			
			if(stu_qq != ""){
				$("#stu_qqMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
			}else{
				$("#stu_qqMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>学员QQ不能为空！</font>");
				$("#stu_qqMsg").focus();
				return false;
			}
			if(stu_qq.length >4 && stu_qq.length<16 ){
				$("#stu_qqMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
			}else{
				$("#stu_qqMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>QQ的长度是 5~15位 ！</font>");
				$("#stu_qqMsg").focus();
				return false;
			}
			
			
			var stu_weixin = trim($("#stu_weixin").val());
			if(stu_weixin != ""){
				$("#stu_weixinMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
			}else{
				$("#stu_weixinMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>学员维信号不能为空！</font>");
				$("#stu_weixinMsg").focus();
				return false;
			} --%>
			
			var stu_email = trim($("#stu_email").val());
			if(true){
				if(stu_email.length<6){
					$("#stu_emailMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>学员邮箱的格式： xx@xx.com！</font>");
					$("#stu_emailMsg").focus();
					return false;
				}
				var n = stu_email.indexOf("@");
				if(n == -1){
					$("#stu_emailMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>邮箱的格式不正确，需要有@！！</font>");
					$("#stu_emailMsg").focus();
					return false;
				}
				var n1= stu_email.lastIndexOf(".com");
				if(n1 != stu_email.length-4){
					$("#stu_emailMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>邮箱的格式不正确，需要有.com！！</font>");
					$("#stu_emailMsg").focus();
					return false;
				}
				$("#stu_emailMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
			}
			var stu_phone = trim($("#stu_phone").val());
			if(stu_phone.length==11 
			&& (
				stu_phone.indexOf("13")==0||
				stu_phone.indexOf("15")==0||
				stu_phone.indexOf("16")==0||
				stu_phone.indexOf("18")==0)
			){
				$("#stu_phoneMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
			}else{
				$("#stu_phoneMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>手机号的格式不正确！</font>");
				$("#stu_phoneMsg").focus();
				return false;
			}
			
			
			var stu_into_time = trim($("#stu_into_time").val());
			if(stu_into_time != ""){
				$("#stu_into_timeMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
			}else{
				$("#stu_into_timeMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>入学时间不能为空！</font>");
				$("#stu_into_timeMsg").focus();
				return false;
			}
			
		<%-- 	var stu_urg_phone = trim($("#stu_urg_phone").val());
			if(stu_urg_phone.length==11 
			&& (
				stu_urg_phone.indexOf("13")==0||
				stu_urg_phone.indexOf("15")==0||
				stu_urg_phone.indexOf("16")==0||
				stu_urg_phone.indexOf("18")==0)
			){
				$("#stu_urg_phoneMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
			}else{
				$("#stu_urg_phoneMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>手机号的格式不正确！</font>");
				$("#stu_urg_phoneMsg").focus();
				return false;
			}
			
			var stu_pare_phone = trim($("#stu_pare_phone").val());
			if(stu_pare_phone.length==11 
			&& (
				stu_pare_phone.indexOf("13")==0||
				stu_pare_phone.indexOf("15")==0||
				stu_pare_phone.indexOf("16")==0||
				stu_pare_phone.indexOf("18")==0)
			){
				$("#stu_pare_phoneMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
			}else{
				$("#stu_pare_phoneMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>手机号的格式不正确！</font>");
				$("#stu_pare_phoneMsg").focus();
				return false;
			} --%>
			
			return true;
		}
		function doSubmit(){
			if(check()){
				document.edit.submit();
			}
		}
		function doFind(){
			window.open("ClassesServlet?method=query", "选择班级","height=300,width=1200,top=10,left=75,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no",  false);
			
		}
		function doUploadImg(){
			var url = "ImgCutUploadServlet?method=getPathParam&pathParam=studentImg&picWidth=222&picHeight=305";
			window.open(url, "uploadFiles", "height=425, width=1000, top=120, left=200, toolbar=no, menubar=no, scrollbars=yes, resizable=no, location=no, status=no");
		}
		
		function callbackPfn(data){
			var stuImg = data.arg.src;
		   	if(stuImg != "" && stuImg != null && stuImg != "undefined"){
				$("#stu_img").val(stuImg);
				$("#stuImg").attr("src", stuImg);
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
				<c:when test="${stuv != null }">
					<li><a href="javascript:void(0)">修改学员信息</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="javascript:void(0)">新增学员信息</a></li> 
				</c:otherwise>
			</c:choose>
    	</ul>
	</div>
	
	
	
		  
	
	
	
	
	
	<div align="left" style="margin-top: 5px;">
		<form action="StudentServlet?method=save" id="edit" name="edit" method="post">
			<table border="0">
				<tr>
					<td colspan="3" class="formtitle">学员信息编辑</td>
				</tr>
				<tr>
					<td>
						<input type="hidden" id="stu_id" name="stu_id" value="${stuv.stu_id }">
					</td>
					<c:choose>
					<c:when test="${stuv != null }">
						<td>
						<input type="hidden" id="cla_id" name="cla_id" value="${stuv.cla_id }">
						</td>
					</c:when>
					<c:otherwise>
						<tr>
						<td width="150" class="tdrsname" align="right">学员所在班级：</td>
						<td width="300" class="tdrsname">
						<select class="dfinput" id="cla_id" name="cla_id"  style="width: 300px;"> 		
						<c:forEach items="${select_cla_idList }" var="classes">
							<c:choose>
							<c:when test="${classes.cla_id ==stuv.cla_id }">
								<option value=${stuv.cla_id } checked>${classes.cla_name }</option>
							</c:when>
							<c:otherwise>
								<option value=${classes.cla_id } >${classes.cla_name }</option>
							</c:otherwise>
							</c:choose>
						</c:forEach>
 						</select>
 						</td>
						<td width="auto" class="tdrsname"><span id="cla_idMsg"></span></td>
						</tr>
					</c:otherwise>
					</c:choose>
					
					
					<td>
						<input type="hidden" id="stu_delete" name="stu_delete" value="${stuv.stu_delete }">
					</td>
				</tr>
				<tr>
					<td width="150" class="tdrsname" align="right">学员姓名：</td>
					<td width="300" class="tdrsname">
						<input class="dfinput" type="text" id="stu_name" name="stu_name" value="${stuv.stu_name }" style="width: 300px;"> 
						
					</td>
					<td><font color="red">*必填*</font></td>
					<td width="auto" class="tdrsname"><span id="stu_nameMsg"></span></td>
					
				</tr>
				<%-- <tr>
					<td class="tdrsname" align="right">学员生日：</td>
					<td  class="tdrsname" >
						<input class="Wdate" type="text" id="stu_birth" name="stu_birth" value="${stuv.stu_birth }" onClick="WdatePicker({skin:'whyGreen',minDate:'1980-01-1',maxDate:'2200-12-31'})" style="height :30px;width:300px;" > 
						
					</td>
					<td><font color="red">*必填*</font></td>
					<td width="auto" class="tdrsname"><span id="stu_birthMsg"></span></td>
				</tr> --%>
 				<tr>
 					<td class="tdrsname" align="right">学员性别：</td>
					<td class="tdrsname" align="left">
					<select class="dfinput" id="stu_sex" name="stu_sex"  style="width: 300px;">			
						<c:forEach items="${select_stu_sex }" var="data">
							<c:choose>
							<c:when test="${data.data_num ==stuv.stu_sex }">
								<option value=${stuv.stu_sex } selected>--${data.data_name }--</option>
							</c:when>
							<c:otherwise>
								<option value=${data.data_num } >${data.data_name }</option>
							</c:otherwise>
							</c:choose>
						</c:forEach>
 					</select>
 					
 					<td width="auto" class="tdrsname"><span id="stu_sexMsg"></span></td>
				</tr>
 				
				<tr>
					<td class="tdrsname" align="right">学员籍贯：</td>
					<td  class="tdrsname">
						<input class="dfinput" type="text" id="stu_native" name="stu_native" value="${stuv.stu_native }" style="width: 300px;"> 
					</td>
					<td width="auto" class="tdrsname"><span id="stu_nativeMsg"></span></td>
				</tr>
				<tr>
					<td class="tdrsname" align="right">学员住址：</td>
					<td  class="tdrsname">
						<input class="dfinput" type="text" id="stu_address" name="stu_address" value="${stuv.stu_address }" style="width: 300px;"> 
					</td>
					<td width="auto" class="tdrsname"><span id="stu_addressMsg"></span></td>
				</tr>
				<%-- <tr>
					<td class="tdrsname" align="right">学员QQ：</td>
					<td  class="tdrsname">
						<input class="dfinput" type="text" id="stu_qq" name="stu_qq" value="${stuv.stu_qq }" style="width:300px;"> 
						
					</td>
					<td><font color="red">*必填*</font></td>
					<td width="auto" class="tdrsname"><span id="stu_qqMsg"></span></td>
				</tr> --%>
				<%-- <tr>
					<td class="tdrsname" align="right">学员微信号：</td>
					<td  class="tdrsname">
						<input class="dfinput" type="text" id="stu_weixin" name="stu_weixin" value="${stuv.stu_weixin }" style="width: 300px;"> 
						
					</td>
					<td><font color="red">*必填*</font></td>
					<td width="auto" class="tdrsname"><span id="stu_weixinMsg"></span></td>
				</tr> --%>
				<tr>
					<td class="tdrsname" align="right">学员邮箱：</td>
					<td  class="tdrsname">
						<input class="dfinput" type="text" id="stu_email" name="stu_email" value="${stuv.stu_email }" style="width: 300px;"> 
						
					</td>
					<td><font color="red">*必填*</font></td>
					
					<td width="auto" class="tdrsname"><span id="stu_emailMsg"></span></td>
				</tr>
				<tr>
					<td class="tdrsname" align="right">学员手机：</td>
					<td  class="tdrsname">
						<input class="dfinput" type="text" id="stu_phone" name="stu_phone" value="${stuv.stu_phone }" style="width: 300px;"> 
						
					</td>
					<td><font color="red">*必填*</font></td>
					<td width="auto" class="tdrsname"><span id="stu_phoneMsg"></span></td>
				</tr>
				<%-- <tr>
					<td class="tdrsname" align="right">学员学历：</td>
					<td  class="tdrsname">
						<select class="dfinput" type="text" id="stu_education" name="stu_education"  style="width: 300px;"> 
									<option value="${stuv.stu_education }" selected>--${stuv.stu_education }-- </option>
									<option value="专科">专科</option>
									<option value="本科">本科</option>
									<option value="硕士">硕士</option>
									<option value="博士">博士</option>
						</select>
					</td>
					<td width="auto" class="tdrsname"><span id="stu_educationMsg"></span></td>
				</tr> --%>
				<tr>
					<td class="tdrsname" align="right">毕业学校：</td>
					<td  class="tdrsname">
						<input class="dfinput" type="text" id="stu_graduation" name="stu_graduation" value="${stuv.stu_graduation }" style="width: 300px;"> 
					</td>
					<td width="auto" class="tdrsname"><span id="stu_graduationMsg"></span></td>
				</tr>
				<%-- <tr>
					<td class="tdrsname" align="right">院系：</td>
					<td  class="tdrsname">
						<input class="dfinput" type="text" id="stu_department" name="stu_department" value="${stuv.stu_department }" style="width: 300px;"> 
					</td>
					<td width="auto" class="tdrsname"><span id="stu_departmentMsg"></span></td>
				</tr> --%>
				<tr>
					<td class="tdrsname" align="right">专业：</td>
					<td  class="tdrsname">
						<input class="dfinput" type="text" id="stu_major" name="stu_major" value="${stuv.stu_major }" style="width: 300px;"> 
					</td>
					<td width="auto" class="tdrsname"><span id="stu_majorMsg"></span></td>
				</tr>
				<tr>
					<td class="tdrsname" align="right">大学入学年份：</td>
					<td  class="tdrsname">
						<input class="Wdate" type="text" id="stu_into_time" name="stu_into_time" value="${stuv.stu_into_time }" onClick="WdatePicker({skin:'whyGreen',minDate:'1980-09-10',maxDate:'2200-12-20'})" style="height:30px;width:300px;"> 
						
					</td>
					<td><font color="red">*必填*</font></td>
					<td width="auto" class="tdrsname"><span id="stu_into_timeMsg"></span></td>
				</tr>
				<%-- <tr>
					<td class="tdrsname" align="right">紧急联系电话：</td>
					<td  class="tdrsname">
						<input class="dfinput" type="text" id="stu_urg_phone" name="stu_urg_phone" value="${stuv.stu_urg_phone }" style="width: 300px;"> 
						
					</td>
					<td><font color="red">*必填*</font></td>
					<td width="auto" class="tdrsname"><span id="stu_urg_phoneMsg"></span></td>
				</tr> --%>
				<%-- <tr>
					<td class="tdrsname" align="right">家长姓名：</td>
					<td  class="tdrsname">
						<input class="dfinput" type="text" id="stu_pare_name" name="stu_pare_name" value="${stuv.stu_pare_name }" style="width: 300px;"> 
					</td>
					<td width="auto" class="tdrsname"><span id="stu_pare_nameMsg"></span></td>
				</tr> --%>
				<%-- <tr>
					<td class="tdrsname" align="right">家长联系方式：</td>
					<td  class="tdrsname">
						<input class="dfinput" type="text" id="stu_pare_phone" name="stu_pare_phone" value="${stuv.stu_pare_phone }" style="width: 300px;"> 
						
					</td>
					<td><font color="red">*必填*</font></td>
					<td width="auto" class="tdrsname"><span id="stu_pare_phoneMsg"></span></td>
				</tr> --%>
				<%-- <tr>
					<td class="tdrsname" align="right">头像：</td>
					<td  class="tdrsname">
						
						<input type="hidden"  id="stu_img" name="stu_img" value="${stuv.stu_img } "/>
        					<c:if test="${not empty stuv.stu_img }">
	        					<img id="stuImg" src="${stuv.stu_img }" width="100" width="100" height="137" title="点此处上传图片" style="cursor:pointer;" onclick="doUploadImg()"/><br>
	        					<span style="color:red">&nbsp;点击图片上传头像，建议图片大小为222*305</span>
        					</c:if>
        					<c:if test="${empty stuv.stu_img }">
	        					<img id="stuImg" src="<%=basePath%>images/touxiang/touxiang.jpg" width="100" width="100" height="137" title="点此处上传图片" style="cursor:pointer;" onclick="doUploadImg()"/><br>
	        					<span style="color:red">&nbsp;点击图片上传头像，建议图片大小为222*305</span>
        					</c:if>
        					
					</td>
					<td width="auto" class="tdrsname"><span id="stu_imgMsg"></span></td>
				</tr> --%>
				<%-- <tr>
 					<td class="tdrsname" align="right">学员状态：</td>
					<td  class="tdrsname">
					<select class="dfinput" id="stu_state" name="stu_state" value="${stuv.stu_state }" style="width: 300px;"> 			
						<c:forEach items="${select_stu_state }" var="data">
							<c:choose>
							<c:when test="${data.data_num ==stuv.stu_state }">
								<option value=${stuv.stu_state } selected>--${data.data_name }--</option>
							</c:when>
							<c:otherwise>
								<option value=${data.data_num } >${data.data_name }</option>
							</c:otherwise>
							</c:choose>
						</c:forEach>
 					</select>
 					<td width="auto" class="tdrsname"><span id="stu_stateMsg"></span></td>
				</tr> --%>
				
				<%-- <tr>
 					<td class="tdrsname" align="right">学员审核状态：</td>
					<td  class="tdrsname">
					<select class="dfinput" id="stu_check" name="stu_check" value="${stuv.stu_check }" style="width: 300px;"> 			
						<c:forEach items="${select_stu_check }" var="data">
							<c:choose>
							<c:when test="${data.data_num ==stuv.stu_check }">
								<option value=${stuv.stu_check } selected>--${data.data_name }--</option>
							</c:when>
							<c:otherwise>
								<option value=${data.data_num } >${data.data_name }</option>
							</c:otherwise>
							</c:choose>
						</c:forEach>
 					</select>
 					<td width="auto" class="tdrsname"><span id="stu_stateMsg"></span></td>
				</tr> --%>
				
				<tr>
					<td class="tdrsname" align="right">学员备注：</td>
					<td  class="tdrsname">
						<textarea class="ckeditor" id="stu_note" name="stu_note" value="${stuv.stu_note }">${stuv.stu_note }</textarea>
					</td>
					<td width="auto" class="tdrsname"><span id="stu_noteMsg"></span></td>
				</tr>
				<tr>
					<td class="tdrsname" align="right">学员录入人：</td>
					<td  class="tdrsname">
						<input class="dfinput" type="text" id="stu_adder" name="stu_adder" value="${stuv.stu_adder }" style="width: 300px;"> 
					</td>
					<td width="auto" class="tdrsname"><span id="stu_adderMsg"></span></td>
				</tr>
				<%-- <tr>
					<td class="tdrsname" align="right">学员录入时间：</td>
					<td  class="tdrsname">
						<input class="Wdate" type="text" id="stu_addtime" name="stu_addtime" value="${stuv.stu_addtime }" onClick="WdatePicker({skin:'whyGreen',minDate:'1980-09-10',maxDate:'2200-12-20'})" style="height:30px;"> 
					</td>
					<td width="auto" class="tdrsname"><span id="stu_addtimeMsg"></span></td>
				</tr> --%>
				
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
