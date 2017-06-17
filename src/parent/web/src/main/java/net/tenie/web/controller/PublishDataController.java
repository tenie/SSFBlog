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
@Controller
public class PublishDataController {

	@Autowired 
	private JdbcTemplate jdbc;
	@RequestMapping(value="/submitPublishdata",method = RequestMethod.POST)
	@ResponseBody
	public String savePublishData(HttpServletRequest request, HttpServletResponse response,@RequestParam Map<String, String> queryParam) throws ServletException, IOException{
      System.out.println("PublishDataController.savePublishData");
      String title = queryParam.get("title");
      String tag = queryParam.get("tag");
      System.out.println("title="+title);
      System.out.println("tag="+tag);
      String data = queryParam.get("data");
      System.out.println("data="+data);
      
     jdbc.update("insert into  blog  ( `post_title`, `post_subtitle`, `post_content`,`time`) values (?, ?, ?, ?)",title,tag,data,new Date());
      
      return "ok";
	}
}
