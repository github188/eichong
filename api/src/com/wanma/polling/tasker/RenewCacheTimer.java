package com.wanma.polling.tasker;


import java.util.List;
import java.util.TimerTask;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bluemobi.product.common.CommonConsts;
import com.bluemobi.product.common.MessageManager;
import com.bluemobi.product.utils.SpringContextHolder;
import com.wanma.app.service.ElectricPileListService;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblElectricpile;
import com.wanma.model.TblGateservice;
import com.wanma.net.ApiGateConnectManager;
import com.wanma.service.GateService;

/**
 *  缓存更新定时器
 *  @Description 更新  WanmaConstants 工具类 Map缓存(ELECTRICPILE_TO_GATE_INFO/GATE_TO_CHANNEL_INFO)
  * @author bruce cheng(http://blog.csdn.net/brucehome)
  * @createTime 2015-7-9 下午03:31:05
  * @updator： 
  * @updateTime：   
  * @version：V1.0
  * 其他可选例子：
 */
public class RenewCacheTimer extends TimerTask {

	private static Log log = LogFactory.getLog(RenewCacheTimer.class);
	private static String cacheTime;
	
	static{
		
		MessageManager messageManager = MessageManager.getMessageManager();
		cacheTime=messageManager.getSystemProperties(CommonConsts.RENEW_CACHE_TIMER);
	}
	
	@Override
	public void run() {
		 
		changeElectricGateCache();
		changeGateChannelCache();
	}

	/**
	 *更新  电桩编号-Gate服务器Id对应信息
	 */
	@SuppressWarnings("unused")
	private void changeElectricGateCache(){
		
		log.info("定时器(RenewCacheTimer)开始执行扫描电桩Gate服务器信息任务......每("+cacheTime+")分执行一次");
		
		try {
			ElectricPileListService electricPileListService = (ElectricPileListService) SpringContextHolder
			.getSpringContext().getBean(ElectricPileListService.class);
			
			//加载电桩状态为离线/上线的电桩
			List<TblElectricpile> eletricpileList=electricPileListService.getElectricPileGateList();
			for (TblElectricpile tblElectricpile : eletricpileList) {
				Integer gateId=(Integer)WanmaConstants.ELECTRICPILE_TO_GATE_INFO.get(tblElectricpile.getElpiElectricpilecode());
				if(gateId!=null && gateId>0 &&  tblElectricpile.getGateId()!=gateId){//有变动进行修改操作
					WanmaConstants.ELECTRICPILE_TO_GATE_INFO.replace(tblElectricpile.getElpiElectricpilecode(), tblElectricpile.getGateId());
				}else if(gateId == null){//添加操作
					WanmaConstants.ELECTRICPILE_TO_GATE_INFO.put(tblElectricpile.getElpiElectricpilecode(), tblElectricpile.getGateId());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error("定时器(RenewCacheTimer)扫描电桩Gate服务器信息异常：", e);
		}
		 
		log.info("定时器(RenewCacheTimer)扫描电桩Gate服务器信息结束。");
	}
	
	/**
	 * 更新 Gate服务器Id-Gate服务器对象对应信息
	 */
    private void changeGateChannelCache(){
    	
		log.info("定时器(RenewCacheTimer)开始执行扫描Gate服务器Channel信息任务......每("+cacheTime+")分执行一次");
		
    	try {
    		GateService gateService = (GateService) SpringContextHolder.getSpringContext().getBean(GateService.class);
        	List<TblGateservice> gateList=gateService.getGateList1();
        	
        	ApiGateConnectManager.connectAllGate(gateList);
        	
        	
    	} catch (Exception e) {
    		// TODO: handle exception
    		log.error("定时器(RenewCacheTimer)扫描Gate服务器Channel信息异常：", e);
    	}
		log.info("定时器(RenewCacheTimer)扫描Gate服务器Channel信息结束。");
	}
}
