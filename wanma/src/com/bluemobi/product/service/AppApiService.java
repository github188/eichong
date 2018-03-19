/**
 * FileName:AppApiService.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 *//*
package com.bluemobi.product.service;

import java.util.List;

import com.bluemobi.product.model.AppApiModel;

*//**
 * App接口定义业务处理接口
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 *//*
public interface AppApiService {

	*//**
	 * 查询App接口数
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param appApiModel
	 *            查询用App接口对象
	 * @return long App接口数
	 * @throws 无
	 *//*
	public long searchAppApiCount(AppApiModel appApiModel);

	*//**
	 * 查询App接口一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param appApiModel
	 *            查询用App接口对象
	 * @return List<AppApiModel> App接口一览
	 * @throws 无
	 *//*
	public List<AppApiModel> searchAppApiList(AppApiModel appApiModel);

	*//**
	 * 取得App接口信息
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param pageId
	 *            画面ID
	 * @param appApiId
	 *            App接口ID
	 * @return 无
	 * @throws 无
	 *//*
	public AppApiModel findAppApi(String appApiId);

	*//**
	 * 取得App接口一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @return List<AppApi> App接口一览
	 * @throws 无
	 *//*
	public List<AppApiModel> getAppApiList();

	*//**
	 * 添加App接口
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param AppApiModel
	 *            App接口信息
	 * @return 无
	 * @throws 无
	 *//*
	public void addAppApi(AppApiModel appApiModel);

	*//**
	 * 编辑App接口
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param AppApiModel
	 *            App接口信息
	 * @return 无
	 * @throws 无
	 *//*
	public void modifyAppApi(AppApiModel appApiModel);

	*//**
	 * 删除App接口
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param appApiId
	 *            App接口ID
	 * @return 无
	 * @throws 无
	 *//*
	public void removeAppApi(String appApiId);

	*//**
	 * App接口唯一性检查
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param appApiId
	 *            App接口ID
	 * @return String App接口唯一性检查结果 "true":通过 "false":不通过
	 * @throws 无
	 *//*
	public String checkAppApiUnique(String appApiId);

	*//**
	 * App接口Url唯一性检查
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param url
	 *            App接口url
	 * @return String App接口url唯一性检查结果 "true":通过 "false":不通过
	 * @throws 无
	 *//*
	public String checkUrlUnique(String url);

}
*/