package com.wanma.ims.service.parkinglock.constant;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by xyc on 2018/1/29.
 * 地锁平台错误常量
 */
public class PlatformErrorCode {

    public static final String SUCCESS_CODE = "0";

    public static final String SUCCESS_MSG = "调用第三方地锁平台接口成功";

    public static final String ERROR_CODE = "-1";

    public static final String ERROR_MSG = "调用第三方地锁平台接口异常！";

    /**
     * 慧泊金成功code和msg
     */
    public static final String WIPARKING_SUCCESS_CODE = "0";

    public static final String WIPARKING_SUCCESS_MSG = "成功";

    /**
     * 慧泊金地锁平台接口调用成功判断
     */
    private static Set<String> SUCCESS_CODE_SET = new HashSet<>(10);

    private static Set<String> SUCCESS_MSG_SET = new HashSet<>(10);

    public static String conventSuccessCode(String errorCode) {
        return SUCCESS_CODE_SET.contains(errorCode) ? SUCCESS_CODE : errorCode;
    }

    public static String conventSuccessMsg(String msg) {
        return SUCCESS_MSG_SET.contains(msg) ? SUCCESS_MSG : msg;
    }

    public static boolean isTransferSuccess(String errorCode, String errorMsg) {
        return SUCCESS_CODE.equals(errorCode) && SUCCESS_MSG.equals(errorMsg);
    }

    static {
        SUCCESS_CODE_SET.add(WIPARKING_SUCCESS_CODE);

        SUCCESS_MSG_SET.add(WIPARKING_SUCCESS_MSG);
    }
}
