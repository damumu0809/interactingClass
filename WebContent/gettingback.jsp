<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.text.*,org.json.JSONObject,org.apache.commons.codec.digest.DigestUtils"%>
<%@page import="java.util.*,java.io.IOException"%>
<%@page import="java.io.IOException,java.sql.*"%>
<%@page import="database.*,java.sql.*,java.security.*"%>
<html>
	<head>
		<meta charset="utf-8" />
		<title>移动互动课堂密码找回</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta content="width=device-width, initial-scale=1" name="viewport" />
		<meta content="" name="description" />
		<meta content="" name="author" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href = "css/bootstrap.css" rel="stylesheet" >
		<link href = "css/bootstrap.min.css" rel = "stylesheet">
		<link href = "css/ourCss.css" rel="stylesheet" >
		<%@ include file="frame_css.jsp"%>
	</head>
	<body>
<%
	request.setCharacterEncoding("UTF-8");	
	String url = request.getRequestURI();	//获取请求发送方相对地址
	String userName=request.getParameter("username");
	String Key = request.getParameter("key");
	String tableName="getbackinfo";
	//开始查询数据库
	try {
		DB query_db = new DB();	//这里用的是MySQL里的test数据库，可以根据需要改成自己的数据库名
		//用户名的传入
		String sql="select * from "+tableName+" where username='"+userName+"' order by id desc";
		ResultSet rs = query_db.query2(sql);
		if(!rs.next()){	//用户名不存在
%>
			<p>请确认连接无误后重试!</p>
<%
		}else{	//用户名已入库
			//获取数据库内存好的key
			String keyGot = rs.getString("bkey");		
			if(!keyGot.equals(Key)){	//两key不匹配
%>
			<p>请确认连接无误后重试!</p>
<%			}else{	//两Key匹配，重置密码;
%>
		<div class="container">	
			<div class="gback_header">
				<h3 class="top-head text-center">MOBILECLASSROOM</h3>
				<p>您可以在此界面重置您的密码</p>
			</div>
			<div class="getback_box col-xs-10 col-xs-offset-1 col-sm-6 col-sm-offset-3">
				<form id="getbackForm"class="form-horizontal" role="form" method="post" action="updataPw.jsp?username=<%=userName%>";>
					<div class="input-group col-xs-8 col-xs-offset-2">
						<span class="input_header hidden-xs">新的密码：</span>
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
						<div id="pass_mess" class="clr">6-16位字符，可由英文、数字及"_"、"-"组成</div>
						<div class="clearfix"></div>
					</div>
					<br />
					<div class="input-group col-xs-8 col-xs-offset-2">
						<span class="input_header hidden-xs">确认密码：</span>
						<div id="chang2" >
							<input type="password" class="form-control" id="confirmedPassword" name="confirmedPassword" placeholder="在此重新输入您的密码" onFocus="inRPass();" onBlur="outRPass();">
						</div>
						<label id="rpass_ok" class="ok"></label><br/>
						<div id="rpass_mess" class="clr">请再次输入密码</div>
					</div>
					<br />
					<div class="subt">
						<a class="col-xs-8 col-xs-offset-2 text-center" onclick="checkResult2();">确定</a>
					</div>
					<div class="clearfix"></div>
				</form>
			</div>
		</div>	
<%
				//response.sendRedirect("index.jsp?username="+userName);
			}
		}
		
	} catch (SQLException sqlexception) {
		sqlexception.printStackTrace();
	}
%>
	</body>
		<script src="js/jquery-1.11.0.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/ourJs.js"></script>
</html>	