package net.tenie.web.session;

import net.tenie.web.tools.ApplicationContextHelper;

public class SessionUtil { 
	
	
	
	public static  LoginSession getSession() {
		return  ApplicationContextHelper.getBeanByType(LoginSession.class);
	}
 
	public static boolean islogin(){
		return  ApplicationContextHelper.getBeanByType(LoginSession.class).getIsLog();
	}
	
	
	 
	
}
