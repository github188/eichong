/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.app.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanma.model.TblInvoice;


public interface InvoiceService {

	/**
	 * 用户的可开票记录
	 * @param params
	 * 			uId 用于id，startTime 起始时间
	 * @return
	 */
	public List<HashMap<String, Object>> enableInvoiceList(HashMap<String, Object> params);
	 
	/**
	 * 获取用户的开票记录
	 * @param params
	 * 			uId 用户id,pageNumber 页码，pageNum 每页数据量
	 * @return
	 */
	public List<HashMap<String, Object>> invoicedList(HashMap<String, Object> params);
	
	/**
	 * 获取发票详情
	 * @param iId 发票id
	 * @return
	 */
	public HashMap<String, Object> invoiceDetail(long iId);
	
	/**
	 * 检查用户是否看过开票流程
	 * @param uId 用户id
	 * @return
	 */
	public HashMap<String, Integer> getInvoiceCheck(int uId);
	
	/**
	 * 添加用户查看开票流程记录
	 * @param uId 用户id
	 * @return
	 */
	public void addInvoiceCheck(int uId);
	
	/**
	 * 获取发票配置信息
	 * @param configType 配置类型:30发票内容31邮费金额
	 * @return
	 */
	public List<HashMap<String, Object>> invoiceConfig(HashMap<String, Object> params);
	/**
	 * 开发票
	 * @param 
	 * @return
	 */
	public Long saveInvoice(TblInvoice invoice);

	/**
	 * 更新发票支付方式
	 * @param payMode
	 */
	public void updatePayMode(int ivPayFreight, long ivId, BigDecimal ivFreightAmount, int ivStatus);
}
