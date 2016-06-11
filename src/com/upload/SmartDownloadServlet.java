package com.upload;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import database.DB;

/**
 * Created by Yaya on 2016/5/10.
 */
@WebServlet(name = "SmartDownloadServlet")
public class SmartDownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filename = request.getParameter("fileName");

        SmartUpload su = new SmartUpload();
        su.initialize(getServletConfig(), request, response);
        su.setContentDisposition(null);
        String filePath=null;
        try {
            DB query_db = new DB();	//这里用的是MySQL里的test数据库，可以根据需要改成自己的数据库名
            //构造sql语句，根据传递过来的查询条件参数
            //String sql="select * from "+tableName+" where device_id like '%"+deviceId+"%' order by register_time desc";
            String sql="select * from myPPt where fileName='"+filename+"'";
            System.out.println(sql);
            System.out.println(filename);
            ResultSet rs = query_db.query2(sql);

            while (rs.next()) {
               filePath= rs.getString("filePath");

                System.out.println(filePath);
            }
            rs.close();
            //query_db.close();

        } catch (SQLException sqlexception) {
            sqlexception.printStackTrace();
        }
        try {
            su.downloadFile(filePath+"/"+ filename);
        } catch (SmartUploadException e) {
            e.printStackTrace();
        }

    }
}
