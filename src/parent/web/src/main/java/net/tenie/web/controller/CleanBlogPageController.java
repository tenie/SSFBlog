package net.tenie.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.javalite.activejdbc.LazyList;
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
import net.tenie.web.service.Search;
import net.tenie.web.session.LoginSession;
import net.tenie.web.session.SessionUtil;

@Controller
@RequestMapping("/pageTitle")
public class CleanBlogPageController {
	 
//	private Result SignIncacheRS;
//	private Result cacheRS; 
	@Autowired 
	private JdbcTemplate jdbc;
	
	@Autowired 
	private Search search;
	 
	/**
	 * 首页数据加载
	 * @param limit	分页设置
	 * @param offset 分页偏移量
	 * @param getCount	该值为1时, 查找所有记录的总数
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value="/{getCount}/{limit}/{offset}",method = RequestMethod.GET)
	@ResponseBody
	public Result htmlView( @PathVariable(value="limit") Integer limit ,
							@PathVariable(value="offset") Integer offset ,
							@PathVariable(value="getCount") String getCount ) throws ServletException{
	  //判断是否登入 
	  boolean bool =SessionUtil.islogin(); 
	  //获取缓存 
	  if(offset == 0){
		 if(bool){
	    	  Result logincacheRS  = CecheResult.getSignIncacheRS();
	    	  if(logincacheRS!=null){
	    	    	 return logincacheRS;
	    	  }
	      }else{
	    	  Result cacheRS =  CecheResult.getCacheRS(); 
	    	  if(cacheRS!=null){
	    	    	 return cacheRS;
	    	  } 
	      }
	  }
	   
      //结果集赋值
      Result rs = new Result(); 
      rs.setMapRs(search.indexSearch(limit, offset, getCount)); 
      //缓存
      if(bool && "1".equals(getCount )){
    	  CecheResult.setLogincacheRS(rs);
      }else if(!bool && "1".equals(getCount)){ 
    	  CecheResult.setCacheRS(rs);
      }
      return rs;
    }
	
	
	//获取tag查询结果
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
	
	
	//获取某片文章的所以内容
	@RequestMapping(value="/postContent/{id}",method = RequestMethod.GET)
	@ResponseBody
	@Deprecated
	public Map<String,Object> htmlContent(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") String id) throws ServletException, IOException{
        List<Map<String, Object>> list=jdbc.queryForList("select * from blog where id=? ",id); 
        return list.get(0);
    }
	
	/**
	 * 不公开文章
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
		@RequestMapping(value="/hiddenContent/{id}",method = RequestMethod.GET)
		@ResponseBody 
		public Result  hiddenContact(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") String id) throws ServletException, IOException{
		
			int i = Blog.update("show_content = ?","id=?", 	0,id);
			Result rs = new Result();
			if(i!=1){
				rs.setError(true);
			} 
			
			return rs;
	    }
		/**
		 * 公开文章
		 * @param request
		 * @param response
		 * @param id
		 * @return
		 * @throws ServletException
		 * @throws IOException
		 */
		@RequestMapping(value="/publicContent/{id}",method = RequestMethod.GET)
		@ResponseBody 
		public Result  publicContact(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") String id) throws ServletException, IOException{
			int i = Blog.update("show_content = ?","id=?", 	1,id);
			Result rs = new Result();
			if(i!=1){
				rs.setError(true);
			} 
			
			return rs;
	    }
		
		
	 
	

}
