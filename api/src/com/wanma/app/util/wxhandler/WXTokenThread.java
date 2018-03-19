package com.wanma.app.util.wxhandler;

import org.apache.log4j.Logger;

public class WXTokenThread implements Runnable{
	private static Logger log = Logger.getLogger(WXTokenThread.class);
	
	public static String accessToken = null;
	
	public void run() {    
        while (true) {    
            try {    
                accessToken = AccessTokenRequestHandler.getAccessToken(); //WeixinUtil.getAccessToken(appid, appsecret);    
                if (null != accessToken) {    
                    //log.info("获取access_token成功，有效时长{}秒 token:{}", accessToken.getExpiresIn(), accessToken.getToken());    
                    // 休眠7000秒    
                    Thread.sleep(7000000);    
                } else {    
                    // 如果access_token为null，60秒后再获取    
                    Thread.sleep(20 * 1000);    
                }    
            } catch (InterruptedException e) {    
                try {    
                    Thread.sleep(60 * 1000);    
                } catch (InterruptedException e1) {    
                    log.error("{}", e1);    
                }    
                log.error("{}", e);    
            }    
        }    
    }    
}
