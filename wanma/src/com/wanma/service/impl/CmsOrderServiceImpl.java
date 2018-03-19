package com.wanma.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.CmsOrderMapper;
import com.wanma.dao.CmsOrderdetailMapper;
import com.wanma.model.TblOrder;
import com.wanma.model.TblOrderdetail;
import com.wanma.service.CmsOrderService;

@Service
public class CmsOrderServiceImpl implements CmsOrderService{

	/** 预约安装处理器 */
	@Autowired
	private CmsOrderMapper tblOrderDao;
	/** 订单详情 */
	@Autowired
	private CmsOrderdetailMapper orderdetailMapper;
	
	/**
	 * 根据用户ID取得用户信息
	 * 
	 * @author xiay
	 * @return
	 * @throws 无
	 */
	public TblOrder findOrder(String pkOrder) {

		// 用户信息
		TblOrder order;

		// 取得用户信息
		order = tblOrderDao.findOrder(pkOrder);

		// 返回用户一览
		return order;
	}
	
	/**
	 * 取得用户一览
	 * 
	 * @author xiay
	 * @return
	 * @throws 无
	 */
	public List<TblOrder> getOrderList() {
		// 用户一览
		List<TblOrder> listOrder;

		// 取得用户一览
		listOrder = tblOrderDao.getOrderList();

		// 返回用户一览
		return listOrder;
	}

	/**
	 * 查询用户个数
	 * 
	 * @author xiay
	 * @return
	 * @throws 无
	 */
	public long searchOrderCount(TblOrder tblOrder) {
		// 用户个数
		long dataCount;

		// 取得用户个数
		dataCount = tblOrderDao.searchOrderCount(tblOrder);

		// 返回用户个数
		return dataCount;

	}

	/**
	 * 查询用户一览
	 * 
	 * @author xiay
	 * @return
	 * @throws 无
	 */
	public List<TblOrder> searchOrderList(TblOrder tblOrder) {
		// 用户一览
		List<TblOrder> listOrder;

		// 取得用户一览
		listOrder = tblOrderDao.searchOrderList(tblOrder);

		// 返回用户一览
		return listOrder;

	}
	
	
	
	/**
	 * 根据个体商家ID取得个体商家信息   --个体商家
	 * 
	 * @author xiay
	 * @return
	 * @throws 无
	 */
	public TblOrder findShop(String pkOrder) {

		// 个体商家信息
		TblOrder shop;

		// 取得个体商家信息
		shop = tblOrderDao.findOrder(pkOrder);

		// 返回个体商家一览
		return shop;
	}
	
	/**
	 * 取得个体商家一览   --个体商家
	 * 
	 * @author xiay
	 * @return
	 * @throws 无
	 */
	public List<TblOrder> getShopList() {
		// 个体商家一览
		List<TblOrder> listShop;

		// 取得个体商家一览
		listShop = tblOrderDao.getShopList();

		// 返回个体商家一览
		return listShop;
	}

	/**
	 * 查询个体商家个数  --个体商家
	 * 
	 * @author xiay
	 * @return 
	 * @throws 无
	 */
	public long searchShopCount(TblOrderdetail orderdetail) {
		// 个体商家个数
		long dataCount;

		// 取得个体商家个数
		dataCount = orderdetailMapper.findProductCount(orderdetail);

		// 返回个体商家个数
		return dataCount;

	}

	/**
	 * 查询个体商家一览  --个体商家
	 * 
	 * @author xiay
	 * @return
	 * @throws 无
	 */
	public List<TblOrderdetail> searchShopList(TblOrderdetail orderdetail) {
		// 个体商家一览
		List<TblOrderdetail> listShop;

		// 取得个体商家一览
		listShop = orderdetailMapper.findProductList(orderdetail);

		// 返回个体商家一览
		return listShop;

	}
	
	
	
