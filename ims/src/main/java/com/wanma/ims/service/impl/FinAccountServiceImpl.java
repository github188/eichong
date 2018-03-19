package com.wanma.ims.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.wanma.ims.common.domain.CompanyDO;
import com.wanma.ims.common.domain.FinAccountConfigRelaDO;
import com.wanma.ims.common.domain.FinAccountDO;
import com.wanma.ims.common.domain.FinAccountRelationDO;
import com.wanma.ims.common.domain.PurchaseHistoryDO;
import com.wanma.ims.common.domain.UserCardDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.domain.base.BasicModel;
import com.wanma.ims.common.domain.bo.AccountBalanceBO;
import com.wanma.ims.common.dto.BaseResultDTO;
import com.wanma.ims.common.dto.BatchResultDTO;
import com.wanma.ims.constants.DownFileConstants;
import com.wanma.ims.constants.ResultCodeConstants;
import com.wanma.ims.constants.WanmaConstants;
import com.wanma.ims.mapper.CompanyMapper;
import com.wanma.ims.mapper.FinAccountConfigRelaMapper;
import com.wanma.ims.mapper.FinAccountMapper;
import com.wanma.ims.mapper.FinAccountRelationMapper;
import com.wanma.ims.mapper.PurchaseHistoryMapper;
import com.wanma.ims.mapper.UserCardMapper;
import com.wanma.ims.mapper.UserMapper;
import com.wanma.ims.service.FinAccountService;
import com.wanma.ims.util.DateUtil;
import com.wanma.ims.util.ExcelExporterUtil;
import com.wanma.ims.util.ExcelImportUtil;
import com.wanma.ims.util.MD5Util;
import com.wanma.ims.util.PKUtil;

