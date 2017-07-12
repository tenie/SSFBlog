package net.tenie.web.interceptor;

import java.io.Writer;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
//
//import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import net.tenie.web.pojo.Result;
import net.tenie.web.session.LoginSession;
import net.tenie.web.session.SessionUtil;
import net.tenie.web.tools.ApplicationContextHelper;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
//		 HandlerMethod method = (HandlerMethod)handler;  
		//JsonGenerator jsonGenerator = jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(System.out, JsonEncoding.UTF8);
		ObjectMapper objectMapper =new ObjectMapper();
		
		HttpServletRequest httpServletRequest =   request;
        //获取请求的URL
        String path = httpServletRequest.getRequestURI();
        LoginSession loginInfo = SessionUtil.getSession();//ApplicationContextHelper.getBeanByType(LoginSession.class);
        loginInfo.setUrl(path);
        System.out.println(loginInfo);
     
		if(path.indexOf("/submitPublishdata") >=0
		  || path.indexOf("/updatePublishdata") >=0
		   ||path.indexOf("/hiddenContent/") >=0
		   ){  
			if(loginInfo.getIsLog()!=null && loginInfo.getIsLog()){
				System.out.println("....登入过....");
				return true;
			}else{
				System.out.println("....没有登入过....");
				//response.setCharacterEncoding("utf-8");
				response.setHeader("Content-Type", "application/json;charset=UTF-8");
				//out.print("nologin");
				Result rs=new Result();
				rs.setError(true);
				rs.setMsg("请先登入~");
				JsonGenerator   jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(response.getOutputStream(),JsonEncoding.UTF8);
				
				jsonGenerator.writeObject(rs);
				return false;
			}
		}
		return true; 
		
	}
}
