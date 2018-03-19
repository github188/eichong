package com.wanma.app.controller;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.exceptions.AppRequestErrorException;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.bluemobi.product.utils.RequestParamUtil;
import com.bluemobi.product.utils.StringUtil;
import com.wanma.app.service.AppUserinfoService;
import com.wanma.model.TblUserinfo;

/**
 * 
         万马充值支付
  * @author bruce cheng(http://blog.csdn.net/brucehome)
  * @createTime 2015-4-15 上午11:14:26
  * @updator： 
  * @updateTime：   
  * @version：V1.0
  * 其他可选例子：
 */
@Controller
@RequestMapping("/app/payRecharge")
public class AppPayController {


    private static Logger log = Logger.getLogger(AppPayController.class);
	
    @Autowired
	private AppUserinfoService userinfoService;
     
	   /*  
	    * 
	    * 暂时注释
	    * 
	    * 用户充值
	    *
    @RequestMapping("/userRecharge")
	@ResponseBody
	public String  userRecharge(HttpServletRequest request) throws AppRequestErrorException {
    	
    	//用户Id
		String userId = RequestParamUtil.getEncodeParam(request, "userId");
		String moneySum = RequestParamUtil.getEncodeParam(request, "moneySum");//金额
 
		TblUserinfo tblUserinfo=new TblUserinfo();
    	try {
    		 
			if (StringUtil.isEmpty(userId)) {
				// 未输入 电桩ID
				return new AccessErrorResult(1050,"error.msg.mess.parameter").toString();
			}
			if (StringUtil.isEmpty(moneySum)) {
				// 未输入 电桩ID
				return new AccessErrorResult(1050,"error.msg.miss.parameter").toString();
			}
			
			tblUserinfo.setPkUserinfo(Integer.parseInt(userId));
			tblUserinfo.setUsinAccountbalance(new BigDecimal(moneySum));
			userinfoService.userRecharge(tblUserinfo);
			tblUserinfo=userinfoService.getUserById(Integer.parseInt(userId));
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("充值失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(1000,"error.msg.invalid.parameter")
					.toString();
		}
		// 返回处理成功信息
		return new AccessSuccessResult(tblUserinfo).toString();
	}
   */
    
}
