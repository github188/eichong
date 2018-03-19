/** 
 * FileName AppCommonController.java
 * 
 * Version 1.0
 *
 * Create by yangwr 2014/8/9
 * 
 * Copyright 2000-2001 Bluemobi. All Rights Reserved.
 */
package com.bluemobi.product.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FileName AppCommonController.java
 * 
 * Version 1.0
 * 
 * Create by yangwr 2014/8/9
 * 
 * APP公共处理控制器
 */
public class DateUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

	// 变量声明 //
	private static Calendar calObject;

	public static final String DATE_FORMAT_FULL = "yyyy/MM/dd HH:mm:ss";
	public static final String DATE_FORMAT_SHORT = "yyyy/MM/dd";
	public static final String DATE_FORMAT_YEAR = "yyyy";
	public final static String DEFAULT_FORMAT = "yyyyMMdd";
	public static final String DATE_FORMAT_CN = "yyyy年MM月dd日HH时mm分ss秒";
	public static final String DATE_FORMAT_CN_SHORT = "yyyy年MM月dd日";

	/**
	 * 转换日期
	 * 
	 * @param dteDate
	 * @param strDateFormat
	 * @return
	 */
	public static String toDateFormat(Date dteDate, String strDateFormat) {

		// 变量声明 //
		String strRet = null;

		try {

			if (dteDate == null) {

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
	 * 年计算年的加法
	 * 
	 * 
	 * @param dteDate
	 *            对象日期
	 * @param lngNumber
	 *            加的年数（负数：过去；正数：未来）
	 * @return Date 计算后日期
	 */
	public static Date getAddYear(Date dteDate, long lngNumber) {

		// 变量声明 //
		Date dteRet = null; // 返回日期保存用

		// /1.取得计算对象日期计算结果
		try {
			// //1.1.生成Canlendar对象
			calObject = Calendar.getInstance();
			calObject.setTime(dteDate);

			// //1.2.日期年的加法
			calObject.add(Calendar.YEAR, (int) lngNumber);

			// //1.3.计算后日期的取得
			dteRet = calObject.getTime();

			// /2.发生异常时处理
		} catch (Exception e) {
			// //2.1. 返回NULL
			dteRet = null;
		}

		// /3.返回计算后的日期
		return dteRet;

	}

	/**
	 * 计算月
	 * 
	 * @param dteDate
	 *            计算对象日期
	 * @param lngNumber
	 *            加的月数（负数：过去；正数：未来）
	 * @return Date 计算后日期
	 */
	public static Date getAddMonth(Date dteDate, long lngNumber) {

		// 变量声明 //
		Date dteRet = null; // 返回日期保存用

		// /1.取得计算对象日期计算结果
		try {
			// //1.1.生成Canlendar对象
			calObject = Calendar.getInstance();
			calObject.setTime(dteDate);

			// //1.2.日期月的加法
			calObject.add(Calendar.MONTH, (int) lngNumber);

			// //1.3.计算后日期的取得
			dteRet = calObject.getTime();

			// /2.发生异常时处理
		} catch (Exception e) {
			// //2.1. 返回NULL
			dteRet = null;
		}

		// /3.返回计算后的日期
		return dteRet;

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
	public static Date getAddDay(Date dteDate, long lngNumber) {

		// 变量声明 //
		Date dteRet = null; // 返回日期保存用

		// /1.取得计算对象日期计算结果
		try {
			// //1.1.生成Canlendar对象
			calObject = Calendar.getInstance();
			calObject.setTime(dteDate);

			// //1.2.日期日的加法
			calObject.add(Calendar.DATE, (int) lngNumber);

			// //1.3.计算后日期的取得
			dteRet = calObject.getTime();

			// /2.发生异常时处理
		} catch (Exception e) {
			// //2.1. 返回NULL
			dteRet = null;
		}

		// /3.返回计算后的日期
		return dteRet;

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

		// 变量声明 //
		Date dteRet = null; // 返回日期保存用

		// /1.取得计算对象日期计算结果
		try {
			// //1.1.生成Canlendar对象
			calObject = Calendar.getInstance();
			calObject.setTime(dteDate);

			// //1.2.日期日的加法
			calObject.add(Calendar.MINUTE, (int) lngNumber);

			// //1.3.计算后日期的取得
			dteRet = calObject.getTime();

			// /2.发生异常时处理
		} catch (Exception e) {
			// //2.1. 返回NULL
			dteRet = null;
		}

		// /3.返回计算后的日期
		return dteRet;

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

		// 变量声明 //
		Date dteRet = null; // 返回日期保存用

		// /1.取得计算对象日期计算结果
		try {
			// //1.1.生成Canlendar对象
			calObject = Calendar.getInstance();
			calObject.setTime(dteDate);

			// //1.2.日期日的加法
			calObject.add(Calendar.SECOND, (int) lngNumber);

			// //1.3.计算后日期的取得
			dteRet = calObject.getTime();

			// /2.发生异常时处理
		} catch (Exception e) {
			// //2.1. 返回NULL
			dteRet = null;
		}

		// /3.返回计算后的日期
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

		// 变量声明 //
		int comparRet = 0; // 返回比较结果保存用

		if (dteDate1 == null || dteDate2 == null) {
			return -1;
		}

		comparRet = dteDate1.compareTo(dteDate2);

		// 返回比较的结果
		return comparRet;

	}

	/**
	 * 判断两个日期距离
	 * 
	 * @param dteDate1
	 *            计算对象日期1
	 * @param dteDate2
	 *             计算对象日期2
	 * @return int 比较的结果
	 */
	public static int compareDateDistance(Date dteDate1, Date dteDate2) {

		// 变量声明 //
		int comparRet = 0; // 返回比较结果保存用

		if (dteDate1 == null || dteDate2 == null) {
			return 0;
		}

		comparRet = dteDate1.compareTo(dteDate2);

		// 返回比较的结果
		return comparRet;

	}

	/**
	 * 根据给定的格式化参数，将字符串转换为日期
	 * 
	 * @param dateString
	 * @param dateFormat
	 * @return java.util.Date
	 */
	public static java.util.Date parse(String dateString, String dateFormat) {
		if (logger.isDebugEnabled()) {
			logger.debug(">>>parse(String dateString, String dateFormat)");
		}
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

		if (logger.isDebugEnabled()) {
			logger.debug("<<<parse(return java.util.Date)");
		}
		return date;
	}

	/**
	 * 
	 * 默认将字符串转换为日期，格式(yyyy-MM-dd)
	 * 
	 * @param dateString
	 * @return
	 */
	public static java.util.Date parse(String dateString) {
		return parse(dateString, DEFAULT_FORMAT);
	}

	/**
	 * 根据给定的格式化参数，将日期转换为字符串
	 * 
	 * @param date
	 * @param dateFormat
	 * @return String
	 */
	public static String toString(java.util.Date date, String dateFormat) {
		if (logger.isDebugEnabled()) {
			logger.debug(">>> toString(java.util.Date date, String dateFormat)");
		}
		if ("".equals(date) || date == null) {
			return "bug: date is null";
		}
		DateFormat sdf = new SimpleDateFormat(dateFormat);
		String str = sdf.format(date);

		if (logger.isDebugEnabled()) {
			logger.debug("<<< toString return String");
		}
		return str;
	}

	/**
	 * 默认将日期转换为字符串，格式(yyyy-MM-dd)
	 * 
	 * @param date
	 * @return String
	 */
	public static String toString(java.util.Date date) {
		return toString(date, DEFAULT_FORMAT);
	}

	/**
	 * 将日期转换为长整型?
	 * 
	 * @param date
	 * @return long
	 */
	public static long toLong(java.util.Date date) {
		if (logger.isDebugEnabled()) {
			logger.debug(">>>toLong(java.util.Date date)");
		}
		if (date == null) {
			return 0;
		}
		long d = date.getTime();

		if (logger.isDebugEnabled()) {
			logger.debug("<<<toLong return long");
		}
		return d;
	}

	/**
	 * 将长整型转换为日期对象
	 * 
	 * @param time
	 * @return date
	 */
	public static java.util.Date toDate(long time) {
		if (logger.isDebugEnabled()) {
			logger.debug(">>>toDate (long time)");
		}
		if ("".equals(time)) {
			return new Date();
		}
		Date date = new Date(time);

		if (logger.isDebugEnabled()) {
			logger.debug("<<<toDate return date");
		}
		return date;
	}

	/**
	 * 获得系统当前时间
	 * 
	 * @return java.util.Date
	 */
    public static String currentStringDate() {
        if (logger.isDebugEnabled()) {
            logger.debug(">>>currentDate()");
        }
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式


        String now = dateFormat.format( date ); 
        if (logger.isDebugEnabled()) {
            logger.debug("<<<currentDate() return date");
        }
        return now;
    }

	/**
	 * 获得系统当前时间(按用户自己格式)
	 * 
	 * @return java.util.Date
	 */
	public static String currentYourDate(String formate) {
		if (logger.isDebugEnabled()) {
			logger.debug(">>>currentDate()");
		}
		Date date = new Date();

		if (logger.isDebugEnabled()) {
			logger.debug("<<<currentDate() return date");
		}
		return toString(date, formate);
	}

	/**
	 * 获得系统当前时间
	 * 
	 * @return java.util.Date
	 */
	public static java.util.Date currentDate() {
		if (logger.isDebugEnabled()) {
			logger.debug(">>>currentDate()");
		}
		Date date = new Date();

		if (logger.isDebugEnabled()) {
			logger.debug("<<<currentDate() return date");
		}
		return date;
	}

	/**
	 * 根据日历的规则，为给定的日历字段添加或减去指定的时间�?
	 * 
	 * @param field
	 *            指定的日历字段
	 * @param date
	 *            需要操作的日期对象
	 * @param value
	 *            更改的时间值
	 * @return java.util.Date
	 */
	public static Date add(int field, Date date, int value) {
		if (logger.isDebugEnabled()) {
			logger.debug(">>>add(int field,Date date,int value)");
		}
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(field, value);
		Date newDate = ca.getTime();

		if (logger.isDebugEnabled()) {
			logger.debug("<<<add() return date");
		}
		return newDate;
	}

	/**
	 * 返回给定日历字段的值
	 * 
	 * @param field
	 *            指定的日历字段
	 * @param date
	 *            给定的日期对象
	 * @return java.util.Date
	 */
	public static int get(int field, Date date) {
		if (logger.isDebugEnabled()) {
			logger.debug(">>>get(int field, Date date");
		}
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		int value = ca.get(field);

		if (logger.isDebugEnabled()) {
			logger.debug("<<<get() return date");
		}
		return value;
	}

	/**
	 * 返回前N个月的日期值
	 * 
	 * @param month
	 * @return
	 */
	public static Date getLastMonth(String month) {
		Calendar ca = Calendar.getInstance();
		int m = 0;
		try {
			m = Integer.parseInt(month);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		ca.add(Calendar.MONTH, -m);
		return ca.getTime();
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


	public static void main(String args[]) {
		// System.out.println(DateUtil.toDateFormat(new Date(),
		// DATE_FORMAT_FULL));
		// System.out.println(DateUtil.getCurrentYear());
		System.out.println(DateUtil.getAddMinute(new Date(), -10));
		System.out.println(DateUtil.currentYourDate("yyyyMMddHHmmssSS"));
	}

}
