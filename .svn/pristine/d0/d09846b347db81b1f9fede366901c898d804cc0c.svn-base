package com.wanma.dao;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblEquipmentrepair;

/**
 * 设备报修
 * 
 * @author xiay
 *
 */
public interface CmsEquipmentrepairMapper {
	/**
	 * 取得设备报修列表
	 * 
	 * @return
	 */
	public List<TblEquipmentrepair> getEquipList();
	
	/**
	 * 查询设备报修个数
	 * 
	 * @param tblEquipmentrepair
	 * @return
	 */
	public long searchEquipCount(TblEquipmentrepair tblEquipmentrepair);
	
	/**
	 * 查询设备报修列表
	 * 
	 * @param tblEquipmentrepair
	 * @return
	 */
	public List<TblEquipmentrepair> searchEquipList(TblEquipmentrepair tblEquipmentrepair);
	
	/**
	 * 添加设备报修
	 * 
	 * @param tblEquipmentrepair
	 * @return
	 */
	public int insertEquip(TblEquipmentrepair tblEquipmentrepair);
	
	/**
	 * 修改状态
	 * 
	 * @param tblEquipmentrepair
	 * @return
	 */
	public int updateEquipStage(TblEquipmentrepair record);

	/**
	 * 导出设备报修清单
	 * @param paramsModel
	 * @return
	 */
	public List<Map<String, Object>> searchLeadOutEquipList(
			TblEquipmentrepair paramsModel);
}
