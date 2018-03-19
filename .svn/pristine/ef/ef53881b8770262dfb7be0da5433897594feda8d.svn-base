package com.wanma.dubbox.transaction.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.wanma.dubbox.model.TblElectricPile;
import com.wanma.dubbox.model.TblElectricPileHead;
import com.wanma.dubbox.model.TblMultiMedia;
import com.wanma.dubbox.service.TblElectricPileHeadService;
import com.wanma.dubbox.service.TblElectricPileService;
import com.wanma.dubbox.service.TblMultiMediaService;
import com.wanma.dubbox.transaction.service.PileTransactionService;

/**
 * 电桩事务控制接口
 * @author lhy
 *
 */
public class PileTransactionServiceImpl implements PileTransactionService {
	@Autowired
	TblElectricPileService tblElectricPileService;
	@Autowired
	TblElectricPileHeadService pileHeadService;
	@Autowired
	TblMultiMediaService tblMultiMediaService;
	
	@Override
	public void insertPile(TblElectricPile eModel,TblMultiMedia fileModel) {
		// 新增电桩
		tblElectricPileService.insert(eModel);
		List<TblElectricPileHead> headList = eModel.getHeadList();
		if(headList != null && headList.size() > 0){
			for(int i=0;i<headList.size(); i++){
				TblElectricPileHead headInfo = headList.get(i);
				headInfo.seteId(eModel.getId());
				headInfo.setSte(0);
				headInfo.setName(i+1+"号枪头");
				headInfo.setHid(i+1);
				pileHeadService.insert(headInfo);
			}
		}
		if(fileModel != null){
			fileModel.setRfcId(eModel.getId());
			tblMultiMediaService.update(fileModel);
		}
	}

	@Override
	public void updatePile(TblElectricPile eModel,
			TblMultiMedia fileModel) {
		tblElectricPileService.update(eModel);
		List<TblElectricPileHead> headList = eModel.getHeadList();
		for(TblElectricPileHead headInfo:headList){
			if(headInfo.getId() == null)
				pileHeadService.insert(headInfo);
			else
				pileHeadService.update(headInfo);
		}
	}

	@Override
	public void deletePile(TblElectricPile eModel) throws Exception {
		eModel.setDelFlag(new Short("1"));
		tblElectricPileService.delete(eModel);
		TblElectricPileHead hModel = new TblElectricPileHead();
		hModel.setFkIds(eModel.getPkIds());
		hModel.setDelFlag(new Short("1"));
		pileHeadService.delete(hModel);
	}
}
