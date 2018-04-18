package com.daily.dailytest.core.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期工具类
 *
 */
public class DateUtil {
	// 默认日期格式
	public static final String DATE_DEFAULT_FORMAT = "yyyy-MM-dd";

	public static final String DATE_YYYYMM_FORMART="yyyy-MM";

	public static final String DATE_MMDD_FORMART="MM-dd";

	// 默认时间格式
	public static final String DATETIME_DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final String TIME_DEFAULT_FORMAT = "HH:mm:ss";

	public static final String DATETIME_DEFAULT_FORMAT_SECORD = "yyyy-MM-dd HH-mm-ss-sss";

	public static final String DATETIME_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

	public static final String DATETIME_YYYYMMDD = "yyyyMMdd";



	// 日期格式化
	private static DateFormat dateFormat = null;

	// 时间格式化
	private static DateFormat dateTimeFormat = null;

	private static DateFormat timeFormat = null;

	private static Calendar gregorianCalendar = null;

	private static  DateFormat dateFormatYyyyMmDd=null;

	static {
		dateFormat = new SimpleDateFormat(DATE_DEFAULT_FORMAT);
		dateTimeFormat = new SimpleDateFormat(DATETIME_DEFAULT_FORMAT);
		timeFormat = new SimpleDateFormat(TIME_DEFAULT_FORMAT);
		dateFormatYyyyMmDd=new SimpleDateFormat(DATETIME_YYYYMMDD);
		gregorianCalendar = new GregorianCalendar();
	}

	/**
	 *将前端传过来的时间戳转化为想要的时间格式的字符串
	 * @param timestamp 前端时间戳（精确到毫秒）
	 * @param format  格式化字符串
	 * @return
	 */
	public static String getDateFormatStr(String timestamp,String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		long lcc_time = Long.valueOf(timestamp);
		return sdf.format(new Date(lcc_time));
	}

