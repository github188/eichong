package com.wanma.dubbox.model;


import java.util.Date;

import com.wanma.dubbox.model.common.BasicModel;


/**
 * 
 * tbl_EquipmentRepair表
 * 
 * @author songjf
 * 
 */
public class TblEquipmentrepair extends BasicModel {
	private static final long serialVersionUID = 1L;
	private Integer wtpId; // 保修类型  0、其他 1、电桩故障 2、充电异常  3、结构损坏 4、键盘失灵 5、显示模糊  
	private String txt; // 保修内容
	private Integer uid; // 用户ID
	private Integer wsts; // 1：未处理 2 处理中 3：已处理
	private Integer sts; // 0：显示 -1已删除
	private Integer epid; //电桩、充电点id
	private Integer dvcTp;//设备类型 1电桩2充电点
	private String deaRst;//处理结果
	public Integer getWtpId() {
		return wtpId;
	}
	public void setWtpId(Integer wtpId) {
		this.wtpId = wtpId;
	}
	public String getTxt() {
		return txt;
	}
	public void setTxt(String txt) {
		this.txt = txt;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getWsts() {
		return wsts;
	}
	public void setWsts(Integer wsts) {
		this.wsts = wsts;
	}
	public Integer getSts() {
		return sts;
	}
	public void setSts(Integer sts) {
		this.sts = sts;
	}
	public Integer getEpid() {
		return epid;
	}
	public void setEpid(Integer epid) {
		this.epid = epid;
	}
	public Integer getDvcTp() {
		return dvcTp;
	}
	public void setDvcTp(Integer dvcTp) {
		this.dvcTp = dvcTp;
	}
	public String getDeaRst() {
		return deaRst;
	}
	public void setDeaRst(String deaRst) {
		this.deaRst = deaRst;
	}
}