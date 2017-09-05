package net.tenie.web.aop;
 
import java.sql.Connection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.javalite.activejdbc.DB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.tenie.web.entity.AccessInfo;
import net.tenie.web.service.CecheResult;
import net.tenie.web.session.LoginSession;
import net.tenie.web.session.SessionUtil; 

@Component   //将其纳入SpringIOC
@Aspect       //声明是一个方面组件类
public class OperateLogger{
	@Autowired
	private BasicDataSource ds;
	//前置,后置,最终通知写法
@Before("within(net.tenie.web.controller.*)")  //表达式表示要作用在哪些目标的上,这里作用到了所以的Controller类上了
 public void log1(){ 
	LoginSession loginInfo = SessionUtil.getSession();
	AccessInfo info = new AccessInfo();
	info.setDate(new Date());
	info.setHost(loginInfo.getHost()); 
	info.setUserAgent(loginInfo.getUserAgent());
	String sessionid = loginInfo.getSessionId();
	String key = loginInfo.getHost() + (sessionid!=null?sessionid:"");
	CecheResult.addSession(key, info);
}
 //环绕  
 @Transactional(timeout=2)
 @Around("within(net.tenie.web.controller.*)")  //表达式表示要作用在哪些目标的上,这里作用到了所以的Controller类上了
 public Object  log1(ProceedingJoinPoint p)throws Throwable{
	DB db = null; 
	try {  
		 /**
		  * 在环绕AOP中使用ActiveJDBC
		  */ 
 
		 db =  new DB(); 
	     db.open(ds);   
		 db.openTransaction(); 
	    //目标方法的调用
		 Object obj = p.proceed(); 
		 //判断那些url下需要清空缓存
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
  //异常通知
 //@AfterThrowing(pointcut="(net.tenie.web.controller..*)",throwing="e")
 public void log3(Exception e){
     StackTraceElement[] elems = e.getStackTrace();
            //…
 }
 
}