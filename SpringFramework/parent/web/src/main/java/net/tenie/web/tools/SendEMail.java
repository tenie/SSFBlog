package net.tenie.web.tools;

import java.util.Properties;
 
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress; 
import javax.mail.internet.MimeMessage; 
 

public class SendEMail { 

	public static void simpleSendMail(String to,String from,String password,String host,String Subject,String body) throws UtilException, MessagingException{
		
		System.out.println("email info : to="+to+
				"\n from="+from+
				"\n password="+password+
				"\n host="+host+
				"\n Subject="+Subject+
				"\n body="+body);
		if( StringUtils.isNullOrEmpty(to) &&
				StringUtils.isNullOrEmpty(from) && 
				StringUtils.isNullOrEmpty(host) &&
				StringUtils.isNullOrEmpty(Subject) &&
				StringUtils.isNullOrEmpty(body)   ){
			  throw new UtilException("参数都必须有值"); 
		}

  	try{ 	  
	      // 获取系统属性
	      Properties properties = System.getProperties();   
	      //授权者;发信的帐号密码 
	      if(password.length()>0){
	    	  System.out.println("use password send ....");
		      // 设置邮件服务器
		      properties.setProperty("mail.smtp.host", host);
		      properties.put("mail.smtp.auth", "true"); 
			  MyAuthenticator authenticator = new MyAuthenticator(from,password ); 
		      Session session = Session.getInstance(properties,authenticator);   
	          MimeMessage message = new MimeMessage(session);  
	          message.setFrom(new InternetAddress(from));  
	          message.addRecipient(Message.RecipientType.TO,   new InternetAddress(to));  
	          message.setSubject(Subject,"utf-8");   
	          message.setContent(body,  "text/html;charset=utf-8" );  
	          Transport.send(message);  
	      }else{
	    	  System.out.println("not use password send ....");
		      properties.setProperty("mail.smtp.host", "localhost"); 
		      Session session = Session.getInstance(properties);//,authenticator);  
		      MimeMessage message = new MimeMessage(session);   
		      message.addRecipient(Message.RecipientType.TO,   new InternetAddress(to));  
		      message.setSubject(Subject,"utf-8");   
		      message.setContent(body,  "text/html;charset=utf-8" );  
		      Transport.send(message);  
	      } 
  	  }catch (MessagingException mex) {
	         mex.printStackTrace();
	         throw  mex;
	      }
	}
	 
} 

 class MyAuthenticator extends javax.mail.Authenticator {
    private String strUser;
    private String strPwd;

    public MyAuthenticator(String user, String password) {
        this.strUser = user;
        this.strPwd = password;
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(strUser, strPwd);
    }
}

