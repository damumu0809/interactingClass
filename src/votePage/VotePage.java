package votePage;

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

import org.json.JSONException;
import org.json.JSONObject;

import database.DB;

/**
 * Servlet implementation class VotePage
 */
@WebServlet("/VotePage")
public class VotePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VotePage() {
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
		java.io.PrintWriter out = response.getWriter( );	
		
		//读取数据库投票记录
		DB db = new DB();
		String sql1 = "SELECT * FROM vote order by id desc";
		String sql2 = "SELECT * FROM voteRecord";
		
		//投票id、主题、选项及票数、单选多选
		String theme;
		int voteId;
		String option1, option2, option3, option4, option5, option6;
		int number1, number2, number3, number4, number5, number6;
		int multipleChoice;
		
		//记录是否已投票
		boolean hasVoted = false;
		//投票发布时间、过期时间、发布人
		String issuePerson;
		long issueTime, expireTime;
		String issueTimef, expireTimef;
		boolean hasExpired = false;
		
		List allVote =  new ArrayList();
		JSONObject vote ;
		
		Date date = new Date();
		long time = date.getTime();

		ResultSet rs1 = db.query2(sql1);
		try {
			while(rs1.next()){
				
				voteId = rs1.getInt("id");
				theme = rs1.getString("theme");
				option1 = rs1.getString("option1");
				option2 = rs1.getString("option2");
				option3 = rs1.getString("option3");
				option4 = rs1.getString("option4");
				option5 = rs1.getString("option5");
				option6 = rs1.getString("option6");
				number1 = rs1.getInt("number1");
				number2 = rs1.getInt("number2");
				number3 = rs1.getInt("number3");
				number4 = rs1.getInt("number4");
				number5 = rs1.getInt("number5");
				number6 = rs1.getInt("number6");
				multipleChoice = rs1.getInt("multipleChoice");
				issuePerson = rs1.getString("issuePerson");
				issueTime = rs1.getLong("issueTime");
				expireTime = rs1.getLong("expireTime");
				
				Date issueTime1 = new Date(issueTime);
				Date expireTime1 = new Date(expireTime);
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				issueTimef = format.format(issueTime1);
				expireTimef = format.format(expireTime1);
			System.out.println(issueTimef);
			System.out.println(expireTimef);
				
				if(time > expireTime){
					//已过期显示投票结果
					vote = new JSONObject();
					hasExpired = true;
					vote.put("theme", theme);
					vote.put("voteId", voteId);
					vote.put("option1", option1);
					vote.put("option2", option2);
					vote.put("option3", option3);
					vote.put("option4", option4);
					vote.put("option5", option5);
					vote.put("option6", option6);
					vote.put("number1", number1);
					vote.put("number2", number2);
					vote.put("number3", number3);
					vote.put("number4", number4);
					vote.put("number5", number5);
					vote.put("number6", number6);
					vote.put("multipleChoice", multipleChoice);
					vote.put("issuePerson", issuePerson);
					vote.put("issueTime", issueTimef);
					vote.put("expireTime", expireTimef);
					vote.put("hasExpired", hasExpired);
					System.out.println("已过期");
					System.out.println(vote);
				}else{
				//未过期
					System.out.println("未过期");
				//查询是否已投票
				sql2 = "SELECT * FROM voteRecord WHERE voteId="+voteId+" AND person=\"xiaomu\"";
			System.out.println(sql2);
				ResultSet rs2 = db.query2(sql2);
				
				if(rs2.next()){
					//已投票显示投票结果
					vote = new JSONObject();
					hasVoted = true;
					vote.put("theme", theme);
					vote.put("voteId", voteId);
					vote.put("option1", option1);
					vote.put("option2", option2);
					vote.put("option3", option3);
					vote.put("option4", option4);
					vote.put("option5", option5);
					vote.put("option6", option6);
					vote.put("number1", number1);
					vote.put("number2", number2);
					vote.put("number3", number3);
					vote.put("number4", number4);
					vote.put("number5", number5);
					vote.put("number6", number6);
					vote.put("multipleChoice", multipleChoice);
					vote.put("hasVoted", hasVoted);
					vote.put("issuePerson", issuePerson);
					vote.put("issueTime", issueTimef);
					vote.put("expireTime", expireTimef);
				}else {
					//未投票显示投票
					hasVoted = false;
					vote = new JSONObject();
					vote.put("theme", theme);
					vote.put("voteId", voteId);
					vote.put("option1", option1);
					vote.put("option2", option2);
					vote.put("option3", option3);
					vote.put("option4", option4);
					vote.put("option5", option5);
					vote.put("option6", option6);
					vote.put("multipleChoice", multipleChoice);
					vote.put("hasVoted", hasVoted);
					vote.put("issuePerson", issuePerson);
					vote.put("issueTime", issueTimef);
					vote.put("expireTime", expireTimef);
				}
				System.out.println(vote);
			}
			allVote.add(vote);
			}
			JSONObject message = new JSONObject();
			message.put("code", 0);
			message.put("list", allVote);
			
			out.println(message.toString());
			//在前端根据multipleChoice来加载单选还是多选框
		
		}catch (SQLException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
