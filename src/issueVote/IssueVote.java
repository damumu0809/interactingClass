package issueVote;

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
 * Servlet implementation class IssueVote
 */
@WebServlet("/IssueVote")
public class IssueVote extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IssueVote() {
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
		
		//获取投票主题
		String theme = request.getParameter("theme");
	System.out.println(theme);	
		//选项
		String option1 = request.getParameter("option1");
		String option2 = request.getParameter("option2");
		String option3 = request.getParameter("option3");
		String option4 = request.getParameter("option4");
		String option5 = request.getParameter("option5");
		String option6 = request.getParameter("option6");
	System.out.println(option1);
		
		//过期时间
		int year = Integer.parseInt(request.getParameter("year"));
		int month = Integer.parseInt(request.getParameter("month"));
		int day = Integer.parseInt(request.getParameter("day"));
		int hour = Integer.parseInt(request.getParameter("hour"));
		int minute = Integer.parseInt(request.getParameter("minute"));

		System.out.println(year);
		System.out.println(day);
		int second = 0;
		Date expiretime = new Date(year-1900, month-1, day, hour, minute, second);
		SimpleDateFormat ft1 = new SimpleDateFormat ("yyyy.MM.dd HH:mm");
		String expireTime = ft1.format(expiretime);
	System.out.println(expireTime);
		
		//单选多选
		int multipleChoice = Integer.parseInt(request.getParameter("multipleChoice"));
		
		//发布时间
		Date time = new Date();
		SimpleDateFormat ft2 = new SimpleDateFormat ("yyyy.MM.dd HH:mm:ss");
		String issueTime = ft2.format(time);
	System.out.println(issueTime);
		
		//写入数据库
		DB db = new DB();
		String sqlInsert = "INSERT INTO vote(theme, issuePerson, issueTime, expireTime, option1, number1, option2, number2, option3, number3, option4, number4, option5, number5, option6, number6, multipleChoice) "
				+ "VALUES(\""+theme+"\", \"teacher\", \""+issueTime+"\", \""+expireTime+"\", \""+option1+"\", 0, \""+option2+"\", 0, \""+option3+"\", 0, \""+option4+"\", 0, \""+option5+"\", 0, \""+option6+"\", 0, "+multipleChoice+")";
	System.out.println(sqlInsert);
		db.query1(sqlInsert);
	System.out.println("success");
		
	out.print("<script type='text/javascript'>alert('发布成功！');window.location.href='./index.html';</script>");
	}

}
