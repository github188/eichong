/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.app.service;

import java.util.List;

import com.wanma.model.TblUsercollect;

/**
 * 反馈信息业务处理接口
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public interface UsercollectService {

	/**
	 * 电站/电桩收藏
	 */
	public void userFavorites(TblUsercollect tblUsercollect);
	 
	/**
	 * 删除收藏列表
	 */
	public void removeFavoritesList(String userCollectId);

}
