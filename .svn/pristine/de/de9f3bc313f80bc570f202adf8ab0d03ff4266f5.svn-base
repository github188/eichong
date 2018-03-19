/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.app.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluemobi.product.common.BluemobiCommon;
import com.bluemobi.product.utils.ObjectUtil;
import com.bluemobi.product.utils.StringUtil;
import com.wanma.app.dao.AppBespokeMapper;
import com.wanma.app.dao.AppOrderMapper;
import com.wanma.app.dao.AppOrderdetailMapper;
import com.wanma.app.dao.AppOrdertrackingMapper;
import com.wanma.app.dao.AppProductMapper;
import com.wanma.app.dao.AppShoppingcartMapper;
import com.wanma.app.dao.AppUseraddressMapper;
import com.wanma.app.dao.AppUserinfoMapper;
import com.wanma.app.dao.MyOrderMapper;
import com.wanma.app.service.OrderService;
import com.wanma.app.util.DateUtil;
import com.wanma.common.ArithUtil;
import com.wanma.common.JudgeNullUtils;
import com.wanma.common.WanmaConstants;
import com.wanma.model.AffirmOrder;
import com.wanma.model.MyOrder;
import com.wanma.model.OrderInfo;
import com.wanma.model.OrderInfoList;
import com.wanma.model.Orders;
import com.wanma.model.TblAppointmentinstallationorder;
import com.wanma.model.TblOrder;
import com.wanma.model.TblOrderdetail;
import com.wanma.model.TblOrdertracking;
import com.wanma.model.TblUseraddress;
import com.wanma.model.TblUserinfo;

