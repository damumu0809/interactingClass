package blog;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DB;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
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
		//doGet(request, response);
		response.setContentType("text/html;charset=utf-8");
		java.io.PrintWriter out = response.getWriter( );
		
		String user = request.getParameter("user");
		System.out.println("正在查找："+user);
		
		DB db = new DB();
		String sql = "SELECT * FROM userinfo WHERE username = '"+user+"' ";
		ResultSet rs = db.query2(sql);
		try {
			if(!rs.next()){
				out.println("<script>alert('该用户不存在！');window.location.href='./blog.html';</script>");
			}else{
				sql = "SELECT id FROM blog WHERE user_name = '"+user+"' ";
				rs = db.query2(sql);
				int id = 0;
				try {
					while(rs.next()){
						id = rs.getInt("id");
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(id == 0){
					//该用户没有发表博客
					//在blog表里面找不到
					out.println("<script>alert('该用户没有发表任何博客！');window.location.href='./blog.html';</script>");
				}
				out.println("<script>window.location.href='./personal.jsp?id="+id+"';</script>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}

}
