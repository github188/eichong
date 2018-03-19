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
import com.bluemobi.product.utils.ObjectUtil;
import com.bluemobi.product.utils.StringUtil;
import com.wanma.app.service.AppProductService;
import com.wanma.model.TblProduct;

/**
 * @Description: 产品控制器
 * @author songjf
 * @createTime：2015-3-15 下午05:52:08
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/app/product")
public class AppProductController {
	private static Log log = LogFactory.getLog(AppProductController.class);

	/** 产品业务处理对象 */
	@Autowired
	private AppProductService appProductService;

	/**
	 * @Title: findProducts
	 * @Description: 获取产品列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/findProducts")
	@ResponseBody
	public String findProducts(@RequestParam Map<String, Object> params,
			AppPager pager) {
		log.info("******************获取产品列表-begin************************");
		// 分页参数
		params.put("pageNum", pager.getPageNum());
		params.put("pageNumber", pager.getPageNumber());
		List<TblProduct> productList = null;
		try {
			productList = appProductService.findProducts(params);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取产品列表错误", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(2004,"error.msg.invalid.parameter")
					.toString();
		}

		log.info("******************获取产品列表-end************************");
		// 返回处理成功信息
		return new AccessSuccessResult(productList).toString();
	}

	/**
	 * @Title: findProductDetail
	 * @Description: 获取产品详情
	 * @param params
	 * @return
	 */
	@RequestMapping("/findProductDetail")
	@ResponseBody
	public String findProductDetail(@RequestParam Map<String, Object> params) {
		log.info("******************获取产品详情-begin************************");
		Map<String, Object> product = null;
		try {
			product = appProductService.findProductDetail(params);
			if(null != product){
//				图片
				String prodDetailimage = (String) product.get("prodDetailimage");
				Double avgStart = (Double) product.get("avgStart");
				if(StringUtil.isEmpty(prodDetailimage)){
					product.put("prodDetailimage", "");
				}
				if(null == avgStart){
					product.put("avgStart", 0.00);
				}
			}
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取产品详情错误", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(1000,"error.msg.invalid.parameter")
					.toString();
		}

		log.info("******************获取产品列表-end************************");
		// 返回处理成功信息
		return new AccessSuccessResult(product).toString();
	}

	/**
	 * @Title: findProductInfo
	 * @Description:立即购买 获取商品信息
	 * @param params
	 * @return
	 */
	@RequestMapping("/findProductInfo")
	@ResponseBody
	public String findProductInfo(@RequestParam Map<String, Object> params) {
		log.info("******************立即购买 获取商品信息列表-end************************");
		Map<String, Object> productInfo = null;
		try {
			productInfo = appProductService.selectById(params);
			if (ObjectUtil.isNotEmpty(productInfo)) {
				String prodProductimage = (String) productInfo
						.get("prodProductimage");
				if (StringUtil.isEmpty(prodProductimage)) {
					productInfo.put("prodProductimage", "");
				}
			}
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("立即购买 获取商品信息错误", e);
			// 返回立即购买 获取商品错误信息
			return new AccessErrorResult(2004,"error.msg.invalid.parameter")
					.toString();
		}

		log.info("******************立即购买 获取商品信息列表-end************************");
		// 返回处理成功信息
		return new AccessSuccessResult(productInfo).toString();

	}

}