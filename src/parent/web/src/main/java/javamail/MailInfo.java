package javamail;
 

/**
 *Module:       MailInfo.java
 *Description:  定义发送邮件的所有字段的javabean
 *Company:      
 *Author:       ptp
 *Date:         Mar 1, 2012
 */
public class MailInfo{
    private String host;// 邮件服务器域名或IP
    private String from;// 发件人
    private String to;// 收件人
    private String cc;// 抄送人
    private String username;// 发件人用户名
    private String password;// 发件人密码
    private String title;// 邮件的主题
    private String content;// 邮件的内容
    
    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }
    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public String getCc() {
        return cc;
    }
    public void setCc(String cc) {
        this.cc = cc;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    
}