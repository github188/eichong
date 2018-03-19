package com.wanma.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.TblChargOrderMapper;
import com.wanma.dao.TblReconciliationMapper;
import com.wanma.model.TblChargingOrder;
import com.wanma.model.TblReconciliation;
import com.wanma.service.TblChargingOrderService;

@Service
public class TblChargingOrderServiceImpl implements TblChargingOrderService {

	@Autowired
	private TblChargOrderMapper chargOrderMapper;
	@Autowired
	private TblReconciliationMapper tblreconciliation;

	@Override
	public void update(TblChargingOrder model) {
		chargOrderMapper.update(model);
	}

	@Override
	public List<TblChargingOrder> getList(TblChargingOrder model) {
		return chargOrderMapper.getList(model);
	}

	@Override
	public TblChargingOrder selectOne(TblChargingOrder model) {
		return chargOrderMapper.selectOne(model);
	}

	@Override
	public TblChargingOrder getOderByOpenId(String openId) {
		return chargOrderMapper.getOderByOpenId(openId);
	}

	@Override
	public List<Map<String, Object>> getListGroupByPileNumByPsId(
			TblChargingOrder odrModel) {
		return chargOrderMapper.getListGroupByPileNumByPsId(odrModel);
	}

	@Override
	public List<Map<String, Object>> getListGroupByHeadNoByPileNum(
			TblChargingOrder odrModel) {
		return chargOrderMapper.getListGroupByHeadNoByPileNum(odrModel);
	}

	@Override
	public TblChargingOrder selectChargeOrder(TblChargingOrder model) {
		return chargOrderMapper.selectChargeOrder(model);
	}

	@Override
	public Map<String, Object> fandChargeOrderSummary(Map<String, Object> params) {
		return chargOrderMapper.fandChargeOrderSummary(params);
	}

	@Override
	public List<TblChargingOrder> findChargeOrder(Map<String, Object> params) {
		return chargOrderMapper.findChargeOrder(params);
	}

	@Override
	public void modifyOrderById(Map<String, Object> params) {
		chargOrderMapper.ChargeOrderById(params);
	}

	@Override
	public int insert(TblReconciliation tblReconciliation) {
		return tblreconciliation.insert(tblReconciliation);
	}

	@Override
	public void modifyStatusById(Map<String, Object> params) {
		tblreconciliation.chargeStatusById(params);
	}

	@Override
	public int checkChargeOrderIsExist(String StartChargeSeq) {
		return chargOrderMapper.checkChargeOrderIsExist(StartChargeSeq);

	}

	@Override
	public Map<String, Object> getChargeOrderInfo(String StartChargeSeq) {
		return chargOrderMapper.getChargeOrderInfo(StartChargeSeq);
	}

	@Override
	public Map<String, Object> getChargingOrderState(String StartChargeSeq) {
		return chargOrderMapper.getChargingOrderState(StartChargeSeq);
	}

	@Override
	public int checkCpyNum(int org) {
		return chargOrderMapper.checkCpyNum(org);
	}

	@Override
	public List<Map<String, Object>> getNariChargeOrder(
			Map<String, Object> model) {
		return chargOrderMapper.getNariChargeOrder(model);
	}

	@Override
	public List<Map<String, Object>> selectCpynumber() {
		return chargOrderMapper.selectCpynumber();
	}

	@Override
	public int checkChargeOrderNum(String StartChargeSeq) {
		return chargOrderMapper.checkChargeOrderNum(StartChargeSeq);
	}

}
