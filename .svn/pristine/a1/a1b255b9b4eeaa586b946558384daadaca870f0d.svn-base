package com.wanma.app.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.common.AppPager;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.wanma.app.service.AppUserinfoService;
import com.wanma.app.service.InvoiceService;
import com.wanma.app.util.DateUtil;
import com.wanma.model.TblInvoice;

@Controller
@RequestMapping("/app/invoice")
public class InvoiceController {
	private static Log log = LogFactory.getLog(InvoiceController.class);
	
	/**
	 * 获取用户90天内的可开票预约、充电记录
	 * @param uId
	 * @return
	 */
	@RequestMapping("/enableInvoiceList")
	@ResponseBody
	public String getEnableInvoiceList(int uId){
		log.info("*************获取用户" + uId + "的可用发票列表****************");
		if(0 == uId){
			return new AccessErrorResult(1050, "error.msg.miss.parameter").toString();
		}
		
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String,Object>>();
		try{
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("uId", uId);
			params.put("startTime", DateUtil.addDay(DateUtil.getDate(),-90));
			list = invoiceService.enableInvoiceList(params);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return new AccessErrorResult(1000,"error.msg.invalid.parameter").toString();
		}
		return new AccessSuccessResult(list).toString();
	}
	
	/**
	 * 获取用户的开票记录
	 * @param params
	 * 			uId 用户id
	 * @param pager
	 * 			pageNumber 页码，pageNum 每页数据量
	 * @return
	 */
	@RequestMapping("/invoicedList")
	@ResponseBody
	public String invoicedList(@RequestParam HashMap<String, Object> params,AppPager pager){
		if(StringUtils.isEmpty(params.get("uId"))){
			return new AccessErrorResult(1050, "error.msg.miss.parameter").toString();
		}
		
		params.put("pageNum", pager.getPageNum());
		params.put("pageNumber", pager.getPageNumber());
		
		return AccessSuccessResult.nAccessSuccessResult(invoiceService.invoicedList(params)).toString();
	}
	
	/**
	 * 获取发票详情
	 * @param iId 发票id
	 * @return
	 */
	@RequestMapping("/invoiceDetail")
	@ResponseBody
	public String invoiceDetail(long iId){
		if(0 == iId){
			return new AccessErrorResult(1050, "error.msg.miss.parameter").toString();
		}
		
		return AccessSuccessResult.nAccessSuccessResult(invoiceService.invoiceDetail(iId)).toString();
	}
	
	@RequestMapping("/invoicePurList")
	@ResponseBody
	public String invoicePurList(long iId) {
		if (0 == iId) {
			return new AccessErrorResult(1050, "error.msg.miss.parameter")
					.toString();
		}

		return AccessSuccessResult.nAccessSuccessResult(userinfoService.invoicePurList(iId)).toString();
	}

