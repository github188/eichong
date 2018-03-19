package com.wanma.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.utils.RequestParamUtil;
import com.wanma.common.SessionMgr;
import com.wanma.model.Installaddress;
import com.wanma.model.TblOrderdetail;
import com.wanma.model.TblUseraddress;
import com.wanma.web.service.WebInstalladdressService;
import com.wanma.web.service.WebOrderService;
import com.wanma.web.support.common.FailedResponse;
import com.wanma.web.support.common.PageResponse;
import com.wanma.web.support.common.SuccessResponse;

/**
 * 用户安装地址控制器
 * 
 * @Description:
 * @author songjf
 * @createTime：2015-6-15 下午07:00:55
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/web/installAddress")
public class WebInstalladdressController {
	private static Log log = LogFactory.getLog(WebInstalladdressController.class);
	/** 安装地址业务处理对象 */
	@Autowired
	private WebInstalladdressService installaddressService;
	
	/** 订单业务处理对象 */
	@Autowired
	private WebOrderService orderService;
	
	/**
	 * @Title: findAddresses
	 * @Description: 获取用户安装地址
	 * @param params
	 * 
	 * @return
	 */
	@RequestMapping("/findAddresses")
	@ResponseBody
	public String findAddresses(HttpServletRequest request) {
		log.info("******************获取用户安装地址-begin************************");
//		String pradUserid = (String) request.getSession().getAttribute(
//				SessionMgr.WEB_SESSION_USER_pk);
		//登陆用户id
		String pradUserid = RequestParamUtil.getEncodeParam(request, "pradUserid");
		List<Installaddress> addressList = null;
		PageResponse<List<Installaddress>> result = null;
		try {
			result = new PageResponse<List<Installaddress>>(0, 0);
			addressList = installaddressService.findAddresses(Integer.parseInt(pradUserid));
			result.setDate(addressList);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取用户安装地址错误", e);
			System.out.println(e);
			// 返回错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}

		log.info("*****************获取用户安装地址-end************************");
		// 返回处理成功信息
		return result.toString();
	}
	
	/**
	 * @Title: insertAddress
	 * @Description: 新增用户安装地址
	 * @param params
	 * @return
	 */
	@RequestMapping("/insertAddress")
	@ResponseBody
	public String insertAddress(Installaddress installaddress) {
		log.info("******************新增用户安装地址-begin************************");

		try {
			installaddressService.insertAddress(installaddress);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("新增用户安装地址错误", e);
			// 返回错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}

		log.info("******************新增用户安装地址-end************************");
		// 返回处理成功信息
		return new SuccessResponse().toString();
	}
	
	/**
	 * @Title: getAddress
	 * @Description: 根据主键获取安装地址 
	 * @param params
	 * 
	 * @return
	 */
	@RequestMapping("/getAddress")
	public String getAddress(HttpServletRequest request, Model model) {
		log.info("******************根据主键获取安装地址 -begin************************");
		// 主键id
		String pkInstalladdress = RequestParamUtil.getEncodeParam(request, "a");

		Installaddress installaddress = null;

		try {
			installaddress = installaddressService.getAddress(Integer.parseInt(pkInstalladdress));
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("根据主键获取安装地址 错误", e);
			System.out.println(e);
			// 返回错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}
		model.addAttribute("installaddress", installaddress);
		log.info("******************根据主键获取安装地址 -end************************");
		// 返回处理成功信息
		return "frontend/energy/energy-editInstallAdrs";
	}
	
	/**
	 * @Title: updateAddress
	 * @Description: 更新安装地址
	 * @param params
	 * 
	 * @return
	 */
	@RequestMapping("/updateAddress")
	@ResponseBody
	public String updateAddress(Installaddress installaddress) {
		log.info("******************更新安装地址-begin************************");

		try {
			installaddressService.updateAddress(installaddress);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("更新安装地址错误", e);
			System.out.println(e);
			// 返回错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}

		log.info("******************更新安装地址-end************************");
		// 返回处理成功信息
		return new SuccessResponse().toString();
	}
	
	/**
	 * @Title: deleteAddress
	 * @Description: 删除安装地址
	 * @param params
	 * 
	 * @return
	 */
	@RequestMapping("/deleteAddress")
	@ResponseBody
	public String deleteAddress(Integer pkInstalladdress) {
		log.info("******************删除安装地址-begin************************");
		try {
			installaddressService.deleteAddress(pkInstalladdress);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("删除安装地址错误", e);
			// 返回错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}

		log.info("******************删除安装地址-end************************");
		// 返回处理成功信息
		return new SuccessResponse().toString();
	}
	
	/**
	 * @Title: selectNoAddProduct
	 * @Description: 获取已购买还未设置安装地址的商品
	 * @return
	 */
	@RequestMapping("/selectNoAddProduct")
	@ResponseBody
	public String selectNoAddProduct(HttpServletRequest request) {
		log.info("******************获取已购买还未设置安装地址的商品 -begin************************");

		List<TblOrderdetail> productList = null;
		PageResponse<List<TblOrderdetail>> result = null;
		try {
			// 登陆用户id
			String ordeUserid = RequestParamUtil.getEncodeParam(request,
					"ordeUserid");
			result = new PageResponse<List<TblOrderdetail>>(0, 0);
			productList = orderService.selectNoAddProduct(ordeUserid);
			result.setDate(productList);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取已购买还未设置安装地址的商品错误", e);
			System.out.println(e);
			// 返回错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}

		log.info("******************获取已购买还未设置安装地址的商品 -end************************");
		return result.toString();
	}
	
}
