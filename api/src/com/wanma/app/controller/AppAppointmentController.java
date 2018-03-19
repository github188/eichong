package com.wanma.app.controller;

import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.wanma.app.service.AppAppointmentService;
import com.wanma.app.util.DateUtil;
import com.wanma.model.TblAppointmentinstallationorder;

/**
 * @Description: 预约安装控制器
 * @author songjf
 * @createTime：2015-3-24 下午01:51:33
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/app/appointment")
public class AppAppointmentController extends BaseController {

	private static Logger log = Logger.getLogger(AppUserController.class);

	/** 预约安装业务处理对象 */
	@Autowired
	private AppAppointmentService appointmentService;

	/**
	 * @Title: selectAddAndPro
	 * @Description: 安装预约 确认安装地址 商品
	 * @param params
	 * @return
	 */
	@RequestMapping("/selectAddAndPro")
	@ResponseBody
	public String selectAddAndPro(@RequestParam Map<String, Object> params) {
		log.info("******************安装预约 确认安装地址 商品-begin************************");
		Map<String, Object> resultMap = null;
		try {
			resultMap = appointmentService.selectAddAndPro(params);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("安装预约 确认安装地址 商品错误", e);
			return new AccessErrorResult(1001, "error.msg.invalid.parameter")
					.toString();
		}

		log.info("******************安装预约 确认安装地址 商品-end************************");
		return new AccessSuccessResult(resultMap).toString();
	}

	/**
	 * @Title: insertAppointment
	 * @Description: 新增预约安装订单
	 * @param params
	 * @return
	 */
	@RequestMapping("/insertAppointment")
	@ResponseBody
	public String insertAppointment(
			TblAppointmentinstallationorder appointment,
			@RequestParam String buyingtime,
			@RequestParam String ordeProductids,
			@RequestParam String ordeProductnames,
			@RequestParam String ordePrices,
			@RequestParam String inDeQuantitys,
			@RequestParam String prodProductCode,
			@RequestParam String indeTotalamounts) {
		log.info("******************新增预约安装订单-begin************************");
		try {

			Date alorBuyingtime = DateUtil.parse(buyingtime,
					"yyyy/MM/dd HH:mm:ss");
			appointment.setAlorBuyingtime(alorBuyingtime);

			appointmentService.insertAppointment(appointment, ordeProductids,
					ordeProductnames, ordePrices, inDeQuantitys,
					prodProductCode, indeTotalamounts);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("新增预约安装订单错误", e);
			return new AccessErrorResult(1001, "error.msg.invalid.parameter")
					.toString();
		}

		log.info("******************新增预约安装订单-end************************");
		return new AccessSuccessResult().toString();
	}

	/**
	 * @Title: insertAppointmentNew
	 * @Description: 新增预约安装订单web
	 * @param appointment
	 *            json字符串
	 * @param userId
	 *            用户id
	 * @return
	 * @return
	 */
	@RequestMapping("/insertAppointmentNew")
	@ResponseBody
	public String insertAppointmentNew(String appointment, int userId,
			String orderAddressids) {
		log.info("******************新增预约安装订单-end************************");
		try {
			appointmentService.insertAppointmentNew(appointment, userId,
					orderAddressids);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("新增预约安装订单错误", e);
			return new AccessErrorResult(1001, "error.msg.invalid.parameter")
					.toString();
		}
		log.info("******************新增预约安装订单-end************************");
		return new AccessSuccessResult().toString();

	}

}
