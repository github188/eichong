/**
 * FileName:ActionPostService.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.bluemobi.product.service;

import java.util.List;

import com.bluemobi.product.model.ActionPostModel;

/**
 * 画面功能职位权限业务处理接口
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public interface ActionPostService {

	/**
	 * 取得画面功能职位权限信息
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param actionPostModel
	 *            画面功能职位权限对象
	 * @return 无
	 * @throws 无
	 */
	public ActionPostModel findActionPost(ActionPostModel actionPostModel);

	/**
	 * 取得画面功能职位权限一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @return List<ActionPost> 画面功能职位权限一览
	 * @throws 无
	 */
	public List<ActionPostModel> getActionPostList();

	/**
	 * 添加画面功能职位权限
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param ActionPostModel
	 *            画面功能职位权限信息
	 * @return ActionPost 画面功能职位权限信息
	 * @throws 无
	 */
	public ActionPostModel addActionPost(ActionPostModel actionPostModel);

	/**
	 * 编辑画面功能职位权限
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param ActionPostModel
	 *            画面功能职位权限信息
	 * @return String 处理结果标识
	 * @throws 无
	 */
	public String modifyActionPost(ActionPostModel actionPostModel);

	/**
	 * 删除画面功能职位权限
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param actionPostModel
	 *            画面功能职位权限对象
	 * @return 无
	 * @throws 无
	 */
	public void removeActionPost(ActionPostModel actionPostModel);

}