	/**
	 * 日期格式化yyyy-MM-dd
	 *
	 * @param date
	 * @return
	 */
	public static Date formatDate(String date, String format) {
		try {
			return new SimpleDateFormat(format).parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 日期格式化yyyy-MM-dd
	 *
	 * @param date
	 * @return
	 */
	public static String getDateFormat(Date date) {
		return dateFormat.format(date);
	}

	/**
	 * 日期格式化yyyy-MM-dd HH:mm:ss
	 *
	 * @param date
	 * @return
	 */
	public static String getDateTimeFormat(Date date) {
		return dateTimeFormat.format(date);
	}

	/**
	 * 时间格式化
	 *
	 * @param date
	 * @return HH:mm:ss
	 */
	public static String getTimeFormat(Date date) {
		return timeFormat.format(date);
	}

	public static String getDatetimeYyyymmdd(Date date){
		return dateFormatYyyyMmDd.format(date);
	}

	/**
	 * 日期格式化
	 *
	 * @param date
	 * @param 格式类型
	 * @return
	 */
	public static String getDateFormat(Date date, String formatStr) {
		if (StringUtils.isNotBlank(formatStr)) {
			return new SimpleDateFormat(formatStr).format(date);
		}
		return null;
	}

	/**
	 * 日期格式化
	 *
	 * @param date
	 * @return
	 */
	public static Date getDateFormat(String date) {
		try {
			return dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 时间格式化
	 *
	 * @param date
	 * @return
	 */
	public static Date getDateTimeFormat(String date) {
		try {
			return dateTimeFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取当前日期(yyyy-MM-dd)
	 *
	 * @param date
	 * @return
	 */
	public static Date getNowDate() {
		return DateUtil.getDateFormat(dateFormat.format(new Date()));
	}

	/**
	 * 获取当前日期星期一日期
	 *
	 * @return date
	 */
	public static Date getFirstDayOfWeek() {
		gregorianCalendar.setFirstDayOfWeek(Calendar.MONDAY);
		gregorianCalendar.setTime(new Date());
		gregorianCalendar.set(Calendar.DAY_OF_WEEK, gregorianCalendar.getFirstDayOfWeek()); // Monday
		return gregorianCalendar.getTime();
	}

	/**
	 * 获取当前日期星期日日期
	 *
	 * @return date
	 */
	public static Date getLastDayOfWeek() {
		gregorianCalendar.setFirstDayOfWeek(Calendar.MONDAY);
		gregorianCalendar.setTime(new Date());
		gregorianCalendar.set(Calendar.DAY_OF_WEEK, gregorianCalendar.getFirstDayOfWeek() + 6); // Monday
		return gregorianCalendar.getTime();
	}

	/**
	 * 获取日期星期一日期
	 *
	 * @param 指定日期
	 * @return date
	 */
	public static Date getFirstDayOfWeek(Date date) {
		if (date == null) {
			return null;
		}
		gregorianCalendar.setFirstDayOfWeek(Calendar.MONDAY);
		gregorianCalendar.setTime(date);
		gregorianCalendar.set(Calendar.DAY_OF_WEEK, gregorianCalendar.getFirstDayOfWeek()); // Monday
		return gregorianCalendar.getTime();
	}

	/**
	 * 获取日期星期一日期
	 *
	 * @param 指定日期
	 * @return date
	 */
	public static Date getLastDayOfWeek(Date date) {
		if (date == null) {
			return null;
		}
		gregorianCalendar.setFirstDayOfWeek(Calendar.MONDAY);
		gregorianCalendar.setTime(date);
		gregorianCalendar.set(Calendar.DAY_OF_WEEK, gregorianCalendar.getFirstDayOfWeek() + 6); // Monday
		return gregorianCalendar.getTime();
	}

	/**
	 * 获取当前月的第一天
	 *
	 * @return date
	 */
	public static Date getFirstDayOfMonth() {
		gregorianCalendar.setTime(new Date());
		gregorianCalendar.set(Calendar.DAY_OF_MONTH, 1);
		return gregorianCalendar.getTime();
	}

	/**
	 * 获取当前月的最后一天
	 *
	 * @return
	 */
	public static Date getLastDayOfMonth() {
		gregorianCalendar.setTime(new Date());
		gregorianCalendar.set(Calendar.DAY_OF_MONTH, 1);
		gregorianCalendar.add(Calendar.MONTH, 1);
		gregorianCalendar.add(Calendar.DAY_OF_MONTH, -1);
		return gregorianCalendar.getTime();
	}

	/**
	 * 获取指定月的第一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfMonth(Date date) {
		gregorianCalendar.setTime(date);
		gregorianCalendar.set(Calendar.DAY_OF_MONTH, 1);
		return gregorianCalendar.getTime();
	}

	/**
	 * 获取指定月的最后一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfMonth(Date date) {
		gregorianCalendar.setTime(date);
		gregorianCalendar.set(Calendar.DAY_OF_MONTH, 1);
		gregorianCalendar.add(Calendar.MONTH, 1);
		gregorianCalendar.add(Calendar.DAY_OF_MONTH, -1);
		return gregorianCalendar.getTime();
	}

	/**
	 * 获取日期前一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getDayBefore(Date date) {
		gregorianCalendar.setTime(date);
		int day = gregorianCalendar.get(Calendar.DATE);
		gregorianCalendar.set(Calendar.DATE, day - 1);
		return gregorianCalendar.getTime();
	}

	/**
	 * 获取日期后一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getDayAfter(Date date) {
		gregorianCalendar.setTime(date);
		int day = gregorianCalendar.get(Calendar.DATE);
		gregorianCalendar.set(Calendar.DATE, day + 1);
		return gregorianCalendar.getTime();
	}

	/**
	 * 获取当前年
	 *
	 * @return
	 */
	public static int getNowYear() {
		Calendar d = Calendar.getInstance();
		return d.get(Calendar.YEAR);
	}

	/**
	 * 获取当前月份
	 *
	 * @return
	 */
	public static int getNowMonth() {
		Calendar d = Calendar.getInstance();
		return d.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取当月天数
	 *
	 * @return
	 */
	public static int getNowMonthDay() {
		Calendar d = Calendar.getInstance();
		return d.getActualMaximum(Calendar.DATE);
	}

	/**
	 * 获取时间段的每一天
	 *
	 * @param 开始日期
	 * @param 结算日期
	 * @return 日期列表
	 */
	public static List<Date> getEveryDay(Date startDate, Date endDate) {
		if (startDate == null || endDate == null) {
			return null;
		}
		// 格式化日期(yy-MM-dd)
		startDate = DateUtil.getDateFormat(DateUtil.getDateFormat(startDate));
		endDate = DateUtil.getDateFormat(DateUtil.getDateFormat(endDate));
		List<Date> dates = new ArrayList<Date>();
		gregorianCalendar.setTime(startDate);
		dates.add(gregorianCalendar.getTime());
		while (gregorianCalendar.getTime().compareTo(endDate) < 0) {
			// 加1天
			gregorianCalendar.add(Calendar.DAY_OF_MONTH, 1);
			dates.add(gregorianCalendar.getTime());
		}
		return dates;
	}
	public static Date getFirstDay(int dayty){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -dayty);
		return c.getTime();
	}
	/**
	 * 获取提前多少个月
	 *
	 * @param monty
	 * @return
	 */
	public static Date getFirstMonth(int monty) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -monty);
		return c.getTime();
	}

	/**
	 * 获取当前时间下的开始时间和结束时间 yyyyMMddHHmmss
	 * @param dateStr 日期字符串
	 * @param isBegin 获取开始or结束
	 * @return 获取开始or结束 （格式 “yyyyMMddHHmmss”）
	 */
	public static String getOneDayStartOrEnd(String dateStr, boolean isBegin){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat simpleDateFormatTime = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		try {
			date = simpleDateFormat.parse(dateStr);
		} catch (ParseException e){

		}
		if (isBegin){
			String str = simpleDateFormatTime.format(date);
			return str + "000000";
		}else {
			String str = simpleDateFormatTime.format(date);
			return str + "235959";
		}
	}


	/**
	 *将时间转化为DATE YYYY-MM-DD
	 * @param dateTime
	 * @return
	 */
	public static String parse2Date(String dateTime){
		SimpleDateFormat sf =new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		try{
			date=sf.parse(dateTime);
		}catch (Exception e){
			e.printStackTrace();
		}
		String dateTimeStr=sf.format(date);
		return dateTimeStr;
	}

	/**
	 *将时间转化为DATETIME
	 * @param dateTime
	 * @return
	 */
	public static String parse2DateTime(String dateTime){
		SimpleDateFormat sf =new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
		Date date=null;
		try{
			date=sf.parse(dateTime);
		}catch (Exception e){
			e.printStackTrace();
		}
		String dateTimeStr=sf.format(date);
		return dateTimeStr;
	}

	/**
	 * 将timestamp转换为Date类型
	 * @param timestramp
	 * @return
	 * @throws ParseException
	 */
	public static Date parseTimestampToDate(Long timestramp) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeStr = format.format(timestramp);
		try {
			return format.parse(timeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

/*	*//**
	 * 将java.util.Date类型转换为timestamp类型
	 * @param date
	 * @return
	 *//*
	public static long parseDateToTimestamp(Date date) {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = sdf.format(date);
		Timestamp timestamp = Timestamp.valueOf(format);
		return 1L;
	}*/

}
