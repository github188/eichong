package com.wanma.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluemobi.product.common.CommonConsts;
import com.bluemobi.product.utils.MD5Util;
import com.bluemobi.product.utils.StringUtil;
import com.wanma.common.WanmaConstants;
import com.wanma.dao.CmsPureBusinessMapper;
import com.wanma.dao.TblUserMapper;
import com.wanma.model.TblPureBusiness;
import com.wanma.model.TblUser;
import com.wanma.service.CmsCommitLogService;
import com.wanma.service.CmsPureBusinessService;

@Service
public class CmsPureBusinessServiceImpl implements CmsPureBusinessService{
	
	/**  */
	@Autowired
	private CmsPureBusinessMapper tblPureBusinessDao;
	@Autowired
	CmsCommitLogService commitLogService;
	@Autowired
	TblUserMapper userMapper;
	/**
	 * 根据纯商家ID取得纯商家信息
	 * 
	 * @author xiay
	 * @return 
	 * @throws 无
	 */
	public TblPureBusiness findBusiness(String businessId) {

		// 纯商家信息
		TblPureBusiness business;

		// 取得纯商家信息
		business = tblPureBusinessDao.findBusiness(businessId);

		// 返回纯商家一览
		return business;
	}
	/**
	 * 根据纯商家爱充网取得纯商家信息
	 * 
	 * @author xiay
	 * @return 
	 * @throws 无
	 */
	public TblPureBusiness findBusinessByLoveId(String loveLogin) {

		// 纯商家信息
		TblPureBusiness business;

		// 取得纯商家信息
		business = tblPureBusinessDao.findBusinessByLoveId(loveLogin);

		// 返回纯商家一览
		return business;
	}
	/**
	 * 添加纯商家
	 * 
	 * @author xiay
	 * @return 无
	 * @throws 无
	 */
	/* (non-Javadoc)
	 * @see com.wanma.service.CmsPureBusinessService#addBusiness(com.wanma.model.TblPureBusiness)
	 */
	/* (non-Javadoc)
	 * @see com.wanma.service.CmsPureBusinessService#addBusiness(com.wanma.model.TblPureBusiness)
	 */
	/* (non-Javadoc)
	 * @see com.wanma.service.CmsPureBusinessService#addBusiness(com.wanma.model.TblPureBusiness)
	 */
	/* (non-Javadoc)
	 * @see com.wanma.service.CmsPureBusinessService#addBusiness(com.wanma.model.TblPureBusiness)
	 */
	public void addBusiness(TblPureBusiness tblPureBusiness) {

		String pd=MD5Util.MD5(tblPureBusiness.getLovePassword());
		tblPureBusiness.setLovePassword(pd);
		// 调用DAO执行纯商家添加处理
		tblPureBusiness.setCreateDate(new Date());
		tblPureBusiness.setUpdatedate(new Date());
		tblPureBusiness.setCompanyName("");
		tblPureBusiness.setNickName("");
		tblPureBusiness.setCompanyAddress("");
		tblPureBusiness.setCompanyEmail("");
		tblPureBusiness.setPostCode("1");
		tblPureBusiness.setScopeBusiness("");
		tblPureBusiness.setAuthorizedName("");
		tblPureBusiness.setAuthorizedPhone("");
		tblPureBusiness.setAuthorizedCard("");
		tblPureBusiness.setBusinessLicence("");
		tblPureBusiness.setOrganizationCode("");
		tblPureBusiness.setTorontoHospital("");
		tblPureBusiness.setAuthorization("");
		tblPureBusiness.setMailingAddress("");
		tblPureBusiness.setPuBuPicType("1");
		tblPureBusinessDao.saveBusiness(tblPureBusiness);
		commitLogService.insert("添加纯商家用户，用户主键：["+tblPureBusiness.getBusinessId()+"]");

	}

