package lambdas;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import months.Weekday;

public class WeekdayStepper {
	static BiFunction<Weekday, Integer, Weekday> nextWeekday =
			(weekday, n) -> {
				int weekLength = Weekday.values().length;
				int nextDayIdx = weekday.ordinal() + n;
				nextDayIdx = nextDayIdx % weekLength;
				nextDayIdx += (nextDayIdx >= 0) ? 0 : weekLength;
				return Weekday.values()[nextDayIdx];
			};

	static Weekday[] dayState = { Weekday.MON };
	static Supplier<Weekday> getNextWeekDay = () -> {
		var origDay = dayState[0];
		dayState[0] = nextWeekday.apply(dayState[0], 1);
		return origDay;
	};

	static Supplier<Weekday> getNextWeekDay2 = new Supplier<Weekday>() {
		Weekday properlyLocalWeekdayState = Weekday.MON;
		
		@Override
		public Weekday get() {
			var origDay = properlyLocalWeekdayState;
			properlyLocalWeekdayState = nextWeekday.apply(properlyLocalWeekdayState, 1);
			return origDay;
		}
	};

	// DO NOT SHARE STATE!
	public static void main(String[] args) {
//		Function<Integer, Supplier<Integer>> makeIncreaser = startValue -> {
//			int[] num = { startValue };
//			return () -> ++num[0];
//		};

		Function<Integer, IntSupplier> makeIncreaser = startValue -> {
			int[] num = { startValue };
			return () -> ++num[0];
		};

		IntSupplier supp1 = makeIncreaser.apply(123);
		var supp2 = makeIncreaser.apply(-10);

		System.out.println(supp1.getAsInt());
		System.out.println(supp1.getAsInt());
		System.out.println(supp1.getAsInt());
		System.out.println(supp1.getAsInt());
		System.out.println(supp1.getAsInt());

		System.out.println(supp2.getAsInt());
		System.out.println(supp2.getAsInt());
		System.out.println(supp2.getAsInt());
		System.out.println(supp2.getAsInt());

		System.out.println(supp1.getAsInt());
	}

	public static void main5(String[] args) {
		Supplier<Supplier<Integer>> makeOnes = () -> () -> 1;
		Supplier<Integer> supp1 = makeOnes.get();
		Supplier<Integer> supp2 = makeOnes.get();
	}

	public static void main4(String[] args) {
		Supplier<Supplier<Weekday>> suppSupp = () -> new Supplier<Weekday>() {
			Weekday properlyLocalWeekdayState = Weekday.MON;
			
			@Override
			public Weekday get() {
				var origDay = properlyLocalWeekdayState;
				properlyLocalWeekdayState = nextWeekday.apply(properlyLocalWeekdayState, 1);
				return origDay;
			}
		};
		
		System.out.println(getNextWeekDay.get());
		System.out.println(getNextWeekDay2.get());
		System.out.println(getNextWeekDay.get());
		System.out.println(getNextWeekDay2.get());
		System.out.println(getNextWeekDay.get());
		System.out.println(getNextWeekDay2.get());

		Supplier<Weekday> supp1 = suppSupp.get();
		Supplier<Weekday> supp2 = suppSupp.get();
		Supplier<Weekday> supp3 = suppSupp.get();
		Supplier<Weekday> supp4 = suppSupp.get();
		System.out.println(supp1.get());
		System.out.println(supp2.get());
		System.out.println(supp3.get());
		System.out.println(supp4.get());
		System.out.println(supp1.get());
		System.out.println(supp2.get());
		System.out.println(supp3.get());
		System.out.println(supp4.get());
		System.out.println(supp1.get());
		System.out.println(supp2.get());
		System.out.println(supp3.get());
		System.out.println(supp4.get());
		
//		for (int i = 0; i < 28; i++) {
//			System.out.println(getNextWeekDay.get());
//		}
	}

	public static void main3(String[] args) {
		for (Weekday today : Weekday.values()) {
			System.out.println(nextWeekday.apply(today, -1234));
		}

//		for (int i = 0; i < 100; i++) {
//			System.out.println(i + " " + (-i % 7));
//		}
	}

	public static void main2(String[] args) {
		Function<Weekday, Weekday> nextWeekday =
			weekday -> {
//				if (weekday == Weekday.SUN) {
//					return Weekday.MON;
//				}
//
//				int idx = weekday.ordinal();
//				int nextIdx = idx + 1;
//				
//				return Weekday.values()[nextIdx];

				return weekday == Weekday.SUN ?
						Weekday.MON :
						Weekday.values()[weekday.ordinal() + 1];
			};

		for (Weekday today : Weekday.values()) {
			System.out.println(nextWeekday.apply(today));
		}
	}
}
