package com.wanma.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * 线程的一些工具类
 * Created by xyc on 2015/6/22.
 */
public class ThreadUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger("ItemServiceImpl");

    public static void safeSleep(long time, String msg) {
        if (msg == null) {
            msg = "";
        }
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            LOGGER.error("Thread | " + Thread.currentThread().getName() + "|sleep error ...|" + msg, e);
        }
    }

    public static void safeSleep(long time, TimeUnit unit, String msg) {
        safeSleep(unit.toMillis(time), msg);
    }

    public static void safeJoin(Thread thread, String msg) {
        if (msg == null) {
            msg = "";
        }
        try {
            thread.join();
        } catch (InterruptedException e) {
            LOGGER.error("Thread | " + Thread.currentThread().getName() + "|join|" + thread.getName() + "|error ...|" + msg, e);
        }
    }
}
