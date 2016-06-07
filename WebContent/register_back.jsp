<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.text.*,org.json.JSONObject"%>
<%@page import="java.util.*,java.io.IOException"%>
<%@page import="java.io.IOException,java.sql.*"%>
<%@page import="database.*,java.sql.*"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>
	<body>
<%
	request.setCharacterEncoding("UTF-8");
	String action=request.getParameter("action");
	String userName=request.getParameter("userName");
	String passWord=request.getParameter("passWord");
	String confirmedPassword=request.getParameter("confirmedPassword");
	String nickName=request.getParameter("nickName");
	String Sex=request.getParameter("sex");
	String userPosition=request.getParameter("userPosition");
	String mailAddr=request.getParameter("mailAddr");
	String tableName="userinfo";
	//开始查询数据库
	try {
		DB query_db = new DB();	//这里用的是MySQL里的test数据库，可以根据需要改成自己的数据库名
		//用户名的传入
		String sql="select * from "+tableName+" where username='"+userName+"' order by id desc";
		ResultSet rs = query_db.query2(sql);
		if(rs.next()){	//存在该记录，该用户名已注册
%>
			<p>该用户名已注册，请重试!</p>
			<a href="login.jsp">点击此处返回</a>
<%
		}else{	//该用户名未注册，录入该用户。
			java.util.Date rTime = new java.util.Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
			String registerTime = df.format(rTime);			
			sql = "insert into userinfo(username,password,nickname,sex,mailaddr,identity,register_time,loginstatus) values('"
					+ userName + "',md5('"+passWord+"'),'"+nickName+"','"+Sex+"','"+mailAddr+"','"+userPosition+"','"+registerTime+"','0')";
			query_db.query1(sql);
%>
			<p>恭喜您!注册成功</p>
			<a href="login.jsp">确认</a>
<%	
		}
		
	} catch (SQLException sqlexception) {
		sqlexception.printStackTrace();
	}
%>
	</body>
</html>	