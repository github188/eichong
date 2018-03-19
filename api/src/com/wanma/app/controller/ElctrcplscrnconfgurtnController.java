package com.wanma.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.exceptions.AppRequestErrorException;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.wanma.common.JudgeNullUtils;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblConfigcontent;
import com.wanma.model.TblElctrcplscrnconfgurtn;
import com.wanma.service.CmsConfigcontentService;


/**
 * 电桩配置控制层
  * @Description:
  * @author bruce cheng(http://blog.csdn.net/brucehome)
  * @createTime：2015-3-11 下午04:25:53 
  * @updator： 
  * @updateTime：   
  * @version：V1.0
 */
@Controller
@RequestMapping("/app/elctrcplscrnconfgurtn")
public class ElctrcplscrnconfgurtnController {
    private static Log log = LogFactory.getLog(ElctrcplscrnconfgurtnController.class);
	
   // @Autowired
   // private ElctrcplscrnconfgurtnService elctrcplscrnconfgurtnService;
    @Autowired
    private CmsConfigcontentService msConfigcontentService;
    
	/**
     * 获取电桩类型
     */
    @RequestMapping("/getScreenTypeList")
	@ResponseBody
	public String  getScreenTypeList(HttpServletRequest request) throws AppRequestErrorException {
    	List<TblElctrcplscrnconfgurtn> screenTypeList=new ArrayList();
    	try {
    		//桩体用途
    		TblConfigcontent tblConfigcontent=new TblConfigcontent();
    		tblConfigcontent.setCocoConfigparameterid(2);
    		tblConfigcontent.setCocoConfigpstatus(WanmaConstants.CONFIG_PARAMETER_EFFECTIVE);
    		List<TblConfigcontent> elctrcUseList=msConfigcontentService.findContentList(tblConfigcontent);
    		screenTypeList=convertData(elctrcUseList);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取电桩类型失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(1000,"error.msg.invalid.parameter")
					.toString();
		}
		// 返回处理成功信息
		return new AccessSuccessResult(screenTypeList).toString();
	}

