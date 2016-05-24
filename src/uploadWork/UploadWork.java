package uploadWork;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
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
	      factory.setRepository(new File("c:\\temp"));
	      
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
	      
	      for(FileItem fi :fileItems){
	      
	         if ( !fi.isFormField () )	
	         {
	            // 获取上传文件的参数
	        	//hidden对象的传递 出现问题java.lang.NullPointerException
	        	//Object taskNum = request.getAttribute("taskNum");
	            fieldName = new String(fi.getFieldName().getBytes("GB2312"),"iso8859-1");
	            fileName = fi.getName();
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
	            System.out.println(filePath);
	            fi.write( file ) ;
	            fileName = fileName.substring( fileName.lastIndexOf("\\")+1);
	            out.println("Uploaded Filename: " + fileName + "<br>");
	        System.out.println("1");
	            
	         }else {
	        	 
	        	 key = fi.getFieldName();
	        System.out.println(key);
	        	 value = fi.getString();
	        System.out.println(value);
	        	 map.put(key, value);
	        System.out.println("2");	
			}
	       
	      }
	    //上传时间
      	Date date = new Date();
      	
      	//时间格式转换
      	SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd HH:mm:ss");
      	String uploadTime = ft.format(date);
      	
      	//连接数据库
	      	DB db = new DB();
	      
          String sqlInsert;
          String sqlSelect;
      	
          //写入数据库
   System.out.println(map.get("taskNum"));
          int taskNum = Integer.parseInt(map.get("taskNum"));
          
          System.out.println(filePath);
          sqlInsert = "INSERT INTO homework(file_name, href, owner, time, taskNum)"+
          " VALUES (\""+fileName+"\",\""+ filePath +"\",\""+"xiaomu\",\""+uploadTime+"\",\""+taskNum+"\")";
     
          /*
           * \会被转义，但是'\'也是正则表达式中的转义字符（replaceAll 的参数就是正则表达式），
           * 需要用两个代表一个。所以：\\\\被java转换成\\,\\又被正则表达式转换成\。
           */
          sqlInsert = sqlInsert.replaceAll("\\\\","/");
    System.out.println(sqlInsert);
    System.out.println("3");      
          boolean rs1 = db.query1(sqlInsert);
        //实现alert之后再跳转页面
  	    out.print("<script type='text/javascript'>alert('上传成功！');window.location.href='./index.html';</script>");

	      
	      /*
	       * 执行完executeQuery后不能再执行executeQuery
	       */
	     /*
	      //查询语句
          sqlSelect = "SELECT * FROM homework WHERE owner='ll'";
          ResultSet rs2 = db.query2(sqlSelect);
          
	       try{
	    	   while(rs2.next()){
	         		out.println("<a href="+ rs2.getString("href") +">"+rs2.getString("file_name")+"</a>"+"</br>");
	         		System.out.println(rs2.getString("href"));
	         	}
	       }
	       catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		*/
	         	
	      out.println("</body>");
	      out.println("</html>");
	    
	   }catch(Exception ex) {
	       System.out.println(ex);
	   }
	   
	    
	    //response.sendRedirect("./index.html");
	    //页面的位置
	    
	    
	    
	}
}
