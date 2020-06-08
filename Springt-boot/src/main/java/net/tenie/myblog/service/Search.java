package net.tenie.myblog.service;

import java.util.List;
import java.util.Map;

public interface Search {
	
 
	List<Map<String, Object>> pageSearch(boolean isLogin, String val) throws Exception;
	List<Map<String, Object>> tagSearch(boolean isLogin, String tag);
	
	Map<String, Object> indexSearch(boolean islogin, Integer limit,Integer offset,String getCount);
	public Map<String, Object>indexSearch(boolean islogin, Integer limit,Integer offset);
	
	public String  getBlogCount(boolean islogin, String getCount);
	public int  getBlogCount(boolean islogin);
}
