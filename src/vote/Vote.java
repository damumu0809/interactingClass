package vote;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		java.io.PrintWriter out = response.getWriter( );	
		//session
		HttpSession session = request.getSession();
		String name = session.getAttribute("username").toString();
		
		//连接数据库
		DB db = new DB();
		
		int voteId = Integer.parseInt(request.getParameter("voteId"));
		int multipleChoice = Integer.parseInt(request.getParameter("multipleChoice"));
		
		//判断是否过期
		Date date = new Date();
      	long time = date.getTime();
      	String sql  = "SELECT * FROM vote WHERE id ="+voteId;
      	ResultSet rs = db.query2(sql);
      	try {
      		while(rs.next()){
      			if(time > rs.getLong("expireTime")){
    				//过期
    				System.out.println("过期！不能投票");
    				out.print("<script type='text/javascript'>alert('过期不能参与投票！');window.location.href='./index.html';</script>");
    			}else{
    				if(multipleChoice == 0){
    					//单选
    					String option = request.getParameter("vote");
    					System.out.println(option);
    					if(option==null){
    						out.print("<script type='text/javascript'>alert('请选择一个选项！');window.location.href='./index.jsp?page=1';</script>");
    					}else{
    					int optionId = (int)(option.charAt(6))-48;
    				System.out.println(optionId);
    					String optionNum = "number"+optionId;
    					
    					//写进数据库 1.在vote表里票数加一 2.voteRecord表里添加此次投票记录
    					String sql1 = "UPDATE vote SET "+optionNum+"="+optionNum+"+1 WHERE id=\""+voteId+"\" ";
    				System.out.println(sql1);
    					db.query1(sql1);
    					
    					String sql2 = "INSERT INTO voteRecord(voteId, optionId, voteTime, person) VALUES("+voteId+","+optionId+",\""+time+"\",\""+name+"\")";
    					System.out.println(sql2);
    					db.query1(sql2);
    					out.print("<script type='text/javascript'>alert('投票成功！');window.location.href='./index.jsp?page=1';</script>");
    					}
    				}else{
    					//多选
    					//未选项为null 已选项为value
    					int optionId[] = new int[6];
    					int i = 1, j=0;
    					String optionNum;
    					for(i=1,j=0; i <=6; i++){
    						if(request.getParameter("check"+i)!= null){
    							optionId[j]=i;
    							j++;
    						}
    					}
    					if(j==0){
    						//没有选择任何一项
    						out.print("<script type='text/javascript'>alert('请选择至少一个选项！');window.location.href='./index.jsp?page=1';</script>");
    					}else{
    				  	String sql1;
    				  	String sql2;
    				  	for(j=0; j<6; j++){
    				  		if(optionId[j]!=0){
    				  			//值为选项num
    				  			optionNum = "number"+optionId[j];
    				  			sql1 = "UPDATE vote SET "+optionNum+"="+optionNum+"+1 WHERE id="+voteId+"";
    				  			System.out.println(sql1);
    							db.query1(sql1);
    							
    							sql2 = "INSERT INTO voteRecord(voteId, optionId, voteTime, person) VALUES("+voteId+","+optionId[j]+",\""+time+"\",\""+name+"\")";
    							System.out.println(sql2);
    							db.query1(sql2);
    							out.print("<script type='text/javascript'>alert('投票成功！');window.location.href='./index.jsp?page=1';</script>");
    				  		}
    				  	}
    					}
    				}
    				
    			}
    			
      			
      		}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
	}

}
