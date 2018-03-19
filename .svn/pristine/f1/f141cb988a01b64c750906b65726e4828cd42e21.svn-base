package com.wanma.ims.controller.result;

import com.wanma.ims.core.GlobalSystem;

public class JsonException extends JsonResult {
    private static final long serialVersionUID = -1556451407092290067L;

    public JsonException() {
        this.success = false;
        this.status = GlobalSystem.getConfig("exception");
        this.msg = GlobalSystem.getConfig("error.msg.exception");
    }

    public JsonException(String msg) {
        this.success = false;
        this.status = GlobalSystem.getConfig("exception");
        this.msg = msg;
    }

}
