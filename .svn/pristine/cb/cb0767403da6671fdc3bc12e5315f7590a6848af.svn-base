package com.wanma.dubbox.transaction.service.impl;


import org.springframework.beans.factory.annotation.Autowired;

import com.wanma.dubbox.model.TblElectricPile;
import com.wanma.dubbox.model.TblMultiMedia;
import com.wanma.dubbox.model.TblPowerStation;
import com.wanma.dubbox.service.TblElectricPileService;
import com.wanma.dubbox.service.TblMultiMediaService;
import com.wanma.dubbox.service.TblPowerStationService;
import com.wanma.dubbox.transaction.service.PowerStationTransactionService;

/**
 * 电桩事务控制接口
 * 
 * @author lhy
 *
 */
public class PowerStationTransactionServiceImpl implements
		PowerStationTransactionService {
	@Autowired
	TblPowerStationService powerStationService;
	@Autowired
	TblElectricPileService tblElectricPileService;
	@Autowired
	TblMultiMediaService tblMultiMediaService;

	@Override
	public void insertStation(TblPowerStation psModel,
			TblMultiMedia fileModel) {
		powerStationService.insert(psModel);
		String eIds = psModel.getElids();
		if (eIds != null && eIds.length() > 0) {
			String[] arry = eIds.split(",");
			for (int i = 0; i < arry.length; i++) {
				TblElectricPile eModel = new TblElectricPile();
				eModel.setNum(i);
				eModel.setId(arry[i]);
				eModel.setIsBd(1);
				eModel.setPowId(psModel.getId());
				tblElectricPileService.update(eModel);
			}
		}
		if (fileModel != null) {
			fileModel.setRfcId(psModel.getId());
			tblMultiMediaService.update(fileModel);
		}
	}

	@Override
	public void updateStation(TblPowerStation psModel, TblElectricPile eModel,
			TblMultiMedia fileModel) throws Exception {
		if (psModel.getId() != null)
			powerStationService.update(psModel);
		else if (psModel.getPkIds() != null)
			powerStationService.updateMore(psModel);
		String newEids = psModel.getElids();
		if (newEids != null && newEids.length() > 0) {
			TblPowerStation psModelTemp = new TblPowerStation();
			psModelTemp.setId(psModel.getId());
			psModelTemp.setRtColumns("elids");
			psModelTemp = powerStationService.selectOne(psModelTemp);
			String oldEids = psModelTemp.getElids();
			int oldCount = 0;
			if (oldEids != null)
				oldCount = oldEids.split(",").length;
			newEids = newEids.replace(oldEids, "");
			String[] arry = newEids.split(",");
			for (int i = 0; i < arry.length; i++) {
				String eId = arry[i];
				if (eId != null && !"".equals(eId)) {
					TblElectricPile eModelTemp = new TblElectricPile();
					eModelTemp.setNum(oldCount+i);
					eModelTemp.setId(eId);
					eModelTemp.setIsBd(1);
					eModelTemp.setPowId(psModel.getId());
					tblElectricPileService.update(eModelTemp);
				}
			}
		}
		if (eModel != null)
			tblElectricPileService.updateMore(eModel);
		if (fileModel != null) {
			fileModel.setRfcId(psModel.getId());
			tblMultiMediaService.update(fileModel);
		}
	}

	@Override
	public void deleteStation(TblPowerStation psModel) {

	}
}
