package com.epcentre.service;

import java.math.BigDecimal;
import java.util.Date;


public class DcRealData {	
	//实时信息
	//1  充电机编号
	//11：M_ME_NB_1  压缩 BCD 码  8Byte
	private String ep_code;
	
	//枪口编号
	private Integer ep_gun_no;
	
	//3  充电机输出电压(11：M_ME_NB_1)(  BIN 码  2Byte)(精确到小数点后一位)
	private BigDecimal output_voltage;
	
	
	//4  充电机输出电流(	//11：M_ME_NB_1)  (BIN 码  2Byte)(精确到小数点后二位)
	private BigDecimal output_current;
	
	//5 充电机状态(11：M_ME_NB_1 ) (压缩 BCD 码  2Byte)	变化上传，0001- 告警 0002-待机 0003- 工作  0004- 离线0005-完成
	private Integer working_status;
    //6车位地锁状态
    private Integer  car_Place_Lock_Status;
	//7  有功总电度 132：M_MD_NA_1  BIN 码  4Byte 精确到小数点后一位
	private BigDecimal total_active_meter_num;

	//8已充金额
	private BigDecimal chargedCost;
	//9当前电价
	private BigDecimal chargePrice;
	//10已充度数
    private BigDecimal chargedMeterNum;
    //11累计充电时间(11：M_ME_NB_1)(BIN 码  2Byte)(单位：min)
  	private Integer total_charge_time;
    //12估计剩余时间(11：M_ME_NB_1)(BIN 码  2Byte)(单位：min)
  	private Integer remain_time;
    //13 是否连接电池	1：M_SP_NA_1  BIN 码  1Byte	布尔型,  变化上传
  	private Integer connect_battry;
    //14枪座状态
    private Integer gun_close_status;
    //15充电枪盖状态
 	private Integer gun_lid_status;//
 	
 	//16车与桩建立通信信号
 	private Integer gun2car_comm_status;	
 	//17车位占用状态
    private Integer car_place_status;
    //18  读卡器通讯异常故障 1：M_SP_NA_1  BIN 码  1Byte	布尔型,  变化上传； 0 正常， 1异常
  	private Integer readCardCommException;
    //19  急停按钮动作故障 1：M_SP_NA_1  BIN 码  1Byte 布尔型,  变化上传； 0 正常， 1异常
  	private Integer urgentBtnException;
    //20  避雷器故障	1：M_SP_NA_1  BIN 码  1Byte	布尔型,  变化上传； 0 正常， 1异常 
  	private Integer arresterException;
    //21  绝缘监测故障1：M_SP_NA_1  BIN 码  1Byte布尔型,  变化上传； 0 正常， 1异常
  	private Integer insulationException;
    //22  充电枪未连接告警	1：M_SP_NA_1  BIN 码  1Byte	布尔型,  变化上传； 0 正常， 1异常
  	private Integer gun_unlinked_warn;
    //23  交易记录已满告警 1：M_SP_NA_1  BIN 码  1Byte	布尔型,  变化上传； 0 正常， 1异常
  	private Integer transRecordFullWarn;
    //24  电度表异常故障1：M_SP_NA_1  BIN 码  1Byte布尔型,  变化上传； 0 正常， 1异常
  	private Integer meterException;
    //25交流输入过压告警
  	private Integer in_vol_warn;
  	
