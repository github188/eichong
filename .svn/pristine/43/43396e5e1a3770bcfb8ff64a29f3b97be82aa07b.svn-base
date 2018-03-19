package com.wanma.controller.simple;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanma.controller.itf.ChargeGiftController;
import com.wanma.service.SimpleOrderService;
import com.wanma.support.common.FailedResponse;


@Controller
@RequestMapping("/simple")
public class SimpleOrderController {
	@Autowired
	private SimpleOrderService soService;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ChargeGiftController.class);
	/**
	 * 订单查询
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryOrder")
	@ResponseBody
	public String getSimpleInfoByOrderId(HttpServletRequest request) {
		 String org = request.getParameter("org");
	      String orderId = request.getParameter("orderId");
        if (StringUtils.isBlank(org)
                || StringUtils.isBlank(orderId))
            return new FailedResponse(1001, "params error").toString();
        
        LOGGER.info("获取订单详细信息！");
        String simpleInfo=soService.getSimpleInfo(orderId);
        
		return simpleInfo;
		
	}

}
