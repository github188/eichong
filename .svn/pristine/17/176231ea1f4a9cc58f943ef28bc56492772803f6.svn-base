package com.bluemobi.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Jimi
 * Date: 13-4-5
 * Time: 下午6:13
 */
public class DateUtils {

    private static final long BASE_TIME;

    static {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(2014, Calendar.JANUARY, 1, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        BASE_TIME = calendar.getTimeInMillis();
    }

    /**
     * 判断是否同一天
     *
     * @param sec1 秒
     * @param sec2 秒
     */
    public static boolean isTheSameDay(final int sec1, final int sec2) {
        return sec1 == sec2 || isTheSameDay(sec1 * TimeConstants.ONE_SECOND, sec2 * TimeConstants.ONE_SECOND);
    }

    /**
     * 判断是否同一天
     *
     * @param time1 毫秒
     * @param time2 毫秒
     */
    public static boolean isTheSameDay(final long time1, final long time2) {
        if (time1 == time2) {
            return true;
        }
        final int day1 = (int) ((time1 - BASE_TIME) / TimeConstants.ONE_DAY);
        final int day2 = (int) ((time2 - BASE_TIME) / TimeConstants.ONE_DAY);
        return day1 == day2;
    }

    public static boolean isTheSameDay(final Calendar date1, final Calendar date2) {
        return isTheSameDay(date1.getTimeInMillis(), date2.getTimeInMillis());
    }

    public static boolean isTheSameDay(final Date date1, final Date date2) {
        return isTheSameDay(date1.getTime(), date2.getTime());
    }

    public static boolean isTheSameDay(final Date date) {
        return isTheSameDay(date, new Date());
    }

    public static boolean isTheSameDay(final int sec) {
        return isTheSameDay(sec * TimeConstants.ONE_SECOND, System.currentTimeMillis());
    }

    public static boolean isTheSameWeek(final int time1, final int time2) {
        return time1 == time2 || isTheSameWeek(time1 * TimeConstants.ONE_SECOND, time2 * TimeConstants.ONE_SECOND);
    }

    public static boolean isTheSameWeek(final long time1, final long time2) {
        if (time1 == time2) {
            return true;
        }

        final int week1 = (int) ((time1 - BASE_TIME) / TimeConstants.ONE_WEEK);
        final int week2 = (int) ((time2 - BASE_TIME) / TimeConstants.ONE_WEEK);
        return week1 == week2;
    }

    public static boolean isTheSameWeek(final Calendar date1, final Calendar date2) {
        return isTheSameWeek(date1.getTimeInMillis(), date2.getTimeInMillis());
    }

    public static boolean isTheSameWeek(final Date date1, final Date date2) {
        return isTheSameWeek(date1.getTime(), date2.getTime());
    }

    public static boolean isThisWeek(final Date date) {
        return isTheSameWeek(date, new Date());
    }

    public static boolean isTheSameWeek(final Date date) {
        return isThisWeek(date);
    }

    public static boolean isTheSameDayAlt(final long time1, final long time2, final int hour, final int minute, final int second, final int milliseconds) {
        final long alt = hour * TimeConstants.ONE_HOUR + minute * TimeConstants.ONE_MINUTE + second * TimeConstants.ONE_SECOND + milliseconds;
        return isTheSameDay(time1 - alt, time2 - alt);
    }

    public static boolean isTheSameDayAlt(final long time1, final long time2, final int hour, final int minute) {
        final long alt = hour * TimeConstants.ONE_HOUR + minute * TimeConstants.ONE_MINUTE;
        return isTheSameDay(time1 - alt, time2 - alt);
    }

    public static boolean isTheSameDayAlt(final long time1, final long time2, final int hour) {
        final long alt = hour * TimeConstants.ONE_HOUR;
        return isTheSameDay(time1 - alt, time2 - alt);
    }

    public static boolean isTheSameDayAlt(final Date date1, final Date date2, final int hour, final int minute) {
        return isTheSameDayAlt(date1.getTime(), date2.getTime(), hour, minute);
    }

    public static boolean isTheSameDayAlt(final Date date1, final Date date2, final int hour, final int minute, final int second, final int milliseconds) {
        return isTheSameDayAlt(date1.getTime(), date2.getTime(), hour, minute, second, milliseconds);
    }

    public static boolean isTheSameDayAlt(final Calendar date1, final Calendar date2, final int hour, final int minute, final int second, final int milliseconds) {
        return isTheSameDayAlt(date1.getTimeInMillis(), date2.getTimeInMillis(), hour, minute, second, milliseconds);
    }

    public static boolean isTheSameDayAlt(final Date date1, final Date date2, final int hour) {
        return isTheSameDayAlt(date1.getTime(), date2.getTime(), hour);
    }

    public static boolean isTheSameWeekAlt(final long time1, final long time2, final int day, final int hour, final int minute, final int second, final int milliseconds) {
        final long alt = (day - 1) * TimeConstants.ONE_DAY + hour * TimeConstants.ONE_HOUR + minute * TimeConstants.ONE_MINUTE + second * TimeConstants.ONE_SECOND + milliseconds;
        return isTheSameWeek(time1 - alt, time2 - alt);
    }

    public static boolean isTheSameWeekAlt(final long time1, final long time2, final int day, final int hour, final int minute) {
        final long alt = (day - 1) * TimeConstants.ONE_DAY + hour * TimeConstants.ONE_HOUR + minute * TimeConstants.ONE_MINUTE;
        return isTheSameWeek(time1 - alt, time2 - alt);
    }

    public static boolean isTheSameWeekAlt(final long time1, final long time2, final int day, final int hour) {
        final long alt = (day - 1) * TimeConstants.ONE_DAY + hour * TimeConstants.ONE_HOUR;
        return isTheSameWeek(time1 - alt, time2 - alt);
    }

    public static boolean isTheSameWeekAlt(final Calendar date1, final Calendar date2, final int day, final int hour, final int minute) {
        return isTheSameWeekAlt(date1.getTimeInMillis(), date2.getTimeInMillis(), day, hour, minute);
    }

    public static boolean isTheSameWeekAlt(final Calendar date1, final Calendar date2, final int day, final int hour, final int minute, final int second, final int milliseconds) {
        return isTheSameWeekAlt(date1.getTimeInMillis(), date2.getTimeInMillis(), day, hour, minute, second, milliseconds);
    }

    public static boolean isTheSameWeekAlt(final Date date1, final Date date2, final int day, final int hour, final int minute, final int second, final int milliseconds) {
        return isTheSameWeekAlt(date1.getTime(), date2.getTime(), day, hour, minute, second, milliseconds);
    }

    public static boolean isTheSameWeekAlt(final Calendar date1, final Calendar date2, final int day, final int hour) {
        return isTheSameWeekAlt(date1.getTimeInMillis(), date2.getTimeInMillis(), day, hour);
    }

    /**
     * 获取两个日期间的差距天数
     *
     * @param time1 秒
     * @param time2 秒
     */
    public static int getDayDiff(final int time1, final int time2) {
        if (time1 == time2) {
            return 0;
        }
        return getDayDiff(time1 * TimeConstants.ONE_SECOND, time2 * TimeConstants.ONE_SECOND);
    }


    /**
     * 获取两个日期间的差距天数
     *
     * @param time1 毫秒
     * @param time2 毫秒
     */
    public static int getDayDiff(final long time1, final long time2) {
        if (time1 == time2) {
            return 0;
        }
        final int day1 = (int) ((time1 - BASE_TIME) / TimeConstants.ONE_DAY);
        final int day2 = (int) ((time2 - BASE_TIME) / TimeConstants.ONE_DAY);
        return Math.abs(day1 - day2);
    }

    public static int getDayDiff(final Date date1, final Date date2) {
        return getDayDiff(date1.getTime(), date2.getTime());
    }

    public static int getDayDiff(final Calendar date1, Calendar date2) {
        return getDayDiff(date1.getTimeInMillis(), date2.getTimeInMillis());
    }
}
