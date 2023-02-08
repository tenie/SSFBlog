package net.tenie.myblog.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.tenie.myblog.entity.Blog;
import net.tenie.myblog.entity.BlogTag;
import net.tenie.myblog.entity.Result;
import net.tenie.myblog.mapper.BlogMapper;
import net.tenie.myblog.mapper.BlogTagMapper;
 
@Controller
public class PublishDataController {

	@Autowired 
	private BlogMapper bm;
	@Autowired 
	private BlogTagMapper btm;
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
      
      String showContent = (String) queryParam.get("showContent");  
      String istop = (String) queryParam.get("istop"); 
      String title = (String) queryParam.get("title"); 
      Long textLength =Long.valueOf( (String) queryParam.get("textLength")); 
      String tags[] = request.getParameterValues("tag"); 
      String data = (String) queryParam.get("content");  
      Blog blog = new Blog();
      blog.setPostTitle(title); 
      blog.setPostContent(data);
      blog.setTime(new Date() );
      blog.setTextLength(textLength); 
      int tf =  "true".equals(showContent)?1:0;
      blog.setShowContent(tf);
      blog.setTop("true".equals(istop)?0:1);  
      bm.saveBlog(blog);
      Long id = blog.getId() ; 
      //标签加到子表中
      if(tags !=null){ 
    	 for(String str : tags){
    		 if(str != null && str.trim().length() > 0) {
    			 BlogTag tag = new 	BlogTag();
            	 tag.setBlogId(id);
        		 tag.setTag(str.trim());
        		 tag.setCreatedAt(new Date()); 
        		 btm.saveBlogTag(tag);
    		 }
    		
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
	public Result updatePublishData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Map<String, Object> queryParam) throws ServletException, IOException{
      System.out.println("PublishDataController.updatePublishData");
      String id = (String) queryParam.get("id"); 
      String showContent = (String) queryParam.get("showContent"); 
      String isTop = (String) queryParam.get("isTop"); 
      String title = (String) queryParam.get("title"); 
      Integer textLength =Integer.valueOf( (String) queryParam.get("textLength")); 
      String tags[] = request.getParameterValues("tag");
      
      String data = (String) queryParam.get("content"); 
      Blog blog = bm.findById(Long.valueOf(id));
      blog.setPostTitle(title); 
      blog.setPostContent(data);
      blog.setTime(new Date());
      blog.setTop("true".equals(isTop)?0:1);
      blog.setShowContent("true".equals(showContent)?1:0);
      blog.setTextLength(Long.valueOf( textLength));
      blog.setUpdatedAt(new Date());
       
      bm.update(blog); 
      
      //标签加到子表中 
      btm.deleteByBlogId(Long.valueOf( id ) );
      if(tags !=null){ 
    	 for(String str : tags){
    		 if(str != null && str.trim().length() > 0) {
	    		 BlogTag tag = new 	BlogTag();
	        	 tag.setBlogId(Long.valueOf(id));
	    		 tag.setTag(str.trim()); 
	    		 btm.saveBlogTag(tag); 
    		 }
         }  
      }
       
      Result  rs= new Result();
      rs.setMsg("更新成功~"); 
      return rs;
	}
	
	 
}
