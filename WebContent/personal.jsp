    <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
        <html xmlns="http://www.w3.org/1999/xhtml">
        <head>
        <%@ page contentType="text/html; charset=UTF-8" language="java"
                import="java.text.*,org.json.JSONObject.*,java.io.PrintWriter"
                import="java.util.*,java.sql.*,java.io.IOException"
                import="database.*"%>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Personal Center</title>
        <link href="css/bootstrap.css" rel='stylesheet' type='text/css' media="all" />
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="js/jquery-1.11.0.min.js"></script>
        <!-- Custom Theme files -->
        <link href="css/style.css" rel='stylesheet' type='text/css' media="all" />
        <!-- Custom Theme files -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
        <!-- Google Fonts -->
        <link href='http://fonts.useso.com/css?family=Open+Sans:400,300,600,700,800' rel='stylesheet' type='text/css'>
        <link href="css/blog.css" rel="stylesheet" />
        <!-- Script Starts Here -->
        <!----//End-slider-script---->
        <!----Calender -------->
        <link rel="stylesheet" href="css/clndr.css" type="text/css" />
        <script src="js/underscore-min.js"></script>
        <script src= "js/moment-2.2.1.js"></script>
        <script src="js/clndr.js"></script>
        <script src="js/site.js"></script>
        <script src="js/loadPersonalPage.js"></script>
        <!----End Calender -------->

        <script type="text/javascript">
        jQuery(document).ready(function($) {
        $(".scroll").click(function(event){
        event.preventDefault();
        $('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
        });
        });
        </script>
        </head>
            <%
            int id = 0;
            if (request.getParameter("id")==null){
             id = 0;
            }else{
           id = Integer.parseInt(request.getParameter("id")); }%>
        <body onload="script:getPersonal(<%=id%>)">
        <!-- Header Starts Here --->
        <div class="header" id="home">
        <div class="container">
        <div class="logo">
        <a href="index.html"><img src="images/logo.png" alt=""></a>
        </div>
        <span class="menu"></span>
        <div class="cleare"></div>
        <script>
        $( "span.menu" ).click(function() {
        $( "ul.navig" ).slideToggle( "slow", function() {
        // Animation complete.
        });
        });
        </script>
        <div class="navigation">
        <ul class="navig">
        <li><a href="index.jsp">首页</a></li>
        <li><a href="blog.html">博客</a></li>
        <li><a href="personal.jsp">我的博客</a></li>
        <li><a href="statistic.html">博客统计</a></li>
        <li><a href="javascript:" data-toggle="modal" data-target="#myModal"  >写博客</a></li>

        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
        <div class="modal-content">
        <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabelVote">写博客</h4>
        </div>

        <form class="form-horizontal"  action="/interactingClass/IssueBlog" method="post" enctype="multipart/form-data">
        <div class="modal-body">
        <div class="form-group">
        <div class="col-sm-offset-1 col-sm-9">
        <textarea class="form-control" rows="1" placeholder="请输入博客主题..." name="topic"></textarea>
        </div>
        </div>
        <div class="form-group">
        <div class="col-sm-offset-1 col-sm-9">
        <textarea class="form-control" rows="4" placeholder="请输入文章内容..." name="text"></textarea>
        </div>
        </div>
        <!-- 添加图片、音频、视频-->
        <div class="form-group">
        <div class="col-sm-offset-1 col-sm-9">
        <!-- 设计原则是只添加图片视频音频中的一种-->
        <input type="file" multiple="multiple" name="picture" id="picture"/>可添加多张图片或一段视频、音频

        </div>
        </div>
        </div>
        <div class="modal-footer">
        <button class="btn btn-primary btn-sm " type="submit" data-toggle="modal" data-target="#myModal" id="issue">发布</button>
        </div>
        </form>

        </div>
        </div>
        </div>
        </ul>

        </div>
        <div class="clearfix"></div>
        </div>
        </div>
        <!-- Header Ends Here --->
        <!-- Blog Starts Here --->
        <!-- Blog Starts Here --->
        <div class="blog">
        <div class="container">
        <h4 style="font-size:30px" class="top-head" id="blogNumber">发表博客</h4>
        <div style="padding-left:40%">
        <div class="desc-left"><i class="des-q"></i><h5 id="pictureNumber">图片</h5></div>
        <div class="desc-left"><i class="des-s"></i><h5 id="videoNumber">视频</h5></div>
        <div class="desc-left"><i class="des-r"></i><h5 id="musicNumber">音频</h5></div>
        </div>
        <%--<p class="head-sub">Duis vitae velit mollis, congue nisi dignissim, pellentesque lorem</p>--%>
        </div>
        </div>
        <div class="blog-list">
        <div class="container">
        <div class="row blog-row">
        <div id="blog">
        <!-- 动态加载博客-->
        </div>

        </div>

        </div>
        </div>
        <!-- Blog Ends Here --->



        <div class="footer">
        <div class="container">
        <p class="copyright">Copyright &copy; 2014.Company name All rights reserved.</p>
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

        </body>

        </html>