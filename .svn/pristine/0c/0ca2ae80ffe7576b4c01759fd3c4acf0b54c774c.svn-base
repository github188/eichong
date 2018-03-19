package com.wanma.web.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wanma.model.TblUsermessage;

public interface WebUsermessageMapper {
	 public final static String PREFIX = WebUsermessageMapper.class.getName();
	 
	 //获取当前用户的全部消息
	 public List<TblUsermessage> getByUserId(Map<String,Object> params); 
	 public  long countUsermessage(Map<String,Object>params);
	 //获取系统消息以及用户自己发给自己的消息
	 public List<HashMap<String,Object>> getSystemMsg(Map<String,Object> params); 
	 public  long countSystemMessage(Map<String,Object>params);
	 
	 
	 //根据消息ID获取单个消息
	 public TblUsermessage getUsermessageById(int id);
	 //根据消息id修改消息状态
	 public void updateById(int id);
	 
	 //获取个人私信列表
	 public  List<HashMap<String,Object>> getPersonMessage(Map<String,Object> params); 
	 public  long countPersonMessage(Map<String,Object>params);

}
