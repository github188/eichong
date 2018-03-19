/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.app.service;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblProduct;

/**
 * 产品业务处理接口
 * 
 * @Description:
 * @author songjf
 * @createTime：2015-3-15 下午05:12:46
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
public interface AppProductService {
	
	/**
	 * @Title: getShareProduct
	 * @Description: 获取 分享商品信息
	 * @param params
	 * @return
	 */
	public TblProduct getShareProduct(TblProduct tblProduct);

	/**
	 * @Title: findProducts
	 * @Description: 获取产品列表
	 * @param params
	 * @return
	 */
	public List<TblProduct> findProducts(Map<String, Object> params);

	/**
	 * @Title: findProductDetail
	 * @Description: 获取产品详情
	 * @param params
	 * @return
	 */
	public Map<String, Object> findProductDetail(Map<String, Object> params);

	/**
	 * @Title: selectById
	 * @Description: 立即购买 获取商品信息
	 * @param params
	 * @return
	 */
	public Map<String, Object> selectById(Map<String, Object> params);

}
