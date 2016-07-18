package blog;

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
		}
		System.out.println("总用户数："+userNum);
		System.out.println("总博客数："+blogNum);
		
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
