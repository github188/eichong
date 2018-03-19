package com.wanma.ims.common.domain;

import com.wanma.ims.util.PKUtil;

import java.util.Date;

public class MultiMediaDO {
    private String fileId;//文件ID

    private String businessType;//业务逻辑类型

    private String fileType;//文件类型

    private String referenceId;//业务关联ID

    private String relativePath;//文件存储文件夹（路径）

    private String fileName;//文件名称

    private String titleFlg;//

    private Date createDate;//创建时间

    private String createUser;//创建人

    private Date lastUpdateDate;//上一次修改时间

    private String lastUpdateUser;//上一次修改人

    public static MultiMediaDO valueOf(String businessType, String referenceId, String relativePath, String fileName, String titleFlg, String createUser) {
        MultiMediaDO multiMedia = new MultiMediaDO();
        multiMedia.setFileId(PKUtil.getUUID());
        multiMedia.setBusinessType(businessType);
        multiMedia.setReferenceId(referenceId);
        multiMedia.setRelativePath(relativePath);
        multiMedia.setFileName(fileName);
        multiMedia.setTitleFlg(titleFlg);
        multiMedia.setCreateDate(new Date());
        multiMedia.setCreateUser(createUser);
        return multiMedia;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTitleFlg() {
        return titleFlg;
    }

    public void setTitleFlg(String titleFlg) {
        this.titleFlg = titleFlg;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }
}