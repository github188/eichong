package com.wanma.ims.util;

import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
	private static Logger log = Logger.getLogger(DateUtil.class);
	/** yyyy-MM-dd HH:mm:ss */
	public static final String TYPE_COM_YMDHMS = "yyyy-MM-dd HH:mm:ss";
	/** yyyy-MM-dd HH:mm */
	public static final String TYPE_COM_YMDHM = "yyyy-MM-dd HH:mm";
	/** MM-dd HH:mm:ss */
	public static final String TYPE_COM_MDHMS = "MM-dd HH:mm:ss";
	/** MM-dd HH:mm */
	public static final String TYPE_COM_MDHM = "MM-dd HH:mm";
	/** yyyy-MM-dd */
	public static final String TYPE_COM_YMD = "yyyy-MM-dd";
	/** yyyy-MM */
	public static final String TYPE_COM_YM = "yyyy-MM";
	/** MM-dd */
	public static final String TYPE_COM_MD = "MM-dd";
	/** HH:mm:ss */
	public static final String TYPE_COM_HMS = "HH:mm:ss";
	/** HH:mm */
	public static final String TYPE_COM_HM = "HH:mm";

	/** yyyyMMddHHmmss */
	public static final String TYPE_COMBINE_YMDHMS = "yyyyMMddHHmmss";
	/** yyyyMMddHHmm */
	public static final String TYPE_COMBINE_YMDHM = "yyyyMMddHHmm";
	/** MMddHHmmss */
	public static final String TYPE_COMBINE_MDHMS = "MMddHHmmss";
	/** MMddHHmm */
	public static final String TYPE_COMBINE_MDHM = "MMddHHmm";
	/** yyyyMMdd */
	public static final String TYPE_COMBINE_YMD = "yyyyMMdd";
	/** yyyyMM */
	public static final String TYPE_COMBINE_YM = "yyyyMM";
	/** MMdd */
	public static final String TYPE_COMBINE_MD = "MMdd";

	/** yyyy年MM月dd日HH时mm分ss秒 */
	public static final String TYPE_CN_YMDHMS = "yyyy年MM月dd日HH时mm分ss秒";
	/** yyyy年MM月dd日HH时mm分 */
	public static final String TYPE_CN_YMDHM = "yyyy年MM月dd日HH时mm分";
	/** MM月dd日HH时mm分ss秒 */
	public static final String TYPE_CN_MDHMS = "MM月dd日HH时mm分ss秒";
	/** MM月dd日HH时mm分 */
	public static final String TYPE_CN_MDHM = "MM月dd日HH时mm分";
	/** yyyy年MM月dd日 */
	public static final String TYPE_CN_YMD = "yyyy年MM月dd日";
	/** yyyy年MM月 */
	public static final String TYPE_CN_YM = "yyyy年MM月";
	/** MM月dd日 */
	public static final String TYPE_CN_MD = "MM月dd日";

	/** yyyy/MM/dd HH:mm:ss */
	public static final String TYPE_EN_YMDHMS = "yyyy/MM/dd HH:mm:ss";
	/** yyyy/MM/dd HH:mm */
	public static final String TYPE_EN_YMDHM = "yyyy/MM/dd HH:mm";
	/** MM/dd HH:mm:ss */
	public static final String TYPE_EN_MDHMS = "MM/dd HH:mm:ss";
	/** MM/dd HH:mm */
	public static final String TYPE_EN_MDHM = "MM/dd HH:mm";
	/** yyyy/MM/dd */
	public static final String TYPE_EN_YMD = "yyyy/MM/dd";
	/** yyyy/MM */
	public static final String TYPE_EN_YM = "yyyy/MM";
	/** MM/dd */
	public static final String TYPE_EN_MD = "MM/dd";

	public static final SimpleDateFormat SDF_YMDHMS = new SimpleDateFormat(TYPE_COM_YMDHMS);
	public static final SimpleDateFormat SDF_YMD = new SimpleDateFormat(TYPE_COM_YMD);
	public static final SimpleDateFormat SDF_HMS = new SimpleDateFormat(TYPE_COM_HMS);
	public static final SimpleDateFormat SDF_COMBINE_YMDHMS = new SimpleDateFormat(TYPE_COMBINE_YMDHMS);

	/** 私有化构造器 */
	private DateUtil() {
	}

	/**
	 * 获取日期转换器
	 * 
	 * @return DateFormat
	 */
	public static DateFormat getDateFormat() {
		return getDateFormat(TYPE_COM_MDHMS);
	}

	/**
	 * 获取日期转换器
	 * 
	 * DateUtil.getDateFormat(DateUtil.TYPE_COM_YMDHMS);
	 * 
	 * @param fmt
	 * @return DateFormat
	 */
	public static DateFormat getDateFormat(String fmt) {
		return new SimpleDateFormat(fmt);
	}

	/**
	 * 与当前时间比较,1是未来，-1是过去,0是现在
	 * 
	 * DateUtil.compareNow(DateUtil.parse("2019-11-10 12:09:09"))
	 * 
	 * @param date1
	 * @return int
	 */
	public static int compareNow(Date date1) {
		Date date2 = new Date();
		int rnum = date1.compareTo(date2);
		return rnum;
	}

	/**
	 * 获取系统当前时间
	 * 
	 * @return String
	 */
	public static String getNow() {
		return getNow(TYPE_COM_YMDHMS);
	}

	/**
	 * 获取系统当前时间 DateUtil.getNow(DateUtil.TYPE_CN_YMD)
	 * 
	 * @return String
	 */
	public static String getNow(String fmt) {
		return format(new Date(), fmt);
	}

	/**
	 * 日期转换字符串，默认格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * DateUtil.parse("2019-11-10 12:09:09")
	 * 
	 * @param dateStr
	 * @return Date
	 * @throws ParseException
	 */
	public static Date parse(String dateStr) {
		return parse(dateStr, TYPE_COM_YMDHMS);
	}

	/**
	 * 日期转换字符串
	 * 
	 * DateUtil.parse("2019年11月10日",DateUtil.TYPE_CN_YMD)
	 * 
	 * @param dateStr
	 * @param fmt
	 * @return Date
	 */
	public static Date parse(String dateStr, String fmt) {
		Date d = null;
		if (dateStr != null) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(fmt);
				d = sdf.parse(dateStr);
			} catch (ParseException e) {
				log.error("解析日期格式" + fmt + "出错：" + e.getLocalizedMessage());
			}
		}
		return d;
	}

	/**
	 * 字符串转日期，默认格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * DateUtil.format(new Date())
	 * 
	 * @param date
	 * @return String
	 */
	public static String format(Date date) {
		return format(date, TYPE_COM_YMDHMS);
	}

	/**
	 * 字符串转日期
	 * 
	 * DateUtil.format(new Date(),DateUtil.TYPE_EN_YMDHMS)
	 * 
	 * @param date
	 * @param fmt
	 * @return String
	 */
	public static String format(Date date, String fmt) {
		SimpleDateFormat sdf = new SimpleDateFormat(fmt);
		return sdf.format(date);
	}

	/**
	 * 字符串转字符串,原字符串默认格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * DateUtil.formatByStr("2019-11-10 12:09:09", DateUtil.TYPE_CN_YMDHM)
	 * 
	 * @param dateStr
	 * @param fmt
	 * @return String
	 */
	public static String formatByStr(String dateStr, String fmt) {
		return formatByStr(dateStr, TYPE_COM_YMDHMS, fmt);
	}

	/**
	 * 字符串转字符串
	 * 
	 * DateUtil.formatByStr("2019-11-10 12:09:09",
	 * DateUtil.TYPE_COM_YMDHMS,DateUtil.TYPE_CN_YMDHM)
	 * 
	 * @param dateStr
	 * @param oldFmt
	 * @param newFmt
	 * @return String
	 */
	public static String formatByStr(String dateStr, String oldFmt,
			String newFmt) {
		return format(parse(dateStr, oldFmt), newFmt);
	}

	/**
	 * 返回日期加X天后的日期
	 * 
	 * DateUtil.addDay("2019-11-10 12:09:09", -21)
	 * 
	 * @param dateStr
	 * @param i
	 *            正数是后面日期，负数是前面日期
	 * @return String
	 */
	public static String addDay(String dateStr, int i) {
		return dateCalculate(dateStr, Calendar.DATE, i, TYPE_COM_YMDHMS,
				TYPE_COM_YMDHMS);
	}

	/**
	 * 返回日期加X天后的日期
	 * 
	 * DateUtil.addDay("2019-11-10 12:09:09", -21,DateUtil.TYPE_CN_YMDHMS)
	 * 
	 * @param dateStr
	 * @param newFmt
	 *            字符串需要被转换的格式
	 * @param i
	 *            正数是后面日期，负数是前面日期
	 * @return String
	 */
	public static String addDay(String dateStr, int i, String newFmt) {
		return dateCalculate(dateStr, Calendar.DATE, i, TYPE_COM_YMDHMS, newFmt);
	}

	/**
	 * 返回日期加X天后的日期
	 * 
	 * DateUtil.addDay("2019-11-10 12:09:09",
	 * -21,DateUtil.TYPE_COM_YMDHMS,DateUtil.TYPE_CN_YMD)
	 * 
	 * @param dateStr
	 * @param oldFmt
	 * @param newFmt
	 * @param i
	 *            正数是后面日期，负数是前面日期
	 * @return String
	 */
	public static String addDay(String dateStr, int i, String oldFmt,
			String newFmt) {
		return dateCalculate(dateStr, Calendar.DATE, i, oldFmt, newFmt);
	}

	/**
	 * 返回日期加X月后的日期
	 * 
	 * DateUtil.addMonth("2019-11-10 12:09:09", -36)
	 * 
	 * @param dateStr
	 * @param i
	 *            正数是后面月数，负数是前面月数
	 * @return String
	 */
	public static String addMonth(String dateStr, int i) {
		return dateCalculate(dateStr, Calendar.MONTH, i, TYPE_COM_YMDHMS,
				TYPE_COM_YMDHMS);
	}

	/**
	 * 返回日期加X月后的日期
	 * 
	 * DateUtil.addMonth("2019-11-10 12:09:09", -36,DateUtil.TYPE_CN_YMDHMS)
	 * 
	 * @param dateStr
	 * @param newFmt
	 * @param i
	 *            正数是后面月数，负数是前面月数
	 * @return String
	 */
	public static String addMonth(String dateStr, int i, String newFmt) {
		return dateCalculate(dateStr, Calendar.MONTH, i, TYPE_COM_YMDHMS,
				newFmt);
	}

	/**
	 * 返回日期加X月后的日期
	 * 
	 * DateUtil.addMonth("2019-11-10 12:09:09",
	 * -36,DateUtil.TYPE_COM_YMDHMS,DateUtil.TYPE_CN_YMDHMS)
	 * 
	 * @param dateStr
	 * @param oldFmt
	 * @param newFmt
	 * @param i
	 *            正数是后面月数，负数是前面月数
	 * @return String
	 */
	public static String addMonth(String dateStr, int i, String oldFmt,
			String newFmt) {
		return dateCalculate(dateStr, Calendar.MONTH, i, oldFmt, newFmt);
	}

	/**
	 * 返回日期加X年后的日期
	 * 
	 * DateUtil.addYear("2019-11-10 12:09:09", -36)
	 * 
	 * @param dateStr
	 * @param i
	 *            正数是后面年数，负数是前面年数
	 * @return String
	 */
	public static String addYear(String dateStr, int i) {
		return dateCalculate(dateStr, Calendar.YEAR, i, TYPE_COM_YMDHMS,
				TYPE_COM_YMDHMS);
	}

	/**
	 * 返回日期加X年后的日期
	 * 
	 * DateUtil.addYear("2019-11-10 12:09:09", -36,DateUtil.TYPE_CN_YMDHMS)
	 * 
	 * @param dateStr
	 * @param newFmt
	 * @param i
	 *            正数是后面年数，负数是前面年数
	 * @return String
	 */
	public static String addYear(String dateStr, int i, String newFmt) {
		return dateCalculate(dateStr, Calendar.YEAR, i, TYPE_COM_YMDHMS, newFmt);
	}

	/**
	 * 返回日期加X年后的日期
	 * 
	 * DateUtil.addYear("2019-11-10 12:09:09",
	 * -36,DateUtil.TYPE_COM_YMDHMS,DateUtil.TYPE_CN_YMDHMS)
	 * 
	 * @param dateStr
	 * @param oldFmt
	 * @param newFmt
	 * @param i
	 *            正数是后面年数，负数是前面年数
	 * @return String
	 */
	public static String addYear(String dateStr, int i, String oldFmt,
			String newFmt) {
		return dateCalculate(dateStr, Calendar.YEAR, i, oldFmt, newFmt);
	}

	/**
	 * 日期计算 DateUtil.dateCalculate("2019-11-10 12:09:09", Calendar.DATE, 1)
	 * 
	 * @param dateStr
	 * @param field
	 *            Calender的计算域
	 *            YEAR,MONTH,DATE,HOUR(12小时),HOUR_OF_DAY(24小时),MINUTE,SECOND
	 * @param i
	 * @return String
	 */
	public static String dateCalculate(String dateStr, int field, int i) {
		return dateCalculate(dateStr, field, i, TYPE_COM_YMDHMS,
				TYPE_COM_YMDHMS);
	}

	/**
	 * 日期计算
	 * 
	 * DateUtil.dateCalculate("2019-11-10 12:09:09", Calendar.DATE,
	 * 1,DateUtil.TYPE_CN_YMDHMS)
	 * 
	 * @param dateStr
	 * @param newFmt
	 * @param field
	 *            Calender的计算域
	 *            YEAR,MONTH,DATE,HOUR(12小时),HOUR_OF_DAY(24小时),MINUTE,SECOND
	 * @param i
	 * @return String
	 */ 
	public static String dateCalculate(String dateStr, int field, int i,
			String newFmt) {
		return dateCalculate(dateStr, field, i, TYPE_COM_YMDHMS, newFmt);
	}

	/**
	 * 日期计算
	 * 
	 * DateUtil.dateCalculate("2019-11-10 12:09:09", Calendar.DATE,
	 * 1,DateUtil.TYPE_COM_YMDHMS,DateUtil.TYPE_CN_YMDHMS)
	 * 
	 * @param dateStr
	 * @param oldFmt
	 * @param newFmt
	 * @param field
	 *            Calender的计算域
	 *            YEAR,MONTH,DATE,HOUR(12小时),HOUR_OF_DAY(24小时),MINUTE,SECOND
	 * @param i
	 * @return String
	 */
	public static String dateCalculate(String dateStr, int field, int i,
			String oldFmt, String newFmt) {
		try {
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(parse(dateStr, oldFmt));
			calendar.add(field, i);
			return format(calendar.getTime(), newFmt);
		} catch (Exception e) {
			log.error("DateUtil.dateCalculate() error:"
					+ e.getLocalizedMessage());
		}
		return null;
	}

	/**
	 * 获取当前年份 DateUtil.currentYear()
	 * 
	 * @return int
	 */
	public static int currentYear() {
		return current(Calendar.YEAR);
	}

	/**
	 * 获取当前月份 DateUtil.currentMonth()
	 * 
	 * @return int
	 */
	public static int currentMonth() {
		return current(Calendar.MONTH) + 1;
	}

	/**
	 * 获取当前日份 DateUtil.currentDate()
	 * 
	 * @return int
	 */
	public static int currentDate() {
		return current(Calendar.DATE);
	}

	/**
	 * 获取当前小时数 DateUtil.currentHour()
	 * 
	 * @return int
	 */
	public static int currentHour() {
		return current(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 获取当前分钟数 DateUtil.currentMinute()
	 * 
	 * @return int
	 */
	public static int currentMinute() {
		return current(Calendar.MINUTE);
	}

	/**
	 * 获取当前秒数 DateUtil.currentSecond()
	 * 
	 * @return int
	 */
	public static int currentSecond() {
		return current(Calendar.SECOND);
	}

	/**
	 * 获取当前某一域的值 DateUtil.current(Calendar.SECOND)
	 * 
	 * @param field
	 *            Calender的计算域 YEAR,MONTH,DATE,HOUR_OF_DAY(24小时),MINUTE,SECOND
	 * @return int
	 */
	public static int current(int field) {
		Calendar calendar = new GregorianCalendar();
		return calendar.get(field);
	}

	/**
	 * 获取当前月份第一天
	 * 
	 * @return String
	 */
	public static String getFirstDateOfMonth() {
		return getFirstDateOfMonth(new Date());
	}

	/**
	 * 获取月份第一天
	 * 
	 * @param date
	 * @return String
	 */
	public static String getFirstDateOfMonth(Date date) {
		return getFirstDateOfMonth(date, TYPE_COM_YMDHMS);
	}

	/**
	 * 获取当前月份第一天
	 * 
	 * @param dateStr
	 * @return String
	 */
	public static String getFirstDateOfMonth(String dateStr) {
		return getFirstDateOfMonth(parse(dateStr), TYPE_COM_YMDHMS);
	}

	/**
	 * 获取月份第一天
	 * 
	 * @param dateStr
	 * @param newFmt
	 * @return String
	 */
	public static String getFirstDateOfMonth(String dateStr, String newFmt) {
		return getFirstDateOfMonth(parse(dateStr), newFmt);
	}

	/**
	 * 获取月份第一天
	 * 
	 * @param dateStr
	 * @param oldFmt
	 * @param newFmt
	 * @return String
	 */
	public static String getFirstDateOfMonth(String dateStr, String oldFmt,
			String newFmt) {
		return getFirstDateOfMonth(parse(dateStr, oldFmt), newFmt);
	}

	/**
	 * 获取月份第一天
	 * 
	 * @param date
	 * @param newFmt
	 * @return String
	 */
	public static String getFirstDateOfMonth(Date date, String newFmt) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, 1);
		return format(calendar.getTime(), newFmt);
	}

	/**
	 * 获取当前月份最后一天
	 * 
	 * @return String
	 */
	public static String getLastDateOfMonth() {
		return getLastDateOfMonth(new Date());
	}

	/**
	 * 获取月份最后一天
	 * 
	 * @param dateStr
	 * @return String
	 */
	public static String getLastDateOfMonth(String dateStr) {
		return getLastDateOfMonth(parse(dateStr), TYPE_COM_YMDHMS);
	}

	/**
	 * 获取月份最后一天
	 * 
	 * @param date
	 * @return String
	 */
	public static String getLastDateOfMonth(Date date) {
		return getLastDateOfMonth(date, TYPE_COM_YMDHMS);
	}

	/**
	 * 获取月份最后一天
	 * 
	 * @param dateStr
	 * @param newFmt
	 * @return String
	 */
	public static String getLastDateOfMonth(String dateStr, String newFmt) {
		return getLastDateOfMonth(parse(dateStr), newFmt);
	}

	/**
	 * 获取月份最后一天
	 * 
	 * @param dateStr
	 * @param oldFmt
	 * @param newFmt
	 * @return String
	 */
	public static String getLastDateOfMonth(String dateStr, String oldFmt,
			String newFmt) {
		return getLastDateOfMonth(parse(dateStr, oldFmt), newFmt);
	}

	/**
	 * 获取月份最后一天
	 * 
	 * @param date
	 * @param newFmt
	 * @return String
	 */
	public static String getLastDateOfMonth(Date date, String newFmt) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		return format(calendar.getTime(), newFmt);
	}

	/**
	 * 获取日期的星期(星期日,一，二，三，四，五，六)
	 * 
	 * @return String
	 */
	public static String getWeek() {
		return getWeek(new Date());
	}

	/**
	 * 获取日期的星期(星期日,一，二，三，四，五，六)
	 * 
	 * @param dateStr
	 *            默认格式YMDHMS
	 * @return String
	 */
	public static String getWeek(String dateStr) {
		return getWeek(parse(dateStr, TYPE_COM_YMDHMS));
	}

	/**
	 * 获取日期的星期(星期日,一，二，三，四，五，六)
	 * 
	 * @param dateStr
	 * @param fmt
	 * @return String
	 */
	public static String getWeek(String dateStr, String fmt) {
		return getWeek(parse(dateStr, fmt));
	}

	/**
	 * 获取日期的星期(星期日,一，二，三，四，五，六)
	 * 
	 * @param date
	 * @return String
	 */
	public static String getWeek(Date date) {
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			int weekNumber = calendar.get(Calendar.DAY_OF_WEEK) - 1;
			String[] weeks = new String[] { "日", "一", "二", "三", "四", "五", "六" };
			return "星期" + weeks[weekNumber];
		} else {
			return null;
		}

	}

	/**
	 * 验证字符串是否可以转换为日期，格式需符合本类中常用格式：COMMON,COMBINE,CN,EN
	 * 
	 * @param dateStr
	 * @return String
	 */
	public static boolean isDate(String dateStr) {
		String[] fmts = new String[] { TYPE_COM_YMDHMS, TYPE_COM_YMDHM,
				TYPE_COM_YMD, TYPE_COM_YM, TYPE_COM_MDHMS, TYPE_COM_MDHM,
				TYPE_COM_MD, TYPE_COM_HMS, TYPE_COM_HM, TYPE_COMBINE_YMDHMS,
				TYPE_COMBINE_YMDHM, TYPE_COMBINE_YMD, TYPE_COMBINE_YM,
				TYPE_COMBINE_MDHMS, TYPE_COMBINE_MDHM, TYPE_COMBINE_MD,
				TYPE_CN_YMDHMS, TYPE_CN_YMDHM, TYPE_CN_YMD, TYPE_CN_YM,
				TYPE_CN_MDHMS, TYPE_CN_MDHM, TYPE_CN_MD, TYPE_EN_YMDHMS,
				TYPE_EN_YMDHM, TYPE_EN_YMD, TYPE_EN_YM, TYPE_EN_MDHMS,
				TYPE_EN_MDHM, TYPE_EN_MD };
		Date d = null;
		for (String fmt : fmts) {
			d = parse(dateStr, fmt);
			if (d != null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 计算日期差值
	 * 
	 * @param dateStr1
	 * @param dateStr2
	 * @param field
	 *            Calender的计算域 DATE,HOUR,MINUTE,SECOND
	 * @return long
	 */
	public static Long subtract(String dateStr1, String dateStr2, int field) {
		return subtract(parse(dateStr1), parse(dateStr2), field);
	}

	/**
	 * 计算日期差值
	 * 
	 * @param dateStr1
	 * @param dateStr2
	 * @param fmt
	 * @param field
	 *            Calender的计算域 DATE,HOUR,MINUTE,SECOND
	 * @return long
	 */
	public static Long subtract(String dateStr1, String dateStr2, String fmt,
			int field) {
		return subtract(parse(dateStr1, fmt), parse(dateStr2, fmt), field);
	}

	/**
	 * 计算日期差值
	 * 
	 * @param date1
	 * @param date2
	 * @param field
	 * @return long
	 */
	public static Long subtract(Date date1, Date date2, int field) {
		Long differ = date1.getTime() - date2.getTime();
		if (field == Calendar.SECOND) {
			return differ / (1000);
		}
		if (field == Calendar.MINUTE) {
			return differ / (60 * 1000);
		}
		if (field == Calendar.HOUR) {
			return differ / (60 * 60 * 1000);
		}
		if (field == Calendar.DATE) {
			return differ / (24 * 60 * 60 * 1000);
		}
		if (field == Calendar.MONTH) {
			return differ / (30 * 24 * 60 * 60 * 1000);
		}
		return 0L;
	}

	public static void main(String[] args) {
		print(DateUtil.compareNow(DateUtil.parse("2019-11-10 12:09:09")));
		print(DateUtil.getNow(DateUtil.TYPE_CN_YMD));
		print(DateUtil.parse("2019-11-10 12:09:09"));
		print(DateUtil.parse("2019年11月10日", DateUtil.TYPE_CN_YMD));
		print(DateUtil.format(new Date()));
		print(DateUtil.format(new Date(), DateUtil.TYPE_EN_YMDHMS));
		print(DateUtil.formatByStr("2019-11-10 12:09:09",
				DateUtil.TYPE_CN_YMDHM));
		print(DateUtil.formatByStr("2019-11-10 12:09:09",
				DateUtil.TYPE_COM_YMDHMS, DateUtil.TYPE_CN_YMDHM));
		print(DateUtil.addDay("2019-11-10 12:09:09", -21));
		print(DateUtil.addDay("2019-11-10 12:09:09", -21,
				DateUtil.TYPE_CN_YMDHMS));
		print(DateUtil.addDay("2019-11-10 12:09:09", -21,
				DateUtil.TYPE_COM_YMDHMS, DateUtil.TYPE_CN_YMD));
		print(DateUtil.addMonth("2019-11-10 12:09:09", -36));
		print(DateUtil.addMonth("2019-11-10 12:09:09", -36,
				DateUtil.TYPE_CN_YMDHMS));
		print(DateUtil.addMonth("2019-11-10 12:09:09", -36,
				DateUtil.TYPE_COM_YMDHMS, DateUtil.TYPE_CN_YMDHMS));
		print(DateUtil.addYear("2019-11-10 12:09:09", -36));
		print(DateUtil.addYear("2019-11-10 12:09:09", -36,
				DateUtil.TYPE_CN_YMDHMS));
		print(DateUtil.addYear("2019-11-10 12:09:09", -36,
				DateUtil.TYPE_COM_YMDHMS, DateUtil.TYPE_CN_YMDHMS));
		print(DateUtil.dateCalculate("2019-11-10 12:09:09", Calendar.DATE, 1));
		print(DateUtil.dateCalculate("2019-11-10 12:09:09", Calendar.DATE, 1,
				DateUtil.TYPE_CN_YMDHMS));
		print(DateUtil.dateCalculate("2019-11-10 12:09:09", Calendar.DATE, 1,
				DateUtil.TYPE_COM_YMDHMS, DateUtil.TYPE_CN_YMDHMS));
		print(DateUtil.currentYear());
		print(DateUtil.currentMonth());
		print(DateUtil.currentDate());
		print(DateUtil.currentHour());
		print(DateUtil.currentMinute());
		print(DateUtil.currentSecond());
		print(DateUtil.current(Calendar.SECOND));
		print(DateUtil.getWeek());
		print(DateUtil.getWeek("2019-10-10 12:09:09"));
		print(DateUtil.getWeek("2011年10月10日12时09分09秒", DateUtil.TYPE_CN_YMDHMS));
		print(DateUtil.getWeek(DateUtil.parse("2019-11-10 12:09:09")));
		print(DateUtil.isDate("2011年10月10日12时09分09秒"));
		print(DateUtil.isDate("2011年10月10日"));
		print(DateUtil.isDate("12:09:09"));
	}

	public static void print(Object obj) {
		System.out.println(obj);
	}
}
