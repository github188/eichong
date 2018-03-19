package com.echong.service.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zangyaoyi on 2016/12/29.
 */
public class Job implements Runnable {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public void run() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("do something  at:" + sdf.format(new Date()));
    }
}