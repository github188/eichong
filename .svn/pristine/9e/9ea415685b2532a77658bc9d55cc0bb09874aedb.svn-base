/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.web.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluemobi.product.common.BluemobiCommon;
import com.bluemobi.product.utils.DateUtil;
import com.bluemobi.product.utils.ObjectUtil;
import com.bluemobi.product.utils.StringUtil;
import com.wanma.common.JudgeNullUtils;
import com.wanma.common.WanmaConstants;
import com.wanma.model.AffirmOrder;
import com.wanma.model.MyOrder;
import com.wanma.model.OrderInfo;
import com.wanma.model.Orders;
import com.wanma.model.TblOrder;
import com.wanma.model.TblOrderdetail;
import com.wanma.model.TblOrdertracking;
import com.wanma.model.TblProduct;
import com.wanma.model.TblShoppingcart;
import com.wanma.model.TblUseraddress;
import com.wanma.web.dao.WebMyOrderMapper;
import com.wanma.web.dao.WebOrderMapper;
import com.wanma.web.dao.WebOrderdetailMapper;
import com.wanma.web.dao.WebOrdertrackingMapper;
import com.wanma.web.dao.WebProductMapper;
import com.wanma.web.dao.WebShoppingcartMapper;
import com.wanma.web.dao.WebUseraddressMapper;
import com.wanma.web.service.WebOrderService;
import com.wanma.web.support.common.FailedResponse;
import com.wanma.web.support.common.ResultResponse;
import com.wanma.web.support.common.SuccessResponse;

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
public class WebOrderServiceImpl implements WebOrderService {

	@Autowired
	WebMyOrderMapper myOrderMapper;
	/* 用户地址表操作dao */
	@Autowired
	WebUseraddressMapper useraddressMapper;
	/* 商品表操作dao */
	@Autowired
	WebProductMapper productMapper;
	/* 订单详情表操作dao */
	@Autowired
	WebOrderdetailMapper orderdetailMapper;
	/* 订单表操作dao */
	@Autowired
	WebOrderMapper orderMapper;
	@Autowired
	WebUseraddressMapper appUseraddressMapper;
	/* 订单跟踪表操作dao */
	@Autowired
	WebOrdertrackingMapper ordertrackingMapper;
	/* 购物车表操作dao */
	@Autowired
	WebShoppingcartMapper shoppingcartMapper;

