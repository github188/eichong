package com.wanma.web.support.common;

import com.bluemobi.product.common.MessageManager;
import org.apache.commons.lang.StringUtils;

/**
 * Created by Haner on 2015/3/30.
 * 操作失败信息响应
 */
public class FailedResponse extends ResultResponse<String> {

    public FailedResponse() {
        this.code = FAILED;
        this.msg = FAILED_MSG;
    }

    public FailedResponse(String message) {
        this.code = Response.FAILED;
        this.msg = MessageManager.getMessageManager().getMessage(message);
        if(StringUtils.isEmpty(this.msg)){
            this.msg = message;
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
