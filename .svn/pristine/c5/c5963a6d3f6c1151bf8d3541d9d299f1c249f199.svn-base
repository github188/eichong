package com.wanma.ims.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.wanma.ims.mapper.*;
import com.wanma.ims.service.*;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.wanma.ims.common.domain.CardApplicationFormDO;
import com.wanma.ims.common.domain.CompanyDO;
import com.wanma.ims.common.domain.FinAccountConfigRelaDO;
import com.wanma.ims.common.domain.FinAccountDO;
import com.wanma.ims.common.domain.PurchaseHistoryDO;
import com.wanma.ims.common.domain.UserCardDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.dto.BaseResultDTO;
import com.wanma.ims.constants.DownFileConstants;
import com.wanma.ims.constants.ResultCodeConstants;
import com.wanma.ims.constants.WanmaConstants;
import com.wanma.ims.util.ExcelExporterUtil;
import com.wanma.ims.util.PKUtil;


@Service("userCardService")
public class UserCardServiceImpl implements UserCardService {

	 private static final Logger LOGGER = LoggerFactory.getLogger(UserCardServiceImpl.class);
	@Autowired
	private UserCardMapper userCardMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserService userService;
	@Autowired
	private FinAccountConfigRelaMapper finAccountConfigRelaMapper;
	@Autowired
	private FinAccountMapper finAccountMapper;
	@Autowired
	private PurchaseHistoryMapper purchaseHistoryMapper;
	@Autowired
	private FinAccountService finAccountService;
	@Autowired
	private CardApplicationFormService cardApplicationFormService;
	@Autowired
	private CardApplicationFormMapper cardApplicationFormMapper;
	@Autowired
	private CarVinRelaService carVinRelaService;
	
	@Override
	public long getCardCount(UserCardDO userCard) {
		userCardMapper.getUserCard(userCard);
		return userCardMapper.getCardCount(userCard);
	}
	@Override
	public List<UserCardDO> getCardList(UserCardDO userCard) {
		List<UserCardDO> userCardList =  userCardMapper.getCardList(userCard);
//		for(UserCardDO card : userCardList){
//			if(card.getIsApp()==1&&card.getNewUserId()!=0){
//				UserDO user = 	userService.getUserById(card.getNewUserId());
//				card.setUserAccount(user.getUserAccount());
//			}
//		}
		return userCardList;
	}
	@Override
	public UserCardDO getUserCard(UserCardDO userCard) {
		return userCardMapper.getUserCard(userCard);
	}

	@Override
	public List<UserCardDO> getCardInfoByUserId(UserCardDO userCard) {
		return userCardMapper.getCardInfoByUserId(userCard);
	}
	@Override
	@Transactional
	public boolean lossUserCard(UserCardDO userCard) {
		userCard.setUcStatus(WanmaConstants.UCSTATUSLOSS);//挂失
		return	userCardMapper.updateUserCard(userCard)>0 ? true:false;
	}
	@Override
	@Transactional
	public boolean relieveLossUserCard(UserCardDO userCard) {
		userCard.setUcStatus(WanmaConstants.UCSTATUSNORMAL);//解挂
		return	userCardMapper.updateUserCard(userCard)>0 ? true:false;
	}

