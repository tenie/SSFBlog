package net.tenie.web.controller;

import java.io.IOException;
import java.util.ArrayList; 
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 
import javax.validation.Valid;
 
import org.javalite.activejdbc.LazyList; 
import org.springframework.beans.factory.annotation.Value; 
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.tenie.pojo.Blog;
import net.tenie.pojo.BlogComment;
import net.tenie.pojo.BlogTag;
import net.tenie.web.entity.Result;
import net.tenie.web.entity.VisitorDTO; 
import net.tenie.web.session.LoginSession;
import net.tenie.web.session.SessionUtil;
import net.tenie.web.tools.ApplicationContextHelper;
import net.tenie.web.tools.ToolsLib; 
/**
 * 获取文章正文
 * @author tenie
 *
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
//		@Autowired  
//		private JdbcTemplate jdbc; 
		
		@Value("${who.am.i}")
		private String myname;
		
		
		
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
		public String htmlContent2(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") String id) throws ServletException, IOException{
		   //判断是否登入
		   LoginSession session = SessionUtil.getSession();
		   boolean islog = session.getIsLog(); 
		   // 获取博客
		   Blog blog = getBlogById(islog, id); 
	       
	       if(blog==null){ 
	    	    return  "/error-page/404";
	       }else{
	    	   //标签
	    	  LazyList<BlogTag> taglist =	BlogTag.where("blog_id=?", id).load();  
		       //评论
		      List<BlogComment>  BlogCommentlist=BlogComment.where("post_id= ?  and parent_id is null ",id).load();
		      List<Map<String,Object>> rs = getcommentsById(id,BlogCommentlist); 
		      
		      request.setAttribute("data", blog);
	    	  request.setAttribute("tags", taglist);
		      request.setAttribute("isLog",islog); 
		      request.setAttribute("commentLength",BlogCommentlist.size()); 
		      request.setAttribute("comments",rs);  
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
		public Result getContent(@PathVariable("id") String id) throws ServletException, IOException{ 
	       Blog blog = Blog.findById(id); 
	       Result rs = new Result();
	       Map<String, Object> map = blog.toMap();
	       LazyList<BlogTag> taglist =	BlogTag.where("blog_id=?", id);
	       List<Map<String,Object>> rsl = new ArrayList<>();
	       for(BlogTag tag:taglist ){
	    	   rsl.add(tag.toMap());
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
				Blog.findById(id).delete();
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
//		@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)  
//		@Transactional(propagation=Propagation.REQUIRED,readOnly=true,timeout=1000,rollbackFor=Exception.class)
		public Result saveComment(HttpServletRequest request,@PathVariable("parentId") String parentId,@Valid VisitorDTO visitor) throws ServletException, IOException, InterruptedException{ 
		 
			boolean isLogin = SessionUtil.islogin();
			BlogComment comment = new BlogComment();
			if(isLogin){
				visitor.setName(myname);
				comment.setInteger("myselft",1);
							 
			} 
			
			comment.setString("post_id",visitor.getPostId());
			comment.setString("name",visitor.getName());
			String commentStr = visitor.getComment();
			commentStr = ToolsLib.deleteAllHTMLTag(commentStr);
			comment.setString("comment",commentStr);
			comment.setString("email", visitor.getEmail());
			comment.setString("url", visitor.getUrl());
			if(parentId !=null && !"".equals(parentId) &&  !"-1".equals(parentId)){
				comment.setString("parent_id", parentId);
			}
			comment.insert(); 
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
	       Blog blog = Blog.findById(id); 
	       Integer likecount = (blog.getInteger("post_like")+1);
	       blog.setInteger("post_like", likecount);
	       blog.saveIt();
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
		public Result commentLikePlusPlus(@PathVariable("id") String id) throws ServletException, IOException{ 
			 BlogComment blogComment =  BlogComment.findById(id); 
		       Integer likecount = (blogComment.getInteger("comment_like")+1);
		       blogComment.setInteger("comment_like", likecount);
		       blogComment.saveIt();
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
		
		/**
		 * 获取编辑文章页面
		 * @param id
		 * @return
		 * @throws ServletException
		 * @throws IOException
		 */
		@RequestMapping(value="/getEditPage/{id}",method = RequestMethod.GET)  
		public String getPageContent(HttpServletRequest request,@PathVariable("id") String id) throws ServletException, IOException{ 
	       Blog blog = Blog.findById(id);  
	       Map<String, Object> map = blog.toMap();
	       LazyList<BlogTag> taglist =	BlogTag.where("blog_id=?", id);
	       List<Map<String,Object>> rsl = new ArrayList<>();
	       for(BlogTag tag:taglist ){
	    	   rsl.add(tag.toMap());
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
		public Result  setTop(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") String id,@PathVariable("top") String top) throws ServletException, IOException{
			int i = 0;
			if("1".equals(top)) {
				 i = Blog.update("top = ?","id=?", 0,id);
			}else {
				 i = Blog.update("top = ?","id=?", 1,id);
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
		private Blog getBlogById( boolean islog, String id) {
			Blog blog;
			if(islog){ 
				blog = Blog.findById(id);
		    }else{
			    blog = Blog.findFirst("id=? and show_content=1", id);
		    }
			if(blog != null ){
			   Integer index = blog.getInteger("read_quantity"); 
	    	   blog.setInteger("read_quantity",index++ );
	    	   blog.saveIt(); 
			}
			
		    return blog;
		}
		
		/**
		 * 获取blog评论的子评论
		 * @param id
		 * @param BlogCommentlist
		 * @return
		 */
		private List<Map<String,Object>> getcommentsById(String id , List<BlogComment>  BlogCommentlist){
			
		      List<Map<String,Object>> rs = new ArrayList<Map<String,Object>>();
		      for(BlogComment bc:BlogCommentlist){ 
		    	  Map<String,Object> rmap = bc.toMap();
		    	  rmap.put("subcomment", BlogComment.where("post_id=? and parent_id=?", id,bc.getId()).load()); 
		    	  rs.add(rmap);
		      }
			return rs;  
			
		}
		 
}
