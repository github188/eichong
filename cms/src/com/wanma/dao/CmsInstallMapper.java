package com.wanma.dao;

import java.util.List;
import com.wanma.model.TblAppointmentinstallationorder;

/**
 * 预约安装订单
 * 
 * @author xiay
 *
 */
public interface CmsInstallMapper {

	/**
	 * 根据预约安装ID取得预约安装信息
	 * 
	 * @author xiay
	 * @return 无
	 */
	public TblAppointmentinstallationorder findInstall(String pkAppointmentinstallationorder);
	
	/**
	 * 取得预约安装一览
	 *  
	 * @author xiay
	 * @return 无
	 */
	public List<TblAppointmentinstallationorder> getInstallList();

	/**
	 * 查询预约安装个数
	 * 
	 * @author xiay
	 * @return 无
	 */
	public long searchInstallCount(TblAppointmentinstallationorder tblInatall);

	/**
	 * 查询预约安装一览
	 * 
	 * @author xiay
	 * @return 无
	 */
	public List<TblAppointmentinstallationorder> searchInstallList(TblAppointmentinstallationorder tblInatall);
}
