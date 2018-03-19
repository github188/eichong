package com.wanma.app.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bluemobi.product.common.AppPager;
import com.bluemobi.product.exceptions.AppRequestErrorException;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.bluemobi.product.utils.RequestParamUtil;
import com.bluemobi.product.web.WebFilter;
import com.wanma.app.service.AppBespokeService;
import com.wanma.app.service.AppUserinfoService;
import com.wanma.app.service.ChargeService;
import com.wanma.app.service.TblChargingorderService;
import com.wanma.app.service.TblElectricpileheadService;
import com.wanma.app.util.Base64Coder;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblChargeinfo;
import com.wanma.model.TblUserinfo;
import com.wanma.net.ElectricPieUtil;

/**
 * 充电展示
 * 
 * @Description:
 * @author bruce cheng(http://blog.csdn.net/brucehome)
 * @createTime：2015-3-11 下午04:25:53
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/app/chargeShow")
public class ChargeController {
	private static Logger log = Logger.getLogger(ChargeController.class);

	@Autowired
	private ChargeService chargeService;
	// 枪口业务处理对象
	@Autowired
	private TblElectricpileheadService electricpileheadService;
	// 充电消费订单业务处理对象
	@Autowired
	private TblChargingorderService chargingorderService;

	/** 预约业务处理对象 */
	@Autowired
	private AppBespokeService bespokeService;

	/** 用户处理对象 */
	@Autowired
	private AppUserinfoService userinfoService;
	
	
	/**
	 * 获取实时充电数据
	 */
	@RequestMapping("/getChangeInfo")
	@ResponseBody
	public String getChangeInfo(HttpServletRequest request)
			throws AppRequestErrorException {
		log.info("******************获取实时充电数据-begin************************");
		// 用户id
		String pkUserinfo = RequestParamUtil.getEncodeParam(request,
				"pkUserinfo");

		TblChargeinfo chargeinfoList = null;
		try {

			TblChargeinfo tblChargeinfo = new TblChargeinfo();
			tblChargeinfo.setChinUserinfo(Integer.parseInt(pkUserinfo));
			tblChargeinfo
					.setChinWorkingstatus(WanmaConstants.CHARGEING_WORKING);
			// tblChargeinfo
			// .setChinLinkedstatus(WanmaConstants.CHARGEING_CONNECT_CLOSE);
			chargeinfoList = chargeService.getChangeInfo(tblChargeinfo);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取实时充电数据数据失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(2004,"error.msg.invalid.parameter")
					.toString();
		}
		log.info("******************获取实时充电数据-end************************");
		// 返回处理成功信息
		return new AccessSuccessResult(chargeinfoList).toString();
	}

	/**
	 * 获取实时充电数据
	 */
	@RequestMapping("/getChangeInfoN")
	@ResponseBody
	public String getChangeInfoN(HttpServletRequest request)
			throws AppRequestErrorException {
		log.info("******************获取实时充电数据-begin************************");
		String pkUserinfo = RequestParamUtil.getEncodeParam(request,"pkUserinfo");
		if(StringUtils.isEmpty(pkUserinfo)){
			return new AccessErrorResult(1050,"error.msg.miss.parameter").toString();
		}
		
		List<Map<String, Object>> chargeinfoList = new ArrayList<Map<String,Object>>();
		try {
			chargeinfoList = chargeService.getChangeInfoN(Integer.parseInt(pkUserinfo));
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			log.error("获取实时充电数据数据失败", e);
			return new AccessErrorResult(2004,"error.msg.invalid.parameter")
					.toString();
		}
		log.info("******************获取实时充电数据-end************************");
		return new AccessSuccessResult(chargeinfoList).toString();
	}
	/**
	 * 结束充电
	 */
	@RequestMapping("/finishCharge")
	@ResponseBody
	public String finishCharge(@RequestParam Map<String, Object> params)
			throws AppRequestErrorException {

		if(StringUtils.isEmpty(params.get("pkElectricPile")) 
				|| StringUtils.isEmpty(params.get("elpiElectricpilecode"))
				|| StringUtils.isEmpty(params.get("ePHeElectricpileHeadId"))
				|| StringUtils.isEmpty(params.get("did"))
				|| StringUtils.isEmpty(params.get("uid"))
				){
			return new AccessErrorResult(1050,"error.msg.miss.parameter").toString();
		}
		
		try {
			TblUserinfo userinfo = userinfoService.getUserById(Integer.parseInt(params.get("uid").toString()));
			//判断是否用的登陆设备在发命令
			byte[] b = Base64Coder.decode(params.get("did").toString());
			String did = WebFilter.judgeRequest(new String(b));
			if(!did.equals(userinfo.getDid())){
				return new AccessErrorResult(1004,"error.msg.fail.checkDid").toString();
			}
			
			String elpiElectricpilecode = (String) params.get("elpiElectricpilecode");
			String ePHeElectricpileHeadId = (String) params.get("ePHeElectricpileHeadId");
			// 请求电桩后台，结束充电
			ElectricPieUtil.stopCharge((String) params.get("pkElectricPile"),
					elpiElectricpilecode, Integer.parseInt(ePHeElectricpileHeadId),
					Integer.parseInt(params.get("uid").toString()));
			
			WanmaConstants.changeState.put(elpiElectricpilecode+"|"+ePHeElectricpileHeadId, WanmaConstants.ELECTRICPILEHEAD_BATTERY);
			
			// 判断结束充电是否成功
			for (int i = 0; i < 20; i++) {
				Integer state=(Integer)WanmaConstants.changeState.get(elpiElectricpilecode+"|"+ePHeElectricpileHeadId);
				if (state == WanmaConstants.ELECTRICPILEHEAD_FREE) {
					WanmaConstants.changeState.remove(elpiElectricpilecode+"|"+ePHeElectricpileHeadId);
					break;
				}else if(state >= 6000){
					String msg = "结束充电失败，";
					if(state == 6000){
						msg += "电桩通讯中断，请稍后再试";
					}else if(state == 6002 || state == 6001){
						msg += "电桩服务通讯中断，请稍后再试";
					}else if(state == 6100 || state == 6101){
						msg += "桩体参数无效，无法结束充电";
					}else if(state == 6301){
						msg = "桩在设置中，暂时无法结束充电";
					}else if(state == 6803){
						msg += "您还没有进行充电，不可结束充电";
					}
					WanmaConstants.changeState.remove(elpiElectricpilecode+"|"+ePHeElectricpileHeadId);

					return new AccessErrorResult(state,msg).toString();
				}
				if (i == 19) {
					WanmaConstants.changeState.remove(elpiElectricpilecode+"|"+ePHeElectricpileHeadId);
					// 返回结束充电失败信息
					return new AccessErrorResult(6001,"请求处理中，请稍后去充电订单中查看结果").toString();
				}
				Thread.sleep(1000);
			}

		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getMessage());
			log.error("结束充电失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(1000,"error.msg.invalid.parameter")
					.toString();
		}
		log.info("******************结束充电-end************************");
		// 返回处理成功信息
		return new AccessSuccessResult().toString();
	}

	/**
	 * @Title: beginCharge
	 * @Description: 开始充电
	 * @param params
	 * @return
	 */
	@RequestMapping("/beginCharge")
	@ResponseBody
	public String beginCharge(@RequestParam Map<String, Object> params)
			throws AppRequestErrorException {

		if(StringUtils.isEmpty(params.get("org")) || StringUtils.isEmpty(params.get("payMod"))){
			return new AccessErrorResult(1001, "请升级到最新版本后方可使用此功能").toString();
		}
		if(StringUtils.isEmpty(params.get("pkElectricPile")) 
				|| StringUtils.isEmpty(params.get("elpiElectricpilecode"))
				|| StringUtils.isEmpty(params.get("ePHeElectricpileHeadId"))
				|| StringUtils.isEmpty(params.get("pkUserinfo"))
				|| StringUtils.isEmpty(params.get("scantype"))
				|| StringUtils.isEmpty(params.get("did"))
				|| StringUtils.isEmpty(params.get("preMoney"))){
			return new AccessErrorResult(1050,"error.msg.miss.parameter").toString();
		}
		
		try {
			//获取预约编号
			String bespokeCode = bespokeService.getBpByUidAndHeadid(Integer.parseInt(params.get("pkUserinfo").toString()),
					Integer.parseInt(params.get("ePHeElectricpileHeadId").toString()),
					Integer.parseInt(params.get("pkElectricPile").toString()));
			
			TblUserinfo userinfo = userinfoService.getUserById(Integer.parseInt(params.get("pkUserinfo").toString()));
			//判断是否用的登陆设备在发命令
			byte[] b = Base64Coder.decode(params.get("did").toString());
			String did = WebFilter.judgeRequest(new String(b));
			if(!did.equals(userinfo.getDid())){
				return new AccessErrorResult(1004,"error.msg.fail.checkDid").toString();
			}
			
			String elpiElectricpilecode = (String) params.get("elpiElectricpilecode");
			String ePHeElectricpileHeadId = (String) params.get("ePHeElectricpileHeadId");
			// 请求电桩后台，开始充电
			ElectricPieUtil.startCharge((String) params.get("pkElectricPile"),
					elpiElectricpilecode, 
					Integer.parseInt(ePHeElectricpileHeadId),userinfo.getPkUserinfo(), 
					userinfo.getUsinPhone(),bespokeCode,userinfo.getUsinAccountbalance(),
					params.get("scantype").toString(),Double.parseDouble(params.get("preMoney").toString()),
					Integer.parseInt(params.get("org").toString()),
					Integer.parseInt(params.get("payMod").toString()));
			WanmaConstants.changeState.put(elpiElectricpilecode+"|"+ePHeElectricpileHeadId, WanmaConstants.ELECTRICPILEHEAD_FREE);
			// 判断开始充电是否成功
			for (int i = 0; i < 20; i++) {
				Integer state=(Integer)WanmaConstants.changeState.get(elpiElectricpilecode+"|"+ePHeElectricpileHeadId);
				if (state == WanmaConstants.ELECTRICPILEHEAD_BATTERY) {
					WanmaConstants.changeState.remove(elpiElectricpilecode+"|"+ePHeElectricpileHeadId);
					break;
				}else if(state > 1000){
					String msg = "充电失败，";
					if(state == 6700){
						msg += "目前只支持二维码充电";
					}else if(state == 6000){
						msg = "亲，充电失败，信号漂移。请筛选--空闲中的电桩使用";
					}else if(state == 6601){
						msg = "亲，充电失败，信号堵塞。请重新开始充电";
					}else if(state == 6002){
						msg = "电桩服务通讯中断，暂时不可充电";
					}else if(state == 6100){
						msg = "亲，充电失败，电桩编码无效。请筛选--空闲中的其它电桩使用";
					}else if(state == 6101){
						msg = "亲，充电失败，电桩枪口编码无效。请筛选--空闲中的其它电桩使用";
					}else if(state == 6102){
						msg = "亲，充电失败，电桩无费率。请筛选--空闲中的电桩使用";
					}else if(state == 6104){
						msg = "亲，充电失败，电桩在升级。请稍等片刻使用或筛选--空闲中的电桩使用";
					}else if(state == 6200){
						msg = "亲，充电失败，枪口停用中。请筛选--空闲中的电桩使用";
					}else if(state == 6201){
						msg = "亲，充电失败，电桩未上线。请筛选--空闲中的电桩使用";
					}else if(state == 6202){
						msg = "亲，充电失败，电桩不开放。请筛选--空闲中的电桩使用";
					}else if(state == 6300){
						msg = "亲，充电失败，电桩已占用。请筛选--空闲中的电桩使用";
					}else if(state == 6301){
						msg = "亲，充电失败，电桩已占用（设置中）。请筛选--空闲中的电桩使用";
					}else if(state == 6401){
						msg = "亲，充电失败，用户长度无效。请致电我们检查用户是否有效";
					}else if(state == 6402){
						msg = "亲，充电失败，用户密码错误。请想想您的用户密码";
					}else if(state == 6403){
						msg = "亲，充电失败，手机通讯校验码失败。请重新开始充电";
					}else if(state == 6404){
						msg = "亲，充电失败，您的账户无效。请致电我们检查您的账户是否有效";
					}else if(state == 6405){
						msg = "亲，充电失败，您的用户已冻结。请致电我们检查您的账户冻结原因";
					}else if(state == 6406){
						msg = "亲，充电失败，您的账户已有预约、充电操作，请结束之前操作后重新开始充电";
					}else if(state == 6570 || state == 6601){
						msg = "亲，充电失败，电桩已占用（预约中）。请筛选--空闲中的电桩使用";
					}else if(state == 6600){
						msg = "亲，充电失败，您的账户已有预约操作，请结束之前操作后重新开始充电";
					}else if(state == 6603){
						msg = "亲，充电失败，信号堵塞。请重新开始充电";
					}else if(state == 6633){
						msg = "您有预约存在，取消之前不可进行充电操作";
					}else if(state == 6701){
						msg = "亲，充电失败，充电订单未完成或未结算。请结束之前充电操作或联系我们";
					}else if(state == 1002){
						msg = "亲，充电失败，您的账户余额不足，请充值后重新充电";
					}else if(state == 6702){
						msg += "充电枪未插好";
					}else if(state == 6703){
						msg += "枪盖未盖好";
					}else if(state == 6704){
						msg += "车与桩通讯未连接";
					}else if(state == 6800){
						msg = "亲，充电失败，您的账户已有充电操作，请结束之前操作后重新开始充电";
					}else if(state == 6801){
						msg = "亲，充电失败，电桩已占用（充电中）。请筛选--空闲中的电桩使用";
					}else if(state == 6802){
						msg = "亲，充电失败，电桩已占用（预约中）。请筛选--空闲中的电桩使用";
					}else if(state == 6803){
						msg = "亲，停止充电失败，未有充电操作。请筛选--空闲中的电桩使用";
					}else if(state == 6804){
						msg = "亲，充电失败，电桩已占用（故障中）。请筛选--空闲中的电桩使用";
					}
					log.error("充电失败，失败码：" + state + ",充电用户id：" + userinfo.getPkUserinfo());
					WanmaConstants.changeState.remove(elpiElectricpilecode+"|"+ePHeElectricpileHeadId);
					return new AccessErrorResult(state,msg).toString();
				}
				
				if (i == 19) {
					WanmaConstants.changeState.remove(elpiElectricpilecode+"|"+ePHeElectricpileHeadId);
					// 返回开始充电失败信息
					return new AccessErrorResult(6001,"请求处理中，请稍后去充电订单中查看结果").toString();
				}
				
				Thread.sleep(1000);
			}

		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getMessage());
			return new AccessErrorResult(1000,"error.msg.invalid.parameter").toString();
		}
		log.info("******************开始充电-end************************");
		// 返回处理成功信息
		return new AccessSuccessResult().toString();
	}

	/**
	 * @Title: selectChargeData
	 * @Description: 获取充电电度，金额，服务费，总费用，开始时间，结束时间
	 * @param params
	 * @return
	 */
	@RequestMapping("/selectChargeData")
	@ResponseBody
	public String selectChargeData(@RequestParam Map<String, Object> params) {
		log.info("******************充电结束页面，获取金额电量等信息-begin************************");
		Map<String, Object> chargeData = new HashMap<String, Object>();
		try {
			chargeData = chargingorderService.selectChargeData(params);
		} catch (Exception e) {
			log.error(e.getMessage());
			// 返回开始充电错误信息
			return new AccessErrorResult(2004,"error.msg.invalid.parameter")
					.toString();

		}

		log.info("******************充电结束页面，获取金额电量等信息-end************************");
		return new AccessSuccessResult(chargeData).toString();
	}

	/**
	 * @Title: paymentCharge
	 * @Description: 充电结束，支付,支付订单时将消费记录写入
	 * @param params
	 * @return
	 */
	@RequestMapping("/paymentCharge")
	@ResponseBody
	public String paymentCharge(@RequestParam Map<String, Object> params) {
		log.info("******************充电结束，支付-begin************************");
		Map<String, Object> map = null;
		try {

			map = chargeService.updateAccountbalance(params);
			Boolean flag = (Boolean) map.get("flag");
			if (!flag) {
				// 返回账户余额不足
				return new AccessErrorResult(1002,"error.msg.nomoney.bespoke")
						.toString();
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			log.error("充电结束，支付失败", e);
			// 返回开始充电错误信息
			return new AccessErrorResult(2001,"error.msg.invalid.parameter")
					.toString();
		}
		log.info("******************充电结束，支付-end************************");
		return new AccessSuccessResult(map).toString();
	}

	/**
	 * 充电订单列表、详情
	 * @param userId 用户id
	 * @return
	 */
	@RequestMapping("/chargeOrderList")
	@ResponseBody
	public String chargeOrderList(@RequestParam Map<String, Object> params,AppPager pager){
		log.info("*********获取充电订单列表--begin****************");
		if(StringUtils.isEmpty(params.get("userId"))){
			return new AccessErrorResult(1050,"error.msg.miss.parameter").toString();
		}
		params.put("pager", pager);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try{
			list = chargeService.getChargeOrderList(params);
		}catch(Exception e){
			log.error(e.getLocalizedMessage());
			log.error("充电订单列表获取失败", e);
			return new AccessErrorResult(2004,"error.msg.fail.chargeOrderList").toString();
		}
		log.info("*********获取充电订单列表--end****************");
		
		return new AccessSuccessResult(list).toString();
	}

	/**
	 * 充电订单详情
	 * @param coId 订单id
	 * @return
	 */
	@RequestMapping("/chargeOrderDetail")
	@ResponseBody
	public String chargeOrderDetail(int coId){
		log.info("*********获取充电订单详情--begin****************");
		Map<String, Object> dataRt = new HashMap<String, Object>();
		try{
			Map<String, Object> data = chargeService.chargeOrderDetail(coId);
			double cgMn = Double.valueOf(data.get("cgMn").toString());
			double svMn = Double.valueOf(data.get("svMn").toString());
			Object cpVal = data.get("cpVal");
			cpVal = cpVal==null ?"0":cpVal;
			double fee = cgMn+svMn-Double.valueOf(cpVal.toString());
			fee = fee < 0 ?0:fee;
			Object qtObj = data.get("qtDate");
			List<Map<String,Object>> details = new ArrayList<Map<String,Object>>();
			if(qtObj != null && !"".equals(qtObj.toString())){
				String qt = data.get("qtDate").toString();
				JSONArray qtArray = JSON.parseArray(qt.replace("{\"data\":", "").replaceAll("]}", "]"));
				Map<String,Object> detail = null;
				for(int i=0;i<qtArray.size();i++){
					detail = new HashMap<String, Object>();
					JSONObject qtData = qtArray.getJSONObject(i);
					String mk = qtData.getString("mark");
					if("1".equals(mk)){
						detail.put("fl", data.get("jp"));
						detail.put("qt", data.get("jqt"));
					}
					if("2".equals(mk)){
						detail.put("fl", data.get("fp"));
						detail.put("qt", data.get("fqt"));
					}
					if("3".equals(mk)){
						detail.put("fl", data.get("pp"));
						detail.put("qt", data.get("pqt"));
					}
					if("4".equals(mk)){
						detail.put("fl", data.get("gp"));
						detail.put("qt", data.get("gqt"));
					}
					details.add(detail);
				}
			}
			dataRt.put("fee", fee);
			dataRt.put("details", details);
			dataRt.put("st", data.get("st"));
			dataRt.put("et", data.get("et"));
			dataRt.put("cgMn", data.get("cgMn"));
			dataRt.put("svMn", data.get("svMn"));
			dataRt.put("sts", data.get("sts"));
			dataRt.put("code", data.get("code"));
			dataRt.put("addr", data.get("addr"));
			dataRt.put("cpVal", data.get("cpVal"));
			dataRt.put("allQlty", data.get("allQlty"));
			dataRt.put("svd", data.get("svd"));
			dataRt.put("ptName", data.get("ptName"));
		}catch(Exception e){
			log.error(e.getLocalizedMessage());
			log.error("充电订单详情获取失败", e);
			return new AccessErrorResult(2004,"error.msg.fail.chargeOrderList").toString();
		}
		log.info("*********获取充电订单详情--end****************");
		
		return new AccessSuccessResult(dataRt).toString();
	}
	
	/**
	 * 获取直流桩在充电前的自检状态
	 * @param params
	 * 			epCode 电桩编号 ，ephCode 枪口编号
	 * @return
	 */
	@RequestMapping("/dcSelfCheckStatus")
	@ResponseBody
	public String dcSelfCheck(@RequestParam Map<String, String> params){
		if(StringUtils.isEmpty(params.get("epCode")) || StringUtils.isEmpty(params.get("ephCode"))){
			return new AccessErrorResult(1050, "error.msg.miss.parameter").toString();
		}
		
		int status = chargeService.getDCSelfCheckStatus(params);
		Map<String, String> map = new HashMap<String, String>();
		map.put("status", status+"");
		return new AccessSuccessResult(map).toString();
	}
	
	


	/**
	 * @Title: beginCharge
	 * @Description: 开始充电（第三方）
	 * @param params
	 * @return
	 */
	@RequestMapping("/beginChargeTpi")
	@ResponseBody
	public String beginChargeTpi(@RequestParam Map<String, Object> params)
			throws AppRequestErrorException {

		if(StringUtils.isEmpty(params.get("org")) || StringUtils.isEmpty(params.get("payMod"))){
			return new AccessErrorResult(1001, "请升级到最新版本后方可使用此功能").toString();
		}
		if(StringUtils.isEmpty(params.get("pkElectricPile")) 
				|| StringUtils.isEmpty(params.get("elpiElectricpilecode"))
				|| StringUtils.isEmpty(params.get("ePHeElectricpileHeadId"))
				|| StringUtils.isEmpty(params.get("pkUserinfo"))
				|| StringUtils.isEmpty(params.get("scantype"))
				|| StringUtils.isEmpty(params.get("did"))
				|| StringUtils.isEmpty(params.get("preMoney"))
				||StringUtils.isEmpty(params.get("userAcc"))){
			return new AccessErrorResult(1050,"error.msg.miss.parameter").toString();
		}
		
		try {
			String elpiElectricpilecode = (String) params.get("elpiElectricpilecode");
			String ePHeElectricpileHeadId = (String) params.get("ePHeElectricpileHeadId");
			// 请求电桩后台，开始充电
			ElectricPieUtil.startCharge((String) params.get("pkElectricPile"),
					elpiElectricpilecode, 
					Integer.parseInt(ePHeElectricpileHeadId),
					Integer.valueOf(params.get("pkUserinfo").toString()), 
					params.get("userAcc").toString(),
					"",new BigDecimal(0),
					params.get("scantype").toString(),
					Double.parseDouble(params.get("preMoney").toString()),
					Integer.parseInt(params.get("org").toString()),
					Integer.parseInt(params.get("payMod").toString()));
			WanmaConstants.changeState.put(elpiElectricpilecode+"|"+ePHeElectricpileHeadId, WanmaConstants.ELECTRICPILEHEAD_FREE);
			// 判断开始充电是否成功
			for (int i = 0; i < 20; i++) {
				Integer state=(Integer)WanmaConstants.changeState.get(elpiElectricpilecode+"|"+ePHeElectricpileHeadId);
				if (state == WanmaConstants.ELECTRICPILEHEAD_BATTERY) {
					WanmaConstants.changeState.remove(elpiElectricpilecode+"|"+ePHeElectricpileHeadId);
					break;
				}else if(state > 1000){
					String msg = "充电失败，";
					if(state == 6700){
						msg += "目前只支持二维码充电";
					}else if(state == 6000){
						msg = "亲，充电失败，信号漂移。请筛选--空闲中的电桩使用";
					}else if(state == 6601){
						msg = "亲，充电失败，信号堵塞。请重新开始充电";
					}else if(state == 6002){
						msg = "电桩服务通讯中断，暂时不可充电";
					}else if(state == 6100){
						msg = "亲，充电失败，电桩编码无效。请筛选--空闲中的其它电桩使用";
					}else if(state == 6101){
						msg = "亲，充电失败，电桩枪口编码无效。请筛选--空闲中的其它电桩使用";
					}else if(state == 6102){
						msg = "亲，充电失败，电桩无费率。请筛选--空闲中的电桩使用";
					}else if(state == 6104){
						msg = "亲，充电失败，电桩在升级。请稍等片刻使用或筛选--空闲中的电桩使用";
					}else if(state == 6200){
						msg = "亲，充电失败，枪口停用中。请筛选--空闲中的电桩使用";
					}else if(state == 6201){
						msg = "亲，充电失败，电桩未上线。请筛选--空闲中的电桩使用";
					}else if(state == 6202){
						msg = "亲，充电失败，电桩不开放。请筛选--空闲中的电桩使用";
					}else if(state == 6300){
						msg = "亲，充电失败，电桩已占用。请筛选--空闲中的电桩使用";
					}else if(state == 6301){
						msg = "亲，充电失败，电桩已占用（设置中）。请筛选--空闲中的电桩使用";
					}else if(state == 6401){
						msg = "亲，充电失败，用户长度无效。请致电我们检查用户是否有效";
					}else if(state == 6402){
						msg = "亲，充电失败，用户密码错误。请想想您的用户密码";
					}else if(state == 6403){
						msg = "亲，充电失败，手机通讯校验码失败。请重新开始充电";
					}else if(state == 6404){
						msg = "亲，充电失败，您的账户无效。请致电我们检查您的账户是否有效";
					}else if(state == 6405){
						msg = "亲，充电失败，您的用户已冻结。请致电我们检查您的账户冻结原因";
					}else if(state == 6406){
						msg = "亲，充电失败，您的账户已有预约、充电操作，请结束之前操作后重新开始充电";
					}else if(state == 6570 || state == 6601){
						msg = "亲，充电失败，电桩已占用（预约中）。请筛选--空闲中的电桩使用";
					}else if(state == 6600){
						msg = "亲，充电失败，您的账户已有预约操作，请结束之前操作后重新开始充电";
					}else if(state == 6603){
						msg = "亲，充电失败，信号堵塞。请重新开始充电";
					}else if(state == 6633){
						msg = "您有预约存在，取消之前不可进行充电操作";
					}else if(state == 6701){
						msg = "亲，充电失败，充电订单未完成或未结算。请结束之前充电操作或联系我们";
					}else if(state == 1002){
						msg = "亲，充电失败，您的账户余额不足，请充值后重新充电";
					}else if(state == 6702){
						msg += "充电枪未插好";
					}else if(state == 6703){
						msg += "枪盖未盖好";
					}else if(state == 6704){
						msg += "车与桩通讯未连接";
					}else if(state == 6800){
						msg = "亲，充电失败，您的账户已有充电操作，请结束之前操作后重新开始充电";
					}else if(state == 6801){
						msg = "亲，充电失败，电桩已占用（充电中）。请筛选--空闲中的电桩使用";
					}else if(state == 6802){
						msg = "亲，充电失败，电桩已占用（预约中）。请筛选--空闲中的电桩使用";
					}else if(state == 6803){
						msg = "亲，停止充电失败，未有充电操作。请筛选--空闲中的电桩使用";
					}else if(state == 6804){
						msg = "亲，充电失败，电桩已占用（故障中）。请筛选--空闲中的电桩使用";
					}
					log.error("充电失败，失败码：" + state + ",用户标识：" + params.get("userAcc"));
					WanmaConstants.changeState.remove(elpiElectricpilecode+"|"+ePHeElectricpileHeadId);
					return new AccessErrorResult(state,msg).toString();
				}
				
				if (i == 19) {
					WanmaConstants.changeState.remove(elpiElectricpilecode+"|"+ePHeElectricpileHeadId);
					// 返回开始充电失败信息
					return new AccessErrorResult(6001,"请求处理中，请稍后去充电订单中查看结果").toString();
				}
				
				Thread.sleep(1000);
			}

		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getMessage());
			return new AccessErrorResult(1000,"error.msg.invalid.parameter").toString();
		}
		log.info("******************开始充电-end************************");
		// 返回处理成功信息
		return new AccessSuccessResult().toString();
	}
	
	/**
	 * 结束充电（第三方）
	 */
	@RequestMapping("/finishChargeTpi")
	@ResponseBody
	public String finishChargeTpi(@RequestParam Map<String, Object> params)
			throws AppRequestErrorException {

		if(StringUtils.isEmpty(params.get("pkElectricPile")) 
				|| StringUtils.isEmpty(params.get("elpiElectricpilecode"))
				|| StringUtils.isEmpty(params.get("ePHeElectricpileHeadId"))
				|| StringUtils.isEmpty(params.get("uid"))
				){
			return new AccessErrorResult(1050,"error.msg.miss.parameter").toString();
		}
		
		try {
			String elpiElectricpilecode = (String) params.get("elpiElectricpilecode");
			String ePHeElectricpileHeadId = (String) params.get("ePHeElectricpileHeadId");
			// 请求电桩后台，结束充电
			ElectricPieUtil.stopChargeTfi((String) params.get("pkElectricPile"),
					elpiElectricpilecode, Integer.parseInt(ePHeElectricpileHeadId),
					Integer.parseInt(params.get("uid").toString()),
					(String) params.get("userAcc"),
					Integer.parseInt(params.get("org").toString()));
			
			WanmaConstants.changeState.put(elpiElectricpilecode+"|"+ePHeElectricpileHeadId, WanmaConstants.ELECTRICPILEHEAD_BATTERY);
			
			// 判断结束充电是否成功
			for (int i = 0; i < 20; i++) {
				Integer state=(Integer)WanmaConstants.changeState.get(elpiElectricpilecode+"|"+ePHeElectricpileHeadId);
				if (state == WanmaConstants.ELECTRICPILEHEAD_FREE) {
					WanmaConstants.changeState.remove(elpiElectricpilecode+"|"+ePHeElectricpileHeadId);
					break;
				}else if(state >= 6000){
					String msg = "结束充电失败，";
					if(state == 6000){
						msg += "电桩通讯中断，请稍后再试";
					}else if(state == 6002 || state == 6001){
						msg += "电桩服务通讯中断，请稍后再试";
					}else if(state == 6100 || state == 6101){
						msg += "桩体参数无效，无法结束充电";
					}else if(state == 6301){
						msg = "桩在设置中，暂时无法结束充电";
					}else if(state == 6803){
						msg += "您还没有进行充电，不可结束充电";
					}
					WanmaConstants.changeState.remove(elpiElectricpilecode+"|"+ePHeElectricpileHeadId);

					return new AccessErrorResult(state,msg).toString();
				}
				if (i == 19) {
					WanmaConstants.changeState.remove(elpiElectricpilecode+"|"+ePHeElectricpileHeadId);
					// 返回结束充电失败信息
					return new AccessErrorResult(6001,"请求处理中，请稍后去充电订单中查看结果").toString();
				}
				Thread.sleep(1000);
			}

		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getMessage());
			log.error("结束充电失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(1000,"error.msg.invalid.parameter")
					.toString();
		}
		log.info("******************结束充电-end************************");
		// 返回处理成功信息
		return new AccessSuccessResult().toString();
	}
}