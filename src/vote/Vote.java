package vote;

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
 * Servlet implementation class Vote
 */
@WebServlet("/Vote")
public class Vote extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Vote() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		request. setCharacterEncoding("utf-8");
		java.io.PrintWriter out = response.getWriter( );	
		
		//连接数据库
		DB db = new DB();
		
		int voteId = Integer.parseInt(request.getParameter("voteId"));
		int multipleChoice = Integer.parseInt(request.getParameter("multipleChoice"));
		if(multipleChoice == 0){
			//单选
			String option = request.getParameter("vote");
			int optionId = (int)(option.charAt(6))-48;
		System.out.println(optionId);
			String optionNum = "number"+optionId;
			
			Date date = new Date();
	      	//时间格式转换
	      	SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd HH:mm:ss");
	      	String time = ft.format(date);
			
			//写进数据库 1.在vote表里票数加一 2.voteRecord表里添加此次投票记录
			String sql1 = "UPDATE vote SET "+optionNum+"="+optionNum+"+1 WHERE id=\""+voteId+"\" ";
		System.out.println(sql1);
			db.query1(sql1);
			
			String sql2 = "INSERT INTO voteRecord(voteId, optionId, voteTime, person) VALUES("+voteId+","+optionId+",\""+time+"\",\"xiaomu\")";
			System.out.println(sql2);
			db.query1(sql2);
		}else{
			//多选
			
			
		}
		
		
		
		
		
		
	}

}
