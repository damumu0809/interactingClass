<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">

	<title>Upload</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="author" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="../css/common.css" />
	<script type="text/javascript" src="../js/jquery-1.11.1.js"></script>
	<script type="text/javascript">
		$(function(){
			$(".thumbs a").click(function(){
				var largePath  = $(this).attr("href");
				var largeAlt = $(this).attr("title");
				$("#largeImg").attr({
					src : largePath,
					alt : largeAlt
				});
				return false;
			});

		});
	</script>
	<style type="text/css">

		div{

			margin:0 auto;
		}
		body{ text-align:center}
		.file-box{ position:relative;width:340px}
		.txt{ height:22px; border:1px solid #cdcdcd; width:180px;}
		.btn{ background-color:#FFF; border:1px solid #CDCDCD;height:24px; width:70px;}
		.file{ position:absolute; top:0; right:80px; height:24px; filter:alpha(opacity:0);opacity: 0;width:260px }
	</style>
</head>

<body>

<h1>点击浏览上传文件</h1>
<br>
<br>

<div class="file-box" >
	<form action="smartUploadServlet.do" method="post" enctype="multipart/form-data">
		<input type='text' name='textfield' id='textfield' class='txt' />
		<input type='button' class='btn' value='浏览...' />
		<input type="file" name="fileField" class="file" id="fileField" size="28" onchange="document.getElementById('textfield').value=this.value" />
		<input type="submit" name="submit" class="btn" value="上传" />

	</form>
</div>
<br><br><hr>

<button type="button" data-toggle="button" class=" btn btn-default"  onclick="{location.href='jsp/Upload.jsp'}">查看文件</button>
<button type="button" data-toggle="button" class=" btn btn-default"  onclick="{location.href='jsp/index.jsp'}">查看播放</button>

<br><br><br>
${result}




</body>

</html>

