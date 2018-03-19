package com.bluemobi.product.model.common;

import com.bluemobi.product.utils.StringUtil;

public class DwzAjaxResult {
	private String statusCode;
	private String message;
	private String navTabId;
	private String rel;
	private String callbackType;
	private String forwardUrl;
	
	public DwzAjaxResult(String statusCode, String message, String navTabId,
			String callbackType, String forwardUrl) {
		this.statusCode = statusCode;
		this.message = StringUtil.toUTF8String(message);
		this.navTabId = navTabId;
		this.callbackType = callbackType;
		this.forwardUrl = forwardUrl;
	}
	public DwzAjaxResult(String statusCode, String message, String navTabId,
			String rel, String callbackType, String forwardUrl) {
		this.statusCode = statusCode;
		this.message = StringUtil.toUTF8String(message);
		this.navTabId = navTabId;
		this.rel = rel;
		this.callbackType = callbackType;
		this.forwardUrl = forwardUrl;
	}
	
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getNavTabId() {
		return navTabId;
	}
	public void setNavTabId(String navTabId) {
		this.navTabId = navTabId;
	}
	public String getCallbackType() {
		return callbackType;
	}
	public void setCallbackType(String callbackType) {
		this.callbackType = callbackType;
	}
	public String getForwardUrl() {
		return forwardUrl;
	}
	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}
	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
}
