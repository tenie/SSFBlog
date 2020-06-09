package net.tenie.myblog.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import net.tenie.myblog.entity.Blog;
import net.tenie.myblog.entity.BlogTag;
import net.tenie.myblog.mapper.BlogMapper;
import net.tenie.myblog.mapper.BlogTagMapper;
import net.tenie.myblog.tools.ReflexBeanTools;
import net.tenie.myblog.tools.ToolsLib;
 

@Service
public class SearchImpl implements Search {

	@Autowired 
	private JdbcTemplate jdbc;
	
	@Autowired 
	private BlogTagMapper btm;
	@Autowired 
	private BlogMapper bm;
	
	
	@Override
	public List<Map<String, Object>> tagSearch(boolean isLogin, String tag ) {
		 List<Map<String, Object>> list=new ArrayList<>();
		 
		 tag = "#"+tag;
		 //登入过的查询
		 if(isLogin ){ 
			  list=jdbc.queryForList("SELECT DISTINCT B.ID,B.POST_TITLE,B.TIME,B.SHOW_CONTENT,B.TOP from blog_tag a  "
			  		+ " left JOIN blog b on b.id = a.blog_id  "
			  		+ "   where  1=1  and a.tag =?  ORDER BY b.top,b.id  DESC ",tag);
		        
		 }else{
			 list=jdbc.queryForList("SELECT DISTINCT B.ID,B.POST_TITLE,B.TIME,B.SHOW_CONTENT,B.TOP from blog_tag a  "
				  		+ " left JOIN blog b on b.id = a.blog_id  "
				  		+ "   where  1=1  and b.show_content=1 and a.tag =?  ORDER BY b.top,b.id  DESC ",tag); 
		 }
		 /**
		  * 1. 查出对应标签的文章
		  * 2. 查出对应文章的其他tag
		  */
		 List<Map<String, Object>> Rslist=new ArrayList<>();
		 for(Map<String,Object> map :list){
			 Integer id =  (Integer) map.get("id");
			 List<String> rstaglist = new ArrayList<>();
			 List<BlogTag> taglist =   btm.findByBlogId(Long.valueOf(id)); 
			 for(BlogTag tags:taglist){
				 rstaglist.add( tags.getTag());
			 }
			 map.put("tags", rstaglist);
			 Rslist.add(map);
		 }
		 return Rslist;
	}

