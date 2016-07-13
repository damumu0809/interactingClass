package issueVote;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		//session
		HttpSession session = request.getSession();
		String name = session.getAttribute("username").toString();
		//中文乱码
		request. setCharacterEncoding("utf-8");
		String option1 = null,option2 = null,option3 = null,option4 = null,option5 = null,option6 = null;
		int multipleChoice = 0;
		long expireTime = 0, issueTime = 0;
		
		
		//获取投票主题
		String theme = request.getParameter("theme");		
	System.out.println("theme:"+theme);	
		if(!theme.isEmpty()){ //不能用 == null
			//选项
			option1 = request.getParameter("option1");
			option2 = request.getParameter("option2");
			option3 = request.getParameter("option3");
			option4 = request.getParameter("option4");
			option5 = request.getParameter("option5");
			option6 = request.getParameter("option6");
		System.out.println(option3);
			if(!option1.isEmpty()&&!option2.isEmpty()){
				//单选多选
				String choice = request.getParameter("multipleChoice");
				if(!choice.isEmpty()){
					multipleChoice = Integer.parseInt(choice);
					System.out.println("multiplechoice:"+multipleChoice);
					
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
					expireTime = expiretime.getTime();
				System.out.println(expireTime);
					//发布时间
					Date time = new Date();
					issueTime = time.getTime();
				System.out.println(issueTime);
					
					if(expireTime <= issueTime){
						//过期时间有误
						out.println("<script type='text/javascript'>alert('请选择正确的过期时间！')</script>");
						out.println("<script type='text/javascript'>window.location.href='./index.jsp?page=1';</script>");
					}else{
						//写入数据库
						DB db = new DB();
						String sqlInsert = "INSERT INTO vote(theme, issuePerson, issueTime, expireTime, option1, number1, option2, number2, option3, number3, option4, number4, option5, number5, option6, number6, multipleChoice) "
								+ "VALUES(\""+theme+"\", \""+name+"\", \""+issueTime+"\", \""+expireTime+"\", \""+option1+"\", 0, \""+option2+"\", 0, \""+option3+"\", 0, \""+option4+"\", 0, \""+option5+"\", 0, \""+option6+"\", 0, "+multipleChoice+")";
					System.out.println(sqlInsert);
						db.query1(sqlInsert);
					System.out.println("success");
						
					out.print("<script type='text/javascript'>alert('发布成功！');window.location.href='./index.jsp?page=1';</script>");
						
					}
				}else{
					out.println("<script>alert('未确定投票类型');</script>");
					out.println("<script type='text/javascript'>window.location.href='./index.jsp?page=1';</script>");
				}
		
			}else{
				out.println("<script>alert('投票至少两个选项不能为空！');</script>");
				out.println("<script type='text/javascript'>window.location.href='./index.jsp?page=1';</script>");
			}
			
		}else{
			out.println("<script type='text/javascript'>alert('投票主题不能为空！');window.location.href='./index.jsp?page=1';</script>");
		}
		
		
		
		
	}
	
}
