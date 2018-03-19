package com.echong.service.Scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

/**
 * Created by zangyaoyi on 2016/12/29.
 */
@Service
public class StartAddDataListener implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(StartAddDataListener.class);


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        LOGGER.debug("onApplicationEvent is begin ");
      /*  try {
            if (event.getApplicationContext().getParent() == null)//root application context 没有parent，他就是老大.
            {
                ScheduledExecutorServiceImpl sc = new ScheduledExecutorServiceImpl();
                sc.scheduledExecutor();
            }
        } catch (Exception e) {
            LOGGER.debug("onApplicationEvent is error ;event:"+event);
        }*/

    }
}
