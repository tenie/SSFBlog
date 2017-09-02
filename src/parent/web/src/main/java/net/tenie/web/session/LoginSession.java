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
	private String sessioId;
    private String userAgent;
    
	
	

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getSessioId() {
		return sessioId;
	}

	public void setSessioId(String sessioId) {
		this.sessioId = sessioId;
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
		return "LoginSession [isLog=" + isLog + ", url=" + url + ", imageCode=" + imageCode + "]";
	}

	 

	 
	
	
	
	
}
