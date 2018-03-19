package com.wanma.app.dao;


import java.util.List;
import java.util.Map;

import com.wanma.model.TblHomepage;

/**
  * @Description: 首页广告表操作dao层接口mapper
  * @author songjf 
  * @createTime：2015-4-23 上午10:14:41 
  * @updator： 
  * @updateTime：   
  * @version：V1.0
 */
public interface AppHomepageMapper {    
    
	public TblHomepage get(java.lang.Integer pkHomepage);
	
	public <K, V> Map<K, V> findOne(java.lang.Integer pkHomepage);
	
	/**
	 * @Title: findHomepages
	 * @Description: 获取首页广告
	 * @param params
	 * @return 
	 */
	public <T, K, V> List<T> findHomepages(Map<K, V> params);
	
	public int insert(TblHomepage tblHomepage);
	
	public int update(TblHomepage tblHomepage);
	
	public int delete(java.lang.Integer pkHomepage );

}