	/**
	 * 根据文章标题搜索
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Override
	public List<Map<String, Object>> pageSearch(boolean isLogin,String val) throws Exception {
		 List<Blog> list=new ArrayList<>(); 
		 //登入过的查询 
		 if(isLogin ){  
			 list = bm.WhereAllPostTitle(val); 
		 }else{
			 list = bm.WherePortionPostTitle(val); 
		 }
		 
		 // 1. 查出对应标签的文章
		 // 2. 查出对应文章的其他tag
		 List<Map<String, Object>> Rslist=new ArrayList<>();
		 for(Blog po :list){  
		     Map<String, Object> pomap = ReflexBeanTools.beanToMap(po.getClass(), po);
			 Map<String, Object> map =   ToolsLib.mapKeyUp(pomap); 
			 Long id =  (Long) map.get("ID");
			 map.remove("postContent");
			 List<String> rstaglist = getBlogTagsById( id ); 
			 map.put("tags", rstaglist);
			 Rslist.add(map);
		 }
		return Rslist;
	}

	@Override
	public Map<String, Object>indexSearch(boolean islogin, Integer limit,Integer offset,String getCount) { 
		 List<Map<String, Object>> blogData=getBlogData(islogin,limit,offset); 
		 List<Map<String, Object>> blogDataAndTag=getBlogTag(blogData);  
		 String blogcount = getBlogCount(islogin,getCount);
		  //结果集赋值  
		 Map<String,Object> rsMap = new HashMap<String, Object>();
		 rsMap.put("signIn", islogin);
		 rsMap.put("Count", blogcount);
		 rsMap.put("dataList", blogDataAndTag); 
		 return rsMap;
	}
	public Map<String, Object>indexSearch(boolean islogin, Integer limit,Integer offset) { 
		 List<Map<String, Object>> blogData=getBlogData(islogin,limit,offset); 
		 List<Map<String, Object>> blogDataAndTag=getBlogTag(blogData);
		  //结果集赋值  
		 Map<String,Object> rsMap = new HashMap<String, Object>();
		 rsMap.put("signIn", islogin); 
		 rsMap.put("dataList", blogDataAndTag); 
		 return rsMap;
	}
	
	
	/**
	 * 获取blog 数据
	 * @param islogin
	 * @param limit
	 * @param offset
	 * @param getCount
	 * @return
	 */
	private List<Map<String, Object>> getBlogData(boolean islogin, Integer limit,Integer offset){ 
		 List<Map<String, Object>> list=new ArrayList<>();
		 //登入过的查询
		 if(islogin ){
			  list=jdbc.queryForList("SELECT id,post_title,time,show_content,top from blog where  1=1 ORDER BY top,id  DESC limit ? offset ?",limit,offset);
		 }else{
			 list=jdbc.queryForList("SELECT id,post_title,time,show_content,top from blog where show_content=1  ORDER BY top,id  DESC limit ? offset ?",limit,offset);
		 } 
		return list;
		
	}
	/**
	 * 获取博客文章总数
	 * @param islogin
	 * @param getCount
	 * @return
	 */
	public String  getBlogCount(boolean islogin, String getCount){
		List<Map<String, Object>> countList =new ArrayList<Map<String, Object>>();
		 if(islogin ){
			 //获取总行数,对分页最后页做判断时需要
		      if("1".equals(getCount)){
		    	 countList =  jdbc.queryForList("SELECT COUNT(ID) AS COUNT FROM BLOG");
		      }  
		 }else{
		      //获取总行数,对分页最后页做判断时需要
			 if("1".equals(getCount)){
		    	 countList =  jdbc.queryForList("SELECT count(id) as count from blog where show_content=1");
	         } 
		 }
		 String rsCount="";
		 if(countList.size()>0){
	    	  rsCount = ""+ countList.get(0).get("count");
	      }
		return rsCount;
		
	}
	public int  getBlogCount(boolean islogin){
		List<Map<String, Object>> countList =new ArrayList<Map<String, Object>>();
		 if(islogin ){
			 //获取总行数,对分页最后页做判断时需要 
		    	 countList =  jdbc.queryForList("SELECT COUNT(ID) AS COUNT FROM BLOG");
		     
		 }else{
		      //获取总行数,对分页最后页做判断时需要 
		    	 countList =  jdbc.queryForList("SELECT count(id) as count from blog where show_content=1");
	        
		 }
		 int rsCount= 0;
		 if(countList.size()>0){
			 rsCount = Integer.valueOf(  ""+ countList.get(0).get("count"));
	      }
		return rsCount;
		
	}
	/**
	 * 获取博客的标签
	 * @param list
	 * @return
	 */
	private List<Map<String, Object>>  getBlogTag( List<Map<String, Object>> list) {
		List<Map<String, Object>> Rslist=new ArrayList<>();
		for(Map<String,Object> map :list){
			Integer id =   (Integer) map.get("id"); 
			List<String> rstaglist = getBlogTagsById( Long.valueOf(id));
			map.put("tags",rstaglist);
			Rslist.add(map);
		 } 
		
		return Rslist;
	}
	
	/**
	 * 根据blog文章的ID找对应的tags
	 * @param id
	 * @return
	 */
	private List<String> getBlogTagsById(Long id){
		List<String> rstaglist = new ArrayList<>(); 
		List<BlogTag> taglist = btm.findByBlogId(id);
		for(BlogTag tags:taglist){
			 rstaglist.add( tags.getTag() );
		} 
		return rstaglist;
	}
	
	

}
