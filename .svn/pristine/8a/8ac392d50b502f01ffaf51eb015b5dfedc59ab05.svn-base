package com.wanma.app.util;

import org.apache.commons.lang.StringUtils;

import com.bluemobi.product.common.MessageManager;

/**
 * Created by Haner on 2015/3/30. 操作失败信息响应
 */
public class FailedResponse extends ResultResponse<String> {

	public FailedResponse() {
		this.status = FAILED;
		this.msg = FAILED_MSG;
	}

	public FailedResponse(int status, String message) {
		this.status = status;
		this.msg = MessageManager.getMessageManager().getMessage(message);
		if (StringUtils.isEmpty(this.msg)) {
			this.msg = message;
		}
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
