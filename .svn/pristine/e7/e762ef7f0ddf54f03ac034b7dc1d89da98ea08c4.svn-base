package com.wanma.ims.controller.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanma.ims.common.domain.ElectricParamDO;
import com.wanma.ims.common.domain.base.Pager;
import com.wanma.ims.constants.ResultCodeConstants;
import com.wanma.ims.constants.WanmaConstants;
import com.wanma.ims.controller.BaseController;
import com.wanma.ims.controller.result.JsonException;
import com.wanma.ims.controller.result.JsonResult;
import com.wanma.ims.core.GlobalSystem;
import com.wanma.ims.service.ElectricParamService;
import com.wanma.ims.util.ApiUtil;
import com.wanma.ims.util.HttpsUtil;


/**
 * 电桩配置参数 
 */
@Controller
@RequestMapping("/manage/config/electric/param")
public class ElectricParamController extends BaseController{
   
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ElectricParamService electricParamService;

	/**
	 * 电桩配置参数-分页
	 */
	@RequestMapping(value = "/getElectricParamListPage", method = RequestMethod.POST)
	@ResponseBody
	public void getElectricParamListPage(Pager pager, ElectricParamDO electricParamDO) {
		JsonResult result = new JsonResult();
		try {
			Long total = electricParamService.countElectricParamList(electricParamDO);
			if (total <= pager.getOffset()) {
				pager.setPageNo(1L);
			}
			pager.setTotal(total);
			electricParamDO.setPager(pager);
			List<ElectricParamDO> list = electricParamService.getElectricParamList(electricParamDO);
			result.setPager(pager);
			result.setDataObject(list);
		} catch (Exception e) {
			LOGGER.debug(this.getClass() + ".getElectricParamListPage() error : ", e);
			responseJson(new JsonException("电桩配置参数分页列表失败"));
			return;
		}
		responseJson(result);
	}
	
	/**
	 * 电桩参数设置
	 * 
	 */
	@RequestMapping(value = "/setElectricParam", method = RequestMethod.POST)
	@ResponseBody
	public void setElectricParam() {
		JsonResult result = new JsonResult();
		try {
			// 电桩
			String codes = request.getParameter("code");
			// 离线充电
			String offlineNum = request.getParameter("offlineNum");
			// 定时SOC
			String socValue = request.getParameter("socValue");
			// 定时充电
			String hour = request.getParameter("hour");
			String minute = request.getParameter("minute");
			String timeStr = hour+minute;
			String[] str = codes.split(",");
			String userName = getCurrentUserName();
			for(int j=0;j<str.length;j++){
				for (int i = 1; i < 4; i++) {
					ElectricParamDO electricParamDO = new ElectricParamDO();
					electricParamDO.setCreator(userName);
					electricParamDO.setModifier(userName);
					electricParamDO.setElectricPileCode(str[j]);
					electricParamDO.setIssuedStatus("0");
					if(i == 1){
						electricParamDO.setArgId(WanmaConstants.ELECTRIC_PARAM_SOC);
						electricParamDO.setArgValue(socValue);
					}
					if(i == 2){
						electricParamDO.setArgId(WanmaConstants.ELECTRIC_PARAM_CHARGE);
						electricParamDO.setArgValue(timeStr);
					}
					if(i == 3){
						electricParamDO.setArgId(WanmaConstants.ELECTRIC_PARAM_OFFLINE);
						electricParamDO.setArgValue(offlineNum);
					}
					if(!electricParamService.setElectricParam(electricParamDO)){
						responseJson(new JsonResult(false, ResultCodeConstants.FAILED, "电桩参数设置失败"));
						return;
					}
				}
			}
			responseJson(new JsonResult(true, ResultCodeConstants.SUCCESS, "电桩参数设置成功"));
			//下发参数设置指令
			if(str.length > 0){
				codes += ":10316";
				String token = ApiUtil.getToken();
			    LOGGER.error("url..."+GlobalSystem.getConfig("apiRoot") + "/app/net/sendChargeSet.do?paramStrs=" + codes + "&t=" + token, "status");
		    	HttpsUtil.getResponseParam(GlobalSystem.getConfig("apiRoot") + "/app/net/sendChargeSet.do?paramStrs=" + codes + "&t=" + token, "status");
			}
			
		} catch (Exception e) {
			LOGGER.debug(this.getClass() + ".setElectricParam() error : ", e);
			responseJson(new JsonException("电桩参数设置失败"));
			return;
		}
		responseJson(result);
	}

}
