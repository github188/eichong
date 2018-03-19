/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.dubbox.dao;

import java.util.List;
import java.util.Map;

import com.wanma.dubbox.model.TblElectricpile;


/**
 * 反馈表操作用DAO接口Mapper
 * 
 * @version V1.0
 * @author lhy
 */
public interface TblElectricpileMapper {

	/**
	 * 分享电桩信息
	 * 
	 * @param feedback
	 */
	public TblElectricpile getShareElectricPile(TblElectricpile tblElectricpile);

	/**
	 * 获取电桩数据
	 * 
	 * @param feedback
	 */
	public List<?> getElectricpile(TblElectricpile tblElectricpile);

	/**
	 * 新的快速查询接口
	 * @param params
	 * @return
	 */
	public List<Map<String, String>> getSearchElecPileList(Map<String, String> params);
	/**
	 * 新接口后去电桩列表
	 * @param tblElectricpile
	 * @return
	 */
	public List<?> getElectricpileN(TblElectricpile tblElectricpile);
	/**
	 * 获取地图模式电桩数据
	 * 
	 * @param feedback
	 */
	public List<?> getElectricpileForMap(TblElectricpile tblElectricpile);

	/**
	 * 获取地图锚点简介
	 * @param params
	 * 		eid 电桩id，lat 纬度，lng 经度
	 * @return
	 */
	public Map<String, String> getAnchorSummaryEp(Map<String, Object> params);
	/**
	 * 通过Id获取电桩数据
	 * 
	 * @param feedback
	 */
	public List<TblElectricpile> getElectricpileById(TblElectricpile tblElectricpile);

	/**
	 * 通过Id获取电桩数据
	 * 
	 * @param feedback
	 */
	public List<TblElectricpile> findOne(TblElectricpile tblElectricpile);

	/**
	 * 通过Id获取电桩数据
	 * 
	 * @param feedback
	 */
	public List<TblElectricpile> findOneN(TblElectricpile tblElectricpile);
	
	/**
	 * 通过枪头Id获取电桩数据
	 * 
	 * @param params
	 */
	public <T, K, V> T getbyPkElecpileHead(Map<K, V> params);

	/**
	 * 后台电桩列表获取数据
	 * 
	 * @param feedback
	 */
	public List<?> getElectricpileByCondition(TblElectricpile tblElectricpile);

	/**
	 * 后台电桩列表获取数据
	 * 
	 * @param feedback
	 */
	public long getElectricpileByConditionCount(TblElectricpile tblElectricpile);

	/**
	 * 添加电桩数据
	 * 
	 * @param feedback
	 */
	public void insert(TblElectricpile tblElectricpile);

	/**
	 * 修改电桩数据
	 * 
	 * @param feedback
	 */
	public void update(TblElectricpile tblElectricpile);

	/**
	 * 电桩绑定电站
	 * 
	 * @param feedback
	 */
	public void electricPileBindPower(TblElectricpile tblElectricpile);

	/**
	 * 通过ID获取桩体数据
	 * 
	 * @param feedback
	 */
	public TblElectricpile get(String eId);

	/**
	 * 通过电桩编号获取电桩信息
	 * 
	 * @param feedback
	 */
	public long searchElectricCountByElecCode(TblElectricpile tblElectricpile);

	/**
	 * 修改电桩状态
	 * 
	 * @param feedback
	 */
	public long updateElectricPileSate(TblElectricpile tblElectricpile);

	/**
	 * 审核驳回电桩
	 * 
	 * @param feedback
	 */
	public long updateElectricPileExamineReason(TblElectricpile tblElectricpile);

	/**
	 * 后台电桩列表获取数据
	 * 
	 * @param feedback
	 */
	public List<?> getElectricpileByCondition1(TblElectricpile tblElectricpile);

	/**
	 * 后台电桩列表获取数据
	 * 
	 * @param feedback
	 */
	public long getElectricpileByConditionCount1(TblElectricpile tblElectricpile);

	/**
	 * @Title: selectPileInfo
	 * @Description: 手机端扫描二维码显示电桩和枪口信息
	 * @param params
	 * @return
	 */
	public <T, K, V> T selectPileInfo(Map<K, V> params);

	/**
	 * @Description: 手机端扫描二维码显示电桩和枪口信息
	 * @param params
	 * @return
	 */
	public <T, K, V> T zNumSelPileInfo(String zCodeNum);
	
	public List<?> findRelevancePowerStation(TblElectricpile tblElectricpile);

	/**
	 * 根据电桩编号获取电桩信息
	 * 
	 * @param feedback
	 */
	public TblElectricpile getElectricPileByCode(String elpiElectricpilecode);
	/**
	 * 修改电桩数据
	 * 
	 * @param feedback
	 */
	public void updateByElecId(TblElectricpile tblElectricpile);
	/**
	 * 删除电桩数据
	 * 
	 * @param feedback
	 */
	public void delete(TblElectricpile tblElectricpile);
	/**
	 * 获取电站所属电桩数量
	 * @param pkElectricpile
	 * @return
	 */
	public int getElectricpileByPowerId(TblElectricpile tblElectricpile);
	
	/**
	 * 获取电桩表中的所有记录
	 * @param epId 暂时不用，以后可能会根据id获取某条记录
	 * @return
	 */
	public List<Map<String, Object>> initEpFromDB(int epId);
	/**
	 * 获取电桩GATE服务器列表
	 * 
	 * @param feedback
	 */
	public List<TblElectricpile> getElectricPileGateList();
	/**
	 * 根据用户id获取汽车的 直交流和接口标准
	 * @param userId
	 * @return
	 */
	public Map<String, String> getPileConditionByUserId(int userId);

	public int getPileState(int pileId);
	
	/**
	 * 获取有电桩的城市行政区域代码
	 * @return
	 */
	public List<Map<String, String>> getEpCityCode();
}
