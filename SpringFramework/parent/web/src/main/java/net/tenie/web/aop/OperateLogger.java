package net.tenie.web.aop;
  
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap; 
import org.apache.commons.dbcp.BasicDataSource;
import org.aspectj.lang.ProceedingJoinPoint; 
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.javalite.activejdbc.DB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component; 
import org.springframework.transaction.annotation.Transactional;

import net.tenie.web.entity.AccessInfo;
import net.tenie.web.service.CecheResult;
import net.tenie.web.session.LoginSession;
import net.tenie.web.session.SessionUtil; 

@Component    
@Aspect     
public class OperateLogger{
	@Autowired
	private BasicDataSource ds; 
@Before("within(net.tenie.web.controller.*)")   
 public void log1(){ 
	LoginSession loginInfo = SessionUtil.getSession();
	AccessInfo info = new AccessInfo();
	info.setDate(new Date());
	info.setHost(loginInfo.getHost()); 
	info.setUserAgent(loginInfo.getUserAgent()); 
	String key = loginInfo.getIp() ;
	info.setIp(key);
	
	ConcurrentHashMap<String, AccessInfo> sessionMap = CecheResult.getSessionMap();
	if(sessionMap.size() > 0) {
		AccessInfo ai = sessionMap.get(key);
		if(ai != null ) {
			ai.setDate(new Date());
			ai.setHost(loginInfo.getHost());
			ai.setAccessCount(  ai.getAccessCount() + 1);
			ai.setUserAgent(loginInfo.getUserAgent());
			ai.setIp(loginInfo.getIp());
		}else {
			info.setAccessCount(0L);
			CecheResult.addSession(key, info);
		}
	}else {
		info.setAccessCount(0L);
		CecheResult.addSession(key, info);
	}
	
} 
 @Transactional(timeout=2)
 @Around("within(net.tenie.web.controller.*)")   
 public Object  log1(ProceedingJoinPoint p)throws Throwable{
	DB db = null; 
	try {   
		 db =  new DB(); 
	     db.open(ds);   
		 db.openTransaction();  
		 Object obj = p.proceed();  
		 LoginSession loginInfo = SessionUtil.getSession();
		 String url = loginInfo.getUrl();
		 if( url.indexOf("/updatePublishdata") >=0
		   ||url.indexOf("/submitPublishdata") >=0
		   ||url.indexOf("/article/delete") >=0
		   ||url.indexOf("/pageTitle/delete") >=0
		   ||url.indexOf("/pageTitle/publicContent") >=0 
		   ||url.indexOf("/pageTitle/hiddenContent") >=0 
		   ||url.indexOf("/logout") >=0   
		   ){
			 CecheResult.setNullLogincacheRS();
			 CecheResult.setNullCacheRS();
		 }
		 
		 
		  db.commitTransaction(); 
	     return obj;
		}catch(Exception e){
			e.printStackTrace(); 
			if(db!=null){
			 	db.rollbackTransaction();  
			}
			
			throw e;
		}
		finally { 	
			if(db!=null){
				db.close(); 
			}
			
		} 
	
}
 
 
}