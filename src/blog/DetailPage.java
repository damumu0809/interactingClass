package blog;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
 * Servlet implementation class DetailPage
 */
@WebServlet("/DetailPage")
public class DetailPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailPage() {
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
		
//		HttpSession session = request.getSession();
//		System.out.println(session.getAttribute("username"));
//		String user_name = session.getAttribute("username").toString();
		
		//获取blog_id
		int blog_id = Integer.parseInt(request.getParameter("id"));
		System.out.println(blog_id);
		
		//上面部分展示就是blogPage  text全篇展示
		String sql1 = "SELECT * FROM blog WHERE id ='"+blog_id+"'";
		ResultSet rs1 = db.query2(sql1);
		int like,comment,hasAcc = 0;
		String name, topic, text = null;
		long time = 0;
		String issueTime;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String type, href = null;//附件信息
		
		JSONObject blog = new JSONObject();
		JSONObject accessory = null;
		JSONObject blogComment = null;
		List allAcc = null;
		List allLike = null;
		List allComment = null;
		
		try {
			while(rs1.next()){
				name = rs1.getString("user_name");
				time = rs1.getLong("time");
				issueTime = format.format(time);
				topic = rs1.getString("topic");
				text = rs1.getString("text");
				like = rs1.getInt("like");
				comment = rs1.getInt("comment");
				hasAcc = rs1.getInt("hasAcc");
				
				blog.put("id", blog_id);
				blog.put("user_name", name);
				blog.put("issueTime",issueTime);
				blog.put("topic", topic);
				blog.put("text", text);
				blog.put("like",like);
				blog.put("comment", comment);
				blog.put("hasAcc", hasAcc);
				
				if(hasAcc == 1){
					//有附件 查询表blog_accessary
					allAcc = new ArrayList<>();
					String sql2 = "SELECT * FROM blog_accessory WHERE blog_id='"+blog_id+"'";
					ResultSet rs2 = db.query2(sql2);
					while(rs2.next()){
						accessory = new JSONObject();
						type = rs2.getString("type");
						href = rs2.getString("href");
						 
						System.out.println(type);
						System.out.println(href);
						
						accessory.put("type", type);
						accessory.put("href", href);
						
						allAcc.add(accessory);
					}	
				}
				blog.put("allAcc", allAcc);
				
				if(like >= 1){
					//有赞显示所有点赞的人
					allLike = new ArrayList<>();
					String like_person = null;
					String sql3 = "SELECT * FROM blog_like WHERE blog_id ='"+blog_id+"' ";
					ResultSet rs3 = db.query2(sql3);
					
					while(rs3.next()){
						like_person = rs3.getString("like_person");
						System.out.println(like_person);
						
						allLike.add(like_person);
					}
					blog.put("allLike", allLike);
				}
				
				
				if(comment >= 1){
					//有评论，显示所有评论，包括对评论的回复
					allComment = new ArrayList<>();
					
					//查询对博客的评论
					String sql4 = "SELECT * FROM blog_comment WHERE blog_id = '"+blog_id+"' AND reply_id = 0";
					ResultSet rs4 = db.query2(sql4);
					//评论者、评论时间、评论内容
					int comment_id = 0;
					String comment_person = null;
					long comment_time = 0;
					String commentTime = null;
					String content = null;
					List allReply = null;
					JSONObject reply = null;
					while(rs4.next()){
						blogComment = new JSONObject();
						
						comment_id = rs4.getInt("id"); //评论id用来确定后面的回复
						comment_person = rs4.getString("person");
						comment_time = rs4.getLong("time");
						commentTime = format.format(comment_time);
						content = rs4.getString("content");
						
						blogComment.put("comment_id", comment_id);
						blogComment.put("person", comment_person);
						blogComment.put("content", content);
						blogComment.put("time", commentTime);
						
						
						
						allReply = new ArrayList<>();
						//查询每条评论的回复reply
						String sql5 = "SELECT * FROM blog_comment WHERE blog_id = '"+blog_id+"' AND reply_id ='"+comment_id+"' ";
						ResultSet rs5 = db.query2(sql5);
						String reply_person = null;
						String reply_content = null;
						long reply_time = 0;
						String replyTime = null;
						while(rs5.next()){
							reply = new JSONObject();
							reply_person = rs5.getString("person");
							reply_content = rs5.getString("content");
							reply_time = rs5.getLong("time");
							replyTime = format.format(reply_time);
						
							reply.put("person", reply_person);
							reply.put("time", replyTime);
							reply.put("content", reply_content);
							
							allReply.add(reply);
						}
						blogComment.put("reply", allReply);
						allComment.add(blogComment);
					}	
					blog.put("allComment", allComment);
				}
				
				
			}
			
			
			JSONObject message = new JSONObject();
			message.put("code", 0);
			message.put("blog", blog);
			out.println(message.toString());
			
			
			
			
		} catch (SQLException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
