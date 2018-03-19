package com.wanma.model;

import java.util.Date;

public class TblActivityschedule {
    private Integer pkActivityschedule;

    private Integer pkActivity;

    private Integer pkCouponvariety;

    private Integer acscNum;

    private Date acscCreatedate;

    private Date acscUpdatedate;

    public Integer getPkActivityschedule() {
        return pkActivityschedule;
    }

    public void setPkActivityschedule(Integer pkActivityschedule) {
        this.pkActivityschedule = pkActivityschedule;
    }

    public Integer getPkActivity() {
        return pkActivity;
    }

    public void setPkActivity(Integer pkActivity) {
        this.pkActivity = pkActivity;
    }

    public Integer getPkCouponvariety() {
        return pkCouponvariety;
    }

    public void setPkCouponvariety(Integer pkCouponvariety) {
        this.pkCouponvariety = pkCouponvariety;
    }

    public Integer getAcscNum() {
        return acscNum;
    }

    public void setAcscNum(Integer acscNum) {
        this.acscNum = acscNum;
    }

    public Date getAcscCreatedate() {
        return acscCreatedate;
    }

    public void setAcscCreatedate(Date acscCreatedate) {
        this.acscCreatedate = acscCreatedate;
    }

    public Date getAcscUpdatedate() {
        return acscUpdatedate;
    }

    public void setAcscUpdatedate(Date acscUpdatedate) {
        this.acscUpdatedate = acscUpdatedate;
    }
}