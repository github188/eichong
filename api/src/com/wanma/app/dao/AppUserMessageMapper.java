package com.wanma.app.dao;

import java.util.List;
import java.util.Map;

public interface AppUserMessageMapper {
	/**
	 * 获取接收消息列表
	 * @return
	 */
	public List<Map<String, Object>> getUserMessageList(int userId);

	public List<Map<String, Object>> getUserMessageTitleList(Map<String, Object> params);

	public Map<String, Object> getMyMessageContent(int messageId);
}
