package com.wanma.app.controller;

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

import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.bluemobi.product.utils.ObjectUtil;
import com.bluemobi.product.utils.StringUtil;
import com.wanma.app.service.TblEquipmentrepairService;
import com.wanma.app.service.TblFeedbackService;
import com.wanma.app.service.TblVersionService;
import com.wanma.model.TblEquipmentrepair;
import com.wanma.model.TblFeedback;
import com.wanma.model.TblVersion;

/**
 * @ClassName: AppOtherController
 * @Description: 万马APP更多其他业务操作控制层
 * @author chenb
 * @date 2015年3月15日 下午4:56:17
 */
@Controller
@RequestMapping("app/other")
public class AppOtherController {
	private static Logger log = Logger.getLogger(AppOtherController.class);
	/** 设备维修业务处理对象 */
	@Autowired
	private TblEquipmentrepairService tblEquipmentrepairService;
	/** 意见反馈业务处理对象 */
	@Autowired
	private TblFeedbackService tblFeedbackService;
	/** 版本信息业务处理对象 */
	@Autowired
	private TblVersionService tblVersionService;

	/**
	 * @Title: addTblEquipmentrepair
	 * @Description: 添加用户设备维修信息
	 * @param @param request
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping("/addTblEquipmentrepair")
	@ResponseBody
	public String addTblEquipmentrepair(HttpServletRequest request) {
		log.info("******************保存用户的设备维修信息-begin************************");
		// 用户ID
		String eqreUserid = request.getParameter("eqreUserid");
		if (StringUtil.isEmpty(eqreUserid))
			return new AccessErrorResult(1050,"error.msg.miss.parameter").toString();
		// 维修类型 注意如果类型是静态数据，请后继者定义好类型CODE
		String eqreWarrantytypeid = request.getParameter("eqreWarrantytypeid");
		if (StringUtil.isEmpty(eqreWarrantytypeid))
			return new AccessErrorResult(1050,"error.msg.miss.parameter").toString();
		String epId = request.getParameter("epId");
		if(StringUtils.isEmpty(epId)){
			return new AccessErrorResult(1050,"error.msg.miss.parameter").toString();
		}
		String deviceType = request.getParameter("deviceType");
		if(StringUtils.isEmpty(deviceType)){
			return new AccessErrorResult(1050, "error.msg.miss.parameter").toString();
		}
		String eqreContent = request.getParameter("eqreContent");
		if (StringUtil.isEmpty(eqreContent))
			eqreContent="";
		try {
			TblEquipmentrepair equipmentrepair = new TblEquipmentrepair();
			// 保存信息
			equipmentrepair.setEqreContent(eqreContent);
			equipmentrepair.setEqreUserid(Integer.parseInt(eqreUserid));
			equipmentrepair.setEqreWarrantytypeid(Integer
					.parseInt(eqreWarrantytypeid));
			equipmentrepair.setEpid(Integer.parseInt(epId));
			equipmentrepair.setDeviceType(Integer.parseInt(deviceType));
			// 未处理
			equipmentrepair.setEqreWarrantystatus(1);
			// 显示
			equipmentrepair.setEqreStatus(0);
			equipmentrepair.setEqreCreatedate(new Date());
			equipmentrepair.setEqreUpdatedate(new Date());
			// 添加记录
			tblEquipmentrepairService.addTblEquipmentrepair(equipmentrepair);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			return new AccessErrorResult(2002,"error.msg.invalid.parameter")
					.toString();
		}
		log.info("******************保存用户的设备维修信息-end************************");
		return new AccessSuccessResult().toString();
	}

	/**
	 * @Title: addTblFeedBack
	 * @Description: 保存意见反馈
	 * @param request
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping("/addTblFeedBack")
	@ResponseBody
	public String addTblFeedBack(HttpServletRequest request) {
		log.info("******************保存用户的意见反馈-begin************************");
		// 用户ID
		String febaUserid = request.getParameter("febaUserid");
		if (StringUtil.isEmpty(febaUserid))
			return new AccessErrorResult(1050,"error.msg.miss.parameter").toString();
		// 反馈内容
		String febaContent = request.getParameter("febaContent");
		if (StringUtil.isEmpty(febaContent))
			return new AccessErrorResult(1050,"error.msg.miss.parameter").toString();
		try {
			// 保存意见反馈信息
			TblFeedback feedback = new TblFeedback();
			feedback.setFebaUserid(Integer.parseInt(febaUserid));
			feedback.setFebaContent(febaContent);
			feedback.setFebaCreatedate(new Date());
			feedback.setFebaUpdatedate(new Date());
			tblFeedbackService.addTblFeedbac(feedback);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e);
			return new AccessErrorResult(2002,"error.msg.invalid.parameter")
					.toString();
		}
		log.info("******************保存用户的意见反馈-end************************");
		return new AccessSuccessResult().toString();
	}

	/**
	 * 获取用户反馈
	 * @param param 
	 * 			userId 用户id
	 * @return
	 */
	@RequestMapping("/getMyFB")
	@ResponseBody
	public String getMyFB(@RequestParam Map<String, Object> param){
		if(StringUtils.isEmpty(param) || StringUtils.isEmpty(param.get("userId"))){
			return new AccessErrorResult(1050,"error.msg.miss.parameter").toString();
		}
		
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		try{
			list = tblFeedbackService.getMyFeedbackList(param);
		}catch(Exception e){
			log.error(e.getStackTrace());
			return new AccessErrorResult(1001, "error.msg.invalid.parameter").toString();
		}
		
		return new AccessSuccessResult(list).toString();
	}
	
	/**
	 * @Title: getVersionInfo
	 * @Description: 获取当前版本信息
	 * @param request
	 * @return String
	 * @throws
	 */
	@RequestMapping("/getVersionInfo")
	@ResponseBody
	public String getVersionInfo(@RequestParam Map<String, Object> params) {
		log.info("******************获取当前版本信息-begin************************");
		TblVersion tblVersion = null;
		try {
			// 版本号
			String versNumber = (String) params.get("versNumber");
			// 设备类型
			int versType = Integer.parseInt((String) params.get("versType"));
			tblVersion = tblVersionService.getTblVersion(versType);
			if (ObjectUtil.isNotEmpty(tblVersion)) {
				// 最新版本号
				String newVersNumber = tblVersion.getVersNumber();
				//if (!versNumber.equals(newVersNumber)) {
					return new AccessSuccessResult(tblVersion).toString();
				//}
			}

		} catch (Exception e) {
			// 保存错误LOG
			log.error(e);
			return new AccessErrorResult(2004,"error.msg.invalid.parameter")
					.toString();
		}
		log.info("******************获取当前版本信息-end************************");
		// 此处返回的给app端状态为0表明不需要更新
		return new AccessErrorResult().toString();
	}
}
