package net.tenie.web.interceptor;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import net.tenie.web.pojo.Result;
import net.tenie.web.session.LoginSession;
import net.tenie.web.session.SessionUtil; 

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
