package net.tenie.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.DB;
import org.javalite.activejdbc.LazyList;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.DbName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.tenie.pojo.Blog;
import net.tenie.web.pojo.Result;
import net.tenie.web.session.LoginSession;
import net.tenie.web.session.SessionUtil;
import net.tenie.web.tools.ApplicationContextHelper; 
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
		   //判断是否登入
		   LoginSession session = SessionUtil.getSession();
		   boolean islog = session.getIsLog();
		   Blog blog;
		   if(islog){
			    blog = new Blog().findById(id);
		   }else{
			   blog = new Blog().findFirst("id=? and show_content=1", id);
		   }
	    
	      // List<Map<String, Object>> list=jdbc.queryForList("select * from blog where id=? ",id);  
	     
	       if(blog==null){ 
	    	 return  "/error-page/404";
	       }else{
	    	   Integer index = blog.getInteger("read_quantity");
	    	   index++;
	    	   blog.setInteger("read_quantity",index );
	    	   blog.saveIt();
	    	   request.setAttribute("data", blog);
		       request.setAttribute("isLog",islog); 
	       }
	       return  "/post";
	    }
		
		@RequestMapping(value="/get/{id}",method = RequestMethod.GET) 
		@ResponseBody
		public Result getContent(@PathVariable("id") String id) throws ServletException, IOException{ 
	       Blog blog = new Blog().findById(id); 
	       Result rs = new Result();
	       Map<String, Object> map = blog.toMap();
	       rs.setMapRs(map);
	       return rs;
	    }
		
		@RequestMapping(value="/update",method = RequestMethod.PUT) 
		@ResponseBody
		public Result updateContent(HttpServletRequest request) throws ServletException, IOException{ 
	       Blog blog = new Blog();
	       request.getParameterMap();
	       Result rs = new Result();
	       Map<String, Object> map = blog.toMap();
	       rs.setMapRs(map);
	       return rs;
	    }
		
		@RequestMapping(value="/delete/{id}",method = RequestMethod.POST) 
		@ResponseBody
		public Result deleteContent(HttpServletRequest request,@PathVariable("id") String id) throws ServletException, IOException{ 
		  
			System.out.println("进入..");
			LoginSession loginInfo = ApplicationContextHelper.getBeanByType(LoginSession.class);
			  Result rs = new Result();
			if(loginInfo.getIsLog()!=null && loginInfo.getIsLog()){
				System.out.println("....登入过....");
				Blog.findById(id).delete();
				 return rs;
				 
			}else{
				rs.setError("yes");
				return rs;
			} 
	       
	       
	    } 
		 
}