	/**
	 * 根据纯商家ID取得纯商家信息  --纯商家
	 * 
	 * @author xiay
	 * @return
	 * @throws 无
	 */
	public TblOrder findFirmShop(String pkOrder) {

		// 用户信息
		TblOrder shopFirm;

		// 取得用户信息
		shopFirm = tblOrderDao.findFirmShop(pkOrder);

		// 返回用户一览
		return shopFirm;
	}
	
	/** 
	 * 取得纯商家一览  --纯商家
	 * 
	 * @author xiay
	 * @return
	 * @throws 无
	 */
	public List<TblOrder> getFirmShopList() {
		// 用户一览
		List<TblOrder> listShopFirm;

		// 取得用户一览
		listShopFirm = tblOrderDao.getFirmShopList();

		// 返回用户一览
		return listShopFirm;
	}

	/**
	 * 查询纯商家个数  --纯商家
	 * 
	 * @author xiay
	 * @return 
	 * @throws 无
	 */
	public long searchFirmShopCount(TblOrderdetail orderdetail) {
		// 用户个数
		long dataCount;

		// 取得用户个数
		dataCount = orderdetailMapper.findProductCount(orderdetail);

		// 返回用户个数
		return dataCount;

	}

	/**
	 * 查询纯商家一览  --纯商家
 	 * 
	 * @author xiay
	 * @return 
	 * @throws 无
	 */
	public List<TblOrderdetail> searchFirmShopList(TblOrderdetail orderdetail) {
		// 用户一览
		List<TblOrderdetail> listShopFirm;

		// 取得用户一览
		listShopFirm = orderdetailMapper.findProductList(orderdetail);

		// 返回用户一览
		return listShopFirm;

	}
	
	
	/**
	 * 根据产品ID取得产品信息  --产品
	 * 
	 * @author xiay
	 * @return
	 * @throws 无
	 */
	public TblOrder findProOrder(String pkOrder) {

		// 用户信息
		TblOrder ProOrder;

		// 取得用户信息
		ProOrder = tblOrderDao.findProOrder(pkOrder);

		// 返回用户一览
		return ProOrder;
	}
	
	/** 
	 * 取得产品一览  --产品
	 * 
	 * @author xiay
	 * @return
	 * @throws 无
	 */
	public List<TblOrder> getProOrderList() {
		// 产品一览
		List<TblOrder> listProOrder;

		// 取得产品一览
		listProOrder = tblOrderDao.getProOrderList();

		// 返回产品一览
		return listProOrder;
	}

	/**
	 * 查询产品个数  --产品
	 * 
	 * @author xiay
	 * @return 
	 * @throws 无
	 */
	public long searcProOrderCount(TblOrder tblOrder) {
		// 产品个数
		long dataCount;

		// 取得产品个数
		dataCount = tblOrderDao.searcProOrderCount(tblOrder);

		// 返回产品个数
		return dataCount;

	}

	/**
	 * 查询产品一览  --产品
 	 * 
	 * @author xiay
	 * @return 
	 * @throws 无
	 */
	public List<TblOrder> searchProOrderList(TblOrder tblOrder) {
		// 产品一览
		List<TblOrder> listProOrder;

		// 取得产品一览
		listProOrder = tblOrderDao.searchProOrderList(tblOrder);

		// 返回产品一览
		return listProOrder;

	}

	/**
	 * 更新订单状态 
	 * 
	 * @author xiay
	 * @return 无
	 */
	@Override
	public void updateOrderStatus(TblOrder tblOrder) {
		tblOrder.setOrdeUpdatedate(new Date());
		tblOrderDao.updateOrderStatus(tblOrder);
	}

	/**
	 * 根据订单获取订单商品列表
	 * 
	 * @author xiay
	 * @return List<TblOrderdetail>
	 */
	@Override
	public List<TblOrderdetail> findByOrderId(TblOrderdetail orderdetail) {
		return orderdetailMapper.findByOrderId(orderdetail);
	}

	/**
	 * 根据订单获取订单商品总数
	 * 
	 * @author xiay
	 * @return int
	 */
	@Override
	public int findCountByOrderId(TblOrderdetail orderdetail) {
		return orderdetailMapper.findCountByOrderId(orderdetail);
	}
}
