package com.wanma.app.service;

import java.util.Map;

import com.wanma.model.TblJpush;
import com.wanma.model.TblUser;
import com.wanma.model.TblUserinfo;

public interface AppGovBusiCheckUserService {
	
	/**
	 * 1:检查用户是否存在
	 * @param tbluser
	 * @return
	 */
	public Map<String, Object> checkUser(TblUser tbluser);
	
	/**
	 * @Title: getUserById
	 * @Description: 根据id获取用户信息
	 * @param pkUserInfo
	 * @return
	 */
	public Map<String, Object> getUserById(int userId);
	
	/**
	 * 更新设备唯一标识
	 * @param params
	 */
	public void updateUserDeviceId(Map<String, Object> params);
	
	/**
	 * @Title: getByuserInfo
	 * @Description: 根据用户id获取用户推送信息
	 * @param params
	 * @return
	 */
	public TblJpush getByuserInfo(java.lang.Integer jpushUserInfo);
	
	/**
	 * @Title: userIsExist
	 * @Description: 判断用户是否存在
	 * @param usinPhone
	 * @return
	 */
	public int userIsExist(String usinPhone);
	
	
	/**
	 * @Title: resetPasswrod
	 * @Description: 重置密码
	 * @param params
	 *            新密码 手机号
	 * @return
	 */
	public int resetPasswrod(Map<String, Object> params);
	
	
	/**
	 * 8:检查用户是否存在
	 * @param usinPhone
	 * @return
	 */
	public int userPhone(String usinPhone);

}
