package issueWork;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DB;

/**
 * Servlet implementation class IssueWork
 */
@WebServlet("/IssueWork")
public class IssueWork extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IssueWork() {
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
		//中文乱码问题
		request.setCharacterEncoding("utf-8");
		
		response.setContentType("text/html;charset=utf-8");
		java.io.PrintWriter out = response.getWriter( );
		
		//获取作业主题和deadline时间
		String theme = request.getParameter("theme");
	System.out.println(theme);
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		
		int dYear = Integer.parseInt(year);
		int dMonth = Integer.parseInt(month);
		int dDay = Integer.parseInt(day);
		Date deadLine = new Date(dYear - 1900, dMonth - 1, dDay);
		SimpleDateFormat ft1 = new SimpleDateFormat ("yyyy.MM.dd");
		String deadline = ft1.format(deadLine);
	System.out.println(deadline);
		
		//发布时间
		Date date = new Date();
		SimpleDateFormat ft2 = new SimpleDateFormat ("yyyy.MM.dd HH:mm:ss");
    	String issueTime = ft2.format(date);
    	
    	//连接数据库
    	DB db = new DB();
    	String insert = "INSERT INTO IssueWork(theme, deadline, time) VALUES(\""+theme+"\", \""+deadline+"\",\""+issueTime+"\")";
    System.out.println(insert);
    	db.query1(insert);

    	out.print("<script type='text/javascript'>alert('发布成功！');window.location.href='./TeacherPage';</script>");
	}

}
