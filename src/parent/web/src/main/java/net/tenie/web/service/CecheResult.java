package net.tenie.web.service;
 
import java.util.Calendar;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import net.tenie.web.entity.Result;


public class CecheResult {
	
	private static Result logincacheRS;
	private static Result cacheRS;
	
	private static Calendar calendar;
	
	private static ConcurrentHashMap<String, Map> sessionMap = new ConcurrentHashMap<String, Map>();
	
	//sessionID 加入到Map
	public static void addSession(String key,Map value) {
		//如果已经在里面了
		if(sessionMap.containsKey(key)) {
//			Map m = sessionMap.get(key); 
		}else {
			sessionMap.put(key, value);
		}
		
		if(calendar !=null ) {
			Calendar now = Calendar.getInstance();
			
		}
		//删除前一天的session
		Set<Entry<String, Map>> vals = sessionMap.entrySet();
		for(Entry entry: vals) { 
			Map rsM = (Map) entry.getValue();
			rsM.get("Time");
			//TODO 如果时间是昨天的就删除;
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
