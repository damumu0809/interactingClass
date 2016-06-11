<%@page contentType="text/html; charset=UTF-8" language="java"
	import="java.text.*,org.json.JSONObject,java.io.PrintWriter"
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
<<<<<<< HEAD
			sql = "select * from menu where identity='"+Identity+"'";
			rs = query_db.query2(sql);
System.out.println("构造出来的sql语句是："+sql);		
			while (rs.next()) {
=======
			String new_identity = "student";
			switch(Identity) {
				case "学生":
					new_identity = "student";
					break;
				case "教师":
					new_identity = "teacher";
					break;
			}
			
			Identity = "student";
			String sql_menu = "select * from menu where identity='" + new_identity + "' order by id";
			ResultSet rs2 = query_db.query2(sql_menu);
System.out.println("构造出来的rs2："+rs2);
System.out.println("构造出来的sql语句是："+sql_menu);
System.out.println("构造出来的rs2.next()："+rs2.next());
			while (rs2.next()) {
System.out.println("while map ");
>>>>>>> a0c038fdf17848315b141b4162591defa451d65d
				List list = new ArrayList();
				System.out.println("list " + list);
				System.out.println("menu_name" + rs2.getString("menu_name"));
				list.add(rs2.getString("menu_name"));
				list.add(rs2.getString("menu_href"));
				list.add(rs2.getString("li_class"));
				list.add(rs2.getString("span_class"));
				list.add(rs2.getString("a_class")); 
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
