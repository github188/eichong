package com.wanma.dao;

import java.util.List;
import java.util.Map;

import com.wanma.model.ElectricPileDetail;
import com.wanma.model.TblElectricpile;
import com.wanma.model.TblElectricpilehead;

public interface CmsElectricSearchMapper {
     /**
      * 电桩充电点地图查找模式
      * @param tblElectricpile
      * @return
      */
	public <K, V> List<Map<K, V>> getElectricpileForMap(TblElectricpile tblElectricpile);
	
	/**
	 * 通过Id获取电桩数据
	 * 
	 * @param feedback
	 */
	public <K, V> List<Map<K, V>> getElectricpileById(TblElectricpile tblElectricpile);

	public ElectricPileDetail getCurrentPileDetail(String eid);

	public List<TblElectricpilehead> getCurrentHeadList(String eid);

	public TblElectricpilehead getHeadDetail(Map<String, String> params);

	public List<String> queryErrorCodeListByUser(Map<String, String> params);
}
