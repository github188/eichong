/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.ui.ModelMap;

import com.wanma.model.PowerStationDetail;
import com.wanma.model.PowerStationElictric;
import com.wanma.model.TblPowerstation;
import com.wanma.model.TblRateinformation;

/**
 * 反馈信息业务处理接口
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public interface WebPowerStationDetailService {

	/**
	 * 获取电桩列表
	 */
	public PowerStationDetail getPowerStationDetail(TblPowerstation tblPowerstation);

	public Map<String, Integer> getPileCount(List<PowerStationElictric> powerElectricpileList);

	public void makeFengzhiPrice(ModelMap map, String elictricPicId);

}
