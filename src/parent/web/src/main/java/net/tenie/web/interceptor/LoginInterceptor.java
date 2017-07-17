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
		ObjectMapper objectMapper =new ObjectMapper(); 
		HttpServletRequest httpServletRequest =   request;
        //获取请求的URL
        String path = httpServletRequest.getRequestURI();
        LoginSession loginInfo = SessionUtil.getSession();//ApplicationContextHelper.getBeanByType(LoginSession.class);
        loginInfo.setUrl(path); 
		if(   path.indexOf("/submitPublishdata") >=0
		   || path.indexOf("/updatePublishdata") >=0
		   || path.indexOf("/article/delete") >=0
		   || path.indexOf("/article/publicContent") >=0
		   || path.indexOf("/article/hiddenContent") >=0
		   ){  
			if(loginInfo.getIsLog()!=null && loginInfo.getIsLog()){ 
				return true;
			}else{
				//在必须要登入的url下,没有登入返回错误信息
				response.setHeader("Content-Type", "application/json;charset=UTF-8"); 
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
