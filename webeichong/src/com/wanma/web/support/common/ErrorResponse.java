package com.wanma.web.support.common;

import com.bluemobi.product.common.MessageManager;
import org.apache.commons.lang.StringUtils;

/**
 * Created by Haner on 2015/3/30.
 * 错误信息响应
 */
public class ErrorResponse extends ResultResponse {

    public ErrorResponse() {
        this.code = ERROR;
        this.msg = ERROR_MSG;
    }

    /**
     * 错误消息设置
     *
     * @param msgKey
     */
    public ErrorResponse(String msgKey) {
        this.code = ERROR;
        this.msg = MessageManager.getMessageManager().getMessage(msgKey);
        if(StringUtils.isEmpty(this.msg)){
            this.msg = msg;
        }
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
