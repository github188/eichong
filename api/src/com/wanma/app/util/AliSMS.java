package com.wanma.app.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.alibaba.fastjson.JSONObject;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class AliSMS {
	private static Log log = LogFactory.getLog(AliSMS.class);
	
	private static String APPKEY = "23459118";
	private static String SECRET = "7c2d3d3bf321244d95f3763138db053c";
	private static String URL = "http://gw.api.taobao.com/router/rest";
	
	/**
	 * 阿里大于短信发送
	 * @param phoneNum 接受手机号
	 * @param smsId 短信模板id
	 * @param parValue json参数字符串：{"name":"张三"}
	 * @return
	 */
	public static boolean sendAliSMS(String phoneNum,String smsId,String parValue){
		
		TaobaoClient client = new DefaultTaobaoClient(URL, APPKEY, SECRET);
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
	
	public static void main(String[] args){
		/*AliSMS a = new AliSMS();
		a.sendAliSMS("18506587178", "SMS_15040115", "{\"code\":\"123\",\"product\":\"aichong\"}");*/
		
		String back = "{\"alibaba_aliqin_fc_sms_num_send_response\":"
				+ "{\"result\":{\"err_code\":\"0\",\"model\":\"103108886119^1103955653484\",\"success\":true},\"request_id\":\"3jvmqc7gt6wj\"}}";
		JSONObject jo = JSONObject.parseObject(back);
		JSONObject jo1 = jo.getJSONObject("alibaba_aliqin_fc_sms_num_send_response");
		JSONObject jo2 = jo1.getJSONObject("result");
		String code = jo.get("err_code").toString();
		System.out.println(code);
		
	}
}
