package com.wanma.support.common;

/**
 * Created by Haner on 2015/3/30.
 * 成功信息响应
 */
public class SuccessResponse extends ResultResponse<String> {

    public SuccessResponse() {
        this.status = SUCCESS;
        this.msg = SUCCESS_MSG;
    }

    public SuccessResponse(String message) {
        this.status = SUCCESS;
        this.msg = message;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
