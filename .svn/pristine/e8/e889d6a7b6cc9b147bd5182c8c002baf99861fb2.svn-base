package com.base.common;

import com.alibaba.fastjson.JSON;
import com.pub.model.Pager;

/**
 * @author wbc
 * @email watermelon8214@126.com
 * @date 2016-08-11 17:30:32
 * @version 1.0
 */
public class BaseResult {

	protected int status;
	protected String msg;
	protected Object data;
	protected Pager pager;
	/**
	 * 状态标示
	 */
	public static final int SUCCESS = 100;
	public static final int FAILED = 0;
	public static final int ERROR = 0;
	public static final int UNAUTHORIZED = 0;
	/**
	 * 信息反馈
	 */
	public static final String SUCCESS_MSG = "操作成功!";
	public static final String FAILED_MSG = "操作失败!";
	public static final String ERROR_MSG = "系统错误!";
	public static final String UNAUTHORIZED_MSG = "未授权!";

	public BaseResult() {
		this.status = SUCCESS;
		this.msg = SUCCESS_MSG;
	}

	public BaseResult(Object data) {
		this.status = SUCCESS;
		this.msg = SUCCESS_MSG;
		this.data = data;
	}

	public BaseResult(Object data, Pager pager) {
		this.status = SUCCESS;
		this.msg = SUCCESS_MSG;
		this.data = data;
		this.pager = pager;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public Object getData() {
		return data;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public String toString() {
		return JSON.toJSONString(this);
	}
}
