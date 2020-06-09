package net.tenie.myblog.session;

import net.tenie.myblog.tools.ApplicationContextHelper;

public class SessionUtil { 
	
	
	
	public static  LoginSession getSession() {
		return  ApplicationContextHelper.getBeanByType(LoginSession.class);
	}
 
	public static boolean islogin(){
		return  ApplicationContextHelper.getBeanByType(LoginSession.class).getIsLog();
	}
	
	
	 
	
}
