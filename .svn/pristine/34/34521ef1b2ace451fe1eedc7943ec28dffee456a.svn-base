package com.wanma.controller;

import java.util.List;
import java.util.Map;

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
import com.wanma.app.controller.ElectricPileListController;
import com.wanma.common.SessionMgr;
import com.wanma.model.TblSimCard;
import com.wanma.model.TblUser;
import com.wanma.service.CmsSimCardService;
import com.wanma.web.support.utils.ApiUtil;
import com.wanma.web.support.utils.HttpsUtil;
/**
 * 
 * @author lyh
 *
 */
@Controller
@RequestMapping("/admin/simCard/")
public class CmsSimCardController {
	/** 日志文件生成器 */
	private static Logger log = Logger
			.getLogger(ElectricPileListController.class);
	
	@Autowired
	private CmsSimCardService simCardService;
	
	/**
	 * 获取sim卡管理列表
	 * @param pager
	 * @param simCard
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/simCardList")
	public String getSimCardList(@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute TblSimCard simCard, Model model,HttpServletRequest request){
		try {
			// ------------|02：查询电桩业务处理|-----------
			// 获取登陆用户
			TblUser loginUser = SessionMgr.getWebUser(request);
			// 个体/纯商家只能查询所属电桩
			simCard.setElpiUserid(loginUser.getUserId().toString());
			simCard.setUserLevel(loginUser.getUserLevel().toString());
			// 电桩总数
			long total = simCardService
					.getSimCardListCount(simCard);
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			// 设置查询参数
			simCard.setPager(pager);
			List<Map<String, Object>> simCardList = (List<Map<String, Object>>) simCardService
					.getSimCardList(simCard);
			pager.setTotal(total);
			model.addAttribute("simCardList", simCardList);
			model.addAttribute("pager", pager);
			model.addAttribute("simCard", simCard);
			model.addAttribute("loginUser", loginUser);
		} catch (Exception e) {
			log.error("获取电桩状态失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(1000, "error.msg.invalid.parameter")
					.toString();
		}
		return "backstage/simCard/simCard-list";
		
	}
	@ResponseBody
	@RequestMapping("/simUpdateUi")
	public String simUpdateUi(TblSimCard simCard,HttpServletRequest request) throws Exception{
		DwzAjaxResult dwzResult;
		String elpiElectricpilecode[]= request.getParameter("elpiElectricpilecode").split(":");
		MessageManager m = new MessageManager();
		String apiBaseUrl = m.getSystemProperties("apiRoot");
		try {
			TblUser loginUser = SessionMgr.getWebUser(request);
			for(int i=0;i<elpiElectricpilecode.length;i++){
				simCard.setElpiElectricpilecode(elpiElectricpilecode[i]);
				simCard.setOperator(loginUser.getUserAccount().toString());
				//获取sim卡编码的数量
				int sum = simCardService.findSimCardCount(elpiElectricpilecode[i]);
				//获取sim卡运营商的数量
				int sum1= simCardService.findSimOperatorCount(elpiElectricpilecode[i]);
				//增加sim卡编码的记录
				if(sum == 0){
					simCardService.insertSimCard(simCard);
				}else{
					simCardService.updateSimCard(simCard);
				}
				//增加sim卡运营商的记录
				if(sum1 == 0){
					simCardService.insertSimOperator(simCard);
				}else{
					simCardService.updateSimCard(simCard);
				}
			}
		String sendStr = "";
		for(String code:elpiElectricpilecode){
			sendStr += code.trim() + ",";
		}
		if(sendStr.length() > 0){
			sendStr = sendStr.substring(0, sendStr.length() - 1);
			sendStr += ":10317";
			String token = ApiUtil.getToken();
			//调用接口定sim
		HttpsUtil.getResponseParam(apiBaseUrl + "/app/net/sendChargeSet.do?paramStrs=" + sendStr + "&t=" + token, "status");
		}
		dwzResult = new DwzAjaxResult("200", "sim卡信息更新成功！", "simCardList","closeCurrent","", "");
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			dwzResult = new DwzAjaxResult("300", "下发失败:系统错误", "", "", "");		
		}
		
		return new JsonObject(dwzResult).toString();
		
	}
}
