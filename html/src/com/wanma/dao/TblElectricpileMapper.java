
package com.wanma.dao;

import com.wanma.model.PileState;
import com.wanma.model.TblElectricpile;
import com.wanma.model.TcbElectric;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TblElectricpileMapper {

	public TblElectricpile getElectricpileDetail(TblElectricpile tblElectricpile);
	public List<TblElectricpile> getElectricpileList(TblElectricpile tblElectricpile);
	public Long getElectricpileListCount(TblElectricpile tblElectricpile);
	public void insert(TblElectricpile tblElectricpile);
	public void update(TblElectricpile tblElectricpile);
	public void updateByCode(TblElectricpile tblElectricpile);
	
	/**
	 * 获取地图模式电桩数据
	 * 
	 * @param feedback
	 */
	public List<TblElectricpile> getElectricpileListForMap(TblElectricpile tblElectricpile);
	public Integer getElectricpileListCountForMap(TblElectricpile tblElectricpile);

	

	/**
	 * 获取需要下线的桩体 
	 * @return
	 */
	public List<TblElectricpile> getOfflineElectric();
	

	
	/**
	 * 查询电桩以及电桩所有枪头
	 * @param tblElectricpile
	 * @return
	 */
	public List<Map<String, Object>> getElectricpileHeadN(TblElectricpile tblElectricpile);
	
	/**
	 * 查询电桩以及电桩数量
	 * @param tblElectricpile
	 * @return
	 */
	public int getCountElectricpileHeadN(TblElectricpile tblElectricpile);

	
	/**
	 * 查询桩体详情列表
	 * @param TblPowerstation
	 * @return
	 */
	public List<Map<String,Object>> selectDetailList(Map<String, Object> params);
	
	public TblElectricpile selectOne(TblElectricpile model);
	
	public List<TcbElectric> getElectricListByPsId(Integer pkPowerstation);

    List<PileState> queryOffLinePileByCompanyNumber(@Param("companyOrg") Integer companyOrg, @Param("stationId") Integer stationId);
	
    public Map<String, Object> getElectricpileInfo(Map<String, Object> map);
    
	/**
	 * 查询统计信息--电站累计电量
	 * @param model
	 * @return
	 */
	public String getStationMeterNum(Map<String, Object> model);
	
	/**
	 * 查询统计信息--电站内电桩、枪头累计电量
	 * @param model
	 * @return
	 */
	public List<Map<String, Object>> getEleMeterNum(Map<String, Object> model);
	
	/**
	 * 根据电站id查询电桩信息
	 * @param tblElectricpile
	 * @return
	 */
	public List<TblElectricpile> getElectricPileByPowerStationId(TblElectricpile tblElectricpile);
	
	/**
	 * 查询电桩详情
	 * @param pkPowerstation
	 * @return
	 */
	public List<TcbElectric> getElectricList(Map<String, Object> model);
	
	/**
	 * 检验电站是否在白名单里
	 * @param mapl
	 * @return
	 */
	public int checkPowerStation(Map<String, Object> mapl);
	
	/**
	 * 查询枪头电量
	 * @param hmap
	 * @return
	 */
	public List<Map<String, Object>> getHeadMeterNum(Map<String, Object> hmap);
	
	
}
