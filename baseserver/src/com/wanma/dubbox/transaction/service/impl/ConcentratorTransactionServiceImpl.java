package com.wanma.dubbox.transaction.service.impl;


import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.wanma.dubbox.model.TblConcentrator;
import com.wanma.dubbox.model.TblElectricPile;
import com.wanma.dubbox.service.TblConcentratorService;
import com.wanma.dubbox.service.TblElectricPileService;
import com.wanma.dubbox.transaction.service.ConcentratorTransactionService;
import com.wanma.util.ObjectUtil;

/**
 * 集中器事务控制服务
 * @author lhy
 *
 */
public class ConcentratorTransactionServiceImpl implements
		ConcentratorTransactionService {
	@Autowired
	TblElectricPileService tblElectricPileService;
	@Autowired
	TblConcentratorService concentratorService;

	@Override
	public void insertConcentrator(TblConcentrator cModel) {
		concentratorService.insert(cModel);
		String postEleids = cModel.getElids();
		if (!StringUtils.isBlank(cModel.getElids())) {
			String[] electricpileIds = postEleids.split(",");
			for (int i = 0; i < electricpileIds.length; i++) {
				TblElectricPile tblElectricpile = new TblElectricPile();
				tblElectricpile.setId(electricpileIds[i]);
				tblElectricpile.setConId(cModel.getId());
				tblElectricPileService.update(tblElectricpile);
			}
		}
	}

	@Override
	public void updateConcentrator(TblConcentrator cModel) {
		concentratorService.update(cModel);
		String postEleids = cModel.getElids();
		if (StringUtils.isNotBlank(cModel.getElids())) {
			String[] electricpileIds = postEleids.split(",");
			TblElectricPile tblElectricpile = new TblElectricPile();
			tblElectricpile.setPkIds(electricpileIds);
			tblElectricpile.setConId(cModel.getId());
			tblElectricPileService.update(tblElectricpile);
		}

	}

	@Override
	public void deleteConcentrator(TblConcentrator cModel) {
		concentratorService.delete(cModel);
		String[] array=null;
		List<TblElectricPile> electricList=null;
		TblElectricPile electric=null;
		//对每个集中器下的电桩进行解绑
		for (String id : cModel.getPkIds()) {
			electric=new TblElectricPile();
			electric.setConId(id);
			electricList=tblElectricPileService.getList(electric);
			if(ObjectUtil.isNotEmpty(electricList)){
				array=new String[electricList.size()];
				for(int i=0;i<electricList.size();i++){
					array[i]=electricList.get(i).getId();
				}
				electric = new TblElectricPile();
				electric.setConId("0");
				electric.setPkIds(array);
				tblElectricPileService.update(electric);
			}
		}
	}

}
