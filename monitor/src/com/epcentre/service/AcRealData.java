package com.epcentre.service;

import java.math.BigDecimal;
import java.util.Date;

import io.netty.channel.Channel;

public class AcRealData {
	//桩编号
	private String ep_code;
	
	//枪口编号
	private Integer ep_gun_no;
	
	//2充电输出电压
	private BigDecimal output_voltage;
		
	//3充电输出电流
	private BigDecimal output_current;
	
	//4工作状态
	private Integer working_status;
		
	//5车与桩连接确认开关状态
	private Integer linked_status;
	
	//6车位地锁状态
    private Integer  car_Place_Lock_Status;
	
    //7有功总电度
  	private BigDecimal total_active_dbnum;
    //8已充金额
    private BigDecimal chargedCost;
    //9当前电价
	private BigDecimal chargePrice; 
	//10已充度数	
	private BigDecimal chargedMeterNum;//已充度数
	//11累计充电时间
	private Integer total_cd_time;
	//13  是否连接电池	1：M_SP_NA_1  BIN 码  1Byte	布尔型,  变化上传
  	private Integer connect_battry;
	//14枪座状态
	private Integer gun_close_status;//枪座
	
	//15充电枪盖状态
	private Integer gun_lid_status;//枪盖
	//16车与桩建立通信信号
	private Integer gun2car_comm_status;
	//17车位占用状态
	private Integer car_place_status;
  	
	//18  读卡器通讯异常故障 1：M_SP_NA_1  BIN 码  1Byte	布尔型,  变化上传； 0 正常， 1异常
  	private Integer readCardCommException;
  	//19 急停按钮动作故障 1：M_SP_NA_1  BIN 码  1Byte 布尔型,  变化上传； 0 正常， 1异常
  	private Integer urgentBtnException;
    //20  避雷器故障	1：M_SP_NA_1  BIN 码  1Byte	布尔型,  变化上传； 0 正常， 1异常 
  	private Integer arresterException;
    //21  绝缘监测故障1：M_SP_NA_1  BIN 码  1Byte布尔型,  变化上传； 0 正常， 1异常
  	private Integer insulationException;
    //22  充电枪未连接告警	1：M_SP_NA_1  BIN 码  1Byte	布尔型,  变化上传； 0 正常， 1异常
  	private Integer gun_unlinked_warn;
    //23  交易记录已满告警 1：M_SP_NA_1  BIN 码  1Byte	布尔型,  变化上传； 0 正常， 1异常
  	private Integer transRecordFullWarn;
  	//24 电度表异常故障1：M_SP_NA_1  BIN 码  1Byte布尔型,  变化上传； 0 正常， 1异常
  	private Integer meterException;
    //25交流输入过压/欠压告警
  	private Integer in_vol_warn;
    //26 充电机过温故障	1：M_SP_NA_1  BIN 码  1Byte	布尔型,  变化上传； 0 正常， 1异常
  	private Integer ChargerOverTempWarn;
  	
  	//27交流电流过负荷告警
  	private Integer loaded_warn;
    //28输出继电器状态
  	private Integer out_relay_status;
  	
