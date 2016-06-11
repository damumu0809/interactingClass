


<!DOCTYPE html>
<html>

<meta charset="utf-8" />
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="../../assets/module/css/fonts.css" rel="stylesheet" type="text/css"/>
<link href="../../assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="../../assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
<link href="../../assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="../../assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<link href="../../assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN THEME STYLES -->
<link href="../../assets/global/css/components.css" id="style_components" rel="stylesheet" type="text/css"/>
<link href="../../assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="../../assets/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
<link href="../../assets/admin/layout/css/themes/darkblue.css" rel="stylesheet" type="text/css" id="style_color"/>
<link href="../../assets/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="favicon.ico"/>
<!-- BEGIN DATABLE STYLES -->
<link rel="stylesheet" type="text/css" href="../../assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css" />
<!-- END DATABLE STYLES -->
<!-- BEGIN UI COMPONENT STYLES -->
<link rel="stylesheet" type="text/css" href="../../assets/global/plugins/bootstrap-select/bootstrap-select.min.css" />
<!-- BEGIN UI COMPONENT PLUGINS -->

<link href="../../assets/module/css/layout.css" rel="stylesheet" type="text/css"/>
<head>
    <meta charset="utf-8" />
    <title></title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
</head>
<body class="page-header-fixed page-quick-sidebar-over-content">
<!-- BEGIN CONTAINER -->
<div class="page-container">
    <!-- ----------------------------------------页面开始，替换这里即可---------------------------------------- -->
    <!-- BEGIN CHAT ROOM -->

    <!-- BEGIN CHAT ROOM -->
    <!-- <a href="javascript:;" class="page-quick-room-toggler"><i class="icon-close"></i></a> -->
    <div class="page-quick-room-wrapper">
        <div class="page-quick-room">
            <div class="tab-pane active page-quick-room-chat" id="quick_chat_tab_1">
                <div class="page-quick-room-chat-user">
                    <div class="page-quick-room-nav">
                        <a href="javascript:;" class="page-quick-room-back-to-list"><i class="icon-arrow-left"></i>返回</a>
                        <span class="page-quick-room-user-name" id="chat_user_name"></span>
                    </div>
                    <div class="page-quick-room-chat-user-messages">
                    </div>
                    <div class="page-quick-room-chat-user-form">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="请输入您想说的...">
                            <div class="input-group-btn">
                                <button type="button" class="btn blue">
                                    发送
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- END CHAT ROOM -->
    <!-- END CHAT ROOM -->
    <!-- ----------------------------------------页面结束，替换上面即可---------------------------------------- -->
</div>
</div>
<!-- END CONTENT -->
</div>
<!-- END CONTAINER -->
</body>
<!-- END BODY -->
</html>

<!-- BEGIN EXTRA HTML -->

<iframe id="ifra_head" style="filter:alpha(opacity=0);position:absolute;width:expression(this.nextSibling.offsetWidth);height:expression(this.nextSibling.offsetHeight);top:expression(this.nextSibling.offsetTop);left:expression(this.nextSibling.offsetLeft);" frameborder="0" ></iframe>
<div class="mengban_head" id="mengban_head"></div>
<div  id="LoadProcess_head" style="position:absolute; visibility:hidden;margin:auto;width:280px; height:50px; z-index:1;z-index:11;">
    <div align=center style="color:#000000;border:1px solid #ffffff;height:100px;padding:5px 5px 5px 5px" id="id2">
        <img src="../../assets/global/img/loading.gif">
        <span id="waitTip">请稍候......</span>
        </img>
    </div>
</div>

<!-- END EXTRA HTML -->


<meta charset="utf-8" />
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="../../assets/global/plugins/respond.min.js"></script>
<script src="../../assets/global/plugins/excanvas.min.js"></script>
<![endif]-->
<script src="../../assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="../../assets/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="../../assets/global/plugins/jquery-ui/jquery-ui.min.js" type="text/javascript"></script>
<script src="../../assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="../../assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="../../assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="../../assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="../../assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="../../assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="../../assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="../../assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="../../assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="../../assets/admin/layout/scripts/quick-sidebar.js" type="text/javascript"></script>
<script src="../../assets/admin/layout/scripts/demo.js" type="text/javascript"></script>
<script src="../../assets/admin/pages/scripts/index.js" type="text/javascript"></script>
<script src="../../assets/admin/pages/scripts/tasks.js" type="text/javascript"></script>
<script src="../../assets/admin/pages/scripts/components-dropdowns.js"></script>
<script src="../../assets/module/scripts/home/mainmenu.js" type="text/javascript"></script>
<script src="../../assets/module/scripts/wechat/tools.js" type="text/javascript"></script>
<script src="../../assets/module/scripts/public/wait_tip.js" type="text/javascript"></script>
<script src="../../assets/module/scripts/public/jweixin-1.0.0.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<!-- BEGIN DATATABLE SCRIPTS -->
<script type="text/javascript" src="../../assets/global/plugins/datatables/media/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="../../assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"></script>
<!-- BEGIN DATATABLE SCRIPTS -->
<!-- BEGIN UI COMPONENT PLUGINS -->
<script type="text/javascript" src="../../assets/global/plugins/bootstrap-select/bootstrap-select.min.js"></script>
<script type="text/javascript" src="../../assets/global/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="../../assets/global/plugins/jquery-multi-select/js/jquery.multi-select.js"></script>
<!-- END UI COMPONENT PLUGINS -->
<!-- END JAVASCRIPTS -->

<link href="../../assets/module/css/teach/chatroom-layout.css" rel="stylesheet" type="text/css"/>
<link href="../../assets/module/css/teach/chatroom-darkblue.css" rel="stylesheet" type="text/css"/>
<script src="../../assets/module/scripts/teach/classroom/chatroom.js" type="text/javascript"></script>

