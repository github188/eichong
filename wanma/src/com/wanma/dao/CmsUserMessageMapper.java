package com.wanma.dao;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblUsermessage;

public interface CmsUserMessageMapper {
	/**
	 * 获取接收消息列表
	 * @return
	 */
	public List<Map<String, Object>> getUserMessageList(int userId);
	
	/**
	 * 添加信息
	 * @param params
	 */
	public void addUserMassage(Map<String, String> params);

	public List<TblUsermessage> getAll(TblUsermessage tblUsermessage);

	public int getCount(TblUsermessage tblUsermessage);

	public TblUsermessage getOne(TblUsermessage tblUsermessage);

	public int insert(TblUsermessage tblUsermessage);

	public int update(TblUsermessage tblUsermessage);

	public int delete(Integer pk);

	public int deleteBatch(String pks);
}
