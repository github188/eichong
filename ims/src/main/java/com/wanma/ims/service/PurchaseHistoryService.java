package com.wanma.ims.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.wanma.ims.common.domain.PurchaseHistoryDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.domain.bo.UserAccountBO;
import com.wanma.ims.common.dto.BaseResultDTO;


/**
 * 消费记录
 * @author bingo
 * @date 2017-07-28
 */
public interface PurchaseHistoryService {
	/**
	* <p>Description: 获取消费记录数量</p>
	* @param
	* @author bingo
	* @date 2017-07-28
	 */
	public Long getPurchaseHistoryCount(PurchaseHistoryDO purchaseHistory);
	
	/**
	* <p>Description: 获取消费记录数据</p>
	* @param
	* @author bingo
	* @date 2017-07-28
	 */
	public List<PurchaseHistoryDO> getPurchaseHistoryList(PurchaseHistoryDO purchaseHistory);
	
	/**
	* <p>Description: 导出消费记录信息</p>
	* @param
	* @author bingo
	* @date 2017-07-28
	 */
	public BaseResultDTO exportPurchaseHistory(HttpServletResponse response,
			PurchaseHistoryDO purchaseHistory, UserDO loginUser) throws Exception;
	
	/**
	* <p>Description: 用户首页中账单</p>
	* @param
	* @author bingo
	* @date 2017-07-31
	 */
	public UserAccountBO getUserAccount(String userId) throws Exception;
	
	/**
	* <p>Description: 卡首页中账单</p>
	* @param
	* @author bingo
	* @date 2017-08-01
	 */
	public UserAccountBO getCardAccount(String cardId) throws Exception;
	
	
	/**
	* <p>Description: 根据发票主键获取交易流水号</p>
	* @param
	* @author zhangchunyan
	* @date 2017-08-18
	 */
	public List<String> getTransNumberByInvoiceId(Long invoiceId);
}
