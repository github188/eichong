package com.wanma.controller.simple;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanma.model.TblChargingOrder;
import com.wanma.service.PileFilterService;
import com.wanma.service.StartChargeService;
import com.wanma.service.StopChargeService;
import com.wanma.service.TblChargingOrderService;
import com.wanma.support.common.AccessSuccessResult;
import com.wanma.support.common.FailedResponse;

/**
 * @Description: 充电管理控制层
 * @author gx
 * @createTime：2017-07-27
 * @updateTime：
 */
@Controller
@RequestMapping("/simple")
public class SimpleChargeController {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SimpleChargeController.class);
	private static  String errorMSG ="errorMSG";
	private static  String startTimes="startTime";

	@Autowired
	private PileFilterService pileFilterService;

	@Autowired
	private StartChargeService startChargeService;

	@Autowired
	private StopChargeService stopChargeService;

	@Autowired
	private TblChargingOrderService ordService;

	/**
	 * @Description: 开始充电
	 * @return
	 * @throws Exception
	 */

	@RequestMapping("/chargeStart")
	@ResponseBody
	public String chargeStart(HttpServletRequest request) {
		// 爱充平台第三方标识
		String org = request.getParameter("org");
		// 停简单订单编号
		String orderId = request.getParameter("orderId");
		// 第三方用户标识
		String userPhone = request.getParameter("userPhone");
		// 充电桩id
		String pointId = request.getParameter("pointId");
		// 枪口编号
		String gunId = request.getParameter("gunId");
		// 预充金额
		int userAmount = (int) Float.parseFloat(request
				.getParameter("userAmount")) * 100;
		if (StringUtils.isBlank(orderId) || StringUtils.isBlank(userPhone)
				|| StringUtils.isBlank(pointId) || StringUtils.isBlank(gunId))
			return new FailedResponse(1001, "params error").toString();
		// 对充电桩进行过滤--begin
		LOGGER.info("开始校验该第三方能否对此充电桩进行充电");
		boolean ok = pileFilterService.checkPileIsOk(org.toString(), pointId);
		if (ok == false) {
			return new FailedResponse(6804, "no right").toString();
		}
		LOGGER.info("结束校验该第三方能否对此充电桩进行充电");
		// 对充电桩进行过滤-end
		LOGGER.info("下发充电命令开始，用户编号：{}",userPhone);
		LOGGER.info("第三方标识：{} ", org);
		int rtCode = startChargeService.startCharge(Integer.parseInt(org),
				userPhone, pointId, Integer.parseInt(gunId), userAmount,
				orderId);

		LOGGER.info("下发充电命令结束！");
		Map<String, Object> data = new HashMap<String, Object>();

		SimpleDateFormat timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat startTime = new SimpleDateFormat("yyyyMMddHHmmss");
		data.put("timestamp", timeStamp.format(new Date()));
		data.put("isSuccess", String.valueOf(rtCode));
		if (rtCode > 0) {
			data.put(errorMSG, "charge Error");
		} else
			data.put(errorMSG, "");
		data.put(startTimes, startTime.format(new Date()));
		data.put("expectedFullTime", "");
		data.put("soc", "");
		return new AccessSuccessResult(data).toString();
	}

	/**
	 * @Description: 结束充电
	 * @return
	 * @throws ParseException 
	 * @throws Exception
	 */
	@RequestMapping("/chargeStop")
	@ResponseBody
	public String chargeStop(HttpServletRequest request) throws ParseException  {
		// 爱充平台第三方标识
		String org = request.getParameter("org");
		// 第三方用户标识
		String userPhone = request.getParameter("userPhone");
		// 充电桩id
		String pointId = request.getParameter("pointId");
		// 枪口编号
		String gunId = request.getParameter("gunId");

		String orderId = request.getParameter("orderId");

		if (StringUtils.isBlank(userPhone) || StringUtils.isBlank(pointId)
				|| StringUtils.isBlank(gunId))
			return new FailedResponse(1001, "params error").toString();

		int rtCode = stopChargeService.stopCharge(Integer.parseInt(org),
				userPhone, pointId, Integer.parseInt(gunId));

		LOGGER.info("下发停止充电命令结束！");
		Map<String, Object> data = new HashMap<String, Object>();

		SimpleDateFormat timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat timeFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		data.put("timestamp", timeStamp.format(new Date()));
		data.put("isSuccess", String.valueOf(rtCode));
		if (rtCode > 0) {
			data.put(errorMSG, "charge Error");
		} else
			data.put(errorMSG, "");

		TblChargingOrder model = new TblChargingOrder();
		model.setChorCode(orderId);
		model = ordService.selectOne(model);
		if (model != null) {
			data.put(startTimes, timeFormat.parse(model.getBeginChargetime()));
			data.put("endTime", timeFormat.parse(model.getEndChargetime()));
			data.put("power", "");
			data.put("soc", "");
			data.put("amount", "");
			data.put("debtAmount", "");
			data.put("endType", "4");
		} else {

			data.put("startTime", "");
			data.put("endTime", "");
			// String power=new
			// java.text.DecimalFormat("#.00").format(Double.parseDouble(model.getChorQuantityelectricity()))

			data.put("power", "");
			data.put("soc", "");
			data.put("amount", "");
			data.put("debtAmount", "");
			data.put("endType", "4");
		}

		return new AccessSuccessResult(data).toString();

	}

}
