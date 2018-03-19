package com.wanma.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanma.model.WebList;
import com.wanma.web.service.WebListService;
import com.wanma.web.service.WebTblRecruitService;
import com.wanma.web.support.common.FailedResponse;
import com.wanma.web.support.common.PageResponse;
import com.wanma.web.support.utils.JsonUtil;

@Controller
@RequestMapping("/web/list")
public class WebListController {
	private static Log log = LogFactory.getLog(WebListController.class);

	@Autowired
	private WebListService listService;
	@Autowired
	private WebTblRecruitService webTblRecruitService;

	@RequestMapping("/findAll")
	@ResponseBody
	public String getAll(HttpServletRequest req, HttpServletResponse resp) {

		resp.setCharacterEncoding("UTF-8");
		List<WebList> list = null;
		list = listService.findAll();
		String list2json = JsonUtil.list2json(list);
		return list2json;

	}

	@RequestMapping("/findByType")
	@ResponseBody
	public String findByType(@RequestParam Map<String, Object> params, HttpServletResponse resp)
			throws UnsupportedEncodingException {
		Integer begin = null, pageSize = null;
		if (!StringUtils.isEmpty((String) params.get("pageSize"))) {
			pageSize = Integer.valueOf((String) params.get("pageSize"));
		}
		if (!StringUtils.isEmpty((String) params.get("pageNum"))) {
			begin = Integer.valueOf((String) params.get("pageNum"));
		}
		PageResponse<List<WebList>> pager = new PageResponse<List<WebList>>(
				begin, pageSize);
		params.put("pageNum", pager.getCount());
		params.put("pageNumber", pager.getBegin());
		try{
			String releType = (String) params.get("releType");
			if (StringUtils.isNotBlank(releType)) {
				String[] releTypes = releType.split(",");
				params.put("releTypes", releTypes);
				List<WebList> webList = listService.getAllByType(params);
				pager.setCountData(listService.countWebList(params));
				pager.setDate(webList);
			}
		}catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取消息信息错误", e);
			// 返回登录信息错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}
		return pager.toString();
	}
	
	@RequestMapping("/findByPk")
	@ResponseBody
	public String getByPk(HttpServletRequest req, HttpServletResponse resp) {

		int pkRelease = Integer.parseInt(req.getParameter("pkRelease"));
		resp.setCharacterEncoding("UTF-8");
		String s = "";
		WebList webList = listService.findByPk(pkRelease);
		s = JsonUtil.object2json(webList);
		return s;
	}
	
	@RequestMapping("/getAll")
	@ResponseBody
	public String getAllByType(@RequestParam Map<String, Object> params,
			HttpServletRequest request) throws UnsupportedEncodingException {
		log.info("******************获取消息信息-begin************************");
		Integer begin = null, pageSize = null;
		// 获取分页参数
		if (!StringUtils.isEmpty((String) params.get("pageSize"))) {
			pageSize = Integer.valueOf((String) params.get("pageSize"));
		}
		if (!StringUtils.isEmpty((String) params.get("pageNum"))) {
			begin = Integer.valueOf((String) params.get("pageNum"));
		}
		PageResponse<List<WebList>> pager = new PageResponse<List<WebList>>(
				begin, pageSize);
		params.put("pageNum", pager.getCount());
		params.put("pageNumber", pager.getBegin());
		try {
			params.put("releType", request.getParameter("releType"));
			List<WebList> webList = listService.getAllByType(params);
			pager.setCountData(listService.countWebList(params));
			pager.setDate(webList);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取消息信息错误", e);
			// 返回登录信息错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}
		log.info("******************获取消息信息-end************************");
		// 返回处理成功信息
		return pager.toString();
	}
	
	@RequestMapping("/detail")
	@ResponseBody
	public String viewDetailInfo(){
		
		return "frontend/alipay/deposit";
	}

}
