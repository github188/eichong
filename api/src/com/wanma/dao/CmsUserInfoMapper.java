package com.wanma.dao;

import java.util.List;

import com.wanma.model.TblCarinfo;
import com.wanma.model.TblConfigcontent;
import com.wanma.model.TblUserinfo;

/**
 * FileName TblUserInfoDao.java
 * 
 * Version 1.0
 * 
 * Create by xiay
 * 
 * 用户表操作用DAO接口Mapper
 */
public interface CmsUserInfoMapper{
	/**
	 * 根据用户ID取得用户信息
	 * 
	 * @author xiay
	 * @return 无
	 */
	public TblUserinfo findUser(String pkUserinfo);
	/**
	 * @Title: getUserById
	 * @Description: 根据id获取用户信息
	 * @param pkUserInfo
	 * @return
	 */
	public TblUserinfo get(java.lang.String pkUserinfo);

	
	/**
	 * 添加用户
	 * 
	 * @author xiay
	 * @return 无
	 */
	public int saveUser(TblUserinfo tblUserinfo);
	
	/**
	 * @Title: userIsExist
	 * @Description: 判断手机号是否存在
	 * @param usinPhone
	 * @return
	 */
	public int userIsPhone(String usinPhone);
	
	/**
	 * @Title: userIsExist
	 * @Description: 判断邮箱是否存在
	 * @param usinEmail
	 * @return
	 */
	public int userIsEmail(String usinEmail);

	/**
	 * 编辑用户
	 * 
	 * @author xiay
	 * @return 无
	 */
	public void modifyUser(TblUserinfo tblUserinfo);
	
	/**
	 * 编辑用户时同时更新用户表中的省市县的编码
	 * 
	 * @param tblUserinfo
	 * @author lyh
	 * @return 无
	 */
	public void updateUser(TblUserinfo tblUserinfo);
	/**
	 * 升级为个体商家
	 * 
	 * @param tblUserinfo
	 * @author xiay
	 * @return 无
	 */
	public void upgradeUser(TblUserinfo tblUserinfo);

	/**
	 * 取得用户一览
	 *  
	 * @author xiay
	 * @return 无
	 */
	public List<TblUserinfo> getUserList();

	/**
	 * 查询用户个数
	 * 
	 * @author xiay
	 * @return 无
	 */
	public long searchUserCount(TblUserinfo tblUserinfo);

	/**
	 * 查询用户一览
	 * 
	 * @author xiay
	 * @return 无
	 */
	public List<TblUserinfo> searchUserList(TblUserinfo tblUserinfo);
	
	/**
	 * 删除用户
	 * 
	 * @author xiay
	 * @return 无
	 */
	public int deleteUser(TblUserinfo tblUserinfo);
	
	/**
	 * 审批
	 * 
	 * @author xiay
	 * @return 无
	 */
	public int approvalUser(TblUserinfo record);
	
	/**
	 * 冻结
	 * 
	 * @author xiay
	 * @return 无
	 */
	public int frostUser(TblUserinfo frost);
	
	/**
	 * 查询电动车品牌
	 * 
	 * @author xiay
	 * @return 无
	 */
	public List<TblConfigcontent> searchBrandList(TblConfigcontent tblConfigcontent);
	
	/**
	 * 根据个体商家ID取得个体商家信息
	 * 
	 * @author xiay
	 * @return 无
	 */
	public TblUserinfo findUnit(String pkUserinfo);

	/**
	 * 添加个体商家
	 * 
	 * @author xiay
	 * @return 无
	 */
	public int saveUnit(TblUserinfo tblUserinfo);

	/**
	 * 编辑个体商家
	 * 
	 * @author xiay
	 * @return 无
	 */
	public void modifyUnit(TblUserinfo tblUserinfo);

	/**
	 * 取得个体商家一览
	 * 
	 * @author xiay
	 * @return 无
	 */
	public List<TblUserinfo> getUnitList();

	/**
	 * 查询个体商家个数
	 * 
	 * @author xiay
	 * @return 无
	 */
	public long searchUnitCount(TblUserinfo tblUserinfo);

	/**
	 * 查询个体商家一览
	 * 
	 * @author xiay
	 * @return 无
	 */
	public List<TblUserinfo> searchUnitList(TblUserinfo tblUserinfo);
	
	
}
