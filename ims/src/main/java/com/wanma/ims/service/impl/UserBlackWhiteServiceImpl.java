package com.wanma.ims.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wanma.ims.common.domain.CompanyChargeRelaDO;
import com.wanma.ims.common.domain.UserBlackWhiteDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.domain.bo.UserBlackWhiteBO;
import com.wanma.ims.mapper.CompanyChargeRelaMapper;
import com.wanma.ims.mapper.UserBlackWhiteMapper;
import com.wanma.ims.mapper.UserMapper;
import com.wanma.ims.service.UserBlackWhiteService;


@Service
public class UserBlackWhiteServiceImpl implements UserBlackWhiteService{

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserBlackWhiteMapper userBlackWhiteMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private CompanyChargeRelaMapper companyChargeRelaMapper;

	@Override
	@Transactional
	public Integer addUserBlackWhite(UserBlackWhiteBO userBlackWhiteBO, UserDO loginUser)  throws Exception{
		if (userBlackWhiteBO == null) {
			log.error(this.getClass() + ".addUserBlackWhite() error : 黑白名单信息不能为空！");
			return 2;
		}
		
		if (userBlackWhiteBO.getUserAccount() == null || "".equals(userBlackWhiteBO.getUserAccount())) {
			log.error(this.getClass() + ".addUserBlackWhite() error : 用户账号不能为空！");
			return 3;
		}
		
		if (userBlackWhiteBO.getFlag() == 0) {
			if (userBlackWhiteBO.getElectricPileCode() == null) {
				log.error(this.getClass() + ".addUserBlackWhite() error : 桩编号不能为空！");
				return 4;
			}else {
				UserDO qryUser = new UserDO();
				qryUser.setUserAccount(userBlackWhiteBO.getUserAccount());
				List<UserDO> userList = userMapper.selectUserList(qryUser);
				if (userList == null || userList.size() == 0) {
					log.error(this.getClass() + ".addUserBlackWhite() error : 用户账号不存在！");
					return 5;
				}else{
					UserDO user = userList.get(0);
					Long cpyId = user.getCpyId();
					String electricPileCode = userBlackWhiteBO.getElectricPileCode();
					CompanyChargeRelaDO qry = new CompanyChargeRelaDO();
					qry.setCpyId(cpyId);
					qry.setElectricPileCode(electricPileCode);
					List<CompanyChargeRelaDO> results = companyChargeRelaMapper.getCompanyChargeRelaList(qry);
					if (results == null || results.size() == 0) {
						log.error(this.getClass() + ".addUserBlackWhite() error : 渠道对应的桩编码不在公司权限表中！");
						return 6;
					}
					
					UserBlackWhiteDO userBlackWhiteDO = new UserBlackWhiteDO();
					userBlackWhiteDO.setCpyId(cpyId);
					userBlackWhiteDO.setElectricPileCode(electricPileCode);
					userBlackWhiteDO.setType(userBlackWhiteBO.getType());
					userBlackWhiteDO.setUserType(0);
					userBlackWhiteDO.setCreator(loginUser.getUserId().toString());
					
					Long count = userBlackWhiteMapper.getUserBlackWhiteCount(userBlackWhiteDO);
					if (count > 0) {
						log.error(this.getClass() + ".addUserBlackWhite() error : 渠道对应的桩编码已存在！");
						return 7;
					}
					
					userBlackWhiteDO.setUserId(user.getUserId());
					return userBlackWhiteMapper.insertUserBlackWhite(userBlackWhiteDO);
				}
			}
		}else {
			UserDO qryUser = new UserDO();
			qryUser.setUserAccount(userBlackWhiteBO.getUserAccount());
			List<UserDO> userList = userMapper.selectUserList(qryUser);
			if (userList == null || userList.size() == 0) {
				log.error(this.getClass() + ".addUserBlackWhite() error : 用户账号不存在！");
				return 5;
			}else{
				UserDO user = userList.get(0);
				Long cpyId = user.getCpyId();
				
				CompanyChargeRelaDO qry = new CompanyChargeRelaDO();
				qry.setCpyId(cpyId);
				List<CompanyChargeRelaDO> results = companyChargeRelaMapper.getCompanyChargeRelaList(qry);
				if (results == null || results.size() == 0) {
					log.error(this.getClass() + ".addUserBlackWhite() error : 渠道对应的桩编码不存在！");
					return 6;
				}
				
				List<UserBlackWhiteDO> list = new ArrayList<UserBlackWhiteDO>();
				UserBlackWhiteDO userBlackWhiteDO = new UserBlackWhiteDO();
				for (CompanyChargeRelaDO companyChargeRelaDO : results) {
					userBlackWhiteDO = new UserBlackWhiteDO();
					userBlackWhiteDO.setElectricPileCode(companyChargeRelaDO.getElectricPileCode());
					userBlackWhiteDO.setCpyId(cpyId);
					userBlackWhiteDO.setType(userBlackWhiteBO.getType());
					userBlackWhiteDO.setUserType(0);
					
					Long count = userBlackWhiteMapper.getUserBlackWhiteCount(userBlackWhiteDO);
					if (count > 0) {
						continue;
					}
					
					userBlackWhiteDO.setCreator(loginUser.getUserId().toString());
					userBlackWhiteDO.setUserId(user.getUserId());
					list.add(userBlackWhiteDO);
				}
				
				if (list.size() == 0) {
					return 1;
				}
				return userBlackWhiteMapper.batchInsertUserBlackWhite(list);
			}
		}
	}

	@Override
	@Transactional
	public boolean batchAddUserBlackWhite(List<UserBlackWhiteDO> list) {
		return userBlackWhiteMapper.batchInsertUserBlackWhite(list) > 0 ?true:false;
	}

	@Override
	public List<UserBlackWhiteDO> getUserBlackWhiteByParam(UserBlackWhiteDO userBlackWhiteDO) {
		return userBlackWhiteMapper.selectUserBlackWhiteByParams(userBlackWhiteDO);
	}

	@Override
	@Transactional
	public boolean modifyUserBlackWhite(UserBlackWhiteDO userBlackWhiteDO) {
		return userBlackWhiteMapper.updateUserBlackWhite(userBlackWhiteDO) > 0 ?true:false;
	}

	@Override
	@Transactional
	public Integer removeUserBlackWhite(UserBlackWhiteDO userBlackWhiteDO)  throws Exception{
		return userBlackWhiteMapper.removeUserBlackWhite(userBlackWhiteDO);
	}

	@Override
	public Long getUserBlackWhiteCount(UserBlackWhiteDO userBlackWhiteDO)  throws Exception{
		return userBlackWhiteMapper.getUserBlackWhiteCount(userBlackWhiteDO);
	}

	@Override
	public List<UserBlackWhiteDO> getUserBlackWhite4Cpy(
			UserBlackWhiteDO userBlackWhiteDO) throws Exception {
		if (userBlackWhiteDO == null || userBlackWhiteDO.getCpyId() == null) {
			log.error(this.getClass() + ".getUserBlackWhite4Cpy() error : 公司账号不能为空！");
			return null;
		}
		
		return userBlackWhiteMapper.getUserBlackWhite4Cpy(userBlackWhiteDO);
	}
}