    //27 充电机过温故障	1：M_SP_NA_1  BIN 码  1Byte	布尔型,  变化上传； 0 正常， 1异常
  	private Integer ChargerOverTempWarn;
  	//28交流电流过负荷告警
  	private Integer loaded_warn;
    //28输出继电器状态
  	private Integer out_relay_status;
    //29  SOC(11：M_ME_NB_1)(  BIN 码  2Byte)整型
  	private Integer soc;
   //30  电池组最低温度(11：M_ME_NB_1)  (BIN 码  2Byte)	精确到小数点后一位
  	private BigDecimal bp_lowest_temperature;
	//31  电池组最高温度(11：M_ME_NB_1)  (BIN 码  2Byte)	精确到小数点后一位
  	private BigDecimal bp_highest_temperature;
    //32  电池反接故障1：M_SP_NA_1  BIN 码  1Byte	布尔型,  变化上传； 0 正常， 1	异常
  	private Integer battry_error_link;
    //33  烟雾报警告警	1：M_SP_NA_1  BIN 码  1Byte	布尔型,  变化上传； 0 正常， 1	异常
  	private Integer fogsWarn;
	//34  BMS 通信异常 1：M_SP_NA_1  BIN 码  1Byte 布尔型,  变化上传； 0 正常， 1异常
	private Integer bms_comm_exception;
	//35  直流电度表异常故障1：M_SP_NA_1  BIN 码  1Byte布尔型,  变化上传； 0 正常， 1异常
  	private Integer dcMeterException;
    //36 充电模式1：M_SP_NA_1  BIN 码  1Byte布尔型,  
  	private Integer charge_model;
  	//37 soc告警1：M_SP_NA_1  BIN 码  1Byte布尔型,  
  	private Integer soc_warn;
    //38 蓄电池模块采样点过温告警1：M_SP_NA_1  BIN 码  1Byte布尔型,  
  	private Integer over_battery_temp_warn;
    //39 输出连接器过温1：M_SP_NA_1  BIN 码  1Byte布尔型,  
  	private Integer over_link_temp_warn;
    //40 整车动力蓄电池组输出连接器连接状态1：M_SP_NA_1  BIN 码  1Byte布尔型,  
  	private Integer out_link_status;
   
	//41  蓄电池充电过流告警	1：M_SP_NA_1  BIN 码  1Byte	布尔型,  变化上传；0：不过	流，1 过流
	private Integer over_current_warn;
	//42  直流母线输出过压告警1：M_SP_NA_1  BIN 码  1Byte布尔型,  变化上传；0：不过压，1 过压,2欠压
	private Integer out_vol_warn;
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

	public Integer getCar_Place_Lock_Status() {
		return car_Place_Lock_Status;
	}


	public void setCar_Place_Lock_Status(Integer car_Place_Lock_Status) {
		this.car_Place_Lock_Status = car_Place_Lock_Status;
	}

	public BigDecimal getTotal_active_meter_num() {
		return total_active_meter_num;
	}

