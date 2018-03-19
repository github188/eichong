package com.wanma.app.dao;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblChargeinfo;


/**
 * 数据访问接口
 * 
 */
public interface ChargeMapper {

	
	/**
	 * 获取实时充电信息
	 * @return
	 */
	public TblChargeinfo get(TblChargeinfo tblChargeinfo);
	
	public List<TblChargeinfo> find(TblChargeinfo tblChargeinfo);
	
	/**
	 * 更新电桩状态
	 * @return
	 */
	public void update(TblChargeinfo tblChargeinfo);
	
	/**
	 * 从交流表获取充电实时信息
	 * @param userId
	 * @return
	 */
	public List<Map<String, Object>> getChangeInfoFromAC(int userId);
	
	/**
	 * 从直流表获取充电实时信息
	 * @param userId
	 * @return
	 */
	public List<Map<String, Object>> getChangeInfoFromDC(int userId);
	
	/**
	 * 获取直流桩在充电前的自检状态
	 * @param params
	 * 			epCode 电桩编号 ，ephCode 枪口编号
	 * @return
	 */
	public int getDCSelfCheckStatus(Map<String, String> params);
}
