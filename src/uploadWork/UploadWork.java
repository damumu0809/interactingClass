package uploadWork;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
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
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import database.DB;


/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadWork")



public class UploadWork extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	   private boolean isMultipart;
	   private String filePath;
	   private int maxFileSize = 100000000 * 1024;
	   private int maxMemSize = 4 * 1024;
	   private File file ;

	   public void init( ){
	      // 获取文件将被存储的位置
	      filePath = 
	             getServletContext().getInitParameter("file-upload"); 
	   }
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadWork() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//session
		HttpSession session = request.getSession();
		String name = session.getAttribute("username").toString();
		
		// 检查我们有一个文件上传请求
	      isMultipart = ServletFileUpload.isMultipartContent(request);
	      response.setContentType("text/html;charset=utf-8");
	      java.io.PrintWriter out = response.getWriter( );
	      if( !isMultipart ){
	         out.println("<html>");
	         out.println("<head>");
	         out.println("<title>Servlet upload</title>");  
	         out.println("</head>");
	         out.println("<body>");
	         out.println("<p>No file uploaded</p>"); 
	         out.println("</body>");
	         out.println("</html>");
	         return;
	      }
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

	      try{ 
	      // 解析请求，获取文件项
	      List<FileItem> fileItems = upload.parseRequest(request);
	      // 处理上传的文件项
	      //Iterator i = fileItems.iterator();
	      out.println("<html>");
	      out.println("<head>");
	      out.println("<title>Servlet upload</title>");  
	      out.println("</head>");
	      out.println("<body>");
	      
	      String fieldName;
	      String fileName = null;
	      String contentType;
	      boolean isInMemory;
	      long sizeInBytes;
	      int flag = 0;//标志是否有文件上传
	      
	      for(FileItem fi :fileItems){
	      
	         if ( !fi.isFormField () )	
	         {
	            // 获取上传文件的参数
	        	//hidden对象的传递 出现问题java.lang.NullPointerException
	        	//Object taskNum = request.getAttribute("taskNum");
	            fieldName = new String(fi.getFieldName().getBytes("GB2312"),"iso8859-1");
	            fileName = fi.getName();
	            if(!fileName.isEmpty()){
	            	flag = 1;
	          System.out.println(fileName);
	            contentType = fi.getContentType();
	            isInMemory = fi.isInMemory();
	            sizeInBytes = fi.getSize();
	            // 写入文件
	            filePath = getServletContext().getInitParameter("file-upload");
	            System.out.println(filePath);
	            if( fileName.lastIndexOf("\\") >= 0 ){
	            	filePath = filePath + fileName.substring( fileName.lastIndexOf("\\")+1);
	               file = new File(filePath ) ;
	            }else{
	            	filePath = filePath + fileName.substring(fileName.lastIndexOf("\\")+1);
	                file = new File(filePath ) ;
	            }
	            System.out.println("路径"+filePath);
	            
	            fi.write( file ) ;
	            fileName = fileName.substring( fileName.lastIndexOf("\\")+1);
	            out.println("Uploaded Filename: " + fileName + "<br>");
	        System.out.println("1");
	            }else{
	            	flag = 0;
	            	out.println("<script>alert('请选择一个文件！');window.location.href='./index.jsp?page=3';</script>");
	            }
	            
	         }else {
	        	 
	        	 key = fi.getFieldName();
	        System.out.println(key);
	        	 value = fi.getString();
	        System.out.println(value);
	        	 map.put(key, value);
	        System.out.println("2");	
			}
	       
	      }
	      
	    if(flag == 1){
	    	
	    	//上传时间
	      	Date date = new Date();
	      	long uploadTime = date.getTime();
	      	
	      	//连接数据库
		      	DB db = new DB();
		      
	          String sqlInsert;
	          String sqlSelect;
	          
	          System.out.println(map.get("taskNum"));
	          int taskNum = Integer.parseInt(map.get("taskNum"));
	          
//	          //判断是否过期
//	          sqlSelect = "SELECT * FROM issueWork WHERE id =" + taskNum;
//	          ResultSet rs = db.query2(sqlSelect);
//	         
//	          while(rs.next()){
//	        	  if(uploadTime >rs.getLong("deadLine")){
//	        		  //过期不能提交
//	        		  out.print("<script type='text/javascript'>alert('作业已经超期！');window.location.href='./index.html';</script>");
//	        	  }
//	          
//	          }
	          
	          //写入数据库
	          System.out.println(filePath);
	          sqlInsert = "INSERT INTO homework(file_name, href, owner, time, taskNum)"+
	          " VALUES (\""+fileName+"\",\""+ filePath +"\",\""+name+"\",\""+uploadTime+"\",\""+taskNum+"\")";
	     
	          /*
	           * \会被转义，但是'\'也是正则表达式中的转义字符（replaceAll 的参数就是正则表达式），
	           * 需要用两个代表一个。所以：\\\\被java转换成\\,\\又被正则表达式转换成\。
	           */
	          sqlInsert = sqlInsert.replaceAll("\\\\","/");
	    System.out.println(sqlInsert);
	    System.out.println("3");      
	           db.query1(sqlInsert);
	        //实现alert之后再跳转页面
	  	    out.print("<script type='text/javascript'>alert('上传成功！');window.location.href='./index.jsp?page=3';</script>");

		      
		      /*
		       * 执行完executeQuery后不能再执行executeQuery
		       */
		     
		     
		    
		   
		   
		    
		    //response.sendRedirect("./index.html");
		    //页面的位置
		    
		    
		    
		}
	
	      
	  


 
	    
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (FileUploadException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
}
}
