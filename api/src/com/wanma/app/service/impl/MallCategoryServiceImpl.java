/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.app.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.app.dao.AppProductMapper;
import com.wanma.app.dao.TblProductcategoryMapper;
import com.wanma.app.service.MallCategoryService;
import com.wanma.common.JudgeNullUtils;
import com.wanma.model.TblProduct;
import com.wanma.model.TblProductcategory;

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
public class MallCategoryServiceImpl implements MallCategoryService {

	@Autowired
	TblProductcategoryMapper tblProductcategoryMapper;
	@Autowired
	AppProductMapper appProductMapper;
	
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
	

}
