package com.wanma.app.controller;

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
import com.wanma.app.service.AppInstalladdressService;
import com.wanma.app.service.OrderService;
import com.wanma.model.Installaddress;
import com.wanma.model.TblOrderdetail;

/**
 * 用户收货地址控制器
 * 
 * @Description:
 * @author songjf
 * @createTime：2015-3-21 下午07:00:55
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/app/installAddress")
public class AppInstalladdressController extends BaseController {
	private static Log log = LogFactory
			.getLog(AppInstalladdressController.class);

	/** 安装地址业务处理对象 */
	@Autowired
	private AppInstalladdressService installaddressService;

	/** 订单业务处理对象 */
	@Autowired
	private OrderService orderService;

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
			// 返回登录信息错误信息
			return new AccessErrorResult(2002, "error.msg.invalid.parameter")
					.toString();
		}

		log.info("******************新增用户安装地址-end************************");
		// 返回处理成功信息
		return new AccessSuccessResult().toString();
	}

	/**
	 * @Title: findAddresses
	 * @Description: 获取用户安装地址
	 * @param params
	 * 
	 * @return
	 */
	@RequestMapping("/findAddresses")
	@ResponseBody
	public String findAddresses(Integer pradUserid) {
		log.info("******************获取用户安装地址-begin************************");

		List<Installaddress> addressList = null;

		try {
			addressList = installaddressService.findAddresses(pradUserid);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取用户收货地址错误", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(2004, "error.msg.invalid.parameter")
					.toString();
		}

		log.info("******************获取用户安装地址-end************************");
		// 返回处理成功信息
		return new AccessSuccessResult(addressList).toString();
	}

	/**
	 * @Title: getAddress
	 * @Description: 根据主键获取安装地址
	 * @param params
	 * 
	 * @return
	 */
	@RequestMapping("/getAddress")
	@ResponseBody
	public String getAddress(Integer pkInstalladdress) {
		log.info("******************根据主键获取安装地址 -begin************************");

		Installaddress installaddress = null;

		try {
			installaddress = installaddressService.getAddress(pkInstalladdress);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("根据主键获取安装地址 错误", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(2004, "error.msg.invalid.parameter")
					.toString();
		}

		log.info("******************根据主键获取安装地址 -end************************");
		// 返回处理成功信息
		return new AccessSuccessResult(installaddress).toString();
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
			// 返回登录信息错误信息
			return new AccessErrorResult(2001, "error.msg.invalid.parameter")
					.toString();
		}

		log.info("******************更新安装地址-end************************");
		// 返回处理成功信息
		return new AccessSuccessResult().toString();
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
			// 返回登录信息错误信息
			return new AccessErrorResult(2003, "error.msg.invalid.parameter")
					.toString();
		}

		log.info("******************删除安装地址-end************************");
		// 返回处理成功信息
		return new AccessSuccessResult().toString();
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
		try {
			// 登陆用户id
			String ordeUserid = RequestParamUtil.getEncodeParam(request,
					"ordeUserid");
			productList = orderService.selectNoAddProduct(ordeUserid);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取用户收货地址错误", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(2004, "error.msg.invalid.parameter")
					.toString();
		}

		log.info("******************获取已购买还未设置安装地址的商品 -end************************");
		return new AccessSuccessResult(productList).toString();
	}

}