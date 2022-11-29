package lambdas;

import static java.lang.Integer.parseInt;
import static java.lang.System.out;
import static java.util.Arrays.sort;
import static java.util.Comparator.comparing;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

import months.Weekday;

public class UseArgs {
	public static void main(String[] args) {
		args = "fbhdsaj FRI gagdfshsf SUN wafb MON dsagye THU FRI dsafyu".split(" ");

//		Function<String, Boolean> isWeekdayName = txt -> {
		Predicate<String> isWeekdayName = txt -> {
			Weekday[] allWeekdays = Weekday.values();
			for (Weekday weekday : allWeekdays) {
//				if (weekday.toString() == txt) ... // wrong! (in Java)
				if (weekday.toString().equals(txt)) {
					return true;
				}
			}
			return false;
		};

		Predicate<String> isWeekdayName2 = txt -> {
			try {
				Weekday.valueOf(txt);
				return true;
			} catch (Exception e) {
				return false;
			}
		};

//		Arrays.sort(args, comparing(txt -> isWeekdayName.test(txt)));
		Arrays.sort(args, comparing(isWeekdayName::test).reversed());
		System.out.println(Arrays.toString(args));


		ToIntFunction<String> weekdayWeighter = txt -> {
			try {
				Weekday weekday = Weekday.valueOf(txt);
				return -weekday.ordinal() - 10;
			} catch (Exception e) {
				return -1;
			}
		};

		Arrays.sort(args, comparing(weekdayWeighter::applyAsInt).reversed());
		System.out.println(Arrays.toString(args));
	}

	public static void main5(String[] args) {
//		BiFunction<Integer, Function<Integer, Integer>, int[]> createArray = (n, f) -> {
		BiFunction<Integer, IntUnaryOperator, int[]> createArray = (n, f) -> {
			int[] retval = new int[n];
			for (int i = 0; i < n; ++i) {
				retval[i] = f.applyAsInt(i);
			}
			return retval;
		};

		System.out.println(Arrays.toString(createArray.apply(16, n -> 2*n+1)));
		System.out.println(Arrays.toString(createArray.apply(16, n -> 3*n+1)));

		int[][] sample = {
				{},
				{1, 2, 3},
				{2, 6}
		};
		
		// Java: no built-in Pair, Tuple, ...
		BiFunction<Map.Entry<Integer, IntUnaryOperator>, IntBinaryOperator, int[][]> createArray2 =
			(twofer, getElemValue) -> {
				Integer n = twofer.getKey();
				IntUnaryOperator getRowSize = twofer.getValue();

				int[][] retval = new int[n][];
				for (int rowIdx = 0; rowIdx < n; ++rowIdx) {
					int rowLen = getRowSize.applyAsInt(rowIdx);
					retval[rowIdx] = new int[rowLen];
					for (int colIdx = 0; colIdx < rowLen; ++colIdx) {
						retval[rowIdx][colIdx] = getElemValue.applyAsInt(rowIdx, colIdx);
					}
				}
				return retval;
			};
	
		System.out.println(Arrays.deepToString(
				createArray2.apply(Map.entry(5, row -> 3 * row), (i, j) -> 10*i+j)
			));

		
		System.out.println(Arrays.toString(
				createArray2.apply(Map.entry(5, row -> 3 * row), (i, j) -> 10*i+j)
			));
	}

	public static void main4(String[] args) {
//		String->Integer
		Function<String, Integer> countAs = s -> {
			int retval = 0;

			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) == 'a')   ++retval;
			}
			
			return retval;
		};
		// String -> int
		ToIntFunction<String> countAs2 = s -> s.replaceAll("[^a]", "").length();

		Function<String, Integer> countBs = s -> {
			int retval = 0;

			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) == 'b')   ++retval;
			}
			
			return retval;
		};

		System.out.println(countAs.apply("abcdarteoiyt"));
		System.out.println(countAs2.applyAsInt("abcdarteoiyt"));

	

		args = new String[] { "a", "b", "c", "1" };
		args = "a b aa c aaagfdjka 1".split(" ");

//		Collections.sort(someList); // sorts a list
//		Arrays.sort(someArray); // sorts an array

//		Comparator<String> cmpAs = (s1, s2) -> countAs.apply(s2) - countAs.apply(s1);
		Comparator<String> cmpAs = (s1, s2) -> countAs.apply(s1) - countAs.apply(s2);

//		System.out.println(args);
		System.out.println(Arrays.toString(args));
		Arrays.sort(args, cmpAs);
		System.out.println(Arrays.toString(args));

		Arrays.sort(args, (s1, s2) -> countAs.apply(s1) - countAs.apply(s2));
		Arrays.sort(args, Comparator.comparing(s -> countAs.apply(s)));
		Arrays.sort(args, Comparator.comparing(countAs::apply));
		Arrays.sort(args, comparing(countAs::apply));
		// sort: not everything should be static imported!
		sort(args, comparing(countAs::apply));
		
		out.println(Arrays.toString(args));

//		int value = Integer.parseInt("5287");
		int value = parseInt("5287");
		System.out.println(value == 6436);

		// not everything can be static imported!
//		System.out.println(toString(args));

		args = "gfds fgabds aiuopuiowqel;ds gfds bbbba gres aaaaaaa".split(" ");	
		
		sort(args, comparing(countAs::apply).thenComparing(countBs::apply));
		sort(args, comparing(countAs::apply).reversed());
		out.println(Arrays.toString(args));
	}

	private <U, T extends Object> U apply(T t1) {
		return null;
	}
}
