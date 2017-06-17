package net.tenie.web.service;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * 采用Spring的JavaMailSender发邮件
 * 
 * @author gaowei.cheng 2015年12月23日
 */
@Component
public class SpringMail implements MailSender {
    private static Logger log = LoggerFactory.getLogger(SpringMail.class);
    //注入一个mailSender
    @Autowired
    JavaMailSenderImpl mailSender;

    @Value("${mail.smtp.from}")
    private String from;
    
    @Value("${mail.smtp.to}")
    private String[] to;
//    @Value("${mail.smtp.cc}")
//    private String[] cc;
    
    public void sendMail() {
    	System.err.println("send.....");
        //读取配置文件中的收件人
//        PropertiesUtil.readProperties("mail.properties");
//        String[] to = PropertiesUtil.getProperty("mail.smtp.to").split(",");// 收件人
//        String[] cc = PropertiesUtil.getProperty("mail.smtp.cc").split(",");// 收件人

        MimeMessage mailMessage = mailSender.createMimeMessage();
        //log.info("发送邮件给" + PropertiesUtil.getProperty("mail.smtp.to"));
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true, "utf-8");
            //helper.setFrom(from);// 设置发件人
            helper.setTo(to);// 设置收件人
           // helper.setCc(cc);// 设置抄送
            helper.setSubject("SpringMail测试");// 设置主题
            helper.setText("这是一封来自SpringMail的测试邮件");// 邮件体
            mailSender.send(mailMessage);// 发送邮件
           System.out.println("邮件发送成功...");
        } catch (Exception e) {
           e.printStackTrace();
            try {
                Thread.sleep(1000 * 1000);
                this.sendMail();
            } catch (InterruptedException e1) {
               System.out.println("重发邮件发生异常");
                log.error(e.getMessage());
                e1.printStackTrace();
            }
        }
    }
}