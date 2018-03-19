
package com.wanma.model;



public class RateInfo  {
	
	 private Integer rateId;//费率表id
	 private String raInQuantumDate;//采用JSON形式存储实际格式
	 private String raInTimeMarker;//时段标志
	 private String raInTipTimeTariff;//尖时段电价
	 private String raInPeakElectricityPrice;//峰时段电价
	 private String raInUsualPrice;//平时段电价
	 private String raInValleyTimePrice;//谷时段电价
	 private String raInServiceCharge;//服务费
	 
	public Integer getRateId() {
		return rateId;
	}
	public void setRateId(Integer rateId) {
		this.rateId = rateId;
	}
	public String getRaInQuantumDate() {
		return raInQuantumDate;
	}
	public void setRaInQuantumDate(String raInQuantumDate) {
		this.raInQuantumDate = raInQuantumDate;
	}
	public String getRaInTimeMarker() {
		return raInTimeMarker;
	}
	public void setRaInTimeMarker(String raInTimeMarker) {
		this.raInTimeMarker = raInTimeMarker;
	}
	public String getRaInTipTimeTariff() {
		return raInTipTimeTariff;
	}
	public void setRaInTipTimeTariff(String raInTipTimeTariff) {
		this.raInTipTimeTariff = raInTipTimeTariff;
	}
	public String getRaInPeakElectricityPrice() {
		return raInPeakElectricityPrice;
	}
	public void setRaInPeakElectricityPrice(String raInPeakElectricityPrice) {
		this.raInPeakElectricityPrice = raInPeakElectricityPrice;
	}
	public String getRaInUsualPrice() {
		return raInUsualPrice;
	}
	public void setRaInUsualPrice(String raInUsualPrice) {
		this.raInUsualPrice = raInUsualPrice;
	}
	public String getRaInValleyTimePrice() {
		return raInValleyTimePrice;
	}
	public void setRaInValleyTimePrice(String raInValleyTimePrice) {
		this.raInValleyTimePrice = raInValleyTimePrice;
	}
	public String getRaInServiceCharge() {
		return raInServiceCharge;
	}
	public void setRaInServiceCharge(String raInServiceCharge) {
		this.raInServiceCharge = raInServiceCharge;
	}
	
	 
	
}
