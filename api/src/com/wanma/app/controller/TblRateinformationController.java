package com.wanma.app.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.wanma.app.service.TblRateinformationService;
import com.wanma.app.util.FailedResponse;
import com.wanma.app.util.ResultResponse;
import com.wanma.model.TblRateinformation;

/**
 * @Description: 费率信息控制器
 * @author songjf
 * @createTime：2015-4-10 下午04:05:09
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/app/rateinformation")
public class TblRateinformationController extends BaseController {
	private static Log log = LogFactory
			.getLog(TblRateinformationController.class);
	// 费率信息处理对象
	@Autowired
	private TblRateinformationService rateinformationService;

	/**
	 * @Title: findRateInfo
	 * @Description: 根据电桩id获取电桩费率信息 此方法用于将数据返给app
	 * @param params
	 * @return
	 */
	@RequestMapping("/findRateInfo")
	@ResponseBody
	public String findRateInfo(@RequestParam Map<String, Object> params) {
		log.info("******************根据电桩id获取电桩费率信息-begin************************");
		// 根据电桩id获取电桩费率信息
		TblRateinformation rateinformation = null;
		try {
			rateinformation = rateinformationService.findRateInfo(params);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("根据电桩id获取电桩费率信息错误", e);
			return new AccessErrorResult(2004, "error.msg.invalid.parameter")
					.toString();
		}
		log.info("******************根据电桩id获取电桩费率信息-end************************");
		return new AccessSuccessResult(rateinformation).toString();
	}

	/** 列表 */
	@RequestMapping
	public String index(HttpServletRequest request) {
		return "/userinfo/list";
	}

	/**
	 * 通过主键pkRateInformation查询, 返回对象JSON
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String getDetail(@PathVariable Long id, HttpServletRequest request,
			Model model) {
		// 主键不能为空
		if (id == null) {
			return new FailedResponse(1050, "费率id不能为空").toString();
		}

		// 查询并获得结果
		TblRateinformation rateInfo = rateinformationService.getById(id);

		// 没有对应的pkRateInformation, 返回错误
		if (rateInfo == null) {
			log.error("费率id错误");
			return new FailedResponse(1050, "费率id错误").toString();
		}
		model.addAttribute("rateInfo", rateInfo);
		return new ResultResponse(rateInfo).toString();
	}

}
