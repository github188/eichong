package com.wanma.ims.controller.result;

import java.io.Serializable;

import com.wanma.ims.common.domain.base.Pager;
import com.wanma.ims.constants.ResultCodeConstants;


public class JsonResult implements Serializable{
	private static final long serialVersionUID = 3291415409326907669L;
	protected boolean success = true;
	protected String status;
	protected String msg;
	protected Object dataObject;
	protected Pager pager;

	public JsonResult(){
		 setSuccess(true);
	     setStatus(ResultCodeConstants.SUCCESS);
	     setMsg("操作成功");
	}

	public JsonResult(Object object){
		setStatus(ResultCodeConstants.SUCCESS);
		setMsg("操作成功");
//		if (object instanceof Boolean) {
//			setSuccess(((Boolean) object).booleanValue());
//		} else {
			setDataObject(object);
//		}
	}

	 public JsonResult(boolean flag, String status, String msg) {
		 setSuccess(flag);
		 setStatus(status);
		 if (flag) {
			setDataObject(msg);
		 } else {
			setMsg(msg);
		 }
	 }

	public boolean isSuccess() {
		return success;
	}

	
	public void setSuccess(boolean success) {
		this.success = success;
	}

	
	public String getStatus() {
		return status;
	}

	
	public void setStatus(String status) {
		this.status = status;
	}

	
	public String getMsg() {
		return msg;
	}

	
	public void setMsg(String msg) {
		this.msg = msg;
	}

	
	public Object getDataObject() {
		return dataObject;
	}

	
	public void setDataObject(Object dataObject) {
		this.dataObject = dataObject;
	}

	
	public Pager getPager() {
		return pager;
	}

	
	public void setPager(Pager pager) {
		this.pager = pager;
	}
    
}
