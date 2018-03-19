package com.wanma.service;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblCarinfo;
import com.wanma.model.TblConfigcontent;
import com.wanma.model.TblUserinfo;

/**
 * FileName TblUserInfoService.java
 * 
 * Version 1.0
 * 
 * Create by xiay
 * 
 * 用户业务处理接口
 */
public interface CmsUserInfoService {

	/**
	 * 根据用户ID取得用户信息
	 * 
	 * @author xaiy
	 * @since Version 1.0
	 * @param userId
	 *            用户ID
	 * @return 无
	 * @throws 无
	 */
	public TblUserinfo findUser(String pkUserinfo);
	
	/**
	 * 添加用户
	 * 
	 * @author xaiy
	 * @since Version 1.0
	 * @param UserModel
	 *            用户信息
	 * @return 用户ID
	 * @throws 无
	 */
	public void addUser(TblUserinfo tblUserinfo);
    
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
	 * @author xaiy
	 * @since Version 1.0
	 * @param UserModel
	 *            用户信息
	 * @return String 处理结果标识
	 * @throws 无
	 */
	public void modifyUser(TblUserinfo tblUserinfo);
	
	/**
	 * 升级为个体商家用户
	 * 
	 * @param tblUserinfo
	 */
	public void upgradeUser(TblUserinfo tblUserinfo);
	

	/**
	 * 取得用户一览
	 * 
	 * @author xaiy
	 * @since Version 1.0
	 * @return List<UserModel> 用户一览
	 * @throws 无
	 */
	public List<TblUserinfo> getUserList();

	/**
	 * 查询用户个数
	 * 
	 * @author xaiy
	 * @since Version 1.0
	 * @param userModel
	 *            用户信息
	 * @return List<UserModel> 用户一览
	 * @throws 无
	 */
	public long searchUserCount(TblUserinfo tblUserinfo);
	

	/**
	 * 查询用户一览
	 * 
	 * @author xaiy
	 * @since Version 1.0
	 * @param userModel
	 *            用户信息
	 * @return List<UserModel> 用户一览
	 * @throws 无
	 */
	public List<TblUserinfo> searchUserList(TblUserinfo tblUserinfo);
	
	/**
	 * 查询电动车品牌
	 * 
	 * @param TblConfigcontent
	 * @return
	 */
	public List<TblConfigcontent> searchBrandList(TblConfigcontent tblConfigcontent);
	
	/**
	 * 删除
	 * 
	 */
	public void deleteUser(TblUserinfo tblUserinfo);
	
	/**
	 * 批量删除
	 * 
	 */
	public void removeAll(String pkUserinfos);
	
	/**
	 * 审批
	 * 
	 * @param tblUserinfo
	 */
	public void approvalUser(TblUserinfo record);
	
	/**
	 * 批量审批
	 * 
	 */
	public void approvalAll(String pkUserinfos);
	
	/**
	 * 冻结
	 * 
	 * @param tblUserinfo
	 */
	public void frostUser(TblUserinfo frost);
	
	/**
	 * 批量冻结
	 * 
	 */
	public void frostAll(String pkUserinfos);
	
	
	/**
	 * 根据个体商家ID取得个体商家信息
	 * 
	 * @author xaiy
	 * @since Version 1.0
	 * @param userId
	 *            个体商家ID
	 * @return 无
	 * @throws 无
	 */
	public TblUserinfo findUnit(String pkUserinfo);

	/**
	 * 添加个体商家
	 * 
	 * @author xaiy
	 * @since Version 1.0
	 * @param UserModel
	 *            个体商家信息
	 * @return 个体商家ID
	 * @throws 无
	 */
	public void addUnit(TblUserinfo tblUserinfo,List<TblUserinfo> unitList);

	/**
	 * 编辑个体商家
	 * 
	 * @author xaiy
	 * @since Version 1.0
	 * @param UserModel
	 *            个体商家信息
	 * @return String 处理结果标识
	 * @throws 无
	 */
	public void modifyUnit(TblUserinfo tblUserinfo,List<TblUserinfo> unitList);

	/**
	 * 取得个体商家一览
	 * 
	 * @author xaiy
	 * @since Version 1.0
	 * @return List<UserModel> 个体商家一览
	 * @throws 无
	 */
	public List<TblUserinfo> getUnitList();

	/**
	 * 查询个体商家个数
	 * 
	 * @author xaiy
	 * @since Version 1.0
	 * @param userModel
	 *            个体商家信息
	 * @return List<UserModel> 个体商家一览
	 * @throws 无
	 */
	public long searchUnitCount(TblUserinfo tblUserinfo);

	/**
	 * 查询个体商家一览
	 * 
	 * @author xaiy
	 * @since Version 1.0
	 * @param userModel
	 *            个体商家信息
	 * @return List<UserModel> 个体商家一览
	 * @throws 无
	 */
	public List<TblUserinfo> searchUnitList(TblUserinfo tblUserinfo);

}
