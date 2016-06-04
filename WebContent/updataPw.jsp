<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.text.*,org.json.JSONObject,org.apache.commons.codec.digest.DigestUtils"%>
<%@page import="java.util.*,java.io.IOException"%>
<%@page import="java.io.IOException,java.sql.*"%>
<%@page import="database.*,java.sql.*,java.security.*"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>
	<body>
<%
	request.setCharacterEncoding("UTF-8");	
	String url = request.getRequestURI();	//获取请求发送方相对地址
	String userName= request.getParameter("username");
	String passWord=request.getParameter("passWord");
	String rPassWord = request.getParameter("confirmedPassword");
	String tableName="userinfo";
System.out.println(passWord+"&&"+rPassWord+"&&"+userName);
		DB query_db = new DB();	
		if(!passWord.equals(rPassWord)){
%>
		请确认输入密码正确。
<%			
		}else{
			String sql;
			sql = "update "+tableName+" set password = md5('"+passWord+"') where username = '"+userName+"'";
System.out.println(sql);			
			query_db.query1(sql);
%>
	恭喜您！密码修改成功
	<a href="login.jsp">点击此连接返回登录界面</a>
<%
		}
		
%>
	</body>
</html>	