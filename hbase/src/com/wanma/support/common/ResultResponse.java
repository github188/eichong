package com.wanma.support.common;

import com.alibaba.fastjson.JSON;

/**
 * Created by Haner on 2015/3/30. 结果集响应
 */
public class ResultResponse<T> extends Response<T> {

	protected int status;
	protected String msg;
	protected T data;
	protected Pager pager;

	public ResultResponse() {
		this.status = SUCCESS;
		this.msg = SUCCESS_MSG;
	}

	public ResultResponse(T data) {
		this.status = SUCCESS;
		this.msg = SUCCESS_MSG;
		this.data = data;
	}

	public ResultResponse(T data, Pager pager) {
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

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String getMsg() {
		return msg;
	}

	@Override
	public T getData() {
		return data;
	}
	

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
