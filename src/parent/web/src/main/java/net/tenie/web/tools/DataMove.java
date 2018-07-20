package net.tenie.web.tools;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.javalite.activejdbc.DB;
import org.javalite.activejdbc.LazyList;
 

import net.tenie.pojo.Blog;
import net.tenie.pojo.BlogTag;
import net.tenie.web.db.ConnectionPool;

public class DataMove {
	public static void main(String[] args) throws Exception { 
		 ConnectionPool.startDBServer("jdbc:h2:tcp://localhost:9092/~/config/web","sa","1qazxsw2","java -cp /Users/tenie/.m2/repository/com/h2database/h2/1.4.197/h2-1.4.197.jar org.h2.tools.Server -tcp -tcpPort 9092 ");  
		 Connection conn = ConnectionPool.getDirectConn("jdbc:h2:tcp://localhost:9092/~/config/ssfblog_db","sa","123456");
		 DB db = null;  
		 db =  new DB(); 
	     db.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/web?useSSL=false", "dev1", "123456");
	     Blog blog =  new Blog();
	     LazyList<Blog> l = blog.findAll();
	     for(Blog b : l){
	    	Integer id =    b.getInteger("id");
	    	String post_title = b.getString("post_title");
	    	String post_subtitle = b.getString("post_subtitle");
	    	Date time = b.getDate("time");
	    	String post_content = b.getString("post_content");
	    	Integer text_length = b.getInteger("text_length");
	    	Integer read_quantity =  b.getInteger("read_quantity");
	    	Integer comment = -1;// b.getInteger("comment");
	    	Integer post_like =   b.getInteger("post_like");
	    	Integer top = b.getInteger("top");
	    	Integer show_content =  b.getInteger("show_content");
	    	Date created_at =  b.getDate("created_at");
	    	Date updated_at =  b.getDate("updated_at");
	    	System.out.println("id == "+ id);
	    	
	    	// 插入h2
	    	java.sql.PreparedStatement stmt = null;
//	    	stmt  = conn.prepareStatement("INSERT INTO blog ("
//	    			+ " post_title, "
//	    			+ "post_subtitle,"
//	    			+ "time,"
//	    			+ "post_content,"
//	    			+ "text_length,"
//	    			+ "read_quantity,"
//	    			+ "comment,"
//	    			+ "post_like,"
//	    			+ "top,"
//	    			+ "show_content,"
//	    			+ "created_at,"
//	    			+ "updated_at "
//	    			+ ")values( ?,?,?,?,?,?,?,?,?,?,?,?"
//	    			+ ") ");
//	    	stmt.setString(1, post_title);
//	    	stmt.setString(2, post_subtitle);
//	    	stmt.setDate(3, time);
//	    	stmt.setString(4, post_content);
//	    	stmt.setInt(5, text_length);
//	    	stmt.setInt(6, read_quantity);
//	    	stmt.setInt(7, comment);
//	    	stmt.setInt(8, post_like);
//	    	stmt.setInt(9, top);
//	    	stmt.setInt(10, show_content);
//	    	stmt.setDate(11, created_at);
//	    	stmt.setDate(12, updated_at);
//	    	stmt.execute();
//	    	stmt.close();
	    	
	    	stmt = conn.prepareStatement("select id from blog where post_title = ?");
	    	stmt.setString(1,post_title);
	    	ResultSet rs =stmt.executeQuery();
	    	int blogid = 0;
	    	if(rs.next()){
	    		blogid = rs.getInt(1);
	    	}
	    	stmt.close();
	    	BlogTag bt = new BlogTag();
	    	LazyList<BlogTag> btl =  bt.where("blog_id = ? ", id);
	    	if(btl!=null )
	    	for(BlogTag t : btl){
	    		Integer blog_id = b.getInteger("blog_id");
	    		String tag = t.getString("tag");
	    		System.out.println("tag"+ tag);
	    		stmt = conn.prepareStatement("insert into blog_tag (blog_id, tag) values (?, ?)");
	    		stmt.setInt(1, blogid);
	    		stmt.setString(2,tag);
	    		stmt.execute();
	    		stmt.close();
	    	}
	    	
	     }	     
	}
}
