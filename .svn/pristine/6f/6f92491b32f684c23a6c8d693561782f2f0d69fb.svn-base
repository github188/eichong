package com.wanma.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.controller.BaseController;
import com.bluemobi.product.utils.JacksonJsonMapper;
import com.wanma.model.OrderAddress;
import com.wanma.web.service.WebOrderAddressService;
import com.wanma.web.support.common.FailedResponse;
import com.wanma.web.support.common.SuccessResponse;

/**
 * 已购商品安装地址控制器
 * 
 * @Description:
 * @author songjf
 * @createTime：2015-3-21 下午07:00:55
 * @updator：
 * @updateTime：
 * @version：V1.0S
 */
@Controller
@RequestMapping("/web/OrderAddress")
public class WebOrderAddressController extends BaseController {
	private static Log log = LogFactory.getLog(WebOrderAddressController.class);

	/** 已购商品安装地址业务处理对象 */
	@Autowired
	private WebOrderAddressService orderAddressService;

	/**
	 * @Title: deleteOrderAddress
	 * @Description: 删除已购商品安装地址信息
	 * @param pkOrderaddress
	 * 		 已购商品安装地址主键
	 * @return
	 */
	@RequestMapping("/deleteOrderAddress")
	@ResponseBody
	public String deleteOrderAddress(Integer pkOrderaddress) {
		log.info("******************删除已购商品安装地址信息-begin************************");
		try {
			orderAddressService.deleteOrderAddress(pkOrderaddress);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("删除已购商品安装地址信息错误", e);
			// 返回错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}

		log.info("******************删除已购商品安装地址信息-end************************");
		// 返回处理成功信息
		return new SuccessResponse().toString();
	}

	

	/**
	 * @Title: insertOrderAddress
	 * @Description: 新增已购商品安装地址信息
	 * @param params
	 * @return
	 */
	@RequestMapping("/insertOrderAddress")
	@ResponseBody
	public String insertOrderAddress(String productIds,String productNames,String Quantities,String pkOrderdetails,
			int pkInstalladdress,HttpServletRequest request) {
		log.info("******************新增已购商品安装地址信息-begin************************");

		try {
			orderAddressService.insertOrderAddress(productIds,productNames,Quantities,pkOrderdetails,pkInstalladdress);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("新增已购商品安装地址信息错误", e);
			// 返回错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}

		log.info("******************新增已购商品安装地址信息-end************************");
		// 返回处理成功信息
		return new SuccessResponse().toString();
	}
	
}