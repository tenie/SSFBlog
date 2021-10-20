package net.tenie.myblog.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import net.tenie.myblog.entity.Result;
import net.tenie.myblog.service.CecheResult;
import net.tenie.myblog.service.Search;
import net.tenie.myblog.service.VerificationCode;
import net.tenie.myblog.session.SessionUtil;

/**
 * main
 * 
 * @author tenie
 */
@Controller
public class mainController {
	Logger logger = LoggerFactory.getLogger(mainController.class);
	@Autowired
	private Search search;
	Integer limit = 10;
	@Value("${ssfblog.title}")
	private String blogTitle;
	@Value("${ssfblog.slogan}")
	private String slogan;

	@Value("${ssfblog.beianNo}")
	private String beianNo;
	
	@Value("${ssfblog.twitter}")
	private String twitter;
	
	@Value("${ssfblog.facebook}")
	private String facebook;
	
	@Value("${ssfblog.github}")
	private String github;
	
	@Value("${ssfblog.weixin}")
	private String weixin;
	@Value("${ssfblog.Copyright}")
	private String Copyright;
	
	
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(HttpServletRequest request, HttpServletResponse response, UriComponentsBuilder uriCB)
			throws ServletException {
		Integer offset = 0;
		logger.info("index_page data begin ");
		Result rs = null;
		// 判断是否登入
		boolean islogin = SessionUtil.islogin();

		CecheResult.getRs(islogin, rs);
		int blogcount = 0;
		// 结果集赋值
		if (rs == null) {
			rs = new Result();
			rs.setMapRs(search.indexSearch(islogin, limit, offset));
			// 缓存
			CecheResult.setRs(islogin, rs);

			blogcount = search.getBlogCount(islogin);
			CecheResult.setBlogcount(blogcount);
		}
		blogcount = CecheResult.getBlogcount();
		// 数据
		request.setAttribute("foo", rs);
		// 是否登入
		request.setAttribute("islogin", islogin ? "y" : "n");
		// 分页
		if (blogcount < limit)
			request.setAttribute("nextpage", -1);
		else
			request.setAttribute("nextpage", 1);
		request.setAttribute("previouspage", -1);

		request.setAttribute("blogTitle", blogTitle);
		request.setAttribute("slogan", slogan);

//		    ${nextpage}
		return "/index";
	}

	@RequestMapping(value = "/page/{page}", method = RequestMethod.GET)
	public String nextpage(HttpServletRequest request, HttpServletResponse response, @PathVariable("page") Integer page,
			UriComponentsBuilder uriCB) throws ServletException {

		Integer offset = limit * page;
		// 是否登入
		// 判断是否登入
		boolean islogin = SessionUtil.islogin();
		request.setAttribute("islogin", islogin ? "y" : "n");
		int blogcount = CecheResult.getBlogcount();
		if (blogcount == 0) {
			blogcount = search.getBlogCount(islogin);
		}
		int more = (limit + offset) - blogcount;
		if (more >= limit || page < 0) {
			return "/error-page/404";
		}
		Result rs = new Result();

		Map<String, Object> m = search.indexSearch(islogin, limit, offset);
		System.out.println(m);
		rs.setMapRs(m);
		// 数据
		request.setAttribute("foo", rs);
		if ((offset + limit) < blogcount) {
			request.setAttribute("nextpage", page + 1);
		} else {
			request.setAttribute("nextpage", -1);
		}
		request.setAttribute("previouspage", page - 1);
		
		request.setAttribute("blogTitle", blogTitle);
		request.setAttribute("slogan", slogan);

		return "/index";
	}

	
	@RequestMapping(value = "/footer", method = RequestMethod.GET)
	public String footer(HttpServletRequest request, HttpServletResponse response, UriComponentsBuilder uriCB)
			throws ServletException {
		request.setAttribute("twitter", twitter);
		request.setAttribute("facebook", facebook);
		request.setAttribute("github", github);
		request.setAttribute("weixin", weixin);
		request.setAttribute("beianNo", beianNo);
		request.setAttribute("Copyright", Copyright);
		return "/footer";
	}
	

	@RequestMapping(value = "/nav", method = RequestMethod.GET)
	public String nav(HttpServletRequest request, HttpServletResponse response, UriComponentsBuilder uriCB)
			throws ServletException {
		request.setAttribute("blogTitle", blogTitle);
	 
		return "/nav";
	}
	
	
	@Value("${ssfblog.aboutMe.title}")
	private String aboutMeTitle;
	@Value("${ssfblog.aboutMe.head1}")
	private String aboutMeHead1;
	@Value("${ssfblog.aboutMe.text1}")
	private String aboutMeText1;
	@Value("${ssfblog.aboutMe.head2}")
	private String aboutMeHead2;
	@Value("${ssfblog.aboutMe.text2}")
	private String aboutMeText2;
	
	
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String aboutme(HttpServletRequest request, HttpServletResponse response, UriComponentsBuilder uriCB)
			throws ServletException {
		request.setAttribute("blogTitle", blogTitle);
		
		request.setAttribute("aboutMeTitle", aboutMeTitle);
		
		request.setAttribute("aboutMeHead1", aboutMeHead1);
		request.setAttribute("aboutMeText1", aboutMeText1);
		request.setAttribute("aboutMeHead2", aboutMeHead2);
		request.setAttribute("aboutMeText2", aboutMeText2);
	 
		return "/about";
	}
	
	@Value("${ssfblog.myEmail}")
	private String myEmail;
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contact(HttpServletRequest request, HttpServletResponse response, UriComponentsBuilder uriCB)
			throws ServletException {
		request.setAttribute("myEmail", myEmail); 
	 
		return "/contact";
	}
	
	@Value("${ssfblog.photo}")
	private String photo;
	@RequestMapping(value = "/photo", method = RequestMethod.GET)
	public void photo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		   File file = new File(photo);
		   File file2 = new File("./");
		   String f2p = file2.getAbsolutePath();
		   System.out.println(f2p);
			if (file.exists()) {
				BufferedImage bufferedImage;
				try {
					bufferedImage = ImageIO.read(file);

					// .获取输出流
					OutputStream ops = response.getOutputStream();
					ImageIO.write(bufferedImage, "jpg", ops);
					ops.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} 
		
	}
	
	
	 
}
