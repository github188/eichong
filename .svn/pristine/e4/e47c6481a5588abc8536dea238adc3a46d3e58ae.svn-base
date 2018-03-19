package com.wanma.web.service;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblRecruit;


public interface WebTblRecruitService {
	
	
		/**
		 * 查出所有的TblRecruit
		 */
       public List<TblRecruit> findSome();
       
       
//       /**
//        * 根据类型查
//        * @param 
//        * @return
//        */
//       public List<TblRecruit> findByType(String releType);
       
       
       /**
        * 根据id查出单个TblRecruit
        * @param pkRecuit
        * @return
        */      
       public TblRecruit findByPk(int pkRecruit);
       //获取招聘全部消息
       public List<TblRecruit> getAll(Map<String,Object> params); 
       public  long countRecruit(Map<String,Object>params);
}
