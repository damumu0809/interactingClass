package onloadPage;

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
 * Servlet implementation class StudentPage
 */
@WebServlet("/StudentPage")
public class StudentPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentPage() {
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
		response.setContentType("text/html;charset=utf-8");
		java.io.PrintWriter out = response.getWriter( );		
		
		//
		DB db = new DB();
		String sqlSelect = "SELECT * FROM issueWork";
		
		ResultSet rs1 = db.query2(sqlSelect);
		
		try {
			int taskNum = 0; //第几次作业
			String theme = null; //作业内容
			boolean finish = false; //是否已提交
			String name = null;//作业名称
			String href = null; //提交之后的链接
			
			JSONObject work;
			List allWork = new ArrayList();
			
			while(rs1.next()){
				taskNum = rs1.getInt("id");
				theme = rs1.getString("theme");
				
				//查询是否提交该次作业
				String sql = "SELECT * FROM homework WHERE taskNum=\""+taskNum+"\" AND owner='xiaomu'";
				ResultSet rs2 = db.query2(sql);
				if(rs2.next()){
					//已提交
					finish = true;
					href = rs2.getString("href");
					name = rs2.getString("file_name");
				}else {
					//未提交
					finish = false;
				}
				work = new JSONObject();
	    	    work.put("taskNum", taskNum);
	    	    work.put("theme", theme);
	    	    work.put("finish", finish);
	    	    work.put("href", href);
	    	    work.put("name", name);
	    	    //out.println(work.toString());
	    	    
	    	    allWork.add(work);
	    	    
			}
			
			JSONObject message = new JSONObject();
			message.put("list", allWork);
			message.put("code", 0);
			
			out.println(message.toString());
			
		} catch (SQLException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

	}

}
