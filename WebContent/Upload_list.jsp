<%--
  Created by IntelliJ IDEA.
  User: Yaya
  Date: 2016/5/23
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件管理</title>
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
    <style>
        #outer {
            width: 650px;
            height: auto;

            border: 1px solid silver;
            position: relative;
        }
     </style>
<style media=print type="text/css"> 
.noprint{visibility:hidden} 
</style> 
   
</head>
<body>
<div class="noprint" style="margin-top:20px;">

    <button type="button" data-toggle="button" class=" btn btn-default"  onclick="{location.href='Upload.jsp'}">上传文件</button>
    <button type="button" data-toggle="button" class=" btn btn-default"   onclick="javascript:deleteChecked();">删除文件</button>
    <button type="button" data-toggle="button" class=" btn btn-default"  onclick="javascript:makeDefaultCourseware();">设为当前播放</button>
    <button type="button" data-toggle="button" class=" btn btn-default"  onclick="{location.href='index.jsp'}">查看播放</button>
    <button type="button" data-toggle="button" class=" btn btn-default"  onclick="javascript:window.print()">打印列表</button>
	<button type="button" data-toggle="button" class=" btn btn-default" id="btnChange" >添加/修改备注</button>
	<button type="button" data-toggle="button" class=" btn btn-default" id="btnQuery" >查询选项</button>
</div>
<div id="query_Remarks" style="display: none;" >
	
   	<form name="input" action="record.jsp?action=query" method="get">
   	<p>按周数查询：</p><hr>
    <p>请输入上课周数:</p>
    
         <input type="text" class="mytxt" id="query_remarks" value=""/>
	 <input type="button" id="query" class="hwq2button white" value="查询" onclick="queryRemarks();"/>
       
    
	</form>

</div>
<%
	String week=request.getParameter("query_remarks");
	session.setAttribute("week", week);
	System.out.println("session"+week);
 %>
<div id="changeList" style="display: none;" >
    <p>请输入上课周数:</p>
    <input type="text" class="mytxt" id="file_name" value=""/>
    <input type="button"  class="hwq2button white" value="添加/修改" onclick="changeRecord();"/>

</div>
<div style="margin:50px;" id="outer" >
    <table style="width: 600px" class="table table-striped table-bordered table-hover datatable" id="record_list">
        <thead>
        <tr>
            <th class="table-checkbox"><input type="checkbox" class="group-checkable" data-set="#record_list .checkboxes" /></th>
              <th>创建人</th>
            <th>文件名称</th>
             <th>上课周数</th>
            <%--<th>文件路径</th>--%>
            <th>创建时间</th>
          
        </tr>
        </thead>
    </table>
</div>

<input id="but1" type="button" value="全选" />


</body>
<link rel="stylesheet" type="text/css" href="css/dataTables.bootstrap.css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>

<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/jquery.uniform.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>

<script src="js/record_list.js" type="text/javascript"></script>

<script src="js/record.js" type="text/javascript"></script>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<script>
    $("#btnChange").click(function(){
        $("#changeList").toggle(1000);
    });
       $("#btnQuery").click(function(){
        $("#query_Remarks").toggle(1000);
    });
    
    $("#but1").click(
    	function(){
    	alert(0);
    	});
</script>

</html>
