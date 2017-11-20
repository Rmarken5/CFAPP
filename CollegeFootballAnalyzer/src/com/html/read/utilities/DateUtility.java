package com.html.read.utilities;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

public class DateUtility {
	private static LocalDate nextOrSameMonday = null;
	private static LocalDate lastMonday = null;

	static {
		Calendar cal = Calendar.getInstance();

		if (cal.get(Calendar.DAY_OF_WEEK) > Calendar.MONDAY) {
			nextOrSameMonday = LocalDate.now().with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
			lastMonday = nextOrSameMonday.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
		} else if (cal.get(Calendar.DAY_OF_WEEK) <= Calendar.MONDAY) {
			nextOrSameMonday = LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
			lastMonday = nextOrSameMonday.with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
		}
	}

	public DateUtility() {

	}

	public static java.sql.Timestamp formTimestampFromDateTime(String date, String time) throws ParseException {
		DateFormat df = new SimpleDateFormat("MMM dd, yyyy hh:mm aa");
		Timestamp ts = null;
		Date holder = null;
		StringBuilder builder = null;
		if (date != null && !"".equals(date) && time != null && !"".equals(time)) {
			builder = new StringBuilder();
			builder.append(date);
			builder.append(" ");
			builder.append(time);
			holder = df.parse(builder.toString());
			ts = new Timestamp(holder.getTime());
		}

		return ts;
	}

	public static boolean isDateBetweenLastMondayToThis(Date date) {
		boolean val = false;
		LocalDate ld = null;
		if(date != null){
		    ld = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		    val = (ld.isAfter(lastMonday) && ld.isBefore(nextOrSameMonday));
		}
		return val;
	}
}
