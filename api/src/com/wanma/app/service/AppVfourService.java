package com.wanma.app.service;

import java.util.List;
import java.util.Map;

import com.wanma.model.ChargingOrderBrief;
import com.wanma.model.TblAppButtonList;
import com.wanma.model.TblBanner;
import com.wanma.model.TblMessageInfo;
import com.wanma.model.TblMessagePointRela;
import com.wanma.model.TblNewsInfo;

public interface AppVfourService {

	List<TblBanner> getBannerList(TblBanner tblBanner);

	//long getChargingCount(TblChargingOrder tblChargingOrder);

	List<ChargingOrderBrief> getCharging(ChargingOrderBrief chargingOrderBrief);

	List<TblMessageInfo> getMessageList(TblMessageInfo tblMessageInfo);

	List<TblAppButtonList> getButtonList();

	List<TblNewsInfo> getInfoList(Map<String, Object> params);

	List<TblMessagePointRela> getMessageInfo(TblMessagePointRela tblMessagePointRela);

	
}
