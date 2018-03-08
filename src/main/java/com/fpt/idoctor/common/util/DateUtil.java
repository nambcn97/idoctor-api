package com.fpt.idoctor.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	public static Date getOnlyCurrentDate() throws ParseException {
		String currentDate = dateFormat.format(new Date());
		return dateFormat.parse(currentDate);
	}

	public static Date formatOnlyDate(Date date) throws ParseException {
		String onlyDate = dateFormat.format(date);
		return dateFormat.parse(onlyDate);
	}

	public static Date getLastDayExceptFriSun(Date date) {
		if (date.getDay() == 0) {
			return getNDateAgo(date, 2);
		} else if (date.getDay() == 1) {
			return getNDateAgo(date, 3);
		} else {
			return getNDateAgo(date, 1);
		}
	}

	public static Date get7DateAgo(Date toDate) {
		return getNDateAgo(toDate, 7);
	}

	public static Date getNDateAgo(Date toDate, int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(toDate);
		calendar.add(Calendar.DATE, -n);
		return calendar.getTime();
	}
}
