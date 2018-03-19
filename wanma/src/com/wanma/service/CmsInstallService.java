package com.wanma.service;

import java.util.List;

import com.wanma.model.TblAppointmentinstallationorder;
/**
 * FileName TblInstallService.java
 * 
 * Version 1.0
 * 
 * Create by xiay
 * 
 * 预约安装处理接口
 */
public interface CmsInstallService {

	/**
	 * 根据预约安装ID取得预约安装信息
	 *
	 * @return
	 * @throws
	 */
	public TblAppointmentinstallationorder findInstall(String pkAppointmentinstallationorder);
	

	/**
	 * 取得预约安装一览
	 * 
	 * @return 
	 * @throws 
	 */
	public List<TblAppointmentinstallationorder> getInstallList();

	/**
	 * 查询预约安装个数
	 * 
	 * @return 
	 * @throws 
	 */
	public long searchInstallCount(TblAppointmentinstallationorder tblInstall);
	

	/**
	 * 查询预约安装一览
	 * 
	 * @return
	 * @throws 
	 */
	public List<TblAppointmentinstallationorder> searchInstallList(TblAppointmentinstallationorder tblInstall);
}
