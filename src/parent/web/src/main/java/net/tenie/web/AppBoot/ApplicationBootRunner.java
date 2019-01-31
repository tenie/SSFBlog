package net.tenie.web.AppBoot;
 
import java.sql.Connection; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import net.tenie.web.db.ConnectionPool;
import net.tenie.web.scheduled.ClearExpireAccessInfo;
import net.tenie.web.tools.ToolsLib;

@Service
public class ApplicationBootRunner implements InitializingBean {
	Logger logger = LoggerFactory.getLogger(ClearExpireAccessInfo.class);
	 
	
	@Value("${net.tenie.h2.boot.port}")
	private String port;
	
	@Value("${net.tenie.url}")
	private String url ;
	@Value("${net.tenie.username}")
	private String user ;
	@Value("${net.tenie.password}")
	private String pw ;
	
	
	@Override
	public  void afterPropertiesSet() throws Exception {
		Connection conn = null;
		try {
			logger.info("初始化工作");
			// 启动h2数据库
			String useCmd  ="java -cp "+ ApplicationBootRunner.class.getResource("/").toString()+"../lib/h2-1.4.197.jar org.h2.tools.Server -tcp -tcpPort "+port;
			ConnectionPool.startDBServer(url,user,pw,useCmd);  
			// 获取脚本路径
			String path =ApplicationBootRunner.class.getResource("/").toString()+"sql.sql";  //"file:/Users/tenie/gitHome/Learning_Notes/java/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/web/WEB-INF/classes/sql.sql";
			path = path.split(":")[1];
			logger.info(path);
			// 获取建表语句
			String sql = ToolsLib.getText(path);
			String[] sqls = sql.split(";");  
			// 创建表(如果需要的话)
		    conn = ConnectionPool.getDirectConn(url,user,pw);
	        boolean isExists = ToolsLib.tableExists("blog",conn); 
	    	if(!isExists){
	    		ToolsLib.createTable(sqls,conn);
	    	}
		} catch (Exception e) { 
			e.printStackTrace();  
        } finally {
				ConnectionPool.releaseConnection(conn,  null, null);
            } 
 
}

	 

	public static void main(String[] args) throws Exception {
		System.out.println("??");
		// ApplicationBootRunner.gettext("./");
		 String path =
		 ApplicationBootRunner.class.getResource("/").toString()+"../lib";
		 System.out.println("path = " + path);

//		new ApplicationBootRunner().afterPropertiesSet();

		// System.out.println(file.getPath());
		
//		String path ="/Users/tenie/gitHome/Learning_Notes/java/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/web/WEB-INF/classes";
//		 
//		 
//		 Runtime rt = Runtime.getRuntime();  
//		 Process p = rt.exec(path);
//		  InputStream is = p.getInputStream();  
//		    BufferedReader br = new BufferedReader(new InputStreamReader(is));  
//		    StringBuffer sbf = new StringBuffer();  
//		    String tmp = "";  
//		    while ((tmp = br.readLine()) != null){  
//		       sbf.append(tmp+"\n"); 
//		       
//		   }
//		    System.out.println(sbf);

//		 BufferedReader in = null; ;
//		 InputStream  is =  p.getInputStream();
//		 
//         in = new BufferedReader(new InputStreamReader(is,"UTF-8"));   // 逐行检查输出,计算类似出现=23ms TTL=62字样的次数  
//         
//         String  line = in.readLine();   
//         while ((line = in.readLine()) != null) {    
//             System.out.println(line);
//         }    
		 
		 
	}

}
