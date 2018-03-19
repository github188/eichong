package com.wanma.dubbox.transaction.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.wanma.dubbox.model.TblAdvertisement;
import com.wanma.dubbox.model.TblMultiMedia;
import com.wanma.dubbox.service.TblAdvertisementService;
import com.wanma.dubbox.service.TblMultiMediaService;
import com.wanma.dubbox.transaction.service.AdvTransactionService;

/**
 * 电桩事务控制接口
 * @author lhy
 *
 */
public class AdvTransactionServiceImpl implements AdvTransactionService {
	@Autowired
	TblAdvertisementService advService;
	@Autowired
	TblMultiMediaService tblMultiMediaService;
	
	@Override
	public void insert(TblAdvertisement model) {
		advService.insert(model);
		updateImg(model);
	}

	@Override
	public void update(TblAdvertisement model) {
		advService.update(model);
		updateImg(model);
	}
	
	private void updateImg(TblAdvertisement model){
		String advPicMfRefId = model.getPicFid();
		if (StringUtils.isNotBlank(advPicMfRefId)) {
			TblMultiMedia mdModel = new TblMultiMedia();
			mdModel.setRfcId(model.getId());
			mdModel.setFileId(advPicMfRefId);
			tblMultiMediaService.update(mdModel);
		}
		String advListPicMfRefId = model.getPicListFid();
		if (StringUtils.isNotBlank(advListPicMfRefId)) {
			TblMultiMedia mdModel = new TblMultiMedia();
			mdModel.setRfcId(model.getId());
			mdModel.setFileId(advListPicMfRefId);
			tblMultiMediaService.update(mdModel);
		}
	}
}
