package net.tenie.web.service;

import java.util.List;
import java.util.Map;

public interface Search {
	
 
	List<Map<String, Object>> pageSearch(String val);
	List<Map<String, Object>> tagSearch(String tag);
	
}
