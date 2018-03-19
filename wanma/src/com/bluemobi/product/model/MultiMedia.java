package com.bluemobi.product.model;

import com.bluemobi.product.model.common.BasicModel;

/**
 * 多媒体文件表
 * 
 * @author
 * 
 */
public class MultiMedia extends BasicModel {

	/**
	 * serial version ID
	 */
	private static final long serialVersionUID = -1359361049471323260L;

	/** 文件ID */
	private String fileId;

	/** 业务分类 */
	private String bussinessType;

	/** 文件 */
	private String fileType;

	/** 关联ID */
	private String referenceId;

	/** 相对路径 */
	private String relativePath;

	/** 标题图片 */
	private String titleFlg;

	/**
	 * 文件ID
	 * 
	 * @return 文件ID
	 */
	public String getFileId() {
		return fileId;
	}

	/**
	 * 文件ID
	 * 
	 * @param pFileId
	 *            文件ID
	 */
	public void setFileId(String pFileId) {
		this.fileId = pFileId;
	}

	/**
	 * 业务分类
	 * 
	 * @return 业务分类
	 */
	public String getBussinessType() {
		return bussinessType;
	}

	/**
	 * 业务分类
	 * 
	 * @param pBussinessType
	 *            业务分类
	 */
	public void setBussinessType(String pBussinessType) {
		this.bussinessType = pBussinessType;
	}

	/**
	 * 文件
	 * 
	 * @return 文件
	 */
	public String getFileType() {
		return fileType;
	}

	/**
	 * 文件
	 * 
	 * @param pFileType
	 *            文件
	 */
	public void setFileType(String pFileType) {
		this.fileType = pFileType;
	}

	/**
	 * 关联ID
	 * 
	 * @return 关联ID
	 */
	public String getReferenceId() {
		return referenceId;
	}

	/**
	 * 关联ID
	 * 
	 * @param pReferenceId
	 *            关联ID
	 */
	public void setReferenceId(String pReferenceId) {
		this.referenceId = pReferenceId;
	}

	/**
	 * 相对路径
	 * 
	 * @return 相对路径
	 */
	public String getRelativePath() {
		return relativePath;
	}

	/**
	 * 相对路径
	 * 
	 * @param pRelativePath
	 *            相对路径
	 */
	public void setRelativePath(String pRelativePath) {
		this.relativePath = pRelativePath;
	}

	/**
	 * 标题图片
	 * 
	 * @return 标题图片
	 */
	public String getTitleFlg() {
		return titleFlg;
	}

	/**
	 * 标题图片
	 * 
	 * @param pTitleFlg
	 *            标题图片
	 */
	public void setTitleFlg(String pTitleFlg) {
		this.titleFlg = pTitleFlg;
	}

}
