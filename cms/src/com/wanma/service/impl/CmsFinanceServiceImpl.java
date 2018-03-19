/**     
 * @Title:  CmsFinanceServiceImpl.java   
 * @Package com.wanma.service.impl   
 * @Description:    TODO  
 * @author: Android_Robot     
 * @date:   2016年1月9日 上午10:45:41   
 * @version V1.0     
 */
package com.wanma.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.CmsFinanceMapper;
import com.wanma.model.TblChargingOrder;
import com.wanma.model.TblInvoice;
import com.wanma.model.TblPurchasehistory;
import com.wanma.service.CmsFinanceService;

/**
 * @author bc
 *
 */
@Service
public class CmsFinanceServiceImpl implements CmsFinanceService {
	@Autowired
	private CmsFinanceMapper cmsFinanceMapper;

	@Override
	public List<Map<String, Object>> personConsumeDetail(Map<String, Object> params) {
		return cmsFinanceMapper.personConsumeDetail(params);
	}

	@Override
	public List<Map<String, Object>> personConsumeStatistic(Map<String, Object> params) {
		return cmsFinanceMapper.personConsumeStatistic(params);
	}

	@Override
	public List<Map<String, Object>> personChargeDetail(Map<String, Object> params) {
		return cmsFinanceMapper.personChargeDetail(params);
	}
	@Override
	public List<Map<String, Object>> personChargeDetail_ept(Map<String, Object> params) {
		return cmsFinanceMapper.personChargeDetail_ept(params);
	}
	@Override
	public List<Map<String, Object>> personBespokeDetail(Map<String, Object> params) {
		return cmsFinanceMapper.personBespokeDetail(params);
	}

	@Override
	public List<Map<String, Object>> personChargeStatistic(Map<String, Object> params) {
		return cmsFinanceMapper.personChargeStatistic(params);
	}

	@Override
	public List<Map<String, Object>> personBespokeStatistic(Map<String, Object> params) {
		return cmsFinanceMapper.personBespokeStatistic(params);
	}

	@Override
	public List<Map<String, Object>> businessChargeDetail(Map<String, Object> params) {
		return cmsFinanceMapper.businessChargeDetail(params);
	}
	@Override
	public List<Map<String, Object>> businessChargeDetail_ept(Map<String, Object> params) {
		return cmsFinanceMapper.businessChargeDetail_ept(params);
	}
	@Override
	public List<Map<String, Object>> businessBespokeDetail(Map<String, Object> params) {
		return cmsFinanceMapper.businessBespokeDetail(params);
	}

	@Override
	public List<Map<String, Object>> businessChargeStatistic(Map<String, Object> params) {
		return cmsFinanceMapper.businessChargeStatistic(params);
	}

	@Override
	public List<Map<String, Object>> businessBespokeStatistic(Map<String, Object> params) {
		return cmsFinanceMapper.businessBespokeStatistic(params);
	}

	@Override
	public long personConsumeDetailCount(Map<String, Object> params) {
		return cmsFinanceMapper.personConsumeDetailCount(params);
	}

	@Override
	public long personConsumeStatisticCount(Map<String, Object> params) {
		return cmsFinanceMapper.personConsumeStatisticCount(params);
	}

	@Override
	public long personChargeDetailCount(Map<String, Object> params) {
		return cmsFinanceMapper.personChargeDetailCount(params);
	}

	@Override
	public long personBespokeDetailCount(Map<String, Object> params) {
		return cmsFinanceMapper.personBespokeDetailCount(params);
	}

	@Override
	public long personChargeStatisticCount(Map<String, Object> params) {
		return cmsFinanceMapper.personChargeStatisticCount(params);
	}

	@Override
	public long personBespokeStatisticCount(Map<String, Object> params) {
		return cmsFinanceMapper.personBespokeStatisticCount(params);
	}

	@Override
	public long businessChargeDetailCount(Map<String, Object> params) {
		return cmsFinanceMapper.businessChargeDetailCount(params);
	}

	@Override
	public long businessBespokeDetailCount(Map<String, Object> params) {
		return cmsFinanceMapper.businessBespokeDetailCount(params);
	}

	@Override
	public long businessChargeStatisticCount(Map<String, Object> params) {
		return cmsFinanceMapper.businessChargeStatisticCount(params);
	}

	@Override
	public long businessBespokeStatisticCount(Map<String, Object> params) {
		return cmsFinanceMapper.businessBespokeStatisticCount(params);
	}
	@Override
	public long getInvoiceManageCount(TblInvoice tblInvoice) {

		return cmsFinanceMapper
				.getInvoiceManageCount(tblInvoice);
	}

	@Override
	public List<TblInvoice> getInvoiceManageList(
			TblInvoice tblInvoice) {
		return cmsFinanceMapper
				.getInvoiceManageList(tblInvoice);
	}
	@Override
	public TblInvoice getInvoiceById(int pkInvoice){
		return cmsFinanceMapper.getInvoiceById(pkInvoice);
	};
	@Override
	public void changeIvNumberById(Map<String, Object> params){
		 cmsFinanceMapper.changeIvNumberById(params);
	};
	@Override
	public TblChargingOrder getChargingOrderById(int pkChargingOrder){
		return cmsFinanceMapper.getChargingOrderById(pkChargingOrder);
	};
	@Override
	public void updateChargingSate(Map<String, Object> params)
	{ cmsFinanceMapper.updateChargingSate(params);
		
	}
	
	@Override
	public  int insert(TblPurchasehistory record){
		return cmsFinanceMapper.insert(record);
	};
	@Override
	public void changePurHistoryById(Map<String, Object> params){
		 cmsFinanceMapper.changePurHistoryById(params);
	}

	@Override
	public List<TblInvoice> getInvoiceManageList_export(TblInvoice tblInvoice) {
		return cmsFinanceMapper.getInvoiceManageList_export(tblInvoice);
	};
}

