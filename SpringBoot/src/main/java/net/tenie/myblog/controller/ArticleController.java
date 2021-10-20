package net.tenie.myblog.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value; 
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.tenie.myblog.entity.Blog;
import net.tenie.myblog.entity.BlogComment;
import net.tenie.myblog.entity.BlogTag;
import net.tenie.myblog.entity.Result;
import net.tenie.myblog.entity.VisitorDTO;
import net.tenie.myblog.mapper.BlogCommentMapper;
import net.tenie.myblog.mapper.BlogMapper;
import net.tenie.myblog.mapper.BlogTagMapper;
import net.tenie.myblog.session.LoginSession;
import net.tenie.myblog.session.SessionUtil;
import net.tenie.myblog.tools.ApplicationContextHelper;
import net.tenie.myblog.tools.ReflexBeanTools;
import net.tenie.myblog.tools.ToolsLib;
 
/**
 * 获取文章正文
 * @author tenie
 *
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
 
		@Autowired  
		private BlogMapper blog;
		
		@Autowired  
		private BlogCommentMapper blogCommentMap;
	
		@Autowired  
		private BlogTagMapper blogTag; 
		@Value("${ssfblog.myName}")
		private String myName;
		
		@Value("${ssfblog.photo}")
		private String photo;
		
		/**
		 * 文章阅读页面
		 * @param request
		 * @param response
		 * @param id
		 * @return
		 * @throws ServletException
		 * @throws IOException
		 */
		@RequestMapping(value="/{id}",method = RequestMethod.GET) 
		public String htmlContent2(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") Long id) throws  Exception{
		   //判断是否登入
		   LoginSession session = SessionUtil.getSession();
		   boolean islog = session.getIsLog(); 
		   // 获取博客
		   Blog bg = getBlogById(islog, id); 
	       
	       if(bg==null){ 
	    	    return  "/error-page/404";
	       }else{
	    	   //标签
	    	   List<BlogTag> taglist =  blogTag.findByBlogId(Long.valueOf(id)); 
		       //评论
	    	   List<BlogComment>  BlogCommentlist= blogCommentMap.findByPostId(Long.valueOf(id));
               List<Map<String,Object>> rs = getcommentsById(id,BlogCommentlist);  
 
		      request.setAttribute("data", bg); 
	    	  request.setAttribute("tags", taglist);
		      request.setAttribute("isLog",islog); 
		      request.setAttribute("commentLength",BlogCommentlist.size()); 
		      request.setAttribute("comments",rs);  
	       }
	       request.setAttribute("myName", myName);
	       File file = new File(photo);   
			if (file.exists()) {
				 request.setAttribute("photo", "/photo");  
			}else {
				request.setAttribute("photo", "../lib/assets/img/codeMonkey.ico");  
			}
			
	      
	       return  "/post";
	    }
	
		/**
		 * 编辑页面获取数据
		 * @param id
		 * @return
		 * @throws ServletException
		 * @throws IOException
		 */
		@RequestMapping(value="/get/{id}",method = RequestMethod.GET) 
		@ResponseBody
		public Result getContent(@PathVariable("id") String id) throws   Exception{ 
			Blog bg =blog.findById(Long.valueOf(id)); 
	       Result rs = new Result(); 
	       Map<String, Object> map = ReflexBeanTools.byField(bg.getClass(), bg); 
	       List<BlogTag> taglist =	  blogTag.findByBlogId( Long.valueOf(id) );
	       List<Map<String,Object>> rsl = new ArrayList<>();
	       for(BlogTag tag:taglist ){ 
	    	   rsl.add( ReflexBeanTools.byField(tag.getClass(), tag) );
	       } 
	       
	       rs.setMapRs(map);
	       rs.setData(rsl);
	       return rs;
	    }
	 
		/**
		 * 删除博文
		 * @param request
		 * @param id
		 * @return
		 * @throws ServletException
		 * @throws IOException
		 */
		@RequestMapping(value="/delete/{id}",method = RequestMethod.POST) 
		@ResponseBody
		public Result deleteContent(HttpServletRequest request,@PathVariable("id") String id) throws ServletException, IOException{ 
		  	LoginSession loginInfo = ApplicationContextHelper.getBeanByType(LoginSession.class); 
			Result rs = new Result();
			if(loginInfo.getIsLog()!=null && loginInfo.getIsLog()){ 
				blog.deleteById(Long.valueOf(id));
				return rs; 
			}else{
				rs.setError(true);
				return rs;
			} 
	    } 
	
		/**
		 * 保存评论
		 * @param request
		 * @param id
		 * @return
		 * @throws ServletException
		 * @throws IOException
		 * @throws InterruptedException 
		 */
		@RequestMapping(value="/comment/{parentId}",method = RequestMethod.POST) 
		@ResponseBody 
		public Result saveComment(HttpServletRequest request,@PathVariable("parentId") String parentId,@Valid VisitorDTO visitor) throws ServletException, IOException, InterruptedException{ 
		 
			boolean isLogin = SessionUtil.islogin();
			BlogComment comment = new BlogComment();
			if(isLogin){
				visitor.setName(myName); 
				comment.setMyselft(1);		 
			} 
			 
			comment.setPostId( Long.valueOf( visitor.getPostId()));
			comment.setName( visitor.getName());
			String commentStr = visitor.getComment();
			commentStr = ToolsLib.deleteAllHTMLTag(commentStr); 
			comment.setComment(commentStr);
			comment.setEmail( visitor.getEmail());
			comment.setUrl(visitor.getUrl());
			comment.setCreatedAt(new Date());
			if(parentId !=null && !"".equals(parentId) &&  !"-1".equals(parentId)){
				comment.setParentId( Long.valueOf( parentId));
			} 
			blogCommentMap.saveBlogComment(comment);
			Result rs = new Result();  
			return rs; 
	    } 
	
		/**
		 * 喜欢按钮触发 加1并返回
		 * @param id
		 * @return
		 * @throws ServletException
		 * @throws IOException
		 */
		@RequestMapping(value="/likeplus/{id}",method = RequestMethod.GET) 
		@ResponseBody
		public Result likePlusPlus(@PathVariable("id") String id) throws ServletException, IOException{ 
	       Blog bg =  blog.findById(Long.valueOf(id));
	       Integer likecount =  bg.getPostLike() +1; 
	       blog.updateLikes(bg.getId(), likecount);
	       Result rs = new Result(); 
	       rs.setMsg(likecount+"");
	       return rs;
	    }
	
		/**
		 * 评论里的赞 加1后返回
		 * @param id
		 * @return
		 * @throws ServletException
		 * @throws IOException
		 */
		@RequestMapping(value="/commentLikePlus/{id}",method = RequestMethod.GET) 
		@ResponseBody
		public Result commentLikePlusPlus(@PathVariable("id") Long id) throws ServletException, IOException{ 
 
			   BlogComment blogComment =  blogCommentMap.findById(id);
		       Integer likecount = (blogComment.getCommentLike()+1);
		       blogComment.setCommentLike( likecount);
 
		       blogCommentMap.update(blogComment);
		       Result rs = new Result(); 
		       rs.setMsg(likecount+"");
		       return rs;
	    }
	
	
	
		/**
		 * 隐藏文章
		 * @param request
		 * @param response
		 * @param id
		 * @return
		 * @throws ServletException
		 * @throws IOException
		 */
		@RequestMapping(value="/hiddenContent/{id}",method = RequestMethod.GET)
		@ResponseBody 
		public Result  hiddenContact(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") Long id) throws ServletException, IOException{
		 
			int i = blog.updateShowContent(id, 0);
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
		public Result  publicContact(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") Long id) throws ServletException, IOException{
 
			int i = blog.updateShowContent(id, 1);
			Result rs = new Result();
			if(i!=1){
				rs.setError(true);
			}
			return rs;
	    }
		
		/**
		 * 获取编辑文章页面
		 * @param id
		 * @return
		 * @throws ServletException
		 * @throws IOException
		 */
		@RequestMapping(value="/getEditPage/{id}",method = RequestMethod.GET)  
		public String getPageContent(HttpServletRequest request,@PathVariable("id") Long id) throws Exception{ 
 
		   Blog bg = blog.findById(id); 
		   Map<String, Object> map = ReflexBeanTools.beanToMap(bg.getClass(), bg); 
		   List<BlogTag> taglist =	 blogTag.findByBlogId(id);
	       List<Map<String,Object>> rsl = new ArrayList<>();
	       for(BlogTag tag:taglist ){
	    	   rsl.add(ReflexBeanTools.beanToMap(tag.getClass(), tag));
	       } 
	        
	       request.setAttribute("blogContent",map);
	       request.setAttribute("tags", rsl);
	       return "/publishPage";
	    }
		/**
		 * 首页置顶设置
		 * @param request
		 * @param response
		 * @param id
		 * @return
		 * @throws ServletException
		 * @throws IOException
		 */
		@RequestMapping(value="/setTop/{id}/{top}",method = RequestMethod.GET)
		@ResponseBody 
		public Result  setTop(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") Long id,@PathVariable("top") String top) throws ServletException, IOException{
			int i = 0;
			if("1".equals(top)) {
				 blog.updateTop(id, 0); 
			}else { 
				 blog.updateTop(id, 1);
			}
			 
			Result rs = new Result();
			if(i!=1){
				rs.setError(true);
			}
			return rs;
	    }
		
		/**
		 * 根据id 获取博客, 阅读量加1
		 * @param islog
		 * @param id
		 * @return
		 */
		private Blog getBlogById( boolean islog, Long id) {
			Blog bg;
			if(islog){ 
				bg =   blog.findById(id);
		    }else{
		    	bg =  blog.findByIdWithShowContent(id);  
		    }
			if(bg != null ){
			   Integer index = bg.getReadQuantity();
			   bg.setReadQuantity(index++ ); 
			   bg.setUpdatedAt(new Date());
	    	   blog.update(bg);
			}
			
		    return bg;
		}
		
		/**
		 * 获取blog评论的子评论
		 * @param id
		 * @param BlogCommentlist
		 * @return
		 * @throws IllegalAccessException 
		 * @throws InstantiationException 
		 */
		private List<Map<String,Object>> getcommentsById(Long id , List<BlogComment>  BlogCommentlist) throws InstantiationException, IllegalAccessException{
			
		      List<Map<String,Object>> rs = new ArrayList<Map<String,Object>>();
		      for(BlogComment bc:BlogCommentlist){ 
		    	  Map<String,Object> rmap = ReflexBeanTools.beanToMap(bc.getClass(), bc);
 
		    	  rmap.put("subcomment", 
		    			  blogCommentMap.findByPostIdWithParentId( id ,  bc.getId() )
		    			  ); 
		    	 
		    	  rs.add(rmap);
		      }
			return rs;  
			
		}
		 
}
