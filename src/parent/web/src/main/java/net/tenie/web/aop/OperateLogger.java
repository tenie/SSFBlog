package net.tenie.web.aop;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component   //将其纳入SpringIOC
@Aspect       //声明是一个方面组件类
public class OperateLogger{
	//前置,后置,最终通知写法
@Before("within(net.tenie.web.controller.PublishDataController)")  //表达式表示要作用在哪些目标的上,这里作用到了所以的Controller类上了
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
 //@Around("within(com.trarens.controller..*)")  //表达式表示要作用在哪些目标的上,这里作用到了所以的Controller类上了
 public Object  log1(ProceedingJoinPoint p)throws Throwable{
    //…
    //目标方法的调用
  Object obj = p.proceed();
     //…
     return obj;
}
  //异常通知
 //@AfterThrowing(pointcut="(net.tenie.web.controller..*)",throwing="e")
 public void log3(Exception e){
     StackTraceElement[] elems = e.getStackTrace();
            //…
 }
}