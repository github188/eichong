package com.epcentre.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.epcentre.util.StringUtil;


/**
 * 
 * @author haojian
 * Mar 27, 2013 4:26:57 PM
 */
public class TimeUtil {
	
	/**
	 * 获取1970年到现在的秒数
	 * @author haojian
	 * Apr 2, 2013 2:17:28 PM
	 * @return
	 */
	public static int currentTimeSecond(){
		int time = (int)(System.currentTimeMillis()/1000);
		return time;
	}
	
	
	/**
	 * 获取1970年1月1日到现在的天数
	 * @author haojian
	 * Aug 13, 2013 11:27:27 AM
	 * @return
	 */
	public static int dayOf1970(){
		
		int currentTimeSecond = TimeUtil.currentTimeSecond() + 8*3600 ;//格林威治时间比北京时间晚8个小时
		int dayOf1970 = currentTimeSecond/(24*3600);//1970年1月1日到现在的天数
		
		return dayOf1970;
	}
	
	
	/**
	 * 获取1970年1月1日到现在的星期数(第一个星期只有4天 (周4,周5,周6,周7))
	 * @author haojian
	 * Aug 13, 2013 11:26:47 AM
	 * @return 
	 */
	public static int weekOf1970(){
		
		int dayOf1970 = TimeUtil.dayOf1970();//1970年1月1日到现在的天数
		int weekOf1970 = (dayOf1970 + 3)/7; //1970年1月1日到现在的星期数 
		
		return weekOf1970;
		
	}
	

	/**多少毫秒后开始刷新每日任务*/
	public static long getRefreshEveryDayTaskDelayTime(){
		long time = getTomorrowTime() - System.currentTimeMillis();
        return time;
	}
	
	/**
	 * 根据日期的格式（2013.6.3|12:00:00）获取 时间的秒数
	 * @param dateStr
	 * @param formatStr 日期的字符串的格式: "yyyy-MM-dd HH:mm:ss"
	 * @return 
	 * @throws ParseException 
	 */
	public static int getSecondByFormatDate(String dateStr, String formatStr){
		
		if( "0".equals(dateStr) || "".equals(dateStr) ){
			return 0;
		}
		
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return (int)(date.getTime() / 1000);
	}
	
	/**
	 * 多少毫秒后到 第一个 该小时，分，秒
	 * @author haojian
	 * May 6, 2012 3:01:51 PM
	 * @param hour 小时
	 * @param minute 分
	 * @param second 秒
	 * @param milliSecond 毫秒
	 * @return
	 */
	public static long getNextTimeMillis(int hour,int minute,int second,int milliSecond){
		if(hour<0||hour>24){
			throw new RuntimeException("hour is error!");
		}
		Calendar cal = Calendar.getInstance();
		
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        cal.set(Calendar.MILLISECOND, milliSecond);
        long time = cal.getTimeInMillis();
        long currentTime = System.currentTimeMillis();
        if(currentTime<=time){
        	return time - currentTime;
        }else{
        	return time - currentTime + 24*3600*1000;
        }
		
	}
	
	/**
	 * 多少毫秒后到下一个 固定星期几的 该小时，分，秒
	 * @author haojian
	 * Jan 9, 2014 10:45:48 AM
	 * @param week
	 * @param hour
	 * @param minute
	 * @param second
	 * @param milliSecond
	 * @return
	 */
	public static long getNextWeekTimeMillis(int week,int hour,int minute,int second,int milliSecond){
		if(hour<0||hour>24){
			throw new RuntimeException("hour is error!");
		}
		Calendar cal = Calendar.getInstance();
		
		cal.set(Calendar.DAY_OF_WEEK, week+1);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        cal.set(Calendar.MILLISECOND, milliSecond);
        long time = cal.getTimeInMillis();
        long currentTime = System.currentTimeMillis();
        if(currentTime<=time){
        	return time - currentTime;
        }else{
        	return time - currentTime + 7*24*3600*1000;
        }
		
	}
	
	
	/**
	 * 返回根据传入的 时分秒 得出的毫秒数
	 * @author haojian
	 * Oct 8, 2012 10:21:22 AM
	 * @param hh 小时
	 * @param mm 分钟
	 * @param ss 秒
	 * @param ms 毫秒
	 * @return
	 */
	public static long getTodayTime(int hh,int mm,int ss,int ms){
		Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hh);
        cal.set(Calendar.MINUTE, mm);
        cal.set(Calendar.SECOND, ss);
        cal.set(Calendar.MILLISECOND, ms);
        
