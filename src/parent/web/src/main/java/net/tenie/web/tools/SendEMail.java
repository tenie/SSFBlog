package net.tenie.web.tools;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
 

public class SendEMail {

	private String from ="tenie@tenie.net";
	private static String to = "tenie@tenie.net";
	//获取邮件发送服务器

	public static  void sendmail2() {
		try{
		//获取邮件服务器
		Properties props = System.getProperties();
 		props.put("mail.smtp.host", "localhost");
//	    props.put("mail.smtp.auth", "true");
		//授权者
//		MyAuthenticator authenticator = new MyAuthenticator("tenie@tenie.net", "pwd");
		
		//获取session
		Session session = Session.getDefaultInstance(props);//,authenticator);
		//创建message
		Message message = new MimeMessage(session);
	
			//message.setFrom(new InternetAddress(from));//设置发件人
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));//收件人
			message.setSubject("设置邮件主题");
		//	message.setText("邮件的文本");
			//发附件的邮件
			//先发邮件文本
			BodyPart bd1= new MimeBodyPart();
			bd1.setText("邮件的文本部分");
			//附件部分
//			BodyPart bd2 = new MimeBodyPart();
//			DataSource source = new FileDataSource("D:\\sdassd1.xls");
//			bd2.setDataHandler(new DataHandler(source));
//			bd2.setFileName("sdassd1.xls");
//			
//			BodyPart bd3 = new MimeBodyPart();
//			DataSource source2 = new FileDataSource("D:\\sdassd1.xls");
//			bd3.setDataHandler(new DataHandler(source2));
//			bd3.setFileName("sdassd1.xls");
			
			//创建一个容器,把文本和附件放入邮件内容中
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(bd1);
//			multipart.addBodyPart(bd2);
//			multipart.addBodyPart(bd3);
			
			message.setContent(multipart);//把容器放入message中 
			Transport.send(message);   
			 System.out.println("Sent message successfully....");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void simpleSendMail(String to,String from,String password,String host,String Subject,String body) throws UtilException{
		if( StringUtils.isNullOrEmpty(to) &&
				StringUtils.isNullOrEmpty(from) &&
				StringUtils.isNullOrEmpty(password) &&
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
	      }
	}
	public static void main(String[] args) {
		sendmail2();
	}
	
//	public static void main(String[] args) {
////		 Send2fileMial send2fileMial = new Send2fileMial();
////		 String string=send2fileMial.getClass().getResource("").getPath();
////		 send2fileMial.sendmail();
////		 System.out.println(string);  
//		 // 收件人电子邮箱
//	      String to = "tenie@tenie.net"; 
//	      // 发件人电子邮箱
//	      String from = "root";
//	      //密码
//	      String password = "pwd";
//	      // 指定发送邮件的主机为 localhost
//	      String host = "smtp.163.com";  
//	      // 获取系统属性
//	      Properties properties = System.getProperties(); 
//	      // 设置邮件服务器
//	      properties.setProperty("mail.smtp.host", "localhost");
//	     // properties.put("mail.smtp.auth", "true");
//	    //授权者;发信的帐号密码
//		//	MyAuthenticator authenticator = new MyAuthenticator(from,password );
//			
//	      // 获取默认的 Session 对象。
//	      Session session = Session.getInstance(properties);//,authenticator);  
//	      try{
//	         // 创建默认的 MimeMessage 对象。
//	         MimeMessage message = new MimeMessage(session); 
//	         // Set From: 头部头字段 //发信人
//	         message.setFrom(new InternetAddress(from));  
//	         // Set To: 头部头字段 //收信人
//	         message.addRecipient(Message.RecipientType.TO,   new InternetAddress(to)); 
//	         // Set Subject: 头字段 //信的主题,标题
//	         message.setSubject("This is the Subject Line!"); 
//	         // 发送 HTML 消息, 可以插入html标签  //信的body,
//	         message.setContent("<h1>T上海呵呵仨打e</h1>",  "text/html;charset=utf-8" ); 
//	         // 发送消息
//	         Transport.send(message);
//	         System.out.println("Sent message successfully....");
//	      }catch (MessagingException mex) {
//	         mex.printStackTrace();
//	      }
//	}
}
/**
 *     
 */

