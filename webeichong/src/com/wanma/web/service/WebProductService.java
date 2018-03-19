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
import com.wanma.web.support.common.Response;

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
public interface WebProductService {

	/**
	 * @Title: findProducts
	 * @Description: 获取产品列表
	 * @param params
	 * @return
	 */
	public Response<?> findProducts(Map<String, Object> params);

	/**
	 * @Title: findProductDetail
	 * @Description: 获取产品详情
	 * @param params
	 * @return
	 */
	public Response<?> findProductDetail(Map<String, Object> params);

}
