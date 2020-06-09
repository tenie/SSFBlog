package net.tenie.myblog.interceptor;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.tenie.myblog.entity.Result;
import net.tenie.myblog.session.LoginSession;
import net.tenie.myblog.session.SessionUtil;
 
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {  
		ObjectMapper objectMapper =new ObjectMapper();  
        //获取请求的URL
        String path = request.getRequestURI();
        System.out.println(path);
        LoginSession loginInfo = SessionUtil.getSession(); 
        String host = request.getHeader("host"); 
        loginInfo.setHost(host); 
        loginInfo.setIp(host.substring(0, host.indexOf(":")));
        loginInfo.setSessionId(request.getRequestedSessionId());  
        String client =  request.getHeader("user-agent");
        loginInfo.setUserAgent(client);
        
        loginInfo.setUrl(path);
        
        //权限控制
		if(   path.indexOf("/submitPublishdata") >=0  
		   || path.indexOf("/updatePublishdata") >=0
		   || path.indexOf("/article/delete") >=0
		   || path.indexOf("/article/publicContent") >=0
		   || path.indexOf("/article/hiddenContent") >=0  
		   || path.indexOf("/article/setTop") >=0 
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
	/**
	 * 可以修改视图
	 */
	@Override	
	public void postHandle(    
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)    
            throws Exception {    
    } 
	/**
	 * 可以根据ex做log
	 */
	@Override
    public void afterCompletion(    
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)    
            throws Exception {
		 
		 
		
    }    
}
