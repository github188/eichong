package com.wanma.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.common.ApiUtil;
import com.base.common.BaseFail;
import com.base.common.BaseResult;
import com.base.common.BaseSuccess;
import com.base.common.GlobalSystem;
import com.base.common.HttpsUtil;
import com.base.common.SessionMgr;
import com.pub.controller.BaseController;
import com.pub.model.Pager;
import com.pub.model.TblUser;
import com.wanma.model.TblRateinformation;
import com.wanma.service.CmsCommitLogService;
import com.wanma.service.ElectricPileService;
import com.wanma.service.impl.CmsRateInfoServiceImpl;
/**
 * 运营管理-配置管理-费率
 * 
 * @author 
 *
 */
@Controller
@RequestMapping(value ="/admin/rateinfo")
public class CmsRateInfoController extends BaseController {
	// 日志输出对象
	private static Logger log = Logger.getLogger(CmsRateInfoController.class);
	
	@Autowired
	CmsRateInfoServiceImpl cmsRateInfoService;
	@Autowired
	ElectricPileService epService;
	@Autowired
	CmsCommitLogService commitLogService;
	
	@RequestMapping("rateinfoPage")
	public String rateInfoPage(){
		return "backstage/rateinfo/rateinfoList";
	}
	
	/**
	 * 根据登录用户获取对应费率列表
	 * @param pager
	 * @param tblRateInfo
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("getRateInfoList")
	@ResponseBody
	public String getRateInfoListByuId(@ModelAttribute("pager") Pager pager,
			@ModelAttribute TblRateinformation tblRateInfo, HttpServletRequest request,Model model){
		BaseResult baseResult=new BaseFail(5001);
		TblUser user = SessionMgr.getWebUser(request);
		tblRateInfo.setUserId(user.getUserId().toString());
		tblRateInfo.setUserLevel(user.getUserLevel());
		Integer total = cmsRateInfoService.getRateInfoNumByUserId(tblRateInfo);
		if(total<=pager.getOffset()){
			pager.setPageNum(1L);
		}
		//根据登录session中的用户id去获取费率列表
		tblRateInfo.setPager(pager);
		List<TblRateinformation> list = cmsRateInfoService.getRateInfoListByUserId(tblRateInfo);
		model.addAttribute("rateInfoList", list);			
		pager.setTotal(total.longValue());			
		baseResult = new BaseResult(list, pager);
		return baseResult.toString();
	}
	
	
	
	
	/**
	 * 跳转到修改界面
	 * @param id
	 * @return
	 */
	@RequestMapping("rateinfoDetail")
	@ResponseBody
	public String rateinfoDetail(int id,Model model){
		TblRateinformation map =  cmsRateInfoService.getRateInfoById(id);
		return new BaseResult(map).toString(); 
	}
	

	
	@RequestMapping("rateinfoModify")
	@ResponseBody
	public String rateinfoModify(TblRateinformation tblRateInfo,HttpServletRequest request,Model model){
		BaseResult baseResult = new BaseSuccess();
		try{
			TblUser user = SessionMgr.getWebUser(request);
			tblRateInfo.setUserId(user.getUserId().toString());
			tblRateInfo.setUpdateUserId(user.getUserId().toString());
			cmsRateInfoService.updateRateInfo(tblRateInfo);
			List<String> list = epService.getEpCodesByRateId(tblRateInfo.getPkRateinformation());
			//将桩体编号拼成要发送的格式
			String sendStr = "";
			for(String code : list){
				sendStr += code + ",";
			}
			if(sendStr.length() > 0){
				sendStr = sendStr.substring(0, sendStr.length() - 1);
				sendStr += ":" + tblRateInfo.getPkRateinformation();
				String apiBaseUrl = GlobalSystem.getConfig("apiRoot");
				String token = ApiUtil.getToken();
				//调用接口更新费率
				HttpsUtil.getResponseParam(apiBaseUrl + "/app/net/sendRate.do?paramStrs=" + sendStr + "&t=" + token, "status");
				commitLogService.insert("费率更新命令下发，主键：["
						+ tblRateInfo.getPkRateinformation() + "]");
			}
		}catch(Exception e){
			log.error(this.getClass() + ".rateinfoModify() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		return baseResult.toString();
	}
	
	@RequestMapping("rateinfoSave")
	@ResponseBody
	public String rateinfoSave(@ModelAttribute TblRateinformation tblRateInfo,HttpServletRequest request,Model model){
		TblUser user = SessionMgr.getWebUser(request);

		//告警金额使用默认值10元
		tblRateInfo.setRainWarnmoney(new BigDecimal(10));
		//最小欲动结金额默认10元
		if(tblRateInfo.getRainMinfreezingmoney()==null){
			tblRateInfo.setRainMinfreezingmoney(new BigDecimal(10));
		}
		
		BaseResult baseResult = new BaseSuccess();
		try{
			tblRateInfo.setUserId(user.getUserId().toString());
			tblRateInfo.setUpdateUserId(user.getUserId().toString());
			cmsRateInfoService.insertRateInfo(tblRateInfo);
		}catch(Exception e){
			log.error(this.getClass() + ".rateinfoSave() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		return baseResult.toString();
	}
	
	@RequestMapping("rateinfoRemove")
	@ResponseBody
	public String rateinfoRemove(String ids){
		BaseResult baseResult = new BaseSuccess();
		try{
			String[] idsn = ids.split(",");
			String errorCode=checkIds(idsn);
			if(StringUtils.isNotBlank(errorCode)){
				return new BaseFail("费率已经绑定桩体:"+errorCode).toString();
			}
			for(String id : idsn){
				cmsRateInfoService.delRateInfo(Integer.parseInt(id));
			}
		}catch(Exception e){
			log.error(this.getClass() + ".rateinfoRemove() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		return baseResult.toString();
	}
	
	private String checkIds(String[] ids){
		String errorCode="";
		for(String id : ids){
			List<TblRateinformation>  list = null;
			list = cmsRateInfoService.getRateAndElectricSend(Integer.parseInt(id));
			if(list.size() > 0)
				errorCode+=id+",";
		}
		return StringUtils.removeEnd(errorCode, ",");
		
	}
	
}
