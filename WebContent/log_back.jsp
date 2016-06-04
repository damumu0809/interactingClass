<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.text.*,org.json.*,org.apache.commons.codec.digest.*"%>
<%@page import="java.util.*,java.io.IOException"%>
<%@page import="java.io.IOException,java.sql.*,javax.servlet.http.HttpSession"%>
<%@page import="database.*,java.sql.*,java.security.*"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>
	<body>
<%
	request.setCharacterEncoding("UTF-8");	
	String url = request.getRequestURI();	//获取请求发送方相对地址
	String action=request.getParameter("action");
	String userName=request.getParameter("username");
	String passWord=request.getParameter("password");
	String ifRemember=request.getParameter("ifremember");
	String tableName="userinfo";
	//开始查询数据库
System.out.println(userName);
	try {
		DB query_db = new DB();	//这里用的是MySQL里的test数据库，可以根据需要改成自己的数据库名
		//用户名的传入
		String sql="select * from "+tableName+" where username='"+userName+"' order by id desc";
		ResultSet rs = query_db.query2(sql);
System.out.println(sql);
		if(!rs.next()){	//用户名未注册
%>
			<p>该用户名未注册，请确认后重试!</p>
			<a href="login.jsp">点击此处返回</a>
<%
		}else{	//用户名已注册
			String passWordGot = rs.getString("password");	
			String Identity = rs.getString("identity");
			
			if(!passWordGot.equals(DigestUtils.md5Hex(passWord))){	//帐号密码不匹配
				
%>
			<p>您的密码有误，请确认后重试！</p>
			<a href="login.jsp">点击此处返回</a>
<%			}else{	//登录成功，进入主界面
				session = request.getSession(); 
				session.setAttribute("username",userName);
				session.setAttribute("identity", Identity);
				response.sendRedirect("index.jsp");
				
			}
		}
		
	} catch (SQLException sqlexception) {
		sqlexception.printStackTrace();
	}
%>
	</body>
</html>	