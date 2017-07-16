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
import org.springframework.web.bind.annotation.ResponseBody;

import net.tenie.pojo.Blog;
import net.tenie.web.pojo.Result;
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
	@ResponseBody 
	public Result  publicContact(HttpServletRequest request, HttpServletResponse response,@PathVariable("val") String val) throws ServletException, IOException{
	
		  val = val.substring(4);
		  //结果集赋值
	      Result rs = new Result();  
	      Map<String,Object> rsMap = new HashMap(); 
	      rsMap.put("signIn", SessionUtil.islogin());  //是否登入
	      rsMap.put("dataList", search.pageSearch(val)); 
	      rs.setMapRs(rsMap); 
	      return rs;
		
	}
	
	
	/**
	 * 通过标签来查找文章
	 * @param tag
	 * @return
	 * @throws ServletException
	 */
	@RequestMapping(value="/tagSearch/{tag}",method = RequestMethod.GET)
	@ResponseBody
	public Result tagSearchhtmlView(  @PathVariable(value="tag") String tag  ) throws ServletException{
	 
      //结果集赋值
      Result rs = new Result();  
      Map<String,Object> rsMap = new HashMap(); 
      rsMap.put("signIn", SessionUtil.islogin());  //是否登入
      rsMap.put("dataList", search.tagSearch(tag)); 
      rs.setMapRs(rsMap); 
      return rs;
    }
			
}