	@Override
	@Transactional
	public boolean frozenUserCard(UserCardDO userCard) {
		userCard.setUcStatus(2);//解冻
		return userCardMapper.updateUserCard(userCard)>0 ? true:false;
	}
	@Override
	@Transactional
	public boolean relieveFrozenUserCard(UserCardDO userCard) {
		userCard.setUcStatus(WanmaConstants.UCSTATUSNORMAL);//解冻
		return userCardMapper.updateUserCard(userCard)>0 ? true:false;
	}
	@Override
	@Transactional
	public boolean cancelUserCard(UserCardDO userCard) {
		userCard.setUcStatus(WanmaConstants.UCSTATUSCANCEL);
		if(userCard.getNewUserId()==0&&userCard.getUcCpyId()>0){//单用卡的，注销的时候卡里面有钱，那么会触发一条注销退款的消费记录
			 FinAccountDO cardFinAccount = new FinAccountDO();
			 cardFinAccount.setAccountId(userCard.getAccountId());
			 List<FinAccountDO> finAccountDOList = finAccountMapper.getFinAccountList(cardFinAccount);
			 if(finAccountDOList!=null){
					Double allocationAmount = finAccountDOList.get(0).getAccountBalance();//查出卡里面的钱 
					if(allocationAmount>0){
						cardFinAccount.setAllocationAmount("-"+allocationAmount);
						cardFinAccount.setModifier(userCard.getModifier());
						finAccountMapper.modifyFinAccountBalance(cardFinAccount);//卡账户的钱减掉
						PurchaseHistoryDO cardPurchaseHistory = new PurchaseHistoryDO();
						cardPurchaseHistory.setPuHiType(17);//
						cardPurchaseHistory.setPuHiTransactionNumber(PKUtil.generatePhNo());
						cardPurchaseHistory.setPuHiPurchaseContent("卡注销退款");
						cardPurchaseHistory.setAccountId(userCard.getAccountId());
						cardPurchaseHistory.setPuHiUserId(userCard.getUcUserId());
						cardPurchaseHistory.setPuHiMonetary(allocationAmount);
						addPurchaseHistory(cardPurchaseHistory);
						}
					}
		}
		return userCardMapper.updateUserCard(userCard)>0? true:false;
	}
	@Override
	public UserCardDO checkExternalCardNumber(UserCardDO userCard) {
			 return userCardMapper.checkExternalCardNumber(userCard);
	}
	@Override
	@Transactional
	public BaseResultDTO bindCard(UserCardDO userCard, UserDO userDO) {
		 userCard.setNewUserId(userDO.getUserId());
		 if(userMapper.selectUserList(userDO)==null){
			 return new BaseResultDTO(false, ResultCodeConstants.FAILED, "用户不存在");
		 }else{
			 userDO = userMapper.selectUserList(userDO).get(0); 
		 }
		 List<FinAccountConfigRelaDO> finConfigRelaDOList = getFinAccountConfigRelaList(userDO.getCpyId());
			if(finConfigRelaDOList!=null){
				if(finConfigRelaDOList.get(0).getPaymentRule()!=WanmaConstants.PAYMENTRULE_CPYACCOUNT){
					 FinAccountDO cardFinAccount = new FinAccountDO();
					 cardFinAccount.setAccountId(userCard.getAccountId());
					 List<FinAccountDO> finAccountDOList = finAccountMapper.getFinAccountList(cardFinAccount);
					if(finAccountDOList!=null){
						Double allocationAmount = finAccountDOList.get(0).getAccountBalance();//查出卡里面的钱 
						if(allocationAmount>0){
							cardFinAccount.setAllocationAmount("-"+allocationAmount);
							cardFinAccount.setModifier(userCard.getModifier());
							finAccountMapper.modifyFinAccountBalance(cardFinAccount);//卡账户的钱减掉
							PurchaseHistoryDO cardPurchaseHistory = new PurchaseHistoryDO();
							cardPurchaseHistory.setPuHiType(17);//对于卡是资金划出
							cardPurchaseHistory.setPuHiPurchaseContent("资金划出");
							cardPurchaseHistory.setPuHiTransactionNumber(PKUtil.generatePhNo());
							cardPurchaseHistory.setAccountId(cardFinAccount.getAccountId());
							cardPurchaseHistory.setPuHiMonetary(allocationAmount);
							cardPurchaseHistory.setPuHiUserId(userCard.getUcUserId());
							addPurchaseHistory(cardPurchaseHistory);
							
							FinAccountDO userFinAccount = new FinAccountDO();
							userFinAccount.setAllocationAmount(allocationAmount.toString());
							userFinAccount.setAccountId(userDO.getAccountId());
							userFinAccount.setModifier(userCard.getModifier());
							finAccountMapper.modifyFinAccountBalance(userFinAccount);//用户的账户钱增加
							PurchaseHistoryDO userpurchaseHistory = new PurchaseHistoryDO();
							userpurchaseHistory.setPuHiType(7);//对于用户是资金划入
							userpurchaseHistory.setPuHiPurchaseContent("资金划入");
							userpurchaseHistory.setPuHiTransactionNumber(PKUtil.generatePhNo());
							userpurchaseHistory.setAccountId(userFinAccount.getAccountId());
							userpurchaseHistory.setPuHiUserId(userDO.getUserId());
							userpurchaseHistory.setPuHiMonetary(allocationAmount);
							addPurchaseHistory(userpurchaseHistory);
							//添加流水记录资金划入
						}
					}	 
				}
			}
		//绑卡的时候 user-vin 关系表修改
		BaseResultDTO resultDTO = carVinRelaService.updateUserVinRela(userCard, userDO);
		if (resultDTO.isFailed()) {
			return new BaseResultDTO(false, resultDTO.getResultCode(), resultDTO.getErrorDetail());
		}
			updateCardApplicationForm(userCard);//修改卡申请列表的数据
			userCard.setAccountId(userDO.getAccountId());
		 userCardMapper.bindCard(userCard);//根据是否app用户修改卡表
		 return new BaseResultDTO();
	}
	
