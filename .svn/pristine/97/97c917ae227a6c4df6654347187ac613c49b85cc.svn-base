package com.wanma.dao;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblMessageInfo;

public interface CmsMessageInfoMapper {

	List<TblMessageInfo> getMessageInfoList(TblMessageInfo messageInfo);

	long getMessageInfoListCount(TblMessageInfo messageInfo);

	List<Map<String, Object>> getCityName();

	List<Object> getpowerstation(Map<String, String> params);

	void insertMessageInfo(TblMessageInfo messageInfo);

	String getMprName(int powerstationId);

	void relationPowerStation(TblMessageInfo messageInfo);

	TblMessageInfo getMessageInfoById(int pkMessageInfoId);

	List<Map<String, Object>> getPowerstationById(int pkMessageInfoId);

	void removeRelationPowerStation(TblMessageInfo messageInfo);

	void updateMessageInfo(TblMessageInfo messageInfo);

	public String getMprNameByPkPowerstation(String pkPowerstation);

	void closeMessageInfoById(int pkMessageInfoId);

	void deleteMessageInfoById(int pkMessageInfoId);

	void deleteMessageInfoPointById(int pkMessageInfoId);
	
	
	
	
	
	
	
	
	
	
	
	
	
}
