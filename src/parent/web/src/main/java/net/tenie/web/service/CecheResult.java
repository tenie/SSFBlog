package net.tenie.web.service;
 
import net.tenie.web.pojo.Result;


public class CecheResult {
	
	private static Result logincacheRS;
	private static Result cacheRS;
	
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
	
	
	
}
