package com.wanma.web.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.bluemobi.product.utils.ObjectUtil;
import com.bluemobi.product.utils.StringUtil;
import com.wanma.common.SessionMgr;
import com.wanma.model.TblEquipmentrepair;
import com.wanma.model.TblFeedback;
import com.wanma.model.TblVersion;
import com.wanma.web.service.WebEquipmentrepairService;
import com.wanma.web.service.WebTblFeedbackService;
import com.wanma.web.service.WebVersionService;

/**
 * @ClassName: AppOtherController
 * @Description: 万马APP更多其他业务操作控制层
 * @author chenb
 * @date 2015年3月15日 下午4:56:17
 */
@Controller
@RequestMapping("web/other")
public class WebOtherController {
	private static Log log = LogFactory.getLog(WebOtherController.class);
	/** 设备维修业务处理对象 */
	@Autowired
	private WebEquipmentrepairService tblEquipmentrepairService;
	/** 意见反馈业务处理对象 */
	@Autowired
	private WebTblFeedbackService tblFeedbackService;
	/** 版本信息业务处理对象 */
	@Autowired
	private WebVersionService tblVersionService;

	/**
	 * @Title: addTblEquipmentrepair
	 * @Description: 保存用户设备维修信息
	 * @param @param request
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping("/addTblEquipmentrepair")
	@ResponseBody
	public String addTblEquipmentrepair(@RequestParam Map<String,Object> param) {
		return tblEquipmentrepairService.addTblEquipmentrepair(param).toString();
	}

	@RequestMapping("/addTblFeedBack")
	@ResponseBody
	public String addTblFeedBack(HttpServletRequest request) {
		log.info("******************保存用户的意见反馈-begin************************");
		// 用户ID
		Long febaUserid =SessionMgr.getUserId(request);
		if (febaUserid!=null)
			// 请传入ID！
			return new AccessErrorResult("请传入用户ID！").toString();
		// 反馈内容
		String febaContent = request.getParameter("content");
		if (StringUtil.isEmpty(febaContent))
			// 请传入ID！
			return new AccessErrorResult("请传入反馈内容！").toString();
		try {
			// 保存意见反馈信息
			TblFeedback feedback = new TblFeedback();
			feedback.setFebaUserid(febaUserid.intValue());
			feedback.setFebaContent(febaContent);
			feedback.setFebaCreatedate(new Date());
			feedback.setFebaUpdatedate(new Date());
			tblFeedbackService.addFeedback(feedback);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e);
			System.out.println(e);
			return new AccessErrorResult("error.msg.invalid.parameter")
					.toString();
		}
		log.info("******************保存用户的意见反馈-end************************");
		return new AccessSuccessResult().toString();
	}


	/**
	 * @Title: getVersionInfo
	 * @Description: 获取当前版本信息
	 * @param params
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
				if (!versNumber.equals(newVersNumber)) {
					return new AccessSuccessResult(tblVersion).toString();
				}
			}

		} catch (Exception e) {
			// 保存错误LOG
			log.error(e);
			return new AccessErrorResult("error.msg.invalid.parameter")
					.toString();
		}
		log.info("******************获取当前版本信息-end************************");
		// 此处返回的给app端状态为0表明不需要更新
		return new AccessErrorResult().toString();
	}
}
