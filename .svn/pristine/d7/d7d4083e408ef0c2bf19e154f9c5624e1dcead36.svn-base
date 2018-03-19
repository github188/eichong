package com.echong.utils;

import java.util.Date;

/**
 * Created by zangyaoyi on 2016/12/30.
 */
public class DateUtils {
    public static int getUnixTime() {
        long time = System.currentTimeMillis() / 1000L;
        return (int) time;
    }

    public static Date unixTime2Date(int unixTime) {
        //  String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format();
        return new Date(unixTime * 1000);
    }

    public static int date2unixTime(Date date) {
        long epoch = date.getTime() / 1000;
        return (int) epoch;
    }

}
