package net.tenie.web.controller;

import java.io.IOException;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.tenie.pojo.Blog;
import net.tenie.pojo.BlogTag;
import net.tenie.web.pojo.Result;
import net.tenie.web.service.CecheResult;
@Controller
public class PublishDataController {

	@Autowired 
	private JdbcTemplate jdbc;
	
	/**
	 * 提交新博文
	 * @param request
	 * @param response
	 * @param queryParam
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/submitPublishdata",method = RequestMethod.POST)
	@ResponseBody
	public Result savePublishData(HttpServletRequest request, HttpServletResponse response,@RequestParam Map<String, Object> queryParam) throws ServletException, IOException{
      System.out.println("PublishDataController.savePublishData");
       
      String showContent = (String) queryParam.get("showContent"); 
      String title = (String) queryParam.get("title"); 
      Integer textLength =Integer.valueOf( (String) queryParam.get("textLength")); 
      String tags[] = request.getParameterValues("tag");
       
      String data = (String) queryParam.get("content");  
      Blog blog = new Blog();
      blog.setString("post_title", title); 
      blog.setString("post_content", data);
      blog.setDate("time", new Date());
      blog.setInteger("text_length", textLength);
     int tf =  "true".equals(showContent)?1:0;
      blog.setInteger("show_content", tf);
      blog.saveIt();
      Integer id = Integer.valueOf( blog.getId().toString()) ; 
      
      //标签加到子表中
      if(tags !=null){ 
    	 for(String str : tags){
    		 BlogTag tag = new 	BlogTag();
        	 tag.setInteger("blog_id", id);
    		 tag.setString("tag", str);
    		 tag.saveIt();
         }  
      } 
      Result  rs= new Result();
      rs.setMsg("发布成功~"); 
      return rs;
	}
	
	/**
	 * 更新
	 * @param request
	 * @param response
	 * @param queryParam
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/updatePublishdata",method = RequestMethod.POST)
	@ResponseBody
	public Result updatePublishData(HttpServletRequest request, HttpServletResponse response,@RequestParam Map<String, Object> queryParam) throws ServletException, IOException{
      System.out.println("PublishDataController.updatePublishData");
      String id = (String) queryParam.get("id"); 
      String showContent = (String) queryParam.get("showContent"); 
      String title = (String) queryParam.get("title"); 
      Integer textLength =Integer.valueOf( (String) queryParam.get("textLength")); 
      String tags[] = request.getParameterValues("tag");
      
      String data = (String) queryParam.get("content"); 
      Blog blog = Blog.findById(id);
      blog.setString("post_title", title); 
      blog.setString("post_content", data);
      blog.setDate("time", new Date());
      
      blog.setInteger("show_content", "true".equals(showContent)?1:0);
      blog.setString("text_length", textLength);
      blog.saveIt();
      
      //标签加到子表中
      BlogTag.delete("blog_id=?", id);
      if(tags !=null){ 
    	 for(String str : tags){
    		 BlogTag tag = new 	BlogTag();
        	 tag.setInteger("blog_id", id);
    		 tag.setString("tag", str);
    		 tag.saveIt();
         }  
      }
       
      Result  rs= new Result();
      rs.setMsg("更新成功~"); 
      return rs;
	}
	
	 
}
