package com.wanma.app.controller;

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

import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.bluemobi.product.utils.ObjectUtil;
import com.wanma.app.service.AppShoppingcartService;
import com.wanma.model.TblShoppingcart;

/**
 * @Description: 购物车控制器
 * @author songjf
 * @createTime：2015-3-16 下午05:53:55
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/app/shoppingcart")
public class AppShoppingcartController {
	private static Log log = LogFactory.getLog(AppShoppingcartController.class);
	/** 购物车业务处理对象 */
	@Autowired
	private AppShoppingcartService shoppingcartService;

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
			return new AccessErrorResult(1000,"error.msg.invalid.parameter")
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
			return new AccessErrorResult(2003,"error.msg.invalid.parameter")
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
			// 返回删除购物车信息错误信息
			return new AccessErrorResult(2004,"error.msg.invalid.parameter")
					.toString();
		}

		log.info("******************获取我的购物车列表-end************************");
		return new AccessSuccessResult(shoppingcartList).toString();
	}

}
