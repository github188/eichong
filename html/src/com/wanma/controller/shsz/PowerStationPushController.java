package com.wanma.controller.shsz;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanma.model.cdzts.PowerstationPush;
import com.wanma.service.CmsPowerstationService;
import com.wanma.service.CmsRateInfoService;
import com.wanma.service.ElectricPileListService;
import com.wanma.service.PowerStationPushService;
import com.wanma.support.common.AccessSuccessResult;
import com.wanma.support.common.FailedResponse;
import com.wanma.support.common.MessageManager;

/**
 * 按公司批量推送充电站Controller
 * @author lyh
 *
 */
@Controller
@RequestMapping("/cdzts")
public class PowerStationPushController {
	private static final Logger LOGGER = LoggerFactory.getLogger(PowerStationPushController.class);
	
	// 充电点处理对象
	@Autowired
	private CmsPowerstationService powerstationService;
	@Autowired
	private ElectricPileListService electricPileListService;
	@Autowired
	private CmsRateInfoService rateInfoService;
	@Autowired
	private PowerStationPushService pspService;
	
	@ResponseBody
	@RequestMapping("/powerStationPush")
	public String powerStationPush(HttpServletRequest request) {
			String powerStationId = request.getParameter("powerStationId");
			if(StringUtils.isBlank(powerStationId)){
				return new FailedResponse(1050, "请求参数不能为空！").toString();
			}
			PowerstationPush model = new PowerstationPush();
			model.setPkPowerstation(Integer.parseInt(powerStationId));	
			LOGGER.info("....检验电站（电站ID:{}）是否在上海停车办的白名单-begin.........",powerStationId);
			//上海停车办
			model.setOrg(Integer.parseInt("9004"));
			int count = powerstationService.getPowerCount(model);
			if(count == 0){
				LOGGER.error("上海停车办的白名单里没有该电站，电站ID：{}",powerStationId);
			}
			LOGGER.info("....检验电站（电站ID:{}）是否在上海停车办的白名单-end.........",powerStationId);	
			int sh = 1;
			if(count > 0){
				sh = pspService.shParkingPush(powerStationId);
			}
			//南京南瑞
			LOGGER.info("...........判断当前是否为正式环境-begin.................");
			MessageManager manager = MessageManager.getMessageManager();
	        int isPush = Integer.parseInt(manager.getSystemProperties("environment.is.formal"));
			if(isPush != 1){
				LOGGER.error("..............当前是非正式环境,不允许推送............");
				return new FailedResponse(1070,"该环境不允许推送充电站变化信息！").toString();
			}
			LOGGER.info("...........判断当前是否为正式环境-end...................");
			PowerstationPush nari = new PowerstationPush();
			nari.setPkPowerstation(Integer.parseInt(powerStationId));
			nari.setOrg(Integer.parseInt("9020"));
			int nr = 1;
			LOGGER.info("....检验电站（电站ID:{}）是否在南京南瑞的白名单-begin.........",powerStationId);
			int nariCount = powerstationService.getPowerCount(nari);
			if(nariCount == 0){
				LOGGER.error("南京南瑞的白名单里没有该电站，电站ID：{}",powerStationId);
			}
			LOGGER.info("....检验电站（电站ID:{}）是否在南京南瑞的白名单-end.........",powerStationId);
			if(nariCount > 0){
				nr = pspService.nariPush(powerStationId);
			}
			if(sh == 1 && nr == 1){
				return new FailedResponse(2004,"推送电站信息结果：上海停车办和南京南均失败！").toString();
			}
			if(sh == 1 && nr == 0){
				return new FailedResponse(2004,"推送电站信息结果：上海停车办推送电站信息失败,南京南瑞成功！").toString();
			}
			if(sh == 0 && nr == 1){
				return new FailedResponse(2004,"推送电站信息结果：上海停车办成功,南京南瑞失败！").toString();
			}
		return new AccessSuccessResult().toString();				
	}	
	
}