	/**
	 * 编辑纯商家
	 * 
	 * @author xiay
	 * @return 无
	 * @throws 无
	 */
	public void modifyBusiness(TblPureBusiness tblPureBusiness ) {
		
		TblPureBusiness tblPureBusiness1 =tblPureBusinessDao.findBusiness(tblPureBusiness.getBusinessId());
		
		tblPureBusiness1.setLoveLogin(tblPureBusiness.getLoveLogin());
		String pd="";
		if(StringUtil.isNotEmpty(tblPureBusiness.getLovePassword())){
			pd=MD5Util.MD5(tblPureBusiness.getLovePassword());
		}
		tblPureBusiness1.setLovePassword(pd);
		tblPureBusiness1.setPureBusinessStatus(tblPureBusiness.getPureBusinessStatus());
		tblPureBusiness1.setCompanyId(tblPureBusiness.getCompanyId());
		
		tblPureBusiness1.setUpdatedate(new Date());
		tblPureBusiness1.setCompanyName("");
		tblPureBusiness1.setNickName("");
		tblPureBusiness1.setCompanyAddress("");
		tblPureBusiness1.setCompanyEmail("");
		tblPureBusiness1.setPostCode("1");
		tblPureBusiness1.setScopeBusiness("");
		tblPureBusiness1.setAuthorizedName("");
		tblPureBusiness1.setAuthorizedPhone("");
		tblPureBusiness1.setAuthorizedCard("");
		tblPureBusiness1.setBusinessLicence("");
		tblPureBusiness1.setOrganizationCode("");
		tblPureBusiness1.setTorontoHospital("");
		tblPureBusiness1.setAuthorization("");
		tblPureBusiness1.setMailingAddress("");
		tblPureBusiness1.setPuBuPicType("1");
		
		// 调用DAO执行纯商家更新处理
		tblPureBusinessDao.modifyBusiness(tblPureBusiness1);
		commitLogService.insert("修改纯商家用户，用户主键：["+tblPureBusiness.getBusinessId()+"]");

	}
	

	/**
	 * 取得纯商家一览
	 * 
	 * @author xiay
	 * @return 
	 * @throws 无
	 */
	public List<TblPureBusiness> getBusinessList(TblUser loginUser) {
		// 纯商家一览
		List<TblPureBusiness> listBusiness;
		/*if (loginUser.getUserLevel().toString().equals(WanmaConstants.USER_LEVER_merchant2)) {// 纯商家
		//	tblElectricpile.setElPiUserName(loginUser.getUserName());

		}*/
		// 取得纯商家一览
		listBusiness = tblPureBusinessDao.getBusinessList();

		// 返回纯商家一览
		return listBusiness;
	}

	/**
	 * 查询纯商家个数
	 * 
	 * @author xiay
	 * @return 
	 * @
	 * throws 无
	 */
	public long searchBusinessCount(TblPureBusiness tblPureBusiness,TblUser loginUser) {
		// 纯商家个数
		long dataCount;
		if (loginUser.getUserLevel()==WanmaConstants.USER_LEVEL_BUSINESS) {// 纯商家
			tblPureBusiness.setPuBuParentLoveLoginId(loginUser.getUserId()+"");
		}
		// 取得纯商家个数
		dataCount = tblPureBusinessDao.searchBusinessCount(tblPureBusiness);

		// 返回纯商家个数
		return dataCount;

	}

	/**
	 * 查询纯商家一览
	 * 
	 * @author xiay
	 * @return 
	 * @throws 无
	 */
	public List<TblPureBusiness> searchBusinessList(TblPureBusiness tblPureBusiness,TblUser loginUser) {
		// 纯商家一览
		List<TblPureBusiness> listBusiness;
		if (loginUser.getUserLevel()==WanmaConstants.USER_LEVEL_BUSINESS) {// 纯商家
			tblPureBusiness.setPuBuParentLoveLoginId(loginUser.getUserId()+"");
		}
		// 取得纯商家一览
		listBusiness = tblPureBusinessDao.searchBusinessList(tblPureBusiness);

		// 返回纯商家一览
		return listBusiness;

	}
	@Override
	public String checkLoveAccount(String loveAccount){
		TblPureBusiness tblPureBusiness=new TblPureBusiness();
		tblPureBusiness.setLoveLogin(loveAccount);
		int loveAcountSum=tblPureBusinessDao.checkLoveAccount(tblPureBusiness);		
		if(loveAcountSum>0){
			return CommonConsts.CHECK_RESULT_NG;
		}else{
			return CommonConsts.CHECK_RESULT_OK;
		}
	}

	/**
	 * 根据登陆账号获取纯商家信息
	 * 
	 * @author xiay
	 * @return TblPureBusiness
	 */
	@Override
	public TblPureBusiness findBusinessByUserId(String userId) {
		return tblPureBusinessDao.findBusinessByUserId(userId);
	}
	
	/**
	 * 根据公司id获取纯商家数量
	 * 
	 * @author xiay
	 * @return 无
	 */
	@Override
	public int findByCompanyId(String companyId) {
		return tblPureBusinessDao.findByCompanyId(companyId);
	}
	
	/**
	 * 删除纯商家
	 * 
	 * @author xiay
	 * @return 无
	 */
	@Override
	public void removeUser(TblUser tblUser) {
		// 调用DAO执行用户更新处理
		tblUser.setUserStatus(3);
		userMapper.updateStatus(tblUser);
		commitLogService.insert("删除纯商家，用户账号：["+tblUser.getUserId()+"]");
	}
}
