package com.pub.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.common.WanmaConstants;
import com.base.util.MD5Util;
import com.base.util.MultiFileUtil;
import com.pub.dao.TblUserMapper;
import com.pub.model.TblUser;
import com.pub.service.TblUserService;

/**
 * FileName UserServiceImpl.java
 * 
 * Version 1.0
 * 
 * Create by yangwr 2014/6/9
 * 
 * 用户业务处理类
 */
@Service
public class TblUserServiceImpl implements TblUserService {

	/** 用户表操作用DAO */
	@Autowired
	private TblUserMapper tblUserMapper;

	/**
	 * 获取登录用户信息
	 * 
	 * @param user
	 * @return
	 */
	@Override
	public TblUser findLoginUser(TblUser user) throws Exception {
		try {
			user = tblUserMapper.getByAccount(user);
			if (user == null)
				return user;
			Integer userLevel = user.getUserLevel();
			if (userLevel <= WanmaConstants.USER_LEVEL_ADMIN) {// 用户level小于3时，直接返回原对象
				return user;
			}
		} catch (Exception e) {
			throw e;
		}
		return findUser(user);
	}

	/**
	 * 根据用户ID取得用户信息
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param userId
	 *            用户ID
	 * @return UserModel 登录用户信息
	 * @throws 无
	 */
	@Override
	public TblUser findUser(TblUser user) throws Exception {
		try {
			Integer userLevel = user.getUserLevel();
			if (userLevel == null)
				userLevel = 0;
			switch (userLevel) {
			case 1:
				user = tblUserMapper.findAdminUser(user.getUserId());
				break;
			case 3:
				user = tblUserMapper.findBusinessUser(user.getUserId());
				break;
			case 5:
				user = tblUserMapper.findNomalUser(user.getUserId());
				break;
			case 6:
				user = tblUserMapper.findNomalUser(user.getUserId());
				break;
			default:
				user = tblUserMapper.findUser(user.getUserId());
				break;
			}
		} catch (Exception e) {
			throw e;
		}
		// 返回用户一览
		return user;
	}

	/**
	 * 添加用户
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param UserModel
	 *            用户信息
	 * @return 无
	 * @throws 无
	 */
	public void insertUser(TblUser user) throws Exception {
		try {
			// 调用DAO执行用户添加处理
			tblUserMapper.insert(user);
			Integer userLevel = user.getUserLevel();
			if (userLevel == WanmaConstants.USER_LEVEL_ADMIN)
				tblUserMapper.insertAdminUser(user);
			if (userLevel == WanmaConstants.USER_LEVEL_NORMAL)
				tblUserMapper.insertNormalUser(user);
			if (userLevel == WanmaConstants.USER_LEVEL_BUSINESS_NORMAL)
				tblUserMapper.insertNormalUser(user);
			if (userLevel == WanmaConstants.USER_LEVEL_BUSINESS)
				tblUserMapper.insertBusinessUser(user);
		} catch (Exception e) {
			throw e;
		}
		// 上传用户附件
		uploadPic(user);
	}

	/**
	 * 编辑用户
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param UserModel
	 *            用户信息
	 * @return 无
	 * @throws 无
	 */
	public void updateUser(TblUser user) throws Exception {
		try {
			tblUserMapper.update(user);
			Integer userLevel = user.getUserLevel();
			if (userLevel == null)
				return;
			if (userLevel == WanmaConstants.USER_LEVEL_ADMIN)
				tblUserMapper.updateAdminUser(user);
			if (userLevel == WanmaConstants.USER_LEVEL_NORMAL)
				tblUserMapper.updateNormalUser(user);
			if (userLevel == WanmaConstants.USER_LEVEL_BUSINESS_NORMAL)
				tblUserMapper.updateNormalUser(user);
			if (userLevel == WanmaConstants.USER_LEVEL_BUSINESS)
				tblUserMapper.updateBusinessUser(user);
		} catch (Exception e) {
			throw e;
		}
		// 上传用户附件
		uploadPicUpdate(user);
	}

