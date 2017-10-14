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
	private static LocalDate nextOrSameWed = null;
	private static LocalDate lastWednesday = null;

	static {
		Calendar cal = Calendar.getInstance();

		if (cal.get(Calendar.DAY_OF_WEEK) > Calendar.WEDNESDAY) {
			nextOrSameWed = LocalDate.now().with(TemporalAdjusters.previous(DayOfWeek.WEDNESDAY));
			lastWednesday = nextOrSameWed.with(TemporalAdjusters.previous(DayOfWeek.WEDNESDAY));
		} else if (cal.get(Calendar.DAY_OF_WEEK) <= Calendar.WEDNESDAY) {
			nextOrSameWed = LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.WEDNESDAY));
			lastWednesday = nextOrSameWed.with(TemporalAdjusters.previous(DayOfWeek.WEDNESDAY));
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

	public static boolean isDateBetweenLastWedToThis(Date date) {
		boolean val = false;
		LocalDate ld = null;
		if(date != null){
		    ld = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		    val = (ld.isAfter(lastWednesday) && ld.isBefore(nextOrSameWed));
		}
		return val;
	}
}
