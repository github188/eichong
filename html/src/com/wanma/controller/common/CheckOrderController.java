package com.wanma.controller.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanma.service.NariChargeOrderPushService;
import com.wanma.service.PowerStationPushService;
import com.wanma.service.TblPowerstationService;
import com.wanma.service.TblReconciliationService;

@Controller
@RequestMapping("/Check")
public class CheckOrderController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CheckOrderController.class);
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	private TblReconciliationService recobcilicationService;
	@Autowired
	private TblPowerstationService psService;
	@Autowired
	private PowerStationPushService pspService;
	@Autowired
	private NariChargeOrderPushService nariPushService;
	
	
	/**
	 * 中电联内部接口(自定义订单对账推送)
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/checkChargeOrder")
	public String checkChargeOrder(String OperatorID,String beginTime,String endTime) throws Exception {
		LOGGER.info("~~~~~~~~~~~~~~~~后台推送begin~~~~~~~~~~~~~~~~~~~~");
	    Date begin = sdf.parse(beginTime);
	    Date end = sdf.parse(endTime);
        Map<String, Object> map=new HashMap<>();
        map.put("OperatorID", OperatorID);
	    Map<String, Object> maps = psService.getPartnerKeyList(map);
		recobcilicationService.PushChargeOrder(maps.get("cpyNumber").toString(), begin, end);
		return "Success";
	}
	
	/**
	 * 中电联-南京南瑞内部接口-自定义推送订单信息
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/notification_orderInfo")
	public String notificationOrderInfo(String startTime,String endTime)throws Exception {
		LOGGER.info("....................推送订单信息（南瑞）-begin.......................");
        int result = 1;
        try {
        	result = nariPushService.nariChargeOrderPush(startTime,endTime);
		} catch (Exception e) {
			return "Fail";
		}
        if(result == 1){
        	return "Fail";
        }
        LOGGER.info("....................推送订单信息（南瑞）-end.......................");
		return "success";
	}
	
	/**
	 * 中电联内部接口-自定义支付宝电站推送
	 * @return
	 */
	@RequestMapping("/alipay_stationInfo")
	@ResponseBody
	public String alipayTest(String lastQueryTime){
		int num = 1;
		try {
			num = pspService.alipayStationPush(lastQueryTime);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		String info = null;
		if(num == 0){
			info = "success";
		}else{
			info = "fail";
		}
		return info;
		
	}
	
	

}