	/**
	 * 取得用户一览
	 * 
	 * @author
	 * @return List<TblUser> 用户一览
	 * @throws 无
	 */
	public List<TblUser> getUserList(TblUser user) throws Exception {
		// 用户一览
		List<TblUser> listUser = null;
		try {
			Integer userLevel = user.getUserLevel();
			if (userLevel == WanmaConstants.USER_LEVEL_NORMAL)
				listUser = tblUserMapper.getNomalUserList(user);
			if (userLevel == WanmaConstants.USER_LEVEL_BUSINESS_NORMAL)
				listUser = tblUserMapper.getNomalUserList(user);
			if (userLevel == WanmaConstants.USER_LEVEL_BUSINESS)
				listUser = tblUserMapper.getBusinessUserList(user);
			if (userLevel == WanmaConstants.USER_LEVEL_ADMIN)
				listUser = tblUserMapper.getAdminUserList(user);
			if (userLevel == WanmaConstants.USER_LEVEL_SUPER)
				listUser = tblUserMapper.getAdminUserList(user);
		} catch (Exception e) {
			throw e;
		}
		// 返回用户一览
		return listUser;
	}

	/**
	 * 查询用户个数
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param UserModel
	 *            用户信息
	 * @return List<UserModel> 用户一览
	 * @throws 无
	 */
	@Override
	public long getUserCount(TblUser user) throws Exception {
		// 用户个数
		long dataCount = 0;
		try {
			// 取得用户个数
			Integer userLevel = user.getUserLevel();
			if (userLevel == WanmaConstants.USER_LEVEL_NORMAL)
				dataCount = tblUserMapper.getNomalUserCount(user);
			if (userLevel == WanmaConstants.USER_LEVEL_BUSINESS_NORMAL)
				dataCount = tblUserMapper.getNomalUserCount(user);
			if (userLevel == WanmaConstants.USER_LEVEL_BUSINESS)
				dataCount = tblUserMapper.getBusinessUserCount(user);
			if (userLevel == WanmaConstants.USER_LEVEL_ADMIN)
				dataCount = tblUserMapper.getAdminUserCount(user);
			if (userLevel == WanmaConstants.USER_LEVEL_SUPER)
				dataCount = tblUserMapper.getAdminUserCount(user);
		} catch (Exception e) {
			throw e;
		}
		// 返回用户个数
		return dataCount;

	}

