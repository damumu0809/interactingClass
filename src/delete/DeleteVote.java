package delete;

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
 * Servlet implementation class DeleteVote
 */
@WebServlet("/DeleteVote")
public class DeleteVote extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteVote() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				response.setContentType("text/html;charset=utf-8");
				java.io.PrintWriter out = response.getWriter( );
				
				JSONObject message = new JSONObject();
				
				//判断username
				HttpSession session = request.getSession();
				String username = session.getAttribute("username").toString();
				System.out.println(username);
				String issuePerson = request.getParameter("issuePerson");
				System.out.println(issuePerson);
				if(username.equals(issuePerson)){
					//可以删除
					System.out.println("可以删除");
					DB db = new DB();
					int voteID = Integer.parseInt(request.getParameter("voteid"));
					String sql = "DELETE FROM vote WHERE id =" + voteID;
					System.out.println("要删除投票id"+voteID);
					db.query1(sql);
					System.out.println("删除成功！");
					try {
						message.put("code", 0);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						message.put("sign", "删除成功");
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				else{
					try {
						message.put("code", 1);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				out.println(message.toString());
	}

}
