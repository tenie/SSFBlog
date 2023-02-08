package net.tenie.myblog;

import java.sql.Connection;
import java.sql.SQLException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.h2.tools.Server;
import cn.hutool.core.io.resource.ClassPathResource; 
import net.tenie.myblog.db.MyConnection;
import net.tenie.myblog.tools.ToolsLib; 


@SpringBootApplication
public class MyblogApplication {
	private static Server h2Server ;
	public static void main(String[] args) throws Exception {
		BDCheck();
		SpringApplication.run(MyblogApplication.class, args);
	}

//	@Bean(initMethod = "getStatus", destroyMethod = "stop")
//    public Server h2Server() throws SQLException {
//		 System.out.println("h2Server ??? " + h2Server.getStatus());
//		 return h2Server;
//	}
	 
	public static  void BDCheck() throws Exception { 
		Connection  conn = null;
		try { 
			System.out.println("初始化工作");   
			// 启动h2数据库//  
//			h2Server = Server.createTcpServer("-tcp", "-ifNotExists", "-tcpAllowOthers", "-tcpPort", "9092").start();
			h2Server = org.h2.tools.Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092").start();

			conn =   MyConnection.getConnection();
 
	        boolean isExists = ToolsLib.tableExists("blog",conn); 
	    	if(!isExists){
	    		// 获取脚本路径
	    		ClassPathResource resource = new ClassPathResource("db/h2_createTable.sql");
	    		String sql = ToolsLib.getText(resource.getStream()); ;
				String[] sqls = sql.split(";");   
	    		ToolsLib.createTable(sqls,conn);
	    	}
		} catch (Exception e) { 
			e.printStackTrace();  
        } finally {
        	if(conn !=null)
        		MyConnection.closeConnection(conn);
        } 
 
}

}