	//修改卡申请列表的数据
	@Transactional
	public void updateCardApplicationForm(UserCardDO userCard) {
		CardApplicationFormDO searchModel = new CardApplicationFormDO();
		searchModel.setUserId(userCard.getNewUserId());
		searchModel.setStatus(0);
		List<CardApplicationFormDO> CardApplicationFormList= cardApplicationFormService.getCardApplicationFormList(searchModel);
		for (CardApplicationFormDO cardApply : CardApplicationFormList) {
			cardApply.setStatus(1);
			cardApply.setUserCard(userCard.getUcExternalCardNumber());
			cardApply.setGmtModified(new Date());
			cardApplicationFormMapper.updateCardApplicationFormSelective(cardApply);
		}
	}
	
	//增加消费记录表的记录
	@Transactional
	public void addPurchaseHistory(PurchaseHistoryDO purchaseHistoryDO) {
		purchaseHistoryDO.setPuHiPurchaseHistoryTime(new Date());
		purchaseHistoryDO.setPuHiConsumerRemark("");
		purchaseHistoryDO.setPuHiChargeType(0);
		purchaseHistoryDO.setPuHiUserOrigin(0);
		purchaseHistoryDO.setPuHiElectricPileCode("");
		purchaseHistoryDO.setPuHiExternalCardNumber("");
		purchaseHistoryDO.setPuHiPaymentNumber("");
		purchaseHistoryDO.setPuhiInvoiceStatus(0);
		purchaseHistoryDO.setPkInvoice(new Long(0));
		purchaseHistoryMapper.addPurchaseHistory(purchaseHistoryDO);
	}
	@Override
	@Transactional
	public void disableCardByUserId(List<Long> userIdList,UserDO loginUser) {//禁用
		for (long userId : userIdList) {
			UserCardDO userCard = new UserCardDO();
			userCard.setModifier(loginUser.getUserAccount());
			userCard.setNewUserId(userId);
			userCardMapper.disableCardByUserId(userCard);
		}
	}
	@Override
	@Transactional
	public void releaseCardByUserId(List<Long> userIdList,UserDO loginUser) {//解禁
		for (long userId : userIdList) {
			UserCardDO userCard = new UserCardDO();
			userCard.setModifier(loginUser.getUserAccount());
			userCard.setNewUserId(userId);
			userCardMapper.releaseCardByUserId(userCard);
		}
	}
	@Override
	public UserCardDO getCardBasicInfo(UserCardDO userCard) {
		return userCardMapper.getCardBasicInfo(userCard);
	}
	@Override
	public BaseResultDTO exportCard(HttpServletResponse response,
			UserCardDO searchModel, UserDO loginUser) {
		 BaseResultDTO result = new BaseResultDTO();
	        List<UserCardDO> userCardList = getExportCardList(searchModel);
	        if (CollectionUtils.isEmpty(userCardList)) {
	            LOGGER.warn("UserCardServiceImpl-exportUser exportUserCardList is empty|searchModel={}|loginUser={}", searchModel, loginUser);
	            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "您导出的数据不存在，请修改正确的查询条件后再导出！");
	        }
	        List<String> attrList = Lists.newArrayList("ucInternalCardNumber", "ucExternalCardNumber", "chUcType", "chUcStatus", "chIsApp", "cpyCompanyname","cardBalance","userAccount");
	        List<String> header = Lists.newArrayList("内卡号", "外卡号", "卡类型", "状态", "是否绑定app", "渠道公司","卡内余额","用户账号");
	        ExcelExporterUtil.exportExcel(response, attrList, header, userCardList, UserCardDO.class, DownFileConstants.FILE_CARD_EXPORT, DownFileConstants.FILE_CARD_EXPORT_SHEET);

