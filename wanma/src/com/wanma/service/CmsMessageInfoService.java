package com.wanma.service;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblMessageInfo;


/**
 * 首页信息业务处理器
 * @author mb
 * 
 */
public interface CmsMessageInfoService {

	List<TblMessageInfo> getMessageInfoList(TblMessageInfo messageInfo);

	long getMessageInfoListCount(TblMessageInfo messageInfo);

	List<Map<String, Object>> getCityName();

	List<Object> getpowerstation(Map<String, String> params);

	void insertMessageInfo(TblMessageInfo messageInfo, String[] pkPowerstation);

	TblMessageInfo getMessageInfoById(int pkMessageInfoId);

	List<Map<String, Object>> getPowerstationById(int pkMessageInfoId);

	void relationPowerStation(TblMessageInfo messageInfo);

	void removeRelationPowerStation(TblMessageInfo messageInfo);

	void updateMessageInfo(TblMessageInfo messageInfo);

	String getMprNameByPkPowerstation(String pkPowerstation);

	void closeMessageInfoById(int pkMessageInfoId);

	void deleteMessageInfoById(int pkMessageInfoId);

	void deleteMessageInfoPointById(int pkMessageInfoId);


	

}
