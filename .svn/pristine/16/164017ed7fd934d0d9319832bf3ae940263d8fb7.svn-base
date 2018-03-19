package com.wanma.web.dao;

import java.util.List;
import java.util.Map;


import com.wanma.model.WebList;

public interface WebListMapper {
	
   public final static String PREFIX = WebListMapper.class.getName();
	//查出所有的WebList
   public List<WebList> findAll();
   //查出某个类型的WebList
   public List<WebList> findByType(String releType);
   //查出某个id的WebList
   public WebList findByPk(int pkRelease);
   
	 //获取关于我们全部消息
   public List<WebList> getAllByType(Map<String,Object> params); 
   public  long countWebList(Map<String,Object>params);
}
