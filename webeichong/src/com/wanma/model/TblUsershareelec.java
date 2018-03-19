package com.wanma.model;

import java.io.Serializable;

import com.bluemobi.product.model.common.BasicListAndMutiFile;

/**
 * 
 * tbl_UserShareElec表
 * @author mew
 *
 */
public class TblUsershareelec extends BasicListAndMutiFile implements Serializable {
	private java.lang.Integer pkUsershareelec; // 主键
	private java.lang.Integer uselUserid; // 用户id
	private java.lang.String uselElecaddress; // 桩体地址
	private java.lang.Integer uselMaker; // 电桩制造商，配置参数内容的ID （万马新能源，南京循道，北京三优，上海普天）
	private java.lang.Integer uselPilechargingmode; // 充电方式，配置参数内容的ID （直流充电桩，交流充电桩）
	private java.lang.Integer uselPowerinterface; // 桩体接口，配置参数内容的ID（国标、欧标、美标）
	private java.lang.String uselOther; // 其它
	private java.lang.String uselParam; // 分享参数
	private java.lang.String uselImage; // 分享图片

	/**
     * 获取主键属性
     *
     * @return pkUsershareelec
     */
	public java.lang.Integer getPkUsershareelec() {
		return pkUsershareelec;
	}
	
	/**
	 * 设置主键属性
	 *
	 * @param pkUsershareelec
	 */
	public void setPkUsershareelec(java.lang.Integer pkUsershareelec) {
		this.pkUsershareelec = pkUsershareelec;
	}
	
	/**
     * 获取用户id属性
     *
     * @return uselUserid
     */
	public java.lang.Integer getUselUserid() {
		return uselUserid;
	}
	
	/**
	 * 设置用户id属性
	 *
	 * @param uselUserid
	 */
	public void setUselUserid(java.lang.Integer uselUserid) {
		this.uselUserid = uselUserid;
	}
	
	/**
     * 获取桩体地址属性
     *
     * @return uselElecaddress
     */
	public java.lang.String getUselElecaddress() {
		return uselElecaddress;
	}
	
	/**
	 * 设置桩体地址属性
	 *
	 * @param uselElecaddress
	 */
	public void setUselElecaddress(java.lang.String uselElecaddress) {
		this.uselElecaddress = uselElecaddress;
	}
	
	/**
     * 获取电桩制造商，配置参数内容的ID （万马新能源，南京循道，北京三优，上海普天）属性
     *
     * @return uselMaker
     */
	public java.lang.Integer getUselMaker() {
		return uselMaker;
	}
	
	/**
	 * 设置电桩制造商，配置参数内容的ID （万马新能源，南京循道，北京三优，上海普天）属性
	 *
	 * @param uselMaker
	 */
	public void setUselMaker(java.lang.Integer uselMaker) {
		this.uselMaker = uselMaker;
	}
	
	/**
     * 获取充电方式，配置参数内容的ID （直流充电桩，交流充电桩）属性
     *
     * @return uselPilechargingmode
     */
	public java.lang.Integer getUselPilechargingmode() {
		return uselPilechargingmode;
	}
	
	/**
	 * 设置充电方式，配置参数内容的ID （直流充电桩，交流充电桩）属性
	 *
	 * @param uselPilechargingmode
	 */
	public void setUselPilechargingmode(java.lang.Integer uselPilechargingmode) {
		this.uselPilechargingmode = uselPilechargingmode;
	}
	
	/**
     * 获取桩体接口，配置参数内容的ID（国标、欧标、美标）属性
     *
     * @return uselPowerinterface
     */
	public java.lang.Integer getUselPowerinterface() {
		return uselPowerinterface;
	}
	
	/**
	 * 设置桩体接口，配置参数内容的ID（国标、欧标、美标）属性
	 *
	 * @param uselPowerinterface
	 */
	public void setUselPowerinterface(java.lang.Integer uselPowerinterface) {
		this.uselPowerinterface = uselPowerinterface;
	}
	
	/**
     * 获取其它属性
     *
     * @return uselOther
     */
	public java.lang.String getUselOther() {
		return uselOther;
	}
	
	/**
	 * 设置其它属性
	 *
	 * @param uselOther
	 */
	public void setUselOther(java.lang.String uselOther) {
		this.uselOther = uselOther;
	}
	
	/**
     * 获取分享参数属性
     *
     * @return uselParam
     */
	public java.lang.String getUselParam() {
		return uselParam;
	}
	
	/**
	 * 设置分享参数属性
	 *
	 * @param uselParam
	 */
	public void setUselParam(java.lang.String uselParam) {
		this.uselParam = uselParam;
	}
	
	/**
     * 获取分享图片属性
     *
     * @return uselImage
     */
	public java.lang.String getUselImage() {
		return uselImage;
	}
	
	/**
	 * 设置分享图片属性
	 *
	 * @param uselImage
	 */
	public void setUselImage(java.lang.String uselImage) {
		this.uselImage = uselImage;
	}
	

	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("TblUsershareelec");
        sb.append("{pkUsershareelec=").append(pkUsershareelec);
        sb.append(", uselUserid=").append(uselUserid);
        sb.append(", uselElecaddress=").append(uselElecaddress);
        sb.append(", uselMaker=").append(uselMaker);
        sb.append(", uselPilechargingmode=").append(uselPilechargingmode);
        sb.append(", uselPowerinterface=").append(uselPowerinterface);
        sb.append(", uselOther=").append(uselOther);
        sb.append(", uselParam=").append(uselParam);
        sb.append(", uselImage=").append(uselImage);
		sb.append('}');
        return sb.toString();
    }
}