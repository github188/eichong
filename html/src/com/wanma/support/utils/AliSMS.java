package com.wanma.support.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONObject;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class AliSMS {
	private static Log log = LogFactory.getLog(AliSMS.class);
	
	private static String appKey = "23459118";
	private static String secret = "7c2d3d3bf321244d95f3763138db053c";
	private static String url = "http://gw.api.taobao.com/router/rest";
	
	private AliSMS(){
		
	}
	
	/**
	 * 阿里大于短信发送
	 * @param phoneNum 接受手机号
	 * @param smsId 短信模板id
	 * @param parValue json参数字符串：{"name":"张三"}
	 * @return
	 */
	public static boolean sendAliSMS(String phoneNum,String smsId,String parValue){
		
		TaobaoClient client = new DefaultTaobaoClient(url, appKey, secret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setSmsType("normal");
		req.setSmsFreeSignName("爱充网");
		req.setSmsParamString(parValue);
		req.setRecNum(phoneNum);
		req.setSmsTemplateCode(smsId);
		try{
			AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
			//解析发送结果
			JSONObject jo = JSONObject.parseObject(rsp.getBody());
			JSONObject jo1 = jo.getJSONObject("alibaba_aliqin_fc_sms_num_send_response");
			JSONObject jo2 = jo1.getJSONObject("result");
			String code = jo2.getString("err_code");
			if("0".equals(code)) return true;
			log.error("发送短信失败，返回码：" + code);
			return false;
		}catch(Exception e){
			log.error("发送阿里短信失败");
			log.error(e.getMessage());
			return false;
		}
		
	}
	
}
