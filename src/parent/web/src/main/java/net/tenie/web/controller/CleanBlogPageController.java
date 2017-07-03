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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.tenie.pojo.Blog;
import net.tenie.web.pojo.Result;
import net.tenie.web.service.CecheResult;
import net.tenie.web.session.LoginSession;
import net.tenie.web.session.SessionUtil;

@Controller
@RequestMapping("/pageTitle")
public class CleanBlogPageController {
	 
//	private Result SignIncacheRS;
//	private Result cacheRS;
	@Autowired 
	private JdbcTemplate jdbc;
	
//	@Autowired
//	private  LoginSession session;
	/**
	 * 获取所以文章的索引
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
	public Result htmlView(@PathVariable(value="limit") Integer limit ,@PathVariable(value="offset") Integer offset ,
											@PathVariable(value="getCount") String getCount  ) throws ServletException{
	  List<Map<String, Object>> countList =new ArrayList();
	  List<Map<String, Object>> list=new ArrayList<>();
	 //判断是否登入
 	 LoginSession session = SessionUtil.getSession();
	 boolean bool =session.getIsLog();
	 System.out.println("boolean==="+bool);
	 
	 Result SignIncacheRS  = CecheResult.getSignIncacheRS();
     Result cacheRS =  CecheResult.getCacheRS(); 
	
	 if("1".equals(getCount) && SignIncacheRS!=null && bool){
    	 return SignIncacheRS;
     }else  if("1".equals(getCount )&& cacheRS!=null && !bool){
    	 return cacheRS;
     }
	 
	 //登入过的查询
	 if(bool && SignIncacheRS==null){
		  list=jdbc.queryForList("select id,post_title,post_subtitle,time,show_content,top from blog where  1=1 ORDER BY top,id  DESC limit ? offset ?",limit,offset);
	      //获取总行数,对分页最后页做判断时需要
	      if("1".equals(getCount)){
	    	 countList =  jdbc.queryForList("select count(id) as count from blog");
	      }     
	 }else if(!bool && cacheRS==null){
		 list=jdbc.queryForList("select id,post_title,post_subtitle,time,show_content,top from blog where show_content=1  ORDER BY top,id  DESC limit ? offset ?",limit,offset);
	      //获取总行数,对分页最后页做判断时需要
	      if("1".equals(getCount)){
	    	 countList =  jdbc.queryForList("select count(id) as count from blog where show_content=1");
	      } 
	 }
    
    
      //结果集赋值
      Result rs = new Result(); 
      String rsCount="";
      Map<String,Object> rsMap = new HashMap();
      if(countList.size()>0){
    	  rsCount = ""+ countList.get(0).get("count");
      }
      rsMap.put("signIn", bool);
      rsMap.put("Count", rsCount);
      rsMap.put("dataList", list); 
      rs.setMapRs(rsMap); 
      if(bool){
    	  CecheResult.setSignIncacheRS(rs);
      }else{ 
    	  CecheResult.setCacheRS(rs);
      }
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
	
	//private文章
		@RequestMapping(value="/hiddenContent/{id}",method = RequestMethod.GET)
		@ResponseBody 
		public Result  hiddenContact(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") String id) throws ServletException, IOException{
		
			int i = Blog.update("show_content = ?","id=?", 	0,id);
			Result rs = new Result();
			if(i!=1){
				rs.setError("yes");
			} 
			CecheResult.setNullSignIncacheRS();
			return rs;
	    }
	//public文章
		@RequestMapping(value="/publicContent/{id}",method = RequestMethod.GET)
		@ResponseBody 
		public Result  publicContact(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") String id) throws ServletException, IOException{
			int i = Blog.update("show_content = ?","id=?", 	1,id);
			Result rs = new Result();
			if(i!=1){
				rs.setError("yes");
			} 
			CecheResult.setNullSignIncacheRS();
			return rs;
	    }
		
		
	 
	

}
