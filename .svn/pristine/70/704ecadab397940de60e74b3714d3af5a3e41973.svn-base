package com.wanma.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.bluemobi.product.utils.JacksonJsonMapper;
import com.bluemobi.product.utils.RequestParamUtil;
import com.bluemobi.product.utils.StringUtil;
import com.wanma.common.ApplicationConsts;
import com.wanma.common.JudgeNullUtils;
import com.wanma.model.AffirmOrder;
import com.wanma.model.MyOrder;
import com.wanma.model.TblOrder;
import com.wanma.model.TblOrderdetail;
import com.wanma.model.TblOrdertracking;
import com.wanma.web.service.WebOrderService;
import com.wanma.web.support.common.FailedResponse;
import com.wanma.web.support.common.PageResponse;
import com.wanma.web.support.common.ResultResponse;
import com.wanma.web.support.common.SuccessResponse;

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
@RequestMapping("/web/myOrder")
public class WebOrderController {
	private static Log log = LogFactory.getLog(WebOrderController.class);

	@Autowired
	private WebOrderService orderService;

	/**
	 * 获取我的订单
	 */
	@RequestMapping("/getMyOrder")
	@ResponseBody
	public String getMyOrder(@RequestParam Map<String, Object> params) {
		Integer begin = null, pageSize = null;
        //获取分页参数
        if (!StringUtils.isEmpty((String) params.get("pageNum"))) {
        	pageSize = Integer.valueOf((String) params.get("pageNum"));
        }
        if (!StringUtils.isEmpty((String) params.get("pageNumber"))) {
        	begin = Integer.valueOf((String) params.get("pageNumber"));
        }
        PageResponse<List<MyOrder>> pager = new PageResponse<List<MyOrder>>(begin, pageSize);
		params.put("pageNum", pager.getCount());
		params.put("pageNumber", pager.getBegin());
		// 订单类型 1-搜索全部 2-搜索待支付 3-搜索待安装 4-搜索已完成
		String orderType = (String) params.get("orderType");
		List<MyOrder> myOrderList = new ArrayList<MyOrder>();
		try {
			if (StringUtil.isEmpty((String) params.get("userId"))) {
				return new FailedResponse("error.msg.invalid.parameter").toString();
			}
			// -----获取消费订单-----1-搜索全部 2-搜索待支付 3-搜索待安装 4-搜索已完成
			if (orderType.equals(JudgeNullUtils.nvlStr(ApplicationConsts.ElectricPileManager.PAGE_ORDER_ALL))) {// 搜索全部
				myOrderList = orderService.getMyOrder(params);
	            pager.setCountData(orderService.countMyOrder(params));
			} else if (orderType.equals(JudgeNullUtils.nvlStr(ApplicationConsts.ElectricPileManager.PAGE_ORDER_NO_PAY))) {// 搜索待支付消费订单
				params.put("orderState", JudgeNullUtils.nvlStr(ApplicationConsts.ElectricPileManager.ORDER_SHOP_NOPAY));
				myOrderList = orderService.getShopOrde(params);
	            pager.setCountData(orderService.countShopOrde(params));
			} else if (orderType.equals(JudgeNullUtils.nvlStr(ApplicationConsts.ElectricPileManager.PAGE_ORDER_NO_INSTALL))) {// 搜搜待安装订单
				params.put("orderState", JudgeNullUtils.nvlStr(ApplicationConsts.ElectricPileManager.ORDER_NO_INSTALL));
				myOrderList = orderService.getInstallOrde(params);
	            pager.setCountData(orderService.countInstallOrde(params));
			} else if (orderType.equals(JudgeNullUtils.nvlStr(ApplicationConsts.ElectricPileManager.PAGE_ORDER_FINSH))) {// 已完成
				params.put("orderState", JudgeNullUtils.nvlStr(ApplicationConsts.ElectricPileManager.ORDER_SHOP_PAY));
				myOrderList = orderService.getMyOrder(params);
	            pager.setCountData(orderService.countMyOrder(params));
			}
            pager.setDate(myOrderList);
		} catch (Exception e) {
			// 保存错误LOG
			System.out.print(e);
			log.error("获取我的订单数据失败", e);
			// 返回登录信息错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}
		// 返回处理成功信息
		return pager.toString();
	}

	/**
	 * 用户确认订单
	 */
	@RequestMapping("/affirmUserOrder")
	@ResponseBody
	public String AffirmUserOrder(HttpServletRequest request) {

		// 确认订单列表
		String affirmOrderList = RequestParamUtil.getEncodeParam(request, "affirmOrderList");
		AffirmOrder affirmOrder = new AffirmOrder();
		try {
			if (StringUtil.isEmpty(affirmOrderList)) {
				// 未输入 电桩ID
				return new FailedResponse("error.msg.invalid.parameter").toString();
			}
			affirmOrder = JSONObject.parseObject(affirmOrderList, AffirmOrder.class);
			affirmOrder = orderService.AffirmUserOrder(affirmOrder);
		} catch (Exception e) {
			// 保存错误LOG
			System.out.print(e);
			log.error("用户确认订单失败", e);
			// 返回登录信息错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}
		// 返回处理成功信息
		return new ResultResponse<AffirmOrder>(affirmOrder).toString();
	}

	/**
	 * @Title: insertOrderInfo
	 * @Description: 确认订单，插入订单信息
	 * @param params
	 * @return
	 */
	@RequestMapping("/insertOrderInfo")
	@ResponseBody
	public String insertOrderInfo(TblOrder order, @RequestParam("shcaProductids") String shcaProductids,
			@RequestParam("prodProductNames") String prodProductNames, @RequestParam("shcaCounts") String shcaCounts,
			@RequestParam("prodPrices") String prodPrices) {
		log.info("******************确认订单，插入订单信息-begin************************");

		int pkOrder = orderService.insertOrderInfo(order, shcaProductids, prodProductNames, shcaCounts, prodPrices);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("pkOrder", pkOrder);
		log.info("******************确认订单，插入订单信息-end************************");
		// 返回处理成功信息
		return new ResultResponse<Map<String, Object>>(resultMap).toString();
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
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			resultMap = orderService.selectOrderDetails(pkOrder);
		} catch (Exception e) {
			// 保存错误LOG
			System.out.print(e);
			log.error("支付成功  获取订单详情失败", e);
			// 返回登录信息错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}

		log.info("******************支付成功  获取订单详情-end************************");
		// 返回处理成功信息
		return new ResultResponse<Map<String, Object>>(resultMap).toString();
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
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}
		log.info("******************删除订单 更新订单状态为删除状态-end************************");
		// 返回处理成功信息
		return new SuccessResponse().toString();
	}
	
	/**
	 * @Title: insertShopOrderInfo
	 * @Description: 购物车页面,结算新增订单
	 * @param params
	 * @return
	 */
	@RequestMapping("/insertShopOrderInfo")
	@ResponseBody
	public String insertShopOrderInfo(HttpServletRequest request) {
		log.info("******************购物车页面,结算新增订单-begin************************");

		ResultResponse<String> response;
		try {
			// 商品id,多个以逗号分隔
			String ids = RequestParamUtil.getEncodeParam(request, "ids");
			// 商品对应数量，多个以逗号分隔
			String counts = RequestParamUtil.getEncodeParam(request, "counts");
			// 总价格
			String totalMoney = RequestParamUtil.getEncodeParam(request,
					"totalMoney");
			// 用户id
			String userId = RequestParamUtil.getEncodeParam(request, "userId");
			//订单数量
			String ordeCommodityTotal = RequestParamUtil.getEncodeParam(request, "ordeCommodityTotal");

			response = orderService.insertShopOrder(ids,counts,totalMoney, userId,Integer.parseInt(ordeCommodityTotal));
		} catch (Exception e) {
			System.out.println(e);
			// 保存错误LOG
			log.error("用户确认订单失败", e);
			// 返回错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}

		log.info("******************购物车页面,结算新增订单-end************************");
		// 返回处理成功信息
		return response.toString();
	}
	
	/**
	 * @Title: selectProductsByOrderId
	 * @Description: 获取商品详情
	 * @param params
	 * @return
	 */
	@RequestMapping("/selectProductsByOrderId")
	@ResponseBody
	public String selectProductsByOrderId(HttpServletRequest request) {
		log.info("******************获取订单详情-begin************************");
		PageResponse<List<TblOrderdetail>> result = null;
		List<TblOrderdetail> orderList = null;
		try {

			// 订单id
			String orDeParentId = RequestParamUtil.getEncodeParam(request,
					"orderId");
			TblOrder order = orderService.get(Integer.parseInt(orDeParentId));
			orderList = orderService.selectProductsByOrderId(Integer
					.parseInt(orDeParentId));
			result = new PageResponse<List<TblOrderdetail>>(0, 0);
			result.setDate(orderList);
			result.setMessage(order.getOrdeTotalamount() + "");
		} catch (NumberFormatException e) {
			// 保存错误LOG
			log.error("获取订单详情失败", e);
			// 返回登录信息错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}
		log.info("******************获取订单详情-end************************");
		return result.toString();
	}
	
	/**
	 * @Title: updateOrder
	 * @Description: 结算，更新订单状态和收货地址
	 * @param params
	 * @return
	 */
	@RequestMapping("/updateOrder")
	@ResponseBody
	public String updateOrder(HttpServletRequest request) {
		log.info("******************结算，更新订单状态和收货地址-begin************************");
		// 订单id
		String pkOrder = RequestParamUtil.getEncodeParam(request, "pkOrder");
		try {

			// 用户id
			String userId = RequestParamUtil.getEncodeParam(request, "userId");

			orderService.updateOrder(pkOrder, userId);
		} catch (Exception e) {
			// 保存错误LOG
			log.error("结算，更新订单状态和收货地址-begin失败", e);
			// 返回登录信息错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}
		log.info("******************结算，更新订单状态和收货地址-end************************");
		return new SuccessResponse(pkOrder).toString();
	}
	
	/**
	 * @Title: findOrderTracks
	 * @Description: 获取订单跟踪信息
	 * @param params
	 * @return
	 */
	@RequestMapping("/findOrderTracks")
	@ResponseBody
	public String findOrderTracks(int orderId) {
		log.info("******************获取订单跟踪信息-begin************************");
		PageResponse<List<TblOrdertracking>> result = null;
		List<TblOrdertracking> ordertrackings = null;
		try {
			ordertrackings = orderService.findOrderTracks(orderId);
			result = new PageResponse<List<TblOrdertracking>>(0, 0);
			result.setDate(ordertrackings);

		} catch (Exception e) {
			// 保存错误LOG
			log.error("获取订单跟踪信息失败", e);
			// 返回登录信息错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}
		log.info("******************获取订单跟踪信息-end************************");
		return result.toString();
	}
	
	/**
	 * @Title: getOrder
	 * @Description: 获取订单信息
	 * @param params
	 * @return
	 */
	@RequestMapping("/getOrder")
	@ResponseBody
	public String getOrder(int orderId) {
		log.info("******************获取订单信息-begin************************");
		TblOrder order = null;
		try {
			order = orderService.get(orderId);
		} catch (Exception e) {
			// 保存错误LOG
			log.error("获取订单跟踪信息失败", e);
			// 返回登录信息错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}
		log.info("******************获取订单信息-end************************");
		return new ResultResponse<TblOrder>(order).toString();
	}
	
	/**
	 * @Title: updateOrderstatus
	 * @Description: 更新订单状态
	 * @param params
	 * @return
	 */
	@RequestMapping("/updateOrderstatus")
	@ResponseBody
	public String updateOrderstatus(TblOrder tblOrder){
		log.info("******************更新订单状态-begin************************");
		try {
			orderService.updateOrderstatus(tblOrder);
		} catch (Exception e) {
			// 保存错误LOG
			log.error("更新订单状态失败", e);
			// 返回登录信息错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}
		log.info("******************更新订单状态-end************************");
		return new SuccessResponse().toString();
	}

}