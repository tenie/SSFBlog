package net.tenie.web.interceptor;

import java.io.Writer;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import net.tenie.web.session.LoginSession;
import net.tenie.web.tools.ApplicationContextHelper;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
//		 HandlerMethod method = (HandlerMethod)handler;  
		 
		 
		HttpServletRequest httpServletRequest =   request;
        //获取请求的URL
        String path = httpServletRequest.getRequestURI();
        System.out.println(path);
		if(path.indexOf("/submitPublishdata") >=0){
			System.out.println("进入..");
			LoginSession loginInfo = ApplicationContextHelper.getBeanByType(LoginSession.class);
			 
			if(loginInfo.getIsLog()!=null && loginInfo.getIsLog()){
				System.out.println("....登入过....");
				return true;
			}else{
				System.out.println("....没有登入过....");
				response.setCharacterEncoding("utf-8");
				java.io.PrintWriter	out = response.getWriter();
				out.print("nologin");
				return false;
			}
		}
		return true; 
		
	}
}
