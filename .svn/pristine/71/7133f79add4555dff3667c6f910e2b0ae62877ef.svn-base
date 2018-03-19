package com.wanma.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.bluemobi.product.utils.RequestParamUtil;
import com.bluemobi.product.utils.StringUtil;
import com.wanma.model.TblProduct;
import com.wanma.model.TblSearchhistory;
import com.wanma.web.service.WebSearchhistoryService;

/**
 * 新能源搜索
 * 
 * @Description:
 * @author bruce cheng(http://blog.csdn.net/brucehome)
 * @createTime：2015-3-11 下午04:25:53
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/web/mallSearch")
public class WebMallSearchController {
	private static Log log = LogFactory.getLog(WebMallSearchController.class);

	@Autowired
	private WebSearchhistoryService tblSearchhistoryService;

	/**
	 * 获取商城搜索历史记录
	 */
	@RequestMapping("/getMallSearchHistory")
	@ResponseBody
	public String getMallSearchHistory(HttpServletRequest request){

		List<TblSearchhistory> tblSearchhistoryList = new ArrayList();
		try {
			tblSearchhistoryList = tblSearchhistoryService
					.getMallSearchHistory();
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取商城搜索历史记录数据失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult("error.msg.invalid.parameter")
					.toString();
		}
		// 返回处理成功信息
		return new AccessSuccessResult(tblSearchhistoryList).toString();
	}

	/**
	 * 删除商城搜索记录
	 */
	@RequestMapping("/removeMallSearchHistory")
	@ResponseBody
	public String removeMallSearchHistory(HttpServletRequest request){

		try {
			tblSearchhistoryService.removeMallSearchHistory();
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("删除商城搜索记录数据失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult("error.msg.invalid.parameter")
					.toString();
		}
		// 返回处理成功信息
		return new AccessSuccessResult().toString();
	}

	/**
	 * 获取商城搜索记录
	 */
	@RequestMapping("/getMallSearchList")
	@ResponseBody
	public String getMallSearchList(HttpServletRequest request){

		// 搜索名称
		String searchName = RequestParamUtil.getEncodeParam(request,
				"searchName");

		List<TblProduct> tblProductList = new ArrayList();
		try {

			if (StringUtil.isEmpty(searchName)) {
				// 未输入 电桩ID
				return new AccessErrorResult("error.msg.invalid.parameter")
						.toString();
			}
			TblSearchhistory tblSearchhistory = new TblSearchhistory();
			tblSearchhistory.setSehiSearchhistory(searchName);
			tblSearchhistory.setSehiCreatedate(new Date());
			tblSearchhistory.setSehiUpdatedate(new Date());

			TblProduct tblProduct = new TblProduct();
			tblProduct.setProdProductname(searchName);
			tblProductList = tblSearchhistoryService
					.findProductListByShopSearch(tblSearchhistory, tblProduct);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取搜索记录数据失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult("error.msg.invalid.parameter")
					.toString();
		}
		// 返回处理成功信息
		return new AccessSuccessResult(tblProductList).toString();
	}

}