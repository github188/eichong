package com.wanma.web.dao;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblRecruit;



public interface WebTblRecruitMapper {
	
   public final static String PREFIX = WebTblRecruitMapper.class.getName();
	//查出所有的WebList
   public List<TblRecruit> findSome();
   //查出某个类型的WebList
 //  public List<WebList> findByType(String releType);
   //查出某个id的WebList
   public TblRecruit findByPk(int pkRecuit);
   
	 //获取招聘全部消息
   public List<TblRecruit> getAll(Map<String,Object> params); 
   public  long countRecruit(Map<String,Object>params);
   
}
