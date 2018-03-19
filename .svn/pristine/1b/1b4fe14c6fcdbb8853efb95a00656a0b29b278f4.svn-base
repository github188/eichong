package com.wanma.ims.common.domain;

import java.util.Date;

/**
 * 配置文件实体类
 */
public class ConfigContentDO {
    private Integer id;//主键 ref表字段: pk_ConfigContent

    private Integer configParameterId;//配置参数ID ref表字段: coCo_ConfigParameterID

    private String content;//内容 ref表字段: coCo_Content

    private Integer status;//状态(0启用，1禁用) ref表字段: coCo_ConfigPStatus

    private Date createTime;//创建时间 ref表字段: coCo_Createdate

    private Date modifyTime;//修改时间 ref表字段: coCo_Updatedate

    private String memo;//备注 ref表字段: coCo_memo

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getConfigParameterId() {
        return configParameterId;
    }

    public void setConfigParameterId(Integer configParameterId) {
        this.configParameterId = configParameterId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
}