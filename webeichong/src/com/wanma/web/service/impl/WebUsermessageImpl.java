package com.wanma.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.model.TblUsermessage;
import com.wanma.web.dao.WebUsermessageMapper;
import com.wanma.web.service.WebUsermessageService;
@Service
public class WebUsermessageImpl implements WebUsermessageService {
	
	@Autowired
	WebUsermessageMapper  usermessageMapper;
	
	
	//获取消息
	@Override
	public List<TblUsermessage> getByuserId(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return usermessageMapper.getByUserId(params);
	}
	@Override
	public long countUsermessage(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return  usermessageMapper.countUsermessage(params);
	}
	
	@Override
	public TblUsermessage getUsermessageById(int id) {
		// TODO Auto-generated method stub
		return usermessageMapper.getUsermessageById(id);
	}

	//修改消息状态
	@Override
	public boolean updateById(int id) {
		usermessageMapper.updateById(id);
		return true;
	}
	@Override
	public List<HashMap<String, Object>> getSystemMsg(Map<String, Object> params) {
		return usermessageMapper.getSystemMsg(params);
	}
	@Override
	public long countSystemMessage(Map<String, Object> params) {
		return usermessageMapper.countSystemMessage(params);
	}
	
	@Override
	public List<HashMap<String, Object>> getPersonMessage(Map<String, Object> params) {
		return usermessageMapper.getPersonMessage(params);
	}
	@Override
	public long countPersonMessage(Map<String, Object> params) {
		return usermessageMapper.countPersonMessage(params);
	}	
}
