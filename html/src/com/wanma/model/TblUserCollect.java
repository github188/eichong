package com.wanma.model;

import java.io.Serializable;

/**
 * 
 * tbl_UserCollect表
 * @author mew
 *
 */
public class TblUserCollect implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private java.lang.Integer pkUsercollect; // 主键
	private java.lang.Integer uscoUserid; // 用户id
	private java.lang.Integer uscoType; // 类型（1电桩，2电站,3电动自行车）
            
	private java.lang.Integer uscoObjectid; // 对象id
	private java.util.Date uscoAddtime; // 收藏的时间
	
	//表单字段
	private String objName;	
	private String objAddress;

	/**
     * 获取主键属性
     *
     * @return pkUsercollect
     */
	public java.lang.Integer getPkUsercollect() {
		return pkUsercollect;
	}
	
	/**
	 * 设置主键属性
	 *
	 * @param pkUsercollect
	 */
	public void setPkUsercollect(java.lang.Integer pkUsercollect) {
		this.pkUsercollect = pkUsercollect;
	}
	
	/**
     * 获取用户id属性
     *
     * @return uscoUserid
     */
	public java.lang.Integer getUscoUserid() {
		return uscoUserid;
	}
	
	/**
	 * 设置用户id属性
	 *
	 * @param uscoUserid
	 */
	public void setUscoUserid(java.lang.Integer uscoUserid) {
		this.uscoUserid = uscoUserid;
	}
	
	/**
     * 获取类型（1电桩，2电站）
            2为电站属性
     *
     * @return uscoType
     */
	public java.lang.Integer getUscoType() {
		return uscoType;
	}
	
	/**
	 * 设置类型（1电桩，2电站）
            2为电站属性
	 *
	 * @param uscoType
	 */
	public void setUscoType(java.lang.Integer uscoType) {
		this.uscoType = uscoType;
	}
	
	/**
     * 获取对象id属性
     *
     * @return uscoObjectid
     */
	public java.lang.Integer getUscoObjectid() {
		return uscoObjectid;
	}
	
	/**
	 * 设置对象id属性
	 *
	 * @param uscoObjectid
	 */
	public void setUscoObjectid(java.lang.Integer uscoObjectid) {
		this.uscoObjectid = uscoObjectid;
	}
	
	/**
     * 获取收藏的时间属性
     *
     * @return uscoAddtime
     */
	public java.util.Date getUscoAddtime() {
		return uscoAddtime;
	}
	
	/**
	 * 设置收藏的时间属性
	 *
	 * @param uscoAddtime
	 */
	public void setUscoAddtime(java.util.Date uscoAddtime) {
		this.uscoAddtime = uscoAddtime;
	}
	

	public String getObjName() {
		return objName;
	}

	public void setObjName(String objName) {
		this.objName = objName;
	}

	public String getObjAddress() {
		return objAddress;
	}

	public void setObjAddress(String objAddress) {
		this.objAddress = objAddress;
	}

	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("TblUserCollect");
        sb.append("{pkUsercollect=").append(pkUsercollect);
        sb.append(", uscoUserid=").append(uscoUserid);
        sb.append(", uscoType=").append(uscoType);
        sb.append(", uscoObjectid=").append(uscoObjectid);
        sb.append(", uscoAddtime=").append(uscoAddtime);
		sb.append('}');
        return sb.toString();
    }
}