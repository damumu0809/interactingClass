<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/index.css">
<%@page contentType="text/html; charset=UTF-8" language="java"
    import="java.text.*,org.json.JSONObject.*,java.util.ArrayList,java.io.PrintWriter"
    import="java.util.*,java.sql.*,java.io.IOException"
    import="database.*,java.sql.ResultSet"%>
<%@ include file="frame_css.jsp"%>
<title>移动互动课堂</title>
</head>
<%
    //session = request.getSession();
    if(session.getAttribute("username")==null){
        out.println("<script>alert('请先登录！');window.location.href='login.jsp'</script>");
    }
    
%>
<body onload="menuInit();">
	<!-- Header Starts Here -->
<div class="header" id="home">
	<div class="container">
		<!-- logo部分 -->
		<span class="menu"></span>
		<div class="cleare"></div>
		<div class="navigation">
			<ul id="menu_ul" class="navig">
			<!-- 导航部分 -->
				<li><a href="#"  class="active">首页</a></li>
				<li><a href="#" class="scroll">互动课堂</a></li>
			</ul>			
		</div>
		<div class="clearfix"></div>			
	</div>
</div>
<!-- Header Ends Here -->
<!-- Banner Starts Here -->
<div class="banner">
<!-- 在css的banner类中注释了背景图片 -->
		<!-- BEGIN CONTAINER -->
	<div class="container">
	<div class="row">
		<div class="col-md-9" > <p>空白页面</p>
		</div>
	</div>

<!-- END CONTAINER -->
		<div class="aarow">
			<a href="#work" class="scroll"><img src="images/arw.png" alt=""></a>
		</div>
	</div>
</div>
<!-- Banner Ends Here -->

<!-- Work Starts Here -->
<%
                    String identity =null;
           if(session.getAttribute("identity")!=null){
        	   identity= session.getAttribute("identity").toString();
        	   System.out.println("===================");
        	   System.out.println(identity);
               if(identity.equals("学生")){
            	   out.println("<script>var flag=1;function page(){if(flag==1){studentPage();} flag=0;}</script>");
               }
               if(identity.equals("教师")){
            	   out.println("<script>var flag=1;function page(){if(flag==1){teacherPage();} flag=0;}</script>");
               }
           }
