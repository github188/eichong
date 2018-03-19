package com.wanma.model;

import java.util.Date;

public class TblMessagePointRela {
	
    private Integer pkMprId;
    
    private Integer pkMeiId;
	
	private Integer pkPowerstation;
	
	private	String mprName;
	
	private Date mprCreatedate;
	
	private String address;
	
	

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getPkMprId() {
		return pkMprId;
	}

	public void setPkMprId(Integer pkMprId) {
		this.pkMprId = pkMprId;
	}

	public Integer getPkMeiId() {
		return pkMeiId;
	}

	public void setPkMeiId(Integer pkMeiId) {
		this.pkMeiId = pkMeiId;
	}

	public Integer getPkPowerstation() {
		return pkPowerstation;
	}

	public void setPkPowerstation(Integer pkPowerstation) {
		this.pkPowerstation = pkPowerstation;
	}

	public String getMprName() {
		return mprName;
	}

	public void setMprName(String mprName) {
		this.mprName = mprName;
	}

	public Date getMprCreatedate() {
		return mprCreatedate;
	}

	public void setMprCreatedate(Date mprCreatedate) {
		this.mprCreatedate = mprCreatedate;
	}

	
   
}