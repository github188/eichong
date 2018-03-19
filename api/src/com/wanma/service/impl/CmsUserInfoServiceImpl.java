package com.wanma.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bluemobi.product.common.CommonConsts;
import com.bluemobi.product.common.dao.MultipartFileDao;
import com.bluemobi.product.utils.MultipartFileUtil;
import com.bluemobi.product.utils.ObjectUtil;
import com.wanma.app.dao.AppCarinfoMapper;
import com.wanma.app.dao.AppUserinfoMapper;
import com.wanma.app.dao.CmsCarCompanyMapper;
import com.wanma.common.WanmaConstants;
import com.wanma.dao.CmsUserInfoMapper;
import com.wanma.model.TblConfigcontent;
import com.wanma.model.TblUserinfo;
import com.wanma.service.CmsUserInfoService;

@Service
public class CmsUserInfoServiceImpl implements CmsUserInfoService{

	/** 用户表操作用DAO */
	@Autowired
	private CmsUserInfoMapper tblUserInfoDao;
	@Autowired
	private CmsCarCompanyMapper carCompanyDao;
	@Autowired
	private AppCarinfoMapper carinfoDao;
	@Autowired
	private AppUserinfoMapper userInfoDao;
	
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
	public TblUserinfo findUser(String pkUserinfo) {

		// 用户信息
		TblUserinfo user;

		// 取得用户信息
		user = tblUserInfoDao.findUser(pkUserinfo);

		// 返回用户一览
		return user;
	}
	
	/**
	 * 查询电动车品牌
	 * 
	 */
	@Override
	public List<TblConfigcontent> searchBrandList(TblConfigcontent tblConfigcontent) {
		// TODO Auto-generated method stub
		return tblUserInfoDao.searchBrandList(tblConfigcontent);
	}
	
	/**
	 * 判断手机号是否存在
	 * 
	 */
	@Override
	public int userIsPhone(String usinPhone) {
		// TODO Auto-generated method stub
		return tblUserInfoDao.userIsPhone(usinPhone);
	}