%>
<div class="services">
	<div class="container" id="work">
        <div class="row">
            <div class="col-md-8 col-md-offset-2">
                <ul class="nav nav-tabs" role="tablist">
                    <li role="presentation">
                        <a href="#vote" aria-controls="vote" role="tab" data-toggle="tab" >
                        <span class="glyphicon glyphicon-stats" aria-hidden="false"></span>
                        投票
                        </a>
                    </li>
                    <li role="presentation" class="active" >
                        <a href="#signin" aria-controls="signin" role="tab" data-toggle="tab">
                            <span class="glyphicon glyphicon-sunglasses" aria-hidden="false"></span>
                            签到
                        </a>
                    </li>
                    <li role="presentation" >
                        <a href="#homework" aria-controls="homework" role="tab" data-toggle="tab" onclick="page();">
                            <span class="glyphicon glyphicon-edit" aria-hidden="false"></span>
                            作业
                        </a>
                    </li>
                </ul>

       
    


    <div class="tab-content">
                    <!-- 投票模块-->
                    <div role="tabpanel" class="tab-pane" id="vote">
                        <!-- 投票内容展示-->
                        <!-- 发布投票-->
                        <button type="button" class="btn btn-group-lg  buttonPosition" data-toggle="modal" data-target="#myModal">
                            发布投票
                        </button>
                        <!-- 发布投票模态框 -->
                        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <!-- 模态框header-->
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                        <h4 class="modal-title" id="myModalLabelVote">发布投票</h4>
                                    </div>

                                    <form class="form-horizontal"  action="/interactingClass/IssueVote" method="post">
                                        <!-- 模态框body-->
                                        <div class="modal-body">
                                            <div class="form-group">
                                                <div class="col-sm-offset-1 col-sm-9">
                                                    <textarea class="form-control" rows="3" placeholder="请输入投票主题" name="theme"></textarea>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-offset-1 col-sm-10" >
                                                    <label>
                                                        <input type="radio" name="multipleChoice"  id="optionsRadios1" value="0">
                                                        单选
                                                    </label>
                                                    <label class="col-sm-offset-2">
                                                        <input type="radio" name="multipleChoice" id="optionsRadios2" value="1">
                                                        多选
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label  class="col-sm-3 control-label">有效时间</label>
                                                <!-- 有效时间精确到十分钟-->
                                                <div class="col-sm-3">
                                                    <select class="form-control" name="year">
                                                        <option value="2016">2016年</option>
                                                        <option value="2017">2017年</option>
                                                        <option value="2018">2018年</option>
                                                        <option value="2019">2019年</option>
                                                        <option value="2020">2020年</option>
                                                    </select>
                                                </div>
                                                <div class="col-sm-2">
                                                    <select class="form-control" name="month">
                                                        <option value="1">1月</option>
                                                        <option value="2">2月</option>
                                                        <option value="3">3月</option>
                                                        <option value="4">4月</option>
                                                        <option value="5">5月</option>
                                                        <option value="6">6月</option>
                                                        <option value="7">7月</option>
                                                        <option value="8">8月</option>
                                                        <option value="9">9月</option>
                                                        <option value="10">10月</option>
                                                        <option value="11">11月</option>
                                                        <option value="12">12月</option>
                                                    </select>
                                                </div>
                                                <div class="col-sm-2">
                                                    <select class="form-control" name="day">
                                                        <option value="1">1日</option>
                                                        <option value="2">2日</option>
                                                        <option value="3">3日</option>
                                                        <option value="4">4日</option>
                                                        <option value="5">5日</option>
                                                        <option value="6">6日</option>
                                                        <option value="7">7日</option>
                                                        <option value="8">8日</option>
                                                        <option value="9">9日</option>
                                                        <option value="10">10日</option>
                                                        <option value="11">11日</option>
                                                        <option value="12">12日</option>
                                                        <option value="13">13日</option>
                                                        <option value="14">14日</option>
                                                        <option value="15">15日</option>
                                                        <option value="16">16日</option>
                                                        <option value="17">17日</option>
                                                        <option value="18">18日</option>
                                                        <option value="19">19日</option>
                                                        <option value="20">20日</option>
                                                        <option value="21">21日</option>
                                                        <option value="22">22日</option>
                                                        <option value="23">23日</option>
                                                        <option value="24">24日</option>
                                                        <option value="25">25日</option>
                                                        <option value="26">26日</option>
                                                        <option value="27">27日</option>
                                                        <option value="28">28日</option>
                                                        <option value="29">29日</option>
                                                        <option value="30">30日</option>
                                                        <option value="31">31日</option>
                                                    </select>
                                                </div>
                                                </br>
                                                <div class="col-sm-3"></div>
                                                <div class="col-sm-3">
                                                    <select class="form-control" name="hour">
                                                        <option value="0">00</option>
                                                        <option value="1">01</option>
                                                        <option value="2">02</option>
                                                        <option value="3">03</option>
                                                        <option value="4">04</option>
                                                        <option value="5">05</option>
                                                        <option value="6">06</option>
                                                        <option value="7">07</option>
                                                        <option value="8">08</option>
                                                        <option value="9">09</option>
                                                        <option value="10">10</option>
                                                        <option value="11">11</option>
                                                        <option value="12">12</option>
                                                        <option value="13">13</option>
                                                        <option value="14">14</option>
                                                        <option value="15">15</option>
                                                        <option value="16">16</option>
                                                        <option value="17">17</option>
                                                        <option value="18">18</option>
                                                        <option value="19">19</option>
                                                        <option value="20">20</option>
                                                        <option value="21">21</option>
                                                        <option value="22">22</option>
                                                        <option value="23">23</option>
                                                    </select>
                                                </div>
                                                <div class="col-sm-2">
                                                    <select class="form-control" name="minute">
                                                        <option value="0">00</option>
                                                        <option value="10">10</option>
                                                        <option value="20">20</option>
                                                        <option value="30">30</option>
                                                        <option value="40">40</option>
                                                        <option value="50">50</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div id="voteForm">
                                            <div class="form-group">
                                                <div class="col-sm-offset-1 col-sm-7">
                                                    <input type="choice" class="form-control" id="choice1" placeholder="选项1" name="option1">
                                                </div>
                                                <button id="addOption">添加选项</button>
                                            </div>
                                            <div class="form-group">
                                                <div class="col-sm-offset-1 col-sm-7">
                                                    <input type="choice" class="form-control" id="choice2" placeholder="选项2" name="option2">
                                                </div>
                                            </div>
                                        	</div>
                                        </div>
                                        <!-- 模态框footer-->
                                        <div class="modal-footer">
                                            <button class="btn btn-primary btn-sm " type="submit" data-toggle="modal" data-target="#myModal" id="issue">发布</button>
                                        </div>
                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>



                    <!-- 签到模块-->
                    <div role="tabpanel" class="tab-pane active" id="signin">
               
                    </div>
                    

		            <!-- 作业模块 -->
            <div role="tabpanel" class="tab-pane" id="homework" >        
           
        	        
                    </div>
                </div>
            </div>
        </div>
</div>
</div>
<div class="footer">

	<div class="container">
		<p class="copyright">Copyright &copy; 2014.www.html5code.net All rights reserved.</p>
		<ul class="social-list">
			<li><i class="sa"></i></li>
			<li><i class="sb"></i></li>
			<li><i class="sc"></i></li>
			<li><i class="sd"></i></li>
			<li><i class="se"></i></li>
			<li><i class="sf"></i></li>
			<li><i class="sg"></i></li>
			<li><i class="sh"></i></li>
			<li><i class="si"></i></li>
		</ul>
				<a href="#home" class="scroll" id="toTop" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>

	</div>
</div>
<%@ include file="frame_js.jsp"%>
<!-- END PAGE LEVEL SCRIPTS -->
<script src="js/frame.js"></script>
<script src="js/jquery-1.11.0.min.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/loadVotePage.js"></script>
<script src="js/loadWorkPage.js"></script>
 <%int  pagen= 2;
     if(request.getParameter("page") == null){
    	pagen = 2;
    }else{
    	pagen = Integer.parseInt(request.getParameter("page"));
    }
    
    	    
    if(pagen == 1){
    	out.println("<script>$('li:eq(1)').removeClass('active');$('.tab-pane:eq(1)').removeClass('active');$('li:eq(0)').addClass('active');$('.tab-pane:eq(0)').addClass('active');</script>");
    }
    //if(pagen == 2){
    	//out.println("<script>$('li:eq(1)').addClass('active');</script>");
    //}
    if(pagen == 3){
        out.println("<script>$('li:eq(1)').removeClass('active');$('.tab-pane:eq(1)').removeClass('active');$('li:eq(2)').addClass('active');$('.tab-pane:eq(2)').addClass('active');</script>");
    }
 %>
</body>
</html>