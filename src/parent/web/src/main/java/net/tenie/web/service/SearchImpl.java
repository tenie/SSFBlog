package net.tenie.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.javalite.activejdbc.LazyList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import net.tenie.pojo.Blog;
import net.tenie.pojo.BlogTag;
import net.tenie.web.session.SessionUtil;

@Service
public class SearchImpl implements Search {

	@Autowired 
	private JdbcTemplate jdbc;
	
	
	@Override
	public List<Map<String, Object>> tagSearch(String tag ) {
		 List<Map<String, Object>> list=new ArrayList<>();
		 //判断是否登入 
		 boolean bool =SessionUtil.islogin();
		 tag = "#"+tag;
		 //登入过的查询
		 if(bool ){
			 
			  list=jdbc.queryForList("select DISTINCT b.id,b.post_title,b.time,b.show_content,b.top from blog_tag a  "
			  		+ " left JOIN blog b on b.id = a.blog_id  "
			  		+ "   where  1=1  and a.tag =?  ORDER BY b.top,b.id  DESC ",tag);
		        
		 }else{
			 list=jdbc.queryForList("select DISTINCT b.id,b.post_title,b.time,b.show_content,b.top from blog_tag a  "
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
			 LazyList<BlogTag> taglist = BlogTag.where("blog_id=?", id);
			 for(BlogTag tags:taglist){
				 rstaglist.add( tags.getString("tag"));
			 }
			map.put("tags", rstaglist);
			Rslist.add(map);
		 }
		return Rslist;
	}

	@Override
	public List<Map<String, Object>> pageSearch(String val) {
		 List<Blog> list=new ArrayList<>();
		 //判断是否登入 
		 boolean bool =SessionUtil.islogin();
		 
		 //登入过的查询
		 if(bool ){
			 
			 list =  Blog.where("post_title like '%"+val+"%'").load(); 
		 }else{
			 list =  Blog.where(" show_content=? and post_title like '%"+val+"%' ",1).load(); 
			 
		 }
		 /**
		  * 1. 查出对应标签的文章
		  * 2. 查出对应文章的其他tag
		  */
		 List<Map<String, Object>> Rslist=new ArrayList<>();
		 for(Blog po :list){
		    Map<String, Object> map =  po.toMap();
			Integer id =  (Integer) map.get("id");
			map.remove("post_content");
			List<String> rstaglist = new ArrayList<>();
			 LazyList<BlogTag> taglist = BlogTag.where("blog_id=?", id);
			 for(BlogTag tags:taglist){
				 rstaglist.add( tags.getString("tag"));
			 }
			map.put("tags", rstaglist);
			Rslist.add(map);
		 }
		return Rslist;
	}

}