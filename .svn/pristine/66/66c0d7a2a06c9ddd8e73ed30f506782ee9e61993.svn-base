package com.wanma.model;

import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



/**
 * 停车办电桩实体类
 */
public class TcbElectric {
	private String operatorId="310000002";// 运营服务商标识
	private Integer pkElc;
	private String operType;// 操作类型1-新增,2-修改,3-删除
	private String equipNo;// 充电桩编号
	private String equipName;// 充电桩名称
	private String equipAddr;// 充电桩地址
	private String stationNo;//充电站编号
	
	private String equipType;
	private String madeDate;
	private String elcLng;
	private String elcLat;
	private String runDate;
	private String powerRating;// 额定功率
	private String currentRated;// 额定电流
	private String voltageRated;// 额定电压
	private String autoMaxVolt;// 车辆最大允许充电电压
	private String equipStatus;// 充电桩状态01：新购；02：待投运；03：运行；04：故障；05：故障待处理；06：故障待报废；07：报废；08:维护
	private String gunNum;// 枪口数量
	private String haveConnectLine;// 是否带连接线
	private String deleteFlag;// 删除标志
	private String headInfo;// 枪头信息字符串
	private List<ElectricHead> guns;
	
	private String cityCode;

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public Integer getPkElc() {
		return pkElc;
	}

	public void setPkElc(Integer pkElc) {
		this.pkElc = pkElc;
	}

	public String getElcLng() {
		return elcLng;
	}

	public void setElcLng(String elcLng) {
		this.elcLng = elcLng;
	}

	public String getElcLat() {
		return elcLat;
	}

	public void setElcLat(String elcLat) {
		this.elcLat = elcLat;
	}

	public String getOperType() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date ud=null,ad=null;
		try {
			ud = sdf.parse(runDate);
			ad=sdf.parse(madeDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (deleteFlag.equals("1")) {
			operType="3";// 删除
		} else if (System.currentTimeMillis()-ud.getTime()<=24*3600*1000L&&
				System.currentTimeMillis()-ad.getTime()>24*3600*1000L) {
			operType="2";// 当创建日期大于1天且修改日期小于1天的才是修改
		} else {
			operType="1";;// 新增
		}
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	public String getEquipNo() {
		return equipNo;
	}

	public void setEquipNo(String equipNo) {
		this.equipNo = equipNo;
	}

	public String getEquipName() {
		return equipName;
	}

	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}
	public String getStationNo() {
		return stationNo;
	}

	public void setStationNo(String stationNo) {
		this.stationNo = stationNo;
	}

	public String getEquipAddr() {
		return equipAddr;
	}

	public void setEquipAddr(String equipAddr) {
		this.equipAddr = equipAddr;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getEquipType() {
		return equipType;
	}

	public void setEquipType(String equipType) {
		this.equipType = equipType;
	}

	public String getMadeDate() {
		return madeDate;
	}

	public void setMadeDate(String madeDate) {
		this.madeDate = madeDate;
	}

	public String getRunDate() {
		return runDate;
	}

	public void setRunDate(String runDate) {
		this.runDate = runDate;
	}

	public String getPowerRating() {
		return powerRating;
	}

	public void setPowerRating(String powerRating) {
		this.powerRating = powerRating;
	}

	public String getCurrentRated() {
		return currentRated;
	}

	public void setCurrentRated(String currentRated) {
		this.currentRated = currentRated;
	}

	public String getVoltageRated() {
		return voltageRated;
	}

	public void setVoltageRated(String voltageRated) {
		this.voltageRated = voltageRated;
	}

	public String getAutoMaxVolt() {
		return autoMaxVolt;
	}

	public void setAutoMaxVolt(String autoMaxVolt) {
		this.autoMaxVolt = autoMaxVolt;
	}

	public String getEquipStatus() {
		return equipStatus;
	}

	public void setEquipStatus(String equipStatus) {
		this.equipStatus = equipStatus;
	}

	public String getGunNum() {
		return gunNum;
	}

	public void setGunNum(String gunNum) {
		this.gunNum = gunNum;
	}

	public String getHaveConnectLine() {
		return haveConnectLine;
	}

	public void setHaveConnectLine(String haveConnectLine) {
		this.haveConnectLine = haveConnectLine;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public List<ElectricHead> getGuns() {
		return getGuns(headInfo);
	}

	public void setGuns(List<ElectricHead> guns) {
		this.guns = guns;
	}

	public String getHeadInfo() {
		return headInfo;
	}

	public void setHeadInfo(String headInfo) {
		this.headInfo = headInfo;
	}

	public List<ElectricHead> getGuns(String headInfo) {
		if(StringUtils.isBlank(headInfo))return null;
		// 格式1:1号枪头,2:2号枪头,3:3号枪头,4:4号枪头
		String[] heads = headInfo.split(",");
		ElectricHead head = null;
		List<ElectricHead> guns= Lists.newArrayList();
		for (String s : heads) {
			head = new ElectricHead();
			String[] array = s.split(":");
			head.setGunNo(array[0]);
			head.setGunName(array[1]);
			head.setGunEquipType(equipType);
			String connectorType="1";
			if(equipType.equals("01")){
				if(haveConnectLine.equals("1")){
					connectorType="3";
				}else{
					connectorType="2";
				}
			}else{
				connectorType="4";
			}
			head.setConnectorType(connectorType);
			guns.add(head);
		}
		return guns;
	}

	class ElectricHead {
		private String gunNo;
		private String gunName;
		private String gunEquipType;// 01-交流式 02-直流式
		private String connectorType;// 充电接口类型1：家用插座,2：交流接口插座（不带枪）,3：交流接口插头（带枪线）,4：直流接口枪头（带枪线）

		public String getGunNo() {
			return gunNo;
		}

		public void setGunNo(String gunNo) {
			this.gunNo = gunNo;
		}

		public String getGunName() {
			return gunName;
		}

		public void setGunName(String gunName) {
			this.gunName = gunName;
		}

		public String getGunEquipType() {
			return gunEquipType;
		}

		public void setGunEquipType(String gunEquipType) {
			this.gunEquipType = gunEquipType;
		}

		public String getConnectorType() {
			return connectorType;
		}

		public void setConnectorType(String connectorType) {
			this.connectorType = connectorType;
		}

	}
}
