package net.tenie.web.service;

import java.util.List;
import java.util.Map;

public interface Search {
	
 
	List<Map<String, Object>> pageSearch(boolean isLogin, String val);
	List<Map<String, Object>> tagSearch(boolean isLogin, String tag);
	
	Map<String, Object> indexSearch(boolean islogin, Integer limit,Integer offset,String getCount);
	
}
