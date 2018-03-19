package com.wanma.controller.itf;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanma.model.TblRateInformation;
import com.wanma.service.TblRateInformationService;
import com.wanma.support.common.FailedResponse;
import com.wanma.support.common.ResultResponse;

/**
 * @Description:费率信息控制器
 * @author Dongyu
 * @createTime 2015/12/08 12:43
 * @updater
 * @updateTime
 * @version v1.0
 */
@Controller
@RequestMapping("/itf/rateinfo")
public class TblRateInformationController {
	/** 日志文件生成器 */
	private static Logger log = Logger
			.getLogger(TblRateInformationController.class);

	@Autowired
	TblRateInformationService tblRateInformationService;

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
			return new FailedResponse(1050,"费率id不能为空").toString();
		}

		// 查询并获得结果
		TblRateInformation rateInfo = tblRateInformationService.getById(id);

		// 没有对应的pkRateInformation, 返回错误
		if (rateInfo == null) {
			log.error("费率id错误");
			return new FailedResponse(1050,"费率id错误").toString();
		}

		model.addAttribute("rateInfo", rateInfo);
		return new ResultResponse(rateInfo).toString();
	}
}