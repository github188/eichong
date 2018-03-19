package com.wanma.controller.tpi;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanma.model.TblChargingOrder;
import com.wanma.model.TblElectricpilehead;
import com.wanma.model.TblUser;
import com.wanma.service.TblChargingOrderService;
import com.wanma.service.TblElectricpileHeadService;
import com.wanma.service.TblElectricpileService;
import com.wanma.service.TblUserService;
import com.wanma.support.common.FailedResponse;
import com.wanma.support.common.RedisService;
import com.wanma.support.common.ResultResponse;
import com.wanma.support.common.WanmaConstants;
import com.wanma.support.utils.ApiUtil;
import com.wanma.support.utils.HttpUtil;

/**
 * @Description: 订单结算控制层
 * @author lhy
 * @createTime：2016-12-06
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/tpi/electric")
public class TpiTransAccOController {
	@Autowired
	private TblChargingOrderService userService;

	/**
	 * @Description: 订单结算
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping("/orderFinish")
	@ResponseBody
	public String orderFinish(HttpServletRequest request) {
		String coSerNum = request.getParameter("coSerNum");
		String transSerNum = request.getParameter("transSerNum");
		if (StringUtils.isBlank(coSerNum)
				|| StringUtils.isBlank(transSerNum)) {
			return new FailedResponse(1001, "params error").toString();
		}
		String transtype = request.getParameter("transtype");
		if(transtype == null)
			transtype = "1";
		TblChargingOrder model = new TblChargingOrder();
		model.setChorTransactionnumber(transSerNum);
		model.setChorTranstype(transtype);
		model.setChorCode(coSerNum);
		model.setChorChargingstatus("3");
		userService.update(model);
		return new ResultResponse().toString();
	}
}
