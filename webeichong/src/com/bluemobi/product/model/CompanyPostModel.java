/**
 * FileName:CompanyPostModel.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.bluemobi.product.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.bluemobi.product.model.common.BasicListModel;

/**
 * 公司职位实体数据模型
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public class CompanyPostModel extends BasicListModel {

	/**
	 * serial version ID
	 */
	private static final long serialVersionUID = -6439237665759259855L;

	/** 公司ID */
	private String companyId;

	/** 公司名称 */
	private String companyName;

	/** 分类 */
	private String category;

	/** 职位ID */
	private String postId;

	/** 职位名称 */
	private String postName;

	/** 备注 */
	private String notes;

	/** 排序 */
	private long sortKey;

	/**
	 * 公司ID的取得。
	 * 
	 * @return 公司ID
	 */
	public String getCompanyId() {
		return companyId;
	}

	/**
	 * 公司ID的设定。
	 * 
	 * @param pCompanyId
	 *            公司ID
	 */
	public void setCompanyId(String pCompanyId) {
		this.companyId = pCompanyId;
	}

	/**
	 * 公司名称的取得。
	 * 
	 * @return 公司名称
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * 公司名称的设定。
	 * 
	 * @param pCompanyName
	 *            公司名称
	 */
	public void setCompanyName(String pCompanyName) {
		this.companyName = pCompanyName;
	}

	/**
	 * 职位ID的取得。
	 * 
	 * @return 职位ID
	 */
	public String getPostId() {
		return postId;
	}

	/**
	 * 职位ID的设定。
	 * 
	 * @param pPostId
	 *            职位ID
	 */
	public void setPostId(String pPostId) {
		this.postId = pPostId;
	}

	/**
	 * 职位名称的取得。
	 * 
	 * @return 职位名称
	 */
	public String getPostName() {
		return postName;
	}

	/**
	 * 职位名称的设定。
	 * 
	 * @param pPostName
	 *            职位名称
	 */
	public void setPostName(String pPostName) {
		this.postName = pPostName;
	}

	/**
	 * 备注的取得。
	 * 
	 * @return 备注
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * 备注的设定。
	 * 
	 * @param pNotes
	 *            备注
	 */
	public void setNotes(String pNotes) {
		this.notes = pNotes;
	}

	/**
	 * 排序的取得。
	 * 
	 * @return 排序
	 */
	public long getSortKey() {
		return sortKey;
	}

	/**
	 * 排序的设定。
	 * 
	 * @param pSortKey
	 *            排序
	 */
	public void setSortKey(long pSortKey) {
		this.sortKey = pSortKey;
	}

	/**
	 * 分类的取得。
	 * 
	 * @return 分类
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * 分类的设定。
	 * 
	 * @param pCategory
	 *            分类
	 */
	public void setCategory(String pCategory) {
		this.category = pCategory;
	}

	/**
	 * 从职位列表中删除指定职位对象
	 * 
	 * @param targetPostList
	 *            职位列表
	 * @param targetPostModel
	 *            指定职位对象
	 * @return 无
	 * @throws 无
	 */
	public static void removePost(List<CompanyPostModel> targetPostList,
			CompanyPostModel targetPostModel) {
		if (targetPostList == null || targetPostList.size() == 0
				|| targetPostModel == null
				|| targetPostModel.getPostId() == null
				|| targetPostModel.getCompanyId() == null) {
			return;
		}

		for (CompanyPostModel postModel : targetPostList) {
			if (StringUtils.equals(postModel.getPostId(),
					targetPostModel.getPostId())
					&& StringUtils.equals(postModel.getCompanyId(),
							targetPostModel.getCompanyId())) {
				targetPostList.remove(postModel);
				break;
			}
		}
	}

}
