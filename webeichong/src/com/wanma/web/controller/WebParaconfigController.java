package com.wanma.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.utils.AccessSuccessResult;
import com.wanma.model.TblCarinfo;
import com.wanma.model.TblParaconfig;
import com.wanma.web.service.WebParaconfigService;
import com.wanma.web.service.impl.WebConfigContentServiceImpl;

/**
 * @Description:其他配置参数控制器
 * @author songjf
 * @createTime：2015-3-13 下午05:09:42
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("web/paraconfig")
public class WebParaconfigController {
	private static Log log = LogFactory.getLog(WebParaconfigController.class);
	/** 配置类型业务处理对象 */
	@Autowired
	private WebParaconfigService paraconfigService;
	@Autowired
	private WebConfigContentServiceImpl webConfigContentService;
	/**
	 * @Title: findParaconfigList
	 * @Description: 根据配置类型获取类型信息
	 * @param paraType
	 *            配置类型 1-车品牌 2-电桩搜索半径 3-设备报修类型 4-所属用户类型
	 * @return
	 */
	@RequestMapping("findParaconfigList")
	@ResponseBody
	public String findParaconfigList(@RequestParam Map<String, Object> params) {
		log.info("******************根据配置类型获取类型信息-begin************************");
		// 获取配置类型名称
		List<TblParaconfig> paraconfigList = paraconfigService
				.findParaconfigList(params);

		log.info("******************根据配置类型获取类型信息-end************************");
		return new AccessSuccessResult(paraconfigList).toString();
	}
	/**
	 * @Title: findCarinfoList
	 * @Description: 获取车型
	 * @param pkParaConfig
	 *            品牌id
	 * @return
	 */
	@RequestMapping("findCarCompanyList")
	@ResponseBody
	public String findCarCompanyList(@RequestParam Map<String, Object> params) {
		log.info("******************获取车型-begin************************");
		// 获取车型
		
		List<HashMap<String, Object>> carCompanyList = paraconfigService.findCarCompanyList(params);
		
		log.info("******************获取车型-end************************");
		return new AccessSuccessResult(carCompanyList).toString();
	}
	
	
	
	

	/**
	 * @Title: findCarinfoList
	 * @Description: 获取车型
	 * @param pkParaConfig
	 *            品牌id
	 * @return
	 */
	@RequestMapping("findCarinfoList")
	@ResponseBody
	public String findCarinfoList(@RequestParam Map<String, Object> params) {
		log.info("******************获取车型-begin************************");
		// 获取车型
		
		List<TblCarinfo> carList = paraconfigService.findCarinfoList(params);
		
		log.info("******************获取车型-end************************");
		return new AccessSuccessResult(carList).toString();
	}

	@RequestMapping("findConfigContentList")
	@ResponseBody
	public String findConfigContentListByCpId(String cpId){
		List<Map<String, Object>> list = webConfigContentService.getConfigContentListByCpId(cpId);
		
		return new AccessSuccessResult(list).toString();
	}
}
