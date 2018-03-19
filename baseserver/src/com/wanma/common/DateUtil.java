package com.wanma.common;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.util.StringUtils;
import org.apache.log4j.Logger;

/**
 * 日期处理工具类
 *
 * @date Mar 11, 2012
 * @modified by
 * @modified date
 * @since JDK1.6
 * @see com.wanma.app.util.util.DateUtil
 */

public class DateUtil {

	private static Logger logger = Logger.getLogger(DateUtil.class);
	private static String defaultDatePattern = null;
	private static Calendar cale = Calendar.getInstance();
	public static final String TS_FORMAT = DateUtil.getDatePattern()
			+ " HH:mm:ss.S";
	/** 日期格式yyyy-MM字符串常量 */
	public static final String MONTH_FORMAT = "yyyy-MM";
	/** 日期格式yyyy-MM-dd字符串常量 */
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	/** 日期格式HH:mm:ss字符串常量 */
	public static final String HOUR_FORMAT = "HH:mm:ss";
	/** 日期格式yyyy-MM-dd HH:mm:ss字符串常量 */
	public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	/** 某天开始时分秒字符串常量 00:00:00 */
	public static final String DAY_BEGIN_STRING_HHMMSS = " 00:00:00";
	/** 某天结束时分秒字符串常量 23:59:59 */
	public static final String DAY_END_STRING_HHMMSS = " 23:59:59";
	private static SimpleDateFormat sdf_date_format = new SimpleDateFormat(
			DATE_FORMAT);
	private static SimpleDateFormat sdf_hour_format = new SimpleDateFormat(
			HOUR_FORMAT);
	private static SimpleDateFormat sdf_datetime_format = new SimpleDateFormat(
			DATETIME_FORMAT);
	
	public static final String DATE_FORMAT_FULL = "yyyy/MM/dd HH:mm:ss";
	public static final String DATE_FORMAT_YEAR = "yyyy";
	// ~ Methods
	// ================================================================

	public DateUtil() {
	}

	/**
	 * 获得服务器当前日期及时间，以格式为：yyyy-MM-dd HH:mm:ss的日期字符串形式返回
	 *
	 * @date Mar 11, 2012
	 * @return
	 */
	public static String getDateTime() {
		try {
			return sdf_datetime_format.format(cale.getTime());
		} catch (Exception e) {
			logger.debug("DateUtil.getDateTime():" + e.getMessage());
			return "";
		}
	}

	/**
	 * 获得服务器当前日期，以格式为：yyyy-MM-dd的日期字符串形式返回
	 *
	 * @date Mar 11, 2012
	 * @return
	 */
	public static String getDate() {
		try {
			return sdf_date_format.format(Calendar.getInstance().getTime());
		} catch (Exception e) {
			logger.debug("DateUtil.getDate():" + e.getMessage());
			return "";
		}
	}

	/**
	 * 获得服务器当前时间，以格式为：HH:mm:ss的日期字符串形式返回
	 *
	 * @date Mar 11, 2012
	 * @return
	 */
	public static String getTime() {
		String temp = " ";
		try {
			temp += sdf_hour_format.format(cale.getTime());
			return temp;
		} catch (Exception e) {
			logger.debug("DateUtil.getTime():" + e.getMessage());
			return "";
		}
	}


	/**
	 * 获得服务器当前日期的年份
	 *
	 * @date Mar 11, 2012
	 * @return
	 */
	public static String getYear() {
		try {
			return String.valueOf(cale.get(Calendar.YEAR));
		} catch (Exception e) {
			logger.debug("DateUtil.getYear():" + e.getMessage());
			return "";
		}
	}

	/**
	 * 获得服务器当前日期的月份
	 *
	 * @date Mar 11, 2012
	 * @return
	 */
	public static String getMonth() {
		try {
			java.text.DecimalFormat df = new java.text.DecimalFormat();
			df.applyPattern("00;00");
			return df.format((cale.get(Calendar.MONTH) + 1));
		} catch (Exception e) {
			logger.debug("DateUtil.getMonth():" + e.getMessage());
			return "";
		}
	}

	/**
	 * 获得服务器在当前月中天数
	 *
	 * @date Mar 11, 2012
	 * @return
	 */
	public static String getDay() {
		try {
			return String.valueOf(cale.get(Calendar.DAY_OF_MONTH));
		} catch (Exception e) {
			logger.debug("DateUtil.getDay():" + e.getMessage());
			return "";
		}
	}