	/**
	 * 判断邮箱是否存在
	 * 
	 */
	@Override
	public int userIsEmail(String usinEmail) {
		// TODO Auto-generated method stub
		return tblUserInfoDao.userIsEmail(usinEmail);
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
	public void addUser(TblUserinfo tblUserinfo) {
	
		// 调用DAO执行用户添加处理
		tblUserInfoDao.saveUser(tblUserinfo);
		
		if (tblUserinfo.getTitleMultiFile() != null
				&& tblUserinfo.getTitleMultiFile().getSize() > 0) {

			// 处理用户图片
			MultipartFileUtil.saveAllMulti(tblUserinfo,
					CommonConsts.MULTI_TYPE_USER, String.valueOf(tblUserinfo.getPkUserinfo()));
		}
	}

	/**
	 * 编辑用户
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param userModel
	 *            用户信息
	 * @return 无
	 * @throws 无
	 */
	public void modifyUser(TblUserinfo tblUserinfo) {
		//安卓接口输入车牌名，后期接口需升级跟IOS一致
		/*if(StringUtils.isNotBlank(tblUserinfo.getUsinAcura())){
			Integer usinCarcompanyId=carCompanyDao.
					getEntityByCarinfoName(tblUserinfo.getUsinAcura()).getPkCarCompany();
			Integer usinCarinfoId=carinfoDao.getEntityByName(tblUserinfo.getUsinAcura()).getPkCarinfo();
			tblUserinfo.setUsinCarcompanyId(usinCarcompanyId);
			tblUserinfo.setUsinCarinfoId(usinCarinfoId);*/
		if(null != tblUserinfo.getUsinCarinfoId() && tblUserinfo.getUsinCarinfoId() > 0){
			Integer usinCarcompanyId=carCompanyDao.
					getEntityByCarinfoId(tblUserinfo.getUsinCarinfoId()).getPkCarCompany();
			tblUserinfo.setUsinCarcompanyId(usinCarcompanyId);
		}
		// 调用DAO执行用户更新处理
		tblUserInfoDao.modifyUser(tblUserinfo);
		if(!StringUtils.isEmpty(tblUserinfo.getaCode()))//StringUtils.isEmpty
		{
		
			userInfoDao.updateUserInfo(tblUserinfo);
		}
		

		if (ObjectUtil.isNotEmpty(tblUserinfo.getAllMultiFile())){
			MultipartFileDao multipartFileDao = new MultipartFileDao();
			List<String> allMultiFileName = multipartFileDao.getAllMultiFileName(CommonConsts.MULTI_TYPE_USER, String.valueOf(tblUserinfo.getPkUserinfo()));
	    	MultipartFileUtil.delelteMulti(allMultiFileName, CommonConsts.MULTI_TYPE_USER, String.valueOf(tblUserinfo.getPkUserinfo()));
			// 处理用户图片
			MultipartFileUtil.saveAllMulti(tblUserinfo,
					CommonConsts.MULTI_TYPE_USER, String.valueOf(tblUserinfo.getPkUserinfo()));

		}
	}
	
	/**
	 * 升级为个体商家
	 * 
	 */
	@Override
	public void upgradeUser(TblUserinfo tblUserinfo) {
		
		tblUserInfoDao.upgradeUser(tblUserinfo);
		//处理身份证附件
		if (ObjectUtil.isNotEmpty(tblUserinfo.getAllMultiFile())) {

			// 处理个体商家身份证
			MultipartFileUtil.saveAllMulti(tblUserinfo,
				WanmaConstants.MULTI_TYPE_ID_CARD_IMAGE, String.valueOf(tblUserinfo.getPkUserinfo()));
		}
		
		
	}

	
	/**
	 * 删除用户
	 * 
	 */
	@Override
	public void deleteUser(TblUserinfo tblUserinfo) {
		// TODO Auto-generated method stub
		tblUserInfoDao.deleteUser(tblUserinfo);
	}

	/**
	 * 批量删除
	 * 
	 */
	@Override
	public void removeAll(String pkUserinfos) {
		// TODO Auto-generated method stub
		String[] approvalArray = pkUserinfos.split(",");
		TblUserinfo userinfo = new TblUserinfo();
		
		for (int i = 0; i < approvalArray.length; i++) {
			userinfo.setPkUserinfo(Integer.valueOf(approvalArray[i]));
			tblUserInfoDao.deleteUser(userinfo);
		}
		
	}
	
	/**
	 * 取得用户一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @return List<UserModel> 用户一览
	 * @throws 无
	 */
	public List<TblUserinfo> getUserList() {
		// 用户一览
		List<TblUserinfo> listUser;

		// 取得用户一览
		listUser = tblUserInfoDao.getUserList();

		// 返回用户一览
		return listUser;
	}

	/**
	 * 查询用户个数
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param userModel
	 *            用户信息
	 * @return List<UserModel> 用户一览
	 * @throws 无
	 */
	public long searchUserCount(TblUserinfo tblUserinfo) {
		// 用户个数
		long dataCount;

		// 取得用户个数
		dataCount = tblUserInfoDao.searchUserCount(tblUserinfo);

		// 返回用户个数
		return dataCount;

	}

	/**
	 * 查询用户一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param userModel
	 * @return List<UserModel> 用户一览
	 * @throws 无
	 */
	public List<TblUserinfo> searchUserList(TblUserinfo tblUserinfo) {
		// 用户一览
		List<TblUserinfo> listUser;

		// 取得用户一览
		listUser = tblUserInfoDao.searchUserList(tblUserinfo);

		// 返回用户一览
		return listUser;

	}

	/**
	 * 审批
	 * 
	 */
	@Override
	public void approvalUser(TblUserinfo record) {
		// TODO Auto-generated method stub
		tblUserInfoDao.approvalUser(record);
	}
	
	/**
	 * 批量审批
	 * 
	 */
	@Override
	public void approvalAll(String pkUserinfos) {
		// TODO Auto-generated method stub
		String[] approvalArray = pkUserinfos.split(",");
		TblUserinfo userinfo = new TblUserinfo();
		
		for (int i = 0; i < approvalArray.length; i++) {
			userinfo.setPkUserinfo(Integer.valueOf(approvalArray[i]));
			userinfo.setUsinGroupid(2);
			userinfo.setUsinUserstatus(5);
			tblUserInfoDao.approvalUser(userinfo);
		}
		
	}
	
	/**
	 * 冻结
	 * 
	 */
	@Override
	public void frostUser(TblUserinfo frost) {
		// TODO Auto-generated method stub
		tblUserInfoDao.frostUser(frost);
	}
	
	/**
	 * 批量冻结
	 * 
	 */
	@Override
	public void frostAll(String pkUserinfos) {
		// TODO Auto-generated method stub
		String[] approvalArray = pkUserinfos.split(",");
		TblUserinfo userinfo = new TblUserinfo();
		
		for (int i = 0; i < approvalArray.length; i++) {
			userinfo.setPkUserinfo(Integer.valueOf(approvalArray[i]));
			userinfo.setUsinUserstatus(2);
			tblUserInfoDao.frostUser(userinfo);
		}
		
	}
	
	/**
	 * 根据个体商家ID取得个体商家信息
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param userId
	 *            个体商家ID
	 * @return UserModel 登录个体商家信息
	 * @throws 无
	 */
	public TblUserinfo findUnit(String pkUserinfo) {

		// 个体商家信息
		TblUserinfo user;

		// 取得个体商家信息
		user = tblUserInfoDao.findUnit(pkUserinfo);

		// 返回个体商家一览
		return user;
	}

	/**
	 * 添加个体商家
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param UserModel
	 *            个体商家信息
	 * @return 无
	 * @throws 无
	 */
	public void addUnit(TblUserinfo tblUserinfo,List<TblUserinfo> unitList) {
				
		// 调用DAO执行个体商家添加处理
		tblUserInfoDao.saveUnit(tblUserinfo);
		
		for(TblUserinfo userInfo:unitList){
			if(userInfo.getPicType().equals(CommonConsts.MULTI_TYPE_USER)){
				//个体商家头像
				MultipartFileUtil.saveAllMulti(userInfo,
						CommonConsts.MULTI_TYPE_USER, String.valueOf(tblUserinfo.getPkUserinfo()));	
			}
			else if(userInfo.getPicType().equals(WanmaConstants.MULTI_TYPE_ID_CARD_IMAGE)){
				//个体商家身份证
				MultipartFileUtil.saveAllMulti(userInfo,
						WanmaConstants.MULTI_TYPE_ID_CARD_IMAGE, String.valueOf(tblUserinfo.getPkUserinfo()));
			}
		}
	}
	

	/**
	 * 编辑个体商家
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param userModel
	 *            个体商家信息
	 * @return 无
	 * @throws 无
	 */
	public void modifyUnit(TblUserinfo tblUserinfo,List<TblUserinfo> unitList) {
		
		// 调用DAO执行个体商家更新处理
		tblUserInfoDao.modifyUnit(tblUserinfo);
				
		for(TblUserinfo userInfo:unitList){
			if(userInfo.getPicType().equals(CommonConsts.MULTI_TYPE_USER)){
				//个体商家头像
				MultipartFileDao multipartFileDao = new MultipartFileDao();
				List<String> allMultiFileName = multipartFileDao.getAllMultiFileName(CommonConsts.MULTI_TYPE_USER, String.valueOf(tblUserinfo.getPkUserinfo()));
		    	MultipartFileUtil.delelteMulti(allMultiFileName, CommonConsts.MULTI_TYPE_USER, String.valueOf(tblUserinfo.getPkUserinfo()));
				
				// 处理用户图片
				MultipartFileUtil.saveAllMulti(tblUserinfo,
						CommonConsts.MULTI_TYPE_USER, String.valueOf(tblUserinfo.getPkUserinfo()));
			}
			else if(userInfo.getPicType().equals(WanmaConstants.MULTI_TYPE_ID_CARD_IMAGE)){
				//个体商家身份证
				MultipartFileDao multipartFileDao = new MultipartFileDao();
				List<String> allMultiFileName = multipartFileDao.getAllMultiFileName(WanmaConstants.MULTI_TYPE_ID_CARD_IMAGE, String.valueOf(tblUserinfo.getPkUserinfo()));
		    	MultipartFileUtil.delelteMulti(allMultiFileName, WanmaConstants.MULTI_TYPE_ID_CARD_IMAGE, String.valueOf(tblUserinfo.getPkUserinfo()));
				
				MultipartFileUtil.saveAllMulti(userInfo,
					WanmaConstants.MULTI_TYPE_ID_CARD_IMAGE, String.valueOf(tblUserinfo.getPkUserinfo()));
			}
		}
	}
	
	
	



	/**
	 * 取得个体商家一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @return List<UserModel> 个体商家一览
	 * @throws 无
	 */
	public List<TblUserinfo> getUnitList() {
		// 个体商家一览
		List<TblUserinfo> listUser;

		// 取得个体商家一览
		listUser = tblUserInfoDao.getUnitList();

		// 返回个体商家一览
		return listUser;
	}

	/**
	 * 查询个体商家个数
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param userModel
	 *            个体商家信息
	 * @return List<UserModel> 个体商家一览
	 * @throws 无
	 */
	public long searchUnitCount(TblUserinfo tblUserinfo) {
		// 个体商家个数
		long dataCount;

		// 取得个体商家个数
		dataCount = tblUserInfoDao.searchUnitCount(tblUserinfo);

		// 返回个体商家个数
		return dataCount;

	}

	/**
	 * 查询个体商家一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param userModel
	 * @return List<UserModel> 个体商家一览
	 * @throws 无
	 */
	public List<TblUserinfo> searchUnitList(TblUserinfo tblUserinfo) {
		// 个体商家一览
		List<TblUserinfo> listUser;

		// 取得个体商家一览
		listUser = tblUserInfoDao.searchUnitList(tblUserinfo);

		// 返回个体商家一览
		return listUser;

	}

	
}
