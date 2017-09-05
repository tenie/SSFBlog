package net.tenie.web.session;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
 

@Component
@Scope("session")
public class LoginSession {
	private Boolean isLog = false;
	private String url;
	private String imageCode;
	private String host;
	private String sessionId;
    private String userAgent;
    
	
	

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getImageCode() {
		return imageCode;
	}

	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
	}

	public Boolean getIsLog() {
		return isLog;
	}

	public void setIsLog(Boolean isLog) {
		this.isLog = isLog;
	}

	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "LoginSession [isLog=" + isLog + ", url=" + url + ", imageCode=" + imageCode + ", host=" + host
				+ ", sessionId=" + sessionId + ", userAgent=" + userAgent + "]";
	}

	 

	 

	 
	
	
	
	
}
