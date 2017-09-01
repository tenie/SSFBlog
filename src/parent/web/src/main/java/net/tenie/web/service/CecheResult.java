package net.tenie.web.service;
 
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import net.tenie.web.entity.AccessInfo;
import net.tenie.web.entity.Result;


public class CecheResult {
	
	private static Result logincacheRS;
	private static Result cacheRS;
	
	private static Calendar calendar; 
	
	private static ConcurrentHashMap<String, AccessInfo> sessionMap = new ConcurrentHashMap<String, AccessInfo>();
	
	public static void main(String[] args) {
		Calendar now = Calendar.getInstance();  
		now.set(Calendar.HOUR, 0);
		now.set(Calendar.MINUTE , 0);
		now.set(Calendar.SECOND, 0);;
		System.out.println( 
				new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(now.getTime())
				);
		 
	}
	
	
	//sessionID 加入到Map
	public static void addSession(String key,AccessInfo value) {
		//如果已经在里面了
		if(sessionMap.containsKey(key)) {
//			Map m = sessionMap.get(key); 
		}else {
			sessionMap.put(key, value);
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
