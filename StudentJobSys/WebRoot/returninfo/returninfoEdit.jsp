<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>就业回访页面</title>
    <meta content="text/html" charset="utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css">
	<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
	<script src="<%=basePath%>My97DatePicker/WdatePicker.js" type="text/javascript"></script><!-- 时间插件的添加 -->
	<script type="text/javascript">
	function check(){
			var stu_id = trim($("#stu_id").val());
			if(stu_id != ""){
				$("#stu_idMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
			
			}else{
				$("#stu_idMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>分类名称不能为空！</font>");
				$("#stu_id").focus();
				return false;
			}
			<%-- var cla_id = trim($("#cla_id").val());
			if(cla_id != ""){
				$("#cla_idMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
			
			}else{
				$("#cla_idMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>分类名称不能为空！</font>");
				$("#cla_").focus();
				return false;
			} --%>
				 
		  	var return_company = trim($("#return_company").val());
			if(return_company != ""){
					$("#return_companyMsg").html("&nbsp;<img src='<%=basePath%>images/right.gif' align='absmiddle'/>");
					
			}else{
					$("#return_companyMsg").html("&nbsp;<img src='<%=basePath%>images/error.gif' align='absmiddle'/>&nbsp;<font color='red'>分类名称不能为空！</font>");
					$("#return_company").focus();
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
		
		function selectClass(a){
			
	   		var url="ReturnInfoServlet?method=selectStu&cla_id="+a;
	   		
	  		 $.get(url, null, function(data){
	      			 var objs=eval("("+data+")"); //解析json对象
	       			 var stu_id=$("#stu_id");
	      			 stu_id.empty(); //初始化否则会追加 
	      			 stu_id.append("<option value='0'>请选择...</option>");
	      			 for(var i=0;i<objs.length;i++){
	      				stu_id.append("<option value='"+objs[i].id+"'>"+objs[i].name+"</option>");
	       			 }
	  		      });
	   } 
	</script>

  </head>
  
  <body>
    <!-- 顶部路径栏 -->
	<div class="place"><span>位置：</span>
    	<ul class="placeul">
        	<li><a href="javascript:void(0)">首页</a></li>
        	
					<li><a href="javascript:void(0)">新增回访信息</a></li> 
				
    	</ul>
	</div>
	<div align="left" style="margin-top: 5px;">
		<form action="ReturnInfoServlet?method=save" id="edit" name="edit" method="post">
			<table border="0">
				<tr>
					<td colspan="1" class="formtitle">&nbsp;学员回访信息编辑 </td>
				</tr>
				<tr>	
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<td><span style="font-family:'宋体';font-size:12px;font-weight: normal;">（<font color="red">带&nbsp;<font color="red">*</font>&nbsp;的为必填项</font>）</span></td>
				</tr>
				<tr>
					<td width="100%" >
				    <table>
						<tr>		
								<td>
									<input type="hidden" id="return_id" name="return_id" value="${riv.return_id }">
								</td>
						</tr>
						<tr>
								<td class="tdrsname" align="right">学员班级：</td>
								<td width="100" class="tdrsname">
								<!-- 设计两个下拉列表，第一个用来选择班级，当选定了班级后，第二个下拉列表用来显示该班级的所有学员 -->
			 						<select class="dfinput" id="cla_id" name="cla_id"  style="width: 346px;"  onchange="selectClass(this.options[this.options.selectedIndex].value)"> 		
									 		 <option value = "">--请选择--</option>
										     <c:forEach items="${select_cla_name }" var="cla">
												<option value="${cla.cla_id }" >-${cla.cla_name }</option>
											 </c:forEach>	
				 					</select>
			 								
			 					</td>
		 						<td> <span class="redXing">*</span></td>
								<td width="auto" class="tdrsname"><span id="cla_idMsg"></span></td>
					</tr>
					<tr>	
				    	<td class="tdrsname" align="right">学员姓名：</td>
				  		<td width="300" class="tdrsname">
								<select class="dfinput" id="stu_id" name="stu_id"  style="width: 346px;"> 		
								
									<option value="0">--请选择--</option>
										
								</select>
						</td>
		 					       
 						<td> <span class="redXing">*</span></td>
		 				<td width="auto" class="tdrsname"><span id="stu_idMsg"></span></td>
				     </tr>
				     <tr>
					 	<td class="tdrsname" align="right">当前城市：</td>
						<td class="tdrsname">
							<input class="dfinput" type="text" id="return_city" name="return_city" value="">
						</td>
						<td class="tdrsname"><span id="return_cityMsg"></span></td>
					 </tr>
 				     <tr>
						<td class="tdrsname" align="right">当前企业：</td>
						<td class="tdrsname">
							<input class="dfinput" type="text" id="return_company" name="return_company" value="">
						</td>
 						<td> <span class="redXing">*</span></td>
						<td class="tdrsname"><span id="return_companyMsg"></span></td>
					</tr>
					<tr>
						<td class="tdrsname" align="right">当前职务：</td>
						<td class="tdrsname">
							<input class="dfinput" type="text" id="return_post" name="return_post" value="">
						</td>
						<td class="tdrsname"><span id="return_postMsg"></span></td>
				    </tr>
					<tr>
						<td class="tdrsname" align="right">当前岗位：</td>
						<td class="tdrsname">
							<input class="dfinput" type="text" id="return_satation" name="return_satation" value="">
						</td>
						<td class="tdrsname"><span id="return_satationMsg"></span></td>
					</tr>
					<tr>
						<td class="tdrsname" align="right">当前待遇：</td>
						<td class="tdrsname">
							<input class="dfinput" type="text" id="return_salary" name="return_salary" value="">
						</td>
						<td class="tdrsname"><span id="return_salaryMsg"></span></td>
					</tr>
					<tr>
						<td class="tdrsname" align="right">满意程度：</td>
					    <td width="300" class="tdrsname">
							<select class="dfinput" id="return_satisfy" name="return_satisfy"  style="width: 346px;"> 
										<option value="">----请选择----</option>
										<option value="非常满意">非常满意</option>
										<option value="满意" >满意</option>
										<option value="一般" >一般</option>
										<option value="不满意" >不满意</option>
										<option value="非常不满意" >非常不满意</option>
							</select>
						</td>
						<td class="tdrsname"><span id="return_satisfyMsg"></span></td>
					</tr>
					<tr>
						<td class="tdrsname" align="right">当前手机号：</td>
						<td class="tdrsname">
							<input class="dfinput" type="text" id="return_phone" name="return_phone" value="">
						</td>
						<td class="tdrsname"><span id="return_phoneMsg"></span></td>
					</tr>
					<tr>
						<td class="tdrsname" align="right">未来城市：</td>
						<td class="tdrsname">
							<input class="dfinput" type="text" id="return_futurecity" name="return_futurecity" value=""> 
						</td>
							<td class="tdrsname"><span id="return_futurecityMsg"></span></td>
					</tr>
					<tr>
						<td class="tdrsname" align="right">回访人：</td>
						<td class="tdrsname">
							<input class="dfinput" type="text" id="return_adder" name="return_adder" value="">
						</td>
						<td class="tdrsname"><span id="return_adderMsg"></span></td>
					</tr>
					<tr>
						<td class="tdrsname" align="right">回访内容：</td>
						<td class="tdrsname"><textarea id="return_content" name="return_content" style="width:346px; height:100px; border:solid 1px #CDBE70;  resize:none;"></textarea></td>
						<td class="tdrsname"><span id="return_contentMsg"></span></td>
					</tr>
					<tr>
						<td class="tdrsname" align="right">备注：</td>
						<td class="tdrsname"><textarea id="return_note" name="return_note"style="width:346px; height:100px; border:solid 1px #CDBE70;  resize:none;"></textarea></td>
						<td class="tdrsname"><span id="return_noteMsg"></span></td>
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
		   </td>
		  </tr>
		 </table>
	  </form>
	</div>
  </body>
</html>
