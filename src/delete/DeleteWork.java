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
 * Servlet implementation class DeleteWork
 */
@WebServlet("/DeleteWork")
public class DeleteWork extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteWork() {
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
		
		//判断身份为教师
		HttpSession session = request.getSession();
		String identity = session.getAttribute("identity").toString();
		JSONObject message = new JSONObject();
		if(identity.equals("教师")){
			int workid = Integer.parseInt(request.getParameter("workId"));
			System.out.println("要删除第几次作业"+ workid);
			
			//删除作业
			DB db = new DB();
			String sql = "DELETE FROM IssueWork WHERE id="+workid;
			boolean rs = db.query1(sql);
			System.out.println(rs); //为false 不是表示是否成功 而是表示指针所指的值
			System.out.println("删除成功！");
			try {
				message.put("code", 0);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				message.put("sign", "删除成功！");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			if(rs){
//				System.out.println("删除成功！");
//				try {
//					message.put("code", 0);
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				try {
//					message.put("sign", "删除成功！");
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}else{
//				System.out.println("删除失败！");
//				try {
//					message.put("code", 1);
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				try {
//					message.put("sign", "删除失败！");
//				} catch (JSONException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//			}
			
			//删除所有提交作业的记录
			//可以不删	
		}
		else{
			
			try {
				message.put("code", 1);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				message.put("sign", "删除失败！");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		out.println(message.toString());
	}

	
}
