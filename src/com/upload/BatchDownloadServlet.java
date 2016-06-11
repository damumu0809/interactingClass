package com.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DB;

/**
 * Created by Yaya on 2016/5/10.
 */
@WebServlet(name = "BatchDownloadServlet")
public class BatchDownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/x-msdownload");
        response.setHeader("Content-Disposition", "attachment;filename=test.zip");

        String str="";
        String rt="/r/n";

        String[] filenames = request.getParameterValues("filename");
        String filename=filenames[0];
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

        ZipOutputStream zos=new ZipOutputStream(response.getOutputStream());
        for (String fileName:filenames )
        {
            str+=filename+rt;
            File file=new File(filePath+"/"+filename);
            zos.putNextEntry(new ZipEntry(filename));
            FileInputStream in=new FileInputStream(file);
            byte b[]=new byte[1024];
            int n=0;
            while ((n=in.read(b))!=-1)
            {
                zos.write(b,0,n);
            }
            zos.flush();
            in.close();

        }
        zos.setComment("Success"+str+rt);
        zos.flush();
        zos.close();
    }
}
