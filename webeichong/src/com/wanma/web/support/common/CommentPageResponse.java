package com.wanma.web.support.common;

import com.alibaba.fastjson.JSON;
import com.wanma.model.Userinfo;

/**
 * Created by Haner on 2015/3/30.
 * 分页数据响应
 */
@SuppressWarnings("rawtypes")
public class CommentPageResponse<T> {

	private PageResponse pager;
    private T replyData;
    private String userImage;

    public String toString() {
        return JSON.toJSONString(this);
    }

	public PageResponse getPager() {
		return pager;
	}

	public void setPager(PageResponse pager) {
		this.pager = pager;
	}

	public T getReplyData() {
		return replyData;
	}

	public void setReplyData(T replyData) {
		this.replyData = replyData;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

}
