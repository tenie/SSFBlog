package net.tenie.myblog.controller;

import java.io.IOException; 
import jakarta.servlet.ServletException; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import net.tenie.myblog.entity.Result;
import net.tenie.myblog.service.CecheResult;
import net.tenie.myblog.service.Search;
import net.tenie.myblog.session.SessionUtil;

 

@Controller
@RequestMapping("/pageTitle")
public class CleanBlogPageController {

	Logger logger = LoggerFactory.getLogger(CleanBlogPageController.class);
	@Autowired
	private Search search;

	/**
	 * 首页数据加载
	 * 
	 * @param limit
	 *            分页设置
	 * @param offset
	 *            分页偏移量
	 * @param getCount
	 *            该值为1时, 查找所有记录的总数
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = "/{getCount}/{limit}/{offset}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Result> htmlView(@PathVariable(value = "limit") Integer limit,
			@PathVariable(value = "offset") Integer offset, @PathVariable(value = "getCount") String getCount,
			UriComponentsBuilder uriCB) throws ServletException {

		logger.info("index_page data begin "); 
		Result rs = null;
		// 判断是否登入
		boolean islogin = SessionUtil.islogin();
		// 获取缓存
		if (offset == 0) {
			CecheResult.getRs(islogin, rs);
		}
		// 结果集赋值
		if (rs == null) {
			rs = new Result();
			rs.setMapRs(search.indexSearch(islogin,limit, offset, getCount));
			// 缓存
			if("1".equals(getCount)){
				CecheResult.setRs(islogin, rs);
			} 
		}

		MultiValueMap<String, String> headers = new HttpHeaders();
		headers.set("Location", uriCB.path("/" + getCount + "/" + limit + "/{offset}").buildAndExpand(offset).toUriString());
		return new ResponseEntity<Result>(rs, headers, HttpStatus.OK);
	}
 

}
