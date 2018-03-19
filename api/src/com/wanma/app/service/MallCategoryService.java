/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.app.service;

import java.util.List;

import com.wanma.model.TblProduct;
import com.wanma.model.TblProductcategory;

/**
 * 反馈信息业务处理接口
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public interface MallCategoryService {

	/**
	 * 获取热门推荐
	 */
	public List<TblProductcategory> getHotRecommend(TblProductcategory tblProductcategory);
	/**
	 * 获取热门推荐商品详情
	 */
	public List<TblProduct> getHotRecommendDetail(TblProduct tblProduct);

}
