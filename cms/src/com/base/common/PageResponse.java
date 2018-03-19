package com.base.common;

import com.alibaba.fastjson.JSON;

/**
 * Created by Haner on 2015/3/30.
 * 分页数据响应
 */
public class PageResponse<T>  {

    private String code;
    private String msg;
    private T data;
    private int begin = 0;
    private int count = 10; //分页大小
    private long countData;//总数目
    private int totalPage;//总页数
    private int pageStart;//页码循环开始
    private int pageEnd;//页码循环结束
    private int currentPage;//当前页
    public static final int DEFAULT_PAGE = 1;
    public static final int PAGE_SIZE = 10;
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

    private PageResponse() {}

    public PageResponse(Integer begin, Integer count) {
        if (count == null || count < 1) {
            count = PAGE_SIZE;
        }
        if (begin == null|| begin < 1) {
            begin = DEFAULT_PAGE;
        }
        this.begin = (begin - 1) * count;
        this.currentPage = begin;
        this.count = count;
        this.code = SUCCESS;
        this.msg = SUCCESS_MSG;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


    public void setMessage(String message) {
        this.msg = message;
    }

    public T getData() {
        calcStartAndEnd();
        return data;
    }

    public void setDate(T obj) {
        data = obj;
    }

    public void setCountData(long countData) {
        this.countData = countData;
    }

    public long getCountData() {
        return countData;
    }

    public int getPageStart() {
        return pageStart;
    }

    public int getPageEnd() {
        return pageEnd;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * 计算总页数
     *
     * @return
     */
    public int getTotalPage() {
        totalPage = (int) ((this.countData + this.count - 1) / this.count);
        return totalPage;
    }

    /**
     * 计算省略页码
     */
    private void calcStartAndEnd() {
        int pageCount = getTotalPage();
        int currentPage = this.currentPage;
        if (pageCount == 0) {
            return;
        }

        if (currentPage > pageCount) {
            currentPage = pageCount;
        }

        if (pageCount > 5) {
            //3
            if (currentPage + 3 > pageCount) {
                this.pageEnd = pageCount;
            } else {
                this.pageEnd = currentPage + 3;
            }
            //start 根据 end 倒推计算
            if (this.pageEnd - 5 <= 0) {
                this.pageStart = 1;
                this.pageEnd = 6;
            } else {
                this.pageStart = this.pageEnd - 5;
            }

        } else {
            this.pageStart = 1;
            this.pageEnd = pageCount;
        }
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public int getBegin() {
        return begin;
    }

    public int getCount() {
        return count;
    }

}
