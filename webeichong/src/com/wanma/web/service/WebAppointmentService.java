package com.wanma.web.service;

import java.util.Map;

import com.wanma.model.TblAppointmentinstallationorder;

/**
 * @Description: 预约安装订单业务处理接口
 * @author songjf
 * @createTime：2015-3-24 下午01:12:24
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
public interface WebAppointmentService {

	/**
	 * @Title: selectAddAndPro
	 * @Description: 安装预约 确认安装地址 商品
	 * @param params
	 * @return
	 */
	public Map<String, Object> selectAddAndPro(Map<String, Object> params);

	/**
	 * @Title: insertAppointment
	 * @Description: 新增预约安装订单
	 * @param params
	 * @return
	 */
	public void insertAppointment(TblAppointmentinstallationorder appointment,
			String ordeProductids, String ordeProductnames,
			String ordePrices, String inDeQuantitys,
			String prodProductCode);
	/***********web******/
	
	/**
	 * @Title: insertAppointmentNew
	 * @Description: 新增预约安装订单
	 * @param appointment
	 *         json字符串
	 * @return
	 */
	public void insertAppointmentNew(String appointment,int userId,String orderAddressids);

}
