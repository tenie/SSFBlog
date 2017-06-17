package net.tenie.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.tenie.web.pojo.Result;
import net.tenie.web.session.LoginSession;
import net.tenie.web.tools.ApplicationContextHelper;

@Controller
public class SignInController {

	@Autowired 
	private JdbcTemplate jdbc;

	
	@RequestMapping(value="/sigIn",method = RequestMethod.POST)
	@ResponseBody
	public Result signIn(HttpServletRequest request, HttpServletResponse response,@RequestParam Map<String, String> queryParam) throws ServletException, IOException{
      System.out.println("ＧetCleanBlogPageController.htmlView");
      String name = queryParam.get("name");
      String password = queryParam.get("password");
      System.out.println("name="+name);
      System.out.println("password="+password);
//       List<Map<String, Object>> list=jdbc.queryForList("select * from user where name=? and password=?",name,password);
//      if(list!=null && list.size()>0){
//    	  return "ok";
//      }else{
//    	  return "fail";
//      }
      //为空返回失败
      if(name ==null &&   "".equals(name)){
    	  return new Result("error","登入失败");
      }
      
      if( "tenie@tenie.net".equals(name) && "bolgmimabolg1234".equals(password)){
 		 LoginSession loginInfo = ApplicationContextHelper.getBeanByType(LoginSession.class);  
 		 loginInfo.setIsLog(true);
 		 return new Result("登入成功");
      }else{
    	 return new Result("error","登入失败");
      }
      
      
      
      
    }
}
