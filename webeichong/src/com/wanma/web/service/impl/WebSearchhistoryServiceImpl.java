/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.model.TblProduct;
import com.wanma.model.TblSearchhistory;
import com.wanma.web.dao.WebProductMapper;
import com.wanma.web.dao.WebSearchhistoryMapper;
import com.wanma.web.service.WebSearchhistoryService;

/***
 * 商城搜索
  * @Description:
  * @author bruce cheng(http://blog.csdn.net/brucehome)
  * @createTime：2015-3-13 下午04:51:34 
  * @updator： 
  * @updateTime：   
  * @version：V1.0
 */
@Service
public class WebSearchhistoryServiceImpl implements WebSearchhistoryService {

	@Autowired
	WebSearchhistoryMapper tblSearchhistoryMapper;
	@Autowired
	WebProductMapper appProductMapper;

	@Override
	public List<TblSearchhistory> getMallSearchHistory() {
		// TODO Auto-generated method stub
		return tblSearchhistoryMapper.find();
	}

	@Override
	public void removeMallSearchHistory() {
		// TODO Auto-generated method stub
		tblSearchhistoryMapper.removeMallSearchHistory();
	}

	@Override
	public List<TblProduct> findProductListByShopSearch(TblSearchhistory tblSearchhistory,TblProduct tblProduct) {
		// TODO Auto-generated method stub
		//01 插入历史记录
		tblSearchhistoryMapper.insert(tblSearchhistory);
		//02:获取搜索产品列表
		List<TblProduct> tblProductList=appProductMapper.findProductListByShopSearch(tblProduct);
		return tblProductList;
	}


}