	/**
	 * 搜索全部/已完成
	 */
	@Override
	public List<MyOrder> getMyOrder(Map<String, Object> params) {
		// TODO Auto-generated method stub
		// 00获取全部/已完成订单
		List<MyOrder> consumeOrderList = new ArrayList<MyOrder>();
		List<Map<String, Object>> myOrdeList = myOrderMapper.getMyOrder(params);// 待改
		for (int i = 0; i < myOrdeList.size(); i++) {
			MyOrder myOrder = new MyOrder();
			Map<String, Object> myOrdeLMap = (Map<String, Object>) myOrdeList.get(i);
			myOrder.setOrderId(JudgeNullUtils.nvlStr(myOrdeLMap.get("orderCode")));
			myOrder.setOrderShopName(JudgeNullUtils.nvlStr(myOrdeLMap.get("orderShopName")));
			myOrder.setOrderState(JudgeNullUtils.nvlStr(myOrdeLMap.get("orderState")));// 1：待支付 2 支付成功
			myOrder.setCommodityTotal(JudgeNullUtils.nvlStr(myOrdeLMap.get("orderTotal")));
			myOrder.setCommodityTotalPrice(JudgeNullUtils.nvlStr(myOrdeLMap.get("orderPrice")));
			myOrder.setCommodityTime(JudgeNullUtils.nvlStr(myOrdeLMap.get("orderTime")).substring(0, 16));
			myOrder.setOrderType(JudgeNullUtils.nvlStr(myOrdeLMap.get("orderType")));
			myOrder.setUserId((String) params.get("userId"));

			List<Orders> ordersList = new ArrayList<Orders>();
			params.put("orderId", JudgeNullUtils.nvlStr(myOrdeLMap.get("orderId")));
			if("1".equals(JudgeNullUtils.nvlStr(myOrdeLMap.get("orderType")))){
				// 01：获取消费订单详情
				List<Map<String, Object>> shopOrdeDetailList = myOrderMapper.getShopOrdeDetail(params);
				for (int j = 0; j < shopOrdeDetailList.size(); j++) {
					Orders orders = new Orders();
					Map<String, Object> shopOrdeDetailMap = (Map<String, Object>) shopOrdeDetailList.get(j);
					orders.setCommodityId(JudgeNullUtils.nvlStr(shopOrdeDetailMap.get("pk_OrderDetail")));
					orders.setCommodityImage(JudgeNullUtils.nvlStr(shopOrdeDetailMap.get("prod_ProductImage")));
					orders.setCommodityName(JudgeNullUtils.nvlStr(shopOrdeDetailMap.get("orDe_ProductName")));
					orders.setCommodityProductPrice(JudgeNullUtils.nvlStr(shopOrdeDetailMap.get("orDe_Price")));
					orders.setCommodityProperty(JudgeNullUtils.nvlStr(shopOrdeDetailMap.get("prod_ProductTag")));// 1：国家补贴
					orders.setCommodityCount(JudgeNullUtils.nvlStr(shopOrdeDetailMap.get("orDe_Quantity")));
					ordersList.add(orders);
				}
			}else{
				// 02：获取安装订单详情
				List<Map<String, Object>> installOrdeDetailList = myOrderMapper.getInstallOrdeDetail(params);
				for (int j = 0; j < installOrdeDetailList.size(); j++) {
					Orders orders = new Orders();
					Map<String, Object> installOrdeDetailMap = (Map<String, Object>) installOrdeDetailList.get(j);
					orders.setCommodityId(JudgeNullUtils.nvlStr(installOrdeDetailMap.get("pk_InstallDetail")));
					orders.setCommodityImage(JudgeNullUtils.nvlStr(installOrdeDetailMap.get("prod_ProductImage")));
					orders.setCommodityName(JudgeNullUtils.nvlStr(installOrdeDetailMap.get("inDe_ProductName")));
					orders.setCommodityProductPrice(JudgeNullUtils.nvlStr(installOrdeDetailMap.get("inDe_Price")));
					orders.setCommodityProperty(JudgeNullUtils.nvlStr(installOrdeDetailMap.get("prod_ProductTag")));// 1：国家补贴 2：免税置购
					orders.setCommodityCount(JudgeNullUtils.nvlStr(installOrdeDetailMap.get("inDe_Quantity")));
					ordersList.add(orders);
				}
			}
			myOrder.setOrders(ordersList);
			consumeOrderList.add(myOrder);
		}
		return consumeOrderList;
	}
	@Override
	public long countMyOrder(Map<String, Object> params) {
		return myOrderMapper.countMyOrder(params);
	}

