package com.wanma.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.bluemobi.product.utils.ObjectUtil;
import com.bluemobi.product.utils.RequestParamUtil;
import com.wanma.common.SessionMgr;
import com.wanma.model.TblShoppingcart;
import com.wanma.web.service.WebShoppingcartService;
import com.wanma.web.support.common.FailedResponse;
import com.wanma.web.support.common.PageResponse;
import com.wanma.web.support.common.ResultResponse;

/**
 * @Description: 购物车控制器
 * @author songjf
 * @createTime：2015-3-16 下午05:53:55
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/web/shoppingcart")
public class WebShoppingcartController {
	private static Log log = LogFactory.getLog(WebShoppingcartController.class);
	/** 购物车业务处理对象 */
	@Autowired
	private WebShoppingcartService shoppingcartService;

	/**
	 * @Title: insertIntoCart
	 * @Description: 加入购物车
	 * @param shoppingcart
	 * @return
	 */
	@RequestMapping("/insertIntoCart")
	@ResponseBody
	public String insertIntoCart(TblShoppingcart shoppingcart) {
		log.info("******************加入购物车-begin************************");

		try {
			// 判断此商品购物车中是否存在
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("shcaProductid", shoppingcart.getShcaProductid());
			params.put("shcaUserid", shoppingcart.getShcaUserid());
			TblShoppingcart shTblShoppingcart = shoppingcartService
					.selectByshCaProductid(params);
			if (ObjectUtil.isNotEmpty(shTblShoppingcart)) {
				int count = shTblShoppingcart.getShcaCount();
				count = count + 1;
				shTblShoppingcart.setShcaCount(count);
				shoppingcartService.updateCart(shTblShoppingcart);
			} else {
				shoppingcart.setShcaCount(1);
				shoppingcartService.insertIntoCart(shoppingcart);
			}

		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("加入购物车错误", e);
			// 返回登录信息错误信息
			return new AccessErrorResult("error.msg.invalid.parameter")
					.toString();
		}

		log.info("******************加入购物车-end************************");
		return new AccessSuccessResult().toString();
	}

	/**
	 * @Title: deleteCart
	 * @Description: 删除购物车信息
	 * @param params
	 * @return
	 */
	@RequestMapping("deleteCart")
	@ResponseBody
	public String deleteCart(@RequestParam Map<String, Object> params) {
		log.info("******************删除购物车信息-begin************************");

		try {
			shoppingcartService.deleteCart(params);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("删除购物车信息错误", e);
			// 返回删除购物车信息错误信息
			return new AccessErrorResult("error.msg.invalid.parameter")
					.toString();
		}

		log.info("******************删除购物车信息-end************************");
		return new AccessSuccessResult().toString();
	}

	/**
	 * @Title: findMyCart
	 * @Description: 获取我的购物车列表
	 * @param params
	 * @return
	 */
	@RequestMapping("findMyCart")
	@ResponseBody
	public String findMyCart(int pkUserInfo) {
		log.info("******************获取我的购物车列表-begin************************");
		List<TblShoppingcart> shoppingcartList = null;
		try {
			shoppingcartList = shoppingcartService.findMyCart(pkUserInfo);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取我的购物车列表错误", e);
			System.out.println(e);
			// 返回删除购物车信息错误信息
			return new AccessErrorResult("error.msg.invalid.parameter")
					.toString();
		}

		log.info("******************获取我的购物车列表-end************************");
		return new AccessSuccessResult(shoppingcartList).toString();
	}
	
	/**
	 * @Title: insertIntoCartNew
	 * @Description: 加入购物车
	 * @param shoppingcart
	 * @return
	 */
	@RequestMapping("/insertIntoCartNew")
	@ResponseBody
	public String insertIntoCartNew(TblShoppingcart shoppingcart,
			HttpServletRequest request) {
		log.info("******************加入购物车-begin************************");
		try {
			// 获取登录用户id
//			String shcaUserid = (String) request.getSession().getAttribute(
//					SessionMgr.WEB_SESSION_USER_pk);
			int shcaUserid = shoppingcart.getShcaUserid();
			// 购买数量
			int shcaCount = shoppingcart.getShcaCount();

			// 判断此商品购物车中是否存在
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("shcaProductid", shoppingcart.getShcaProductid());
			params.put("shcaUserid", shcaUserid);
			TblShoppingcart shTblShoppingcart = shoppingcartService
					.selectByshCaProductidNew(params);
			shoppingcart.setShcaUserid(shcaUserid);
			if (ObjectUtil.isNotEmpty(shTblShoppingcart)) {
				int count = shTblShoppingcart.getShcaCount();
				count = count + shcaCount;
				shTblShoppingcart.setShcaCount(count);
				shoppingcartService.updateCart(shTblShoppingcart);
			} else {
				shoppingcartService.insertIntoCart(shoppingcart);
			}
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			System.out.println(e);
			log.error("加入购物车错误", e);
			// 返回错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}

		log.info("******************加入购物车-end************************");
		// 返回处理成功信息
		return new ResultResponse().toString();
	}
	
	/**
	 * @Title: deleteCartNew
	 * @Description: 删除购物车信息
	 * @param params
	 * @return
	 */
	@RequestMapping("deleteCartNew")
	@ResponseBody
	public String deleteCartNew(HttpServletRequest request) {
		log.info("******************删除购物车信息-begin************************");

		try {
			// 获取登录用户id
						String shcaUserid = (String) request.getSession().getAttribute(
								SessionMgr.WEB_SESSION_USER_pk);
			String shcaProductid = RequestParamUtil.getEncodeParam(request, "shcaProductid");
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("shcaUserid", shcaUserid);
			map.put("shcaProductid", shcaProductid);
			shoppingcartService.deleteCartNew(map);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("删除购物车信息错误", e);
			// 返回删除购物车信息错误信息
			return new AccessErrorResult("error.msg.invalid.parameter")
					.toString();
		}

		log.info("******************删除购物车信息-end************************");
		return new AccessSuccessResult().toString();
	}
	
	/**
	 * @Title: findMyCart
	 * @Description: 获取我的购物车列表
	 * @param params
	 * @return
	 */
	@RequestMapping("findMyCartNew")
	@ResponseBody
	public String findMyCartNew(HttpServletRequest request) {
		log.info("******************获取我的购物车列表-begin************************");
		// 获取登录用户id
		String pkUserInfo = (String) request.getSession().getAttribute(
				SessionMgr.WEB_SESSION_USER_pk);
		List<TblShoppingcart> shoppingcartList = null;
		PageResponse<List<TblShoppingcart>> result = null;
		try {
			shoppingcartList = shoppingcartService.findMyCartNew(Integer
					.parseInt(pkUserInfo));
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取我的购物车列表错误", e);
			System.out.println(e);
			// 返回错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}
		result = new PageResponse<List<TblShoppingcart>>(0, 0);
		result.setDate(shoppingcartList);
		log.info("******************获取我的购物车列表-end************************");
		return result.toString();
	}

}
