package blog;

import java.io.IOException;
import java.util.Date;

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
 * Servlet implementation class Like
 */
@WebServlet("/Like")
public class Like extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Like() {
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
		System.out.println(session.getAttribute("username"));
		String user_name = session.getAttribute("username").toString();
		
		Date date = new Date();
		long time = date.getTime();
		
		//获取blog_id
		int blog_id = Integer.parseInt(request.getParameter("id"));
		System.out.println(blog_id);
		
		//插入blog_like表 每条点赞记录
		String sql1 = "INSERT INTO blog_like(blog_id, like_person, like_time) VALUES ('"+blog_id+"','"+user_name+"','"+time+"')";
		db.query1(sql1);
		
		//blog表中点赞数+1
		String sql2 = "UPDATE blog SET blog.like=blog.like+1 WHERE id="+blog_id+"";
		db.query1(sql2);
		System.out.println("点赞成功");
		
		JSONObject message = new JSONObject();
		try {
			message.put("code", 0);
			message.put("mg", "success");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println(message.toString());
		
	}

}