	/**
	 * 删除用户
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param user
	 *            用户对象
	 * @return 无
	 * @throws 无
	 */
	@Override
	public void deleteUser(TblUser user) throws Exception {
		try {
			// 删除用户图片
			deletePic(user);
			// 调用DAO执行用户更新处理
			tblUserMapper.updateStatus(user);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 删除用户
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param user
	 *            用户对象
	 * @return 无
	 * @throws 无
	 */
	@Override
	public void frozenUser(TblUser user) {
		// 调用DAO执行用户更新处理
		tblUserMapper.updateStatus(user);
	}

	@Override
	public void upgradeUser(TblUser tblUser) {
		tblUserMapper.updateNormalUser(tblUser);
	}

	@Override
	public boolean checkAccount(TblUser user) {
		return tblUserMapper.checkAccount(user) == 0;
	}

	@Override
	public boolean checkBusinessAccount(TblUser user) {
		return tblUserMapper.checkBusinessAccount(user) == 0;
	}

	/**
	 * 上传用户附件
	 * 
	 * @param user
	 */
	private void uploadPic(TblUser user) {
//		if (ObjectUtil.isFileNotEmpty(user.getAllMultiFile())) {
//			// 处理用户图片
//			MultipartFileUtil.saveAllMulti(user, WanmaConstants.USER_NORMALUSE,
//					user.getUserId() + "", true);
//		}
//		if (ObjectUtil.isFileNotEmpty(user.getIdCardPic())) {
//			// 处理个体商家身份证
//			user.setAllMultiFile(user.getIdCardPic());
//			MultipartFileUtil.saveAllMulti(user,
//					WanmaConstants.MULTI_TYPE_ID_CARD_IMAGE, user.getUserId()
//							.toString());
//		}
	}

	/**
	 * 上传用户附件
	 * 
	 * @param user
	 */
	private void uploadPicUpdate(TblUser user) {
		/*if (ObjectUtil.isFileNotEmpty(user.getAllMultiFile())) {
			// 处理用户图片
			MultipartFileUtil.updateAllMulti(user,
					WanmaConstants.USER_NORMALUSE, user.getUserId() + "");
		}
		if (ObjectUtil.isFileNotEmpty(user.getIdCardPic())) {
			// 处理个体商家身份证
			user.setAllMultiFile(user.getIdCardPic());
			MultipartFileUtil.updateAllMulti(user,
					WanmaConstants.MULTI_TYPE_ID_CARD_IMAGE, user.getUserId()
							.toString());
		}*/
	}

	/**
	 * 删除用户附件
	 * 
	 * @param user
	 */
	private void deletePic(TblUser user) {
		// 删除用户图片
		String userId = user.getUserId().toString();
		MultiFileUtil.delelteFile(WanmaConstants.USER_NORMALUSE, userId);
		/*MultipartFileDao multipartFileDao = new MultipartFileDao();
		List<String> allMultiFileName = multipartFileDao.getAllMultiFileName(
				WanmaConstants.USER_NORMALUSE, userId);
		MultipartFileUtil.delelteMulti(allMultiFileName,
				WanmaConstants.USER_NORMALUSE, userId);*/
	}

	/**
	 * 获取角色用户列表
	 */
	@Override
	public List<TblUser> getRoleUserList(String roleId) {
		// 返回角色用户列表
		return tblUserMapper.getRoleUserList(roleId);
	}

	/**
	 * 获取可选角色用户列表
	 */
	@Override
	public List<TblUser> getSelectRoleUserList(TblUser user) {
		// 返回角色用户列表
		return tblUserMapper.getSelectRoleUserList(user);
	}

	/**
	 * 获取可选角色用户数量
	 */
	@Override
	public long getSelectRoleUserCount(TblUser user) {
		// 返回角色用户列表
		return tblUserMapper.getSelectRoleUserCount(user);
	}

	@Override
	public boolean checkNowPassword(String userId, String nowPassword) {
		return MD5Util.Md5(nowPassword).equals(
				tblUserMapper.findUser(Long.valueOf(userId)).getUserPassword());
	}

	/*
	 * Title: unFrozenUser Description:
	 * 
	 * @param userinfo
	 * 
	 * @see
	 * com.bluemobi.product.service.TblUserService#unFrozenUser(com.wanma.model
	 * .TblUser)
	 */
	@Override
	public void unFrozenUser(TblUser user) {
		tblUserMapper.updateStatus(user);

	}

	/*
	 * Description:
	 * 
	 * @param user
	 * 
	 * @return
	 */
	@Override
	public int getUserConsumeCount(TblUser user) {
		return tblUserMapper.getUserConsumeCount(user);
	}

	/*
	 * Description:
	 * 
	 * @param long1
	 * 
	 * @return
	 */
	@Override
	public TblUser getPileAndChildCount(Long userId) {
		return tblUserMapper.getPileAndChildCount(userId);
	}

	@Override
	public boolean checkBusinessPhone(TblUser user) {
		return tblUserMapper.checkBusinessPhone(user) == 0;
	}

	@Override
	public boolean checkAdminPhone(TblUser user) {
		return tblUserMapper.checkAdminPhone(user) == 0;
	}

	@Override
	public String findPhoneByAccount(TblUser user) {
		return "";
	}

	@Override
	public int findUserCountByPhone(TblUser user) {
		return tblUserMapper.findUserCountByPhone(user);
	}

	/*
	 * Description:
	 * 
	 * @param user
	 * 
	 * @return
	 */
	@Override
	public List<TblUser> getAppUserList(TblUser user) {
		// 设置level为null，查app用户
		user.setUserLevel(null);
		user.setApplyCard("0");
		return tblUserMapper.getNomalUserList(user);
	}

	@Override
	public long getAppUserListCount(TblUser user) {
		// 设置level为null，查app用户
		user.setUserLevel(null);
		return tblUserMapper.getNomalUserCount(user);
	}

	/*
	 * Description: 查询可绑定卡用户列表
	 * 
	 * @param user
	 * 
	 * @return
	 */
	@Override
	public List<TblUser> getApplyCardUserList(TblUser user) {
		if ("1".equals(user.getIsQuicklyApply())) {
			return tblUserMapper.getQuicklyApplyCardUserList(user);
		} else {
			return tblUserMapper.getApplyCardUserList(user);
		}
	}

	/*
	 * Description: 查询可绑定卡用户数量
	 * 
	 * @param user
	 * 
	 * @return
	 */
	@Override
	public long getApplyCardUserListCount(TblUser user) {
		if ("1".equals(user.getIsQuicklyApply())) {
			return tblUserMapper.getQuicklyApplyCardUserListCount(user);
		} else {
			return tblUserMapper.getApplyCardUserListCount(user);
		}
	}

}
