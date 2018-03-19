package com.wanma.ims.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wanma.ims.common.domain.InvoiceDO;
import com.wanma.ims.common.domain.OrderInvoiceDO;
import com.wanma.ims.mapper.FinInvoiceMapper;
import com.wanma.ims.service.FinInvoiceService;
import com.wanma.ims.service.OrderService;


@Service("InvoiceService")
public class FinInvoiceServiceImpl implements FinInvoiceService{
	@Autowired
	private FinInvoiceMapper finInvoiceMapper;
	@Autowired
	private OrderService orderService;
	@Override
	public long getUserInvoiceCount(InvoiceDO invoice) {
		return finInvoiceMapper.getUserInvoiceCount(invoice);
	}
	@Override
	public List<InvoiceDO> getUserInvoiceList(InvoiceDO invoice) {
		List<InvoiceDO> invouceList =  finInvoiceMapper.getUserInvoiceList(invoice);
		List<Long> invoiceIdList = new ArrayList<Long>();
		for(int i=0;i<invouceList.size();i++){
			invoiceIdList.add(invouceList.get(i).getPkInvoice());
		}
		List<OrderInvoiceDO> orderInvoiceDOList = orderService.getFav(invoiceIdList);
		HashMap<Long, OrderInvoiceDO> orderInvoiceDOMap = new HashMap<Long, OrderInvoiceDO>();
		for(OrderInvoiceDO OrderInvoice:orderInvoiceDOList){
			orderInvoiceDOMap.put(OrderInvoice.getInvoiceId(), OrderInvoice);
		}
		for(InvoiceDO invoiceDO:invouceList){
			if(orderInvoiceDOMap.get(invoiceDO.getPkInvoice())!=null){
				invoiceDO.setDiscountChangMoney(orderInvoiceDOMap.get(invoiceDO.getPkInvoice()).getFavMoney());
				invoiceDO.setDiscountServiceMoney(orderInvoiceDOMap.get(invoiceDO.getPkInvoice()).getFavServiceMoney());
			}
		}
		return invouceList;
	}
	@Override
	public InvoiceDO getFinInvoiceById(InvoiceDO invoice) {
		InvoiceDO invoiceDO = finInvoiceMapper.getFinInvoiceById(invoice);
		List<Long> invoiceIdList = new ArrayList<Long>();
		invoiceIdList.add(invoiceDO.getPkInvoice());
		List<OrderInvoiceDO> orderInvoiceDOList = orderService.getFav(invoiceIdList);
		if(orderInvoiceDOList!=null){
			invoiceDO.setDiscountChangMoney(orderInvoiceDOList.get(0).getFavMoney());
			invoiceDO.setDiscountServiceMoney(orderInvoiceDOList.get(0).getFavServiceMoney());
		}
		return invoiceDO;
	}
	@Override
	@Transactional
	public boolean confirmInvoice(InvoiceDO invoice) {
		return finInvoiceMapper.confirmInvoice(invoice)>0?true:false;
	}
	@Override
	@Transactional
	public boolean refuseInvoice(InvoiceDO invoice) {
		return finInvoiceMapper.refuseInvoice(invoice)>0?true:false;
	}
	@Override
	public long getCpyInvoiceCount(InvoiceDO invoice) {
		if(invoice.getInvoiceMode()==1){//充值开票
			return finInvoiceMapper.getCpyRechargeInvoiceCount(invoice);
		}else{
			return finInvoiceMapper.getCpyChargeInvoiceCount(invoice);
		}
		
		
	}
	@Override
	public List<InvoiceDO> getCpyInvoiceList(InvoiceDO invoice) {
		if(invoice.getInvoiceMode()==1){//充值开票
			return finInvoiceMapper.getCpyRechargeInvoiceList(invoice);
		}else{
			List<InvoiceDO> invouceList = finInvoiceMapper.getCpyChargeInvoiceList(invoice);
			 List<Long> invoiceIdList = new ArrayList<Long>();
				for(int i=0;i<invouceList.size();i++){
					invoiceIdList.add(invouceList.get(i).getPkInvoice());
				}
				List<OrderInvoiceDO> orderInvoiceDOList = orderService.getFav(invoiceIdList);
				HashMap<Long, OrderInvoiceDO> orderInvoiceDOMap = new HashMap<Long, OrderInvoiceDO>();
				for(OrderInvoiceDO OrderInvoice:orderInvoiceDOList){
					orderInvoiceDOMap.put(OrderInvoice.getInvoiceId(), OrderInvoice);
				}
				for(InvoiceDO invoiceDO:invouceList){
					invoiceDO.setDiscountChangMoney(orderInvoiceDOMap.get(invoiceDO.getPkInvoice()).getFavMoney());
					invoiceDO.setDiscountServiceMoney(orderInvoiceDOMap.get(invoiceDO.getPkInvoice()).getFavServiceMoney());
				}
				return invouceList;
		}
	
	}
	@Override
	@Transactional
	public boolean addCpyInvoice(InvoiceDO invoice, String ids) throws Exception  {
		return finInvoiceMapper.addCpyInvoice(invoice) >0?true:false;
	}
	@Override
	@Transactional
	public boolean modifyCpyInvoice(InvoiceDO invoice, String ids) {
		return finInvoiceMapper.modifyCpyInvoice(invoice)>0?true:false;
	}
	@Override
	@Transactional
	public boolean modifyFinInvoice(InvoiceDO invoice) {
		return finInvoiceMapper.modifyFinInvoice(invoice)>0?true:false;
	}
	
	
	
}
