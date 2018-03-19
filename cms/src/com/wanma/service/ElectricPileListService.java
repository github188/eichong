/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.pub.model.TblUser;
import com.wanma.model.ElectricPileList;
import com.wanma.model.PowerElectricPile;
import com.wanma.model.TblElectricpile;
import com.wanma.model.TblPowerstation;
import com.wanma.model.TblTypespan;

/**
 * 反馈信息业务处理接口
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public interface ElectricPileListService {

	/**
	 * 获取地图模式电桩列表
	 * 
	 * @param electricTypeId
	 *            汽车类型ID
	 * @param distance
	 *            距离 m
	 * @param price
	 *            价格
	 * @param evaluate
	 *            好评
	 */
	public List<ElectricPileList> getElectricPileList(
			TblPowerstation TblPowerstation, TblElectricpile tblElectricpile);

	/**
	 * 快速检索新接口
	 * 
	 * @param params
	 * @return
	 */
	public List<Map<String, String>> getElecSearchList(
			Map<String, String> params);

	/**
	 * 新接口获取列表模式电桩充电点
	 * 
	 * @param TblPowerstation
	 * @param tblElectricpile
	 * @return
	 */
	public List<ElectricPileList> getElectricPileListN(
			TblPowerstation TblPowerstation, TblElectricpile tblElectricpile);

	/**
	 * 后台电桩列表获取数据
	 */
	public List<TblElectricpile> getElectricpileByCondition(TblElectricpile tblElectricpile);

	/**
	 * 后台电桩列表获取总数据
	 */
	public long getElectricpileByConditionCount(TblElectricpile tblElectricpile);

	/**
	 * 后台电桩列表获取数据
	 */
	public List<?> getElectricpileByCondition1(TblElectricpile tblElectricpile);

	/**
	 * 后台电桩列表获取总数据
	 */
	public long getElectricpileByConditionCount1(TblElectricpile tblElectricpile);

	/**
	 * 添加电桩数据
	 */
	public void addElectricpile(TblElectricpile tblElectricpile,
			MultipartFile[] listImage, MultipartFile[] detailImage,
			TblUser loginUser);

	/**
	 * 修改电桩数据
	 */
	public void changeElectricpile(TblElectricpile tblElectricpile,
			MultipartFile[] listImage, MultipartFile[] detailImage,
			TblUser loginUser);

	/**
	 * 通过ID获取电桩数据
	 */
	public TblElectricpile getElectricpileById(TblElectricpile tblElectricpile);

	/**
	 * 电桩编号唯一性检查
	 * 
	 */
	public String checkElectricUnique(String elpiElectricpilecode);

	/**
	 * 修改电桩状态
	 * 
	 * @param feedback
	 */
	public void updateElectricPileSate(String elpiElectricpilecode,
			int elpiState,String companyNumber);

	/**
	 * 驳回电桩
	 */
	public long changeElectricPileExamineReason(TblElectricpile tblElectricpile);

	/**
	 * 电桩绑定充电点
	 */
	public void electricPileBindPower(TblElectricpile tblElectricpile);

	public List<?> findRelevancePowerStation(TblElectricpile tblElectricpile);

	/**
	 * 删除电桩
	 */
	public void removeElectricPile(TblElectricpile tblElectricpile);

	/**
	 * 获取电桩表所有数据
	 * 
	 * @param epId
	 * @return
	 */
	public List<Map<String, Object>> initEpFromDB(int epId);

	/**
	 * 根据电桩编号获取电桩信息
	 * 
	 * @param feedback
	 */
	public TblElectricpile getElectricPileByCode(String elpiElectricpilecode);

	/**
	 * 修改电桩状态，电桩编号
	 * 
	 * @param elpiElectricpilecode
	 *            电桩编号
	 * @param elpiState
	 *            电桩状态
	 */
	public String updateSateAndCode(String elpiElectricpilecode, int elpiState);

	/**
	 * 获取需要下线的桩体
	 * @return
	 */
	public List<TblElectricpile> getOfflineElectric();
	
	/**
	 * 根据id更新电桩状态
	 * @return
	 */
	public int updateEleState(TblElectricpile tblElectricpile);
	
	/**
	 * 查询电桩以及电桩所有枪头
	 * @param tblElectricpile
	 * @return
	 */
	public List<PowerElectricPile> getElectricPileHeadList(TblElectricpile tblElectricpile);
	/**
	 * 查询电桩以及电桩数量
	 * @param tblElectricpile
	 * @return
	 */
	public int getCountElectricPileHeadList(TblElectricpile tblElectricpile);

	public String getAreaTree();

	public List<?> getRateElectricpileByCondition(
			TblElectricpile tblElectricpile);

	public long getRateElectricpileByConditionCount(
			TblElectricpile tblElectricpile);

	public List<?> getElectricpileForConcentrator(TblElectricpile tblElectricpile);

	public long getElectricpileForConcentratorCount(
			TblElectricpile tblElectricpile);

	public boolean isBespokeOrCharging(TblElectricpile tblElectricpile);
	
	public List<TblTypespan> getTypespanList();

	public void updateByElecId(TblElectricpile electric);

	public void updateStateByPowerIds(TblElectricpile electricpile); 
}
