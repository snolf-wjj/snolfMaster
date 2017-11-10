package com.snolf.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IDEA
 * User：wangjunjie
 * Date: 2017/11/1
 * Time: 14:35
 */
public class DateUtil {

	public static final String DATETIME_PATTERN_8 = "yyyyMMdd";
	public static final String DATETIME_PATTERN_10 = "yyyy-MM-dd";
	public static String DATETIME_PATTERN_14 = "yyyyMMddHHmmss";
	public static String DATETIME_PATTERN_19 = "yyyy-MM-dd HH:mm:ss";


	/**
	 * 获取指定日期格式的日期
	 * @param format
	 * @return
	 */
	public static String getDateByFormat(String format) {
		String date = "";
		Date currentDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		date = formatter.format(currentDate);
		return date;
	}

	/**
	 * 获取当前时间 年-月-日
	 * @return
	 */
	public static String getDate() {
		String strCurrentDate = "";
		Date currentDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		strCurrentDate = formatter.format(currentDate);
		return strCurrentDate;
	}

	/**
	 * 获取当前时间 时:分:秒
	 * @return
	 */
	public static String getTime() {
		String strCurrentTime = "";
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		strCurrentTime = formatter.format(currentTime);
		return strCurrentTime;
	}

	/**
	 * 日期转字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDateString(Date date, String format){
		return new SimpleDateFormat(format).format(date);
	}
}