	/**
	 * 比较两个日期相差的天数
	 *
	 * @date Mar 11, 2012
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getMargin(String date1, String date2) {
		int margin;
		try {
			ParsePosition pos = new ParsePosition(0);
			ParsePosition pos1 = new ParsePosition(0);
			Date dt1 = sdf_date_format.parse(date1, pos);
			Date dt2 = sdf_date_format.parse(date2, pos1);
			long l = dt1.getTime() - dt2.getTime();
			margin = (int) (l / (24 * 60 * 60 * 1000));
			return margin;
		} catch (Exception e) {
			logger.debug("DateUtil.getMargin():" + e.toString());
			return 0;
		}
	}

	/**
	 * 比较两个日期相差的月数
	 *
	 * @date Mar 11, 2012
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getMonthMargin(String date1, String date2) {
		int margin;
		try {
			margin = (Integer.parseInt(date2.substring(0, 4)) - Integer
					.parseInt(date1.substring(0, 4))) * 12;
			margin += (Integer.parseInt(date2.substring(4, 7).replaceAll("-0",
					"-")) - Integer.parseInt(date1.substring(4, 7).replaceAll(
					"-0", "-")));
			return margin;
		} catch (Exception e) {
			logger.debug("DateUtil.getMargin():" + e.toString());
			return 0;
		}
	}

	/**
	 * 返回日期加X天后的日期
	 *
	 * @date Mar 11, 2012
	 * @param date
	 * @param i
	 * @return
	 */
	public static String addDay(String date, int i) {
		try {
			GregorianCalendar gCal = new GregorianCalendar(
					Integer.parseInt(date.substring(0, 4)),
					Integer.parseInt(date.substring(5, 7)) - 1,
					Integer.parseInt(date.substring(8, 10)));
			gCal.add(GregorianCalendar.DATE, i);
			return sdf_date_format.format(gCal.getTime());
		} catch (Exception e) {
			logger.debug("DateUtil.addDay():" + e.toString());
			return getDate();
		}
	}

	/**
	 * 返回日期加X月后的日期
	 *
	 * @date Mar 11, 2012
	 * @param date
	 * @param i
	 * @return
	 */
	public static String addMonth(String date, int i) {
		try {
			GregorianCalendar gCal = new GregorianCalendar(
					Integer.parseInt(date.substring(0, 4)),
					Integer.parseInt(date.substring(5, 7)) - 1,
					Integer.parseInt(date.substring(8, 10)));
			gCal.add(GregorianCalendar.MONTH, i);
			return sdf_date_format.format(gCal.getTime());
		} catch (Exception e) {
			logger.debug("DateUtil.addMonth():" + e.toString());
			return getDate();
		}
	}

	/**
	 * 返回日期加X年后的日期
	 *
	 * @date Mar 11, 2012
	 * @param date
	 * @param i
	 * @return
	 */
	public static String addYear(String date, int i) {
		try {
			GregorianCalendar gCal = new GregorianCalendar(
					Integer.parseInt(date.substring(0, 4)),
					Integer.parseInt(date.substring(5, 7)) - 1,
					Integer.parseInt(date.substring(8, 10)));
			gCal.add(GregorianCalendar.YEAR, i);
			return sdf_date_format.format(gCal.getTime());
		} catch (Exception e) {
			logger.debug("DateUtil.addYear():" + e.toString());
			return "";
		}
	}

	/**
	 * 返回默认的日期格式
	 *
	 * @date Mar 11, 2012
	 * @return
	 */
	public static synchronized String getDatePattern() {
		defaultDatePattern = "yyyy-MM-dd";
		return defaultDatePattern;
	}

