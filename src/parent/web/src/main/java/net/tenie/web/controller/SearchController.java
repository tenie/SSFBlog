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
	
	//public文章
	@RequestMapping(value="/{val}",method = RequestMethod.GET)
	@ResponseBody 
	public Result  publicContact(HttpServletRequest request, HttpServletResponse response,@PathVariable("val") String val) throws ServletException, IOException{
	
		 
		  //结果集赋值
	      Result rs = new Result();  
	      Map<String,Object> rsMap = new HashMap(); 
	      rsMap.put("signIn", SessionUtil.islogin());  //是否登入
	      rsMap.put("dataList", search.pageSearch(val)); 
	      rs.setMapRs(rsMap); 
	      return rs;
		
	}
			
}