@Service("finAccountService")
public class FinAccountServiceImpl implements FinAccountService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private FinAccountMapper finAccountMapper;
	
	@Autowired
	private FinAccountRelationMapper finAccountRelationMapper;
	
	@Autowired
	private FinAccountConfigRelaMapper finAccountConfigRelaMapper;
	
	@Autowired
    private UserMapper userMapper;
	
	@Autowired
    private UserCardMapper userCardMapper;
	
	@Autowired
    private CompanyMapper companyMapper;
	
	@Autowired
    private PurchaseHistoryMapper purchaseHistoryMapper;
	
	@Override
	public Long getFinAccountCount(FinAccountDO finAccount) {
		return finAccountMapper.getFinAccountCount(finAccount);
	}
	
	@Override
	public List<FinAccountDO> getFinAccountList(FinAccountDO finAccount) {
		return finAccountMapper.getFinAccountList(finAccount);
	}
	
	@Override
	@Transactional
	public Long addFinAccount(BasicModel object) throws Exception{
		if (object == null) {
			log.error(this.getClass() + ".addFinAccount() error : 参数不能为空！");
			return -1L;
		}
		
		FinAccountDO finAccount = new FinAccountDO();
		Long accountId = 0L;
		//1.用户  2.公司 3.卡
		Integer sysType = object.getSysType();
		if (sysType.intValue() == 1) {
			UserDO user = (UserDO)object;

			//验证用户信息
			if (user == null || user.getUserId() == 0l) {
				log.error(this.getClass() + ".addFinAccount() error : 用户信息不能为空！");
				return -2L;
			}
			Long userId = user.getUserId();
			UserDO result = userMapper.selectUserByUserId(userId);
			if (result == null) {
				log.error(this.getClass() + ".addFinAccount() error : " + userId + "用户信息不存在！");
				return -3L;
			}
			
			//取得是大账户的账务配置
			FinAccountConfigRelaDO finAccountConfigRela = new FinAccountConfigRelaDO();
			finAccountConfigRela.setCpyId(user.getCpyId());
			finAccountConfigRela.setBillAccountId(1L);
			finAccountConfigRela.setPaymentRule(1);
			Long count = finAccountConfigRelaMapper.getFinAccountConfigRelaCount(finAccountConfigRela);
			if (count != null && count.intValue() > 0) {
				CompanyDO resultCompany = companyMapper.selectCpyListById(user.getCpyId());
				if (resultCompany == null) {
					log.error(this.getClass() + ".addFinAccount() error : " + user.getCpyId() + "找不到对应的渠道数据！");
					return -4L;
				}
				accountId = resultCompany.getAccountId();
			}else{
				finAccount.setAccountNO(PKUtil.generateAccountNo(userId, sysType));
				finAccount.setAccountPwd(MD5Util.Md5(WanmaConstants.DEFAULT_LOGIN_PASSWORD));
				finAccount.setAccountBalance(Double.valueOf(0));
				finAccount.setAccountPresent(Double.valueOf(0));
//				finAccount.setTradeType(resultCompany.getTradeType());
				finAccount.setTradeType(user.getTradeType());
				finAccount.setAccountWarn(0d);
				finAccount.setAccountStatus(1);
				finAccount.setIsDel(0);
				finAccount.setCreator(user.getCreator());
				finAccount.setModifier(user.getCreator());
				// 取系统当前时间
				Date now = DateUtil.parse(DateUtil.getNow(DateUtil.TYPE_COM_YMDHMS));
				finAccount.setGmtCreate(now);
				finAccount.setGmtModified(now);
				
				finAccountMapper.addFinAccount(finAccount);
				accountId = finAccount.getAccountId();
			}
			
			FinAccountRelationDO finAccountRelation = new FinAccountRelationDO();
			finAccountRelation.setUserId(userId);
			finAccountRelation.setBillAccountId(1L);
			finAccountRelation.setAccountId(accountId);
			finAccountRelation.setPriority(1);
			finAccountRelation.setIsDel(0);
			finAccountRelation.setCreator("");
			finAccountRelation.setModifier("");
			finAccountRelation.setGmtCreate(new Date());
			finAccountRelation.setGmtModified(new Date());
			int addResult = finAccountRelationMapper.addFinAccountRelation(finAccountRelation);
			if (addResult < 1) {
				log.error(this.getClass() + ".addFinAccount() error : " + accountId + "新增账务关系失败！");
				return -4L;
			}
		}else if (sysType.intValue() == 2) {
			CompanyDO company = (CompanyDO)object;
			
			//验证公司信息
			if (company == null || company.getCpyId() == 0l) {
				log.error(this.getClass() + ".addFinAccount() error : 公司信息不能为空！");
				return -5L;
			}
			Long cypId = company.getCpyId();
			CompanyDO resultCompany = companyMapper.selectCpyListById(cypId);
			if (resultCompany == null) {
				log.error(this.getClass() + ".addFinAccount() error : 找不到" + cypId + "的渠道数据！");
				return -4L;
			}

			finAccount.setAccountNO(PKUtil.generateAccountNo(Long.valueOf(company.getCpyNumber()), sysType));
			finAccount.setAccountPwd(MD5Util.Md5(WanmaConstants.DEFAULT_LOGIN_PASSWORD));
			finAccount.setAccountBalance(0d);
			finAccount.setAccountPresent(0d);
			finAccount.setTradeType(resultCompany.getTradeType());
			finAccount.setAccountWarn(0d);
			finAccount.setAccountStatus(1);
			finAccount.setIsDel(0);
			finAccount.setCreator(company.getCreator());
			finAccount.setModifier(company.getCreator());
			// 取系统当前时间
			Date now = DateUtil.parse(DateUtil.getNow(DateUtil.TYPE_COM_YMDHMS));
			finAccount.setGmtCreate(now);
			finAccount.setGmtModified(now);
			int count = finAccountMapper.addFinAccount(finAccount);
			if(count <= 0){
				log.error(this.getClass() + ".addFinAccount() error : " + accountId + "新增资金账户失败！");
				return -3L;
			}
			//付费策略
			FinAccountConfigRelaDO finAccountConfigRelaDO =  new FinAccountConfigRelaDO();
			finAccountConfigRelaDO.setCpyId(company.getCpyId());
			finAccountConfigRelaDO.setBillAccountId(new Long(1));
			finAccountConfigRelaDO.setPaymentRule(company.getPayRule());
			finAccountConfigRelaDO.setIsDel(0);
			finAccountConfigRelaDO.setCreator(company.getCreator());
			finAccountConfigRelaDO.setModifier(company.getCreator());
			finAccountConfigRelaDO.setGmtCreate(new Date());
			finAccountConfigRelaDO.setGmtModified(new Date());
			if (finAccountConfigRelaMapper.addFinAccountConfigRela(finAccountConfigRelaDO) <= 0) {
				log.error(this.getClass() + ".addFinAccount() error : " + accountId + "新增账务关系失败！");
				return -4L;
			}
			return accountId = finAccount.getAccountId();
		}else if(sysType.intValue() == 3){
			UserCardDO userCard = (UserCardDO)object;

			//验证卡信息
			if (userCard == null || userCard.getUcUserId() == 0L) {
				log.error(this.getClass() + ".addFinAccount() error : 卡信息不能为空！");
				return -2L;
			}
			Long cardId = userCard.getUcId();
			UserCardDO result = userCardMapper.getCardBasicInfo(userCard);
			if (result == null) {
				log.error(this.getClass() + ".addFinAccount() error : " + cardId + "卡信息不存在！");
				return -6L;
			}
			
			//验证渠道信息
			Integer cypId = userCard.getUcCpyId();
			CompanyDO resultCompany = companyMapper.selectCpyListById(Long.valueOf(cypId));
			if (resultCompany == null) {
				log.error(this.getClass() + ".addFinAccount() error : " + cypId + "找不到对应的渠道数据！");
				return -4L;
			}
			if (resultCompany.getTradeType() == null || resultCompany.getTradeType().intValue() == 0) {
				log.error(this.getClass() + ".addFinAccount() error : " + cypId + "请先维护渠道的付费策略！");
				return -7L;
			}
			
			UserDO user = userMapper.selectUserByUserId(result.getUcUserId());
			if (user == null) {
				log.error(this.getClass() + ".addFinAccount() error : " + result.getUcUserId() + "用户信息不存在！");
				return -3L;
			}
			
			//取得是大账户的账务配置
			FinAccountConfigRelaDO finAccountConfigRela = new FinAccountConfigRelaDO();
			finAccountConfigRela.setCpyId(Long.valueOf(cypId));
			finAccountConfigRela.setBillAccountId(1L);
			finAccountConfigRela.setPaymentRule(1);
			Long count = finAccountConfigRelaMapper.getFinAccountConfigRelaCount(finAccountConfigRela);
			if (count != null && count > 0) {
				accountId = resultCompany.getAccountId();
			}else{
				finAccount.setAccountNO(PKUtil.generateAccountNo(result.getUcUserId(), sysType));
				finAccount.setAccountPwd(MD5Util.Md5(WanmaConstants.DEFAULT_LOGIN_PASSWORD));
				finAccount.setAccountBalance(0d);
				finAccount.setAccountPresent(0d);
				finAccount.setTradeType(userCard.getTradeType());
				finAccount.setAccountWarn(0d);
				finAccount.setAccountStatus(1);
				finAccount.setIsDel(0);
				finAccount.setCreator(userCard.getCreator());
				finAccount.setModifier(userCard.getCreator());
				// 取系统当前时间
				Date now = DateUtil.parse(DateUtil.getNow(DateUtil.TYPE_COM_YMDHMS));
				finAccount.setGmtCreate(now);
				finAccount.setGmtModified(now);
				
				finAccountMapper.addFinAccount(finAccount);
				accountId = finAccount.getAccountId();
			}
			
			FinAccountRelationDO finAccountRelation = new FinAccountRelationDO();
			finAccountRelation.setUserId(result.getUcUserId());
			finAccountRelation.setBillAccountId(1L);
			finAccountRelation.setAccountId(accountId);
			finAccountRelation.setPriority(1);
			finAccountRelation.setIsDel(0);
			finAccountRelation.setCreator("");
			finAccountRelation.setModifier("");
			finAccountRelation.setGmtCreate(new Date());
			finAccountRelation.setGmtModified(new Date());
			int addResult = finAccountRelationMapper.addFinAccountRelation(finAccountRelation);
			if (addResult < 1) {
				log.error(this.getClass() + ".addFinAccount() error : " + accountId + "新增账务关系失败！");
				return -4L;
			}
		}else{
			return 0L;
		}
		return accountId;
	}
	
	
	@Override
	@Transactional
	public int modifyFinAccount(FinAccountDO finAccount, UserDO user) throws Exception{
		FinAccountDO qryfinAccount = new FinAccountDO();
		qryfinAccount.setAccountId(finAccount.getAccountId());
		Long qryResult = finAccountMapper.getFinAccountCount(qryfinAccount);
		if (qryResult < 1) {
			return 2;
		}

		finAccount.setModifier(user.getUserAccount());
		finAccount.setGmtModified(DateUtil.parse(DateUtil.getNow(DateUtil.TYPE_COM_YMDHMS)));

		return finAccountMapper.modifyFinAccount(finAccount);
	}
	
	@Override
	@Transactional
	public int removeFinAccount(FinAccountDO finAccount) throws Exception{
		return finAccountMapper.removeFinAccount(finAccount);
	}

	@Override
	public List<FinAccountDO> getFinAccount4User(AccountBalanceBO accountBalance) {
		if (accountBalance == null || accountBalance.getUserId() == null) {
			log.error(this.getClass() + ".getFinAccount4User() error : 用户信息不允许为空！");
			return null;
		}
		
		if (accountBalance.getCpyType() == null) {
			log.error(this.getClass() + ".getFinAccount4User() error : 用户渠道信息不允许为空！");
			return null;
		}
		Map<String, Object> map = setParams(accountBalance);
		
		return finAccountMapper.getFinAccount4User(map);
	}

	@Override
	public Double getFinAccountBalance4User(AccountBalanceBO accountBalance) {
		if (accountBalance == null || accountBalance.getUserId() == null) {
			log.error(this.getClass() + ".getFinAccountBalance4User() error : 用户信息不允许为空！");
			return 0.0;
		}
		
		if (accountBalance.getCpyType() == null) {
			log.error(this.getClass() + ".getFinAccountBalance4User() error : 用户渠道信息不允许为空！");
			return 0.0;
		}
		Map<String, Object> map = setParams(accountBalance);
		
		return finAccountMapper.getFinAccountBalance4User(map);
	}

	private Map<String, Object> setParams(AccountBalanceBO accountBalance) {
		UserCardDO userCard = new UserCardDO();
		userCard.setNewUserId(accountBalance.getUserId());
		List<UserCardDO> userRelaList = userCardMapper.getCardInfoByUserId(userCard);
		List<Long> userDefaultCardIdList = new ArrayList<Long>();
		for (UserCardDO userCardDO : userRelaList) {
			userDefaultCardIdList.add(userCardDO.getUcUserId());
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", accountBalance.getUserId());
		map.put("cpyType", accountBalance.getCpyType());
		map.put("userDefaultIds", userDefaultCardIdList);
		return map;
	}
	
	@Override
	public List<FinAccountDO> getFinAccount4Card(UserCardDO userCard) {
		if (userCard == null || userCard.getUcId() == null) {
			log.error(this.getClass() + ".getFinAccount4Card() error : 卡信息不允许为空！");
			return null;
		}
		
		return finAccountMapper.getFinAccount4Card(userCard);
	}

	@Override
	@Transactional
	public int modifyFinAccountPwd(Long accountId, UserDO loginUser) throws Exception {
		if (accountId == null) {
			log.error(this.getClass() + ".modifyFinAccountPwd() error : 资金账户信息不允许为空！");
			return 2;
		}
		if (loginUser == null || loginUser.getUserAccount() == null || "".equals(loginUser.getUserAccount())) {
			log.error(this.getClass() + ".modifyFinAccountPwd() error : 修改人帐号信息不允许为空！");
			return 3;
		}
		
		FinAccountDO finAccount = new FinAccountDO();
		finAccount.setAccountId(accountId);
		finAccount.setAccountPwd(MD5Util.Md5(WanmaConstants.DEFAULT_LOGIN_PASSWORD));
		finAccount.setModifier(loginUser.getUserAccount());
		finAccount.setGmtModified(DateUtil.parse(DateUtil.getNow(DateUtil.TYPE_COM_YMDHMS)));
		
		return finAccountMapper.modifyFinAccountPwd(finAccount);
	}
	
	@Override
	@Transactional
	public int updateFinAccountPwd(Long accountId, String accountPwd,
			UserDO loginUser) throws Exception {
		if (accountId == null) {
			log.error(this.getClass() + ".modifyFinAccountPwd() error : 资金账户信息不允许为空！");
			return 2;
		}
		if (loginUser == null || loginUser.getUserAccount() == null || "".equals(loginUser.getUserAccount())) {
			log.error(this.getClass() + ".modifyFinAccountPwd() error : 修改人帐号信息不允许为空！");
			return 3;
		}
		
		FinAccountDO finAccount = new FinAccountDO();
		finAccount.setAccountId(accountId);
		finAccount.setAccountPwd(MD5Util.Md5(accountPwd));
		finAccount.setModifier(loginUser.getUserAccount());
		finAccount.setGmtModified(DateUtil.parse(DateUtil.getNow(DateUtil.TYPE_COM_YMDHMS)));
		
		return finAccountMapper.modifyFinAccountPwd(finAccount);
	}
	
	/**
	* <p>Description: 获取公司首页中的余额和状态</p>
	* @param
	* @author bingo
	* @date 2017-7-10
	 */
	public FinAccountDO getFinAccountBalance4Cpy(String accountId) throws Exception{
		if (accountId == null || "".equals(accountId)) {
			log.error(this.getClass() + ".getFinAccountBalance4Cpy() error : 公司的资金账户信息不允许为空！");
			return null;
		}
		
		return finAccountMapper.getFinAccountBalance4Cpy(accountId);
	}

	@Override
	public BaseResultDTO exportFinAccount(HttpServletResponse response,
			FinAccountDO finAccount, UserDO loginUser) throws Exception {
		BaseResultDTO result = new BaseResultDTO();

		Long count = finAccountMapper.getFinAccountCount(finAccount);
		if (count > 50000L) {
			log.warn(this.getClass() + ".exportPurchaseHistory is too long|finAccount={}|loginUser={}", finAccount, loginUser);
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "导出的数据量超过5万！");
		}
		
        List<FinAccountDO> finAccountList = getExportFinAccountList(finAccount);
        if (CollectionUtils.isEmpty(finAccountList)) {
            log.warn(this.getClass() + ".exportFinAccount is empty|finAccount={}|loginUser={}", finAccount, loginUser);
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "您导出的数据不存在，请修改正确的查询条件后再导出！");
        }
        List<String> attrList = Lists.newArrayList("accountNO", "accountBalance", "accountWarn", "accountStatusName", "tradeTypeName");
        List<String> header = Lists.newArrayList("资金账户号", "余额", "预警金额",  "状态", "类型");
        ExcelExporterUtil.exportExcel(response, attrList, header, finAccountList, FinAccountDO.class, DownFileConstants.FILE_FIN_ACCOUNT_EXPORT, DownFileConstants.FILE_FIN_ACCOUNT_EXPORT_SHEET);

        return result;
	}
	
	private List<FinAccountDO> getExportFinAccountList(FinAccountDO qryFinAccount) {
        List<FinAccountDO> finAccountList = finAccountMapper.getFinAccountList(qryFinAccount);
        Map<Integer, String> tradeTypeMap = new HashMap<>();
        tradeTypeMap.put(0, "未明");
        tradeTypeMap.put(1, "信用账号");
        tradeTypeMap.put(2, "储值账号");

        Map<Integer, String> accountStatusMap = new HashMap<>();
        accountStatusMap.put(1, "正常");
        accountStatusMap.put(2, "冻结");
        accountStatusMap.put(3, "删除");

        for (FinAccountDO finAccount : finAccountList) {
        	finAccount.setTradeTypeName(tradeTypeMap.get(finAccount.getTradeType()));
        	finAccount.setAccountStatusName(accountStatusMap.get(finAccount.getAccountStatus()));
        }

        return finAccountList;
    }

	@Override
	@Transactional
	public BaseResultDTO equalAllocation(CompanyDO company, MultipartFile file, String allocationAmount, UserDO loginUser) {
		BaseResultDTO resultDTO = new BaseResultDTO();
		try {
			List<List<String>> data = null;
			// 读取EXCEL
			 BatchResultDTO<List<String>> result = ExcelImportUtil.importListFromExcel(file);
			 if (result.isFailed()) {
	             return result;
	         }	
			 data = result.getModule();
			 FinAccountDO cpyfinAccount = finAccountMapper.getFinAccountBalance4Cpy(company.getAccountId().toString());
			 for (int i = 1; i < data.size(); i++) {
				if (CollectionUtils.isEmpty(data.get(i))) {
	                continue;
	            }
				//修改公司资金账户余额
				 cpyfinAccount.setAccountId(company.getAccountId());
				 cpyfinAccount.setModifier(loginUser.getUserAccount());
				 cpyfinAccount.setAllocationAmount("-"+allocationAmount);
				 finAccountMapper.modifyFinAccountBalance(cpyfinAccount);
				 //增加消费记录
				 addPurchaseHistory(17, Double.parseDouble(allocationAmount), new Long(0), "大账户为小账户等额配资", company.getAccountId());
				//修改用户资金账户余额
				 UserDO userDO = userMapper.selectUserByUserAccountAndCpyId(data.get(i).get(0), company.getCpyId());
				 FinAccountDO userfinAccount = new FinAccountDO();
				 userfinAccount.setAccountId(userDO.getAccountId());
				 userfinAccount.setModifier(loginUser.getUserAccount());
				 userfinAccount.setAllocationAmount(allocationAmount);
				 finAccountMapper.modifyFinAccountBalance(userfinAccount);
				//增加消费记录
				 addPurchaseHistory(7, Double.parseDouble(allocationAmount),userDO.getUserId(), "大账户为小账户等额配资", userDO.getAccountId());
			 }
		} catch (Exception e) {
			 log.error(this.getClass() + "-checkExcel is error|fileName={}|loginUser={}", file.getOriginalFilename(), e);
		}
		return resultDTO;
	}

	@Override
	@Transactional
	public BaseResultDTO separateAllocation(CompanyDO company,
			FinAccountDO userfinAccount, UserDO loginUser) throws Exception{
		BaseResultDTO result = new BaseResultDTO();
		Double allocationAmount = Double.parseDouble(userfinAccount.getAllocationAmount());//分配的资金
		Long userId;
		userfinAccount = finAccountMapper.getFinAccountByAccountNO(userfinAccount.getAccountNO());
		if(null == userfinAccount){
			 return new BaseResultDTO(false, ResultCodeConstants.FAILED, "资金账户不存在");
		}
		if(userfinAccount.getAccountStatus()!=1){
			 return new BaseResultDTO(false, ResultCodeConstants.FAILED, "资金账户状态不正常");
		}
		UserDO userDO = userMapper.getCpyUserByAccountId(userfinAccount.getAccountId());//根据资金账户查渠道用户
		if(userDO!=null){
			if(!userDO.getCpyId().toString().equals(company.getCpyId().toString())){
				 return new BaseResultDTO(false, ResultCodeConstants.FAILED, "该资金账户不是此公司用户");
			}
			userId = userDO.getUserId();
		}else {
			UserCardDO userCardDO = userCardMapper.getUserCardByAccountId(userfinAccount.getAccountId());
			if(userCardDO==null){
				 return new BaseResultDTO(false, ResultCodeConstants.FAILED, "资金账号不是渠道用户或者卡");
			}else if(!userCardDO.getUcCpyId().toString().equals(company.getCpyId().toString())){
				 return new BaseResultDTO(false, ResultCodeConstants.FAILED, "该资金账户不是此公司用户");
			}
			userId = userCardDO.getUcUserId();
		}
		
		//修改公司资金账户余额
		 FinAccountDO cpyfinAccount = finAccountMapper.getFinAccountBalance4Cpy(company.getAccountId().toString());
		 if(cpyfinAccount.getAccountBalance()<allocationAmount){
			 return new BaseResultDTO(false, ResultCodeConstants.FAILED, "资金账户资金不够分配");
		 }
		 cpyfinAccount.setAccountId(company.getAccountId());
		 cpyfinAccount.setModifier(loginUser.getUserAccount());
		 cpyfinAccount.setAllocationAmount("-"+allocationAmount);
		 finAccountMapper.modifyFinAccountBalance(cpyfinAccount);
		 addPurchaseHistory(17, allocationAmount, new Long(0), "大账户为小账户单独配资", company.getAccountId());
		 //修改用户资金账户余额
		 userfinAccount.setModifier(loginUser.getUserAccount());
		 userfinAccount.setAllocationAmount(allocationAmount.toString());
		 userfinAccount.setAccountId(userfinAccount.getAccountId());
		 finAccountMapper.modifyFinAccountBalance(userfinAccount);
		 addPurchaseHistory(7, allocationAmount, userId, "大账户为小账户单独配资",userfinAccount.getAccountId());
		return result;
	}
	
	/**
	* <p>Description: 修改大账户预警金额</p>
	* @param
	* @author bingo
	* @date 2017-8-25
	*/
	@Transactional
	public BaseResultDTO modifyFinAccountWarn(FinAccountDO finAccount, UserDO user) throws Exception{
		BaseResultDTO baseResultDTO = new BaseResultDTO();
		if (finAccount == null || finAccount.getAccountId() == null) {
			log.error(this.getClass() + ".modifyFinAccountWarn() error : 资金账户数据不能为空！");
			baseResultDTO.setSuccess(false);
			baseResultDTO.setErrorDetail("资金账户数据不能为空！");
			return baseResultDTO;
		}
		
		if (finAccount.getAccountWarn() == null) {
			log.error(this.getClass() + ".modifyFinAccountWarn() error : 预警金额不能为空！");
			baseResultDTO.setSuccess(false);
			baseResultDTO.setErrorDetail("预警金额不能为空！");
			return baseResultDTO;
		}
		
		finAccount.setModifier(user.getUserId().toString());
		finAccount.setGmtModified(new Date());
		
		int count = finAccountMapper.modifyFinAccountWarn(finAccount);
		if (count < 1) {
			log.error(this.getClass() + ".modifyFinAccountWarn() error : 更新失败！");
			baseResultDTO.setSuccess(false);
			baseResultDTO.setErrorDetail("更新失败！");
			return baseResultDTO;
		}
		return baseResultDTO;
	}

	@Override
	@Transactional
	public boolean modifyFinAccountRefund(FinAccountDO finAccount) throws Exception {
		boolean flag = finAccountMapper.updateFinAccountRefund(finAccount) > 0;
		//增加交易流水
		PurchaseHistoryDO purchaseHistory = new PurchaseHistoryDO();
		purchaseHistory.setPuHiType(14);
		purchaseHistory.setPuHiPurchaseHistoryTime(new Date());
		purchaseHistory.setPuHiMonetary(finAccount.getAccountBalance());
		purchaseHistory.setPuHiUserId(finAccount.getUserId());
		purchaseHistory.setPuHiTransactionNumber(PKUtil.generatePhNo());
		purchaseHistory.setAccountId(finAccount.getAccountId());
		purchaseHistory.setPuHiConsumerRemark("退款");
		purchaseHistory.setPuHiChargeType(0);
		purchaseHistory.setPkInvoice(0L);
		purchaseHistory.setPuhiInvoiceStatus(0);
		purchaseHistoryMapper.addPurchaseHistory(purchaseHistory);
		return flag;
	}

	@Override
	public BaseResultDTO checkExcel(CompanyDO company, MultipartFile file,FinAccountDO finAccount) {
		BaseResultDTO resultDTO = new BaseResultDTO();
		if (file == null || file.isEmpty()) {
			log.warn(this.getClass() + "-checkExcel is failed, multipartFile is null");
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "请导入正确的excel文件！");
		}
		try {
			List<List<String>> data = null;
			List<String> list = new ArrayList<String>();
			String errorString=null;
			// 读取EXCEL
			 BatchResultDTO<List<String>> result = ExcelImportUtil.importListFromExcel(file);
			 if (result.isFailed()) {
	             return result;
	         }	
			 data = result.getModule();
			 int userAccountExcelCount=0;//用户数量
			 for (int i = 1; i < data.size(); i++) {
				if (CollectionUtils.isEmpty(data.get(i))) {
	                continue;
	            }
				if (userMapper.selectUserByUserAccount(data.get(i).get(0).trim())==null) {
					errorString+="第"+i+"行错误用户账号不存在;";
				}else if (userMapper.selectUserByUserAccountAndCpyId(data.get(i).get(0).trim(),company.getCpyId())==null) {
					errorString+="第"+i+"行错误不是该渠道用户;";
				}
				 //取第一列的数据
				 list.add(data.get(i).get(0));
				 userAccountExcelCount++;
			 }
			 if (errorString!=null) {
				 return new BaseResultDTO(false, ResultCodeConstants.FAILED, errorString);
			 }else {
				 list = hasSame(list);//去重
				 int userAccountCount = list.size();
				 double allocationAmount = Double.parseDouble(finAccount.getAllocationAmount());
				 allocationAmount = allocationAmount*userAccountExcelCount;
				 finAccount = finAccountMapper.getFinAccountById(company.getAccountId().toString());
				 if(allocationAmount>finAccount.getAccountBalance()){
					 return new BaseResultDTO(false, ResultCodeConstants.FAILED, "资金不够分配");
				 }
				 Map<String,Object> params=new HashMap<String, Object>();
				 params.put("userAccountCount", userAccountCount);
				 params.put("allocationAmount", allocationAmount);
				 params.put("accountNO", finAccount.getAccountNO());
				 resultDTO.setObj(params);
			 }
		} catch (Exception e) {
			 log.error(this.getClass() + "-checkExcel is error|fileName={}|loginUser={}", file.getOriginalFilename(), e);
		}
		return resultDTO;
		
	}
	public List<String> hasSame( List<String> list) {
		for(int i=0 ;i<list.size();i++){//循环获取用户账号
			for(int j=i+1;j<list.size();j++){
				if(list.get(i).equals(list.get(j))){
					list.remove(i);
				}
			}
		}
		return list;
	}
	
	public void addPurchaseHistory(Integer puHiType,double allocationAmount,Long puHiUserId,String puHiPurchaseContent,Long accountId) {
		PurchaseHistoryDO purchaseHistory = new PurchaseHistoryDO();
			purchaseHistory.setPuHiType(puHiType);
			purchaseHistory.setPuHiPurchaseHistoryTime(new Date());
			purchaseHistory.setPuHiMonetary(allocationAmount);
			purchaseHistory.setPuHiUserId(puHiUserId);
			purchaseHistory.setPuHiConsumerRemark("");
			purchaseHistory.setPuHiPurchaseContent(puHiPurchaseContent);
			purchaseHistory.setPuHiChargeType(0);
			purchaseHistory.setPuHiUserOrigin(0);
			purchaseHistory.setPuHiElectricPileCode("");
			purchaseHistory.setAccountId(accountId);
			purchaseHistory.setPuHiExternalCardNumber("");
			purchaseHistory.setPuHiTransactionNumber(PKUtil.generatePhNo());
			purchaseHistory.setPuHiPaymentNumber("");
			purchaseHistory.setPuhiInvoiceStatus(0);
			purchaseHistory.setPkInvoice(new Long(0));
		purchaseHistoryMapper.addPurchaseHistory(purchaseHistory);
	}
}