/***
 * 
 * 获取订单
 * 
 * @Description:
 * @author bruce cheng(http://blog.csdn.net/brucehome)
 * @createTime：2015-3-13 下午04:51:34
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	MyOrderMapper myOrderMapper;
	/* 用户地址表操作dao */
	@Autowired
	AppUseraddressMapper useraddressMapper;
	/* 商品表操作dao */
	@Autowired
	AppProductMapper productMapper;
	/* 订单详情表操作dao */
	@Autowired
	AppOrderdetailMapper orderdetailMapper;
	/* 订单表操作dao */
	@Autowired
	AppOrderMapper orderMapper;
	@Autowired
	AppUseraddressMapper appUseraddressMapper;
	/* 订单跟踪表操作dao */
	@Autowired
	AppOrdertrackingMapper ordertrackingMapper;
	/* 购物车表操作dao */
	@Autowired
	AppShoppingcartMapper shoppingcartMapper;
	/*预约表操作dao*/
	@Autowired
	AppBespokeMapper bespokeMapper;
	/*用户表操作dao*/
	@Autowired
	AppUserinfoMapper userinfoMapper;

	/**
	 * 搜索全部/已完成
	 */
	@Override
	public List<MyOrder> getMyOrder(MyOrder myOrder) {
		// TODO Auto-generated method stub
		List<MyOrder> consumeOrderList = new ArrayList<MyOrder>();
		// 01:消费订单
		List<MyOrder> shopList = getShopOrde(myOrder);
		consumeOrderList.addAll(shopList);
		// 02：安装订单
		List<MyOrder> installList = getInstallOrde(myOrder);
		consumeOrderList.addAll(installList);
		return consumeOrderList;
	}

	/**
	 * 搜索消费订单
	 */
	@Override
	public List<MyOrder> getShopOrde(MyOrder myOrders) {

		List<MyOrder> consumeOrderList = new ArrayList<MyOrder>();
		// 01获取消费订单
		List<?> shopOrdeList = myOrderMapper.getShopOrde(myOrders);// 待改
		for (int i = 0; i < shopOrdeList.size(); i++) {
			MyOrder myOrder = new MyOrder();
			Map<String, Object> shopOrdeLMap = (Map<String, Object>) shopOrdeList
					.get(i);
			myOrder.setOrderId(JudgeNullUtils.nvlStr(shopOrdeLMap
					.get("orde_Code")));
			myOrder.setOrderShopName(JudgeNullUtils.nvlStr(shopOrdeLMap
					.get("orde_CommodityShops")));
			myOrder.setOrderState(JudgeNullUtils.nvlStr(shopOrdeLMap
					.get("orde_Status")));// 1：待支付 2 支付成功
			myOrder.setCommodityTotal(JudgeNullUtils.nvlStr(shopOrdeLMap
					.get("orde_CommodityTotal")));
			myOrder.setCommodityTotalPrice(JudgeNullUtils.nvlStr(shopOrdeLMap
					.get("orde_TotalAmount")));
			myOrder.setOrderType("1");
			myOrder.setUserId("");
			// 02：获取订单详情
			List<Orders> orders = new ArrayList<Orders>();
			myOrders.setOrderId(JudgeNullUtils.nvlStr(shopOrdeLMap
					.get("pk_Order")));
			List<?> shopOrdeDetailList = myOrderMapper
					.getShopOrdeDetail(myOrders);
			for (int j = 0; j < shopOrdeDetailList.size(); j++) {
				Orders orders2 = new Orders();
				Map<String, Object> shopOrdeDetailMap = (Map<String, Object>) shopOrdeDetailList
						.get(j);
				orders2.setCommodityId(JudgeNullUtils.nvlStr(shopOrdeDetailMap
						.get("pk_OrderDetail")));
				orders2.setCommodityImage(JudgeNullUtils
						.nvlStr(shopOrdeDetailMap.get("prod_ProductImage")));
				orders2.setCommodityName(JudgeNullUtils
						.nvlStr(shopOrdeDetailMap.get("orDe_ProductName")));
				orders2.setCommodityProductPrice(JudgeNullUtils
						.nvlStr(shopOrdeDetailMap.get("orDe_Price")));
				orders2.setCommodityProperty(JudgeNullUtils
						.nvlStr(shopOrdeDetailMap.get("prod_ProductTag")));// 1：国家补贴
																			// 2：免税置购
				orders2.setCommodityCount(JudgeNullUtils
						.nvlStr(shopOrdeDetailMap.get("orDe_Quantity")));
				orders.add(orders2);
			}
			myOrder.setOrders(orders);
			consumeOrderList.add(myOrder);
		}
		return consumeOrderList;
	}

	@Override
	public List<MyOrder> getInstallOrde(MyOrder myOrders) {
		// TODO Auto-generated method stub

		List<MyOrder> consumeOrderList = new ArrayList<MyOrder>();
		// 01获取消费订单
		List<?> shopOrdeList = myOrderMapper.getInstallOrde(myOrders);// 待改
		for (int i = 0; i < shopOrdeList.size(); i++) {
			MyOrder myOrder = new MyOrder();
			Map<String, Object> shopOrdeLMap = (Map<String, Object>) shopOrdeList
					.get(i);
			myOrder.setOrderId(JudgeNullUtils.nvlStr(shopOrdeLMap
					.get("aLOr_InstallationOrderCode")));
			myOrder.setOrderShopName(JudgeNullUtils.nvlStr(shopOrdeLMap
					.get("aLOr_CommodityShops")));
			myOrder.setOrderState(JudgeNullUtils.nvlStr(shopOrdeLMap
					.get("aLOr_InstallationOrderStatus")));// 1：待支付 2 支付成功
			myOrder.setCommodityTotal(JudgeNullUtils.nvlStr(shopOrdeLMap
					.get("aLOr_CommodityTotal")));
			myOrder.setCommodityTotalPrice(JudgeNullUtils.nvlStr(shopOrdeLMap
					.get("aLOr_Money")));
			myOrder.setOrderType("2");
			myOrder.setUserId("");
			myOrders.setOrderId(JudgeNullUtils.nvlStr(shopOrdeLMap
					.get("pk_AppointmentInstallationOrder")));
			// 02：获取订单详情
			List<Orders> orders = new ArrayList<Orders>();
			List<?> shopOrdeDetailList = myOrderMapper
					.getInstallOrdeDetail(myOrders);
			for (int j = 0; j < shopOrdeDetailList.size(); j++) {
				Orders orders2 = new Orders();
				Map<String, Object> shopOrdeDetailMap = (Map<String, Object>) shopOrdeDetailList
						.get(j);
				orders2.setCommodityId(JudgeNullUtils.nvlStr(shopOrdeLMap
						.get("pk_InstallDetail")));
				orders2.setCommodityImage(JudgeNullUtils.nvlStr(shopOrdeLMap
						.get("prod_ProductImage")));
				orders2.setCommodityName(JudgeNullUtils.nvlStr(shopOrdeLMap
						.get("inDe_ProductName")));
				orders2.setCommodityProductPrice(JudgeNullUtils
						.nvlStr(shopOrdeLMap.get("inDe_Price")));
				orders2.setCommodityProperty(JudgeNullUtils.nvlStr(shopOrdeLMap
						.get("prod_ProductTag")));// 1：国家补贴 2：免税置购
				orders2.setCommodityCount(JudgeNullUtils.nvlStr(shopOrdeLMap
						.get("inDe_Quantity")));
				orders.add(orders2);
			}
			myOrder.setOrders(orders);
			consumeOrderList.add(myOrder);
		}
		return consumeOrderList;

	}

	/**
	 * @Title: confirmOrder
	 * @Description:立即购买 获取用户默认地址 商品信息(等陈应明大神解决)
	 * @param params
	 * @return
	 */
	@Override
	public Map<String, Object> confirmOrder(Map<String, Object> params) {
		// 获取用户默认收货地址
		Map<String, Object> addressMap = useraddressMapper.findDefault(Integer
				.parseInt((String) params.get("pkUserInfo")));
		// 商品id
		String pkProduct = (String) params.get("pkProduct");
		// 保存查询出来的所有商品信息
		List<Map<String, Object>> productList = null;
		if (StringUtil.isNotEmpty(pkProduct)) {
			productList = new ArrayList<Map<String, Object>>();
			String[] pkProducts = pkProduct.split(",");
			for (int i = 0; i < pkProducts.length; i++) {
				// 商品信息
				Map<String, Object> productMap = productMapper
						.selectById(params);
				productList.add(productMap);
			}
		}

		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		Map<String, Object> productMap = null;
		if (ObjectUtil.isNotEmpty(productList)) {
			productMap = new HashMap<String, Object>();
			for (Map<String, Object> product : productList) {
				// 品牌id
				String prodBrand = (String) product.get("prodBrand");

			}
		}
		return null;
	}

	/**
	 * @Title: insertOrderInfo
	 * @Description: 确认订单，插入订单信息
	 * @param params
	 * @return
	 */
	@Override
	public int insertOrderInfo(TblOrder order, String shcaProductids,
			String prodProductNames, String shcaCounts, String prodPrices) {
		// 订单状态：1待支付 2 支付成功 3 操作完成
		order.setOrdeStatus(WanmaConstants.ORDER_STATUS_WAIT);
		// 订单号
		order.setOrdeCode(BluemobiCommon.getOrderNo());
		order.setOrdeOrdertype(1);
		order.setOrdeCreatedate(new Date());
		order.setOrdeUpdatedate(new Date());
		orderMapper.insert(order);
		// 新增订单详情
		if (StringUtil.isNotEmpty(shcaProductids)) {
			// 订单详情
			TblOrderdetail orderdetail = new TblOrderdetail();

			Map<String, Object> cartMap = new HashMap<String, Object>();
			String[] productidsArray = shcaProductids.split(",");
			String[] productNameArray = prodProductNames.split(",");
			String[] countArray = shcaCounts.split(",");
			String[] priceArray = prodPrices.split(",");
			for (int i = 0; i < productidsArray.length; i++) {
				orderdetail.setOrdeParentid(order.getPkOrder());
				orderdetail.setOrdeProductid(Integer
						.parseInt(productidsArray[i]));
				orderdetail.setOrdeProductname(productNameArray[i]);
				// 商品价格
				BigDecimal prodPrice = new BigDecimal(priceArray[i]);
				orderdetail.setOrdePrice(prodPrice);
				// 商品数量
				int shcaCount = Integer.parseInt(countArray[i]);
				orderdetail.setOrdeQuantity(shcaCount);
				orderdetail.setOrdeTotalamount(prodPrice
						.multiply(new BigDecimal(shcaCount)));
				orderdetail.setOrdeCreatedate(new Date());
				orderdetail.setOrdeUpdatedate(new Date());
				// 新增商品详情
				orderdetailMapper.insert(orderdetail);

				cartMap.put("shcaUserid", order.getOrdeUserid());
				cartMap.put("shcaProductid", productidsArray[i]);
				shoppingcartMapper.deleteCart(cartMap);
			}
		}

		// 新增订单跟踪
		TblOrdertracking ordertracking = new TblOrdertracking();
		ordertracking.setOrtrId(order.getPkOrder());
		// 跟踪类型 1购买 2付款 3预约安装 4收货 5安装中 6完成
		ordertracking.setOrtrStatus(WanmaConstants.ORDER_TRACK_BUY);
		ordertracking.setOrtrCreatedate(new Date());
		ordertracking.setOrtrUpdatedate(new Date());
		ordertrackingMapper.insert(ordertracking);
		return order.getPkOrder();
	}

	/**
	 * @Title: deleteOrder
	 * @Description: 删除订单 更新订单状态为删除状态
	 * @param params
	 * @return
	 */
	@Override
	public void deleteOrder(Map<String, Object> params) {
		params.put("ordeStatus", WanmaConstants.ORDER_STATUS_DELETE);
		orderMapper.deleteOrder(params);
	}

	/**
	 * 用户确认订单
	 */
	@Override
	public AffirmOrder AffirmUserOrder(AffirmOrder affirmOrder) {
		// TODO Auto-generated method stub
		TblUseraddress tblUseraddress = new TblUseraddress();
		tblUseraddress.setPradUserid(JudgeNullUtils.nvlInteget(affirmOrder
				.getUserId()));
		tblUseraddress.setPradIsDefault("1");// 获取用户默认地址
		// 01:获取订单收货地址信息
		List<TblUseraddress> useraddressList = appUseraddressMapper
				.find(tblUseraddress);
		for (TblUseraddress tblUseraddress2 : useraddressList) {
			affirmOrder.setReceiveName(JudgeNullUtils.nvlStr(tblUseraddress2
					.getPradUsername()));
			affirmOrder.setReceiveTel(JudgeNullUtils.nvlStr(tblUseraddress2
					.getPradPhonenumber()));
			affirmOrder.setReceiveAddress(JudgeNullUtils.nvlStr(tblUseraddress2
					.getPkUseraddress()));
		}

		return affirmOrder;
	}

	/**
	 * @Title: selectOrderDetails
	 * @Description: 获取订单详情
	 * @param params
	 * @return
	 */
	@Override
	public Map<String, Object> selectOrderDetails(Integer pkOrder) {
		// 根据订单id获取订单信息
		List<OrderInfo> orderInfoList = orderMapper.selectOrderDetail(pkOrder);
		// 获取此订单中商品总数，总价，订单号，成交时间
		Map<String, Object> orderInfo = orderMapper.selectOrderInfo(pkOrder);
		orderInfo.put("completeDate", DateUtil.toDateFormat(
				(Date) orderInfo.get("completeDate"), "yyyy-MM-dd HH:mm:ss"));

		// 获取订单跟踪信息
		List<TblOrdertracking> ordertrackingList = ordertrackingMapper
				.findOrderTracks(pkOrder);
		// 返回结果
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("orderInfoList", orderInfoList);
		resultMap.put("ordertrackingList", ordertrackingList);
		resultMap.put("orderInfo", orderInfo);
		return resultMap;
	}
	
	/**
	 * @Title: selectNoAddProduct
	 * @Description: 获取已购买还未设置安装地址的商品 
	 * @param ordeUserid
	 *        用户id
	 * @return
	 */
	@Override
	public List<TblOrderdetail> selectNoAddProduct(String ordeUserid) {
		return orderMapper.selectNoAddProduct(ordeUserid);
	}

	/**
	 * @Title: updateOrderStatus
	 * @Description: 更新订单状态
	 * @param tblOrder
	 */
	@Override
	public void updateOrderStatus(TblOrder tblOrder) {
		tblOrder.setOrdeUpdatedate(new Date());
		orderMapper.updateOrderStatus(tblOrder);
	}
	
}
