package com.wanma.service;

import java.util.List;
import java.util.Map;


/**
 * @Description: 充电统计
 * @author 
 */
public interface StatisticFaultService {

	/**
	 * 
	 * 故障统计
	 * (统计： v1 故障电桩数 v2 离线电桩数 v3 违规拔枪v4 BMS通信异常 v5 电表异常v6急停v7 接触器故障v8 过流停止v9过压停止v10 防雷器故障
            v11 连接线断掉v12 漏电保护器v13电桩断电v14 ccu故障v15 显示屏故障v16 电源模块故障 v17充电金额不足 v18自动充满 v19车主动停止)
	 * @return
	 */
	public Map<String,Object> queryFaultDataCount(Map<String,Object> param);
	
	/**
	 * 
	 * 故障统计
	 * 
	 * @return
	 */
	public Integer countChargeFaultDetail(Map<String,Object> params);
	/**
	 * 
	 * 故障统计 明细
	 * (date:时间 v1 故障名称 ,v2桩体编号,v3电桩地址 )
	 * @return
	 */
	public List<Map<String,Object>> queryChargeFaultDetail(Map<String,Object> params);
}
