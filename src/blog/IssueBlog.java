package blog;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import database.DB;

/**
 * Servlet implementation class IssueBlog
 */
@WebServlet("/IssueBlog")
public class IssueBlog extends HttpServlet {
	private static final long serialVersionUID = 1L;

	   private boolean isMultipart;
	   private String filePath;
	   private int maxFileSize = 100000000 * 1024;
	   private int maxMemSize = 4 * 1024;
	   private File file ;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IssueBlog() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		response.setContentType("text/html;charset=utf-8");
		java.io.PrintWriter out = response.getWriter( );
		
		DB db = new DB();
		
		HttpSession session = request.getSession();
		String user_name = session.getAttribute("username").toString();
		
		Date date = new Date();
		long time = date.getTime();
		
		int hasAcc = 0;//标注是否有附件
		
		
		//图片、视频、音频
		 isMultipart = ServletFileUpload.isMultipartContent(request);
		 if(!isMultipart){
			 //没有文件上传
			 hasAcc = 0;
			 System.out.println("没有附件");
		 }else{
			 hasAcc = 1;
		 }//这里并不能判断是否有附件
		 DiskFileItemFactory factory = new DiskFileItemFactory();
	      // 文件大小的最大值将被存储在内存中
	      factory.setSizeThreshold(maxMemSize);
	      // Location to save data that is larger than maxMemSize.
	      factory.setRepository(new File("C://temp"));
	      
	      // 创建一个新的文件上传处理程序
	      ServletFileUpload upload = new ServletFileUpload(factory);
	      // 允许上传的文件大小的最大值
	      upload.setSizeMax( maxFileSize );
	      //编码
	      upload.setHeaderEncoding("utf-8");
	      //定制Map充当作用域，存数据
	      Map<String,String> map = new TreeMap<String, String>();
	      String key;
	      String value;

	      // 解析请求，获取文件项
	      List<FileItem> fileItems = new ArrayList<FileItem>();
	      List<String> allFile = new ArrayList<String>();
	      try{
			fileItems = upload.parseRequest(request);
		
			
	      // 处理上传的文件项
			//Iterator i = fileItems.iterator();
			
	      String fieldName;
	      String fileName = null;
	      String contentType;
	      boolean isInMemory;
	      long sizeInBytes;
	      
	      for(FileItem fi :fileItems){
	         if ( !fi.isFormField () )	
	         {
	            // 获取上传文件的参数
	            fieldName = new String(fi.getFieldName().getBytes("GB2312"),"iso8859-1");
	            fileName = fi.getName();
	            System.out.println(fileName);
	            if(fileName.isEmpty()){
	            	//没有上传文件
	            	hasAcc = 0;
	            }else{
	            	hasAcc = 1;
	            	contentType = fi.getContentType();
		            isInMemory = fi.isInMemory();
		            sizeInBytes = fi.getSize();
		            // 写入文件
		            filePath = getServletContext().getInitParameter("file-upload");
		            if( fileName.lastIndexOf("\\") >= 0 ){
		            	filePath = filePath + fileName.substring( fileName.lastIndexOf("\\")+1);
		               file = new File(filePath );
		            }else{
		            	filePath = filePath + fileName.substring(fileName.lastIndexOf("\\")+1);
		                file = new File(filePath );
		            }
		            System.out.println(filePath);
		           
		            
					fi.write( file ) ;
					
		            fileName = fileName.substring( fileName.lastIndexOf("\\")+1);
		            allFile.add(filePath);
		            System.out.println("所有附件"+allFile);
		            
		            out.println("Uploaded Filename: " + fileName + "<br>");	 
	            }
	                       
	         }else {	        	 
	        	 key = fi.getFieldName();
	        System.out.println(key);
	        	 value = fi.getString();
	       
	        //中文乱码
	        	value =  new String(value.getBytes("iso8859-1"),"utf-8");
	        	System.out.println(value);
	        	 map.put(key, value);
			} 
	      }
	     }catch(Exception ex) {
		      System.out.println(ex);
		 }
	    //添加进表blog并获得blog_id
	      String topic = map.get("topic");
	      String text = map.get("text");
	      
	      if(topic.isEmpty()){
	    	  out.println("<script>alert('主题不能为空！');window.location.href='./blog.html';</script>");
	      }else{
	    	  if(text.isEmpty()){
	    		  out.println("<script>alert('内容不能为空！');window.location.href='./blog.html';</script>");
	    	  }else{
	    		  String sql1 = "INSERT INTO blog(user_name, time, topic, text, hasAcc) VALUES('"+user_name+"','"+time+"','"+topic+"','"+text+"','"+hasAcc+"')";
	    	      
	    		    
	    	      int blog_id = db.query3(sql1);
	    	      System.out.println(blog_id);
	    	      
	    	    //添加进表blog_accessory
	    	      String sql2;
	    	      String type = null;
	    	      String dotname = null; //后缀名
	    	     
	    	      for(String href: allFile){
	    	    	  System.out.println(href);
	    	    	  //判断文件的类型
	    	    	  dotname = href.substring( href.lastIndexOf(".")+1);
	    	    	  System.out.println(dotname);
	    	    	  
	    	    	  if(dotname.equals("bmp")||dotname.equals("jpg")||dotname.equals("jepg")||dotname.equals("gif")||dotname.equals("swf")){
	    	    		 type = "picture"; 
	    	    	  }
	    	    	  if(dotname.equals("avi")||dotname.equals("mpeg")||dotname.equals("mov")||dotname.equals("avi")||dotname.equals("mp4")){
	    	    		 type = "video"; 
	    	    	  }
	    	    	  if(dotname.equals("cmf")||dotname.equals("cda")||dotname.equals("mp3")||dotname.equals("wav")||dotname.equals("mid")){
	    		    	 type = "music"; 
	    		     }
	    	    	 System.out.println(type);	    	  
	    	    	  
	    	    	  sql2 = "INSERT INTO blog_accessory(blog_id, type, href) VALUES('"+blog_id+"','"+type+"' ,'"+href+"' )";
	    	    	  sql2 = sql2.replaceAll("\\\\","/");
	    	    	  db.query1(sql2);
	    	      }
	    	      out.print("<script type='text/javascript'>alert('发布成功！');window.location.href='./blog.html';</script>");
	    		  
	    	  }
	      }
	      
	      
	      
	      
	  	     
	 }
	 
}
