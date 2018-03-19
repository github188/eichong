package com.wanma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wanma.dao.CmsInstallMapper;
import com.wanma.model.TblAppointmentinstallationorder;
import com.wanma.service.CmsInstallService;

@Service
public class CmsInstallServiceImpl implements CmsInstallService{

	/** 预约安装处理器 */
	@Autowired
	private CmsInstallMapper tblInstallDao;
	
	/**
	 * 根据用户ID取得用户信息
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param InstallId
	 *            用户ID
	 * @return InstallModel 登录用户信息
	 * @throws 无
	 */
	public TblAppointmentinstallationorder findInstall(String pkAppointmentinstallationorder) {

		// 用户信息
		TblAppointmentinstallationorder install;

		// 取得用户信息
		install = tblInstallDao.findInstall(pkAppointmentinstallationorder);

		// 返回用户一览
		return install;
	}
	
	/**
	 * 取得用户一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @return List<InstallModel> 用户一览
	 * @throws 无
	 */
	public List<TblAppointmentinstallationorder> getInstallList() {
		// 用户一览
		List<TblAppointmentinstallationorder> listInstall;

		// 取得用户一览
		listInstall = tblInstallDao.getInstallList();

		// 返回用户一览
		return listInstall;
	}

	/**
	 * 查询用户个数
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param InstallModel
	 *            用户信息
	 * @return List<InstallModel> 用户一览
	 * @throws 无
	 */
	public long searchInstallCount(TblAppointmentinstallationorder tblInsatall) {
		// 用户个数
		long dataCount;

		// 取得用户个数
		dataCount = tblInstallDao.searchInstallCount(tblInsatall);

		// 返回用户个数
		return dataCount;

	}

	/**
	 * 查询用户一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param InstallModel
	 * @return List<InstallModel> 用户一览
	 * @throws 无
	 */
	public List<TblAppointmentinstallationorder> searchInstallList(TblAppointmentinstallationorder tblInsatall) {
		// 用户一览
		List<TblAppointmentinstallationorder> listInstall;

		// 取得用户一览
		listInstall = tblInstallDao.searchInstallList(tblInsatall);

		// 返回用户一览
		return listInstall;

	}
}
