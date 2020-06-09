package net.tenie.myblog.controller;
 
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
import net.tenie.myblog.entity.Result;
import net.tenie.myblog.service.Search;
import net.tenie.myblog.session.SessionUtil;
 

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
	 * @throws Exception 
	 */
	@RequestMapping(value="/{val}",method = RequestMethod.GET)
//	@ResponseBody 
	public String  publicContact(HttpServletRequest request, HttpServletResponse response,@PathVariable("val") String val) throws Exception{
	 
		  boolean islogin = SessionUtil.islogin();  //是否登入
		  //结果集赋值
	      Result rs = new Result();  
	      Map<String,Object> rsMap = new HashMap<String, Object>(); 
	      rsMap.put("signIn", islogin); 
	      rsMap.put("dataList", search.pageSearch(islogin,val)); 
	      rs.setMapRs(rsMap); 
	      
	      System.out.println(rs);
	        // 数据
		    request.setAttribute("foo", rs);
		    // 是否登入
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
//	@ResponseBody
	public String tagSearchhtmlView(HttpServletRequest request,  @PathVariable(value="tag") String tag  ) throws ServletException{
	 
      //结果集赋值
      Result rs = new Result();  
      boolean islogin = SessionUtil.islogin();
      Map<String,Object> rsMap = new HashMap<String,Object>(); 
      rsMap.put("signIn", islogin);  //是否登入
      rsMap.put("dataList", search.tagSearch(islogin, tag));  
      rs.setMapRs(rsMap); 
      // 数据
	    request.setAttribute("foo", rs);
	    // 是否登入
	    request.setAttribute("islogin", islogin?"y":"n");
	    request.setAttribute("searchVal", tag);	
	    request.setAttribute("searchType", "tag");	
	    return "/search";
    }
			
}
