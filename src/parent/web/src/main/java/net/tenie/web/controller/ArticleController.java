package net.tenie.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod; 
/**
 * 获取文章正文
 * @author tenie
 *
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
	@Autowired 
	private JdbcTemplate jdbc;
	 
		
		@RequestMapping(value="/{id}",method = RequestMethod.GET) 
		public String htmlContent2(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") String id) throws ServletException, IOException{
	       System.err.println("free Marker......");
	       List<Map<String, Object>> list=jdbc.queryForList("select * from blog where id=? ",id); 
	       
	       request.setAttribute("data", list);
	       request.setAttribute("foo", "foo");
	       if(list==null || list.size()==0){
	    	 
	    	 return  "/error-page/404";
	       }
	       return  "/post";
	    }
}
