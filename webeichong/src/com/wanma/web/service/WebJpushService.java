/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.web.service;

import java.util.List;

import com.wanma.model.TblJpush;
import com.wanma.model.TblProduct;

/**
 * @Description: 极光推送业务处理接口
 * @author songjf
 * @createTime：2015-3-15 下午05:12:46
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
public interface WebJpushService {

	/**
	 * @Title: getByuserInfo
	 * @Description: 根据用户id获取用户推送信息
	 * @param params
	 * @return
	 */
	public TblJpush getByuserInfo(int jpushUserInfo);

	/**
	 * @Title: update
	 * @Description: 更新用户推送信息
	 * @param params
	 * @return
	 */
	public int update(TblJpush tblJpush);

	/**
	 * @Title: insert
	 * @Description: 新增用户推送信息
	 * @param params
	 * @return
	 */
	public int insert(TblJpush tblJpush);

}
