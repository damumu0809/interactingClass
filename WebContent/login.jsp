<%@page contentType="text/html; charset=UTF-8"%>
<%@page language="java" pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="utf-8" />
	<title>移动互动课堂登录</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta content="width=device-width, initial-scale=1" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="author" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href = "css/bootstrap.css" rel="stylesheet" >
	<link href = "css/bootstrap.min.css" rel = "stylesheet">
	<link href = "css/ourCss.css" rel="stylesheet" >
</head>
<body class="login_back">
<div class="row">
	<!--START LOGIN-->
	<div class="login_box col-xs-10 col-xs-offset-1 col-sm-3 col-sm-offset-2">
		<!--下表单id采用普通命名-->
		<form class="form-horizontal" role="form" method="post" action="log_back.jsp">
			<div class="form-group" >
				<input type="text" class="form-control" id="username" name="username" placeholder="请输入用户名">
			</div>
			<div class="form-group">
				<input style="float:left" type="password" class="form-control" id="password" name="password" placeholder="请输入密码">
			</div>
			<div class="form-group" style="float:right;">
				<input type="checkbox" id="ifremember" name="ifremember"> 请记住我
			</div>
			<br />
			<div class="form-group">
				<button type="submit" class="btn btn-default" style="float:left;width:100%;background-color:#CE0">登录</button>
			</div>
			<div class="clearfix"></div>
		</form>
		<div class="login_footer">
			<a data-toggle="modal" href="#gettingback_box">忘记密码？</a>
			<span> | </span>
			<a data-toggle="modal" href="#register_box">注册新帐号</a>
		</div>
	</div>
	<!--END LOIN-->
</div>

<!--START GETTINGBACK-->
<div class="modal fade" id="gettingback_box" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">
					找回密码
				</h4>
			</div>
			<form id="getbackForm" class="bs-example bs-example-form" role="form" method="post" action="getback.jsp">
				<div class="modal-body">
					<div class="input-group col-xs-10 col-xs-offset-1">
						请输入您要找回密码的用户名：
						<br />
						<br />
						<input type="text" class="form-control" id="lostUserName" name="lostUserName" placeholder="在此输入您的用户名">
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="submit" class="btn btn-primary">
						确认
					</button>
				</div>
			</form>
		</div>
	</div>
</div>
<!--END GETTINGBACK-->

<!--START REGISTER-->
<div class="modal fade" id="register_box" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">
					注册一个新的帐号
				</h4>
			</div>
			<!--以下表单id采用驼峰命名-->
			<form id="registerForm" class="bs-example bs-example-form" role="form" method="post" action="register_back.jsp">
				<div class="modal-body">
					<br />
					<div class="input-group col-xs-12 col-sm-8 col-sm-offset-2">
						<span class="input_header"><b class="s">*</b> 用户名：</span>
						<input type="text" class="form-control" id="userName" name="userName" placeholder="在此输入您的用户名" onFocus="inUser();" onBlur="outUser()">
						<label id="username_ok" class="ok"></label><br/>        <!--label标签不会再新的一行显示  -->
						<div id="username_mess" class="clr">4-20位字符，可由中文、英文、数字及"_"、"-"组成</div>
					</div>
					<br />
					<div class="input-group col-xs-12 col-sm-8 col-sm-offset-2">
						<span class="input_header"><b class="s">*</b> 密码：</span>
						<div id="chang" >
							<input type="password" class="form-control" id="passWord" name="passWord" placeholder="在此输入您的密码" onFocus="inPass();" onBlur="outPass();" onKeyup="keyPass();">
						</div >
						<label id="pass_ok" class="ok"></label>
						<div id="strength">安全强度：
							<table class="showStren" align="center">
								<tr align="center">
								<td id="l" class="ceil">弱</td>
								<td id="m" class="ceil">中</td>
								<td id="h" class="ceil">强</td>
							</tr>
							</table>
						</div>
						<div class="showPw">
							<input type="checkbox" name="visi" id="viewpwd" onClick="visibe();"/>
							<label class="v">显示密码</label><br/>
						</div>	
						<div id="pass_mess" class="clr">6-16位字符，可由英文、数字及"_"、"-"组成</div>
						<div class="clearfix"></div>
					</div>
					<br />
					<div class="input-group col-xs-12 col-sm-8 col-sm-offset-2">
						<span class="input_header"><b class="s">*</b> 确认密码：</span>
						<div id="chang2" >
							<input type="password" class="form-control" id="confirmedPassword" name="confirmedPassword" placeholder="在此重新输入您的密码" onFocus="inRPass();" onBlur="outRPass();">
						</div>
						<label id="rpass_ok" class="ok"></label><br/>
						<div id="rpass_mess" class="clr">请再次输入密码</div>
					</div>
					<br />
					<div class="input-group col-xs-12 col-sm-8 col-sm-offset-2">
						<span class="input_header"><b class="s">*</b> 昵称：</span>
						<input type="text" class="form-control" id="nickName" name="nickName" placeholder="在此输入您的昵称">
					</div>
					<br />
					<div class="input-group col-xs-12 col-sm-8 col-sm-offset-2">
						<span class="input_header">性别：</span>
						<input type="radio" name="sex" id="male"  value="male" checked="true";>男   
						<input type="radio" name="sex" id="female"  value="female">女
					</div>
					<br />
					<div class="input-group col-xs-12 col-sm-8 col-sm-offset-2">
						<span class="input_header">身份：</span>
						<select class="form-control" id="userPosition" name="userPosition">
							<!--value的值表示身份的权限优先级 adim优先级为'0'-->
							<option value="学生" > 学生 </option>
							<option value="教师" > 教师 </option>
							<option value="游客" > 游客 </option>
						</select>
					</div>
					<br />
					<div class="input-group col-xs-12 col-sm-8 col-sm-offset-2">
						<span class="input_header"><b class="s">*</b> 电子邮箱：</span>
						<input type="text" class="form-control" id="mailAddr" name="mailAddr" placeholder="在此输入您的电子邮箱" onFocus="inMail();" onBlur="outMail();">
						<label id="mail_ok" class="ok"></label>
						<div id="mail_mess" class="clr">请输入常用的邮箱，将用来找回密码、接收通知等</div>
					</div>
					<br />
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary" onclick="checkResult();">
						提交
					</button>
				</div>
			</form>	
		</div>
	</div>
</div>
<!--END REGISTER-->

</body>
<!-- END BODY -->
	<script src="js/jquery-1.11.0.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/ourJs.js"></script>
</html>
