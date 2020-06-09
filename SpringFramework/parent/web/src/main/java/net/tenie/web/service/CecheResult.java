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
	private static String ip;
	private static ConcurrentHashMap<String, AccessInfo> sessionMap = new ConcurrentHashMap<String, AccessInfo>();
	private static String vpnserver;
	private static int blogcount;
	
	
	
	
	public static int getBlogcount() {
		return blogcount;
	}

	public static void setBlogcount(int blogcount) {
		CecheResult.blogcount = blogcount;
	}

	public static String getVpnserver() {
		return vpnserver;
	}

	public static void setVpnserver(String vpnserver) {
		CecheResult.vpnserver = vpnserver;
	}

	public static String getIp() {
		return ip;
	}

	public static void setIp(String ip) {
		CecheResult.ip = ip;
	}

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
		int index = 0;
		for(Entry<String, AccessInfo> entry: vals) { 
			
			AccessInfo info =  entry.getValue();
			rs += "("+ ++index+") clinet ip :"+entry.getKey();
			rs +="; accessCount:"+ info.getAccessCount();
			rs +="; host:"+ info.getHost();
			rs +="; userAgent:"+ info.getUserAgent();
			rs +=": data:"+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(info.getDate()) + "<br/>\n";
		}
		
		return  rs;
	}
	
	 
	public static void addSession(String key,AccessInfo value) {
		if(key ==null ) return; 
		if(sessionMap.containsKey(key)) { 
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
		Set<Entry<String, AccessInfo>> vals = sessionMap.entrySet();
		for(Entry<String, AccessInfo> entry: vals) { 
			AccessInfo info =    entry.getValue();  
			info.getDate();
			if(now.getTime().getTime() > info.getDate().getTime()) {
				logger.info("clear session ="+entry.getKey());
				sessionMap.remove(entry.getKey());
			
				
			}
		}
	}
	 
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
		CecheResult.logincacheRS = logincacheRS; 
	}
	public static Result getCacheRS() {
		return cacheRS;
	}
	public static void setCacheRS(Result cache) {
		 cacheRS = cache; 
	}
	public static ConcurrentHashMap<String, AccessInfo>  getSessionMap() {
		return sessionMap;
	}
//	public static void setSessionMap(ConcurrentHashMap sessionMap) {
//		CecheResult.sessionMap = sessionMap;
//	}
//	
	/**
	 * 设置登入缓存和非登入缓存
	 * @param islogin
	 * @param rs
	 */
	public static void  setRs(boolean islogin, Result rs) {
		
		if (islogin ) {
			CecheResult.setLogincacheRS(rs);
		} else if (!islogin) {
			CecheResult.setCacheRS(rs);
		} 
		
	}
	/**
	 * 获取缓存
	 * @param islogin
	 * @param rs
	 * @return
	 */
	public static Result getRs(boolean islogin, Result rs){
		if (islogin) {
			Result logincacheRS = getSignIncacheRS();
			if (logincacheRS != null) {
				rs = logincacheRS;
			}
		} else {
			Result cacheRS = getCacheRS();
			if (cacheRS != null) {
				rs = cacheRS;
			}
		} 
		
		return rs;
	}
	
	
}
