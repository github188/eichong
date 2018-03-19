/**     
 * @Title:  AlipayController.java   
 * @Package com.wanma.controller.itf   
 * @Description:    TODO  
 * @author: Android_Robot     
 * @date:   2016年3月23日 下午4:29:55   
 * @version V1.0     
 */
package com.wanma.controller.itf;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wanma.alipay.AlipayConfig;
import com.wanma.alipay.AlipaySubmit;

/**
 * @author bc
 *
 */
@Controller
@RequestMapping(value = "/alipay")
public class AlipayController {
	private Log log = LogFactory.getLog(this.getClass());

	@RequestMapping(value = "/deposit", method = RequestMethod.POST)
	public String deposit(HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		// 商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no =new Date().getTime()+"3";
		// 订单名称，必填
		String subject = "充充币WAP";
		// 付款金额，必填
		String total_fee = request.getParameter("total_fee");
		// 用户ID
		String userId = request.getParameter("userId");
		// 收银台页面上，商品展示的超链接，必填
		String show_url = "12345";

		// 把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", AlipayConfig.service);
		sParaTemp.put("partner", AlipayConfig.partner);
		sParaTemp.put("seller_id", AlipayConfig.seller_id);
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", AlipayConfig.payment_type);
		sParaTemp.put("notify_url", AlipayConfig.notify_url);
		sParaTemp.put("return_url", AlipayConfig.return_url);
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("subject", subject);
		sParaTemp.put("total_fee", total_fee);
		sParaTemp.put("show_url", show_url);
		sParaTemp.put("body", userId);
		// 其他业务参数根据在线开发文档，添加参数.文档地址:https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.2Z6TSk&treeId=60&articleId=103693&docType=1
		// 如sParaTemp.put("参数名","参数值");

		// 建立请求
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
		model.addAttribute("content", sHtmlText);
		return "alipay/deposit";
	}
}
