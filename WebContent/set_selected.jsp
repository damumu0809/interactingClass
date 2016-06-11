<%@page contentType="text/html; charset=UTF-8" language="java"
        import="java.text.*,org.json.JSONObject,java.util.ArrayList,java.io.PrintWriter"
        import="java.util.HashMap,java.util.List,java.sql.*,java.util.Map,java.io.IOException"%>
<%@ page import="database.*" %>
<%
    String action=request.getParameter("action");
    String Id=request.getParameter("id");
    String fileName="";
    String filePath="";


    //开始查询数据库
    //注意：如果遇到问题，Tomcat的日志在C:\Tomcat\logs\stdout.log，可以查看
    List jsonList = new ArrayList();
    try {
        Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException classnotfoundexception) {
        classnotfoundexception.printStackTrace();
    }

    //注意：数据表video_file确保在test数据库下面，如果没有就导入进去，或者放在自己建的数据库，下面的test相应要修改

    DB query_db=new DB();
    //构造sql语句，根据传递过来的查询条件参数，目前是deviceId和gpsTime
    String sql="update myPPt set selected=0";
    query_db.query1(sql);
    String sql1= "update myPPt set selected=1 where id='"+Id+"'";
    query_db.query2(sql1);
    //也可以根据device_id查询
    //String sql="select * from video_file where device_id like '%"+deviceId+"%' order by register_time";
//		System.out.println("构造出来的sql语句是："+sql);
//        ResultSet rs = query_db.executeQuery(sql);
//        while (rs.next()) {
//
//            fileName=rs.getString("fileName");
//
//        }
//        rs.close();
    //query_db.close();

    filePath="../images";
    response.setContentType("text/html; charset=UTF-8");
    try {
        response.getWriter().print(filePath+"/"+fileName);
        response.getWriter().flush();
        response.getWriter().close();
    } catch (IOException e) {
        e.printStackTrace();
    }
    System.out.println("返回结果给调用页面了。");
%>

<%--$.post("index.html?user_id=admin",function(data){--%>
<%--alert(0);--%>
<%----%>
<%--}--%>
