package com.wanma.app.util.wxhandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONObject;
import com.wanma.app.util.PayConfig;
import com.wanma.app.util.WXUtil;
import com.wanma.net.ApiGateConnectManager;

public class AccessTokenRequestHandler extends RequestHandler {

	private static Log log = LogFactory.getLog(AccessTokenRequestHandler.class);
	
	public AccessTokenRequestHandler(HttpServletRequest request,
			HttpServletResponse response) {
		super(request, response);
	}

	private static String access_token = "";

	/**
	 * 获取凭证access_token
	 * @return
	 */
	public static String getAccessToken() {
		//if ("".equals(access_token)) {// 如果为空直接获取
			return getTokenReal();
		/*}else if (tokenIsExpire(access_token)) {// 如果过期重新获取
			return getTokenReal();
		}
		return access_token;*/
	}

	/**
	 * 实际获取access_token的方法
	 * @return
	 */
	protected static String getTokenReal() {
		String requestUrl = PayConfig.WX_TOKENURL + "?grant_type=" + PayConfig.WX_GRANT_TYPE + "&appid="
				+ PayConfig.WX_APP_ID + "&secret=" + PayConfig.WX_APP_SECRET;
		String resContent = "";
		TenpayHttpClient httpClient = new TenpayHttpClient();
		httpClient.setMethod("GET");
		httpClient.setReqContent(requestUrl);
		if (httpClient.call()) {
			resContent = httpClient.getResContent();
			if (resContent.indexOf(PayConfig.WX_ACCESS_TOKEN) > 0) {
				JSONObject jsonO = JSONObject.parseObject(resContent);
				access_token = jsonO.getString(PayConfig.WX_ACCESS_TOKEN);
			} else {
				log.error("获取access_token值返回错误！！！");
			}
		} else {
			log.error("后台调用通信失败");
			log.error(httpClient.getResponseCode());
			log.error(httpClient.getErrInfo());
			// 有可能因为网络原因，请求已经处理，但未收到应答。
		}

		return access_token;
	}

	/**
	 * 判断传递过来的参数access_token是否过期
	 * @param access_token
	 * @return
	 */
	/*private static boolean tokenIsExpire(String access_token) {
		boolean flag = false;
		PrepayIdRequestHandler wxReqHandler = new PrepayIdRequestHandler(null, null);
		wxReqHandler.setParameter("appid", PayConfig.WX_APP_ID);
		wxReqHandler.setParameter("appkey",PayConfig.WX_APP_KEY);
		wxReqHandler.setParameter("noncestr", WXUtil.getNonceStr());
		wxReqHandler.setParameter("package", PayConfig.WX_packageValue);
		wxReqHandler.setParameter("timestamp", WXUtil.getTimeStamp());
		wxReqHandler.setParameter("traceid", PayConfig.WX_traceid);

		// 生成支付签名
		String sign = wxReqHandler.createSHA1Sign();
		wxReqHandler.setParameter("app_signature", sign);
		wxReqHandler.setParameter("sign_method", PayConfig.WX_SIGN_METHOD);
		String gateUrl = PayConfig.WX_GATEURL + access_token;
		wxReqHandler.setGateUrl(gateUrl);

		// 发送请求
		String accesstoken = wxReqHandler.sendAccessToken();
		if (PayConfig.WX_EXPIRE_ERRCODE.equals(accesstoken) || PayConfig.WX_FAIL_ERRCODE.equals(accesstoken))
			flag = true;
		return flag;
	}*/

}
