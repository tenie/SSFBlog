package net.tenie.web.session;

import net.tenie.web.tools.ApplicationContextHelper;

public class SessionUtil { 

	public static  LoginSession getSession() {
		return  ApplicationContextHelper.getBeanByType(LoginSession.class);
	}
 
	
	
	 
	
}
