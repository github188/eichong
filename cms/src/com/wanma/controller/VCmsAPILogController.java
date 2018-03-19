package com.wanma.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.common.SpringContextHolder;
import com.base.common.WanmaConstants;
import com.pub.model.Pager;
import com.pub.model.TblUser;
import com.wanma.model.ApiLog;
import com.wanma.model.ApiLog.ComparatorApiLog;
import com.wanma.service.impl.RedisService;

/**
 * API接口调用日志
 * 
 * @author wangfei
 * 
 */
@Controller
@RequestMapping("/admin/apilog")
public class VCmsAPILogController {

	// 日志输出对象
	private static Logger log = Logger.getLogger(VCmsAPILogController.class);

	@Autowired
	private RedisService redisService;

	// API接口统计列表
	@RequestMapping(value = "/apilogList")
	public String getApiLogList(@ModelAttribute("pager") Pager pager,
			Model model, HttpServletRequest request) {

		String keyPattern = "app:visit:*";

		Set<String> apiLogList = redisService.getTotalRecords(keyPattern);

		List<ApiLog> listStrArr = new ArrayList<ApiLog>();

		for (String apiLogKey : apiLogList) {

			String value = redisService.strGet(apiLogKey).toString();
			String[] apiurls = apiLogKey.split(":");
			String _url = "";

			if (null != apiurls && apiurls.length > 0) {
				_url = apiurls[apiurls.length - 1];
			}

			listStrArr.add(new ApiLog(_url, value));
		}
		ComparatorApiLog comparator = new ComparatorApiLog();
		Collections.sort(listStrArr, comparator);

		// 取得登录用户信息
		System.out.println(apiLogList.toString());
		TblUser loginUser = (TblUser) request.getSession().getAttribute(
				WanmaConstants.SESS_PRINCIPAL);

		// 将日志信息放到会话中
		model.addAttribute("loginUserLevel", loginUser.getUserLevel());
		model.addAttribute("apiLogList", listStrArr);
		model.addAttribute("pager", pager);

		// 跳转至日志信息
		return "backstage/apilog/listapilog";
	}

	public static void main(String[] args) {
		RedisService redisService = (RedisService) SpringContextHolder
				.getBean("redisService");

		redisService.listGetAll("");

	}
}
