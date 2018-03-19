package com.wanma.model;

import java.io.Serializable;

/**
 * 
 * tbl_Favorite表
 * 
 * @author songjf
 * 
 */
public class TblFavorite implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4771415136660370413L;
	private java.lang.Integer pkFavorite; // 主键
	private java.lang.Integer favoProductid; // 产品Id
	private java.lang.Integer favoUserid; // 用户Id
	private java.util.Date favoCreatetime; // 收藏时间
	private java.util.Date favoCreatedate; // 创建时间
	private java.util.Date favoUpdatedate; // 修改时间

	/**
	 * 获取主键属性
	 * 
	 * @return pkFavorite
	 */
	public java.lang.Integer getPkFavorite() {
		return pkFavorite;
	}

	/**
	 * 设置主键属性
	 * 
	 * @param pkFavorite
	 */
	public void setPkFavorite(java.lang.Integer pkFavorite) {
		this.pkFavorite = pkFavorite;
	}

	/**
	 * 获取产品Id属性
	 * 
	 * @return favoProductid
	 */
	public java.lang.Integer getFavoProductid() {
		return favoProductid;
	}

	/**
	 * 设置产品Id属性
	 * 
	 * @param favoProductid
	 */
	public void setFavoProductid(java.lang.Integer favoProductid) {
		this.favoProductid = favoProductid;
	}

	/**
	 * 获取用户Id属性
	 * 
	 * @return favoUserid
	 */
	public java.lang.Integer getFavoUserid() {
		return favoUserid;
	}

	/**
	 * 设置用户Id属性
	 * 
	 * @param favoUserid
	 */
	public void setFavoUserid(java.lang.Integer favoUserid) {
		this.favoUserid = favoUserid;
	}

	/**
	 * 获取收藏时间属性
	 * 
	 * @return favoCreatetime
	 */
	public java.util.Date getFavoCreatetime() {
		return favoCreatetime;
	}

	/**
	 * 设置收藏时间属性
	 * 
	 * @param favoCreatetime
	 */
	public void setFavoCreatetime(java.util.Date favoCreatetime) {
		this.favoCreatetime = favoCreatetime;
	}

	/**
	 * 获取创建时间属性
	 * 
	 * @return favoCreatedate
	 */
	public java.util.Date getFavoCreatedate() {
		return favoCreatedate;
	}

	/**
	 * 设置创建时间属性
	 * 
	 * @param favoCreatedate
	 */
	public void setFavoCreatedate(java.util.Date favoCreatedate) {
		this.favoCreatedate = favoCreatedate;
	}

	/**
	 * 获取修改时间属性
	 * 
	 * @return favoUpdatedate
	 */
	public java.util.Date getFavoUpdatedate() {
		return favoUpdatedate;
	}

	/**
	 * 设置修改时间属性
	 * 
	 * @param favoUpdatedate
	 */
	public void setFavoUpdatedate(java.util.Date favoUpdatedate) {
		this.favoUpdatedate = favoUpdatedate;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("TblFavorite");
		sb.append("{pkFavorite=").append(pkFavorite);
		sb.append(", favoProductid=").append(favoProductid);
		sb.append(", favoUserid=").append(favoUserid);
		sb.append(", favoCreatetime=").append(favoCreatetime);
		sb.append(", favoCreatedate=").append(favoCreatedate);
		sb.append(", favoUpdatedate=").append(favoUpdatedate);
		sb.append('}');
		return sb.toString();
	}
}