/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.web.service;

import java.util.List;

import com.wanma.model.TblUsercollect;
import com.wanma.web.support.common.Response;

/**
 * 反馈信息业务处理接口
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public interface WebUsercollectService {

	/**
	 * 电站/电桩收藏
	 */
	public Response<?> userFavorites(TblUsercollect tblUsercollect);
	 
	/**
	 * 删除收藏列表
	 */
	public void removeFavoritesList(String userCollectId);
	
	
	/**
	 * 用户收藏
	 */
	public List<TblUsercollect> getUserCollect(TblUsercollect tblUsercollect);

}