    //A相输入 电压
  	private BigDecimal a_vol;
  	//B相输入 电压
  	private BigDecimal b_vol;
  	//C相输入 电压
  	private BigDecimal c_vol;
  	//A相输入 电流
  	private BigDecimal a_current;
  	//B相输入 电流
  	private BigDecimal b_current;
  	//C相输入 电流
  	private BigDecimal c_current;
  	//最高输出电压
  	private BigDecimal max_out_vol;
  	//最低输出电压
  	private BigDecimal min_out_vol;
  	//最大输出电流
  	private BigDecimal max_out_current;
  	@Override
	public String toString() {
		
		final StringBuilder sb = new StringBuilder();
        sb.append("RealAcChargeInfo");
        sb.append(",{epcode=").append(this.ep_code).append("}\n");
        sb.append(",{epgunno=").append(this.ep_gun_no).append("}\n");
        sb.append(",{output_voltage(输出电压)=").append(output_voltage).append("}\n");
        sb.append(",{output_current(输出电流)=").append(output_current).append("}\n");
        sb.append(",{linked_status(连接状态)=").append(linked_status).append("}\n");
        sb.append(",{gun_close_status(收枪状态,暂时不支持)=").append(gun_close_status).append("}\n");
        sb.append(",{gun_lid_status(枪盖状态,暂时不支持)=").append(gun_lid_status).append("}\n");
        sb.append(",{working_status(工作状态)=").append(working_status).append("}\n");
        sb.append(",{in_vol_warn(输入过压/欠压)=").append(in_vol_warn).append("}\n");
        sb.append(",{loaded_warn(负何告警)=").append(loaded_warn).append("}\n");
        sb.append(",{out_relay_status(输出继电器状态)=").append(out_relay_status).append("}\n");
        sb.append(",{max_out_current(最大输出电流)=").append(max_out_current).append("}\n");
        sb.append(",{min_out_vol(最低输出电压)=").append(min_out_vol).append("}\n");
        sb.append(",{max_out_vol(最高输出电压)=").append(max_out_vol).append("}\n");
        sb.append(",{c_current(C相输入 电流)=").append(c_current).append("}\n");
        sb.append(",{b_current(B相输入 电流)=").append(b_current).append("}\n");
        sb.append(",{a_current(A相输入 电流)=").append(a_current).append("}\n");
        sb.append(",{c_vol(C相输入 电压)=").append(c_current).append("}\n");
        sb.append(",{b_vol(B相输入 电压)=").append(b_current).append("}\n");
        sb.append(",{A_vol(B相输入 电压)=").append(a_current).append("}\n");
        sb.append(",{ChargerOverTempWarn( 充电机过温故障)=").append(ChargerOverTempWarn).append("}\n");
        sb.append(",{meterException(电度表异常故障)=").append(meterException).append("}\n");
        sb.append(",{transRecordFullWarn(交易记录已满告警)=").append(transRecordFullWarn).append("}\n");
        sb.append(",{gun_unlinked_warn(充电枪未连接告警)=").append(gun_unlinked_warn).append("}\n");
        sb.append(",{insulationException(绝缘监测故障)=").append(insulationException).append("}\n");
        sb.append(",{readCardCommException(读卡器通讯异常故障 )=").append(readCardCommException).append("}\n");
        sb.append(",{arresterException(避雷器故障)=").append(arresterException).append("}\n");
        sb.append(",{urgentBtnException(急停按钮动作故障)=").append(urgentBtnException).append("}\n");
        sb.append(",{car_place_status(车位占用状态)=").append(car_place_status).append("}\n");
        sb.append(",{gun2car_comm_status(车与桩建立通信信号)=").append(gun2car_comm_status).append("}\n");
        sb.append(",{connect_battry(是否连接电池)=").append(connect_battry).append("}\n");
        sb.append(",{total_cd_time(累计充电时间)=").append(total_cd_time).append("}\n");
        sb.append(",{chargedMeterNum(已充度数)=").append(chargedMeterNum).append("}\n");
        sb.append(",{chargePrice(当前电价)=").append(chargePrice).append("}\n");
        sb.append(",{chargedCost(已充金额)=").append(chargedCost).append("}\n");
        sb.append(",{total_active_dbnum(有功总电度)=").append(connect_battry).append("}\n");
        sb.append(",{car_Place_Lock_Status(车位地锁状态)=").append(connect_battry).append("}\n");
        
		return sb.toString();
	}
   
  	
  	
	public String getEp_code() {
		return ep_code;
	}

	public void setEp_code(String ep_code) {
		this.ep_code = ep_code;
	}


	public Integer getEp_gun_no() {
		return ep_gun_no;
	}



	public void setEp_gun_no(Integer ep_gun_no) {
		this.ep_gun_no = ep_gun_no;
	}


	public BigDecimal getOutput_voltage() {
		return output_voltage;
	}


	public void setOutput_voltage(BigDecimal output_voltage) {
		this.output_voltage = output_voltage;
	}



	public BigDecimal getOutput_current() {
		return output_current;
	}


	public void setOutput_current(BigDecimal output_current) {
		this.output_current = output_current;
	}

	public Integer getWorking_status() {
		return working_status;
	}


	public void setWorking_status(Integer working_status) {
		this.working_status = working_status;
	}

	public Integer getLinked_status() {
		return linked_status;
	}


	public void setLinked_status(Integer linked_status) {
		this.linked_status = linked_status;
	}



	public Integer getCar_Place_Lock_Status() {
		return car_Place_Lock_Status;
	}


	public void setCar_Place_Lock_Status(Integer car_Place_Lock_Status) {
		this.car_Place_Lock_Status = car_Place_Lock_Status;
	}



	public BigDecimal getTotal_active_dbnum() {
		return total_active_dbnum;
	}


	public void setTotal_active_dbnum(BigDecimal total_active_dbnum) {
		this.total_active_dbnum = total_active_dbnum;
	}


	public BigDecimal getChargedCost() {
		return chargedCost;
	}


	public void setChargedCost(BigDecimal chargedCost) {
		this.chargedCost = chargedCost;
	}


	public BigDecimal getChargePrice() {
		return chargePrice;
	}



	public void setChargePrice(BigDecimal chargePrice) {
		this.chargePrice = chargePrice;
	}


	public BigDecimal getChargedMeterNum() {
		return chargedMeterNum;
	}

	public void setChargedMeterNum(BigDecimal chargedMeterNum) {
		this.chargedMeterNum = chargedMeterNum;
	}


