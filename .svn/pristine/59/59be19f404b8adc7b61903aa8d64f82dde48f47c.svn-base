/**     
 * @Title:  CmsFinanceMapper.java   
 * @Package com.wanma.dao   
 * @Description:    TODO  
 * @author: Android_Robot     
 * @date:   2016年1月9日 上午10:46:53   
 * @version V1.0     
 */
package com.wanma.dao;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblChargingOrder;
import com.wanma.model.TblCompany;
import com.wanma.model.TblInvoice;
import com.wanma.model.TblPurchasehistory;

/**
 * @author bc
 *
 */
public interface CmsFinanceMapper {

	public List<Map<String, Object>> personConsumeDetail(
			Map<String, Object> params);

	public long personConsumeDetailCount(Map<String, Object> params);

	public List<Map<String, Object>> personConsumeStatistic(
			Map<String, Object> params);

	public long personConsumeStatisticCount(Map<String, Object> params);

	public List<Map<String, Object>> personChargeDetail(
			Map<String, Object> params);

	public long personChargeDetailCount(Map<String, Object> params);

	public List<Map<String, Object>> personBespokeDetail(
			Map<String, Object> params);

	public long personBespokeDetailCount(Map<String, Object> params);

	public List<Map<String, Object>> personChargeStatistic(
			Map<String, Object> params);

	public long personChargeStatisticCount(Map<String, Object> params);

	public List<Map<String, Object>> personBespokeStatistic(
			Map<String, Object> params);

	public long personBespokeStatisticCount(Map<String, Object> params);

	public List<Map<String, Object>> businessChargeDetail(
			Map<String, Object> params);

	public long businessChargeDetailCount(Map<String, Object> params);

	public List<Map<String, Object>> businessBespokeDetail(
			Map<String, Object> params);

	public long businessBespokeDetailCount(Map<String, Object> params);

	public List<Map<String, Object>> businessChargeStatistic(
			Map<String, Object> params);

	public long businessChargeStatisticCount(Map<String, Object> params);

	public List<Map<String, Object>> businessBespokeStatistic(
			Map<String, Object> params);

	public long businessBespokeStatisticCount(Map<String, Object> params);

	public long getInvoiceManageCount(TblInvoice tblInvoice);

	public List<TblInvoice> getInvoiceManageList(TblInvoice tblInvoice);

	public TblInvoice getInvoiceById(int pkInvoice);
	
	public void changeIvNumberById(Map<String, Object> params);
	
	public TblChargingOrder getChargingOrderById(int pkChargingOrder);

	public void updateChargingSate(Map<String, Object> params);

	public List<Map<String, Object>> businessChargeDetail_ept(
			Map<String, Object> params);

	public List<Map<String, Object>> personChargeDetail_ept(
			Map<String, Object> params);

	public int insert(TblPurchasehistory record);

	public void changePurHistoryById(Map<String, Object> params);

	public List<TblInvoice> getInvoiceManageList_export(TblInvoice tblInvoice);
	
	public List<Map<String, Object>> getCustomerBillManageList(Map<String, Object> params);
	
	public long getCustomerBillManageListCount(	Map<String, Object> params);
	
	public List<Map<String, Object>> getinvoiceRecordList(Map<String, Object> params);
	
	public long getinvoiceRecordListCount(Map<String, Object> params);
	
	public List<Map<String, Object>> getBillOrderList(
			Map<String, Object> params);
	
	public TblInvoice getInvoiceRecordById(int pkInvoice);
	
	public void changeInvoiceRecord(Map<String, Object> params);
	
	public long getBillOrderListCount(Map<String, Object> params);
	
	public int  insertInvoiceRecord(TblInvoice tblInvoice);
	
	public void removeInvoiceRecord(TblInvoice tblInvoice);
	
	public void getModifyBillingStatus(Map<String, Object> params);
	
	public List<Map<String, Object>> getInvoicedOrderList(
			Map<String, Object> params);

	public long getInvoicedOrderListCount(Map<String, Object> params);
}
