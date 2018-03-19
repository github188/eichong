package com.wanma.ims.service;

import java.util.List;
import java.util.Map;

import com.wanma.ims.common.domain.MessageInfoDO;


/**
 * 首页消息
 */
public interface MessageInfoService {

	long selectMessageInfoCount(MessageInfoDO messageInfoDO);

	List<MessageInfoDO> selectMessageInfoList(MessageInfoDO messageInfoDO);
	/**
	 * 新增首页消息
	 * @param messageInfoDO
	 * @param pkPowerstation
	 */
	void insertMessageInfo(MessageInfoDO messageInfoDO, String[] pkPowerstation);
	/**
	 * 新增首页消息
	 * @param messageInfoDO
	 * @param pkPowerstation
	 */
	List<Object> getpowerstation(Map<String, String> params);
	/**
	 * 根据主键获取首页消息信息
	 * @param messageInfoDO
	 * @param pkPowerstation
	 */
	MessageInfoDO getMessageInfoById(String pkMessageInfoId);

	List<Map<String, Object>> getPowerstationById(Integer pkMessageInfoId);

	void updateMessageInfo(MessageInfoDO messageInfo);

	String getMprNameByPkPowerstation(String id);

	void relationPowerStation(MessageInfoDO messageInfo);

	void removeRelationPowerStation(MessageInfoDO messageInfo);

	boolean closeMessageInfoById(String pkMessageInfoId);

	boolean deleteMessageInfoById(String pkMessageInfoId);

	boolean deleteMessageInfoPointById(String pkMessageInfoId);


	
}
