package net.tenie.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.tenie.web.entity.Result;
import net.tenie.web.service.VerificationCode;
import net.tenie.web.session.LoginSession;
import net.tenie.web.session.SessionUtil;
import net.tenie.web.tools.ApplicationContextHelper;

@Controller
public class LoginInController {

	 
	@Value("${ssfblog.username}")
	private String userName;
	
	@Value("${ssfblog.password}")
	private String pwd;
	
	
	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	@ResponseBody
	public Result signIn(HttpServletRequest request, HttpServletResponse response,@RequestParam Map<String, String> queryParam) throws ServletException, IOException{
     
      String name = queryParam.get("name");
      String password = queryParam.get("password");
      String code = queryParam.get("code"); 
      //为空返回失败
      if(name ==null &&   "".equals(name)){
    	  return new Result(true,"登入失败");
      }
      LoginSession session=SessionUtil.getSession();
      String secode =  session.getImageCode();
      session.setImageCode("");//清空验证码
      if( !"".equals(secode)&&code.equalsIgnoreCase(secode)){ 
	      if( userName.equals(name) && pwd.equals(password)){
	 		 LoginSession loginInfo = ApplicationContextHelper.getBeanByType(LoginSession.class);  
	 		 loginInfo.setIsLog(true);
	 		 
	 	 	 return new Result("登入成功~");
	       }else{
	    	 return new Result(true,"帐号或密码错误~");
	       }
	 }else{
		    
		    return new Result(true,"验证码错误~");
      }  
    }
	/**
	 * 退出
	 * @param request
	 * @param response
	 * @param queryParam
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/logout",method = RequestMethod.POST)
	@ResponseBody
	public Result signOut(HttpServletRequest request, HttpServletResponse response,@RequestParam Map<String, String> queryParam) throws ServletException, IOException{
		 LoginSession loginInfo = ApplicationContextHelper.getBeanByType(LoginSession.class);  
 		 loginInfo.setIsLog(false);
		 return new Result("退出成功!");  
    }
	
	/**
	 * 获取验证码
	 * @param request
	 * @param response
	 * @param queryParam
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/getImageCode",method = RequestMethod.GET) 
	public void getimage(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		  VerificationCode.getImage(request, response); 
    }
	
	/**
	 * 判断是否登入状态
	 * @param request
	 * @param response
	 * @param queryParam
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/islogin",method = RequestMethod.GET) 
	@ResponseBody
	public Result islogin(HttpServletRequest request, HttpServletResponse response,@RequestParam Map<String, String> queryParam) throws ServletException, IOException{
		LoginSession session=SessionUtil.getSession();
		 Result rs = new Result(); 
		 rs.setMsg(""+session.getIsLog());
		 return rs;
    }
}
