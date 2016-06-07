package onloadPage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
		//session
		HttpSession session = request.getSession();
		String username = session.getAttribute("username").toString();
		
        DB db = new DB();
		String sqlSelect = "SELECT * FROM issueWork order by id desc";
		ResultSet rs1 = db.query2(sqlSelect);
		
		int taskNum;
		String theme;
		String href;
		String name;
		String time = null;//发布时间
		String deadLine = null;//deadLine
		List hrefs_files = new ArrayList<>();
		JSONObject href_file; 
		
		JSONObject work;
		List allWork = new ArrayList<>();
		
		try {
			while(rs1.next()){
				
				taskNum = rs1.getInt("id");
				theme = rs1.getString("theme");
				//转换格式
				Date time1 = new Date(rs1.getLong("time"));
				Date deadLine1 = new Date(rs1.getLong("deadLine"));
				SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				time = ft.format(time1);
				deadLine = ft.format(deadLine1);
				
				String sql = "SELECT * FROM homework WHERE taskNum="+taskNum+"";
				System.out.println(sql);
				ResultSet rs2 = db.query2(sql);
				
				hrefs_files.clear();
				while(rs2.next()){
					href = rs2.getString("href"); 
				System.out.println(href);
					name = rs2.getString("file_name");
					href_file = new JSONObject();
					href_file.put("href", href);
					href_file.put("file", name);
					hrefs_files.add(href_file);	
				}
				
				work = new JSONObject();
				work.put("taskNum",taskNum);
				work.put("theme", theme);
				work.put("time", time);
	    	    work.put("deadLine", deadLine);
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
