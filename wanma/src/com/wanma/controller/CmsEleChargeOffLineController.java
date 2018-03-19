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
import com.wanma.model.TblEleChargeOffLine;

import com.wanma.model.TblUser;
import com.wanma.service.CmsCommitLogService;
import com.wanma.service.CmsEleChargeOffLineService;

import com.wanma.web.support.utils.ApiUtil;
import com.wanma.web.support.utils.HttpsUtil;




/**
 * @Description: 离线充电设置controller
 * @author lyh
 * @updateTime：
 * @version：V3.5.9
 */
@Controller
@RequestMapping("/admin/electricOffLine")
public class CmsEleChargeOffLineController {
	// 日志输出对象
	private static Logger log = Logger.getLogger(CmsEleChargeOffLineController.class);
	@Autowired
	CmsEleChargeOffLineService cmsEleChargeOffLineService;
	@Autowired
	CmsCommitLogService commitLogService;
	/**
	 * ecOffLineList列表
	 * 
	 * @param pager
	 * @param advModel
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("ecOffLineList")
	public String getEcOffLineList(@ModelAttribute("pager") DwzPagerMySQL pager,TblEleChargeOffLine ecOffLine, Model model, HttpServletRequest request) {
		log.info("******************获取ecOffLineList信息列表-begin************************");
		try {
			TblUser loginUser = SessionMgr.getWebUser(request);
			ecOffLine.setElpiUserid(loginUser.getUserId().toString());
			ecOffLine.setUserLevel(loginUser.getUserLevel().toString());
			// 充电点总数
			long total = cmsEleChargeOffLineService.getEleChargeOffLineCount(ecOffLine);
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			// 设置查询参数
			ecOffLine.setPager(pager);
			// 获取电桩列表
			List<TblEleChargeOffLine> ecOffLineList = cmsEleChargeOffLineService.getEleChargeOffLineList(ecOffLine);
			pager.setTotal(total);
			//将数据放入会话
			model.addAttribute("pager",pager);
			model.addAttribute("proviceMap", WanmaConstants.provinceMap);
			model.addAttribute("ecOffLine", ecOffLine);
			model.addAttribute("ecOffLineList",ecOffLineList);
		} catch (Exception e) {
			log.error("获取ecOffLineList列表失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(1000, "error.msg.invalid.parameter")
					.toString();
		}
		log.info("******************获取ecOffLineList信息列表-end************************");
		// 跳转至banner列表页面
		return "backstage/electricOffLine/electric-offLine-list";
	}
	
	@RequestMapping("/setEcOffLine")
	@ResponseBody
	public String setEcOffLine(TblEleChargeOffLine ecOffLine,HttpServletRequest request) throws IOException, ParseException {
		DwzAjaxResult dwzResult;
		String elpiElectricpilecode[]= request.getParameter("elpiElectricpilecode").split(":");
		String argValue = request.getParameter("argValue");
		MessageManager m = new MessageManager();
		String apiBaseUrl = m.getSystemProperties("apiRoot");
		
		try{
			TblUser loginUser = SessionMgr.getWebUser(request);
			for(int i=0;i<elpiElectricpilecode.length;i++){
				int sum =cmsEleChargeOffLineService.findCodeFormEleChargeOffLine(elpiElectricpilecode[i]);
				ecOffLine.setElpiElectricpilecode(elpiElectricpilecode[i]);
				ecOffLine.setArgValue(Integer.parseInt(argValue));
				ecOffLine.setOperator(loginUser.getUserAccount().toString());
					if(sum==0){
						cmsEleChargeOffLineService.insertEcOffLine(ecOffLine);
					}else{
						cmsEleChargeOffLineService.updateEcOffLine(ecOffLine);
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
				//调用接口设置离线充电设置
			HttpsUtil.getResponseParam(apiBaseUrl + "/app/net/sendChargeSet.do?paramStrs=" + sendStr + "&t=" + token, "status");
			}
			dwzResult = new DwzAjaxResult("200", "离线充电设置成功", "ecOffLineList","","closeCurrent", "");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			dwzResult = new DwzAjaxResult("300", "下发失败:系统错误", "", "", "");
		}
		return new JsonObject(dwzResult).toString();
	}
	
}