	public Integer getTotal_cd_time() {
		return total_cd_time;
	}

	public void setTotal_cd_time(Integer total_cd_time) {
		this.total_cd_time = total_cd_time;
	}


	public Integer getConnect_battry() {
		return connect_battry;
	}

	public void setConnect_battry(Integer connect_battry) {
		this.connect_battry = connect_battry;
	}


	public Integer getGun_close_status() {
		return gun_close_status;
	}

	public void setGun_close_status(Integer gun_close_status) {
		this.gun_close_status = gun_close_status;
	}

	public Integer getGun_lid_status() {
		return gun_lid_status;
	}

	public void setGun_lid_status(Integer gun_lid_status) {
		this.gun_lid_status = gun_lid_status;
	}


	public Integer getGun2car_comm_status() {
		return gun2car_comm_status;
	}

	public void setGun2car_comm_status(Integer gun2car_comm_status) {
		this.gun2car_comm_status = gun2car_comm_status;
	}

	public Integer getCar_place_status() {
		return car_place_status;
	}

	public void setCar_place_status(int car_place_status) {
		this.car_place_status = car_place_status;
	}


	public Integer getReadCardCommException() {
		return readCardCommException;
	}

	public void setReadCardCommException(Integer readCardCommException) {
		this.readCardCommException = readCardCommException;
	}

	public Integer getUrgentBtnException() {
		return urgentBtnException;
	}


	public void setUrgentBtnException(Integer urgentBtnException) {
		this.urgentBtnException = urgentBtnException;
	}

	public Integer getArresterException() {
		return arresterException;
	}


	public void setArresterException(Integer arresterException) {
		this.arresterException = arresterException;
	}


	public Integer getInsulationException() {
		return insulationException;
	}

	public void setInsulationException(Integer insulationException) {
		this.insulationException = insulationException;
	}

	public Integer getGun_unlinked_warn() {
		return gun_unlinked_warn;
	}

	public void setGun_unlinked_warn(Integer gun_unlinked_warn) {
		this.gun_unlinked_warn = gun_unlinked_warn;
	}


	public Integer getTransRecordFullWarn() {
		return transRecordFullWarn;
	}

	public void setTransRecordFullWarn(Integer transRecordFullWarn) {
		this.transRecordFullWarn = transRecordFullWarn;
	}


	public Integer getMeterException() {
		return meterException;
	}

	public void setMeterException(Integer meterException) {
		this.meterException = meterException;
	}

	public Integer getIn_vol_warn() {
		return in_vol_warn;
	}

	public void setIn_vol_warn(Integer in_more_vol_warn) {
		this.in_vol_warn = in_vol_warn;
	}


	public Integer getChargerOverTempWarn() {
		return ChargerOverTempWarn;
	}


	public void setChargerOverTempWarn(Integer chargerOverTempWarn) {
		ChargerOverTempWarn = chargerOverTempWarn;
	}

	public Integer getLoaded_warn() {
		return loaded_warn;
	}


	public void setLoaded_warn(Integer loaded_warn) {
		this.loaded_warn = loaded_warn;
	}

	public Integer getOut_relay_status() {
		return out_relay_status;
	}


	public void setOut_relay_status(Integer out_relay_status) {
		this.out_relay_status = out_relay_status;
	}
    
	public BigDecimal getA_vol() {
		return a_vol;
	}

	public void setA_vol(BigDecimal a_vol) {
		this.a_vol = a_vol;
	}

	public BigDecimal getB_vol() {
		return b_vol;
	}

	public void setB_vol(BigDecimal b_vol) {
		this.b_vol = b_vol;
	}

	public BigDecimal getC_vol() {
		return c_vol;
	}

	public void setC_vol(BigDecimal c_vol) {
		this.c_vol = c_vol;
	}

	public BigDecimal getA_current() {
		return a_current;
	}

	public void setA_current(BigDecimal a_current) {
		this.a_current = a_current;
	}

	public BigDecimal getB_current() {
		return b_current;
	}

	public void setB_current(BigDecimal b_current) {
		this.b_current = b_current;
	}

	public BigDecimal getC_current() {
		return c_current;
	}

	public void setC_current(BigDecimal c_current) {
		this.c_current = c_current;
	}

	public BigDecimal getMax_out_vol() {
		return max_out_vol;
	}

	public void setMax_out_vol(BigDecimal max_out_vol) {
		this.max_out_vol = max_out_vol;
	}

	public BigDecimal getMin_out_vol() {
		return min_out_vol;
	}

	public void setMin_out_vol(BigDecimal min_out_vol) {
		this.min_out_vol = min_out_vol;
	}

	public BigDecimal getMax_out_current() {
		return max_out_current;
	}

	public void setMax_out_current(BigDecimal max_out_current) {
		this.max_out_current = max_out_current;
	}
	
 }


