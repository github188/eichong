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
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.exceptions.AppRequestErrorException;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.bluemobi.product.utils.RequestParamUtil;
import com.bluemobi.product.utils.StringUtil;
import com.wanma.app.service.PowerStationDetailService;
import com.wanma.model.PowerStationDetail;
import com.wanma.model.TblPowerstation;


/**
 * 电站详情
  * @Description:
  * @author bruce cheng(http://blog.csdn.net/brucehome)
  * @createTime：2015-3-11 下午04:25:53 
  * @updator： 
  * @updateTime：   
  * @version：V1.0
 */
@Controller
@RequestMapping("/app/powerStationDetail")
public class PowerStationDetailController {
    private static Logger log = Logger.getLogger(PowerStationDetailController.class);
	
    @Autowired
    private PowerStationDetailService powerStationDetailService;
    
    /**
     * 获取电站详情
     */
    @RequestMapping("/getPowerStationDetail")
	@ResponseBody
	public String  getPowerStationDetail(HttpServletRequest request) throws AppRequestErrorException {
    	
    	//电桩Id
		String powerStationId = RequestParamUtil.getEncodeParam(request, "powerStationId");
		//用户id
		String pkUserinfo = RequestParamUtil.getEncodeParam(request, "pkUserinfo");
		String longitude = RequestParamUtil.getEncodeParam(request,"longitude");
		String latitude = RequestParamUtil.getEncodeParam(request,"latitude");
    	List<PowerStationDetail> powerStationDetailList=new ArrayList<PowerStationDetail>();
    	try {
    		TblPowerstation tblPowerstation=new TblPowerstation();
    		
			if (StringUtil.isEmpty(powerStationId)) {
				// 未输入 电桩ID
				return new AccessErrorResult(1050,"error.msg.miss.parameter").toString();
			}
			if (StringUtil.isEmpty(pkUserinfo)) {
				// 未输入 用户id
				return new AccessErrorResult(1050,"error.msg.miss.parameter").toString();
			}
			tblPowerstation.setPkPowerstation(Integer.parseInt(powerStationId));
			tblPowerstation.setPkUserinfo(Integer.parseInt(pkUserinfo));
			if(!StringUtils.isEmpty(longitude) && !StringUtils.isEmpty(latitude)){
				tblPowerstation.setPostLongitude(new BigDecimal(longitude));
				tblPowerstation.setPostLatitude(new BigDecimal(latitude));
			}
			powerStationDetailList=powerStationDetailService.getPowerStationDetail(tblPowerstation);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取电站详情数据失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(2004,"error.msg.invalid.parameter")
					.toString();
		}
		// 返回处理成功信息
		return AccessSuccessResult.nAccessSuccessResult(powerStationDetailList).toJSONString();
	}
    
    /**
     * 获取电站详情
     */
    @RequestMapping("/getPointDetail")
	@ResponseBody
	public String  getPointDetail(HttpServletRequest request) throws AppRequestErrorException {
    	
    	//电桩Id
		String powerStationId = RequestParamUtil.getEncodeParam(request, "powerStationId");
		String longitude = RequestParamUtil.getEncodeParam(request,"longitude");
		String latitude = RequestParamUtil.getEncodeParam(request,"latitude");
		Map<String, Object> data = new HashMap<String, Object>();
    	try {
    		TblPowerstation tblPowerstation=new TblPowerstation();
			if (StringUtil.isEmpty(powerStationId)) {
				// 未输入 电桩ID
				return new AccessErrorResult(1050,"error.msg.miss.parameter").toString();
			}
			tblPowerstation.setPkPowerstation(Integer.parseInt(powerStationId));
			if(!StringUtils.isEmpty(longitude) && !StringUtils.isEmpty(latitude)){
				tblPowerstation.setPostLongitude(new BigDecimal(longitude));
				tblPowerstation.setPostLatitude(new BigDecimal(latitude));
			}
			data=powerStationDetailService.getDetailById(tblPowerstation);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取电站详情数据失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(2004,"error.msg.invalid.parameter")
					.toString();
		}
		// 返回处理成功信息
		return AccessSuccessResult.nAccessSuccessResult(data).toJSONString();
	}
}