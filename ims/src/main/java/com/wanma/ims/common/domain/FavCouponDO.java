package com.wanma.ims.common.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.wanma.ims.common.domain.base.BasicModel;


/*优惠券表*/
public class FavCouponDO extends BasicModel {

	private static final long serialVersionUID = -6464222747600674362L;

	private Integer pkCoupon;// 主键

	private Integer pkActivity;// 活动表主键

	private Integer pkCouponvariety;// 优惠券品种主键

	private Short cpStatus;// 优惠券状态（1-已使用）

	private Short cpLimitation;// 电桩限制（1-仅限交流电桩，2-仅限直流电装，3-不限充电桩）

	private Integer cpCouponvalue;// 优惠券面值

	private Integer cpCouponcondition;// 优惠券使用条件

	private String cpCouponcode;// 优惠码

	private Integer cpUserid;// 持有人（用户ID）

	private String cpBegindate;// 生效时间

	private String cpEnddate;// 失效时间

	private String cpCreatedate;// 创建时间

	private String cpUpdatedate;// 修改时间

	private Integer cpstates;// 状态：1:未兑换,2:已兑换,3:已使用,4:已过期
	
	private Date currentDate;
	
	private String covaActivityname;

    private String actActivityname;
    
    private String cpActivitytype;
    
    private String userPhone;//用户手机号
    
    private String userName;//用户名称
    
    private String chCpLimitation;//导出
    
    private String chCpstates;//导出
    
    private Integer covaScope;//0:全国通用 1:城市通用 2:站点通用 
    private String cityCode; // 现金券品种适用范围：市
    private String couponCityScope; // 范围 列表页面用

	private Long cpyId;//所属的发行公司id
	private String cpyName;//所属的发行公司名称
	private Long accountId;//所属的发行公司资金账户id
	private String chargingOrderId;//充电消费订单id

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
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

	public String getCpBegindate() {
		return cpBegindate;
	}

	public void setCpBegindate(String cpBegindate) {
		this.cpBegindate = cpBegindate;
	}

	public String getCpEnddate() {
		return cpEnddate;
	}

	public void setCpEnddate(String cpEnddate) {
		this.cpEnddate = cpEnddate;
	}

	public String getCpCreatedate() {
		return cpCreatedate;
	}

	public void setCpCreatedate(String cpCreatedate) {
		this.cpCreatedate = cpCreatedate;
	}

	public String getCpUpdatedate() {
		return cpUpdatedate;
	}

	public void setCpUpdatedate(String cpUpdatedate) {
		this.cpUpdatedate = cpUpdatedate;
	}

	public String getChCpLimitation() {
		return chCpLimitation;
	}

	public void setChCpLimitation(String chCpLimitation) {
		this.chCpLimitation = chCpLimitation;
	}

	public String getChCpstates() {
		return chCpstates;
	}

	public void setChCpstates(String chCpstates) {
		this.chCpstates = chCpstates;
	}

	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd ");  
	public Integer getCpstates() throws ParseException {
		if(cpUserid!=null){
		Date date = new Date();
		//1、未兑换未过期(未兑换：cp_userId未绑定用户;未过期：结束时间在当前时间之后)
		if (cpUserid == 0&& sdf.parse(cpEnddate).after(date)) {
			cpstates=1;
		}
		//2、未兑换已过期(未兑换：cp_userId未绑定用户;已过期：结束时间在当前时间之前)
		if (cpUserid == 0&& sdf.parse(cpEnddate).before(date)) {
			cpstates=2;
		}
		//3、已兑换已使用(已兑换：cp_userId绑定用户;已使用：cp_Status为1)
		if(cpUserid != 0 && cpStatus == 1 ){
			cpstates=3;
		}
		//4、已兑换未使用未过期(已兑换：cp_userId绑定用户;未使用：cp_Status不为1;未过期：结束时间在当前时间之后)
		if(cpUserid != 0 && cpStatus != 1&& sdf.parse(cpEnddate).after(date) ){
			cpstates=4;
		}
		//5、已兑换未使用已过期(已兑换：cp_userId绑定用户;未使用：cp_Status不为1;已过期：结束时间在当前时间之前)
		if(cpUserid != 0 && cpStatus != 1&& sdf.parse(cpEnddate).before(date) ){
			cpstates=5;
		}
		}
		return cpstates;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getCovaScope() {
		return covaScope;
	}

	public void setCovaScope(Integer covaScope) {
		this.covaScope = covaScope;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCouponCityScope() {
		return couponCityScope;
	}

	public void setCouponCityScope(String couponCityScope) {
		this.couponCityScope = couponCityScope;
	}

	@Override
	public String toString() {
		return "FavCouponDO [pkCoupon=" + pkCoupon + ", pkActivity="
				+ pkActivity + ", pkCouponvariety=" + pkCouponvariety
				+ ", cpStatus=" + cpStatus + ", cpLimitation=" + cpLimitation
				+ ", cpCouponvalue=" + cpCouponvalue + ", cpCouponcondition="
				+ cpCouponcondition + ", cpCouponcode=" + cpCouponcode
				+ ", cpUserid=" + cpUserid + ", cpBegindate=" + cpBegindate
				+ ", cpEnddate=" + cpEnddate + ", cpCreatedate=" + cpCreatedate
				+ ", cpUpdatedate=" + cpUpdatedate + ", cpstates=" + cpstates
				+ ", currentDate=" + currentDate + ", covaActivityname="
				+ covaActivityname + ", actActivityname=" + actActivityname
				+ ", cpActivitytype=" + cpActivitytype + ", userPhone="
				+ userPhone + ", userName=" + userName + ", chCpLimitation="
				+ chCpLimitation + ", chCpstates=" + chCpstates
				+ ", covaScope=" + covaScope + ", cityCode=" + cityCode
				+ ", couponCityScope=" + couponCityScope + ", sdf=" + sdf + "]";
	}

	public Long getCpyId() {
		return cpyId;
	}

	public void setCpyId(Long cpyId) {
		this.cpyId = cpyId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getChargingOrderId() {
		return chargingOrderId;
	}

	public void setChargingOrderId(String chargingOrderId) {
		this.chargingOrderId = chargingOrderId;
	}

	public String getCpyName() {
		return cpyName;
	}

	public void setCpyName(String cpyName) {
		this.cpyName = cpyName;
	}

	
}