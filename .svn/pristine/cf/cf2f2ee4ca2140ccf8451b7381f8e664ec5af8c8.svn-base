package com.wanma.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.common.log.SystemControllerLog;
import com.wanma.dao.CmsUserMessageMapper;
import com.wanma.dao.TblUserMapper;
import com.wanma.model.TblUser;
import com.wanma.model.TblUsermessage;

@Service
public class CmsUserMessageServiceImpl {
	@Autowired
	CmsUserMessageMapper cmsUserMessageMapper;
	@Autowired
	TblUserMapper userMapper;
	/**
	 * 获取接收到的消息列表
	 * @return
	 */
	public List<Map<String, Object>> getUserMessageList(int userId){
		return cmsUserMessageMapper.getUserMessageList(userId);
	}
	
	/**
	 * 添加个人信息
	 * @param params
	 * 	  		title 标题
	 * 			content 内容
	 * 			to_user_id 接收人id
	 * 			from_user_id 发送人id
	 * 			from_user_name 发送人姓名
	 */
	@SystemControllerLog(description = "添加个人信息")
	public void addUserMessage(Map<String, String> params){
		cmsUserMessageMapper.addUserMassage(params);
	}
	
	
	public List<TblUsermessage> getAll(TblUsermessage tblUsermessage) {
		return cmsUserMessageMapper.getAll(tblUsermessage);
	}

	public int getCount(TblUsermessage tblUsermessage) {
		return cmsUserMessageMapper.getCount(tblUsermessage);
	}
	
	public TblUsermessage getOne(TblUsermessage tblUsermessage) {
		return cmsUserMessageMapper.getOne(tblUsermessage);
	}

	@SystemControllerLog(description = "添加个人信息")
	public int insert(TblUsermessage tblUsermessage) {
		if(tblUsermessage.getFromUserid()!=0){
			TblUser user=userMapper.findNomalUser(tblUsermessage.getFromUserid().longValue());
			tblUsermessage.setFromUsername(user.getNormName());
		}else{
			tblUsermessage.setFromUsername("爱充网");
		}
		tblUsermessage.setStatus(0);
		tblUsermessage.setCreateTime(new Date());
		return cmsUserMessageMapper.insert(tblUsermessage);
	}

	@SystemControllerLog(description = "更新个人信息")
	public int update(TblUsermessage tblUsermessage) {
		return cmsUserMessageMapper.update(tblUsermessage);
	}
	
	@SystemControllerLog(description = "删除个人信息")
	public int delete(Integer pk) {
		return cmsUserMessageMapper.delete(pk);
	}

	@SystemControllerLog(description = "批量删除个人信息")
	public int deleteBatch(String pks) {
		return cmsUserMessageMapper.deleteBatch(pks);
	}
	
}
