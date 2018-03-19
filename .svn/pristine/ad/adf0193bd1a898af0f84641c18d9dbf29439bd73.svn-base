package com.wanma.app.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.app.dao.AppVfourMapper;
import com.wanma.app.service.AppVfourService;
import com.wanma.model.ChargingOrderBrief;
import com.wanma.model.TblAppButtonList;
import com.wanma.model.TblBanner;
import com.wanma.model.TblMessageInfo;
import com.wanma.model.TblMessagePointRela;
import com.wanma.model.TblNewsInfo;

@Service
public class AppVfourServiceImpl implements AppVfourService {
	@Autowired
	private AppVfourMapper appVfourMapper;

	@Override
	public List<TblBanner> getBannerList(TblBanner tblBanner) {
		// TODO Auto-generated method stub
		return appVfourMapper.getBannerList(tblBanner);
	}

	@Override
	public List<ChargingOrderBrief> getCharging(ChargingOrderBrief chargingOrderBrief) {
		// TODO Auto-generated method stub
		return appVfourMapper.getCharging(chargingOrderBrief);
	}

	@Override
	public List<TblMessageInfo> getMessageList(TblMessageInfo tblMessageInfo) {
		// TODO Auto-generated method stub
		return appVfourMapper.getMessageList(tblMessageInfo);
	}

	@Override
	public List<TblAppButtonList> getButtonList() {
		// TODO Auto-generated method stub
		return appVfourMapper.getButtonList();
	}

	@Override
	public List<TblNewsInfo> getInfoList(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return appVfourMapper.getInfoList(params);
	}

	@Override
	public List<TblMessagePointRela> getMessageInfo(
			TblMessagePointRela tblMessagePointRela) {
		// TODO Auto-generated method stub
		return appVfourMapper.getMessageInfo(tblMessagePointRela);
	}

	
	
}
