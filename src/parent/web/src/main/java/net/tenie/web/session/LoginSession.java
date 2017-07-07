package net.tenie.web.session;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
 

@Component
@Scope("session")
public class LoginSession {
	private Boolean isLog = false;
	private String url;

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
		return "LoginSession [isLog=" + isLog + ", url=" + url + "]";
	}

	 
	
	
	
	
}
