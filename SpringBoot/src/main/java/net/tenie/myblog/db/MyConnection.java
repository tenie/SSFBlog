package net.tenie.myblog.db;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 
import net.tenie.myblog.tools.YmlUtil;
 
 
public class MyConnection {
	  static Logger log = LoggerFactory.getLogger(MyConnection.class);  
	  static String driver; 
	  static String url; 
      static String user; 
      static String pass;
	  private static java.sql.Connection sqlConnection;
	 
	 

	 public static  java.sql.Connection getConnection() throws Exception {
	         if(sqlConnection != null) return sqlConnection;
	         String driver =  
		        	 YmlUtil.getValue("application.yml","spring.datasource.driver-class-name").toString();
	         String url = 
	        		  YmlUtil.getValue("application.yml","spring.datasource.url").toString();
	        String user =
	        		 YmlUtil.getValue("application.yml","spring.datasource.username").toString();
	        String pass =
	        		  YmlUtil.getValue("application.yml","spring.datasource.password").toString();

		     
	        try {
	            Class.forName(driver);
	            sqlConnection =  DriverManager.getConnection(url, user, pass);
	        } catch (ClassNotFoundException | SQLException e) {
	            log.error(e.getMessage());
	        }

	        return sqlConnection;
	    }

	    public static  void closeConnection(java.sql.Connection sqlConnection) {
	        try {
	            sqlConnection.close();
	        } catch (SQLException e) {
	            log.error(e.getMessage());
	        }
	    }
}
