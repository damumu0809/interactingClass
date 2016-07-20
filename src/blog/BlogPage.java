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
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import database.DB;

/**
 * Servlet implementation class BlogPage
 */
@WebServlet("/BlogPage")
public class BlogPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlogPage() {
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
		
		JSONObject message = new JSONObject();
		
		HttpSession session = request.getSession();
		if(session.getAttribute("username") == null){
			try {
				System.out.println("未登录");
				message.put("code", 1);//未登录
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
		
		DB db = new DB();
		
		
		
		//blog表中总记录数
		int count = 0;
		String sql1 = "SELECT * FROM blog";
		ResultSet rs = db.query2(sql1);
		try {
			while(rs.next()){
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		System.out.println(count);
		
		
		//总页数
		int pages = 1;
		pages = count%5==0 ? count/5 :count/5+1;//每页五条记录
		System.out.println("pages:"+pages);
		
		//查询该页的记录
		int page = Integer.parseInt(request.getParameter("page"));
		System.out.println("page:"+page);
		int first = (page -1)*5;
		int last = page*5;
		String sql2 = "SELECT * FROM blog ORDER BY id DESC LIMIT "+first+","+last+"";
		System.out.println(sql2);
		ResultSet rs2 = db.query2(sql2);
		
		//blog页面需要显示的有
		//作者、标题、文字内容的前50字、发表时间、点赞数、评论数
		//附件若有图片显示为滑动浏览、视频、音频展示都放在blog页面
		int id,like,comment,hasAcc = 0;
		String user_name, topic, text = null;
		long time = 0;
		String issueTime;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String type, href = null;//附件信息
		
		JSONObject blog = null;
		JSONObject accessory = null;
		List allBlog = new ArrayList<>();
		List allAcc = null;
		
		
		
		try {
			while(rs2.next()){
				id = rs2.getInt("id");
				user_name = rs2.getString("user_name");
				time = rs2.getLong("time");
				issueTime = format.format(time);
				topic = rs2.getString("topic");
				text = rs2.getString("text");
				like = rs2.getInt("like");
				comment = rs2.getInt("comment");
				hasAcc = rs2.getInt("hasAcc");
			
				blog = new JSONObject();
				blog.put("id", id);
				blog.put("user_name", user_name);
				blog.put("issueTime",issueTime);
				blog.put("topic", topic);
				blog.put("text", text);
				blog.put("like",like);
				blog.put("comment", comment);
				blog.put("hasAcc", hasAcc);
				
				if(hasAcc == 1){
					//有附件 查询表blog_accessary
					allAcc = new ArrayList<>();
					String sql3 = "SELECT * FROM blog_accessory WHERE blog_id='"+id+"'";
					ResultSet rs3 = db.query2(sql3);
					while(rs3.next()){
						accessory = new JSONObject();
						type = rs3.getString("type");
						href = rs3.getString("href");
						 
						System.out.println(type);
						System.out.println(href);
						
						accessory.put("type", type);
						accessory.put("href", href);
						
						allAcc.add(accessory);
					}
					
				}
				blog.put("acc", allAcc);
				allBlog.add(blog);
			}
			
			message.put("code", 0);
			message.put("list", allBlog);
			message.put("pages",pages);
			
			
			
		} catch (SQLException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		System.out.println(message.toString());
		out.println(message.toString());
		
	}
	

}
