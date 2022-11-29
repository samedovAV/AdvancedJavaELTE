package months;

public enum Weekday {
	MON, TUE, WED, THU, FRI, SAT, SUN;
	
	boolean isThisDay(int year, Month month, int day) {
		return new MyDate(year, month, day).getWeekday() == this;
	}
}
