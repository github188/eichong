package com.wanma.web.controller;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.controller.BaseController;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.wanma.model.TblAppointmentinstallationorder;
import com.wanma.web.service.WebAppointmentService;
import com.wanma.web.support.common.FailedResponse;
import com.wanma.web.support.common.SuccessResponse;

/**
 * @Description: 预约安装控制器
 * @author songjf
 * @createTime：2015-3-24 下午01:51:33
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/web/appointment")
public class WebAppointmentController extends BaseController {

	private static Log log = LogFactory.getLog(WebUserController.class);

	/** 预约安装业务处理对象 */
	@Autowired
	private WebAppointmentService appointmentService;

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
			System.out.println(e);
			return new AccessErrorResult("error.msg.invalid.parameter")
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
			@RequestParam String ordeProductids,
			@RequestParam String ordeProductnames,
			@RequestParam String ordePrices,
			@RequestParam String inDeQuantitys,
			@RequestParam String prodProductCode) {
		log.info("******************新增预约安装订单-begin************************");
		try {
			appointmentService.insertAppointment(appointment, ordeProductids,
					ordeProductnames, ordePrices, inDeQuantitys,
					prodProductCode);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("新增预约安装订单错误", e);
			return new AccessErrorResult("error.msg.invalid.parameter")
					.toString();
		}

		log.info("******************新增预约安装订单-end************************");
		return new AccessSuccessResult().toString();
	}

	/**************** web ***************/
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
	public String insertAppointmentNew(String appointment,int userId,String orderAddressids) {
		log.info("******************新增预约安装订单web-end************************");
		try {
			appointmentService.insertAppointmentNew(appointment,userId,orderAddressids);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("新增预约安装订单web错误", e);
			// 返回错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}
		log.info("******************新增预约安装订单web-end************************");
		return new SuccessResponse().toString();

	}

}
