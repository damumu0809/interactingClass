    <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
        <html xmlns="http://www.w3.org/1999/xhtml">
        <head>
        <%@page contentType="text/html; charset=UTF-8" language="java"
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
        <!-- Script Starts Here -->
        <!----//End-slider-script---->
        <!----Calender -------->
        <link rel="stylesheet" href="css/clndr.css" type="text/css" />
        <script src="js/underscore-min.js"></script>
        <script src= "js/moment-2.2.1.js"></script>
        <script src="js/clndr.js"></script>
        <script src="js/site.js"></script>
        <script src="js/loadDetailPage.js"></script>
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
            <% int id = Integer.parseInt(request.getParameter("id")); %>
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
        <li><a href="index.html">Home</a></li>
        <li><a href="index.html" class="scroll">About</a></li>
        <li><a href="index.html" class="scroll">Services</a></li>
        <li><a href="index.html" class="scroll">WORKS </a></li>
        <li><a href="index.html" class="scroll">CLIENTS</a></li>
        <li><a href="blog.html" class="active">BLOG</a></li>
        <li><a href="index.html" class="scroll">CONTACT</a></li>
        </ul>

        </div>
        <div class="clearfix"></div>
        </div>
        </div>
        <!-- Header Ends Here --->
        <!-- Blog Starts Here --->
        <div class="blog">
        <div class="container">
        <h3 class="top-head">Our Blogs</h3>
        <p class="head-sub">Duis vitae velit mollis, congue nisi dignissim, pellentesque lorem</p>
        </div>
        </div>
        <div class="blog-list">
        <div class="container" >
        <div class="row blog-row">
        <div class="col-md-8 content-bar" >

        <!-- Post One Start Here --->

        <div class="clearfix"></div>
        <div id="blog"></div>
        <br/>
        <div class="clearfix"></div>
        <div id="like" class="comments"></div>
        <br/>
        <!-- Comments -->
        <div class="comments" id="comment">



        </div>
        <!-- Comments -->
        <!-- Leave A Reply -->
        <div class="reply" >
        <h3 class="top-head caty">我要评论</h3>
        <span class="blog-line thik thiky"></span>
        <form class="reply-box" action="/interactingClass/Comment" method="post">
        <div class="reply-text">
        <input type="text" name="content" placeholder="评论内容" >
        <input type="hidden" name="id" id="reply"/>
        <input type="hidden" name="reply_id" value="0" />
        </div>
        <div class="reply-text">
        <button type="submit">评论</button>
        </div>
        </form>
        </div>
        <!-- Leave A Reply -->
        </div>
        <div class="clearfix"></div>
        </div>
        <!-- Blog Description -->
        </div>

        </div>
        </div>

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
        </div>

        </body>

        </html>