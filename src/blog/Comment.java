package blog;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DB;

/**
 * Servlet implementation class Comment
 */
@WebServlet("/Comment")
public class Comment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Comment() {
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
		
		//获取blog_id
		int blog_id = Integer.parseInt(request.getParameter("id"));
		//获取reply_id
		int reply_id = 0;
		
		String sql1 = "INSERT INTO blog_comment(blog_id, person, time, reply_id) VALUES('"+blog_id+"','"+user_name+"','"+time+"','"+reply_id+"')";
		db.query1(sql1);
		
		if(reply_id == 0){
			//评论该篇微博,评论数+1
			String sql2 = "UPDATE blog SET comment=comment+1 WHERE id='"+blog_id+"'";
			db.query1(sql2);
		}
		
	}
}
