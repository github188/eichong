/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.app.service;

import java.util.List;
import java.util.Map;

import com.wanma.model.PowerStationDetail;
import com.wanma.model.TblElectricpile;
import com.wanma.model.TblPowerstation;

/**
 * 反馈信息业务处理接口
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public interface PowerStationDetailService {
	
	/**
	 * @Title: getSharePowerstation
	 * @Description: 查询分享电站信息
	 * @param TblPowerstation
	 * @return
	 */
	public TblPowerstation getSharePowerstation(TblPowerstation tblPowerstation);
	
	/**
	 * @Title: searchCount
	 * @Description: 查询分享电站信息
	 * @param TblPowerstation
	 * @return
	 */
	public long searchCount(TblPowerstation tblPowerstation);

	/**
	 * 获取电桩列表
	 */
	public List<PowerStationDetail> getPowerStationDetail(TblPowerstation tblPowerstation);

	/**
	 * 获取站点详情
	 */
	public Map<String, Object> getDetailById(TblPowerstation tblPowerstation);

}
