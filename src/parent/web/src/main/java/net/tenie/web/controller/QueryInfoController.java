package net.tenie.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.tenie.web.service.CecheResult;



@Controller
@RequestMapping("/queryInfo")  //  /queryInfo/sessionInfo
public class QueryInfoController {
//	@Autowired
//	private Tool tool;
	
//	@Autowired
//	@Qualifier ( "dev1JdbcTemplate" )
//	private JdbcTemplate jdbc;
	@RequestMapping(value="/sessionInfo",method = RequestMethod.GET)
	@ResponseBody
	public String sessionInfo(@RequestParam Map<String, String> queryParam) {
		 
		return CecheResult.sessionMapToString();
	}
	
}
