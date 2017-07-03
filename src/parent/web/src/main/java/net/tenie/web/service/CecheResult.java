package net.tenie.web.service;
 
import net.tenie.web.pojo.Result;


public class CecheResult {
	
	private static Result SignIncacheRS;
	private static Result cacheRS;
	
	//设置为null
	public static void setNullCacheRS(){
		cacheRS= null;
	}
	public static void setNullSignIncacheRS(){
		SignIncacheRS= null;
	}
	
	public static Result getSignIncacheRS() {
		return SignIncacheRS;
	}
	public static void setSignIncacheRS(Result signIncacheRS) {
		SignIncacheRS = signIncacheRS; 
	}
	public static Result getCacheRS() {
		return cacheRS;
	}
	public static void setCacheRS(Result cache) {
		 cacheRS = cache; 
	}
	
	
	
}