	/**
	 * 搜索消费订单
	 */
	@Override
	public List<MyOrder> getShopOrde(Map<String, Object> params) {
		// TODO Auto-generated method stub
		List<MyOrder> consumeOrderList = new ArrayList<MyOrder>();
		// 01获取消费订单
		List<Map<String, Object>> shopOrdeList = myOrderMapper.getShopOrde(params);// 待改
		for (int i = 0; i < shopOrdeList.size(); i++) {
			MyOrder myOrder = new MyOrder();
			Map<String, Object> shopOrdeLMap = (Map<String, Object>) shopOrdeList.get(i);
			myOrder.setOrderId(JudgeNullUtils.nvlStr(shopOrdeLMap.get("orde_Code")));
			myOrder.setOrderShopName(JudgeNullUtils.nvlStr(shopOrdeLMap.get("orde_CommodityShops")));
			myOrder.setOrderState(JudgeNullUtils.nvlStr(shopOrdeLMap.get("orde_Status")));// 1：待支付 2 支付成功
			myOrder.setCommodityTotal(JudgeNullUtils.nvlStr(shopOrdeLMap.get("orde_CommodityTotal")));
			myOrder.setCommodityTotalPrice(JudgeNullUtils.nvlStr(shopOrdeLMap.get("orde_TotalAmount")));
			myOrder.setCommodityTime(JudgeNullUtils.nvlStr(shopOrdeLMap.get("orde_Createdate")).substring(0, 16));
			myOrder.setUserId((String) params.get("userId"));
			myOrder.setOrderType("1");
			// 02：获取消费订单详情
			List<Orders> ordersList = new ArrayList<Orders>();
			params.put("orderId", JudgeNullUtils.nvlStr(shopOrdeLMap.get("pk_Order")));
			List<Map<String, Object>> shopOrdeDetailList = myOrderMapper.getShopOrdeDetail(params);
			for (int j = 0; j < shopOrdeDetailList.size(); j++) {
				Orders orders = new Orders();
				Map<String, Object> shopOrdeDetailMap = (Map<String, Object>) shopOrdeDetailList.get(j);
				orders.setCommodityId(JudgeNullUtils.nvlStr(shopOrdeDetailMap.get("pk_OrderDetail")));
				orders.setCommodityImage(JudgeNullUtils.nvlStr(shopOrdeDetailMap.get("prod_ProductImage")));
				orders.setCommodityName(JudgeNullUtils.nvlStr(shopOrdeDetailMap.get("orDe_ProductName")));
				orders.setCommodityProductPrice(JudgeNullUtils.nvlStr(shopOrdeDetailMap.get("orDe_Price")));
				orders.setCommodityProperty(JudgeNullUtils.nvlStr(shopOrdeDetailMap.get("prod_ProductTag")));// 1：国家补贴
				orders.setCommodityCount(JudgeNullUtils.nvlStr(shopOrdeDetailMap.get("orDe_Quantity")));
				ordersList.add(orders);
			}
			myOrder.setOrders(ordersList);
			consumeOrderList.add(myOrder);
		}
		return consumeOrderList;
	}
	@Override
	public long countShopOrde(Map<String, Object> params) {
		return myOrderMapper.countShopOrde(params);
	}

