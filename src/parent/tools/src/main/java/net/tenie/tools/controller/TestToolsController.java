package net.tenie.tools.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/tools/test")
public class TestToolsController {
	
	@Autowired
//    @Qualifier ( "dev1JdbcTemplate" )
	private JdbcTemplate jdbc;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public String test(@RequestParam Map<String, String> queryParam) {
		 System.out.println("queryCustomerInfo");
		 List<Map<String, Object>> ls= jdbc.queryForList("select * from foo");
		 System.out.println(ls);
		return "...";
	}
	
}
