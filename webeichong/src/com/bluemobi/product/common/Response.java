package com.bluemobi.product.common;


/**
 * @author kim
 */
public interface Response<T> {

    public static final String SUCCESS = "OK";
    public static final String FAILED = "FAILED";
    public static final String FORBIDDEN = "FORBIDDEN";
    public static final String UNAUTHORIZED = "UNAUTHORIZED";

    public static final int DEFAULT_PAGE = 0;
    public static final int PAGE_SIZE = 10;

    public String getCode();

    public String getMessage();

    public T getResult();

}
