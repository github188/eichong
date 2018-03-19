package com.wanma.app.dao;

import java.util.Map;

import com.wanma.model.TblJpush;
import com.wanma.model.TblUser;

public interface AppGovBusiCheckMapper {
	/**
	 * 1检查用户是否存在
	 * @param tbluser
	 * @return
	 */
	public Map<String, Object> checkUser(TblUser tbluser);
	
	/**
	 * 2:根据用户id获取用户信息
	 * @param userId
	 * @return
	 */
	public Map<String, Object>get(int userId);
	
	/**
	 * 3更新用户登录的设备唯一标识
	 * @param deviceId
	 */
	public void updateUserDeviceId(Map<String, Object> params);
	
	/**
	 * @Title: getByuserInfo
	 * @Description: 5根据用户id获取用户推送信息
	 * @param params
	 * @return
	 */
	public TblJpush getByuserInfo(java.lang.Integer jpushUserInfo);
	
	/**
	 * @Title: userIsExist
	 * @Description: 6判断用户是否存在
	 * @param usinPhone
	 * @return
	 */
	public int userIsExist(String usinPhone);
	
	/**
	 * @Title: resetPasswrod
	 * @Description: 7重置密码
	 * @param params
	 *            新密码 用户id
	 * @return
	 */
	public <K, V> int resetPasswrod(Map<K, V> params);
	
	
	/**
	 * 8:检查用户是否存在
	 * @param usinPhone
	 * @return
	 */
	public int userPhone(String usinPhone);

}
