package com.wanma.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TcbStation {
	private String operType;//操作类型1-新增,2-修改,3-删除
	private String stationNo;
	private String stationName;
	private String stationAddr;
	private String cityCode;//城市编码
	private String countyCode;//区县编码
	private String operatorId="310000002";//运营服务商标识
	private String stationStatus;//充电站状态01未运营,02运营中 ,03-暂停运营
	private String rtLon;//经度
	private String rtLat;//纬度
	private String construction="255";//建设场所1：居民区,2：公共机构,3：企事业单位,4：写字楼,5：工业园区,6：交通枢纽,7：大型文体设施,8：城市绿地,9：大型建筑配建停车场,10：路边停车位,11：城际高速服务区,255：其他
	private String stationType="255";//站点类型1：公共,50：个人,100：公交（专用）,101：环卫（专用）,102：物流（专用）,103：出租车（专用）,255：其他
	private String madeDate;
	private String runDate;
	private String deleteFlag="0";// 删除标志
	
	public String getOperType() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date d=null;
		try {
			d = sdf.parse(madeDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (deleteFlag.equals("1")) {
			operType="3";// 删除
		} else if (System.currentTimeMillis()-d.getTime()<=24*3600*1000L) {
			operType="1";// 新增
		} else {
			operType="2";;// 修改
		}
		return operType;
	}
	public void setOperType(String operType) {
		this.operType = operType;
	}
	public String getStationNo() {
		return stationNo;
	}
	public void setStationNo(String stationNo) {
		this.stationNo = stationNo;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getStationAddr() {
		return stationAddr;
	}
	public void setStationAddr(String stationAddr) {
		this.stationAddr = stationAddr;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCountyCode() {
		return countyCode;
	}
	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public String getStationStatus() {
		return stationStatus;
	}
	public void setStationStatus(String stationStatus) {
		this.stationStatus = stationStatus;
	}
	public String getRtLon() {
		return rtLon;
	}
	public void setRtLon(String rtLon) {
		this.rtLon = rtLon;
	}
	public String getRtLat() {
		return rtLat;
	}
	public void setRtLat(String rtLat) {
		this.rtLat = rtLat;
	}
	public String getConstruction() {
		return construction;
	}
	public void setConstruction(String construction) {
		this.construction = construction;
	}
	public String getStationType() {
		return stationType;
	}
	public void setStationType(String stationType) {
		this.stationType = stationType;
	}
	public String getRunDate() {
		return runDate;
	}
	public void setRunDate(String runDate) {
		this.runDate = runDate;
	}
	
	
	
}
