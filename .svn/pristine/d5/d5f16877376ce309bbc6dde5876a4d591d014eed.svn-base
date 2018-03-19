package com.echong.service.Scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by zangyaoyi on 2016/12/29.
 */
public class ScheduledExecutorServiceImpl {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledExecutorServiceImpl.class);


    public static void main(String[] args) {
        scheduledExecutor();
    }

    public static void scheduledExecutor() {
        LOGGER.debug("scheduledExecutor is begin");
        ScheduledExecutorService schedule = Executors.newScheduledThreadPool(5);
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(" begin to do something at:" + sdf.format(new Date()));
        schedule.scheduleWithFixedDelay(new Job(), 1, 20, TimeUnit.SECONDS);
    }
}

