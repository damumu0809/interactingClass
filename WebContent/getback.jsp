<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.text.*,org.json.JSONObject,org.apache.commons.codec.digest.DigestUtils"%>
<%@page import="java.util.*,java.io.IOException,java.util.Random"%>
<%@page import="java.io.IOException,java.sql.*"%>
<%@page import="database.*,java.sql.*,java.security.*"%>
<%@page import="javax.mail.Message,javax.mail.Session,javax.mail.Transport,javax.mail.internet.InternetAddress,javax.mail.internet.MimeMessage"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">	
		<title>移动互动课堂密码找回</title>
	</head>
	<body>
<%
	request.setCharacterEncoding("UTF-8");	
	String url = request.getRequestURI();	//获取请求发送方相对地址
	String action=request.getParameter("action");
	String userName=request.getParameter("lostUserName");
System.out.print(userName);
	String tableName="userinfo";
	//开始查询数据库
	try {
		//用db类连接"w_xm01"数据库；
		DB query_db = new DB();	
		//用户名的传入
		String sql="select * from "+tableName+" where username='"+userName+"' order by id desc";
		ResultSet rs = query_db.query2(sql);
System.out.print(sql);		
		if(!rs.next()){	//用户名未注册
%>
			<p>该用户名未注册，请确认后重试!</p>
			<a href="login.jsp">点击此处返回</a>
<%
		}else{	//用户名已注册
			String mailGot = rs.getString("mailaddr");		//获取邮箱地址
			String nickName = rs.getString("nickname");		//获取昵称
			//创建KEY
			Random ramkey = new Random();
			String rak = ramkey.nextInt(999999)+"";
			String Key = DigestUtils.md5Hex(rak);
			//获取申请时间
			java.util.Date rTime = new java.util.Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
			String timeApplied = df.format(rTime);
			//用户名、key、申请时间入库
			sql = "insert into getbackinfo(username,bkey,timeapplied) values('"+userName+
				"','"+Key+"','"+timeApplied+"') ON DUPLICATE KEY UPDATE bkey='"+Key+"',timeapplied='"+timeApplied+"'";
System.out.print(sql);			
			query_db.query1(sql);		
			sendMail(mailGot,nickName,userName,Key);			//发送邮件
%>
			<p>邮件已发送，请查收后找回密码</p>
			<a href="login.jsp">确认</a>
<%			
		}
		
	} catch (SQLException sqlexception) {
		sqlexception.printStackTrace();
	}
%>
<%!
		//发送邮件：
		public static void sendMail(String addr,String nName,String uName,String key)throws Exception {
			//新建properties类，用于配置session
			Properties prop = new Properties();
			//主机、协议
			prop.setProperty("mail.host", "smtp.163.com");
			prop.setProperty("mail.transport.protocol", "smtp");
			prop.setProperty("mail.smtp.auth", "true");
			//创建Session
			Session session = Session.getInstance(prop);
			//Session的Debug模式
			session.setDebug(true);
			//获取transport对象
			Transport ts = session.getTransport();
			//用户名密码连接163邮箱；
			ts.connect("smtp.163.com", "15680918651", "ASD1095");
			//邮件内容
			Message message = createSimpleMail(session,addr,nName,uName,key);
			//发送邮件
			ts.sendMessage(message, message.getAllRecipients());
			ts.close();
		}
		public static MimeMessage createSimpleMail(Session session,String addr,String nName,String uName,String key)
			throws Exception {
			//初始化邮件内容
			MimeMessage message = new MimeMessage(session);
			//设置发件人
			message.setFrom(new InternetAddress("15680918651@163.com"));
			//收件人
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(addr));
			//邮件主题
			message.setSubject("移动互动课堂密码找回");
			//邮件内容
			String mes = "";
			mes+="亲爱的" +nName + "您好：<br/><br/>";
			mes+="点击以下链接设置新密码。<br/><br/>";
			mes+="<a href = \"http://ylxdemo.wicp.net:9997/Wednesday_XM01/gettingback.jsp?username="+uName+"&key=" + key +"\">http://ylxdemo.wicp.net:9997/Wednesday_XM01/gettingback.jsp?username=" + uName + "&key=" + key +" </a><br/><br/>";
			mes+="注意:请您在收到邮件24小时内使用，否则该链接将会失效。<br/><br/>";
			message.setContent(mes, "text/html;charset=UTF-8");
			
			return message;
		}
%>

	</body>
</html>	