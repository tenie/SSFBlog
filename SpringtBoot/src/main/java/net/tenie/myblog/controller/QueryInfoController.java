package net.tenie.myblog.controller;

import java.util.Map;
 
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.tenie.myblog.service.CecheResult;
 



@Controller
@RequestMapping("/queryInfo")  
public class QueryInfoController {
 
	@RequestMapping(value="/sessionInfo",method = RequestMethod.GET)
	@ResponseBody
	public String sessionInfo(@RequestParam Map<String, String> queryParam) {
		 
		return CecheResult.sessionMapToString();
	}
	
	@RequestMapping(value="/setip",method = RequestMethod.GET)
	@ResponseBody
	public String setip(@RequestParam Map<String, String> queryParam) { 
		return CecheResult.getIp();
	}
	@RequestMapping(value="/ip",method = RequestMethod.GET) 
	@ResponseBody
	public String ip(@RequestParam Map<String, String> queryParam) { 
		return CecheResult.getIp();
	}
	
	@RequestMapping(value="/setvpnserver/{server}",method = RequestMethod.GET) 
	@ResponseBody
	public String setVpnServer(@PathVariable(value = "server") String server, @RequestParam Map<String, String> queryParam) { 
		if(server !=null && !"".equals(server)) {
			CecheResult.setVpnserver(server);
		}else {
			CecheResult.setVpnserver("null");
		}
		return server;
	}
	
	@RequestMapping(value="/getvpnserver",method = RequestMethod.GET) 
	@ResponseBody
	public String getVpnServer(@RequestParam Map<String, String> queryParam) {  
		if( CecheResult.getVpnserver() == null ||
				"".equals( CecheResult.getVpnserver() ) ){
			return "null";
		}else {
			return CecheResult.getVpnserver();
		}
	}
	
	@RequestMapping(value="/postTest",method = RequestMethod.POST) 
	@ResponseBody
	public String foo(@RequestParam Map<String, String> queryParam) { 
		System.out.println(  queryParam.get("name") ); 
		if( CecheResult.getVpnserver() == null ||
				"".equals( CecheResult.getVpnserver() ) ){
			return "null";
		}else {
			return CecheResult.getVpnserver();
		}
	}
	
	
	
}
