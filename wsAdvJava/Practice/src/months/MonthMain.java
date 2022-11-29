package months;

public class MonthMain {
	public static void main(String[] args) {
		Months3 myFavouriteMonth = Months3.JAN;
		Months3 myLeastFavouriteMonth = Months3.JAN;
		// type inference
//		var anotherMonth = Month2.FEB;

		System.out.println(Months3.JAN);
		System.out.println(Months3.JAN.getName("en"));
		System.out.println(myFavouriteMonth.getName("xyz"));
		System.out.println(myLeastFavouriteMonth.getName("hu"));
	}
}
