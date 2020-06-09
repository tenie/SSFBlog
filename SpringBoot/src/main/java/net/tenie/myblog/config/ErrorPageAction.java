package net.tenie.myblog.config;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorPageAction {
    @RequestMapping(value = "/error400Page")
    public String error400Page() {
    	  return "error-page/404";
    }
    @RequestMapping(value = "/error401Page")
    public String error401Page() {
        return "error-page/401";
    }
    @RequestMapping(value = "/error404Page")
    public String error404Page(Model model) {
        model.addAttribute("code","6666666");
        model.addAttribute("msg","....");
        return "error-page/404";
    }
    @RequestMapping(value = "/error500Page")
    public String error500Page(Model model) {
//        return "/500";
    	 return "error-page/500";
    }
}