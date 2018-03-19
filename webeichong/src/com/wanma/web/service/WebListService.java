package com.wanma.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.wanma.model.WebList;

public interface WebListService {
	
	
		/**
		 * 查出所有的WebList
		 */
       public List<WebList> findAll();
       
       
       /**
        * 根据类型查出WebList
        * @param releType
        * @return
        */
       public List<WebList> findByType(String releType);
       
       
       /**
        * 根据id查出单个WebList
        * @param releTyp
        * @return
        */      
       public WebList findByPk(int pkRelease);
       
       //获取关于我们全部消息
       public List<WebList> getAllByType(Map<String,Object> params); 
       
       public  long countWebList(Map<String,Object>params);
}
