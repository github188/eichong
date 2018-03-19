package com.wanma.dubbox.model;

import java.util.List;

import com.wanma.dubbox.model.common.BasicModel;

public class TblTypespan extends BasicModel {
	private static final long serialVersionUID = 1L;
	private String ts; // 产品型号
	private String modNm; // 产品名称
	private String ftg; // 产品说明
	private String fnm; // 文件ID和名称
	private String pnm; // 外部产品型号（其他公司产品型号）
	private String rmk; // 备注
	private List<TblBomList> bomList;//bom清单

	public String getTs() {
		return ts;
	}

	public void setTs(String ts) {
		this.ts = ts;
	}

	public String getModNm() {
		return modNm;
	}

	public void setModNm(String modNm) {
		this.modNm = modNm;
	}

	public String getFtg() {
		return ftg;
	}

	public void setFtg(String ftg) {
		this.ftg = ftg;
	}

	public String getFnm() {
		return fnm;
	}

	public void setFnm(String fnm) {
		this.fnm = fnm;
	}

	public String getPnm() {
		return pnm;
	}

	public void setPnm(String pnm) {
		this.pnm = pnm;
	}

	public String getRmk() {
		return rmk;
	}

	public void setRmk(String rmk) {
		this.rmk = rmk;
	}


	public List<TblBomList> getBomList() {
		return bomList;
	}

	public void setBomList(List<TblBomList> bomList) {
		this.bomList = bomList;
	}
}
