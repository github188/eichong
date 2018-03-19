package com.wanma.web.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluemobi.product.utils.StringUtil;
import com.wanma.model.TblShoppingcart;
import com.wanma.web.dao.WebShoppingcartMapper;
import com.wanma.web.service.WebShoppingcartService;

/**
 * @Description: 购物车业务处理类
 * @author songjf
 * @createTime：2015-3-16 下午05:49:01
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Service
public class WebShoppingcartServiceImpl implements WebShoppingcartService {
	/* 购物车表操作dao */
	@Autowired
	private WebShoppingcartMapper shoppingcartMapper;

	/**
	 * @Title: insertIntoCart
	 * @Description: 加入购物车
	 * @param shoppingcart
	 * @return
	 */
	@Override
	public int insertIntoCart(TblShoppingcart shoppingcart) {
		shoppingcart.setShcaCreatedate(new Date());
		shoppingcart.setShcaUpdatedate(new Date());
		return shoppingcartMapper.insert(shoppingcart);
	}

	/**
	 * @Title: selectByshCaProductid
	 * @Description: 根据产品id获取购物车信息
	 * @param params
	 * @return
	 */
	@Override
	public TblShoppingcart selectByshCaProductid(Map<String,Object> params) {
		return shoppingcartMapper.selectByshCaProductid(params);
	}

	/**
	 * @Title: updateCart
	 * @Description: 更新购物车
	 * @param params
	 * @return
	 */
	@Override
	public int updateCart(TblShoppingcart shoppingcart) {
		shoppingcart.setShcaUpdatedate(new Date());
		return shoppingcartMapper.update(shoppingcart);
	}

	/**
	 * @Title: deleteCart
	 * @Description: 删除购物车
	 * @param params
	 * @return
	 */
	@Override
	public void deleteCart(Map<String,Object> params) {
		String shoppingcarts = (String) params.get("pkShoppingcart");
		if(StringUtil.isNotEmpty(shoppingcarts)){
			String[] carts = shoppingcarts.split(",");
			for(int i =0;i<carts.length;i++){
				shoppingcartMapper.delete(Integer.parseInt(carts[i]));
			}
		}
	}

	/**
	 * @Title: findMyCart
	 * @Description: 获取我的购物车
	 * @param params
	 * @return
	 */
	@Override
	public List<TblShoppingcart> findMyCart(Integer pkUserInfo) {
		List<TblShoppingcart> shoppingcartList = shoppingcartMapper
				.findMyCart(pkUserInfo);
//		if (ObjectUtil.isNotEmpty(shoppingcartList)) {
//			for (TblShoppingcart shoppingcart : shoppingcartList) {
//				// 折扣价格
//				BigDecimal prodDiscountprice = shoppingcart
//						.getProdDiscountprice();
//				// 获取商品数量
//				int shcaCount = shoppingcart.getShcaCount();
//				// 判断此商品是否打折
//				BigDecimal bd = new BigDecimal(0);
//				int result = prodDiscountprice.compareTo(bd);
//				if (result == 0) {
//					// 市场价格
//					BigDecimal prodMarketprice = shoppingcart
//							.getProdMarketprice();
//
//					shoppingcart.setTotalPrice(prodMarketprice
//							.multiply(new BigDecimal(shcaCount)));
//				} else {
//
//					shoppingcart.setTotalPrice(prodDiscountprice
//							.multiply(new BigDecimal(shcaCount)));
//				}
//			}
//
//		}

		return shoppingcartList;
	}

	/**
	 * @Title: selectByshCaProductidNew
	 * @Description: 根据产品id获取购物车信息
	 * @param params
	 * @return
	 */
	@Override
	public TblShoppingcart selectByshCaProductidNew(Map<String, Object> params) {
		return shoppingcartMapper.selectByshCaProductidNew(params);
	}

	/**
	 * @Title: deleteCart
	 * @Description: 删除购物车
	 * @param params
	 * @return
	 */
	@Override
	public void deleteCartNew(Map<String, Object> params) {
		shoppingcartMapper.deleteCart(params);
	}
	
	/**
	 * @Title: findMyCart
	 * @Description: 获取我的购物车
	 * @param params
	 * @return
	 */
	@Override
	public List<TblShoppingcart> findMyCartNew(Integer pkUserInfo) {
		List<TblShoppingcart> shoppingcartList = shoppingcartMapper
				.findMyCart(pkUserInfo);
		if (null != shoppingcartList && shoppingcartList.size() > 0) {
			for (TblShoppingcart shoppingcart : shoppingcartList) {
				// 产品信息
				List<Map<String, Object>> productList = shoppingcart
						.getProductList();
				for (Map<String, Object> map : productList) {
					// 产品数量
//					int shcaCount = (Integer) map.get("shcaCount");
					BigDecimal unitPrice = null;
					if (map.containsKey("prodDiscountprice")) {
						// 折扣价格
						unitPrice = (BigDecimal) map.get("prodDiscountprice");
					} else {
						// 市场价
						unitPrice = (BigDecimal) map.get("prodMarketprice");
					}
					map.put("unitPrice", unitPrice);
				}
			}

		}

		return shoppingcartList;
	}

}
