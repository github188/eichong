package com.wanma.support.cdzts;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.wanma.service.PowerStationPushService;
import com.wanma.support.common.MessageManager;
import com.wanma.support.common.RedisService;

public class AlipayTask {
	private final static Logger log = LoggerFactory.getLogger(AlipayTask.class);
	private SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
    private RedisService redisService;
	@Autowired
	private PowerStationPushService pspService;
	public void doAlipayStatWork() {
		log.info("Alipay QuartzTask is begin");
    	MessageManager manager = MessageManager.getMessageManager();
        int isPush = Integer.parseInt(manager.getSystemProperties("environment.is.formal"));
        if(isPush == 1){
        	runAlipayTask();
        }else{
        	 log.info("...............当前是非正式环境,不允许推送............");
        }
        log.info("Alipay QuartzTask is end");
	}

	private void runAlipayTask() {
		try {
	        log.info("runAlipayTask is begin");
	        //上次的推送时间
	        String lastQueryTime = null;
	        //获取redis服务中的缓存时间-上次推送的时间
	        String redisTime = redisService.strGet(WanmaConstants.ALIPAY_ORDER_TIME);
	        //当前时间
	        Date now = new Date();
	         if(redisTime == null){	        	
	        	 lastQueryTime = fmt.format(new Date(now.getTime() - 7* 24 * 60 * 60 * 1000));//7天
	        }else{
	        	Date date =fmt.parse(redisTime);
	        	//上次推送时间+1秒作为本次的lastQueryTime
	        	lastQueryTime = fmt.format(new Date(date.getTime() + 1000));
	        }
	        log.info("runNariTask lastQueryTime:{}", lastQueryTime);
	        pspService.alipayStationPush(lastQueryTime);	
	        log.info("runAlipayTask is end");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
}
