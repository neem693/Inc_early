package com.anylogic.iot.api.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateTimeUtil {

	public static Date parse(String time) {
		return parse("yyyy-MM-dd HH:mm:ss.SSS", time);
	}

	public static Date parse(String pattern, String time) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			return format.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date getDate(int year, int month, int date, int hour, int minute, int second) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, date, hour, minute, second);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date getDate(int hour, int minute, int second) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, second);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	public static Calendar getCalendar(int field, int amount) {
		return getCalendar(new Date(), field, amount);
	}
	
	public static Calendar getCalendar(Date date, int field, int amount) {
		Calendar cal = Calendar.getInstance();
		
		cal.setTime(date);
		cal.add(field, amount);
		
		return cal;
	}

}
