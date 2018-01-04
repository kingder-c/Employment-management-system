<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>上传页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<link href="<%=basePath%>css/jquery.Jcrop.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath%>css/picLoad.css" rel="stylesheet" type="text/css" />
	
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery.Jcrop.min.js"></script>
	
	<script language="Javascript">
			// 实际裁剪时的宽、高
			var xW, yH;
			
			function init(){
				// 上传完原图后返回的缩略图的宽度和高度
				var w = $("#w").val(); 
				var h = $("#h").val();
				
				// 上传前所要求的宽度和高度
				var picW = $("#picWidth").val(); 
				var picH = $("#picHeight").val();
				
				// 如果指定的需要裁剪的初始宽高值都小于图片本身的宽高 则默认裁剪区大小为指定的初始宽高值
				if(picW < w && picH < h){
					xW = picW;
					yH = picH;
				}else {
					// 否则按照宽高比对需要裁剪的区域进行缩放
    				if((picW/picH) < (w/h)){
    					xW = (h * picW) / picH;
    					yH = h;
    				}else {
    					xW = w;
    					yH = (w * picH) / picW;
    				}
				}
				
			}
				
			jQuery(document).ready(function(){
				init();
				jQuery('#cropbox').Jcrop({
					onChange: showCoords,
					onSelect: showCoords,
					setSelect: [ 0, 0, xW, yH ], // 设置一个初选框的位置
					bgColor:     'white', // 设置背景容器颜色 
            		bgOpacity:   .6,      // 设置当图像被裁剪选框外的透明度
            		aspectRatio: xW / yH  // 设置宽高比
				});

			});

			function showCoords(c){
				jQuery('#x').val(c.x);
				jQuery('#y').val(c.y);
				jQuery('#w').val(c.w);
				jQuery('#h').val(c.h);
			}
			
            function checkImage(FileUpload){
            	var imgPath = FileUpload.value;
            	imgPath = imgPath.toLowerCase().substr(imgPath.lastIndexOf("."));
            	if(imgPath != ".jpg" && imgPath != ".jpeg" && imgPath != ".png" && imgPath != ".gif" && imgPath != ".bmp"){
            		FileUpload.value = "";
            		alert("您选择的图片格式不正确！");
            	}else {
            		$("#fileUpload").submit();
            	}
            }
            
            function saveImg(){
            	var x = $('#x').val(); 
            	var y = $('#y').val(); 
            	var w = $('#w').val(); 
            	var h = $('#h').val();
				var inputImg = $("#inputImg").val();
            	if (x == "" || y == "" || w == "" || h == "" || inputImg == "") {
            		alert("请先选择上传图片");
            	}else {
            		var data = {"arg":inputImg,"x":$('#x').val(),"y":$('#y').val(),"w":$('#w').val(),"h":$('#h').val()};
            		jQuery.get("ImgCutUploadServlet?method=uploadSmallImg",data,callbackfn,"html");
            	}
            }
            function callbackfn(datas){
            	window.opener.callbackPfn(datas);
            	window.self.close();
            }
            function cancel(){
            	window.self.close();
            }
		</script>
</head>
<body>

	<form id="fileUpload" name="fileUpload"
		action="ImgCutUploadServlet?method=uploadFile&pathParam=${pathParam }&picWidth=222&picHeight=305" 
		enctype="multipart/form-data" method="post">
		
		<div id="hidden">
			<input type="hidden" id="method" name="method" value="" /> 
			<input type="hidden" id="pathParam" name="pathParam" value="${pathParam} }" />

		</div>
		<div class="tpsc">
			<h1>请选择图像文件</h1>
			<div style="text-align: center;width: 140px;height: 39px;margin: 10px auto 10px;padding: 0px;">
				<a class="btn_addPic" href="javascript:void(0);">
					<span><em>+</em>添加图片</span>
					<input id="myFile" name="myFile" tabindex="3"
					title="支持jpg、jpeg、gif、png、bmp格式，文件小于4M" class="filePrew"
					type="file" size="3" onchange="checkImage(this)"> 
				</a>
			</div>
			<span><font color="green" size="2">支持jpg、jpeg、gif、png、bmp格式，文件小于4M</font></span><br />
			<div style="margin: 10px auto 10px;padding: 0px;">
				<font color="red" size="3"></font>
			</div>
		</div>
	</form>
	<form id="cutImg" name="fileUpload" method="post"  ENCTYPE="multipart/form-data">
			<div id="hidden2">
				<input type="hidden" size="4" id="x" name="x" value="0" />
				<input type="hidden" size="4" id="y" name="y" value="0" />
				<input type="hidden" size="4" id="w" name="w" value="500" />
				<input type="hidden" size="4" id="h" name="h" value="375" />
				<input type="hidden" id="picWidth" name="picWidth" value="225" />
				<input type="hidden" id="picHeight" name="picHeight" value="305" />
				<input type="hidden" id="inputImg" name="inputImg" value="${fileName}" />
			</div>
			<br />
			<div class="tpsc">
				<h1>第二步：请根据大小对图像进行裁剪后点击上传</h1>
			</div>
			<div style="margin:20px auto 0px auto; width:500px;">
				<img src="${cropbox }" id="cropbox"   />
			</div>
			<div class="btn">
				<input class="sc" type="button" id="saveButton" name="saveButton"
					value="上传" onclick="saveImg()" /> <input class="qx" type="button"
					id="cancelButton" name="cancelButton" value="取消" onclick="cancel()" />
			</div>
		</form>
</body>
</html>