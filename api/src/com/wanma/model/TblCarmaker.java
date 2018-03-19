package com.wanma.model;

import java.io.Serializable;

import com.bluemobi.product.model.common.BasicListAndMutiFile;

/**
 * 
 * Tbl_CarMaker表
 * @author wubc
 * 2015-05-27	
 */
public class TblCarmaker extends BasicListAndMutiFile implements Serializable {
	private static final long serialVersionUID = 1099901769568839613L;
	private java.lang.Integer pkCarmaker; // 主键
	private java.lang.String makerName; // 制造厂商名称
	private java.lang.String makerRemark; // 标识
	private java.util.Date makerCreatedate; // 创建时间
	private java.util.Date makerUpdatedate; // 修改时间

	public java.lang.Integer getPkCarmaker() {
		return pkCarmaker;
	}

	public void setPkCarmaker(java.lang.Integer pkCarmaker) {
		this.pkCarmaker = pkCarmaker;
	}

	public java.lang.String getMakerName() {
		return makerName;
	}

	public void setMakerName(java.lang.String makerName) {
		this.makerName = makerName;
	}

	public java.lang.String getMakerRemark() {
		return makerRemark;
	}

	public void setMakerRemark(java.lang.String makerRemark) {
		this.makerRemark = makerRemark;
	}

	public java.util.Date getMakerCreatedate() {
		return makerCreatedate;
	}

	public void setMakerCreatedate(java.util.Date makerCreatedate) {
		this.makerCreatedate = makerCreatedate;
	}

	public java.util.Date getMakerUpdatedate() {
		return makerUpdatedate;
	}

	public void setMakerUpdatedate(java.util.Date makerUpdatedate) {
		this.makerUpdatedate = makerUpdatedate;
	}

	@Override
	public String toString() {
		return "TblCarMaker [pkCarmaker=" + pkCarmaker + ", makerName="
				+ makerName + ", makerRemark=" + makerRemark
				+ ", makerCreatedate=" + makerCreatedate + ", makerUpdatedate="
				+ makerUpdatedate + "]";
	}

}