package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
	// JDBC 驱动器名称和数据库的 URL
    static final String JDBC_DRIVER="com.mysql.jdbc.Driver";  

    static final String DB_URL="jdbc:mysql://localhost/w_xm01?useUnicode=true&characterEncoding=UTF-8" ;
    //static final String DB_URL="jdbc:mysql://localhost/w_xm01?charset=utf-8";

    //static final String DB_URL="jdbc:mysql://localhost/w_xm01?charset=utf-8";
  //  static final String DB_URL="jdbc:mysql://localhost/interacting?useUnicode=true&characterEncoding=UTF-8";
  

    //  数据库的凭据
    static final String USER = "root";
    static final String PASS = "root";
    
    
    Connection conn=null;
    Statement stmt=null;
    PreparedStatement ps=null;
    
    public DB(){
    	
    }
    public boolean query1(String sql){
    	try{
        	// 注册 JDBC 驱动器
            Class.forName("com.mysql.jdbc.Driver");

            // 打开一个连接
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            
            // 执行 SQL 查询
            stmt = conn.createStatement();
            boolean rs =  stmt.execute(sql);
            return rs;

         
//            // 清理环境
//            rs.close();
//            stmt.close();
//            conn.close();
         }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
         }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
         }finally{
//            // 最后是用于关闭资源的块
//            try{
//               if(stmt!=null)
//                  stmt.close();
//            }catch(SQLException se2){
//            }// 我们不能做什么
//            try{
//               if(conn!=null)
//               conn.close();
//            }catch(SQLException se){
//               se.printStackTrace();
//            }//end finally try
         } //end try
		return true;
    	
    }
    
    public ResultSet query2(String sql){
    	try{
        	// 注册 JDBC 驱动器
            Class.forName("com.mysql.jdbc.Driver");

            // 打开一个连接
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            
            // 执行 SQL 查询
            stmt = conn.createStatement();
            ResultSet rs =  stmt.executeQuery(sql);
            return rs;

         
//            // 清理环境
//            rs.close();
//            stmt.close();
//            conn.close();
         }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
         }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
         }finally{
//            // 最后是用于关闭资源的块
//            try{
//               if(stmt!=null)
//                  stmt.close();
//            }catch(SQLException se2){
//            }// 我们不能做什么
//            try{
//               if(conn!=null)
//               conn.close();
//            }catch(SQLException se){
//               se.printStackTrace();
//            }//end finally try
         } //end try
    	return null;
    }	
    
    //返回刚插入的id
    	public int query3(String sql){
        	try{
            	// 注册 JDBC 驱动器
                Class.forName("com.mysql.jdbc.Driver");

                // 打开一个连接
                conn = DriverManager.getConnection(DB_URL,USER,PASS);
                
                // 执行 SQL 查询
                ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS );
                ps.executeUpdate();
                ResultSet rs =ps.getGeneratedKeys();
                final int ID_COLUMN_INDEX=1;
                //没有指针是取不到rs的值的
                while(rs.next()){
                	return rs.getInt(ID_COLUMN_INDEX );
                }
                
             
//                // 清理环境
//                rs.close();
//                stmt.close();
//                conn.close();
             }catch(SQLException se){
                // 处理 JDBC 错误
                se.printStackTrace();
             }catch(Exception e){
                // 处理 Class.forName 错误
                e.printStackTrace();
             }finally{
//                // 最后是用于关闭资源的块
//                try{
//                   if(stmt!=null)
//                      stmt.close();
//                }catch(SQLException se2){
//                }// 我们不能做什么
//                try{
//                   if(conn!=null)
//                   conn.close();
//                }catch(SQLException se){
//                   se.printStackTrace();
//                }//end finally try
             } //end try
        	return 0;
    	
    }
}