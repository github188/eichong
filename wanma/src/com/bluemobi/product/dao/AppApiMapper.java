/**
 * FileName:AppApiMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 *//*
package com.bluemobi.product.dao;

import java.util.List;

import com.bluemobi.product.model.AppApiModel;

*//**
 * App接口表操作用DAO接口Mapper
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 *//*
public interface AppApiMapper {

	*//**
	 * 根据用户App接口ID取得App接口数
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param appApiId
	 *            App接口ID
	 * @return int App接口数
	 * @throws 无
	 *//*
	public int getAppApiCountById(String appApiId);

	*//**
	 * 根据用户App接口url取得App接口数
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param url
	 *            App接口url
	 * @return int App接口数
	 * @throws 无
	 *//*
	public int getUrlCountById(String url);

	*//**
	 * 取得App接口信息
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param appApiId
	 *            App接口ID
	 * @return AppApiModel App接口信息
	 * @throws 无
	 *//*
	public AppApiModel findAppApi(String appApiId);

	*//**
	 * 取得App接口一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @return List<AppApiModel> App接口一览
	 * @throws 无
	 *//*
	public List<AppApiModel> getAppApiList();

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
	 * 添加App接口
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param AppApiModel
	 *            App接口信息
	 * @return AppApi App接口信息
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
	 * @return String 处理结果标识
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

}
*/