package com.wanma.controller.itf;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanma.support.common.FailedResponse;
import com.wanma.support.common.WanmaConstants;
import com.wanma.support.utils.ApiUtil;
import com.wanma.support.utils.HttpUtil;
import com.wanma.support.utils.PasswordUtil;


/**
 * @Description: 电桩充电控制层
 * @author wbc
 * @createTime：2015-11-19 16:25:05
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/itf/charge")
public class TblElectricpileChargeController{

	/**
	 * 直流桩充电自检状态
	 * @author mb
	 * @param request
	 */
	@RequestMapping("/checkStatus")
	@ResponseBody
	public String checkStatus(HttpServletRequest request){
		String epCode = request.getParameter("epCode");//桩体编号
		String ephCode = request.getParameter("ephCode");//枪头编号
		// 准备参数params
		Map<String, String> params = new HashMap<String, String>();
		// 判断信息是否完整, 如不完整则返回FailedResponse
		if(StringUtils.isBlank(epCode)
			||StringUtils.isBlank(ephCode)){
		    return new FailedResponse(1050,"输入参数不全！").toString();	
		}
		params.put("epCode", epCode);
		params.put("ephCode", ephCode);
		return HttpUtil.doPost(WanmaConstants.API_URL_CHECKSTATUS, params);
	}

	/**
	 * 充电实时详情信息
	 * @author mb
	 * @param request
	 */
	@RequestMapping("/getChangeInfoN")
	@ResponseBody
	public String getChangeInfoN(HttpServletRequest request){
		String pkUserinfo = request.getParameter("pkUserinfo");//用户id
		// 准备参数params
		Map<String, String> params = new HashMap<String, String>();
		// 判断信息是否完整, 如不完整则返回FailedResponse
		if(StringUtils.isBlank(pkUserinfo)){
		    return new FailedResponse(1050,"输入参数不全！").toString();	
		}
		params.put("pkUserinfo", pkUserinfo);
		return HttpUtil.doPost(WanmaConstants.API_URL_CHANGE_INFO, params);
	}
	
	/**
	 * 立即充电
	 * @author mb
	 * @param request
	 */
	@RequestMapping("/beginCharge")
	@ResponseBody
	public String beginCharge(HttpServletRequest request){
		String pkElectricPile = request.getParameter("pkElectricPile");//桩体id
		String elpiElectricpilecode = request.getParameter("elpiElectricpilecode");//桩体编号
		String ePHeElectricpileHeadId = request.getParameter("ePHeElectricpileHeadId");//枪头编号
		String pkUserinfo = request.getParameter("pkUserinfo");//用户id
		String scantype = request.getParameter("scantype");//1二维码2验证码
		String did = request.getParameter("did");//设备码（需加密）
		did = ApiUtil.encode(did);
		String preMoney = request.getParameter("preMoney");//预冲金额
		String org = request.getParameter("org");
		String payMod = request.getParameter("payMod");
		// 准备参数params
		Map<String, String> params = new HashMap<String, String>();
		// 判断信息是否完整, 如不完整则返回FailedResponse
		if(StringUtils.isBlank(pkElectricPile)
			||StringUtils.isBlank(elpiElectricpilecode)
			||StringUtils.isBlank(ePHeElectricpileHeadId)
			||StringUtils.isBlank(pkUserinfo)
			||StringUtils.isBlank(scantype)
			||StringUtils.isBlank(did)
			){
		    return new FailedResponse(1050,"输入参数不全！").toString();	
		}
		params.put("pkElectricPile", pkElectricPile);
		params.put("elpiElectricpilecode", elpiElectricpilecode);
		params.put("ePHeElectricpileHeadId", ePHeElectricpileHeadId);
		params.put("pkUserinfo", pkUserinfo);
		params.put("scantype", scantype);
		params.put("did", did);
		params.put("preMoney", preMoney);
		params.put("org", org);
		params.put("payMod", payMod);
		return HttpUtil.doPost(WanmaConstants.API_URL_BEGIN_CHARGE, params);
	}
	
	/**
	 * 结束充电
	 * @author mb
	 * @param request
	 */
	@RequestMapping("/finishCharge")
	@ResponseBody
	public String finishCharge(HttpServletRequest request){
		String pkElectricPile = request.getParameter("pkElectricPile");//桩体id
		String elpiElectricpilecode = request.getParameter("elpiElectricpilecode");//桩体编号
		String ePHeElectricpileHeadId = request.getParameter("ePHeElectricpileHeadId");//枪头编号
		String did = request.getParameter("did");//设备码（需加密）
		did = ApiUtil.encode(did);
		String uid = request.getParameter("uid");//用户id
		// 准备参数params
		Map<String, String> params = new HashMap<String, String>();
		// 判断信息是否完整, 如不完整则返回FailedResponse
		if(StringUtils.isBlank(pkElectricPile)
			||StringUtils.isBlank(elpiElectricpilecode)
			||StringUtils.isBlank(ePHeElectricpileHeadId)
			||StringUtils.isBlank(did)
			||StringUtils.isBlank(uid)
			){
		    return new FailedResponse(1050,"输入参数不全！").toString();	
		}
		params.put("pkElectricPile", pkElectricPile);
		params.put("elpiElectricpilecode", elpiElectricpilecode);
		params.put("ePHeElectricpileHeadId", ePHeElectricpileHeadId);
//		params.put("did", did);
		params.put("did", PasswordUtil.getWanmaDeviceId());
		params.put("uid", uid);
		return HttpUtil.doPost(WanmaConstants.API_URL_FINISH_CHARGE, params);
	}
	
	/**
	 * 结束充电
	 * @author mb
	 * @param request
	 */
	@RequestMapping("/publishEp")
	@ResponseBody
	public String publishEp(HttpServletRequest request){
		String address = request.getParameter("address");//电桩地址
		String longitude = request.getParameter("longitude");//经度
		String latitude = request.getParameter("latitude");//维度
		String parameter_note = request.getParameter("parameter_note");//参数说明
		String allMultiFile = request.getParameter("allMultiFile");//图片
		// 准备参数params
		Map<String, String> params = new HashMap<String, String>();
		// 判断信息是否完整, 如不完整则返回FailedResponse
		if(StringUtils.isBlank(address)
			||StringUtils.isBlank(longitude)
			||StringUtils.isBlank(latitude)
			||StringUtils.isBlank(parameter_note)
			){
		    return new FailedResponse(1050,"输入参数不全！").toString();	
		}
		params.put("address", address);
		params.put("longitude", longitude);
		params.put("latitude", latitude);
		params.put("parameter_note", parameter_note);
		params.put("allMultiFile", StringUtils.isNotBlank(allMultiFile)?allMultiFile:null);
		return HttpUtil.doPost(WanmaConstants.API_URL_PUBLISH_EP, params);
	}
	/**
	 * 充电结束页
	 * @author mb
	 * @param request
	 */
	@RequestMapping("/selectChargeData")
	@ResponseBody
	public String selectChargeData(HttpServletRequest request){
		String chOrTransactionNumber = request.getParameter("chOrTransactionNumber");//充电流水号
		String pkUserinfo = request.getParameter("pkUserinfo");//用户id
		// 准备参数params
		Map<String, String> params = new HashMap<String, String>();
		// 判断信息是否完整, 如不完整则返回FailedResponse
		if(StringUtils.isBlank(chOrTransactionNumber)
		||StringUtils.isBlank(pkUserinfo)

				){
			return new FailedResponse(1050,"输入参数不全！").toString();	
		}
		params.put("chOrTransactionNumber", chOrTransactionNumber);
		params.put("pkUserinfo", pkUserinfo);

		return HttpUtil.doPost(WanmaConstants.API_URL_SELECT_CHARGE, params);
	}
}