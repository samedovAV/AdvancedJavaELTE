package months;

public class EnumInfos {
	public static void main(String[] args) {
		Month m = Month.MAR;

		int ordinal = m.ordinal();
		System.out.println(ordinal);

		Month converted = Month.valueOf("FEB");
		System.out.println(converted == Month.FEB);
		System.out.println(converted == Month.MAR);
		System.out.println(converted.ordinal());

//		Month.valueOf("dfhuskdgfudsak");

		Month[] allMonths = Month.values();
		System.out.println(allMonths.length);
		for (Month month : allMonths) {
			System.out.println(month);
		}
		
		Weekday answer =
			new MyDate(2022, Month.SEP, 22).getWeekday();

		System.out.println(answer);
		
		System.out.println(Weekday.MON.isThisDay(2022, Month.SEP, 22));
		System.out.println(Weekday.THU.isThisDay(2022, Month.SEP, 22));
	}
}
