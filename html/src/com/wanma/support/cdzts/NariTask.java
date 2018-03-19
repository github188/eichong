package com.wanma.support.cdzts;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.wanma.service.NariChargeOrderPushService;
import com.wanma.support.common.MessageManager;
import com.wanma.support.common.RedisService;
import com.wanma.support.common.WanmaConstants;
/**
 * 南京南瑞-充电订单推送（间隔15分钟）
 * @author lyh
 *
 */
public class NariTask {
	private final static Logger log = LoggerFactory.getLogger(NariTask.class);
	private static final SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
    private RedisService redisService;
	@Autowired
	private NariChargeOrderPushService nariPushService;
	
	public void doNariStatWork() {
        log.info("...南京南瑞充电订单定时推送-begin...");
    	MessageManager manager = MessageManager.getMessageManager();
        int isPush = Integer.parseInt(manager.getSystemProperties("environment.is.formal"));
        if(isPush == 1){
        	runNariTask();
        }else{
        	 log.error("...当前是非正式环境,不允许推送...");
        }
        log.info("...南京南瑞充电订单定时推送-end...");
    }

	private void runNariTask() {
		try {
	        log.info("runNariTask is begin");
	        //本次查询充电订单的结束时间（当前时间）
	        Date now = new Date();
	        String endTime = fmt.format(now);
	        //获取redis服务中的缓存时间
	        String redisTime = redisService.strGet(WanmaConstants.NARI_ORDER_TIME);
	        //本次查询充电订单的开始时间
	        String startTime = null;
	        if(redisTime == null){	        	
	        	startTime = fmt.format(new Date(now.getTime() - 7* 24 * 60 * 60 * 1000));//7天
	        }else{
	        	Date date =fmt.parse(redisTime);
	        	//上次查询结束时间+1秒作为本次查询开始时间
	        	startTime = fmt.format(new Date(date.getTime() + 1000));
	        }
	        log.info("南京南瑞充电订单定时推送-beginTime:{}|endTime:{}", startTime, endTime);
	        nariPushService.nariChargeOrderPush(startTime,endTime);	
	        log.info("runNariTask is end");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
