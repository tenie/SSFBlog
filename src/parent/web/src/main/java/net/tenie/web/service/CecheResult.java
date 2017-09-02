package net.tenie.web.service;
 
import java.text.SimpleDateFormat;
import java.util.Calendar; 
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 
import net.tenie.web.entity.AccessInfo;
import net.tenie.web.entity.Result; 


public class CecheResult {
	private static Logger logger = LoggerFactory.getLogger(CecheResult.class); 
	private static Result logincacheRS;
	private static Result cacheRS;   
	private static ConcurrentHashMap<String, AccessInfo> sessionMap = new ConcurrentHashMap<String, AccessInfo>();
	
	
	
	
	public static void main(String[] args) {
		Calendar now = Calendar.getInstance();  
		now.set(Calendar.HOUR, 0);
		now.set(Calendar.MINUTE , 0);
		now.set(Calendar.SECOND, 0);;
		logger.info( 
				new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(now.getTime())
				);
		 
	}
	
	public static String sessionMapToString(){
		String rs ="";
		Set<Entry<String, AccessInfo>> vals = sessionMap.entrySet();
		for(Entry entry: vals) { 
			
			AccessInfo info = (AccessInfo) entry.getValue();
			rs +="sessionID:"+entry.getKey();
			rs +="; host:"+ info.getHost();
			rs +="; userAgent:"+ info.getUserAgent();
			rs +=": data:"+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(info.getDate()) + "<br/>\n";
		}
		
		return  rs;
	}
	
	
	//sessionID 加入到Map
	public static void addSession(String key,AccessInfo value) {
		//如果已经在里面了
		if(sessionMap.containsKey(key)) {
//			Map m = sessionMap.get(key); 
		}else {
			sessionMap.put(key, value);
			logger.info("add new sessionMap ==" + key);
		} 
		
	}
	
	public static void rmSeeion(Calendar now) {
		if(now ==null) {
			 now = Calendar.getInstance(); 
			 now.set(Calendar.HOUR, 0);
			 now.set(Calendar.MINUTE , 0);
			 now.set(Calendar.SECOND, 0); 
		}
	    
		//删除前一天的session
		Set<Entry<String, AccessInfo>> vals = sessionMap.entrySet();
		for(Entry entry: vals) { 
			AccessInfo info =   (AccessInfo) entry.getValue(); 
			//TODO 如果时间是昨天的就删除;
			info.getDate();
			if(now.getTime().getTime() > info.getDate().getTime()) {
				logger.info("clear session ="+entry.getKey());
				sessionMap.remove(entry.getKey());
			
				
			}
		}
	}
	
	//设置为null
	public static void setNullCacheRS(){
		cacheRS= null;
	}
	public static void setNullLogincacheRS(){
		logincacheRS= null;
	}
	
	public static Result getSignIncacheRS() {
		return logincacheRS;
	}
	public static void setLogincacheRS(Result logincacheRS) {
		logincacheRS = logincacheRS; 
	}
	public static Result getCacheRS() {
		return cacheRS;
	}
	public static void setCacheRS(Result cache) {
		 cacheRS = cache; 
	}
	public static ConcurrentHashMap getSessionMap() {
		return sessionMap;
	}
//	public static void setSessionMap(ConcurrentHashMap sessionMap) {
//		CecheResult.sessionMap = sessionMap;
//	}
//	
	
	
}