	/**
	 * 将日期类转换成指定格式的字符串形式
	 *
	 * @date Mar 11, 2012
	 * @param aMask
	 * @param aDate
	 * @return
	 */
	public static final String getDateTime(String aMask, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate == null) {
			logger.error("aDate is null!");
		} else {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}
		return (returnValue);
	}

	/**
	 * 将指定的日期转换成默认格式的字符串形式
	 *
	 * @date Mar 11, 2012
	 * @param aDate
	 * @return
	 */
	public static final String convertDateToString(Date aDate) {
		return getDateTime(getDatePattern(), aDate);
	}

	/**
	 * 将日期字符串按指定格式转换成日期类型
	 *
	 * @date Mar 11, 2012
	 * @param aMask
	 *            指定的日期格式，如:yyyy-MM-dd
	 * @param strDate
	 *            待转换的日期字符串
	 * @return
	 * @throws java.text.ParseException
	 */
	public static final Date convertStringToDate(String aMask, String strDate)
			throws ParseException {
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(aMask);

		if (logger.isDebugEnabled()) {
			logger.debug("converting '" + strDate + "' to date with mask '"
					+ aMask + "'");
		}
		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			logger.error("ParseException: " + pe);
			throw pe;
		}
		return (date);
	}

	/**
	 * 返回一个JAVA简单类型的日期字符串
	 *
	 * @date Mar 11, 2012
	 * @return
	 */
	public static String getSimpleDateFormat() {
		SimpleDateFormat formatter = new SimpleDateFormat();
		String NDateTime = formatter.format(new Date());
		return NDateTime;
	}

	/**
	 * 取得指定时间的日戳
	 *
	 * @return
	 */
	public static String getTimestamp(Date date) {
		cale.setTime(date);
		return cale.getTimeInMillis() + "";
	}

	/**
	 * 时间戳转换 日期格式
	 *
	 * @param mask
	 *            时间戳
	 * @param time
	 * @return
	 */
	public static String converTimestampToDateStr(String mask, long time) {
		Timestamp timestamp = new Timestamp(time);
		if (mask.equals(DateUtil.DATE_FORMAT)) {
			return sdf_date_format.format(timestamp);
		}

		if (mask.equals(DateUtil.DATETIME_FORMAT)) {
			return sdf_datetime_format.format(timestamp);
		}

		if (mask.equals(DateUtil.HOUR_FORMAT)) {
			return sdf_hour_format.format(timestamp);
		}

		return null;
	}

	/**
	 * 计算两个时间点的相隔长度 格式：2015-05-06 08:01:01
	 * @param str1
	 * @param str2
	 * @return 最大单位为小时，最小单位为秒
	 */
	public static String getDistanceTime(String str1, String str2) {  
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        Date one;  
        Date two;  
        long day = 0;  
        long hour = 0;  
        long min = 0;  
        long sec = 0;  
        try {  
            one = df.parse(str1);  
            two = df.parse(str2);  
            long time1 = one.getTime();  
            long time2 = two.getTime();  
            long diff ;  
            if(time1<time2) {  
                diff = time2 - time1;  
            } else {  
                diff = time1 - time2;  
            }  
            day = diff / (24 * 60 * 60 * 1000);  
            hour = (diff / (60 * 60 * 1000) - day * 24);  
            if(day >= 1){
            	hour += day * 24;
            }
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);  
            sec = (diff/1000-day*24*60*60-hour*60*60-min*60);  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        return hour + "时" + min + "分" + sec + "秒";  
    } 
	
	/**
	 * 将给定的分钟数转为时分字符串
	 * @param min
	 * @return
	 */
	public static String min2TimeString(int minute){
		long day = 0;  
        long hour = 0;  
        long min = 0; 
		day = minute / (24 * 60);  
        hour = (minute / 60 - day * 24);  
        if(day >= 1){
        	hour += day * 24;
        }
        min = minute - day * 24 * 60 - hour * 60;  
		
        return hour + "时" + min + "分";  
	}
	
	/**
	 * 获得系统当前时间(按用户自己格式)
	 * 
	 * @return java.util.Date
	 */
	public static String currentYourDate(String formate) {
		Date date = new Date();

		return toString(date, formate);
	}
	
	/**
	 * 根据给定的格式化参数，将日期转换为字符串
	 * 
	 * @param date
	 * @param dateFormat
	 * @return String
	 */
	public static String toString(java.util.Date date, String dateFormat) {
		if ("".equals(date) || date == null) {
			return "bug: date is null";
		}
		DateFormat sdf = new SimpleDateFormat(dateFormat);
		String str = sdf.format(date);

		return str;
	}
	
	/**
	 * 将日期转换为长整型?
	 * 
	 * @param date
	 * @return long
	 */
	public static long toLong(java.util.Date date) {
		if (date == null) {
			return 0;
		}
		long d = date.getTime();

		return d;
	}
	
	/**
	 * 计算秒
	 * 
	 * @param dteDate
	 *            计算对象日期
	 * @param lngNumber
	 *            加的秒数（负数：过去；正数：未来））
	 * @return Date 计算后日期
	 */
	public static Date getAddSecond(Date dteDate, long lngNumber) {
		Date dteRet = null; // 返回日期保存用
		try {
			// //1.1.生成Canlendar对象
			//calObject = Calendar.getInstance();
			cale.setTime(dteDate);
			// //1.2.日期日的加法
			cale.add(Calendar.SECOND, (int) lngNumber);
			// //1.3.计算后日期的取得
			dteRet = cale.getTime();
		} catch (Exception e) {
			dteRet = null;
		}

		return dteRet;

	}
	
	/**
	 * 比较两个日期
	 * 
	 * @param dteDate1
	 *            计算对象日期1
	 * @param dteDate2
	 *             计算对象日期2
	 * @return int 比较的结果
	 */
	public static int compareDate(Date dteDate1, Date dteDate2) {
		int comparRet = 0; // 返回比较结果保存用
		if (dteDate1 == null || dteDate2 == null) {
			return -1;
		}
		comparRet = dteDate1.compareTo(dteDate2);

		return comparRet;

	}
	
	/**
	 * 转换日期
	 * 
	 * @param dteDate
	 * @param strDateFormat
	 * @return
	 */
	public static String toDateFormat(Date dteDate, String strDateFormat) {
		String strRet = null;
		try {
			if (StringUtils.isEmpty(dteDate)) {
				strRet = "";
			} else {
				strRet = new SimpleDateFormat(strDateFormat).format(dteDate);
			}
		} catch (Exception e) {
			strRet = "";
		}

		return strRet;

	}
	
	/**
	 * 计算日
	 * 
	 * @param dteDate
	 *            计算对象日期
	 * @param lngNumber
	 *            加的天数（负数：过去；正数：未来））
	 * @return Date 计算后日期
	 */
	public static Date getAddMinute(Date dteDate, long lngNumber) {

		Date dteRet = null; // 返回日期保存用

		// /1.取得计算对象日期计算结果
		try {
			// //1.1.生成Canlendar对象
			//calObject = Calendar.getInstance();
			cale.setTime(dteDate);

			// //1.2.日期日的加法
			cale.add(Calendar.MINUTE, (int) lngNumber);

			// //1.3.计算后日期的取得
			dteRet = cale.getTime();

		} catch (Exception e) {
			dteRet = null;
		}

		return dteRet;
	}
	
	/**
	 * 转换默认格式日期
	 * 
	 * @param dteDate
	 * @param strDateFormat
	 * @return
	 */
	public static String toDateDefaultFormat(Date dteDate) {

		return toDateFormat(dteDate, DATE_FORMAT_FULL);

	}
	
	/**
	 * 根据给定的格式化参数，将字符串转换为日期
	 * 
	 * @param dateString
	 * @param dateFormat
	 * @return java.util.Date
	 */
	public static java.util.Date parse(String dateString, String dateFormat) {
		if (dateString == null || "".equals(dateString.trim())  ) {
			return null;
		}
		DateFormat sdf = new SimpleDateFormat(dateFormat);
		Date date = null;
		try {
			date = sdf.parse(dateString);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return date;
	}
	
	/** 
	* @Title: getSeconds 
	* @Description: TODO(返回毫秒数) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public static String getSeconds(){
	    Calendar ca = Calendar.getInstance();
	    return ca.getTimeInMillis()+"";
	}
	
	/**
	 * 取得当前年份
	 * 
	 * @param dteDate
	 * @param strDateFormat
	 * @return
	 */
	public static String getCurrentYear() {
		Date dteDate = new Date();
		return toDateFormat(dteDate, DATE_FORMAT_YEAR);
	}
	
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
	
	public static void main(String[] args) throws ParseException{
		//DateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd");
		System.out.println(sdf.parse("2015-05-22 00:00:00"));
		//Date date = new Date("2015-05-22 00:00:00");
		System.out.println(DateUtil.toDateFormat(sdf.parse("2015-05-22 00:00:00"), "yyyy.MM.dd"));
	}
}
