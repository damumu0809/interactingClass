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
</head>
<body>
<div style="margin-top:20px;">

    <button type="button" data-toggle="button" class=" btn btn-default"  onclick="{location.href='Upload.jsp'}">上传文件</button>
    <button type="button" data-toggle="button" class=" btn btn-default"  onclick="javascript:makeDefaultCourseware();">设为当前播放</button>
    <button type="button" data-toggle="button" class=" btn btn-default"  onclick="{location.href='index.jsp'}">查看播放</button>

</div>

<div style="margin:50px;" id="outer" >
    <table style="width: 600px" class="table table-striped table-bordered table-hover datatable" id="record_list">
        <thead>
        <tr>
            <th class="table-checkbox"><input type="checkbox" class="group-checkable" data-set="#record_list .checkboxes" /></th>
            <th>创建人</th>
            <th>文件名称</th>
          
        </tr>
        </thead>
    </table>
</div>




</body>
<link rel="stylesheet" type="text/css" href="../css/dataTables.bootstrap.css" />
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css"/>

<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../js/jquery.uniform.min.js"></script>
<script type="text/javascript" src="../js/jquery.dataTables.min.js"></script>

<script src="../js/record_list.js" type="text/javascript"></script>

<script src="../js/record.js" type="text/javascript"></script>
<link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

</html>
