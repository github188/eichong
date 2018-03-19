/**
 * FileName:OAuthConst.java
 * Author: Administrator
 * Create: 2014年7月12日
 * Last Modified: 2014年7月12日
 * Version: V1.0 
 */
package com.bluemobi.product.common;

import javax.servlet.http.HttpServletRequest;

import com.bluemobi.product.utils.HttpServletRequestUtil;

/**
 * 第三方登录信息公共接口
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年7月12日
 */
public class OAuthCommon {

	public static final String CLIENTSECRET = "unity";

	/** 第三方登录设置文件:服务器路径 */
	public static String MYSERVER_URL = "oauth.server.myserver.server.url";
	/** 第三方登录设置文件:客户端ID */
	public static String MYSERVER_CLIENT_ID = "oauth.server.myserver.client.id";
	/** 第三方登录设置文件:请求类型 */
	public static String MYSERVER_RESPONSE_TYPE = "oauth.server.myserver.response.type";
	/** 第三方登录设置文件:操作方式 */
	public static String MYSERVER_SCOPE = "oauth.server.myserver.scope";
	/** 第三方登录设置文件:返回路径Action */
	public static String MYSERVER_CALLBACK_ACTION = "oauth.server.myserver.callback.action";
	/** 第三方登录设置文件:应用的appSecret信息 */
	public static String MYSERVER_CLIENT_SECRET= "oauth.server.myserver.client.secret";

	/** 第三方登录请求参数:客户端ID */
	public static String PARAM_CLIENT_ID = "client_id";
	/** 第三方登录请求参数:请求类型 */
	public static String PARAM_RESPONSE_TYPE = "response_type";
	/** 第三方登录请求参数:操作方式 */
	public static String PARAM_SCOPE = "scope";
	/** 第三方登录请求参数:返回路径Action */
	public static String PARAM_CALLBACK_ACTION = "redirect_uri";
	/** 第三方登录请求参数:应用的appSecret信息 */
	public static String PARAM_CLIENTSECRET = "clientSecret";

	public static String getRemoteAuthenUrl() {
		MessageManager messageManager = MessageManager.getMessageManager();
		StringBuffer remoteUri = new StringBuffer();

		String url = messageManager.getRemoteAuthenProperties(MYSERVER_URL);
		String clientId = messageManager
				.getRemoteAuthenProperties(MYSERVER_CLIENT_ID);
		String responseType = messageManager
				.getRemoteAuthenProperties(MYSERVER_RESPONSE_TYPE);
		String scope = messageManager.getRemoteAuthenProperties(MYSERVER_SCOPE);
		String callbackAction = "";

		callbackAction = getCallbackActionUrl();

		remoteUri.append(url);
		remoteUri.append("?");
		remoteUri.append(PARAM_CLIENT_ID);
		remoteUri.append("=");
		remoteUri.append(clientId);
		remoteUri.append("&");
		remoteUri.append(PARAM_CALLBACK_ACTION);
		remoteUri.append("=");
		remoteUri.append(callbackAction);
		remoteUri.append("&");
		remoteUri.append(PARAM_RESPONSE_TYPE);
		remoteUri.append("=");
		remoteUri.append(responseType);
		remoteUri.append("&");
		remoteUri.append(PARAM_SCOPE);
		remoteUri.append("=");
		remoteUri.append(scope);

		return remoteUri.toString();
	}

	public static String getClientId() {
		MessageManager messageManager = MessageManager.getMessageManager();
		String clientId = "";

		clientId = messageManager.getRemoteAuthenProperties(MYSERVER_CLIENT_ID);

		return clientId;
	}

	public static String getClientSecret() {
		MessageManager messageManager = MessageManager.getMessageManager();
		String clientSecret = "";

		clientSecret = messageManager.getRemoteAuthenProperties(MYSERVER_CLIENT_SECRET);

		return clientSecret;
	}

	public static String getCallbackActionUrl() {
		MessageManager messageManager = MessageManager.getMessageManager();
		String localServerUrl = "";

		localServerUrl = getLocalServerUrl();
		String callbackAction = messageManager
				.getRemoteAuthenProperties(MYSERVER_CALLBACK_ACTION);

		callbackAction = localServerUrl + callbackAction;

		return callbackAction;
	}

	public static String getLocalServerUrl() {

		HttpServletRequest request = HttpServletRequestUtil.getHttpRequest();
		String strBackUrl = "";

		strBackUrl = "http://" + request.getServerName() // 服务器地址
				+ ":" + request.getServerPort() // 端口号
				+ request.getContextPath(); // 项目名称
		/*
		 * strBackUrl = localServerUrl.replaceAll("/", "%2f"); strBackUrl =
		 * localServerUrl.replaceAll(":", "%3a");
		 */

		return strBackUrl;
	}

}
