/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.web.service;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblProduct;
import com.wanma.model.TblSearchhistory;

/**
 * 反馈信息业务处理接口
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public interface WebSearchhistoryService {

	/**
	 * 获取历史搜索记录
	 */
	public List<TblSearchhistory> getMallSearchHistory();
	/**
	 * 删除搜索历史记录
	 */
	public void removeMallSearchHistory();
	/**
	 * @Title: findProducts
	 * @Description: 获取产品列表
	 * @param params
	 * @return
	 */
	public List<TblProduct> findProductListByShopSearch(TblSearchhistory tblSearchhistory,TblProduct tblProduct);


}
