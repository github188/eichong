package com.wanma.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.common.MessageManager;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.JsonObject;
import com.wanma.common.SessionMgr;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblSocCharge;
import com.wanma.model.TblUser;
import com.wanma.service.CmsCommitLogService;
import com.wanma.service.CmsSocChargeService;
import com.wanma.web.support.utils.ApiUtil;
import com.wanma.web.support.utils.HttpsUtil;




/**
 * @Description: 定时充电管理controller
 * @author lyh
 * @updateTime：
 * @version：V3.5.7
 */
@Controller
@RequestMapping("/admin/socCharge")
public class CmsSocChargeController {
	// 日志输出对象
	private static Logger log = Logger.getLogger(CmsSocChargeController.class);
	@Autowired
	CmsSocChargeService cmsSocChargeService;
	@Autowired
	CmsCommitLogService commitLogService;
	/**
	 * socCharge列表
	 * 
	 * @param pager
	 * @param advModel
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("socChargeList")
	public String listSplash(@ModelAttribute("pager") DwzPagerMySQL pager,TblSocCharge socCharge, Model model, HttpServletRequest request) {
		log.info("******************获取socCharge信息列表-begin************************");
		try {
			TblUser loginUser = SessionMgr.getWebUser(request);
			socCharge.setElpiUserid(loginUser.getUserId().toString());
			socCharge.setUserLevel(loginUser.getUserLevel().toString());
			// 充电点总数
			long total = cmsSocChargeService.getElectricpileCount(socCharge);
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			// 设置查询参数
			socCharge.setPager(pager);
			// 获取电桩列表
			List<TblSocCharge> socChargeList = cmsSocChargeService.getElectricpileList(socCharge);
			pager.setTotal(total);
			//将数据放入会话
			model.addAttribute("pager",pager);
			model.addAttribute("proviceMap", WanmaConstants.provinceMap);
			model.addAttribute("socCharge", socCharge);
			model.addAttribute("socChargeList",socChargeList);
		} catch (Exception e) {
			log.error("获取socCharge列表失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(1000, "error.msg.invalid.parameter")
					.toString();
		}
		log.info("******************获取timeCharge信息列表-end************************");
		// 跳转至banner列表页面
		return "backstage/socCharge/socCharge";
	}
	
	@RequestMapping("/setSocCharge")
	@ResponseBody
	public String setSocCharge(TblSocCharge socCharge,HttpServletRequest request) throws IOException, ParseException {
		DwzAjaxResult dwzResult;
		String elpiElectricpilecode[]= request.getParameter("elpiElectricpilecode").split(":");
		String argValue = request.getParameter("argValue");
		MessageManager m = new MessageManager();
		String apiBaseUrl = m.getSystemProperties("apiRoot");
		
		try{
			TblUser loginUser = SessionMgr.getWebUser(request);
			for(int i=0;i<elpiElectricpilecode.length;i++){
				int sum =cmsSocChargeService.findCodeFormSocCharge(elpiElectricpilecode[i]);
				socCharge.setElpiElectricpilecode(elpiElectricpilecode[i]);
				socCharge.setArgValue(Integer.parseInt(argValue));
				socCharge.setOperator(loginUser.getUserAccount().toString());
					if(sum==0){
						cmsSocChargeService.insertSocCharge(socCharge);
					}else{
						cmsSocChargeService.updateSocCharge(socCharge);
					}
			}
			//将桩体编号拼成要发送的格式
			String sendStr = "";
			for(String code : elpiElectricpilecode){
				sendStr += code.trim() + ",";
			}
			if(sendStr.length() > 0){
				sendStr = sendStr.substring(0, sendStr.length() - 1);
				sendStr += ":10316";
				String token = ApiUtil.getToken();
				//调用接口定Soc开关
			HttpsUtil.getResponseParam(apiBaseUrl + "/app/net/sendChargeSet.do?paramStrs=" + sendStr + "&t=" + token, "status");
			}
			dwzResult = new DwzAjaxResult("200", "定Soc成功", "socChargeList","","closeCurrent", "");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			dwzResult = new DwzAjaxResult("300", "下发失败:系统错误", "", "", "");
		}
		return new JsonObject(dwzResult).toString();
	}
	
}


