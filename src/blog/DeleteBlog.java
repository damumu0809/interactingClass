package blog;

import java.io.IOException;

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
 * Servlet implementation class DeleteBlog
 */
@WebServlet("/DeleteBlog")
public class DeleteBlog extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteBlog() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		response.setContentType("text/html;charset=utf-8");
		java.io.PrintWriter out = response.getWriter( );
		
		JSONObject message = new JSONObject();
		
		HttpSession session = request.getSession();
		String username = session.getAttribute("username").toString();
		System.out.println(username);
		String user = request.getParameter("user");
		System.out.println(user);
		
		if(username.equals(user)){
			//可以删除
			DB db = new DB();
			int id = Integer.parseInt(request.getParameter("id"));
			String sql = "DELETE FROM blog WHERE id = '"+id+"'";
			db.query1(sql);
			sql = "DELETE FROM blog_accessory WHERE blog_id = '"+id+"'";
			db.query1(sql);
			sql = "DELETE FROM blog_like WHERE blog_id = '"+id+"'";
			db.query1(sql);
			sql = "DELETE FROM blog_comment WHERE blog_id = '"+id+"'";
			db.query1(sql);
			
			try {
				message.put("code",0);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			try {
				message.put("code",1);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		out.println(message.toString());
	}

}
