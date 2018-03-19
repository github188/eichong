package com.wanma.controller.common;

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

import com.wanma.service.PileFilterService;
import com.wanma.service.StartChargeService;
import com.wanma.service.StopChargeService;
import com.wanma.support.common.AccessSuccessResult;
import com.wanma.support.common.FailedResponse;

/**
 * @Description: 充电管理控制层
 * @author gx
 * @createTime：2017-07-27
 * @updateTime：
 */
@Controller
@RequestMapping("/eichong")
public class ChargeController {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ChargeController.class);

	@Autowired
	private PileFilterService pileFilterService;

	@Autowired
	private StartChargeService startChargeService;
	
	@Autowired
	private StopChargeService stopChargeService;

	/**
	 * @Description: 开始充电
	 * @return
	 * @throws Exception
	 */

	@RequestMapping("/chargeStart")
	@ResponseBody
	public String chargeStart(HttpServletRequest request) throws Exception {
		// 爱充平台第三方标识
		String org = request.getParameter("org");
		// 第三方订单编号
		String outOrderId = request.getParameter("outOrderId");
		// 第三方用户标识
		String outUserId = request.getParameter("outUserId");
		// 充电桩id
		String pointId = request.getParameter("pointId");
		// 枪口编号
		String gunId = request.getParameter("gunId");
		// 预充金额
		int userAmount = (int) Float.parseFloat(request
				.getParameter("userAmount")) * 100;
		if (StringUtils.isBlank(outOrderId) || StringUtils.isBlank(outUserId)
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
		LOGGER.info("下发充电命令开始，用户编号：" + outUserId + ";第三方标识：" + org);
		int rtCode = startChargeService.startCharge(Integer.parseInt(org),
				outUserId, pointId, Integer.parseInt(gunId), userAmount,
				outOrderId);

		LOGGER.info("下发充电命令结束！");
		   Map<String, Object> data = new HashMap<String, Object>();
		
	    SimpleDateFormat timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        data.put("timestamp", timeStamp.format(new Date()));
	        data.put("isSuccess", String.valueOf(rtCode));
	        if (rtCode > 0) {
	        	  data.put("errorMSG", "charge Error");
			}else
				data.put("errorMSG", "");
	       
	        return new AccessSuccessResult(data).toString();
	}

	/**
	 * @Description: 结束充电
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/chargeStop")
	@ResponseBody
	public String chargeStop(HttpServletRequest request) throws Exception {
		// 爱充平台第三方标识
		String org = request.getParameter("org");
		// 第三方用户标识
		String outUserId = request.getParameter("outUserId");
		// 充电桩id
		String pointId = request.getParameter("pointId");
		// 枪口编号
		String gunId = request.getParameter("gunId");
		
		if ( StringUtils.isBlank(outUserId)
				|| StringUtils.isBlank(pointId) || StringUtils.isBlank(gunId))
			return new FailedResponse(1001, "params error").toString();
			
		int rtCode = stopChargeService.stopCharge(Integer.parseInt(org),
				outUserId, pointId, Integer.parseInt(gunId));
	
		   LOGGER.info("下发停止充电命令结束！");
		   Map<String, Object> data = new HashMap<String, Object>();
			
		    SimpleDateFormat timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        data.put("timestamp", timeStamp.format(new Date()));
		        data.put("isSuccess", String.valueOf(rtCode));
		        if (rtCode > 0) {
		        	  data.put("errorMSG", "charge Error");
				}else
					data.put("errorMSG", "");
		  
		        return new AccessSuccessResult(data).toString();
		
	}

}
