package net.tenie.web.controller;

import java.io.IOException;
import java.util.Map;

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

import net.tenie.web.pojo.Result;
import net.tenie.web.service.MailSender;
import net.tenie.web.tools.SendEMail;
import net.tenie.web.tools.StringUtils;

@Controller
public class ContactPageController {
	@Autowired 
	private JdbcTemplate jdbc;
	@Autowired 
	private MailSender mailSender;
	
	@Value("${mail.to}")
	private String to;
	@Value("${mail.from}")
	private String from;
	
	@Value("${mail.from.password}")
	private String password;
	
	@Value("${mail.host}")
	private String host;
	
	
	@RequestMapping(value="/postContactData",method = RequestMethod.POST)
	@ResponseBody
	public Result postContactData(HttpServletRequest request, HttpServletResponse response,@RequestParam Map<String, String> queryParam) throws ServletException, IOException{
      System.out.println("ＧetCleanBlogPageController.postContactData");
      String name = queryParam.get("name");
      String phone = queryParam.get("phone");
      String email = queryParam.get("email");
      String message = queryParam.get("message");
      System.out.println("name= "+name);
      System.out.println("phone= "+phone);
      System.out.println("email= "+email);
      System.out.println("message= "+message);
      
      if( StringUtils.isNullOrEmpty(name.trim())&&
		  StringUtils.isNullOrEmpty(email.trim())&&
		  StringUtils.isNullOrEmpty(message.trim()) ){
    	  return new Result(true,"Name,Email,Message不能空白!");
      }
      //String name = queryParam.get("name");
      //jdbc.update(sql, args)
       jdbc.update("insert into  contact_data  ( `email`, `phone`, `message`, `name`) values ( ?, ?, ?, ?)",email,phone,message,name);
     
       SendEMail.simpleSendMail("tenie@tenie.net", from, password, host,
    		   	"有人通过联系页面发信息给你了!", "<b>信息:</b>"+message+"<br> <b>name:</b> "+name+"<br> <b>email:</b>"+email
    		   );
       //   mailSender.sendMail();
     //   SendEMail.sendmail2(); // 测试通过
       return new Result("信息已经发生给tenie@tenie.net!");
    }
}
