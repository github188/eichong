package com.wanma.ims.common.dto;

import java.util.HashMap;
import java.util.Map;

public class ResultDTO<T> extends BaseResultDTO{

	  private static final long serialVersionUID = -1434823240496058193L;
	  protected T module;
	  protected Map<String, String> checkErrorInfo = new HashMap<String, String>();

	  public ResultDTO() {
	  }

    public ResultDTO(boolean success, String resultCode, String errorDetail) {
        this.success = success;
        this.resultCode = resultCode;
        this.errorDetail = errorDetail;
    }

    public ResultDTO(T module) {
	    this.module = module;
	  }

	  public T getModule() {
	    return this.module;
	  }

	  public void setModule(T module) {
	    this.module = module;
	  }

	
	public Map<String, String> getCheckErrorInfo() {
		return checkErrorInfo;
	}

	
	public void setCheckErrorInfo(Map<String, String> checkErrorInfo) {
		this.checkErrorInfo = checkErrorInfo;
	}
      
	    
}
