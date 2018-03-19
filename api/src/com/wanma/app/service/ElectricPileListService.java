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

import org.springframework.web.multipart.MultipartFile;

import com.bluemobi.product.model.UserModel;
import com.wanma.model.ElectricPileList;
import com.wanma.model.TblElectricpile;
import com.wanma.model.TblPowerstation;

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
	 * @param electricTypeId 汽车类型ID
	 * @param distance 距离 m
	 * @param price 价格
	 * @param evaluate 好评
	 */
	public List<ElectricPileList>  getElectricPileList(TblPowerstation TblPowerstation,TblElectricpile tblElectricpile);
	/**
	 * 快速检索新接口
	 * @param params
	 * @return
	 */
	public List<Map<String, String>> getElecSearchList(Map<String, String> params);
	/**
	 * 新接口获取列表模式电桩电站
	 * @param TblPowerstation
	 * @param tblElectricpile
	 * @return
	 */
	public List<ElectricPileList>  getElectricPileListN(TblPowerstation TblPowerstation,TblElectricpile tblElectricpile);
	/**
	 * 后台电桩列表获取数据
	 */
	public List<?> getElectricpileByCondition(TblElectricpile tblElectricpile);
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
	//public void addElectricpile(TblElectricpile tblElectricpile,MultipartFile[] listImage,MultipartFile[] detailImage,UserModel loginUser) ;
	/**
	 * 修改电桩数据
	 */
	//public void changeElectricpile(TblElectricpile tblElectricpile,MultipartFile[] listImage,MultipartFile[] detailImage,UserModel loginUser);
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
	 *  修改电桩状态 
	 * @param feedback
	 */
	public void updateElectricPileSate(String elpiElectricpilecode,int elpiState) ;
	/**
	 * 驳回电桩
	 */
	public long changeElectricPileExamineReason(TblElectricpile tblElectricpile);
	/**
	 * 电桩绑定电站
	 */
	public void electricPileBindPower(TblElectricpile tblElectricpile);

	public List<?> findRelevancePowerStation(TblElectricpile tblElectricpile);
	/**
	 * 删除电桩
	 */
	public void removeElectricPile(TblElectricpile tblElectricpile);
	
	/**
	 * 获取电桩表所有数据
	 * @param epId
	 * @return
	 */
	public List<Map<String, Object>> initEpFromDB(int epId);
	/**
	 * 获取电桩GATE服务器列表
	 * 
	 * @param feedback
	 */
	public List<TblElectricpile> getElectricPileGateList();
}
