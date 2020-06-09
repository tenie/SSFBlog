package net.tenie.web.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 
import org.h2.jdbcx.JdbcConnectionPool; 
import net.tenie.web.tools.ToolsLib;
 
public class ConnectionPool {
	 
	  
    private static ConnectionPool cp = null;
    private JdbcConnectionPool jdbcCP = null; 
    private ConnectionPool(String url, String user, String pw) { 
        jdbcCP = JdbcConnectionPool.create(url, user, pw);
        jdbcCP.setMaxConnections(50);
    }
     
    private static ConnectionPool getInstance(String pt , String us , String pw) {
        if (cp == null) {
            cp = new ConnectionPool(pt, us, pw);
        }
        return cp;
    } 
    
    public Connection getConnection() throws SQLException {
        return jdbcCP.getConnection();
    }
     
    public static Connection getDirectConn(String pt , String us , String pw) throws SQLException {
    	 if (cp == null) {
    		 getInstance(pt, us, pw);
         }
    	
        return cp.getConnection();
    }
    
    /**
     * 释放资源
     * @param conn
     * @param stmt
     * @param rs
     * @throws SQLException
     */
    public static void releaseConnection(Connection conn, Statement stmt,  ResultSet rs) throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
    
    
    
   /**
    * 测试db服务器是否开启
    * @param pt
    * @param us
    * @param pw
    * @return
    */
   public static boolean  testDBserver(String pt , String us , String pw) {
	   try {
			 Connection conn =  ConnectionPool.getDirectConn(pt, us, pw); //ConnectionPool.getInstance("jdbc:h2:tcp://localhost:9092/~/config/test", "sa", "123456").getConnection();
			 conn.close();
			 return true;
		} catch (Exception e) {
			System.out.println("error..");
		}
		  
		  System.out.println("OK...");
	 
	   return false;
   }
   
   /**
    * 启动数据库服务器
    * @param pt
    * @param us
    * @param pw
    * @param cmd
    * @return
    */
   public static boolean startDBServer (String pt , String us , String pw, String cmd) {
	   boolean isrun = testDBserver(pt, us, pw);
	   if(!isrun){
		   try {
				ToolsLib.ExecCmd(cmd);
				return true;
			} catch (IOException e) {
				 
				e.printStackTrace();
			} catch (InterruptedException e) {
				 
				e.printStackTrace();
			}
	   } 
		
		return false;  
   }
   
    
    

}