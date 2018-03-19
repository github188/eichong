package com.wanma.web.dao;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblUserinfo;
import com.wanma.page.Page;

/**
 * 数据访问接口
 * 
 */
public interface WebUserinfoMapper {
	public final static String PREFIX = WebUserinfoMapper.class.getName();

	/**
	 * @Title: getUserById
	 * @Description: 根据id获取用户信息
	 * @param pkUserInfo
	 * @return
	 */
	public TblUserinfo get(java.lang.String pkUserinfo);

	public <K, V> Map<K, V> findOne(java.lang.Integer pkUserinfo);

	public <T, K, V> List<T> find(Map<K, V> params);

	public int insert(TblUserinfo tblUserinfo);

	public int update(TblUserinfo tblUserinfo);
	public int upgradeoUser(TblUserinfo tblUserinfo);

	public int delete(java.lang.Integer pkUserinfo);

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);

	/**
	 * @Title: getUserByMobile
	 * @Description: 根据手机号码取得用户ID
	 * @param mobileNumber
	 * @return pkUserInfo 用户id
	 */
	public String getUserByMobile(String usinPhone);

	/**
	 * @Title: getUserCountByPassword
	 * @Description: 取得用户 记录数
	 * @param params
	 * @param usInPassword
	 * @return
	 */
	public int getUserCountByPassword(Map<String, Object> params);

	/**
	 * @Title: getUserById
	 * @Description: 根据id获取用户信息
	 * @param pk_UserInfo
	 * @return
	 */
	public <T> T getUserById(String pkUserInfo);

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
	 *        新密码 用户id
	 * @return
	 */
	public <K, V> int resetPasswrod(Map<K, V> params);
	
	/**
	 * @Title: updateAccountbalance
	 * @Description: 更新用户余额
	 * @param params
	 * @return
	 */
	public <K, V> int updateAccountbalance(Map<K, V> params);

	/**
	 * @Title: updateAccountbalance
	 * @Description: 更新用户余额
	 * @param tblUserinfo
	 * @return
	 */
	public int userRecharge(TblUserinfo tblUserinfo);
	
	 /**
     * @param usinPhone
     * @return
     * @Title: getUserByPhone
     * @Description: 根据usinPhone获取用户信息
     */
	public TblUserinfo getUserByPhone(String usinPhone);
	/**
	 * @Title: getByPhone
	 * @Description: 根据手机号获取用户信息
	 * @param pkUserInfo
	 * @return
	 */
	public TblUserinfo getByPhone(java.lang.String pkUserinfo);
	/**
	 * 更新用户账户余额
	 * @param userMap
	 * 			userId 用户id blance 账户余额
	 */
	public void updateUserBlance(Map<String, Object> userMap);
	
	/**
	 * 根据用户id获取充电订单列表
	 * @param userId
	 * @return
	 */
	public List<Map<String, Object>> getChargeOrderListByUid(int userId);

	public int updateDeviceId(TblUserinfo userinfo);

}
