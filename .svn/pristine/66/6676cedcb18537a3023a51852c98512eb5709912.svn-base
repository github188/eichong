package com.wanma.controller;
 
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.common.BaseFail;
import com.base.common.BaseResult;
import com.base.common.BaseSuccess;
import com.base.common.SessionMgr;
import com.pub.model.Pager;
import com.pub.model.TblUser;
import com.wanma.service.impl.CmsFeeLimitServiceImpl;

/**
 * 运营管理-配置管理-服务费上限
 * @author bc
 *
 */
@Controller
@RequestMapping("admin/feelimit")
public class CmsFeeLimitController {

	// 日志输出对象
	private static Logger log = Logger.getLogger(CmsFeeLimitController.class);

	@Autowired
	CmsFeeLimitServiceImpl cmsFeeLimitService;
	
	@RequestMapping("feelimitPage")
	public String feelimitPage(){
		return "backstage/feelimit/feelimit";
	}
	
	@RequestMapping(value = "/getFeelimitList")
	@ResponseBody
	public String getFeeLimitList(@ModelAttribute("pager") Pager pager,
			@ModelAttribute HashMap<String, Object> params, String provinceId,
			Model model, HttpServletRequest request) {
		BaseResult baseResult=new BaseFail(5001);
		log.info("************获取费率上限列表-start************");
		List<HashMap<String, Object>> serviceLimitList = new ArrayList<HashMap<String, Object>>();
		params.put("offset", pager.getOffset());
		params.put("numPerPage", pager.getNumPerPage());
		params.put("provinceId", provinceId);
		long total = cmsFeeLimitService.searchServiceLimitCount(params);
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
			params.put("offset", 0);
		}
		serviceLimitList = cmsFeeLimitService.searchServiceLimitList(params);
		pager.setTotal(total);

		// 取得登录用户信息
		TblUser loginUser = SessionMgr.getWebUser(request);
		baseResult = new BaseResult(serviceLimitList, pager);
		log.info("************获取费率上限列表-end************");
		return baseResult.toString();
	}

	@RequestMapping("/feelimitDetail")
	@ResponseBody
	public String feelimitDetail(String cityId) {
		List<HashMap<String, Object>> serviceLimit = null;
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("cityId", cityId);
		serviceLimit = cmsFeeLimitService.findServiceLimit(params);
		return new BaseResult(serviceLimit).toString();
	}

	@RequestMapping("/feelimitModify")
	@ResponseBody
	public String feelimitModify(
			@ModelAttribute HashMap<String, Object> params,
			HttpServletRequest request) {
		log.info("************费率上限编辑-start************");
		String serviceLimit = request.getParameter("serviceLimit");
		BaseResult baseResult = new BaseSuccess();
		if (!serviceLimit.matches("^\\d+(\\.\\d+)?$")) {
			baseResult = new BaseFail("服务费上限不能为非数字");
			return baseResult.toString();
		}
		String cityId = request.getParameter("cityId");
		params.put("cityId", cityId);
		params.put("serviceLimit", serviceLimit);
		if (!cmsFeeLimitService.checkMaxHighFeel(params)) {
			return new BaseFail("低于同一个城市的已有费率的最高服务费值").toString();
		}
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy/MM/dd HH:mm:ss");// 可以方便地修改日期格式
		String lastUpdatedate = dateFormat.format(now);
		params.put("lastUpdatedate", lastUpdatedate);
		try {
			cmsFeeLimitService.updateServiceLimitById(params);
		} catch (Exception e) {
			log.error(this.getClass() + ".serviceLimitModify() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		log.info("******************费率上限编辑-end************************");
		// 返回处理结果信息
		return baseResult.toString();
	}
}
