<%@page contentType="text/html;charset=UTF-8" %>

<%

    String filePath ="";
    String fileName=request.getParameter("fileName");

    filePath="../data/"+fileName;
    


%>

<body>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">


<div id="ppt_div" class="ppt_div">



    <object id="presentation" classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0" align="middle" style="">
        <param name="allowScriptAccess" value="sameDomain" />

        <param name="movie" value="<%=filePath%>" />
        <param name="quality" value="high" />
        <param name="bgcolor" value="#ffffff" />
        <param name="allowFullScreen" value="true" />
        <embed id="presentation_filename" src="<%=filePath%>"  quality="high" bgcolor="#ffffff" width="880" height="680" name="presentation" align="middle" allowScriptAccess="sameDomain" type="application/x-shockwave-flash" pluginspage="http://www.adobe.com/go/getflashplayer" allowFullScreen="true" />
    </object>

</div>

<!--dm start-->
<div class="dm">
    <!--d_screen start-->
    <div class="d_screen">
        <a href="#" class="d_del">X</a>
        <div class="d_mask"></div>
        <div class="d_show">
        </div>
    </div>
    <!--end d_screen-->
</div>
<div id="command_panel" class="command_panel">
    <%--<a href="#" id="barrage_btn">弹幕开关</a>--%>
    <%--<a href="#" id="test_btn">查看</a>--%>
        <a id="" class="btn green" href="javascript:initPage();">播放 <i class="fa fa-edit"></i> </a>
        <a id="" class="btn green" href="javascript:download();">下载 <i class="fa fa-edit"></i> </a>


 
</div>
<div id="tip_div" class="tip_div"></div>
<!--dm end-->
</body>
<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="js/powerpoint.js" type="text/javascript"></script>

<link rel="stylesheet" type="text/css" href="css/barrage.css" />
<script src="js/barrage.js" type="text/javascript"></script>

<style>
    .ppt_div{
        width: 100%;
        height: auto;
        position: absolute;
        top: 0;
        left: 0;
        bottom:0px;
    }
</style>
<!-- ============================================================================= -->
<!-- Container -->
<div class="container">
    <!-- section with buttons-->
    <section>

        <p>
            <button id="trigger-overlay" type="button">
                鼠标点击或者按键盘M查看效果
            </button>
        </p>
    </section>
    <!-- section with buttons end-->

</div>
<!-- Container end -->
<div class="overlay overlay-slidedown">
    <button type="button" class="overlay-close">
        Close
    </button>

    <nav>
        <ul>
            <li>
                <a href="#" onclick="goHome();">返回主页</a>
            </li>
            <li>
                <a href="#" onclick="goPPT();">返回PPT</a>
            </li>
            <li>
                <a href="#">查看状态</a>
            </li>
            <li>
                <a href="#" onclick="openBarrage();">打开弹幕</a>
            </li>
            <li>
                <a href="#" onclick="closeBarrage();">关闭弹幕</a>
            </li>
        </ul>
    </nav>
    <div class="your_blocks">
        <div class="your_block">
            <a href="#"><img src="../test/popup_menu/img/intro.jpg" class="block_attachment" alt=""> <span> 欢迎大家来到弹幕的世界！</span> </a>
        </div>
    </div>
</div>
<!-- js on bottom for speed-->
<script src="js/mousetrap.min.js"></script>
<script src="js/modernizr.custom.js"></script>
<script src="js/classie.js"></script>
<script src="js/cmdscriptmin.js"></script>
<script src="js/record.js" type="text/javascript"></script>
<!-- js on bottom for speed end -->
<link rel="stylesheet" type="text/css" href="css/normalize.css" />
<link rel="stylesheet" type="text/css" href="css/main.css" />
<link rel="stylesheet" type="text/css" href="css/style4.css" />
<link rel="stylesheet" type="text/css" href="css/font-awesome.css">

