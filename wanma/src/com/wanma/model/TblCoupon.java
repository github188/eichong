package com.wanma.model;

import java.io.Serializable;
import java.util.Date;

import com.bluemobi.product.model.common.BasicListAndMutiFile;

/*优惠券表*/
public class TblCoupon extends BasicListAndMutiFile implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7196892269574600387L;

	private Integer pkCoupon;// 主键

	private Integer pkActivity;// 活动表主键

	private Integer pkCouponvariety;// 优惠券品种主键

	private Short cpStatus;// 优惠券状态（1-已使用）

	private Short cpLimitation;// 电桩限制（1-仅限交流电桩，2-仅限直流电装，3-不限充电桩）

	private Integer cpCouponvalue;// 优惠券面值

	private Integer cpCouponcondition;// 优惠券使用条件

	private String cpCouponcode;// 优惠码

	private Integer cpUserid;// 持有人（用户ID）

	private Date cpBegindate;// 生效时间

	private Date cpEnddate;// 失效时间

	private Date cpCreatedate;// 创建时间

	private Date cpUpdatedate;// 修改时间
	private Integer row;// 编号

	private Integer cpstates;// 状态：1:未兑换,2:已兑换,3:已使用,4:已过期
	
	private Date currentDate;
	
	private String covaActivityname;

    private String actActivityname;
    
    private String cpActivitytype;
    
    private String first;//1:代表首次进入该页面
    
    private String userPhone;//用户手机号
    
	
	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getActActivityname() {
		return actActivityname;
	}

	public void setActActivityname(String actActivityname) {
		this.actActivityname = actActivityname;
	}

	public String getCovaActivityname() {
		return covaActivityname;
	}

	public void setCovaActivityname(String covaActivityname) {
		this.covaActivityname = covaActivityname;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	
	
	
	

	public void setCpstates(Integer cpstates) {
		this.cpstates = cpstates;
	}

	public String getCpActivitytype() {
		return cpActivitytype;
	}

	public void setCpActivitytype(String cpActivitytype) {
		this.cpActivitytype = cpActivitytype;
	}

	public Integer getPkCoupon() {
		return pkCoupon;
	}

	public void setPkCoupon(Integer pkCoupon) {
		this.pkCoupon = pkCoupon;
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

	public Short getCpStatus() {
		return cpStatus;
	}

	public void setCpStatus(Short cpStatus) {
		this.cpStatus = cpStatus;
	}

	public Short getCpLimitation() {
		return cpLimitation;
	}

	public void setCpLimitation(Short cpLimitation) {
		this.cpLimitation = cpLimitation;
	}

	public Integer getCpCouponvalue() {
		return cpCouponvalue;
	}

	public void setCpCouponvalue(Integer cpCouponvalue) {
		this.cpCouponvalue = cpCouponvalue;
	}

	public Integer getCpCouponcondition() {
		return cpCouponcondition;
	}

	public void setCpCouponcondition(Integer cpCouponcondition) {
		this.cpCouponcondition = cpCouponcondition;
	}

	public String getCpCouponcode() {
		return cpCouponcode;
	}

	public void setCpCouponcode(String cpCouponcode) {
		this.cpCouponcode = cpCouponcode == null ? null : cpCouponcode.trim();
	}

	public Integer getCpUserid() {
		return cpUserid;
	}

	public void setCpUserid(Integer cpUserid) {
		this.cpUserid = cpUserid;
	}

	public Date getCpBegindate() {
		return cpBegindate;
	}

	public void setCpBegindate(Date cpBegindate) {
		this.cpBegindate = cpBegindate;
	}

	public Date getCpEnddate() {
		return cpEnddate;
	}

	public void setCpEnddate(Date cpEnddate) {
		this.cpEnddate = cpEnddate;
	}

	public Date getCpCreatedate() {
		return cpCreatedate;
	}

	public void setCpCreatedate(Date cpCreatedate) {
		this.cpCreatedate = cpCreatedate;
	}

	public Date getCpUpdatedate() {
		return cpUpdatedate;
	}

	public void setCpUpdatedate(Date cpUpdatedate) {
		this.cpUpdatedate = cpUpdatedate;
	}

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}
	public Integer getCpstates() {
		if(cpUserid!=null){
		Date date = new Date();
		//1、未兑换未过期(未兑换：cp_userId未绑定用户;未过期：结束时间在当前时间之后)
		if (cpUserid == 0&& cpEnddate.after(date)) {
			cpstates=1;
		}
		//2、未兑换已过期(未兑换：cp_userId未绑定用户;已过期：结束时间在当前时间之前)
		if (cpUserid == 0&& cpEnddate.before(date)) {
			cpstates=2;
		}
		//3、已兑换已使用(已兑换：cp_userId绑定用户;已使用：cp_Status为1)
		if(cpUserid != 0 && cpStatus == 1 ){
			cpstates=3;
		}
		//4、已兑换未使用未过期(已兑换：cp_userId绑定用户;未使用：cp_Status不为1;未过期：结束时间在当前时间之后)
		if(cpUserid != 0 && cpStatus != 1&& cpEnddate.after(date) ){
			cpstates=4;
		}
		//5、已兑换未使用已过期(已兑换：cp_userId绑定用户;未使用：cp_Status不为1;已过期：结束时间在当前时间之前)
		if(cpUserid != 0 && cpStatus != 1&& cpEnddate.before(date) ){
			cpstates=5;
		}
		}
		return cpstates;
	}
}