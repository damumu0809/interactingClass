<%@page contentType="text/html; charset=UTF-8" language="java"
	import="java.text.*,org.json.JSONObject,java.util.ArrayList,java.io.PrintWriter"
	import="java.util.*,java.sql.*,java.io.IOException"
	import="database.*,java.sql.ResultSet"%>
<%
System.out.println("走到这里了！");
	//session = request.getSession();
	String userName=session.getAttribute("username").toString();
System.out.println("获得的参数是：user_name="+userName);
	List jsonList = new ArrayList();
	try {
		DB query_db = new DB();	//这里用的是MySQL里的test数据库，可以根据需要改成自己的数据库名
System.out.println("连接数据库Ok！！！");
		//根据用户名获取用户身份
		String sql="select * from userinfo where username='"+userName+"' order by id";
System.out.println("构造出来的sql语句是："+sql);
		ResultSet rs = query_db.query2(sql);
		if(rs.next()){
			String Identity = rs.getString("identity");	
			
System.out.println(Identity);
		
			//通过用户身份设置菜单
			sql = "select * from menu where identity='"+Identity+"'";
			rs = query_db.query2(sql);
System.out.println("构造出来的sql语句是："+sql);		
			while (rs.next()) {
				List list = new ArrayList();
				list.add(rs.getString("MENU_NAME"));
				list.add(rs.getString("MENU_HREF"));
				list.add(rs.getString("LI_CLASS"));
				list.add(rs.getString("SPAN_CLASS"));
				list.add(rs.getString("A_CLASS"));
				jsonList.add(list);
			}

		
		}
System.out.println("数据库关闭了！！！");
	} catch (SQLException sqlexception) {
		sqlexception.printStackTrace();
	}
	//////////数据库查询完毕，得到了json数组jsonList//////////
	//jsonList.clear();
	//下面开始构建返回的json
	JSONObject jsonObj=new JSONObject();
	jsonObj.put("Data",jsonList);
	jsonObj.put("result_msg","ok");	//如果发生错误就设置成"error"等
	jsonObj.put("result_code",0);	//返回0表示正常，不等于0就表示有错误产生，错误代码
System.out.println("最后构造得到的json是："+jsonObj.toString());
	response.setContentType("text/html; charset=UTF-8");
	try {
		response.getWriter().print(jsonObj);
		response.getWriter().flush();
		response.getWriter().close();
	} catch (IOException e) {
		e.printStackTrace();
	}
	System.out.println("返回结果给调用页面了。");
%>