    /**
     *  电桩配置数据转换
     * @param configContentList
     * @return
     */
    @SuppressWarnings("unused")
	private List<TblElctrcplscrnconfgurtn> convertData(List<TblConfigcontent> configContentList){
    	
    	List<TblElctrcplscrnconfgurtn> electriConfig=new ArrayList<TblElctrcplscrnconfgurtn>();
    	for (TblConfigcontent tblConfigcontent : configContentList) {
    		TblElctrcplscrnconfgurtn tblElctrcplscrnconfgurtn =new TblElctrcplscrnconfgurtn();
    		tblElctrcplscrnconfgurtn.setPkElctrcplscrnconfgurtn(JudgeNullUtils.nvlInteget(tblConfigcontent.getPkConfigcontent()));
    		tblElctrcplscrnconfgurtn.setEpscName(JudgeNullUtils.nvlStr(tblConfigcontent.getCocoContent()));
    		electriConfig.add(tblElctrcplscrnconfgurtn);
		}
    	return electriConfig;
    }
    /**
     *  电桩配置数据转换
     * @param configContentList
     * @return
     */
    @SuppressWarnings("unused")
	private List<TblElctrcplscrnconfgurtn> convertRadiusData(List<TblConfigcontent> configContentList){
    	
    	List<TblElctrcplscrnconfgurtn> electriConfig=new ArrayList<TblElctrcplscrnconfgurtn>();
    	for (TblConfigcontent tblConfigcontent : configContentList) {
		            
    		TblElctrcplscrnconfgurtn tblElctrcplscrnconfgurtn =new TblElctrcplscrnconfgurtn();
   	        String distanceString=JudgeNullUtils.nvlStr((tblConfigcontent.getCocoContent()));
             distanceString=distanceString.substring(0, distanceString.lastIndexOf("km"));
    		
            tblElctrcplscrnconfgurtn.setPkElctrcplscrnconfgurtn(Integer.parseInt(distanceString)*1000);
    		tblElctrcplscrnconfgurtn.setEpscName(JudgeNullUtils.nvlStr(tblConfigcontent.getCocoContent()));
    		electriConfig.add(tblElctrcplscrnconfgurtn);
		}
    	return electriConfig;
    }
    /**
     * 获取电桩状态
     */
    @RequestMapping("/getScreenStateList")
	@ResponseBody
	public String  getScreenStateList(HttpServletRequest request) throws AppRequestErrorException {
       	List<TblElctrcplscrnconfgurtn> screenState=new ArrayList();
    	try {
    		 
    		TblElctrcplscrnconfgurtn tblElctrcplscrnconfgurtn =new TblElctrcplscrnconfgurtn();
    		tblElctrcplscrnconfgurtn.setPkElctrcplscrnconfgurtn(0); 
    		tblElctrcplscrnconfgurtn.setEpscName("全部");
    		screenState.add(tblElctrcplscrnconfgurtn);
    		
    		TblElctrcplscrnconfgurtn tblElctrcplscrnconfgurtn1 =new TblElctrcplscrnconfgurtn();
    		tblElctrcplscrnconfgurtn1.setPkElctrcplscrnconfgurtn(10);
    		tblElctrcplscrnconfgurtn1.setEpscName("离线");
    		screenState.add(tblElctrcplscrnconfgurtn1);
    		
    		TblElctrcplscrnconfgurtn tblElctrcplscrnconfgurtn2 =new TblElctrcplscrnconfgurtn();
    		tblElctrcplscrnconfgurtn2.setPkElctrcplscrnconfgurtn(15);
    		tblElctrcplscrnconfgurtn2.setEpscName("上线");
    		screenState.add(tblElctrcplscrnconfgurtn2);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取电桩状态失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(2002,"error.msg.invalid.parameter")
					.toString();
		}
		// 返回处理成功信息
		return new AccessSuccessResult(screenState).toString();
	}
    /**
     * 获取电桩搜索半径
     */
    @RequestMapping("/getScreenRadiusList")
	@ResponseBody
	public String  getScreenRadiusList(HttpServletRequest request) throws AppRequestErrorException {
       	List<TblElctrcplscrnconfgurtn> screenRadius=new ArrayList();
    	try {
    		TblConfigcontent tblConfigcontent=new TblConfigcontent();
    		tblConfigcontent.setCocoConfigparameterid(7);
    		tblConfigcontent.setCocoConfigpstatus(WanmaConstants.CONFIG_PARAMETER_EFFECTIVE);
    		List<TblConfigcontent> elctrcUseList=msConfigcontentService.findContentList(tblConfigcontent);
    		screenRadius=convertRadiusData(elctrcUseList);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取电桩状态失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(2004,"error.msg.invalid.parameter")
					.toString();
		}
		// 返回处理成功信息
		return new AccessSuccessResult(screenRadius).toString();
	}
    /**
     * 获取电桩是否支持预约
     */
    @RequestMapping("/getScreenSubscribeList")
	@ResponseBody
	public String  getScreenSubscribeList(HttpServletRequest request) throws AppRequestErrorException {
       	List<TblElctrcplscrnconfgurtn> screenSubscribe=new ArrayList();
    	try {
    		TblElctrcplscrnconfgurtn tblElctrcplscrnconfgurtn =new TblElctrcplscrnconfgurtn();
    		tblElctrcplscrnconfgurtn.setPkElctrcplscrnconfgurtn(0);
    		tblElctrcplscrnconfgurtn.setEpscName("全部");
    		screenSubscribe.add(tblElctrcplscrnconfgurtn);
    		
    		TblElctrcplscrnconfgurtn tblElctrcplscrnconfgurtn1 =new TblElctrcplscrnconfgurtn();
    		tblElctrcplscrnconfgurtn1.setPkElctrcplscrnconfgurtn(10);
    		tblElctrcplscrnconfgurtn1.setEpscName("支持预约");
    		screenSubscribe.add(tblElctrcplscrnconfgurtn1);
    		
    		TblElctrcplscrnconfgurtn tblElctrcplscrnconfgurtn2 =new TblElctrcplscrnconfgurtn();
    		tblElctrcplscrnconfgurtn2.setPkElctrcplscrnconfgurtn(15);
    		tblElctrcplscrnconfgurtn2.setEpscName("无卡消费");
    		screenSubscribe.add(tblElctrcplscrnconfgurtn2);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取电桩状态失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(2004,"error.msg.invalid.parameter")
					.toString();
		}
		// 返回处理成功信息
		return new AccessSuccessResult(screenSubscribe).toString();
	}
}