package com.wanma.app.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.exceptions.AppRequestErrorException;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.bluemobi.product.utils.StringUtil;
import com.wanma.app.service.TblUsershareelecService;
import com.wanma.model.TblUsershareelec;


/**
 * 电桩查找地图模式，分享
  * @Description:
  * @author bruce cheng(http://blog.csdn.net/brucehome)
  * @createTime：2015-3-11 下午04:25:53 
  * @updator： 
  * @updateTime：   
  * @version：V1.0
 */
@Controller
@RequestMapping("/app/userShareElec")
public class TblUsershareelecController {
    private static Logger log = Logger.getLogger(TblUsershareelecController.class);
	
    @Autowired
    private TblUsershareelecService tblUsershareelecService;

    
    /**
     * 电桩查找地图模式，分享
     */
    @RequestMapping("/userShareElec")
	@ResponseBody
	public String  userShareElec(TblUsershareelec tblUsershareelec) throws AppRequestErrorException {
    	

		
    	try {
    		
			if (tblUsershareelec.getUselUserid()<0) {
				// 未输入 电桩ID
				return new AccessErrorResult(1050,"error.msg.miss.parameter").toString();
			}
			if (StringUtil.isEmpty(tblUsershareelec.getUselElecaddress())) {
				// 未输入 电桩ID
				return new AccessErrorResult(1050,"error.msg.miss.parameter").toString();
			}
			tblUsershareelecService.userShareElec(tblUsershareelec);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("地图模式/分享失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(2002,"error.msg.invalid.parameter")
					.toString();
		}
		// 返回处理成功信息
		return new AccessSuccessResult().toString();
	}

}