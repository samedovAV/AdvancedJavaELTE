package months;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MyDate {
	int year;
	Month month;
	int day;

	public MyDate(int year, Month month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}

	Weekday getWeekday() {
		var gc = new GregorianCalendar(year, month.ordinal(), day-1);
		int idx = gc.get(Calendar.DAY_OF_WEEK);
		return Weekday.values()[idx];
	}
}