	public void setTotal_active_meter_num(BigDecimal total_active_meter_num) {
		this.total_active_meter_num = total_active_meter_num;
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

	public Integer getTotal_charge_time() {
		return total_charge_time;
	}

	public void setTotal_charge_time(Integer total_charge_time) {
		this.total_charge_time = total_charge_time;
	}

	public Integer getRemain_time() {
		return remain_time;
	}

	public void setRemain_time(Integer remain_time) {
		this.remain_time = remain_time;
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

	public void setCar_place_status(Integer car_place_status) {
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
	public void setIn_vol_warn(Integer in_vol_warn) {
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


	public Integer getSoc() {
		return soc;
	}

	public void setSoc(Integer soc) {
		this.soc = soc;
	}


	public BigDecimal getBp_lowest_temperature() {
		return bp_lowest_temperature;
	}

	public void setBp_lowest_temperature(BigDecimal bp_lowest_temperature) {
		this.bp_lowest_temperature = bp_lowest_temperature;
	}


	public BigDecimal getBp_highest_temperature() {
		return bp_highest_temperature;
	}

	public void setBp_highest_temperature(BigDecimal bp_highest_temperature) {
		this.bp_highest_temperature = bp_highest_temperature;
	}

	public Integer getBattry_error_link() {
		return battry_error_link;
	}

	public void setBattry_error_link(Integer battry_error_link) {
		this.battry_error_link = battry_error_link;
	}


	public Integer getFogsWarn() {
		return fogsWarn;
	}

	public void setFogsWarn(Integer fogsWarn) {
		this.fogsWarn = fogsWarn;
	}


	public Integer getBms_comm_exception() {
		return bms_comm_exception;
	}

	public void setBms_comm_exception(Integer bms_comm_exception) {
		this.bms_comm_exception = bms_comm_exception;
	}

	public Integer getDcMeterException() {
		return dcMeterException;
	}


	public void setDcMeterException(Integer dcMeterException) {
		this.dcMeterException = dcMeterException;
	}

	public Integer getCharge_model() {
		return charge_model;
	}

	public void setCharge_model(Integer charge_model) {
		this.charge_model = charge_model;
	}

	public Integer getSoc_warn() {
		return soc_warn;
	}

	public void setSoc_warn(Integer soc_warn) {
		this.soc_warn = soc_warn;
	}
	public Integer getOver_battery_temp_warn() {
		return over_battery_temp_warn;
	}


	public void setOver_battery_temp_warn(Integer over_battery_temp_warn) {
		this.over_battery_temp_warn = over_battery_temp_warn;
	}

	public Integer getOver_link_temp_warn() {
		return over_link_temp_warn;
	}

	public void setOver_link_temp_warn(Integer over_link_temp_warn) {
		this.over_link_temp_warn = over_link_temp_warn;
	}

	public Integer getOut_link_status() {
		return out_link_status;
	}

	public void setOut_link_status(Integer out_link_status) {
		this.out_link_status = out_link_status;
	}

	public Integer getOver_current_warn() {
		return over_current_warn;
	}

	public void setOver_current_warn(Integer over_current_warn) {
		this.over_current_warn = over_current_warn;
	}

	public Integer getOut_vol_warn() {
		return out_vol_warn;
	}

	public void setOut_vol_warn(Integer out_more_vol_warn) {
		this.out_vol_warn = out_more_vol_warn;
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

	@Override
	public String toString() {
			
			final StringBuilder sb = new StringBuilder();
	        sb.append("RealAcChargeInfo");
	        sb.append(",{epcode=").append(this.ep_code).append("}\n");
	        sb.append(",{epgunno=").append(this.ep_gun_no).append("}\n");
	        sb.append(",{output_voltage(输出电压)=").append(output_voltage).append("}\n");
	        sb.append(",{output_current(输出电流)=").append(output_current).append("}\n");
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
	        sb.append(",{total_charge_time(累计充电时间)=").append(total_charge_time).append("}\n");
	        sb.append(",{remain_time(剩余时间)=").append(remain_time).append("}\n");
	        sb.append(",{chargedMeterNum(已充度数)=").append(chargedMeterNum).append("}\n");
	        sb.append(",{chargePrice(当前电价)=").append(chargePrice).append("}\n");
	        sb.append(",{chargedCost(已充金额)=").append(chargedCost).append("}\n");
	        sb.append(",{total_active_dbnum(有功总电度)=").append(connect_battry).append("}\n");
	        sb.append(",{car_Place_Lock_Status(车位地锁状态)=").append(connect_battry).append("}\n");
	        sb.append(",{soc(soc)=").append(soc).append("}\n");
	        sb.append(",{bp_lowest_temperature(电池组最低温度)=").append(bp_lowest_temperature).append("}\n");
	        sb.append(",{bp_highest_temperature(电池组最高温度)=").append(bp_highest_temperature).append("}\n");
	        sb.append(",{battry_error_link(电池反接故障)=").append(battry_error_link).append("}\n");
	        sb.append(",{fogsWarn(烟雾报警告警)=").append(fogsWarn).append("}\n");
	        sb.append(",{bms_comm_exception(BMS 通信异常)=").append(bms_comm_exception).append("}\n");
	        sb.append(",{dcMeterException(直流电度表异常故障)=").append(dcMeterException).append("}\n");
	        sb.append(",{charge_model(充电模式)=").append(charge_model).append("}\n");
	        sb.append(",{soc_warn(soc告警)=").append(soc_warn).append("}\n");
	        sb.append(",{over_battery_temp_warn(蓄电池模块采样点过温告警)=").append(over_battery_temp_warn).append("}\n");
	        sb.append(",{over_link_temp_warn(输出连接器过温)=").append(over_link_temp_warn).append("}\n");
	        sb.append(",{out_link_status(整车动力蓄电池组输出连接器连接状态)=").append(out_link_status).append("}\n");
	        sb.append(",{over_current_warn(蓄电池充电过流告警)=").append(over_current_warn).append("}\n");
	        sb.append(",{out_vol_warn(直流母线输出过压告警)=").append(out_vol_warn).append("}\n");
	      
	        return sb.toString();
		}

}