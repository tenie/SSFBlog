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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.tenie.web.pojo.Result;
@Controller
public class PublishDataController {

	@Autowired 
	private JdbcTemplate jdbc;
	@RequestMapping(value="/submitPublishdata",method = RequestMethod.POST)
	@ResponseBody
	public Result savePublishData(HttpServletRequest request, HttpServletResponse response,@RequestParam Map<String, Object> queryParam) throws ServletException, IOException{
      System.out.println("PublishDataController.savePublishData");
      String title = (String) queryParam.get("title"); 
      Integer textLength =Integer.valueOf( (String) queryParam.get("textLength")); 
      String tags[] = request.getParameterValues("tag");
      String tagVAL = "";
      for(String str : tags){
    	  tagVAL+=str+",";
      } 
     
      tagVAL=tagVAL.substring(0, tagVAL.length()-1);  
      String data = (String) queryParam.get("content"); 
      
      
     jdbc.update("insert into  blog  ( `post_title`, `post_subtitle`, `post_content`,`time`,text_length) values (?, ?, ?, ?,?)",title,tagVAL,data,new Date(),textLength);
      Result  rs= new Result();
      rs.setMsg("发布成功~");
      return rs;
	}
	
	 
}
