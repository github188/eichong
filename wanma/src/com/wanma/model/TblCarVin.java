package com.wanma.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.bluemobi.product.model.common.BasicListAndMutiFile;

public class TblCarVin extends BasicListAndMutiFile implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 248243046758106819L;

	/**
	 * 
	 */
	

	private Integer pkCarVin;

    private String cvVinCode;

    private String cvName;

    private BigDecimal cvServicemoney;

    private Short cvIsdelete;

    private Date cvCreatedate;

    private Date cvUpdatedate;
    
    private String cvLicenseNumber;
    

    public String getCvLicenseNumber() {
		return cvLicenseNumber;
	}

	public void setCvLicenseNumber(String cvLicenseNumber) {
		this.cvLicenseNumber = cvLicenseNumber;
	}

	public Integer getPkCarVin() {
        return pkCarVin;
    }

    public void setPkCarVin(Integer pkCarVin) {
        this.pkCarVin = pkCarVin;
    }

    public String getCvVinCode() {
        return cvVinCode;
    }

    public void setCvVinCode(String cvVinCode) {
        this.cvVinCode = cvVinCode == null ? null : cvVinCode.trim();
    }

    public String getCvName() {
        return cvName;
    }

    public void setCvName(String cvName) {
        this.cvName = cvName == null ? null : cvName.trim();
    }

    public BigDecimal getCvServicemoney() {
        return cvServicemoney;
    }

    public void setCvServicemoney(BigDecimal cvServicemoney) {
        this.cvServicemoney = cvServicemoney;
    }

    public Short getCvIsdelete() {
        return cvIsdelete;
    }

    public void setCvIsdelete(Short cvIsdelete) {
        this.cvIsdelete = cvIsdelete;
    }

    public Date getCvCreatedate() {
        return cvCreatedate;
    }

    public void setCvCreatedate(Date cvCreatedate) {
        this.cvCreatedate = cvCreatedate;
    }

    public Date getCvUpdatedate() {
        return cvUpdatedate;
    }

    public void setCvUpdatedate(Date cvUpdatedate) {
        this.cvUpdatedate = cvUpdatedate;
    }
}