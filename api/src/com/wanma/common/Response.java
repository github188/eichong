package com.wanma.common;


/**
 * Created by Haner on 2015/3/30.
 */
public abstract class Response<T> {
    /**
     * 状态标示
     */
    public static final String SUCCESS = "OK";
    public static final String FAILED = "FAILED";
    public static final String ERROR = "ERROR";
    public static final String UNAUTHORIZED = "UNAUTHORIZED";
    /**
     * 信息反馈
     */
    public static final String SUCCESS_MSG = "操作成功!";
    public static final String FAILED_MSG = "操作失败!";
    public static final String ERROR_MSG = "系统错误!";
    public static final String UNAUTHORIZED_MSG = "未授权!";
    /**
     * 默认分页数值
     */
    public static final int DEFAULT_PAGE = 1;
    public static final int PAGE_SIZE = 10;

    /**
     * common abstract method
     *
     * @return
     */
    public abstract String getCode();

    public abstract String getMsg();

    public abstract T getData();

}
