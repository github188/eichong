package com.wanma.ims.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wanma.ims.common.domain.FinAccountDO;
import com.wanma.ims.common.domain.OrderDO;
import com.wanma.ims.common.domain.OrderInvoiceDO;
import com.wanma.ims.common.domain.PurchaseHistoryDO;
import com.wanma.ims.common.dto.BaseResultDTO;
import com.wanma.ims.constants.WanmaConstants;
import com.wanma.ims.mapper.CarVinRelaMapper;
import com.wanma.ims.mapper.FinAccountMapper;
import com.wanma.ims.mapper.OrderMapper;
import com.wanma.ims.mapper.PurchaseHistoryMapper;
import com.wanma.ims.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private FinAccountMapper finAccountMapper;
	@Autowired
	private CarVinRelaMapper carVinRelaMapper;
	@Autowired
	private PurchaseHistoryMapper purchaseHistoryMapper;
	@Override
	public List<OrderInvoiceDO> getFav(List<Long> invoiceIdList) {
		OrderInvoiceDO o = new OrderInvoiceDO();
		o.setInvoiceIdList(invoiceIdList);
		return orderMapper.selectFav(o);
	}

	@Override
	public Long countOrderInvoiceDetail(OrderInvoiceDO orderInvoiceDO) {
		return orderMapper.countOrderInvoiceDetail(orderInvoiceDO);
	}
	
	@Override
	public List<OrderInvoiceDO> getOrderInvoiceDetail(OrderInvoiceDO orderInvoiceDO) {
		return orderMapper.selectOrderInvoiceDetail(orderInvoiceDO);
	}

	@Override
	public Long countOrderList(OrderDO orderDO) {
		return orderMapper.countOrderList(orderDO);
	}

	@Override
	public List<OrderDO> getOrderList(OrderDO orderDO) {
		return fill(orderMapper.selectOrderList(orderDO));
	}

	@Override
	public Long countThirdOrderList(OrderDO orderDO) {
		return orderMapper.countThirdOrderList(orderDO);
	}

	@Override
	public List<OrderDO> getThirdOrderList(OrderDO orderDO) {
		return fill(orderMapper.selectThirdOrderList(orderDO));
	}

    private List<OrderDO> fill(List<OrderDO> list){
    	Set<Long> accountIdSet = new HashSet<Long>();
    	for (OrderDO orderDO : list) {
    		accountIdSet.add(orderDO.getAccountId());
		}
    	Map<Long,FinAccountDO> finMap = new HashMap<Long,FinAccountDO>();
    	//根据资金账户ID查询资金账户
    	FinAccountDO fin = new FinAccountDO();
    	fin.setAccountIdList(new ArrayList<>(accountIdSet));
    	List<FinAccountDO> finList = finAccountMapper.getFinAccountList(fin);
    	for (FinAccountDO finAccountDO : finList) {
    		finMap.put(finAccountDO.getAccountId(), finAccountDO);
		}
    	//根据VIN码查询车牌号
    	for (OrderDO orderDO : list) {
			if(null != finMap.get(orderDO.getAccountId())){
				orderDO.setAccountNo(finMap.get(orderDO.getAccountId()).getAccountNO());
			}
		}
    	return list;
    }

	@Override
	@Transactional
	public BaseResultDTO manualAccountOrder(String ids,Integer status,String modifier) throws Exception{
		BaseResultDTO result = new BaseResultDTO();
		String[] orderIds = ids.split(",");
		for(int i=0;i<orderIds.length;i++){
			OrderDO order = new OrderDO();
			order.setOrderId(new Long(orderIds[i]));
			order.setOrderStatus(status);
			OrderDO newOrder = orderMapper.selectOrderById(order);
			if(null == newOrder){
				result.setSuccess(false);
				result.setErrorDetail("订单信息为空，请刷新页面重试");
				return result;
			}
			Long accountId = orderMapper.selectOrderRecordByTransNo(newOrder);
			if(null == accountId || accountId <= 0){
				result.setSuccess(false);
				result.setErrorDetail("订单资金账户为空，请确定数据信息");
				return result;
			}
			boolean flag = true;
			if(status == WanmaConstants.ORDER_STATUS_DONE){
				order.setOrderStatus(WanmaConstants.ORDER_STATUS_SUCCESS);
			}
			if(status == WanmaConstants.ORDER_STATUS_WAIT || status == WanmaConstants.ORDER_STATUS_EXCEPTION_WAIT){
				flag = false;
				order.setOrderStatus(WanmaConstants.ORDER_STATUS_EXCEPTION_DONE);
			}
			int num = orderMapper.updateOrderById(order);
			if(num > 0 && flag){
				//信用账户恢复金额
				FinAccountDO finAccount = new FinAccountDO();
				finAccount.setAllocationAmount(newOrder.getTotalMoney().toString());
				finAccount.setAccountId(accountId);
				finAccount.setModifier(modifier);
				finAccountMapper.modifyFinAccountBalance(finAccount);
			}else if(num > 0 && !flag){
				orderMapper.updateOrderRecordById(newOrder.getPkChargingRecord());
				//恢复金额
				FinAccountDO finAccount = new FinAccountDO();
				finAccount.setAllocationAmount(newOrder.getFrozenAmt());
				finAccount.setAccountId(accountId);
				finAccount.setModifier(modifier);
				finAccountMapper.modifyFinAccountBalance(finAccount);
				//增加交易流水
				PurchaseHistoryDO purchaseHistory = new PurchaseHistoryDO();
				purchaseHistory.setPuHiType(11);
				purchaseHistory.setPuHiPurchaseHistoryTime(new Date());
				purchaseHistory.setPuHiMonetary(new Double(newOrder.getFrozenAmt()));
				purchaseHistory.setPuHiUserId(newOrder.getUserId());
				purchaseHistory.setPuHiTransactionNumber(newOrder.getTransactionNumber());
				purchaseHistory.setAccountId(accountId);
				purchaseHistory.setPuHiConsumerRemark("未结算订单退款|异常订单退款");
				purchaseHistory.setPuHiChargeType(0);
				purchaseHistory.setPkInvoice(0L);
				purchaseHistory.setPuhiInvoiceStatus(0);
				purchaseHistory.setPuHiPurchaseContent(newOrder.getOrderCode());
				purchaseHistoryMapper.addPurchaseHistory(purchaseHistory);
			}else{
				result.setSuccess(false);
				result.setErrorDetail("结算失败");
			}
		}
		return result;
	}

	public List<OrderDO> getOrdersForBatch(OrderDO orderDO){
		return orderMapper.getOrdersForBatch(orderDO);
	}
}
