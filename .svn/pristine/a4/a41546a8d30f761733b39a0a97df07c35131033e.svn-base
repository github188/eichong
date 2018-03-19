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

import com.wanma.common.JudgeNullUtils;
import com.wanma.model.TblProduct;
import com.wanma.model.TblProductcategory;
import com.wanma.web.dao.WebProductMapper;
import com.wanma.web.dao.WebProductcategoryMapper;
import com.wanma.web.service.WebMallCategoryService;

/***
 *
 *   商品城，类目
  * @Description:
  * @author bruce cheng(http://blog.csdn.net/brucehome)
  * @createTime：2015-3-13 下午04:51:34 
  * @updator： 
  * @updateTime：   
  * @version：V1.0
 */
@Service
public class WebMallCategoryServiceImpl implements WebMallCategoryService {

	@Autowired
	WebProductcategoryMapper tblProductcategoryMapper;
	@Autowired
	WebProductMapper appProductMapper;
	
	@Override
	public List<TblProductcategory> getHotRecommend(
			TblProductcategory tblProductcategory) {
		// TODO Auto-generated method stub
		List<TblProductcategory> productcategoryList=tblProductcategoryMapper.getHotRecommend(tblProductcategory);
		for (TblProductcategory tblProductcategory2 : productcategoryList) {
			tblProductcategory2.setPrcaImage(JudgeNullUtils.nvlStr(tblProductcategory2.getPrcaImage()));	
			tblProductcategory2.setPrcaName(JudgeNullUtils.nvlStr(tblProductcategory2.getPrcaName()));
		}
		return productcategoryList;
	}
	@Override
	public List<TblProduct> getHotRecommendDetail(TblProduct tblProduct) {
		// TODO Auto-generated method stub
		return appProductMapper.getHotRecommendDetail(tblProduct);
	}
	@Override
	public int getHotRecommendDetailCount(TblProduct tblProduct) {
		// TODO Auto-generated method stub
		return appProductMapper.getHotRecommendDetailCount(tblProduct);
	}


}