	        return result;
	}
    private List<UserCardDO> getExportCardList(UserCardDO searchModel) {
        List<UserCardDO> userCardList = userCardMapper.getExportCardList(searchModel);
        Map<Integer, String> cardTypeMap = new HashMap<>();
        cardTypeMap.put(10, "爱充普通公共储蓄卡");
        cardTypeMap.put(11, "爱充普通专属储蓄卡");
        cardTypeMap.put(12, "爱充企业信用卡");
        cardTypeMap.put(13, "爱充合作公共储蓄卡");
        cardTypeMap.put(14, "爱充合作专属储蓄卡");

        Map<Integer, String> cardStatusMap = new HashMap<>();
        cardStatusMap.put(0, "正常");
        cardStatusMap.put(1, "挂失");
        cardStatusMap.put(2, "冻结");
        
        Map<Integer, String> cardAppMap = new HashMap<>();
        cardAppMap.put(0, "否");
        cardAppMap.put(1, "是");

        for (UserCardDO userCard : userCardList) {
        	userCard.setChUcType(cardTypeMap.get(userCard.getUcType()));
        	userCard.setChIsApp(cardAppMap.get(userCard.getIsApp()));
        	userCard.setChUcStatus(cardStatusMap.get(userCard.getUcStatus()));
        	if(userCard.getIsApp()==1&&userCard.getNewUserId()!=0){
					UserDO user = 	userService.getUserById(userCard.getNewUserId());
					userCard.setUserAccount(user.getUserAccount());
			}
        }
        return userCardList;
    }
	@Override
	public Map<String, Integer> getCardForCompany(UserCardDO userCard) {
		Map<String, Integer> userCardMap =  new HashMap<String, Integer>();
		int totalCardNum = userCardMapper.getTotalCpyCardNum(userCard);
		int lossCardNum = userCardMapper.getLossCpyCardNum(userCard);
		userCardMap.put("totalCardNum", totalCardNum);
		userCardMap.put("lossCardNum", lossCardNum);
		return userCardMap;
	}
	@Override
	@Transactional
	public void lossUserCardForList(UserCardDO userCard, String ids) {
		userCard.setUcStatus(WanmaConstants.UCSTATUSLOSS);//挂失
		String[] idArr = ids.split(",");
		for(int i=0;i<idArr.length;i++){
			userCard.setUcId(Long.parseLong(idArr[i]));
			userCardMapper.updateUserCard(userCard);
		}
	}
	
	public List<FinAccountConfigRelaDO> getFinAccountConfigRelaList(Long cpyId) {
		FinAccountConfigRelaDO finAccountConfigRelaDO = new FinAccountConfigRelaDO();
		finAccountConfigRelaDO.setBillAccountId(new Long(1));
		finAccountConfigRelaDO.setCpyId(cpyId);
		return finAccountConfigRelaMapper.getFinAccountConfigRelaList(finAccountConfigRelaDO);
	}
	
	@Override
	@Transactional
	public BaseResultDTO bindCompanyForCard(String ids, CompanyDO company,
			UserDO loginUser, Integer tradeType,Long levelId) {
		BaseResultDTO result = new BaseResultDTO();
		try {
			List<FinAccountConfigRelaDO> finConfigRelaDOList = getFinAccountConfigRelaList(company.getCpyId());
			if(finConfigRelaDOList!=null){
				if(finConfigRelaDOList.get(0).getPaymentRule()==0||finConfigRelaDOList.get(0).getPaymentRule()==null){
					  return new BaseResultDTO(false, ResultCodeConstants.FAILED, "公司付费策略为空或为0不能绑卡！");
				}
			}
			String[] idArr = ids.split(",");
			for(int i=0;i<idArr.length;i++){
				UserCardDO userCard = new UserCardDO();
				userCard.setUcId(Long.parseLong(idArr[i]));
				userCard = userCardMapper.getUserCard(userCard);
				if(userCard.getUcCpyId() > 0){
		            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "已经绑定公司的卡不能再绑！");
				}
				UserDO user = userMapper.selectUserByUserId(userCard.getUcUserId());
				if (null == user) {
					return new BaseResultDTO(false, ResultCodeConstants.FAILED, "卡默认用户信息不存在");
				}
				userCard.setCreator(loginUser.getUserAccount());
				userCard.setModifier(loginUser.getUserAccount());
				userCard.setUcCpyId(company.getCpyId().intValue());
				userCard.setUcStatus(0);
				userCard.setSysType(3);
				userCard.setTradeType(tradeType);
				Long accountId = finAccountService.addFinAccount(userCard);
				if(accountId<0){
					return new BaseResultDTO(false, ResultCodeConstants.FAILED, "创建资金账户失败！");
				}
				userCard.setAccountId(accountId);
				//更改基表数据
//				LevelDO level = levelMapper.selectIsDefaultLevel(company.getCpyId());
				if(null == levelId){
					return new BaseResultDTO(false, ResultCodeConstants.FAILED, "请选择公司等级！");
				}
				user.setLevelId(levelId);
				userMapper.updateByUserIdSelective(user);
				//更改卡表数据
				if(tradeType == WanmaConstants.TRADE_TYPE_STORED){
					userCard.setUcType(WanmaConstants.CARD_STORED);
				}else{
					userCard.setUcType(WanmaConstants.CARD_CREDIT);
				}
				userCardMapper.updateUserCard(userCard);
			}
		} catch (Exception e) {
			 return new BaseResultDTO(false, ResultCodeConstants.FAILED, "绑定失败!");
		}
		return result;
	}
	
}
