package net.tenie.myblog;

import java.sql.Connection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cn.hutool.core.io.resource.ClassPathResource; 
import net.tenie.myblog.db.MyConnection;
import net.tenie.myblog.tools.ToolsLib; 
import net.tenie.myblog.tools.YmlUtil;


@SpringBootApplication
public class MyblogApplication {

	public static void main(String[] args) throws Exception {
		BDCheck();
		SpringApplication.run(MyblogApplication.class, args);
	}
	
	 
	public static  void BDCheck() throws Exception { 
		
		Connection conn =   MyConnection.getConnection();
//		Path p= Paths.get("");
		try { 
			System.out.println("初始化工作");   
			// 启动h2数据库// 
			if(conn == null) {
				String port = YmlUtil.getValue("application.yml", "net.tenie.h2.boot.port").toString();
				String h2jar =  YmlUtil.getValue("application.yml", "net.tenie.h2.boot.h2jar").toString();
				
				String useCmd  = "java -cp "+ h2jar +" org.h2.tools.Server -tcp -tcpPort "+port;
				
				ToolsLib.ExecCmd(useCmd);
			    conn =   MyConnection.getConnection();
			} 
 
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
        	MyConnection.closeConnection(conn);
        } 
 
}

}
