package com.wanma.app.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.common.AppPager;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.wanma.app.service.AppUseraddressService;
import com.wanma.model.TblUseraddress;

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
@RequestMapping("/app/userAddress")
public class AppUseraddressController extends BaseController {
	private static Log log = LogFactory.getLog(AppUseraddressController.class);

	/** 产品评论业务处理对象 */
	@Autowired
	private AppUseraddressService useraddressService;

	/**
	 * @Title: insertAddress
	 * @Description: 新增用户收货地址
	 * @param params
	 * @return
	 */
	@RequestMapping("/insertAddress")
	@ResponseBody
	public String insertAddress(TblUseraddress useraddress) {
		log.info("******************新增用户收货地址-begin************************");

		try {
			useraddressService.insertAddress(useraddress);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("新增用户收货地址错误", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(2002,"error.msg.invalid.parameter")
					.toString();
		}

		log.info("******************新增用户收货地址-end************************");
		// 返回处理成功信息
		return new AccessSuccessResult().toString();
	}

	/**
	 * @Title: findAddresses
	 * @Description: 获取用户收货地址
	 * @param params
	 * 
	 * @return
	 */
	@RequestMapping("/findAddresses")
	@ResponseBody
	public String findAddresses(@RequestParam Map<String, Object> params) {
		log.info("******************获取用户收货地址-begin************************");

		List<TblUseraddress> productaddressList = null;

		try {
			productaddressList = useraddressService.findAddresses(params);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取用户收货地址错误", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(2004,"error.msg.invalid.parameter")
					.toString();
		}

		log.info("******************获取用户收货地址-end************************");
		// 返回处理成功信息
		return new AccessSuccessResult(productaddressList).toString();
	}

	/**
	 * @Title: getAddress
	 * @Description: 根据id获取用户收货地址
	 * @param params
	 * 
	 * @return
	 */
	@RequestMapping("/getAddress")
	@ResponseBody
	public String getAddress(Integer pkUseraddress) {
		log.info("******************根据id获取用户收货地址-begin************************");

		TblUseraddress productaddress = null;

		try {
			productaddress = useraddressService.getAddress(pkUseraddress);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("根据id获取用户收货地址错误", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(2004,"error.msg.invalid.parameter")
					.toString();
		}

		log.info("******************根据id获取用户收货地址-end************************");
		// 返回处理成功信息
		return new AccessSuccessResult(productaddress).toString();
	}

	/**
	 * @Title: updateAddress
	 * @Description: 更新用户收货地址
	 * @param params
	 * 
	 * @return
	 */
	@RequestMapping("/updateAddress")
	@ResponseBody
	public String updateAddress(TblUseraddress useraddress) {
		log.info("******************更新用户收货地址-begin************************");

		try {
			useraddressService.updateAddress(useraddress);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("更新用户收货地址错误", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(2001,"error.msg.invalid.parameter")
					.toString();
		}

		log.info("******************更新用户收货地址-end************************");
		// 返回处理成功信息
		return new AccessSuccessResult().toString();
	}

	/**
	 * @Title: deleteAddress
	 * @Description: 删除用户收货地址
	 * @param params
	 * 
	 * @return
	 */
	@RequestMapping("/deleteAddress")
	@ResponseBody
	public String deleteAddress(@RequestParam Map<String, Object> params) {
		log.info("******************删除用户收货地址-begin************************");

		try {
			useraddressService.deleteAddress(params);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("删除用户收货地址错误", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(2003,"error.msg.invalid.parameter")
					.toString();
		}

		log.info("******************删除用户收货地址-end************************");
		// 返回处理成功信息
		return new AccessSuccessResult().toString();
	}

	/**
	 * @Title: updateIsDefault
	 * @Description: 更新用户默认地址
	 * @param params
	 * 
	 * @return
	 */
	@RequestMapping("/updateIsDefault")
	@ResponseBody
	public String updateIsDefault(@RequestParam Map<String, Object> params) {
		log.info("******************更新用户默认地址-begin************************");

		try {
			useraddressService.updateIsDefault(params);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("更新用户默认地址错误", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(2001,"error.msg.invalid.parameter")
					.toString();
		}

		log.info("******************更新用户默认地址-end************************");
		// 返回处理成功信息
		return new AccessSuccessResult().toString();
	}
	
	/**
	 * @Title: findDefault
	 * @Description: 获取用户默认地址
	 * @param params
	 * @return 
	 */
	@RequestMapping("/findDefault")
	@ResponseBody
	public String findDefault(int pkUserInfo){
		log.info("******************获取用户默认地址-begin************************");
		
		Map<String, Object> resultMap = null;
		try {
			resultMap = useraddressService.findDefault(pkUserInfo);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e);
			log.error("更新用户默认地址错误", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(2004,"error.msg.invalid.parameter")
					.toString();
		}
		
		log.info("******************获取用户默认地址-end************************");
		// 返回处理成功信息
		return new AccessSuccessResult(resultMap).toString();
	}
}