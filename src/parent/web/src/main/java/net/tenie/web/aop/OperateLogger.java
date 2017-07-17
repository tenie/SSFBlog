package net.tenie.web.aop;
 
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
import org.springframework.stereotype.Component;

import net.tenie.web.service.CecheResult;
import net.tenie.web.session.LoginSession;
import net.tenie.web.session.SessionUtil;
import net.tenie.web.tools.ApplicationContextHelper;

@Component   //将其纳入SpringIOC
@Aspect       //声明是一个方面组件类
public class OperateLogger{
	//前置,后置,最终通知写法
@Before("within(net.tenie.web.controller.*)")  //表达式表示要作用在哪些目标的上,这里作用到了所以的Controller类上了
 public void log1(){
//	Boolean  log = (Boolean) request.getSession().getAttribute("log");
//	if(log !=null && log){
//		System.out.println("登入过");
//	}else{
//		System.out.println("没有登入过");
//	}
 	System.out.println("Aop......");
}
 //环绕
 @Around("within(net.tenie.web.controller.*)")  //表达式表示要作用在哪些目标的上,这里作用到了所以的Controller类上了
 public Object  log1(ProceedingJoinPoint p)throws Throwable{
	
	try {  
		 /**
		  * 在环绕AOP中使用ActiveJDBC
		  */
		System.out.println("环绕AOP");
		 DataSource ds = (DataSource) ApplicationContextHelper.getBeanByType(BasicDataSource.class);
		 new DB().open(ds);
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
		 
		 
	     return obj;
		}catch(Exception e){
			System.out.println("**************************");
			e.printStackTrace();
			System.out.println("**************************");
			throw e;
		}
		finally {
			new DB().close();
		} 
	
}
  //异常通知
 //@AfterThrowing(pointcut="(net.tenie.web.controller..*)",throwing="e")
 public void log3(Exception e){
     StackTraceElement[] elems = e.getStackTrace();
            //…
 }
}