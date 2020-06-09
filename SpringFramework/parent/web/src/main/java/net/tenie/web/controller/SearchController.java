package net.tenie.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map; 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;  
import net.tenie.web.entity.Result;
import net.tenie.web.service.Search;
import net.tenie.web.session.SessionUtil;

@Controller
@RequestMapping("/search")
public class SearchController {
			
	@Autowired 
	private Search search;
	
	/**
	 * 通过文章标题来查找文章
	 * @param request
	 * @param response
	 * @param val
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/{val}",method = RequestMethod.GET) 
	public String  publicContact(HttpServletRequest request, HttpServletResponse response,@PathVariable("val") String val) throws ServletException, IOException{
	 
		  boolean islogin = SessionUtil.islogin();  
	      Result rs = new Result();  
	      Map<String,Object> rsMap = new HashMap<String, Object>(); 
	      rsMap.put("signIn", islogin); 
	      rsMap.put("dataList", search.pageSearch(islogin,val)); 
	      rs.setMapRs(rsMap);  
	      request.setAttribute("foo", rs); 
	      request.setAttribute("islogin", islogin?"y":"n");
	      request.setAttribute("searchVal", val);	
	      request.setAttribute("searchType", "title");	
	      return "/search";
		
	}
	
	
	/**
	 * 通过标签来查找文章
	 * @param tag
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value="/tagSearch/{tag}",method = RequestMethod.GET) 
	public String tagSearchhtmlView(HttpServletRequest request,  @PathVariable(value="tag") String tag  ) throws ServletException{
	  
      Result rs = new Result();  
      boolean islogin = SessionUtil.islogin();
      Map<String,Object> rsMap = new HashMap<String,Object>(); 
      rsMap.put("signIn", islogin);  
      rsMap.put("dataList", search.tagSearch(islogin, tag));  
      rs.setMapRs(rsMap);  
	  request.setAttribute("foo", rs);
	  request.setAttribute("islogin", islogin?"y":"n");
	  request.setAttribute("searchVal", tag);	
	  request.setAttribute("searchType", "tag");	
	  return "/search";
    }
			
}
