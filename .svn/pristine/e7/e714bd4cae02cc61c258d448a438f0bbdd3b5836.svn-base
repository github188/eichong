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
import com.wanma.app.service.ApplyEpService;
import com.wanma.app.service.TblEquipmentrepairService;
import com.wanma.app.service.TblFeedbackService;
import com.wanma.app.service.TblVersionService;
import com.wanma.model.TblEquipmentrepair;
import com.wanma.model.TblFeedback;
import com.wanma.model.TblVersion;

@Controller
@RequestMapping("/app/apply")
public class ApplyEpController {
	private static Logger log = Logger.getLogger(ApplyEpController.class);
	
	/**
	 * 添加建桩申请
	 * @param params
	 * 			a_type	申请类型 1免费建桩2自费建桩
	 *			c_type	分类 1个人2企业
	 * 			name	联系人姓名
	 *			phone	联系人电话
	 *			p_code	省份代码
	 *			c_code	城市代码
	 *			a_code	区县代码
	 *			addr	具体安装地址
	 *			h_park	是否自有车位 1否2是（个人必填）
	 *			park_num	车位数量 1少于等于10个 2大于10个（企业必填）
	 *			ep_type	设备需求 1直流2交流3交直流都有（企业必填）
	 *			i_agree	物业是否同意安装 1同意2不同意
	 *			i_report	电力是否通过报备 1通过2未通过
	 *			i_ground	安装地点 1地面2地下
	 * @return
	 */
	@RequestMapping("/addApplyEp")
	@ResponseBody
	public String applyEp(@RequestParam Map<String, String> params){
		if(StringUtils.isEmpty(params.get("a_type")) || StringUtils.isEmpty(params.get("c_type"))
				|| StringUtils.isEmpty(params.get("name")) || StringUtils.isEmpty(params.get("phone"))
				|| StringUtils.isEmpty(params.get("p_code")) || StringUtils.isEmpty(params.get("c_code"))
				|| StringUtils.isEmpty(params.get("a_code")) || StringUtils.isEmpty(params.get("addr"))
				|| StringUtils.isEmpty(params.get("i_agree")) || StringUtils.isEmpty(params.get("i_report"))
				|| StringUtils.isEmpty(params.get("i_ground")) || StringUtils.isEmpty(params.get("uid"))){
			return new AccessErrorResult(1050, "error.msg.miss.parameter").toString();
		}
		
		try {
			applyEpService.saveApplyEp(params);
		} catch (Exception e) {
			log.error(e.getStackTrace());
			return new AccessErrorResult(2002, "error.msg.invalid.sql").toString();
		}
		
		return new AccessSuccessResult().toString(); 
	}
	
	@RequestMapping("/applyList")
	@ResponseBody
	public String applyList(int uid){
		if(StringUtils.isEmpty(uid)){
			return new AccessErrorResult(1050, "error.msg.miss.parameter").toString();
		}
		
		List<Map<String, String>> applyList = applyEpService.getApplyEpList(uid);
		
		return new AccessSuccessResult(applyList).toString();
	}
	
	@Autowired
	ApplyEpService applyEpService;
}