        long time = cal.getTimeInMillis();
        return time;
	}
	
	/**
	 * 返回根据传入的 年月日 时分秒 得出的毫秒数
	 * @author haojian
	 * Jul 8, 2013 3:01:16 PM
	 * @param year
	 * @param month
	 * @param day
	 * @param hh
	 * @param mm
	 * @param ss
	 * @param ms
	 * @return
	 */
	public static long getTime(int year,int month,int day,int hh,int mm,int ss,int ms){
		Calendar cal = Calendar.getInstance();
		
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, day);
		
        cal.set(Calendar.HOUR_OF_DAY, hh);
        cal.set(Calendar.MINUTE, mm);
        cal.set(Calendar.SECOND, ss);
        cal.set(Calendar.MILLISECOND, ms);
        
        long time = cal.getTimeInMillis();
        return time;
	}
	
	/**返回明天凌晨0点的毫秒数*/
	public static long getTomorrowTime(){
		long time = TimeUtil.getTodayTime(24, 0, 0, 0);
        return time;
	}
	
	/**返回今天凌晨0点的毫秒数*/
	public static long getTodayZeroHourTime(){
		long time = TimeUtil.getTodayTime(0, 0, 0, 0);
        return time;
	}
	
	/**返回昨天凌晨0点的毫秒数*/
	public static long getYesterdayTime(){
		long time = TimeUtil.getTodayTime(-24, 0, 0, 0);
        return time;
	}
	
	
	/**
	 * 校验时间是不是今天
	 * @author haojian
	 * Jan 15, 2014 9:56:58 AM
	 * @param time
	 * @return
	 */
	public static boolean checkTimeIsToday(long time){
		
		long beginTime = getTodayZeroHourTime();
		long endTime = beginTime+24*3600*1000;
		
		if(time>beginTime&&time<=endTime){
			return true;
		}
		return false;
	}
	
	
	/**校验毫秒数跟当前时间是否同一天,每天以hour为界限,登陆时候清除每日数据用*/
	public static boolean checkIsSameDayByHour(long time,int hour){
		
		long beginTime = TimeUtil.getTodayZeroHourTime()+hour*3600*1000;
		long endTime = beginTime+24*3600*1000;
		
		Calendar cal = Calendar.getInstance();
		int currentHour = cal.get(Calendar.HOUR_OF_DAY);
		if(currentHour>=0&&currentHour< 5 ){
			beginTime = beginTime - 24*60*60*1000;
			endTime = endTime - 24*60*60*1000;
		}
		
		if(time>beginTime&&time<=endTime){
			return true;
		}
		return false;
	}

	
	/**
	 * 通过字符串获取当前的毫秒数 
	 * @author haojian
	 * Jul 13, 2012 11:13:14 AM
	 * @param hhmmss 字符串格式 "12:30:00"
	 * @return
	 */
	public static long getTodayTimeMillisByHHMMSS(String hhmmss){
		
		int[] ii = StringUtil.stringToIntArray(hhmmss,":");
		
		int hour = ii[0];
		int minute = ii[1];
		int second = ii[2];
		
		if(hour<0||hour>24){
			throw new RuntimeException("hour is error!");
		}
		
		Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        long time = cal.getTimeInMillis();
        
        return time;
        
	}
	
	/**
	 * 根据毫秒数和格式获取需要的字符串时间格式
	 * @author haojian
	 * Aug 22, 2013 9:47:29 AM
	 * @param time   毫秒数
	 * @param pattern  格式，如："yyyy-MM-dd HH:mm:ss"
	 * @return
	 */
	public static String getFormatTime(long time,String pattern){
		
		SimpleDateFormat sdf =  new SimpleDateFormat(pattern);
		String str = sdf.format(new Date(time));
		return str;
		
	}

	/**
	 * 判断两个 1970年1月1日到现在的 秒数 是否是同一天
	 * @author haojian
	 * Jul 8, 2013 2:33:53 PM
	 * @param time1 (单位：秒)
	 * @param time2 (单位：秒)
	 * @return
	 */
	public static boolean checkIsSameDay(int time1, int time2){
		
		Date date1 = new Date(time1*1000L);
		Date date2 = new Date(time2*1000L);
		long t1 = TimeUtil.getTime(date1.getYear(), date1.getMonth(), date1.getDay(), 0, 0, 0, 0);
		long t2 = TimeUtil.getTime(date2.getYear(), date2.getMonth(), date2.getDay(), 0, 0, 0, 0);
		
		if(t1==t2){
			return true;
		}else{
			return false;
		}
		
	}
	
	/**
	 * 获取当前是星期几
	 * 星期天返回 0
	 * @author haojian
	 * Oct 17, 2013 3:04:13 PM
	 * @return
	 */
	public static int getWeek(){
		Calendar cal = Calendar.getInstance();
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		
		int week = dayOfWeek - 1;
		
		return week;
	}
	
	
	/**
	 * 将当前这个星期的 “星期几|hh:mm:ss” 格式的字符串转换成毫秒数
	 * @author haojian
	 * Jan 7, 2014 2:14:47 PM
	 * @param weekStr  格式如："2|19:30:01"
	 */
	public static long getTimeByWeekStr(String weekStr){
		String[] ss = weekStr.split("\\|");
		String[] hms = ss[1].split(":");
		int week = Integer.valueOf(ss[0]);
		//一周中的第一天是星期0，第二天星期1,第三天星期2
		week = week + 1;
		int hour = Integer.valueOf(hms[0]);
		int minute = Integer.valueOf(hms[1]);
		int second = Integer.valueOf(hms[2]);
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK,week);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        
        long time = cal.getTimeInMillis();
		return time;
	}
	
	
	public static void main(String [] args){
		
		//System.out.println(TimeUtil.getTodayTime(-48,0,0,0));
		
		/*int begin = TimeUtil.getSecondByFormatDate("1970.1.1|00:00:00", BaseConstant.DATE_FORMATE_STR);
		int end = TimeUtil.getSecondByFormatDate("2013.11.24|00:00:00", BaseConstant.DATE_FORMATE_STR);
		System.out.println(begin);
		System.out.println(end);
		System.out.println(new Date(-315648000*1000L));*/
		
	/*	long time1  = TimeUtil.getTimeByWeekStr(GameParam.crossServerWar_joinTime_begin);
		long time2  = TimeUtil.getTimeByWeekStr(GameParam.crossServerWar_joinTime_end);
		long time3  = TimeUtil.getTimeByWeekStr(GameParam.crossServerWar_startTime_begin);
		long time4  = TimeUtil.getTimeByWeekStr(GameParam.crossServerWar_startTime_end);
		System.out.println(TimeUtil.getFormatTime(time1, "yyyy-MM-dd HH:mm:ss"));
		System.out.println(TimeUtil.getFormatTime(time2, "yyyy-MM-dd HH:mm:ss"));
		System.out.println(TimeUtil.getFormatTime(time3, "yyyy-MM-dd HH:mm:ss"));*/
		System.out.println(TimeUtil.getSecondByFormatDate("2030-4-5 12:00:00", "yyyy-MM-dd HH:mm:ss"));
		
	}

	
}