	/**
	 * 获取发票配置信息
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping("/invoiceConfig")
	@ResponseBody
	public String invoiceConfig() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HashMap<String, Object> params = new HashMap<String, Object>();
		// 发票内容
		params.put("configType", 30);
		List<HashMap<String, Object>> list = invoiceService
				.invoiceConfig(params);
		resultMap.put("invoiceContent", list.get(0).get("configValue"));
		// 发票金额
		params.put("configType", 31);
		list = invoiceService.invoiceConfig(params);
		resultMap.put("invoiceAmount", list.get(0).get("configValue"));
		return AccessSuccessResult.nAccessSuccessResult(resultMap).toString();
	}

	/**
	 * 添加发票
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping("/saveInvoice")
	@ResponseBody
	public String saveInvoice(@RequestParam HashMap<String, Object> params) {
		TblInvoice invoice=new TblInvoice();
		// 发票类型：0-个人开票 ，1-公司开票
		if (!params.containsKey("ivType")) {
			return new AccessErrorResult(1050, "miss ivType").toString();
		}
		invoice.setIvInvoiceType(new Integer((String)params.get("ivType")));
		// 发票内容
		if (!params.containsKey("ivContent")) {
			return new AccessErrorResult(1050, "miss ivContent").toString();
		}
		invoice.setIvInvoiceContent((String)params.get("ivContent"));
		// 发票台头
		if (!params.containsKey("ivCompanyName")) {
			return new AccessErrorResult(1050, "miss ivCompanyName").toString();
		}
		invoice.setIvCompanyName((String)params.get("ivCompanyName"));
		// 发票金额
		if (!params.containsKey("ivAmount")) {
			return new AccessErrorResult(1050, "miss ivAmount").toString();
		}
		if (!isDouble(((String) params.get("ivAmount")))) {
			return new AccessErrorResult(1050, "error ivAmount").toString();
		}
		invoice.setIvInvoiceAmount(new Double((String)params.get("ivAmount")));
		// 收件人姓名
		if (!params.containsKey("ivRecipients")) {
			return new AccessErrorResult(1050, "miss ivRecipients").toString();
		}
		invoice.setIvRecipients((String)params.get("ivRecipients"));
		// 收件人手机号码
		if (!params.containsKey("ivRecipientsNumber")) {
			return new AccessErrorResult(1050, "miss ivRecipientsNumber")
					.toString();
		}
		invoice.setIvRecipientsNumber((String)params.get("ivRecipientsNumber"));
		// 所属省份代码
		if (!params.containsKey("ivProvince")) {
			return new AccessErrorResult(1050, "miss ivProvince").toString();
		}
		invoice.setIvOwnProvinceCode((String)params.get("ivProvince"));
		// 所属城市代码
		if (!params.containsKey("ivCity")) {
			return new AccessErrorResult(1050, "miss ivCity").toString();
		}
		invoice.setIvOwnCityCode((String)params.get("ivCity"));
		// 所属区县代码
		if (!params.containsKey("ivCounty")) {
			return new AccessErrorResult(1050, "miss ivCounty").toString();
		}
		invoice.setIvOwnCountyCode((String)params.get("ivCounty"));
		// 收件人地址
		if (!params.containsKey("ivAddress")) {
			return new AccessErrorResult(1050, "miss ivAddress").toString();
		}
		invoice.setIvConsigneeAddress((String)params.get("ivAddress"));
		// 用户ID
		if (!params.containsKey("ivUserId")) {
			return new AccessErrorResult(1050, "miss ivUserId").toString();
		}
		invoice.setIvUserId(new Long((String)params.get("ivUserId")));
		// 开票支付类型
		if (!params.containsKey("ivPayFreight")) {
			return new AccessErrorResult(1050, "miss ivPayFreight")
					.toString();
		}
		invoice.setIvPayFreight(new Integer((String)params.get("ivPayFreight")));
		// 开票关联消费记录
		if (!params.containsKey("ivRecords")) {
			return new AccessErrorResult(1050, "miss ivRecords")
					.toString();
		}
		invoice.setIvRecords((String)params.get("ivRecords"));
		//运费
		if(!StringUtils.isEmpty(params.get("ivFreightAmount"))){
			invoice.setIvFreightAmount(new BigDecimal(params.get("ivFreightAmount").toString()));
		}
		if ("1".equals((String) params.get("ivType"))) {
			// 纳税人识别号
			if (!params.containsKey("ivTaxpayerNumber")) {
				return new AccessErrorResult(1050, "miss ivTaxpayerNumber")
						.toString();
			}
			invoice.setIvTaxpayerNumber((String)params.get("ivTaxpayerNumber"));
			// 公司地址
			if (!params.containsKey("ivCompanyAddress")) {
				return new AccessErrorResult(1050, "miss ivCompanyAddress")
						.toString();
			}
			invoice.setIvCompanyAddress((String)params.get("ivCompanyAddress"));
			// 公司电话
			if (!params.containsKey("ivPhoneNumber")) {
				return new AccessErrorResult(1050, "miss ivPhoneNumber")
						.toString();
			}
			invoice.setIvPhoneNumber((String)params.get("ivPhoneNumber"));
			// 开户银行名称
			if (!params.containsKey("ivBankName")) {
				return new AccessErrorResult(1050, "miss ivBankName")
						.toString();
			}
			invoice.setIvBankName((String)params.get("ivBankName"));
			// 开户银行账号
			if (!params.containsKey("ivBankAccount")) {
				return new AccessErrorResult(1050, "miss ivBankAccount")
						.toString();
			}
			invoice.setIvBankAccount((String)params.get("ivBankAccount"));
		}else{
//			params.put("ivTaxpayerNumber", "");
//			params.put("ivCompanyAddress", "");
//			params.put("ivPhoneNumber", "");
//			params.put("ivBankName", "");
//			params.put("ivBankAccount", "");
			invoice.setIvTaxpayerNumber("");
			invoice.setIvCompanyAddress("");
			invoice.setIvPhoneNumber("");
			invoice.setIvBankName("");
			invoice.setIvBankAccount("");
		}
		//发票号码
//		params.put("ivInvoiceNumber", "");
//		params.put("ivTrackNumber", "");
//		params.put("ivStatus", 0);
//		params.put("yourId", 0);
		invoice.setIvInvoiceNumber("");
		invoice.setIvTrackNumber("");
		if(invoice.getIvPayFreight()==4){
			invoice.setIvInvoiceStatus(0);
		}else{
			invoice.setIvInvoiceStatus(2);
		}
		try {
			if(!StringUtils.isEmpty(params.get("pkInvoice"))){
				invoiceService.updatePayMode(invoice.getIvPayFreight(), Integer.parseInt(params.get("pkInvoice").toString()),
						invoice.getIvFreightAmount(),invoice.getIvInvoiceStatus());
			}else{
				invoiceService.saveInvoice(invoice);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return new AccessErrorResult(2001, "开发票失败，请稍后再试").toString();
		}
		
		return AccessSuccessResult.nAccessSuccessResult(invoice.getPkInvoice()).toString();
	}
	
	/**
	 * 添加用户查看开票流程记录
	 * @param uId 用户id
	 * @return
	 */
	@RequestMapping("/addInvoiceCheck")
	@ResponseBody
	public String addInvoiceCheck(int uId){
		if(0 == uId){
			return new AccessErrorResult(1050, "error.msg.miss.parameter").toString();
		}
		
		HashMap<String, Integer> map = invoiceService.getInvoiceCheck(uId);
		if(null == map || map.isEmpty()){
			invoiceService.addInvoiceCheck(uId);
		}
		return AccessSuccessResult.nAccessSuccessResult("").toString();
	}
	
	/**
	 * 检查用户是否看过开票流程
	 * @param uId 用户id
	 * @return
	 */
	@RequestMapping("/getInvoiceCheck")
	@ResponseBody
	public String getInvoiceCheck(int uId){
		if(0 == uId){
			return new AccessErrorResult(1050, "error.msg.miss.parameter").toString();
		}
		HashMap<String, Integer> map = invoiceService.getInvoiceCheck(uId);
		if(null == map || map.isEmpty()){
			map = new HashMap<String, Integer>();
			map.put("ic", 0);
		}
		return AccessSuccessResult.nAccessSuccessResult(map).toString();
	}

	public static boolean isDouble(String s) {
		String pattern = "\\-?[0-9]+(.[0-9]+)?";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(s);
		return m.matches();
	}
	
	@Autowired
	InvoiceService invoiceService;
	@Autowired
	AppUserinfoService userinfoService;
}
