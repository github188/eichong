package com.wanma.model;

import com.pub.model.Entity;

/**
 * 
 * tbl_InstallDetail表
 * 
 * @author songjf
 * 
 */
public class TblInstalldetail extends Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5524091023666857609L;
	private java.lang.Integer pkInstalldetail; // 主键
	private java.lang.Integer indeParentid; // 父预约安装订单Id
	private java.lang.Integer indeProductid; // 产品Id
	private java.lang.String indeProductname; // 产品名称
	private java.math.BigDecimal indePrice; // 单价
	private java.lang.Integer indeQuantity; // 数量
	private java.math.BigDecimal indeTotalamount; // 总价
	private java.util.Date indeCreatedate; // 创建时间
	private java.util.Date indeUpdatedate; // 修改时间
	private java.lang.String indeProductcode; //
	
	private java.lang.String indeInstallationaddress; // 安装地址
	private java.lang.String indeInstallationperson; // 联系人
	private java.lang.String indeLnstallationtel; // 联系电话

	/**
	 * 获取主键属性
	 * 
	 * @return pkInstalldetail
	 */
	public java.lang.Integer getPkInstalldetail() {
		return pkInstalldetail;
	}

	/**
	 * 设置主键属性
	 * 
	 * @param pkInstalldetail
	 */
	public void setPkInstalldetail(java.lang.Integer pkInstalldetail) {
		this.pkInstalldetail = pkInstalldetail;
	}

	/**
	 * 获取父订单Id属性
	 * 
	 * @return indeParentid
	 */
	public java.lang.Integer getIndeParentid() {
		return indeParentid;
	}

	/**
	 * 设置父订单Id属性
	 * 
	 * @param indeParentid
	 */
	public void setIndeParentid(java.lang.Integer indeParentid) {
		this.indeParentid = indeParentid;
	}

	/**
	 * 获取产品Id属性
	 * 
	 * @return indeProductid
	 */
	public java.lang.Integer getIndeProductid() {
		return indeProductid;
	}

	/**
	 * 设置产品Id属性
	 * 
	 * @param indeProductid
	 */
	public void setIndeProductid(java.lang.Integer indeProductid) {
		this.indeProductid = indeProductid;
	}

	/**
	 * 获取产品名称属性
	 * 
	 * @return indeProductname
	 */
	public java.lang.String getIndeProductname() {
		return indeProductname;
	}

	/**
	 * 设置产品名称属性
	 * 
	 * @param indeProductname
	 */
	public void setIndeProductname(java.lang.String indeProductname) {
		this.indeProductname = indeProductname;
	}

	/**
	 * 获取单价属性
	 * 
	 * @return indePrice
	 */
	public java.math.BigDecimal getIndePrice() {
		return indePrice;
	}

	/**
	 * 设置单价属性
	 * 
	 * @param indePrice
	 */
	public void setIndePrice(java.math.BigDecimal indePrice) {
		this.indePrice = indePrice;
	}

	/**
	 * 获取数量属性
	 * 
	 * @return indeQuantity
	 */
	public java.lang.Integer getIndeQuantity() {
		return indeQuantity;
	}

	/**
	 * 设置数量属性
	 * 
	 * @param indeQuantity
	 */
	public void setIndeQuantity(java.lang.Integer indeQuantity) {
		this.indeQuantity = indeQuantity;
	}

	/**
	 * 获取总价属性
	 * 
	 * @return indeTotalamount
	 */
	public java.math.BigDecimal getIndeTotalamount() {
		return indeTotalamount;
	}

	/**
	 * 设置总价属性
	 * 
	 * @param indeTotalamount
	 */
	public void setIndeTotalamount(java.math.BigDecimal indeTotalamount) {
		this.indeTotalamount = indeTotalamount;
	}

	/**
	 * 获取创建时间属性
	 * 
	 * @return indeCreatedate
	 */
	public java.util.Date getIndeCreatedate() {
		return indeCreatedate;
	}

	/**
	 * 设置创建时间属性
	 * 
	 * @param indeCreatedate
	 */
	public void setIndeCreatedate(java.util.Date indeCreatedate) {
		this.indeCreatedate = indeCreatedate;
	}

	/**
	 * 获取修改时间属性
	 * 
	 * @return indeUpdatedate
	 */
	public java.util.Date getIndeUpdatedate() {
		return indeUpdatedate;
	}

	/**
	 * 设置修改时间属性
	 * 
	 * @param indeUpdatedate
	 */
	public void setIndeUpdatedate(java.util.Date indeUpdatedate) {
		this.indeUpdatedate = indeUpdatedate;
	}

	/**
	 * 获取属性
	 * 
	 * @return indeProductcode
	 */
	public java.lang.String getIndeProductcode() {
		return indeProductcode;
	}

	/**
	 * 设置属性
	 * 
	 * @param indeProductcode
	 */
	public void setIndeProductcode(java.lang.String indeProductcode) {
		this.indeProductcode = indeProductcode;
	}
	
	/**
     * 获取安装地址属性
     *
     * @return indeInstallationaddress
     */
	public java.lang.String getIndeInstallationaddress() {
		return indeInstallationaddress;
	}
	
	/**
	 * 设置安装地址属性
	 *
	 * @param indeInstallationaddress
	 */
	public void setIndeInstallationaddress(java.lang.String indeInstallationaddress) {
		this.indeInstallationaddress = indeInstallationaddress;
	}
	
	/**
     * 获取联系人属性
     *
     * @return indeInstallationperson
     */
	public java.lang.String getIndeInstallationperson() {
		return indeInstallationperson;
	}
	
	/**
	 * 设置联系人属性
	 *
	 * @param indeInstallationperson
	 */
	public void setIndeInstallationperson(java.lang.String indeInstallationperson) {
		this.indeInstallationperson = indeInstallationperson;
	}
	
	/**
     * 获取联系电话属性
     *
     * @return indeLnstallationtel
     */
	public java.lang.String getIndeLnstallationtel() {
		return indeLnstallationtel;
	}
	
	/**
	 * 设置联系电话属性
	 *
	 * @param indeLnstallationtel
	 */
	public void setIndeLnstallationtel(java.lang.String indeLnstallationtel) {
		this.indeLnstallationtel = indeLnstallationtel;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("TblInstalldetail");
		sb.append("{pkInstalldetail=").append(pkInstalldetail);
		sb.append(", indeParentid=").append(indeParentid);
		sb.append(", indeProductid=").append(indeProductid);
		sb.append(", indeProductname=").append(indeProductname);
		sb.append(", indePrice=").append(indePrice);
		sb.append(", indeQuantity=").append(indeQuantity);
		sb.append(", indeTotalamount=").append(indeTotalamount);
		sb.append(", indeCreatedate=").append(indeCreatedate);
		sb.append(", indeUpdatedate=").append(indeUpdatedate);
		sb.append(", indeProductcode=").append(indeProductcode);
		sb.append(", indeInstallationaddress=").append(indeInstallationaddress);
        sb.append(", indeInstallationperson=").append(indeInstallationperson);
        sb.append(", indeLnstallationtel=").append(indeLnstallationtel);
		sb.append('}');
		return sb.toString();
	}
}