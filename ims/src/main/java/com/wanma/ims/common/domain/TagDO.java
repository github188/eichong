package com.wanma.ims.common.domain;

import com.wanma.ims.common.domain.base.BasicModel;

/**
 * Created by xyc on 2017/7/20.
 * 标签实体类
 */
public class TagDO extends BasicModel {

    private Long id;//主键，标签ID

    private Integer type;//标签类型，0 用户标签

    private String name;//标签名

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
