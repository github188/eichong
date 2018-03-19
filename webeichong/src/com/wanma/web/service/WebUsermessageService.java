package com.wanma.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wanma.model.TblUsermessage;

public interface WebUsermessageService {
           //根据接收人id查出所有消息
	    public List<TblUsermessage>  getByuserId(Map<String, Object> params);
	    public long countUsermessage(Map<String, Object> params);
	    
	    
	    //获取系统消息以及用户自己发给自己的消息
		 public List<HashMap<String,Object>> getSystemMsg(Map<String,Object> params); 
		 public  long countSystemMessage(Map<String,Object>params);
	    
	    //根据消息id查出消息
	    public TblUsermessage getUsermessageById(int id);
	    
	    
	    //根据消息id修改消息状态
	    public boolean updateById(int id);
	    
	    
	  //获取私信
		 public List<HashMap<String,Object>> getPersonMessage(Map<String,Object> params); 
		 public  long countPersonMessage(Map<String,Object>params);
}
