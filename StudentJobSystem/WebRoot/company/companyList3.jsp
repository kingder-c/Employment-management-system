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
    
    <title>企业信息列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/bootstrap.min.css">
		<style type="text/css">
		body, html {width: 100%;height: 100%;margin:0;font-family:"微软雅黑";font-size:14px;}
		#r-result,#r-result table{width:100%; clear:both;}
	</style>
	<script src="<%=basePath%>js/jquery.js" type="text/javascript"></script>
	<script src="<%=basePath%>js/bootstrap.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=3.0&ak=mQMD9GPZVsfTT3LjpNvZlaXnDytFDXMq"></script>
	<script type="text/javascript">
		function doSubmit(currentPage) {
			$("#pageIndex").val(currentPage);
			$("#search").submit();
		}
		function goPage() {
			var goPage = $("#goPage").val();
			var totalPage = $("#totalPage").val();
			if(totalPage == 0){
				$("#pageIndex").val(1);
			}else {
				if (goPage > totalPage) {
					$("#pageIndex").val(totalPage);
				} else {
					$("#pageIndex").val(goPage);
				}
			}
			$("#search").submit();
		}
		
		function doInsert(){
			window.location.href = "<%=basePath%>CompanyServlet?method=edit";
		}
		function doDelete(){
			var radioes = $("input[name='com_id']:checked").val();
			if (radioes == null || radioes == undefined){
				alert("请选中要删除的纪录！");return;
			}else if (confirm("确定要删除选定的记录吗？\n")){
				window.location.href = "<%=basePath%>CompanyServlet?method=dele&com_id="+radioes;
			}
		}
		function doUpdate(){
			var radioes = $("input[name='com_id']:checked").val();
			if (radioes == null || radioes == undefined){
				alert("请选中要修改的纪录！");return;
			}else{
				window.location.href = "<%=basePath%>CompanyServlet?method=edit&com_id="+radioes;
			}
		}
		function test(){
			window.location.href="company/companyView.jsp?company=companyBean";
		}
		function doAdd(){
			var radioes = $("input[name='com_id']:checked").val();
			if (radioes == null || radioes == undefined){
				alert("请先选择企业！");return;
			}else if (confirm("确定要选择选定的企业吗？\n")){
				// 将需要的值（学员Id）传回父窗口，关闭子窗口
				var com_id = radioes;
				$.get("CompanyServlet?method=getCompanyByAjax&com_id="+com_id, "",callback,cancel);
			}
		}
		function callback(datas){
			window.opener.getCompanyInfo(datas);
            window.self.close();
		}
		function cancel(){
            window.self.close();
        }
		
	</script>

  </head>
  
  <body>
     <!-- 顶部路径栏 -->
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="javascript:void(0)">首页</a></li>
			<li><a href="javascript:void(0);">企业信息管理</a></li>
		</ul>
	</div>
	<div class="rightinfo">
		<form action="<%=basePath%>CompanyServlet?method=selectCompany" method="post" id="search" name="search">
			<!-- 隐藏域 -->
			<div>
				<input type="hidden" name="pageIndex" id="pageIndex" value="${currentPage }">
				<input type="hidden" name=totalPage id="totalPage" value="${totalPage }">
			</div>

			<ul class="seachform">
				<li><label>根据企业名字查询</label>
					<input class="scinput" name="com_name" type="text" value="${com_name}" /></li>
				<li><label>根据企业城市查询</label>
					<input class="scinput" name="com_city" type="text" value="${com_city}" /></li>
				<li><label>根据企业状态查询</label>
					   <select class="scinput" name="com_status" type="text">  
					      <option value="">请选择</option>  
					      <option value="0">招聘中</option>  
					      <option value="1">不招聘</option>  
					    </select> </li>
					
				<li><input type="button" class="scbtn" value="查询" onClick="doSubmit(1)"/> </li>
			</ul>
			
			<!-- <ul style="text-align: right;" class="toolbar" >
				<li>
					<a href="javascript:void(0);" onclick="doUpdate()">
					<img alt="开户" src="images/t01.png"><span style="margin-top: -2px;">开户</span></a>
				</li>	
				<li>
					<a href="javascript:void(0);" onclick="doInsert()">
					<img alt="添加" src="images/t01.png"><span style="margin-top: -2px;">添加</span></a>
				</li>	 
				<li>	
					<a href="javascript:void(0);" onclick="doUpdate()">
					<img alt="修改" src="images/t02.png"><span style="margin-top: -2px;">修改</span></a>
				</li>	 
				<li>	
					<a href="javascript:void(0);" onclick="doDelete()">
					<img alt="删除" src="images/t03.png"><span style="margin-top: -2px;">删除</span></a>
				</li>

			</ul> -->
			
		</form>
		<table class="tablelist"
			style="table-layout:fixed;word-wrap:break-word;word-break:break-all;">
			<thead>
				<tr>
					<th width="8%">选择</th>
					<th width="15%">名字</th>
					<th width="15%">状态</th>
					<th width="15%">城市</th>
					<th width="auto">审核状态</th>
					<th width="25%">路线规划</th>
				</tr>
			</thead>
			<!-- EL表达式中 empty为关键字 用来判断对象是否为空 -->
			<c:if test="${empty list}">
				<tr class="odd">
					<td colspan="5" align="center">没有查询到相关记录！</td>
				</tr>
			</c:if>
			<c:forEach items="${list }" var="companyBean">
				<tr name="trname" class="odd">
					<td><input type="radio" name="com_id" value="${companyBean.com_id }"></td>
					<td>
						<a href="CompanyServlet?method=view&com_id=${companyBean.com_id } " >
							${companyBean.com_name }
						</a>
					</td>
					<%-- <td><a herf="javascript:void(0)" onClick="test()">${companyBean.com_name }</a></td>--%>
					<c:forEach items="${select_com_status }" var="data">
					<c:choose>
								<c:when test="${companyBean.com_status==data.data_num }">
								<td>${data.data_name }</td>
								</c:when>
					</c:choose>
					</c:forEach>
					<td>${companyBean.com_city }</td>
					<td>${companyBean.com_checkstatus }</td>
					<td><button type="button" id="myButton1"  class="btn" style="background-color: #ff9900bf" autocomplete="off" onclick="CarToObj(116.523091,39.9551)"> 驾车</button>&nbsp<button type="button" id="myButton1"  class="btn" style="background-color: #ff9900bf" autocomplete="off" onclick="BusToObj(116.523091,39.9551)"> 公交</button></td>
				</tr>
			</c:forEach>
		</table>

		<!-- 分页功能 开始 -->
		<div class="pagin">
			<div>
				共&nbsp;<i class="blue">${totalNum }</i>&nbsp;条记录，当前显示第&nbsp;<i
					class="blue">${currentPage }&nbsp;</i>页&nbsp;/&nbsp;共&nbsp;<i
					class="blue">${totalPage }&nbsp;</i>页&nbsp;&nbsp;
				跳到&nbsp;第&nbsp;<input class="goPage" type="text" id="goPage"
					name="goPage" value="${currentPage }">&nbsp;页&nbsp;&nbsp; <input
					type="button" value="跳转" onclick="goPage()">
			</div>
			<div class="paginList">
				<c:if test="${currentPage == 1 }">
					<a href="javascript:void(0);">首页</a>&nbsp;
					<a href="javascript:void(0);">上一页</a>&nbsp;
				</c:if>
				<c:if test="${currentPage > 1 }">
					<a class="blue" href="javascript:void(0);" onClick="doSubmit(1)">首页</a>&nbsp;
					<a class="blue" href="javascript:void(0);"
						onClick="doSubmit(${currentPage-1})">上一页</a>&nbsp;
				</c:if>
				<c:if test="${currentPage >= totalPage }">
					<a href="javascript:void(0);">下一页</a>&nbsp;
					<a href="javascript:void(0);">尾页</a>&nbsp;
				</c:if>
				<c:if test="${currentPage < totalPage }">
					<a class="blue" href="javascript:void(0);"
						onClick="doSubmit(${currentPage+1 })">下一页</a>&nbsp;
					<a class="blue" href="javascript:void(0);"
						onClick="doSubmit(${totalPage})">尾页</a>&nbsp;
				</c:if>
			</div>
		</div>
		
		<table>
				<tr style="line-height:50px; ">
					<!-- 按钮换成背景图片 -->
					<td>&nbsp;</td>
					<td colspan="1" align="center"><input class="button"
						type="button" name="确认" value="确认" onClick="doAdd()" />
					&nbsp;&nbsp;&nbsp;&nbsp;<input class="button"
						type="button" name="返回" value="返回" onClick="cancel()"/>
					</td><td colspan="3">&nbsp;</td>
				</tr>
		</table>
		<div>
		  <div style="width:60%;height:450px;border:#ccc solid 1px;" id="dituContent"></div>

		  <div id="r-result" style="display:none;width:40%;float:right;margin-top:-450px"></div>
		</div>
		<script>
	    //创建和初始化地图函数：
	    function initMap(){
	        createMap();//创建地图
	        setMapEvent();//设置地图事件
	        addMapControl();//向地图添加控件
	        //addMarker();//向地图中添加marker
	    }
	    
	    //创建地图函数：
	    function createMap(){
	        var map = new BMap.Map("dituContent");//在百度地图容器中创建一个地图
	        var point = new BMap.Point(116.34935,39.957945);//定义一个中心点坐标
	        map.centerAndZoom(point,16);//设定地图的中心点和坐标并将地图显示在地图容器中
	        window.map = map;//将map变量存储在全局
	    }
	    
	    //地图事件设置函数：
	    function setMapEvent(){
	        map.enableDragging();//启用地图拖拽事件，默认启用(可不写)
	        map.enableScrollWheelZoom();//启用地图滚轮放大缩小
	        map.enableDoubleClickZoom();//启用鼠标双击放大，默认启用(可不写)
	        map.enableKeyboard();//启用键盘上下左右键移动地图
	    }
	    function CarToObj(x,y){
	    	//alert(x+y);
	    	document.getElementsByTagName('BODY')[0].scrollTop=document.getElementsByTagName('BODY')[0].scrollHeight;
	        var geolocation = new BMap.Geolocation();
	    	geolocation.getCurrentPosition(function(r){
	    		if(this.getStatus() == BMAP_STATUS_SUCCESS){
	    			var mk = new BMap.Marker(r.point);
	    			map.addOverlay(mk);
	    			map.panTo(r.point);

	    			var p1 = new BMap.Point(r.point.lng,r.point.lat);
	    			var p2 = new BMap.Point(x,y);

	    			var driving = new BMap.DrivingRoute(map, {renderOptions:{map: map, autoViewport: true}});
	    			driving.search(p1, p2);
	    		}
	    		else {
	    			alert('failed'+this.getStatus());
	    		}        
	    	},{enableHighAccuracy: true});
	    }
        function BusToObj(x,y){
        	//alert(x+y);
        	document.getElementsByTagName('BODY')[0].scrollTop=document.getElementsByTagName('BODY')[0].scrollHeight;
	    	//alert(x+y);
	    	document.getElementsByTagName('BODY')[0].scrollTop=document.getElementsByTagName('BODY')[0].scrollHeight;
	        var geolocation = new BMap.Geolocation();
	    	geolocation.getCurrentPosition(function(r){
	    		if(this.getStatus() == BMAP_STATUS_SUCCESS){
	    			var mk = new BMap.Marker(r.point);
	    			map.addOverlay(mk);
	    			map.panTo(r.point);

	    			var p1 = new BMap.Point(r.point.lng,r.point.lat);
	    			var p2 = new BMap.Point(x,y);

	    			var transit = new BMap.TransitRoute(map, {
	            		renderOptions: {map: map, panel: "r-result"}, 
	            		onResultsHtmlSet : function(){$("#r-result").show()}  	
	                });
	                transit.search(p1,p2);
	    		}
	    		else {
	    			alert('failed'+this.getStatus());
	    		}        
	    	},{enableHighAccuracy: true});

            
	    }
	    
	    //地图控件添加函数：
	    function addMapControl(){
	        //向地图中添加缩放控件
		var ctrl_nav = new BMap.NavigationControl({anchor:BMAP_ANCHOR_TOP_LEFT,type:BMAP_NAVIGATION_CONTROL_ZOOM});
		map.addControl(ctrl_nav);
	        //向地图中添加缩略图控件
		var ctrl_ove = new BMap.OverviewMapControl({anchor:BMAP_ANCHOR_BOTTOM_RIGHT,isOpen:1});
		map.addControl(ctrl_ove);
	        //向地图中添加比例尺控件
		var ctrl_sca = new BMap.ScaleControl({anchor:BMAP_ANCHOR_BOTTOM_LEFT});
		map.addControl(ctrl_sca);
	    }    //开启鼠标滚轮缩放
	    initMap();
		</script>
	</div>
  </body>
</html>
