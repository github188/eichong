package com.wanma.ims.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.wanma.ims.common.domain.PurchaseHistoryDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.domain.bo.AccountBalanceBO;
import com.wanma.ims.common.domain.bo.UserAccountBO;
import com.wanma.ims.common.dto.BaseResultDTO;
import com.wanma.ims.constants.DownFileConstants;
import com.wanma.ims.constants.ResultCodeConstants;
import com.wanma.ims.mapper.PurchaseHistoryMapper;
import com.wanma.ims.mapper.UserMapper;
import com.wanma.ims.service.PurchaseHistoryService;
import com.wanma.ims.util.ExcelExporterUtil;

@Service("purchaseHistoryService")
public class PurchaseHistoryServiceImpl implements PurchaseHistoryService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PurchaseHistoryMapper purchaseHistoryMapper;
	
	@Autowired
	private UserMapper userMapper;
	 
	@Override
	public Long getPurchaseHistoryCount(PurchaseHistoryDO purchaseHistory) {
		return purchaseHistoryMapper.getPurchaseHistoryCount(purchaseHistory);
	}
	
	@Override
	public List<PurchaseHistoryDO> getPurchaseHistoryList(PurchaseHistoryDO purchaseHistory) {
		return this.getExportPurchaseHistoryList(purchaseHistory);
	}

	@Override
	public BaseResultDTO exportPurchaseHistory(HttpServletResponse response,
			PurchaseHistoryDO purchaseHistory, UserDO loginUser) throws Exception {
		BaseResultDTO result = new BaseResultDTO();
		
		Long count = purchaseHistoryMapper.getPurchaseHistoryCount(purchaseHistory);
		if (count > 50000L) {
			log.warn(this.getClass() + ".exportPurchaseHistory is too long|purchaseHistory={}|loginUser={}", purchaseHistory, loginUser);
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "导出的数据量超过5万！");
		}
		
		List<PurchaseHistoryDO> purchaseHistoryList = getExportPurchaseHistoryList(purchaseHistory);
        if (CollectionUtils.isEmpty(purchaseHistoryList)) {
            log.warn(this.getClass() + ".exportPurchaseHistory is empty|purchaseHistory={}|loginUser={}", purchaseHistory, loginUser);
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "您导出的数据不存在，请修改正确的查询条件后再导出！");
        }
    	
        List<String> attrList = Lists.newArrayList("puHiTransactionNumber", "accountNO", "billAccountName", "puHiMonetary", "puHiElectricPileCode",
        		"puHiPaymentNumber", "puHiExternalCardNumber", "puHiTypeName", "puHiChargeTypeName", "puHiUserOriginName", "userAccount",
        		"puHiPurchaseContent", "puHiCreatedate", "puHiUpdatedate", "puHiPurchaseHistoryTime", "puHiConsumerRemark");
        List<String> header = Lists.newArrayList("流水号", "资金账号", "账单科目", "金额", "桩体编号", "订单编号", "卡号", "用户ID", "充值类型"
        		, "用户来源", "用户账号","收益内容", "创建时间", "修改时间", "消费时间", "消费备注");
        ExcelExporterUtil.exportExcel(response, attrList, header, purchaseHistoryList, PurchaseHistoryDO.class, DownFileConstants.FILE_PURCHASE_HISTORY_EXPORT, DownFileConstants.FILE_PURCHASE_HISTORY_EXPORT_SHEET);

        return result;
	}
	
	private List<PurchaseHistoryDO> getExportPurchaseHistoryList(PurchaseHistoryDO qryPurchaseHistory) {
        List<PurchaseHistoryDO> purchaseHistoryList = purchaseHistoryMapper.getPurchaseHistoryList(qryPurchaseHistory);
        Map<Integer, String> tradeTypeMap = new HashMap<>();
        tradeTypeMap.put(0, "未明");
        tradeTypeMap.put(1, "信用账户");
        tradeTypeMap.put(2, "储蓄账户");

        Map<Integer, String> puHiTypeMap = new HashMap<>();
        puHiTypeMap.put(0, "未明");
        puHiTypeMap.put(1, "充电消费（预冻结）");
        puHiTypeMap.put(2, "停车费");
        puHiTypeMap.put(3, "信用还款");
        puHiTypeMap.put(4, "充值");
        puHiTypeMap.put(5, "发票快递费");
        puHiTypeMap.put(6, "溢缴款");
        puHiTypeMap.put(7, "资金划入");
        puHiTypeMap.put(8, "授信");
        puHiTypeMap.put(11, "充电消费退款");
        puHiTypeMap.put(12, "停车费退款");
        puHiTypeMap.put(13, "信用还款退款");
        puHiTypeMap.put(14, "充值退款");
        puHiTypeMap.put(15, "发票快递费退款");
        puHiTypeMap.put(16, "溢缴款领回");
        puHiTypeMap.put(17, "资金划出");

        Map<Integer, String> puHiChargeTypeMap = new HashMap<>();
        puHiChargeTypeMap.put(0, "未明");
        puHiChargeTypeMap.put(1, "支付宝充值");
        puHiChargeTypeMap.put(2, "微信充值");
        puHiChargeTypeMap.put(3, "银联充值");
        puHiChargeTypeMap.put(4, "充电卡现金充值");
        puHiChargeTypeMap.put(5, "换卡转账");
        puHiChargeTypeMap.put(6, "7月活动送");
        puHiChargeTypeMap.put(7, "人工充值");
        puHiChargeTypeMap.put(8, "授信调整");
        puHiChargeTypeMap.put(9, "人工赠送");
        
    	Map<Integer, String> puHiUserOriginMap = new HashMap<>();
    	puHiUserOriginMap.put(0, "未明");
    	puHiUserOriginMap.put(1, "富士康");
    	puHiUserOriginMap.put(2, "吉利");
    	puHiUserOriginMap.put(3, "绿地");
    	puHiUserOriginMap.put(4, "浙誉");
    	puHiUserOriginMap.put(5, "车纷享");
    	
        for (PurchaseHistoryDO purchaseHistory : purchaseHistoryList) {
        	purchaseHistory.setTradeTypeName(tradeTypeMap.get(purchaseHistory.getTradeType()));
        	purchaseHistory.setPuHiTypeName(puHiTypeMap.get(purchaseHistory.getPuHiType()));
        	purchaseHistory.setPuHiChargeTypeName(puHiChargeTypeMap.get(purchaseHistory.getPuHiChargeType()));
        	purchaseHistory.setPuHiUserOriginName(puHiUserOriginMap.get(purchaseHistory.getPuHiUserOrigin()));
        }

        return purchaseHistoryList;
    }
	
	public UserAccountBO getUserAccount(String userId) throws Exception{
		UserDO searchModel = new UserDO();
        searchModel.setUserId(Long.parseLong(userId));
        List<UserDO> userList = userMapper.selectUserList(searchModel);;
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        }

        UserDO user = userList.get(0);
        AccountBalanceBO balanceSearchModel = new AccountBalanceBO();
        balanceSearchModel.setUserId(user.getUserId());
        balanceSearchModel.setCpyType(user.getCpyType());
		
		return purchaseHistoryMapper.getUserAccount(balanceSearchModel);
	}
	
	public UserAccountBO getCardAccount(String cardId) throws Exception{
		return purchaseHistoryMapper.getCardAccount(cardId);
	}

	@Override
	public List<String> getTransNumberByInvoiceId(Long invoiceId) {
		return purchaseHistoryMapper.selectTransNumberByInvoiceId(invoiceId);
	}
}
