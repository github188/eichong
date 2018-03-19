package com.wanma.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.controller.BaseController;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.bluemobi.product.utils.RequestParamUtil;
import com.wanma.common.SessionMgr;
import com.wanma.model.TblOrderdetail;
import com.wanma.model.TblUseraddress;
import com.wanma.web.service.WebOrderService;
import com.wanma.web.service.WebUseraddressService;
import com.wanma.web.support.common.FailedResponse;
import com.wanma.web.support.common.PageResponse;
import com.wanma.web.support.common.ResultResponse;
import com.wanma.web.support.common.SuccessResponse;

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
@RequestMapping("/web/userAddress")
public class WebUseraddressController extends BaseController {
	private static Log log = LogFactory.getLog(WebUseraddressController.class);

	/** 产品评论业务处理对象 */
	@Autowired
	private WebUseraddressService useraddressService;

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
			return new AccessErrorResult("error.msg.invalid.parameter")
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
			System.out.println(e);
			// 返回登录信息错误信息
			return new AccessErrorResult("error.msg.invalid.parameter")
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
			System.out.println(e);
			// 返回登录信息错误信息
			return new AccessErrorResult("error.msg.invalid.parameter")
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
			return new AccessErrorResult("error.msg.invalid.parameter")
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
			return new AccessErrorResult("error.msg.invalid.parameter")
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
			return new AccessErrorResult("error.msg.invalid.parameter")
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
	public String findDefault(int pkUserInfo) {
		log.info("******************获取用户默认地址-begin************************");

		Map<String, Object> resultMap = null;
		try {
			resultMap = useraddressService.findDefault(pkUserInfo);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e);
			log.error("更新用户默认地址错误", e);
			// 返回登录信息错误信息
			return new AccessErrorResult("error.msg.invalid.parameter")
					.toString();
		}

		log.info("******************获取用户默认地址-end************************");
		// 返回处理成功信息
		return new AccessSuccessResult(resultMap).toString();
	}

	// ************************web端************
	/**
	 * @Title: findDefault
	 * @Description: 获取用户默认地址
	 * @param params
	 * @return
	 */
	@RequestMapping("/findDefaultNew")
	@ResponseBody
	public String findDefaultNew(int pkUserInfo, int orderId) {
		log.info("******************获取用户默认地址-begin************************");

		TblUseraddress resultMap = null;
		try {
			resultMap = useraddressService.findDefaultNew(pkUserInfo);
			if (resultMap != null) {
				resultMap.setOrderId(orderId);
			}
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e);
			log.error("获取用户默认地址错误", e);
			// 返回错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}

		log.info("******************获取用户默认地址-end************************");
		// 返回处理成功信息
		return new ResultResponse<TblUseraddress>(resultMap).toString();
	}

	/**
	 * @Title: findAddresses
	 * @Description: 获取用户收货地址
	 * @param params
	 * 
	 * @return
	 */
	@RequestMapping("/findAddressesNew")
	@ResponseBody
	public String findAddressesNew(@RequestParam Map<String, Object> params) {
		log.info("******************获取用户收货地址-begin************************");

		List<TblUseraddress> productaddressList = null;
		PageResponse<List<TblUseraddress>> result = null;
		try {
			result = new PageResponse<List<TblUseraddress>>(0, 0);
			productaddressList = useraddressService.findAddresses(params);
			result.setDate(productaddressList);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取用户收货地址错误", e);
			System.out.println(e);
			// 返回错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}

		log.info("******************获取用户收货地址-end************************");
		// 返回处理成功信息
		return result.toString();
	}

	/**
	 * @Title: deleteAddress
	 * @Description: 删除用户收货地址
	 * @param params
	 * 
	 * @return
	 */
	@RequestMapping("/deleteAddressNew")
	@ResponseBody
	public String deleteAddressNew(String pkUseraddress) {
		log.info("******************删除用户收货地址-begin************************");
		try {
			useraddressService.deleteAddressNew(pkUseraddress);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("删除用户收货地址错误", e);
			// 返回错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}

		log.info("******************删除用户收货地址-end************************");
		// 返回处理成功信息
		return new SuccessResponse().toString();
	}

	/**
	 * @Title: updateIsDefaultNew
	 * @Description: 更新用户默认地址
	 * @param params
	 * 
	 * @return
	 */
	@RequestMapping("/updateIsDefaultNew")
	@ResponseBody
	public String updateIsDefaultNew(@RequestParam Map<String, Object> params) {
		log.info("******************更新用户默认地址-begin************************");

		try {
			useraddressService.updateIsDefault(params);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("更新用户默认地址错误", e);
			// 返回错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}

		log.info("******************更新用户默认地址-end************************");
		// 返回处理成功信息
		return new SuccessResponse().toString();
	}

	/**
	 * @Title: insertAddressNew
	 * @Description: 新增用户收货地址
	 * @param params
	 * @return
	 */
	@RequestMapping("/insertAddressNew")
	@ResponseBody
	public String insertAddressNew(TblUseraddress useraddress,
			HttpServletRequest request) {
		log.info("******************新增用户收货地址-begin************************");

		try {
			// 获取登录用户id
			// String pradUserid = (String) request.getSession().getAttribute(
			// SessionMgr.WEB_SESSION_USER_pk);
			// useraddress.setPradUserid(Integer.parseInt(pradUserid));
			useraddressService.insertAddress(useraddress);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("新增用户收货地址错误", e);
			// 返回错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}

		log.info("******************新增用户收货地址-end************************");
		// 返回处理成功信息
		return new SuccessResponse().toString();
	}

	/**
	 * @Title: getAddressNew
	 * @Description: 根据id获取用户收货地址
	 * @param params
	 * 
	 * @return
	 */
	@RequestMapping("/getAddressNew")
	public String getAddressNew(HttpServletRequest request, Model model) {
		log.info("******************根据id获取用户收货地址-begin************************");
		// 主键id
		String pkUseraddress = RequestParamUtil.getEncodeParam(request, "d");
		// 订单id
		String orderId = RequestParamUtil.getEncodeParam(request, "a");

		TblUseraddress useraddress = null;

		try {
			useraddress = useraddressService.getAddress(Integer
					.parseInt(pkUseraddress));
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("根据id获取用户收货地址错误", e);
			System.out.println(e);
			// 返回错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}
		model.addAttribute("useraddress", useraddress);
		model.addAttribute("orderId", orderId);
		log.info("******************根据id获取用户收货地址-end************************");
		// 返回处理成功信息
		return "frontend/energy/energy-editAdrs";
	}

	/**
	 * @Title: updateAddressNew
	 * @Description: 更新用户收货地址
	 * @param params
	 * 
	 * @return
	 */
	@RequestMapping("/updateAddressNew")
	@ResponseBody
	public String updateAddressNew(TblUseraddress useraddress) {
		log.info("******************更新用户收货地址-begin************************");

		try {
			useraddressService.updateAddress(useraddress);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("更新用户收货地址错误", e);
			System.out.println(e);
			// 返回错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}

		log.info("******************更新用户收货地址-end************************");
		// 返回处理成功信息
		return new SuccessResponse().toString();
	}

}