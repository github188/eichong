package com.wanma.ims.common.domain;

import com.wanma.ims.common.domain.base.BasicModel;

/**
 * Created by xyc on 2017/7/20.
 * 用户标签实体类
 */
public class UserTagDO extends BasicModel {

    private Long id;//主键

    private Long userId;//用户id

    private Long tagId;//标签id

    //以下为非持久化字段
    private String tagName;//标签名

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
