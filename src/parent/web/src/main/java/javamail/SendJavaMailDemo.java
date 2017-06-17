package javamail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

 
 
 

public class SendJavaMailDemo {
	private String from;
	private String to;
	private String password;  
	 public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public String getPassword() {
		return password;
	}

	public static String DoDecrypt(String str)
     {
         String deStr = str.toLowerCase();
         StringBuffer deStrBuff = new StringBuffer();
         for (int i = 0; i < deStr.length(); i += 2)
        {
             String subStr = deStr.substring(i, i + 2);
             int tmpch = Integer.parseInt(subStr, 16);
             tmpch ^= 1;
             tmpch ^= 0xa;
             deStrBuff.append((char)tmpch);
         }
  
         return deStrBuff.toString();
     }
	
	public SendJavaMailDemo() {
		super();
		getPropertiesDate();
	}
	
	

	//获取配置文件
	public void getPropertiesDate(){
		Properties properties = new Properties();  
        try{  
            InputStream inputStream = new FileInputStream(this.getClass().getResource("").getPath()+"mail.properties");  
            properties.load(inputStream);  
            inputStream.close(); //关闭流  
        }  
        catch (IOException e){  
            e.printStackTrace();  
        }  
          from = properties.getProperty("from");  
          to = properties.getProperty("to");  
          password = properties.getProperty("password"); 
        
	}
	
	 
	public  void sendmail(String from,String to,String password,String mailTitle,String mailText,String errDataPath) {
		password = SendJavaMailDemo.DoDecrypt(password);
		System.out.println(password);
		String smtpString = from.split("@")[1];
		smtpString="smtp."+smtpString;
		try{
		//获取邮件服务器
		Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.qq.com");//"smtp.qq.com"
	    props.put("mail.smtp.auth", "true");
		//授权者
		MyAuthenticator authenticator = new MyAuthenticator("825588060@qq.com","13564234363");
		
		//获取session
		Session session = Session.getDefaultInstance(props,authenticator);
		//创建message
		Message message = new MimeMessage(session);
	
			message.setFrom(new InternetAddress("825588060@qq.com"));//设置发件人
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));//收件人
			message.setSubject(mailTitle);
		//	message.setText("邮件的文本");
			//发附件的邮件
			//先发邮件文本
			BodyPart bd1= new MimeBodyPart();
			bd1.setText(mailText);
			//附件部分
//			BodyPart bd2 = new MimeBodyPart();
//			DataSource source = new FileDataSource(dataPath);
//			bd2.setDataHandler(new DataHandler(source));
//			bd2.setFileName("同步数据.xls");
			
//			BodyPart bd3 = new MimeBodyPart();
//			DataSource source1 = new FileDataSource(errDataPath);
//			bd3.setDataHandler(new DataHandler(source1));
//			bd3.setFileName("日志.xls");
//			
			//创建一个容器,把文本和附件放入邮件内容中
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(bd1);
//			multipart.addBodyPart(bd2);
//			multipart.addBodyPart(bd3); 
			message.setContent(multipart);//把容器放入message中
			
			Transport.send(message); 
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
//		String from ="snoopy32@qq.com";
//		String smtpString = from.split("@")[1];
//		smtpString="smtp."+smtpString;
//		System.out.println(smtpString);
		SendJavaMailDemo sendJavaMailDemo=new SendJavaMailDemo();
		String fromString=sendJavaMailDemo.getFrom();
		String toString = sendJavaMailDemo.getTo();
		String password = sendJavaMailDemo.getPassword();
		
		sendJavaMailDemo.sendmail(fromString, toString, password, "ffff", "test", "err");
		System.out.println(fromString);
		System.out.println(toString);
		System.out.println(password);
	}
}
