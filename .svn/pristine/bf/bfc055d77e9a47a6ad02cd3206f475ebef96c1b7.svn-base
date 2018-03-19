package com.wanma.app.controller;

import java.sql.Timestamp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.wanma.app.service.impl.AppPublishEpServiceImpl;
import com.wanma.model.TblPublishEp;

@Controller
@RequestMapping("/app/publishEp")
public class AppPublishEpController {
	private static Logger log = Logger.getLogger(AppPublishEpController.class);
	
	/**
	 * 添加分享
	 * @param params
	 * 			img 图片
	 * 			address 地址
	 * 			longitude 经度
	 * 			latitude 维度
	 * 			parameter_note 参数说明
	 * 			note 备注
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public String addShareEp(TblPublishEp tblPublishEp){
		
		if(StringUtils.isEmpty(tblPublishEp.getAddress()) || StringUtils.isEmpty(tblPublishEp.getLongitude())
				|| StringUtils.isEmpty(tblPublishEp.getLatitude()) || StringUtils.isEmpty(tblPublishEp.getParameter_note())){
			log.error("参数不完整！AppPublishEpController：addShareEp");
			return new AccessErrorResult(1050,"error.msg.miss.parameter").toString();
		}
		tblPublishEp.setEdittime(new Timestamp(System.currentTimeMillis()));
		try{
			appPublishEpService.addShareEp(tblPublishEp);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			log.error("获取产品信息错误", e);
			return new AccessErrorResult(2002,"error.msg.invalid.parameter")
					.toString();
		}
		return new AccessSuccessResult().toString();
	}
	
	@Autowired
	AppPublishEpServiceImpl appPublishEpService;
}
