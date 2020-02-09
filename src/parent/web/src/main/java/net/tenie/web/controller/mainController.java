package net.tenie.web.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import net.tenie.web.entity.Result;
import net.tenie.web.service.CecheResult;
import net.tenie.web.service.Search;
import net.tenie.web.session.SessionUtil;
/**
 * main
 * @author tenie
 */
@Controller
public class mainController { 
	Logger logger = LoggerFactory.getLogger(CleanBlogPageController.class);
	@Autowired
	private Search search;
	Integer limit = 10;
	
	
		@RequestMapping(value="/",method = RequestMethod.GET) 
		public String main(HttpServletRequest request, HttpServletResponse response,
				
				UriComponentsBuilder uriCB) throws ServletException { 
			 Integer offset = 0;  
			logger.info("index_page data begin "); 
			Result rs = null;
			// 判断是否登入
			boolean islogin = SessionUtil.islogin(); 
			 
			CecheResult.getRs(islogin, rs);
			int blogcount = 0;
			// 结果集赋值
			if (rs == null) {
				rs = new Result();
				rs.setMapRs(search.indexSearch(islogin,limit, offset));
				// 缓存 
				CecheResult.setRs(islogin, rs);

			    blogcount = search.getBlogCount(islogin);
				CecheResult.setBlogcount(blogcount); 
			}    
			blogcount = CecheResult.getBlogcount();
			// 数据
		    request.setAttribute("foo", rs);
		    // 是否登入
		    request.setAttribute("islogin", islogin?"y":"n");
		    // 分页
		    if(blogcount< limit)
		    	request.setAttribute("nextpage", -1);
		    else
		    	 request.setAttribute("nextpage", 1);	
		    request.setAttribute("previouspage", -1);	
		    
		    
		    return  "/index";
		}
		
		@RequestMapping(value="/page/{page}",method = RequestMethod.GET) 
		public String nextpage(HttpServletRequest request, HttpServletResponse response,
				@PathVariable("page") Integer page,
				UriComponentsBuilder uriCB) throws ServletException {
			 
			 Integer offset = limit* page ; 
			// 是否登入
			// 判断是否登入
				boolean islogin = SessionUtil.islogin();
			    request.setAttribute("islogin", islogin?"y":"n");
			    int  blogcount = CecheResult.getBlogcount();
			    if(blogcount == 0) {
			    	 blogcount = search.getBlogCount(islogin);
			    }
			int more =  (limit + offset) - blogcount ;
			if( more >= limit || page < 0) {
				 return  "/error-page/404";
			}
			Result rs  = new Result();
			   
			Map<String, Object> m = search.indexSearch(islogin,limit, offset); 
			rs.setMapRs(m);
			// 数据
		    request.setAttribute("foo", rs);  
		    if ((offset+limit) < blogcount ) { 
		    	request.setAttribute("nextpage", page+ 1 ); 
		    } 	
		    else { 
		    	 request.setAttribute("nextpage", -1);
		    }
		    request.setAttribute("previouspage", page-1); 
		    
		    return  "/index";
		}

	
		 
			 
		 
}
