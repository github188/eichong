package com.wanma.app.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.app.dao.AppUserMessageMapper;
import com.wanma.app.service.AppUserMessageService;

@Service
public class AppUserMessageServiceImpl implements AppUserMessageService {
	
	/**
	 * 获取接收到的消息列表
	 * @return
	 */
	public List<Map<String, Object>> getUserMessageList(int userId){
		return appUserMessageMapper.getUserMessageList(userId);
	}

	public List<Map<String, Object>> getUserMessageTitleList(Map<String, Object> params) {
		return appUserMessageMapper.getUserMessageTitleList(params);
	}

	public Map<String, Object> myMessageContent(int messageId) {
		return appUserMessageMapper.getMyMessageContent(messageId);
	}
	
	
	@Autowired
	AppUserMessageMapper appUserMessageMapper;
}
