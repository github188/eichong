package com.wanma.service;

import java.util.List;

import com.wanma.model.TblEquipmentrepair;


/**
 * 设备报修处理器
 * 
 * @author xiay
 *
 */
public interface CmsEquipmentrepairService {

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
	public void addEquip(TblEquipmentrepair tblEquipmentrepair);
	
	/**
	 * 更新用户状态
	 * 
	  * 
	 * @param tblEquipmentrepair
	 * @return
	 */
	public int updateEquipStage(TblEquipmentrepair record);
	
}
