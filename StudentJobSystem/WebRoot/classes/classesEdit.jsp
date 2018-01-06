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
    
    <title>班级信息编辑页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css">
	<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
	<script src="<%=basePath%>My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script type="text/javascript">
		function check(){
			var cla_name = trim($("#cla_name").val());
			if(cla_name != ""){
				$("#cla_nameMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
				
			}else{
				$("#cla_nameMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>班级名称不能为空！</font>");
				$("#cla_nameMsg").focus();
				return false;
			}
			
			<%-- var cla_QQ = trim($("#cla_QQ").val());
			if(cla_QQ != ""){
				$("#cla_QQMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
			}else{
				$("#cla_QQMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>班级QQ不能为空！</font>");
				$("#cla_QQMsg").focus();
				return false;
			} --%>
			
			var cla_starttime = trim($("#cla_starttime").val());
			if(cla_starttime != ""){
				$("#cla_starttimeMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
				
			}else{
				$("#cla_starttimeMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>不能为空！</font>");
				$("#cla_starttimeMsg").focus();
				return false;
			}
			
			<%-- var cla_wantendtime = trim($("#cla_wantendtime").val());
			if(cla_wantendtime != ""){
				$("#cla_wantendtimeMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
				
			}else{
				$("#cla_wantendtimeMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>不能为空！</font>");
				$("#cla_wantendtimeMsg").focus();
				return false;
			}
			
			var cla_trueendtime = trim($("#cla_trueendtime").val());
			if(cla_trueendtime != ""){
				$("#cla_trueendtimeMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
				
			}else{
				$("#cla_trueendtimeMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>不能为空！</font>");
				$("#cla_trueendtimeMsg").focus();
				return false;
			}
			
			if(cla_QQ.length >4 && cla_QQ.length<16 ){
				$("#cla_QQMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
			}else{
				$("#cla_QQMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>QQ的长度是 5~15位 ！</font>");
				$("#cla_QQMsg").focus();
				return false;
			} --%>
			
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



		function doFind(){
			window.open("CourseServlet?method=query", "选择课程","height=360,width=1200,top=10,left=75,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no",  false);
			
		}

	</script>
  </head>
  
  <body>
    <!-- 顶部路径栏 -->
	<div class="place"><span>位置：</span>
    	<ul class="placeul">
        	<li><a href="javascript:void(0)">首页</a></li>
        	<c:choose>
				<c:when test="${cla != null }">
					<li><a href="javascript:void(0)">修改班级信息</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="javascript:void(0)">新增班级信息</a></li> 
				</c:otherwise>
			</c:choose>
    	</ul>
	</div>
	<div align="left" style="margin-top: 5px;">
		<form action="ClassesServlet?method=save" id="edit" name="edit" method="post">
			<table border="0">
				<tr>
					<td colspan="3" class="formtitle">班级信息编辑</td>
				</tr>
				
				<tr>
					<td>
						<input type="hidden" id="cla_id" name="cla_id" value="${cla.cla_id }">
					</td>
					<c:choose>
						<c:when test="${cla != null }">
							<td>
							<input type="hidden" id="cou_id" name="cou_id" value="${cla.cou_id }">
							</td>
						</c:when>
						<c:otherwise>
							<tr>
							<td width="150" class="tdrsname" align="right">班级开设课程：</td>
							<td width="360" class="tdrsname">
								<select class="dfinput" id="cou_id" name="cou_id"  style="width: 360px;"> 		
									<c:forEach items="${select_cou_idList }" var="course">
										<c:choose>
										<c:when test="${course.cou_id==cla.cou_id }">
											<option value=${cla.cou_id } checked>${course.cou_name }-</option>
											<%-- <option value=${course.cou_id } checked>${course.cou_name }</option> --%>
										</c:when>
										<c:otherwise>
											<option value=${course.cou_id }>${course.cou_name }</option>
										</c:otherwise>
										</c:choose>
									</c:forEach>
		 						</select>
							</td>
							<td>
							<td width="auto" class="tdrsname"><span id="cou_idMsg"></span></td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tr>
				
				<tr>
					<c:choose>
						<c:when test="${cla != null }">
							<td>
							<input type="hidden" id="tea_id" name="tea_id" value="${cla.tea_id }">
							</td>
						</c:when>
						<c:otherwise>
							<tr>
							<td width="150" class="tdrsname" align="right">授课教师：</td>
							<td width="360" class="tdrsname">
								<select class="dfinput" id="tea_id" name="tea_id"  style="width: 360px;"> 		
									<c:forEach items="${select_tea_idList }" var="teacher">
										<c:choose>
										<c:when test="${teacher.tea_id==cla.tea_id }">
											<option value=${cla.tea_id } checked>${teacher.tea_name }-</option>
											<%-- <option value=${course.cou_id } checked>${course.cou_name }</option> --%>
										</c:when>
										<c:otherwise>
											<option value=${teacher.tea_id }>${teacher.tea_name }</option>
										</c:otherwise>
										</c:choose>
									</c:forEach>
		 						</select>
							</td>
							<td>
							<td width="auto" class="tdrsname"><span id="cou_idMsg"></span></td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tr>
				
				<tr>
					<td class="tdrsname" align="right">班级名称：</td>
					<td  class="tdrsname">
						<input class="dfinput" type="text" id="cla_name" name="cla_name" value="${cla.cla_name }" style="width: 360px;"> 
					</td>
					<td width="auto" class="tdrsname"><span id="cla_nameMsg"></span></td>
				</tr>
 				
				<%-- <tr>
					<td class="tdrsname" align="right">授课地点：</td>
					<td  class="tdrsname">
						<input class="dfinput" type="text" id="cla_address" name="cla_address" value="${cla.cla_address }" style="width: 360px;"> 
					</td>
					<td width="auto" class="tdrsname"><span id="cla_addressMsg"></span></td>
				</tr> --%>
				
				<%-- <tr>
					<td class="tdrsname" align="right">班主任：</td>
					<td  class="tdrsname">
						<input class="dfinput" type="text" id="cla_master" name="cla_master" value="${cla.cla_master }" style="width: 360px;"> 
					</td>
					<td width="auto" class="tdrsname"><span id="cla_masterMsg"></span></td>
				</tr> --%>
				
				<%-- <tr>
					<td class="tdrsname" align="right">班长：</td>
					<td  class="tdrsname">
						<input class="dfinput" type="text" id="cla_head" name="cla_head" value="${cla.cla_head }" style="width: 360px;"> 
					</td>
					<td width="auto" class="tdrsname"><span id="cla_headMsg"></span></td>
				</tr>
				 --%>
				<%-- <tr>
					<td class="tdrsname" align="right">班级QQ群：</td>
					<td  class="tdrsname">
						<input class="dfinput" type="text" id="cla_QQ" name="cla_QQ" value="${cla.cla_QQ }" style="width: 360px;"> 
					</td>
					<td width="auto" class="tdrsname"><span id="cla_QQMsg"></span></td>
				</tr> --%>
				
				<tr>
					<td class="tdrsname" align="right">班级性质：</td>
					<td  class="tdrsname">
						<select class="dfinput" type="text" id="cla_nature" name="cla_nature" value="${cla.cla_nature }" style="width: 360px;">
							<option value="">--请选择--</option>
							<option value="散招">散招</option>
					   		<option value="3+1合作">3+1合作</option>
					   	 	<option value="混合班">混合班</option>
					 	</select>
					</td>
					<td width="auto" class="tdrsname"><span id="cla_natureMsg"></span></td>
				</tr>
				
				<%-- <tr>
					<td class="tdrsname" align="right">班级特情：</td>
					<td  class="tdrsname">
						<select class="dfinput" type="text" id="cla_info" name="cla_info" value="${cla.cla_info }" style="width: 360px;">
							<option value="">--请选择--</option>
							<option value="独立班">独立班</option>
					   		<option value="混合班">混合班</option>
					   	 	<option value="其他">其他</option>
					 	</select>
					</td>
					<td width="auto" class="tdrsname"><span id="cla_infoMsg"></span></td>
				</tr> --%>
				
				<%-- <tr>
					<td class="tdrsname" align="right">班级类型：</td>
					<td  class="tdrsname">
						<select class="dfinput" type="text" id="cla_kind" name="cla_kind" value="${cla.cla_kind }" style="width: 360px;">
							<option value="">--请选择--</option>
							<option value="周末班">周末班</option>
					   		<option value="实习班">实习班</option>
					   	 	<option value="其他">其他</option>
					 	</select>
					</td>
					<td width="auto" class="tdrsname"><span id="cla_kindMsg"></span></td>
				</tr> --%>
				
				<tr>
					<td class="tdrsname" align="right">班级人数：</td>
					<td  class="tdrsname">
						<input class="dfinput" type="text" id="cla_num" name="cla_num" value="${cla.cla_num }" style="width: 360px;"> 
					</td>
					<td width="auto" class="tdrsname"><span id="cla_numMsg"></span></td>
				</tr>
				
				<tr>
					<td class="tdrsname" align="right">开班时间：</td>
					<td  class="tdrsname">
						<input class="Wdate" type="text" id="cla_starttime" name="cla_starttime" value="${cla.cla_starttime }" onClick="WdatePicker({skin:'whyGreen',minDate:'1980-01-1',maxDate:'2200-12-31'})" style="height :30px;width:360px" > 
					</td>
					<td width="auto" class="tdrsname"><span id="cla_starttimeMsg"></span></td>
				</tr>
				
				<%-- <tr>
					<td class="tdrsname" align="right">预计结课时间：</td>
					<td  class="tdrsname">
						<input class="Wdate" type="text" id="cla_wantendtime" name="cla_wantendtime" value="${cla.cla_wantendtime }" onClick="WdatePicker({skin:'whyGreen',minDate:'1980-01-1',maxDate:'2200-12-31'})" style="height :30px;width:360px" > 
					</td>
					<td width="auto" class="tdrsname"><span id="cla_wantendtimeMsg"></span></td>
				</tr>
				
				<tr>
					<td class="tdrsname" align="right">实际结课时间：</td>
					<td  class="tdrsname">
						<input class="Wdate" type="text" id="cla_trueendtime" name="cla_trueendtime" value="${cla.cla_trueendtime }" onClick="WdatePicker({skin:'whyGreen',minDate:'1980-01-1',maxDate:'2200-12-31'})" style="height :30px;width:360px" > 
					</td>
					<td width="auto" class="tdrsname"><span id="cla_trueendtimeMsg"></span></td>
				</tr> --%>
				
				<%-- <tr>
					<td class="tdrsname" align="right">班级状态：</td>
					<td  class="tdrsname">
						<select class="dfinput" type="text" id="cla_state" name="cla_state" value="${cla.cla_state }" style="width: 360px;">
							<option value="">--请选择--</option>
							<option value="已开班">已开班</option>
					   		<option value="已结课">已结课</option>
					 	</select>
					</td>
					<td width="auto" class="tdrsname"><span id="cla_stateMsg"></span></td>
				</tr> --%>
				
				
				
				<tr>
					<td class="tdrsname" align="right">备注：</td>
					<td class="tdrsname"><textarea id="cla_note" name="cla_note"style="width:360px; height:100px; border:solid 1px #CDBE70;  resize:none;"></textarea></td>
					<td class="tdrsname"><span id="cla_noteMsg"></span></td>
				</tr>
				
				<tr>
					<td class="tdrsname" align="right">录入人：</td>
					<td  class="tdrsname">
						<input class="dfinput" type="text" id="cla_adder" name="cla_adder" value="${cla.cla_adder }" style="width: 360px;"> 
					</td>
					<td width="auto" class="tdrsname"><span id="cla_adderMsg"></span></td>
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
