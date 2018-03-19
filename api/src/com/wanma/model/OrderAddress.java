package com.wanma.model;


/**
 * 
 * tbl_order_address表
 * @author mew
 *
 */
public class OrderAddress{
	private java.lang.Integer pkOrderaddress; // 主键
	private java.lang.Integer oradOrderdetail; // 订单详情表主键
	private java.lang.Integer oradProductid; // 产品Id
	private java.lang.String oradProductname; // 产品名称
	private java.lang.Integer oradInstalladdress; // 安装地址Id
	private java.lang.Integer oradQuantity; // 安装数量
	private java.lang.Integer oradStatus; // 是否提交预约：0否 1是
	private java.util.Date oradCreatedate; // 创建时间
	private java.util.Date oradUpdatedate; // 修改时间

	/**
     * 获取主键属性
     *
     * @return pkOrderaddress
     */
	public java.lang.Integer getPkOrderaddress() {
		return pkOrderaddress;
	}
	
	/**
	 * 设置主键属性
	 *
	 * @param pkOrderaddress
	 */
	public void setPkOrderaddress(java.lang.Integer pkOrderaddress) {
		this.pkOrderaddress = pkOrderaddress;
	}
	
	/**
     * 获取订单详情表主键属性
     *
     * @return oradOrderdetail
     */
	public java.lang.Integer getOradOrderdetail() {
		return oradOrderdetail;
	}
	
	/**
	 * 设置订单详情表主键属性
	 *
	 * @param oradOrderdetail
	 */
	public void setOradOrderdetail(java.lang.Integer oradOrderdetail) {
		this.oradOrderdetail = oradOrderdetail;
	}
	
	/**
     * 获取产品Id属性
     *
     * @return oradProductid
     */
	public java.lang.Integer getOradProductid() {
		return oradProductid;
	}
	
	/**
	 * 设置产品Id属性
	 *
	 * @param oradProductid
	 */
	public void setOradProductid(java.lang.Integer oradProductid) {
		this.oradProductid = oradProductid;
	}
	
	/**
     * 获取产品名称属性
     *
     * @return oradProductname
     */
	public java.lang.String getOradProductname() {
		return oradProductname;
	}
	
	/**
	 * 设置产品名称属性
	 *
	 * @param oradProductname
	 */
	public void setOradProductname(java.lang.String oradProductname) {
		this.oradProductname = oradProductname;
	}
	
	/**
     * 获取安装地址Id属性
     *
     * @return oradInstalladdress
     */
	public java.lang.Integer getOradInstalladdress() {
		return oradInstalladdress;
	}
	
	/**
	 * 设置安装地址Id属性
	 *
	 * @param oradInstalladdress
	 */
	public void setOradInstalladdress(java.lang.Integer oradInstalladdress) {
		this.oradInstalladdress = oradInstalladdress;
	}
	
	/**
     * 获取安装数量属性
     *
     * @return oradQuantity
     */
	public java.lang.Integer getOradQuantity() {
		return oradQuantity;
	}
	
	/**
	 * 设置安装数量属性
	 *
	 * @param oradQuantity
	 */
	public void setOradQuantity(java.lang.Integer oradQuantity) {
		this.oradQuantity = oradQuantity;
	}
	
	/**
     * 获取是否提交预约：0否 1是属性
     *
     * @return oradStatus
     */
	public java.lang.Integer getOradStatus() {
		return oradStatus;
	}
	
	/**
	 * 设置是否提交预约：0否 1是属性
	 *
	 * @param oradStatus
	 */
	public void setOradStatus(java.lang.Integer oradStatus) {
		this.oradStatus = oradStatus;
	}
	
	/**
     * 获取创建时间属性
     *
     * @return oradCreatedate
     */
	public java.util.Date getOradCreatedate() {
		return oradCreatedate;
	}
	
	/**
	 * 设置创建时间属性
	 *
	 * @param oradCreatedate
	 */
	public void setOradCreatedate(java.util.Date oradCreatedate) {
		this.oradCreatedate = oradCreatedate;
	}
	
	/**
     * 获取修改时间属性
     *
     * @return oradUpdatedate
     */
	public java.util.Date getOradUpdatedate() {
		return oradUpdatedate;
	}
	
	/**
	 * 设置修改时间属性
	 *
	 * @param oradUpdatedate
	 */
	public void setOradUpdatedate(java.util.Date oradUpdatedate) {
		this.oradUpdatedate = oradUpdatedate;
	}
	

	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("TblOrderAddress");
        sb.append("{pkOrderaddress=").append(pkOrderaddress);
        sb.append(", oradOrderdetail=").append(oradOrderdetail);
        sb.append(", oradProductid=").append(oradProductid);
        sb.append(", oradProductname=").append(oradProductname);
        sb.append(", oradInstalladdress=").append(oradInstalladdress);
        sb.append(", oradQuantity=").append(oradQuantity);
        sb.append(", oradStatus=").append(oradStatus);
        sb.append(", oradCreatedate=").append(oradCreatedate);
        sb.append(", oradUpdatedate=").append(oradUpdatedate);
		sb.append('}');
        return sb.toString();
    }
}