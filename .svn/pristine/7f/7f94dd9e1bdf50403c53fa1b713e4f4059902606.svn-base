package com.wanma.app.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.exceptions.AppRequestErrorException;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.bluemobi.product.utils.RequestParamUtil;
import com.bluemobi.product.utils.StringUtil;
import com.wanma.app.service.ElectricPileDetaillService;
import com.wanma.app.service.impl.PowerStationDetailServiceImpl;
import com.wanma.common.JudgeNullUtils;
import com.wanma.model.ElectricPileDetail;
import com.wanma.model.TblElectricpile;

/**
 * 电桩详情
 * 
 * @Description:
 * @createTime：2015-3-11 下午04:25:53
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/app/electricPileDetail")
public class ElectricPileDetailController {
	private static Logger log = Logger.getLogger(ElectricPileDetailController.class);

	@Autowired
	private ElectricPileDetaillService electricPileDetaillService;

	/**
	 * 获取电桩详情
	 */
	@RequestMapping("/getElectricPileDetail")
	@ResponseBody
	public String getElectricPileDetail(HttpServletRequest request)
			throws AppRequestErrorException {

		// 电桩Id
		String powerStationId = RequestParamUtil.getEncodeParam(request,"electricPileId");
		// 用户id
		String pkUserinfo = RequestParamUtil.getEncodeParam(request,"pkUserinfo");
		String longitude = RequestParamUtil.getEncodeParam(request,"longitude");
		String latitude = RequestParamUtil.getEncodeParam(request,"latitude");
		
		List<ElectricPileDetail> electricDetailList = new ArrayList();
		try {
			TblElectricpile tblElectricpile = new TblElectricpile();

			if (StringUtil.isEmpty(powerStationId) ) {
				return new AccessErrorResult(1050,"error.msg.miss.parameter").toString();
			}
			if (StringUtil.isEmpty(pkUserinfo)) {
				return new AccessErrorResult(1050,"error.msg.miss.parameter").toString();
			}
			tblElectricpile.setPkElectricpile(Integer.parseInt(powerStationId));
			tblElectricpile.setPkUserinfo(Integer.parseInt(pkUserinfo));
			if(!StringUtils.isEmpty(longitude) && !StringUtils.isEmpty(latitude)){
				tblElectricpile.setElpiLongitude(new BigDecimal(longitude));
				tblElectricpile.setElpiLatitude(new BigDecimal(latitude));
			}
			electricDetailList = electricPileDetaillService.getElectricPileDetail(tblElectricpile);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new AccessErrorResult(2004,"error.msg.invalid.parameter").toString();
		}

		return new AccessSuccessResult(electricDetailList).toString();
	}

	/**
	 * 手机端扫描二维码显示电桩和枪口信息
	 */
	@RequestMapping("/selectPileInfo")
	@ResponseBody
	public String selectPileInfo(@RequestParam Map<String, Object> params) {
		log.info("******************手机端扫描二维码显示电桩和枪口信息-begin************************");

		if(StringUtils.isEmpty(params.get("elpiElectricpilecode")) ||
				StringUtils.isEmpty(params.get("ePHeElectricpileHeadId"))){
			return new AccessErrorResult(1050,"error.msg.miss.parameter").toString();
		}
		
		Map<String,Object> electricpile = null;
		try {
			// 获取电桩和枪口信息
			electricpile = electricPileDetaillService.selectPileInfo(params);
			int compNum = Integer.parseInt(electricpile.get("company_number").toString());
			int state = Integer.parseInt(electricpile.get("elpiState").toString());
			if(compNum > 0 && state == 10){
				return new AccessErrorResult(1001, "该桩目前不对外开放，暂不提供二维码充电功能").toString();
			}
			//计算当前电费
			String mark = PowerStationDetailServiceImpl.getCurrentPowerRateMark(JudgeNullUtils.nvlStr(electricpile.get("rateDate")));
			switch(mark){
				case "1":
					electricpile.put("currentRate",new BigDecimal(JudgeNullUtils.nvlStr(electricpile.get("jPrice"))));
					break;
				case "2":
					electricpile.put("currentRate",new BigDecimal(JudgeNullUtils.nvlStr(electricpile.get("fPrice"))));
					break;
				case "3":
					electricpile.put("currentRate",new BigDecimal(JudgeNullUtils.nvlStr(electricpile.get("pPrice"))));
					break;
				case "4":
					electricpile.put("currentRate",new BigDecimal(JudgeNullUtils.nvlStr(electricpile.get("gPrice"))));
					break;
				default:
					electricpile.put("currentRate",new BigDecimal(0));
			}
			//最终返回前清掉费率，减少返回内容
			electricpile.put("rateData", "");
			
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("手机端扫描二维码显示电桩和枪口信息失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(2004,"error.msg.invalid.parameter")
					.toString();
		}
		log.info("******************手机端扫描二维码显示电桩和枪口信息-end************************");
		// 返回处理成功信息
		return new AccessSuccessResult(electricpile).toString();

	}
	
	/**
	 * 手机端输入二维码编号显示电桩和枪口信息
	 */
	@RequestMapping("/zNumSelPileInfo")
	@ResponseBody
	public String zNumSelPileInfo(String zCodeNum) {
		log.info("******************手机端输入二维码编号显示电桩和枪口信息-begin************************");

		if(StringUtils.isEmpty(zCodeNum)){
			return new AccessErrorResult(1050,"error.msg.miss.parameter").toString();
		}
		
		Map<String,Object> electricpile = null;
		try {
			// 获取电桩和枪口信息
			electricpile = electricPileDetaillService.zNumSelPileInfo(zCodeNum);
			if(null == electricpile || electricpile.isEmpty()){
				return new AccessErrorResult(1001, "未找到相应记录，请确保编码正确").toString();
			}
			
			Date date = new Date();
			long time = date.getTime();
			if(time >= Long.parseLong(electricpile.get("ePHe_qrdate").toString() + "000")){
				return new AccessErrorResult(1001, "二维码已过期，请重新生成").toString();
			}
			
			int compNum = Integer.parseInt(electricpile.get("company_number").toString());
			int state = Integer.parseInt(electricpile.get("elpiState").toString());
			if(compNum > 0 && state == 10){
				return new AccessErrorResult(1001, "该桩目前不对外开放，暂不提供二维码充电功能").toString();
			}
			
			//计算当前电费
			String mark = PowerStationDetailServiceImpl.getCurrentPowerRateMark(JudgeNullUtils.nvlStr(electricpile.get("rateDate")));
			switch(mark){
				case "1":
					electricpile.put("currentRate",new BigDecimal(JudgeNullUtils.nvlStr(electricpile.get("jPrice"))));
					break;
				case "2":
					electricpile.put("currentRate",new BigDecimal(JudgeNullUtils.nvlStr(electricpile.get("fPrice"))));
					break;
				case "3":
					electricpile.put("currentRate",new BigDecimal(JudgeNullUtils.nvlStr(electricpile.get("pPrice"))));
					break;
				case "4":
					electricpile.put("currentRate",new BigDecimal(JudgeNullUtils.nvlStr(electricpile.get("gPrice"))));
					break;
				default:
					electricpile.put("currentRate",new BigDecimal(0));
			}
			//最终返回前清掉费率，减少返回内容
			electricpile.put("rateData", "");
			
		} catch (Exception e) {
			log.error("输入二维码编号显示电桩和枪口信息失败", e);
			return new AccessErrorResult(2004,"error.msg.invalid.sql").toString();
		}
		log.info("******************手机端输入二维码编号显示电桩和枪口信息-end************************");
		// 返回处理成功信息
		return new AccessSuccessResult(electricpile).toString();

	}
	
	/**
	 * 获取有电桩的城市行政区域代码
	 * @return
	 */
	@RequestMapping("/getEpCityCode")
	@ResponseBody
	public String getEpCityCOde(){
		
		return new AccessSuccessResult(electricPileDetaillService.getEpCityCode()).toString();
	}
}