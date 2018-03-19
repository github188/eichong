package com.wanma.support.common;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sgcc.constant.CommonConsts;
import com.sgcc.utils.DateUtil;
import com.wanma.service.TblChargingOrderService;
import com.wanma.service.TblReconciliationService;

public class CecTask {
	private final static Logger log = LoggerFactory.getLogger(CecTask.class);
	@Autowired
    private RedisService redisService;
	@Autowired
	private TblReconciliationService reconciliationService;
	@Autowired
	private TblChargingOrderService chargeOrderService;
	
	 public  Date CecBegin() {
	        Date beginTime = beginTimeHandle();
	        return beginTime;
	    } 
	 public  Date CecEnd() {
	        Date endTime = endTimeHandle();
	        return endTime;
	    }
	 
	
	 public void doStatWork() {
		  log.info("CecQuartzTask is begin");
			MessageManager manager = MessageManager.getMessageManager();
	        int isPush = Integer.parseInt(manager.getSystemProperties("environment.is.formal"));
	        if(isPush == 1){
	        	runCecTask();
	        }else{
	        	 log.info("...............当前是非正式环境,不允许推送............");
	        }
	        log.info("CecQuartzTask is end");
	    }
	 public void runCecTask() {
		  try {
	        log.info("runCecTask is begin");
	        Date lastTime = new Date();
            lastTime = DateUtil.addDateDays(lastTime, -1L);
            Date beginTime = DateUtil.getDailyStartTime(lastTime);
            Date endTime = DateUtil.getDailyEndTime(lastTime);
	        log.info("runCecTask beginTime:{}|endTime:{}", beginTime, endTime);
	        List<Map<String,Object>> cpynumber = chargeOrderService.selectCpynumber();
	        for (Map<String, Object> map : cpynumber) {
	            reconciliationService.PushChargeOrder(map.get("cpyNumber").toString(), beginTime, endTime);
			}
		     
			} catch (Exception e) {
				e.printStackTrace();
			}
	    
	 }
	 private  Date beginTimeHandle() {
	        String latestSyncTime = getLatestSyncTime();
	        log.info("latestSyncTime is :", latestSyncTime);
	        if (null != latestSyncTime) {
	            Date lastTime = new Date();
	            lastTime = DateUtil.addDateDays(lastTime, -1L);
	            return DateUtil.getDailyStartTime(lastTime);
	        }
	        return null;
	    }
	 
	 private  Date endTimeHandle() {
	        Date lastTime = new Date();
	        lastTime = DateUtil.addDateDays(lastTime, -1L);
	        return DateUtil.getDailyEndTime(lastTime);
	    }
	 
	 private  String getLatestSyncTime() {
	        return redisService.strGet(CommonConsts.SGCC_SYNC_TIME);
	    }
	  
	 

}
