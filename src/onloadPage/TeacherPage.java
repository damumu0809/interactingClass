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
 * Servlet implementation class TeacherPage
 */
@WebServlet("/TeacherPage")
public class TeacherPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherPage() {
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
		
        DB db = new DB();
		String sqlSelect = "SELECT * FROM issueWork";
		ResultSet rs1 = db.query2(sqlSelect);
		
		int taskNum;
		String theme;
		String href;
		String name;
		List hrefs_files = new ArrayList<>();
		JSONObject href_file; 
		
		JSONObject work;
		List allWork = new ArrayList<>();
		
		try {
			while(rs1.next()){
				
				taskNum = rs1.getInt("id");
				theme = rs1.getString("theme");
				
				String sql = "SELECT * FROM homework WHERE taskNum="+taskNum+"";
				ResultSet rs2 = db.query2(sql);
				while(rs2.next()){
					href = rs2.getString("href"); 
					name = rs2.getString("file_name");
					href_file = new JSONObject();
					href_file.put("href", href);
					href_file.put("file", name);
					hrefs_files.add(href_file);
					
				}
				
				work = new JSONObject();
				work.put("taskNum",taskNum);
				work.put("theme", theme);
				work.put("hrefs_files", hrefs_files);
				allWork.add(work);
			}	
			
			JSONObject message = new JSONObject();
			message.put("code", 0);
			message.put("list", allWork);
			
			out.println(message.toString());
			
		} catch (SQLException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
	}

}
