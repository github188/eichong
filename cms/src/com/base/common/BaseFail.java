package com.base.common;

import org.apache.commons.lang.StringUtils;

/**
 * Created by Haner on 2015/3/30.
 * 操作失败信息响应
 */
public class BaseFail extends BaseResult {


    public BaseFail(int status) {
        this.status = status;
        this.msg = GlobalSystem.getConfig(""+status);
        if(StringUtils.isEmpty(this.msg)){
            this.msg = FAILED_MSG;
        }
    }
    
    public BaseFail(String msg) {
        this.status = FAILED;
        this.msg = GlobalSystem.getConfig(msg);
        if(StringUtils.isEmpty(this.msg)){
            this.msg = msg;
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
