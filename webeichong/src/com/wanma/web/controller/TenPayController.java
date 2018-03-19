package com.wanma.web.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanma.tenpay.ClientResponseHandler;
import com.wanma.tenpay.ConstantUtil;
import com.wanma.tenpay.RequestHandler;
import com.wanma.tenpay.ResponseHandler;
import com.wanma.tenpay.TenpayHttpClient;
import com.wanma.web.service.WebUserinfoService;

/**
 * 微信支付Controller
 * @author 
 * @修改日期 2015-6-4 下午5:29:53
 */
@Controller
@RequestMapping(value="/tenpay")
public class TenPayController {
	 private static Log log = LogFactory.getLog(TenPayController.class);
	 /**
     * 用户信息业务处理对象
     */
    @Autowired
    private WebUserinfoService userinfoService; 
	
	@RequestMapping(value="/notify" ,method = RequestMethod.POST)  
	@ResponseBody
    public String notify(HttpServletRequest request,HttpServletResponse response){
		log.error("进入微信NOTIFY....");
		try {
			//---------------------------------------------------------
			//财付通支付通知（后台通知）示例，商户按照此文档进行开发即可
			//---------------------------------------------------------
			//商户号
			String partner = ConstantUtil.WX_PARTNER;

			//密钥
			String key = ConstantUtil.WX_PARTNER_KEY;
			Map m = request.getParameterMap();
			log.error("Map size:"+m.size());
			log.error("sign:"+request.getParameter("sign"));
			Iterator it = m.keySet().iterator();
			while (it.hasNext()) {
				String k = (String) it.next();
				String v = ((String[]) m.get(k))[0];			
				//debug信息
				log.error( "controller参数=> KEY:" + k +
						",,,,,value:" + v);
			}
			//创建支付应答对象
			ResponseHandler resHandler = new ResponseHandler(request, response);
			
			resHandler.setKey(key);

			//判断签名
			if(resHandler.isTenpaySign()) {
				
				//通知id
				String notify_id = resHandler.getParameter("notify_id");
				
				//创建请求对象
				RequestHandler queryReq = new RequestHandler(null, null);
				//通信对象
				TenpayHttpClient httpClient = new TenpayHttpClient();
				//应答对象
				ClientResponseHandler queryRes = new ClientResponseHandler();
				
				//通过通知ID查询，确保通知来至财付通
				queryReq.init();
				queryReq.setKey(key);
				queryReq.setGateUrl("https://gw.tenpay.com/gateway/verifynotifyid.xml");
				queryReq.setParameter("partner", partner);
				queryReq.setParameter("notify_id", notify_id);
				
				//通信对象
				httpClient.setTimeOut(5);
				//设置请求内容
				httpClient.setReqContent(queryReq.getRequestURL());
				log.error("queryReq:" + queryReq.getRequestURL());
				//后台调用
				if(httpClient.call()) {
					//设置结果参数
					queryRes.setContent(httpClient.getResContent());
					log.error("queryRes:" + httpClient.getResContent());
					queryRes.setKey(key);
						
						
					//获取返回参数
					String retcode = queryRes.getParameter("retcode");
					String trade_state = queryRes.getParameter("trade_state");
				
					String trade_mode = queryRes.getParameter("trade_mode");
						
					//判断签名及结果
					if(queryRes.isTenpaySign()&& "0".equals(retcode) && "0".equals(trade_state) && "1".equals(trade_mode)) {
						log.error("订单查询成功");
						//取结果参数做业务处理				
						log.error("out_trade_no:" + queryRes.getParameter("out_trade_no")+
								" transaction_id:" + queryRes.getParameter("transaction_id"));
						log.error("trade_state:" + queryRes.getParameter("trade_state")+
								" total_fee:" + queryRes.getParameter("total_fee"));
					        //如果有使用折扣券，discount有值，total_fee+discount=原请求的total_fee
						log.error("discount:" + queryRes.getParameter("discount")+
								" time_end:" + queryRes.getParameter("time_end"));
						//------------------------------
						//处理业务开始
						//------------------------------
						
						//处理数据库逻辑
						//Boolean flag=userinfoService.addReChargeRecordForTenpay(request);
						
						//------------------------------
						//处理业务完毕
						//------------------------------
						//resHandler.sendToCFT("Success");
						return "Success";
					}
					else{
							//错误时，返回结果未签名，记录retcode、retmsg看失败详情。
							log.error("查询验证签名失败或业务错误");
							log.error("retcode:" + queryRes.getParameter("retcode")+
									" retmsg:" + queryRes.getParameter("retmsg"));
					}
				
				} else {

					log.error("后台调用通信失败");
						
					log.error(httpClient.getResponseCode());
					log.error(httpClient.getErrInfo());
					//有可能因为网络原因，请求已经处理，但未收到应答。
				}
			}
			else{
				log.error("通知签名验证失败");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "你好呀";
	}
}
