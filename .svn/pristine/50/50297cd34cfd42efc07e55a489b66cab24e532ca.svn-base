package com.wanma.app.dao;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblPurchasehistory;


/**
 * 数据访问接口
 * 
 */
public interface TblPurchasehistoryMapper {

	/**
	 * 获取消费记录
	 * @return String orderId
	 */
	List<TblPurchasehistory> find(TblPurchasehistory tblPurchasehistory);
	
	/**
	 * 获取个人账单
	 * @param params
	 * 			uId 用户id， pageNum 页码，pageNumber 每页数量
	 * @return
	 */
	List<TblPurchasehistory> myBills(Map<String, Object> params);
	
	/**
	 * 获取发票对应的消费列表
	 * @param iId 发票id
	 * @return
	 */
	List<TblPurchasehistory> invoicePurList(long iId);
	
	public void insert(TblPurchasehistory ph);
	
	public void updateInvoice(Map<String,Object> params);
	
	/**
	 * 获取账单
	 */
	List<Map<String,Object>> getMtBills(Map<String,Object> params);
	
	/**
	 * 获取消费记录
	 */
	List<Map<String,Object>> getPhDetail(Map<String,Object> params);
}
