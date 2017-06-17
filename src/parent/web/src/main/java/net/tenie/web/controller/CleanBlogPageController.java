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

import net.tenie.web.pojo.Result;

@Controller
@RequestMapping("/pageTitle")
public class CleanBlogPageController {
	private List<String> myList;
	
	CleanBlogPageController(){
//		System.out.println("创建ＧetCleanBlogPageController");
//		myList = new ArrayList();
	}
	@Autowired 
	private JdbcTemplate jdbc;
	
	 
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
      System.out.println("ＧetCleanBlogPageController.htmlView");
     
      List<Map<String, Object>> countList =new ArrayList();
      List<Map<String, Object>> list=jdbc.queryForList("select * from blog limit ? offset ?",limit,offset);
     //获取总行数,对分页最后页做判断时需要
      if("1".equals(getCount)){
    	 countList =  jdbc.queryForList("select count(id) as count from blog");
      }
       
      //结果集赋值
      Result rs = new Result(); 
      String rsCount="";
      Map<String,Object> rsMap = new HashMap();
      if(countList.size()>0){
    	  rsCount = ""+ countList.get(0).get("count");
      }
      rsMap.put("Count", rsCount);
      rsMap.put("dataList", list); 
      rs.setMapRs(rsMap); 
      return rs;
    }
	
	
	//获取某片文章的所以内容
	@RequestMapping(value="/postContent/{id}",method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> htmlContent(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") String id) throws ServletException, IOException{
      System.out.println("ＧetCleanBlogPageController.htmlContent");
      
       List<Map<String, Object>> list=jdbc.queryForList("select * from blog where id=? ",id); 
      
       return list.get(0);
    }
	
	

}
