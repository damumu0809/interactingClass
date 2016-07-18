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
 * Servlet implementation class PersonalPage
 */
@WebServlet("/PersonalPage")
public class PersonalPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonalPage() {
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
		
		//通过blogid找user 查询该user的所有blog
		//统计发表博客总数 图片博客多少  视频博客多少 音频博客多少2
		//显示所有博客不分页
		DB db = new DB();
		

		
		int blogId = Integer.parseInt(request.getParameter("user"));
		String user = null;
		if(blogId == 0){
			//访问自己的中心
			HttpSession session = request.getSession();
			System.out.println(session.getAttribute("username"));
			user = session.getAttribute("username").toString();
		}else{
			String sql1 = "SELECT user_name FROM blog WHERE id= '"+blogId+"' ";
			ResultSet rs1 = db.query2(sql1);
			
			try {
				while(rs1.next()){
					user = rs1.getString("user_name");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		System.out.println("正在访问"+user+"的博客中心");
		
		String sql2 = "SELECT * FROM blog WHERE user_name = '"+user+"'  ORDER BY id DESC";
		//String sql4 = "SELECT COUNT(*) FROM blog WHERE user_name = '"+user+"'";
		//count 语句用现有的query2执行不了
		ResultSet rs2 = db.query2(sql2);
		//ResultSet rs4  = db.query2(sql4);
		
		int blogNum = 0;
		int pictureNum = 0;
		int musicNum = 0;
		int videoNum = 0;
//		try {
//			 blogNum = rs4.getInt(0);
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		//System.out.println("微博总数为："+ blogNum);
		
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
		
		
		JSONObject message = new JSONObject();
		
		try {
			while(rs2.next()){
				blogNum++;
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
					String sql3 = "SELECT * FROM blog_accessory  WHERE blog_id='"+id+"'";
					ResultSet rs3 = db.query2(sql3);
					while(rs3.next()){
						accessory = new JSONObject();
						type = rs3.getString("type");
						href = rs3.getString("href");
						 
						System.out.println(type);
						System.out.println(href);
						
						if(type.equals("video"))
							videoNum++;
						if(type.equals("picture"))
							pictureNum++;
						if(type.equals("music"))
							musicNum++;
						
						accessory.put("type", type);
						accessory.put("href", href);
						
						allAcc.add(accessory);
					}
					
				}
				blog.put("acc", allAcc);
				allBlog.add(blog);
			}
			System.out.println("总博客数"+blogNum);
			System.out.println("图片"+pictureNum);
			System.out.println("视频"+videoNum);
			System.out.println("音频"+musicNum);
			
			message.put("code", 0);
			message.put("list", allBlog);
			message.put("blog", blogNum);
			message.put("picture", pictureNum);
			message.put("video", videoNum);
			message.put("music", musicNum);
			message.put("user", user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		System.out.println(message.toString());
		out.println(message.toString());
	
	}

}
