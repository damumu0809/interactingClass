package com.upload;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;

import database.DB;
//import org.json.JSONObject;

public class SmartUploadServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
            doPost(req,resp);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //设置上传文件保存路径
        String filePath = "/opt/tomcat/webapps/data";


        File file = new File(filePath);
        int id=0;
        if(!file.exists()){
            file.mkdir();
        }

        SmartUpload su = new SmartUpload();
        //初始化对象
        su.initialize(getServletConfig(), request, response);
        //设置允许上传文件类型
        su.setAllowedFilesList("txt,jpg,gif,flash,swf");
        String result = "上传成功！";
        //设置禁止上传的文件类型
        try {
            su.setDeniedFilesList("rar,jsp,js");
            //上传文件
            su.upload();

            int count = su.save(filePath);
            System.out.println("上传成功" +  count + "个文件！");
        } catch (Exception e) {
            result = "上传失败！";
            e.printStackTrace();
        }

        com.jspsmart.upload.File temFile=su.getFiles().getFile(0);
        String fileName=temFile.getFileName();
        System.out.println(fileName);

        int selected=0;
        System.out.println(filePath);
        DB upload_db = new DB();
        String sql = "insert into myPPt(fileName,filePath,selected) values('" + fileName  + "','" + filePath + "','" + selected + "')";
        System.out.println(sql);

        System.out.println(sql);
        upload_db.query1(sql);

        //upload_db.close();


        request.setAttribute("result", result);
        request.getRequestDispatcher("Upload.jsp").forward(request, response);
    }

}