	@Override
	public List<MyOrder> getInstallOrde(Map<String, Object> params) {
		// TODO Auto-generated method stub
		List<MyOrder> consumeOrderList = new ArrayList<MyOrder>();
		// 01获取安装订单
		List<Map<String, Object>> installOrdeList = myOrderMapper.getInstallOrde(params);// 待改
		for (int i = 0; i < installOrdeList.size(); i++) {
			MyOrder myOrder = new MyOrder();
			Map<String, Object> installOrdeLMap = (Map<String, Object>) installOrdeList.get(i);
			myOrder.setOrderId(JudgeNullUtils.nvlStr(installOrdeLMap.get("aLOr_InstallationOrderCode")));
			myOrder.setOrderShopName(JudgeNullUtils.nvlStr(installOrdeLMap.get("aLOr_CommodityShops")));
			myOrder.setOrderState(JudgeNullUtils.nvlStr(installOrdeLMap.get("aLOr_InstallationOrderStatus")));// 1：待支付 2 支付成功
			myOrder.setCommodityTotal(JudgeNullUtils.nvlStr(installOrdeLMap.get("aLOr_CommodityTotal")));
			myOrder.setCommodityTotalPrice(JudgeNullUtils.nvlStr(installOrdeLMap.get("aLOr_Money")));
			myOrder.setCommodityTime(JudgeNullUtils.nvlStr(installOrdeLMap.get("aLOr_BuyingTime")));
			myOrder.setUserId((String) params.get("userId"));
			myOrder.setOrderType("2");
			// 02：获取安装订单详情
			List<Orders> ordersList = new ArrayList<Orders>();
			params.put("orderId", JudgeNullUtils.nvlStr(installOrdeLMap.get("pk_AppointmentInstallationOrder")));
			List<Map<String, Object>> installOrdeDetailList = myOrderMapper.getInstallOrdeDetail(params);
			for (int j = 0; j < installOrdeDetailList.size(); j++) {
				Orders orders = new Orders();
				Map<String, Object> installOrdeDetailMap = (Map<String, Object>) installOrdeDetailList.get(j);
				orders.setCommodityId(JudgeNullUtils.nvlStr(installOrdeDetailMap.get("pk_InstallDetail")));
				orders.setCommodityImage(JudgeNullUtils.nvlStr(installOrdeDetailMap.get("prod_ProductImage")));
				orders.setCommodityName(JudgeNullUtils.nvlStr(installOrdeDetailMap.get("inDe_ProductName")));
				orders.setCommodityProductPrice(JudgeNullUtils.nvlStr(installOrdeDetailMap.get("inDe_Price")));
				orders.setCommodityProperty(JudgeNullUtils.nvlStr(installOrdeDetailMap.get("prod_ProductTag")));// 1：国家补贴 2：免税置购
				orders.setCommodityCount(JudgeNullUtils.nvlStr(installOrdeDetailMap.get("inDe_Quantity")));
				ordersList.add(orders);
			}
			myOrder.setOrders(ordersList);
			consumeOrderList.add(myOrder);
		}
		return consumeOrderList;

	}
	@Override
	public long countInstallOrde(Map<String, Object> params) {
		return myOrderMapper.countInstallOrde(params);
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
		Map<String, Object> addressMap = useraddressMapper.findDefault(Integer.parseInt((String) params.get("pkUserInfo")));
		// 商品id
		String pkProduct = (String) params.get("pkProduct");
		// 保存查询出来的所有商品信息
		List<Map<String, Object>> productList = null;
		if (StringUtil.isNotEmpty(pkProduct)) {
			productList = new ArrayList<Map<String, Object>>();
			String[] pkProducts = pkProduct.split(",");
			for (int i = 0; i < pkProducts.length; i++) {
				// 商品信息
				Map<String, Object> productMap = productMapper.selectById(params);
				productList.add(productMap);
			}
		}

		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		Map<String, Object> productMap = new HashMap<String, Object>();
		if (ObjectUtil.isNotEmpty(productList)) {
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
		order.setOrdeTotalamount(new Double(1));
		order.setOrdeUserid(1);
		order.setOrdeRemark("");
		order.setOrdeReceiveingname("");
		order.setOrdeReceiveingaddress("");
		order.setOrdeReceiveingcontact("");
		order.setOrdeDeliveryway(1);
		order.setOrdeOrdertype(1);
		order.setOrdeExpress("");
		order.setOrdeCommodityTotal(1);
		order.setOrdeCommodityShops("");
		order.setOrdeTypeOrder("1");
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
				orderdetail.setOrdeProductid(Integer.parseInt(productidsArray[i]));
				orderdetail.setOrdeProductname(productNameArray[i]);
				// 商品价格
				BigDecimal prodPrice = new BigDecimal(priceArray[i].trim());
				orderdetail.setOrdePrice(prodPrice);
				// 商品数量
				int shcaCount = Integer.parseInt(countArray[i]);
				orderdetail.setOrdeQuantity(shcaCount);
				orderdetail.setOrdeTotalamount(prodPrice.multiply(new BigDecimal(shcaCount)));
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
		tblUseraddress.setPradUserid(JudgeNullUtils.nvlInteget(affirmOrder.getUserId()));
		tblUseraddress.setPradIsDefault("1");// 获取用户默认地址
		// 01:获取订单收货地址信息
		List<TblUseraddress> useraddressList = appUseraddressMapper.find(tblUseraddress);
		for (TblUseraddress tblUseraddress2 : useraddressList) {
			affirmOrder.setReceiveName(JudgeNullUtils.nvlStr(tblUseraddress2.getPradUsername()));
			affirmOrder.setReceiveTel(JudgeNullUtils.nvlStr(tblUseraddress2.getPradPhonenumber()));
			affirmOrder.setReceiveAddress(JudgeNullUtils.nvlStr(tblUseraddress2.getPkUseraddress()));
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
		orderInfo.put("completeDate", DateUtil.toDateFormat((Date) orderInfo.get("completeDate"), "yyyy-MM-dd HH:mm:ss"));

		// 获取订单跟踪信息
		List<TblOrdertracking> ordertrackingList = ordertrackingMapper.findOrderTracks(pkOrder);
		// 返回结果
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("orderInfoList", orderInfoList);
		resultMap.put("ordertrackingList", ordertrackingList);
		resultMap.put("orderInfo", orderInfo);
		return resultMap;
	}
	
	/**
	 * @Title: insertOrder
	 * @Description: 购物车页面，结算新增订单
	 * @param params
	 * @return
	 */
	@Override
	public ResultResponse<String> insertShopOrder(String ids,String counts,
			String totalMoney, String userId,int ordeCommodityTotal) {
		// 商品id
		String[] idArray = ids.split(",");
		//商品对应数量
		String[] countArray = counts.split(",");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("shcaUserid", userId);
		// 判断库存
		for (int i = 0; i < idArray.length; i++) {
			params.put("shcaProductid", idArray[i]);
			// 获取商品信息
			TblShoppingcart shoppingcart = shoppingcartMapper
					.selectByshCaProductid(params);
			// 库存数量
			int prodStockquantity = shoppingcart.getProdStockquantity();
			// 购买数量
//			int count = shoppingcart.getShcaCount();
			int count = Integer.parseInt(countArray[i]);
			if (prodStockquantity - count < 0) {
				FailedResponse response = new FailedResponse(
						shoppingcart.getProdProductName() + "库存不足");
				return response;
			}
		}

		// 获取用户默认收货地址
		TblUseraddress address = useraddressMapper.findDefault(Integer
				.parseInt(userId));

		TblOrder order = new TblOrder();
		// 订单状态：1待支付 2 支付成功 3 操作完成
		order.setOrdeStatus(WanmaConstants.ORDER_STATUS_WAIT);
		// 订单号
		order.setOrdeCode(BluemobiCommon.getOrderNo());
		order.setOrdeTotalamount(Double.valueOf(totalMoney));
		order.setOrdeUserid(Integer.parseInt(userId));
		order.setOrdeOrdertype(1);
		if (address != null) {
			order.setOrdeReceiveingname(address.getPradUsername());
			order.setOrdeReceiveingaddress(address.getPradAddress()
					+ address.getPradStreet());
			order.setOrdeReceiveingcontact(address.getPradPhonenumber());
		} else {
			order.setOrdeReceiveingname("");
			order.setOrdeReceiveingaddress("");
			order.setOrdeReceiveingcontact("");
		}

		order.setOrdeCreatedate(new Date());
		order.setOrdeUpdatedate(new Date());
		order.setOrdeRemark("");
		order.setOrdeCommodityShops("");
		order.setOrdeExpress("");
		order.setOrdeDeliveryway(0);
		order.setOrdeTypeOrder("0");
		order.setOrdeCommodityTotal(ordeCommodityTotal);
		orderMapper.insert(order);
		
		// 新增订单详情
		TblOrderdetail orderdetail = new TblOrderdetail();
		// 计算价格
		BigDecimal totalCountMoney = new BigDecimal(0);
		for (int i = 0; i < idArray.length; i++) {
			params.put("shcaProductid", idArray[i]);
			// 获取商品信息
			TblShoppingcart shoppingcart = shoppingcartMapper
					.selectByshCaProductid(params);
			orderdetail.setOrdeParentid(order.getPkOrder());
			orderdetail.setOrdeProductid(shoppingcart.getShcaProductid());
			orderdetail.setOrdeProductname(shoppingcart.getProdProductName());
			// 获取商品价格
			BigDecimal unitPrice = shoppingcart.getProdDiscountprice();
			if (unitPrice == null) {
				unitPrice = shoppingcart.getProdMarketprice();
			}
			orderdetail.setOrdePrice(unitPrice);
			orderdetail.setOrdeQuantity(Integer.parseInt(countArray[i]));
			BigDecimal totalAmount = unitPrice.multiply(new BigDecimal(countArray[i]));
			orderdetail.setOrdeTotalamount(totalAmount);
			orderdetail.setOrdeCreatedate(new Date());
			orderdetail.setOrdeUpdatedate(new Date());
			orderdetailMapper.insert(orderdetail);
			// 删除购物车中此商品
			shoppingcartMapper.deleteCart(params);
			// 扣除库存数量
			TblProduct product = new TblProduct();
			product.setPkProduct(Integer.parseInt(idArray[i]));
			product.setProdStockquantity(Integer.parseInt(countArray[i]));
			product.setProdSoldquantity(Integer.parseInt(countArray[i]));
			productMapper.updateQuantity(product);

			totalCountMoney = totalCountMoney.add(totalAmount);
		}
		// 判断页面获取的价格和后台计算价格是否相等
		if (!totalCountMoney.equals(new BigDecimal(totalMoney))) {
			order.setOrdeTotalamount(totalCountMoney.doubleValue());
			orderMapper.update(order);
		}

		// 新增订单跟踪
		TblOrdertracking ordertracking = new TblOrdertracking();
		ordertracking.setOrtrId(order.getPkOrder());
		// 跟踪类型 1购买 2付款 3预约安装 4收货 5安装中 6完成
		ordertracking.setOrtrStatus(WanmaConstants.ORDER_TRACK_BUY);
		ordertracking.setOrtrCreatedate(new Date());
		ordertracking.setOrtrUpdatedate(new Date());
		ordertrackingMapper.insert(ordertracking);

		return new SuccessResponse(order.getPkOrder() + "");
	}
	
	/**
	 * @Title: get
	 * @Description: 获取订单信息
	 * @param params
	 * @return
	 */
	@Override
	public TblOrder get(Integer pkOrder) {
		return orderMapper.get(pkOrder);
	}
	
	/**
	 * @Title: selectProductsByOrderId
	 * @Description: 获取订单中商品信息
	 * @param params
	 * @return
	 */
	@Override
	public List<TblOrderdetail> selectProductsByOrderId(Integer orDeParentId) {
		List<TblOrderdetail> orderProductList = orderdetailMapper
				.selectProductsByOrderId(orDeParentId);
		if (null != orderProductList && orderProductList.size() > 0) {
			for (TblOrderdetail detail : orderProductList) {
				List<Map<String, Object>> productList = detail.getProductList();
				// 记录品牌下商品总价格
				BigDecimal totalMoney = new BigDecimal(0);
				// 记录品牌下商品总数
				int totalCount = 0;
				for (Map<String, Object> map : productList) {
					// 总价
					BigDecimal ordeTotalamount = (BigDecimal) map
							.get("ordeTotalamount");
					// 数量
					int ordeQuantity = (Integer) map.get("ordeQuantity");
//					totalMoney = totalMoney.add(ordeTotalamount
//							.multiply(new BigDecimal(ordeQuantity)));
					totalMoney = totalMoney.add(ordeTotalamount);
					totalCount += ordeQuantity;
				}
				detail.setOrdeTotalamount(totalMoney);
				detail.setOrdeQuantity(totalCount);
			}
		}

		return orderProductList;
	}
	
	/**
	 * @Title: updateOrder
	 * @Description: 结算，更新订单状态和收货地址
	 * @param params
	 * @return
	 */
	@Override
	public void updateOrder(String pkOrder, String userId) {
		// 获取用户默认收货地址
		TblUseraddress address = useraddressMapper.findDefault(Integer
				.parseInt(userId));
		TblOrder order = new TblOrder();
		order.setPkOrder(Integer.parseInt(pkOrder));
		order.setOrdeReceiveingname(address.getPradUsername());
		order.setOrdeReceiveingaddress(address.getPradAddress()
				+ address.getPradStreet());
		order.setOrdeReceiveingcontact(address.getPradPhonenumber());
		// 订单状态：1待支付 2 支付成功 3 操作完成
		order.setOrdeStatus(WanmaConstants.ORDER_STATUS_SUCCESS);
		// 更新收货地址以及订单状态
		orderMapper.update(order);

		// 新增订单跟踪状态
		TblOrdertracking ordertracking = new TblOrdertracking();
		ordertracking.setOrtrId(order.getPkOrder());
		// 跟踪类型 1购买 2付款 3预约安装 4收货 5安装中 6完成
		ordertracking.setOrtrStatus(WanmaConstants.ORDER_TRACK_PAY);
		ordertracking.setOrtrCreatedate(new Date());
		ordertracking.setOrtrUpdatedate(new Date());
		ordertrackingMapper.insert(ordertracking);
	}
	
	/**
	 * @Title: selectOrdertracking
	 * @Description: 获取订单跟踪信息
	 * @param pkOrder
	 *            订单主键
	 * @return
	 */
	@Override
	public List<TblOrdertracking> findOrderTracks(Integer pkOrder) {
		return ordertrackingMapper.findOrderTracks(pkOrder);
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
	 * @Title: updateOrderstatus
	 * @Description: 更新订单状态
	 * @param tblOrder
	 */
	@Override
	public void updateOrderstatus(TblOrder tblOrder) {
		tblOrder.setOrdeUpdatedate(new Date());
		orderMapper.update(tblOrder);
	}

}
