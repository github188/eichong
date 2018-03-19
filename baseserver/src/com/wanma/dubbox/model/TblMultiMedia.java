package com.wanma.dubbox.model;

import com.wanma.dubbox.model.common.BasicModel;

/**
 * 
 * tb_multi_media表
 * 
 * @author mew
 * 
 */
public class TblMultiMedia extends BasicModel {
	private static final long serialVersionUID = -8504782827678496759L;
	private String rfcId; // 关联主键
	private String fileId; //文件id
	private String[] fileIds;//文件id集合

	public String getRfcId() {
		return rfcId;
	}

	public void setRfcId(String rfcId) {
		this.rfcId = rfcId;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String[] getFileIds() {
		return fileIds;
	}

	public void setFileIds(String[] fileIds) {
		this.fileIds = fileIds;
	}
}