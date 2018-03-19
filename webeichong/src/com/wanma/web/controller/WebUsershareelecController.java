package com.wanma.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.bluemobi.product.utils.StringUtil;
import com.wanma.model.TblUsershareelec;
import com.wanma.web.service.WebUsershareelecService;

/**
 * 电桩查找地图模式，分享
 * 
 * @Description:
 * @author bruce cheng(http://blog.csdn.net/brucehome)
 * @createTime：2015-3-11 下午04:25:53
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/web/userShareElec")
public class WebUsershareelecController {
	private static Log log = LogFactory
			.getLog(WebUsershareelecController.class);

	@Autowired
	private WebUsershareelecService tblUsershareelecService;

	/**
	 * 电桩查找地图模式，分享
	 */
	@RequestMapping("/userShareElec")
	@ResponseBody
	public String userShareElec(TblUsershareelec tblUsershareelec){

		try {

			if (tblUsershareelec.getUselUserid() < 0) {
				// 未输入 电桩ID
				return new AccessErrorResult("error.msg.invalid.parameter")
						.toString();
			}
			if (StringUtil.isEmpty(tblUsershareelec.getUselElecaddress())) {
				// 未输入 电桩ID
				return new AccessErrorResult("error.msg.invalid.parameter")
						.toString();
			}
			tblUsershareelecService.userShareElec(tblUsershareelec);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("地图模式/分享失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult("error.msg.invalid.parameter")
					.toString();
		}
		// 返回处理成功信息
		return new AccessSuccessResult().toString();
	}

}