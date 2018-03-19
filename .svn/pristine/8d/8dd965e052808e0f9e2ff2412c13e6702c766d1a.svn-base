package com.wanma.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bluemobi.product.exceptions.AppRequestErrorException;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.bluemobi.product.utils.RequestParamUtil;
import com.bluemobi.product.utils.StringUtil;
import com.wanma.app.service.OrderService;
import com.wanma.common.ApplicationConsts;
import com.wanma.common.JudgeNullUtils;
import com.wanma.model.AffirmOrder;
import com.wanma.model.MyOrder;
import com.wanma.model.TblOrder;

/**
 * 我的订单
 * 
 * @Description:
 * @author bruce cheng(http://blog.csdn.net/brucehome)
 * @createTime：2015-3-11 下午04:25:53
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/app/myOrder")
public class OrderController {
	private static Logger log = Logger.getLogger(OrderController.class);

	@Autowired
	private OrderService orderService;

	/**
	 * 获取我的订单
	 */
	@RequestMapping("/getMyOrder")
	@ResponseBody
	public String getMyOrder(HttpServletRequest request)
			throws AppRequestErrorException {

		// 订单类型 1-搜索全部 2-搜索待支付 3-搜索待安装 4-搜索已完成
		String orderType = RequestParamUtil
				.getEncodeParam(request, "orderType");
		String userId = RequestParamUtil.getEncodeParam(request, "userId");
		List<MyOrder> myOrderList = new ArrayList();
		try {

			if (StringUtil.isEmpty(orderType)) {
				// 未输入 电桩ID
				return new AccessErrorResult(1050,"error.msg.miss.parameter")
						.toString();
			}
			MyOrder myOrder = new MyOrder();
			myOrder.setUserId(userId);
			// -----获取消费订单-----1-搜索全部 2-搜索待支付 3-搜索待安装 4-搜索已完成
			if (orderType
					.equals(JudgeNullUtils
							.nvlStr(ApplicationConsts.ElectricPileManager.PAGE_ORDER_ALL))) {// 搜索全部
				myOrderList = orderService.getMyOrder(myOrder);
			} else if (orderType
					.equals(JudgeNullUtils
							.nvlStr(ApplicationConsts.ElectricPileManager.PAGE_ORDER_NO_PAY))) {// 搜索待支付消费订单
				myOrder.setOrderState(JudgeNullUtils
						.nvlStr(ApplicationConsts.ElectricPileManager.ORDER_SHOP_NOPAY));
				myOrderList = orderService.getShopOrde(myOrder);
			} else if (orderType
					.equals(JudgeNullUtils
							.nvlStr(ApplicationConsts.ElectricPileManager.PAGE_ORDER_NO_INSTALL))) {// 搜搜待安装订单
				myOrder.setOrderState(JudgeNullUtils
						.nvlStr(ApplicationConsts.ElectricPileManager.ORDER_NO_INSTALL));
				myOrderList = orderService.getInstallOrde(myOrder);
			} else if (orderType
					.equals(JudgeNullUtils
							.nvlStr(ApplicationConsts.ElectricPileManager.PAGE_ORDER_FINSH))) {// 已完成
				myOrder.setOrderState(JudgeNullUtils.nvlStr(JudgeNullUtils
						.nvlStr(ApplicationConsts.ElectricPileManager.ORDER_SHOP_PAY)));
				myOrderList = orderService.getMyOrder(myOrder);
			}
		} catch (Exception e) {
			// 保存错误LOG
			log.error("获取我的订单数据失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(1000,"error.msg.invalid.parameter")
					.toString();
		}
		// 返回处理成功信息
		return new AccessSuccessResult(myOrderList).toString();
	}

	/**
	 * 用户确认订单
	 */
	@RequestMapping("/affirmUserOrder")
	@ResponseBody
	public String AffirmUserOrder(HttpServletRequest request)
			throws AppRequestErrorException {

		// 确认订单列表
		String affirmOrderList = RequestParamUtil.getEncodeParam(request,
				"affirmOrderList");
		AffirmOrder affirmOrder = new AffirmOrder();
		try {

			if (StringUtil.isEmpty(affirmOrderList)) {
				// 未输入 电桩ID
				return new AccessErrorResult(1050,"error.msg.miss.parameter")
						.toString();
			}
			
			affirmOrder = JSONObject.toJavaObject(JSONObject.parseObject(affirmOrderList),AffirmOrder.class);
			affirmOrder = orderService.AffirmUserOrder(affirmOrder);
		} catch (Exception e) {
			// 保存错误LOG
			log.error("用户确认订单失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(1000,"error.msg.invalid.parameter")
					.toString();
		}
		// 返回处理成功信息
		return new AccessSuccessResult(affirmOrder).toString();
	}

	/**
	 * @Title: insertOrderInfo
	 * @Description: 确认订单，插入订单信息
	 * @param params
	 * @return
	 */
	@RequestMapping("/insertOrderInfo")
	@ResponseBody
	public String insertOrderInfo(TblOrder order,
			@RequestParam("shcaProductids") String shcaProductids,
			@RequestParam("prodProductNames") String prodProductNames,
			@RequestParam("shcaCounts") String shcaCounts,
			@RequestParam("prodPrices") String prodPrices) {
		log.info("******************确认订单，插入订单信息-begin************************");
//		插入订单信息
		int pkOrder = orderService.insertOrderInfo(order, shcaProductids,
				prodProductNames, shcaCounts, prodPrices);
		
		Map<String,Object> resultMap = new HashMap<String, Object>();
		resultMap.put("pkOrder", pkOrder);

		log.info("******************确认订单，插入订单信息-end************************");
		// 返回处理成功信息
		return new AccessSuccessResult(resultMap).toString();
	}

	/**
	 * @Title: selectOrderDetails
	 * @Description:支付成功 获取订单详情
	 * @param pkOrder
	 *            订单id
	 * @return
	 */
	@RequestMapping("/selectOrderDetails")
	@ResponseBody
	public String selectOrderDetails(Integer pkOrder) {
		log.info("******************支付成功  获取订单详情-begin************************");
		Map<String, Object> resultMap = null;
		try {
			resultMap = orderService.selectOrderDetails(pkOrder);
		} catch (Exception e) {
			// 保存错误LOG
			log.error("支付成功  获取订单详情失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(2004,"error.msg.invalid.parameter")
					.toString();
		}

		log.info("******************支付成功  获取订单详情-end************************");
		// 返回处理成功信息
		return new AccessSuccessResult(resultMap).toString();
	}

	/**
	 * @Title: deleteOrder
	 * @Description: 删除订单 更新订单状态为删除状态
	 * @param params
	 * @return
	 */
	@RequestMapping("/deleteOrder")
	@ResponseBody
	public String deleteOrder(@RequestParam Map<String, Object> params) {
		log.info("******************删除订单 更新订单状态为删除状态-begin************************");

		try {
			orderService.deleteOrder(params);
		} catch (Exception e) {
			// 保存错误LOG
			log.error("删除订单 更新订单状态为删除状态失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(2003,"error.msg.invalid.parameter")
					.toString();
		}

		log.info("******************删除订单 更新订单状态为删除状态-end************************");
		// 返回处理成功信息
		return new AccessSuccessResult().toString();
	}
	
	/**
	 * @Title: updateOrderStatus
	 * @Description: 更新订单状态
	 * @param params
	 * @return
	 */
	@RequestMapping("/updateOrderStatus")
	@ResponseBody
	public String updateOrderStatus(TblOrder tblOrder){
		log.info("******************更新订单状态-begin************************");
		try {
			orderService.updateOrderStatus(tblOrder);
		} catch (Exception e) {
			// 保存错误LOG
			log.error("删除订单 更新订单状态为删除状态失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(2003,"error.msg.invalid.parameter")
					.toString();
		}

		log.info("******************更新订单状态-end************************");
		// 返回处理成功信息
		return new AccessSuccessResult().toString();
	}

}