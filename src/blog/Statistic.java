package blog;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import database.DB;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * Servlet implementation class Statistic
 */
@WebServlet("/Statistic")
public class Statistic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Statistic() {
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
		
		//打开指定的文件  
		String path = getServletContext().getInitParameter("excel-file");
        System.out.println(path);
        path = path+"file.xls";
		WritableWorkbook book = Workbook.createWorkbook(new File(path));  
		  
		//创键Excel表格中的第一个表,命名为"blog"  
		WritableSheet wsheet = book.createSheet("blog", 0);  
		
		try {  
			
			wsheet.addCell(new Label(0, 0, "总用户"));  
			wsheet.addCell(new Label(4, 0, "总博客"));  
			wsheet.addCell(new Label(0, 1, "用户名"));  
			wsheet.addCell(new Label(1, 1, "已发博客"));  
			wsheet.addCell(new Label(2, 1, "赞")); 
			wsheet.addCell(new Label(3, 1, "评论"));  
  
			} catch (Exception e) {  
			System.out.println(e);  
			}  
		
		//先从userinfo里获取username
		//再从blog表中查询发表博客数、获得总赞及评论
		
		String sql1 = "SELECT username FROM userinfo";
		ResultSet rs1 = db.query2(sql1);
		int userNum = 0;
		int blogNum = 0;
		
		List userBlog = new ArrayList();
		JSONObject userList = null;
		
		try {
			while(rs1.next()){
				//对用户循环
				userNum++;
				int blog = 0;
				String username = rs1.getString("username");
				String sql2 = "SELECT * FROM blog WHERE user_name = '"+username+"'";
				ResultSet rs2 = db.query2(sql2);
				int likeNum = 0;
				int commentNum = 0;
				while(rs2.next()){
					//对每个用户的博客循环
					blog++;
					int like = Integer.parseInt(rs2.getString("like"));
					int comment = Integer.parseInt(rs2.getString("comment"));
					likeNum = likeNum+like;
					commentNum = commentNum+comment;
				}
				System.out.println(username+"总赞"+likeNum);
				System.out.println(username+"总评论"+commentNum);
				System.out.println(username+"博客数"+blog);
				blogNum = blogNum + blog;
				
				wsheet.addCell(new Label(0, userNum+1, username));  
				wsheet.addCell(new jxl.write.Number(1, userNum+1, blog));  
				wsheet.addCell(new jxl.write.Number(2, userNum+1, likeNum)); 
				wsheet.addCell(new jxl.write.Number(3, userNum+1, commentNum));  
				
				userList = new JSONObject();
				userList.put("username", username);
				userList.put("blog", blog);
				userList.put("likeNum", likeNum);
				userList.put("commentNum", commentNum);
				
				userBlog.add(userList);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("总用户数："+userNum);
		System.out.println("总博客数："+blogNum);
		
		try {
			wsheet.addCell(new jxl.write.Number(1, 0, userNum));
			wsheet.addCell(new jxl.write.Number(5, 0, blogNum)); 
			// 写入数据并关闭文件  
			book.write();  
			book.close();
		} catch (RowsExceededException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (WriteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		JSONObject message = new JSONObject();
		try {
			message.put("userNum", userNum);
			message.put("blogNum", blogNum);
			message.put("userBlog", userBlog);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.println(message.toString());
	}

}
