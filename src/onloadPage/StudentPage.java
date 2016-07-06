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
		
		//session
				HttpSession session = request.getSession();
				String username = session.getAttribute("username").toString();
		
		int page = Integer.parseInt(request.getParameter("page"));
		int count = 0;		
		
		DB db = new DB();
		String sqlAll = "SELECT * FROM issueWork";
		
		ResultSet rs = db.query2(sqlAll);
		try {
			while(rs.next()){
				count++;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("作业总数："+count);
		//总页数
		int pages = 1;
		pages = count%5==0 ? count/5 :count/5+1;//每页五条记录
		System.out.println("pages:"+pages);
		int first = (page -1)*5;
		int last = page*5;
		String sqlSelect = "SELECT * FROM issueWork order by id desc LIMIT "+first+","+last+"";
		ResultSet rs1 = db.query2(sqlSelect);
		
		try {
			int taskNum = 0; //第几次作业
			String theme = null; //作业内容
			boolean finish = false; //是否已提交
			String name = null;//作业名称
			String href = null; //提交之后的链接
			String time = null;//发布时间
			String deadLine = null;//deadLine
			boolean hasExpired = false;
			
			
			
			Date uploadTime = new Date();
			long timeNow = uploadTime.getTime();
			
			JSONObject work;
			List allWork = new ArrayList();
			
			while(rs1.next()){
				taskNum = rs1.getInt("id");
				theme = rs1.getString("theme");
				//转换格式
				Date time1 = new Date(rs1.getLong("time"));
				Date deadLine1 = new Date(rs1.getLong("deadLine"));
				SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				time = ft.format(time1);
				deadLine = ft.format(deadLine1);
				
				//判断是否过期
				if(timeNow > rs1.getLong("deadLine")){
					//过期  
					hasExpired = true;
					//查询是否提交该次作业
					String sql = "SELECT * FROM homework WHERE taskNum=\""+taskNum+"\" AND owner=\""+username+"\"";
					ResultSet rs2 = db.query2(sql);
					if(rs2.next()){
						//已提交
						finish = true;
						href = rs2.getString("href");
						name = rs2.getString("file_name");
					}else {
						//未提交不显示提交框
						finish = false;
					}
				}else{
					hasExpired = false;
					//查询是否提交该次作业
					String sql = "SELECT * FROM homework WHERE taskNum=\""+taskNum+"\" AND owner=\""+username+"\"";
					
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
				}
				
				
				work = new JSONObject();
	    	    work.put("taskNum", taskNum);
	    	    work.put("time", time);
	    	    work.put("deadLine", deadLine);
	    	    work.put("theme", theme);
	    	    work.put("finish", finish);
	    	    work.put("href", href);
	    	    work.put("name", name);
	    	    work.put("hasExpired", hasExpired);
	    	    //out.println(work.toString());
	    	    
	    	    allWork.add(work);
	    	    
			}
			
			JSONObject message = new JSONObject();
			message.put("list", allWork);
			message.put("code", 0);
			message.put("pages", pages);
			message.put("count", count);
			
			out.println(message.toString());
			
		} catch (SQLException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

	}

}
