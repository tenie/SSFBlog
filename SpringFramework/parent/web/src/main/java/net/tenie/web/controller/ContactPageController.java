package net.tenie.web.controller;

import java.io.IOException; 
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import net.tenie.pojo.ContactData;
import net.tenie.web.entity.Result;
import net.tenie.web.entity.VisitorDTO;
import net.tenie.web.tools.SendEMail;
import net.tenie.web.tools.UtilException;

@Controller
public class ContactPageController { 
	
	@Value("${mail.to}")
	private String to;
	@Value("${mail.from}")
	private String from;
	
	@Value("${mail.from.password}")
	private String password;
	
	@Value("${mail.host}")
	private String host;
	
	/**
	 * 联系页面信息保持,并发邮件通知
	 * @param request
	 * @param response
	 * @param queryParam
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws UtilException
	 * @throws MessagingException
	 */
	@RequestMapping(value="/postContactData",method = RequestMethod.POST)
	@ResponseBody
	public Result postContactData(@Valid VisitorDTO visitor) throws ServletException, IOException, UtilException, MessagingException{
     
      String name = visitor.getName();
      String phone = visitor.getPhone();
      String email =visitor.getEmail();
      String message = visitor.getMessage();
      
      
       ContactData contact = new ContactData();
       contact.createIt("email",email,"phone",phone,"message",message,"name",name);  
       SendEMail.simpleSendMail(to, from, password, host,
				    		   	"有人通过联系页面发信息给你了!",
				    		   	"<b>信息:</b>"+message+"<br> <b>name:</b> "+name+"<br> <b>email:</b>"+email
				    		   ); 
       return new Result("信息已经发生给"+to+"!");
    }
}
