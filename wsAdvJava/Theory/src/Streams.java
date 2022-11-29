import static java.lang.System.out;
import static java.nio.file.Files.lines;
import static java.util.Arrays.stream;
import static java.util.Optional.empty;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.IntStream.iterate;
import static java.util.stream.IntStream.range;
import static java.util.stream.IntStream.rangeClosed;
import static java.util.stream.Stream.of;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.function.IntUnaryOperator;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class MyClass {}

public class Streams {
	public static void main(String[] args) throws IOException {
//		iterate(1, n -> n * 1023)
//			.limit(200)
//			.forEach(out::println);

		double randomNumber = Math.random();
		Random randomGenerator = new Random();
		
		Stream.generate(() -> ThreadLocalRandom.current().nextInt())
			.limit(200)
			.forEach(out::println);

		Optional<Integer> roundRandomNumber = Stream.generate(() -> ThreadLocalRandom.current().nextInt())
			.limit(1000)
			.filter(n -> n % 1000 == 0)
			.findFirst();
		System.out.println(roundRandomNumber.map(n -> 2 * n));

	
		Optional<Integer> roundRandomNumber2 = Stream.generate(() -> ThreadLocalRandom.current().nextInt())
			.parallel()
			.limit(1000)
			.filter(n -> n % 1000 == 0)
			.findAny();
		System.out.println(roundRandomNumber2.map(n -> 2 * n));

		boolean noneMatch = Stream.generate(() -> ThreadLocalRandom.current().nextInt())
				.parallel()
				.limit(1000)
				.noneMatch(n -> n % 1000 == 0);
		boolean anyMatch = Stream.generate(() -> ThreadLocalRandom.current().nextInt())
				.parallel()
				.limit(1000)
				.anyMatch(n -> n % 1000 == 0);
		boolean allMatch = Stream.generate(() -> ThreadLocalRandom.current().nextInt())
				.parallel()
				.limit(1000)
				.allMatch(n -> n % 1000 == 0);
		System.out.println(noneMatch);
		System.out.println(anyMatch);
		System.out.println(allMatch);

		List<Integer> collected = Stream.generate(() -> ThreadLocalRandom.current().nextInt(-1000, 1000))
			.limit(30)
//			.sorted()
//			.sorted(Comparator.comparing(n -> ("" + n).length()))
//			.sorted(Comparator.comparing((Integer n) -> ("" + n).length()))
//			.sorted(Comparator.comparing(n -> ("" + n).length())
//					.thenComparing(n -> n))
			.sorted(Comparator.comparing((Integer n) -> ("" + n).length())
					.thenComparing(n -> n))
			.collect(Collectors.toList());

		Function<Integer, Integer> f1 = n -> n;
		ToIntFunction<Integer> f2 = n -> n;
		IntUnaryOperator f3 = n -> n;
//		var f4 = n -> n;
		var f4a = (IntUnaryOperator)n -> n;
		
		System.out.println(collected);

//		reduce (foldl)
//		5 6 8       26
// 		 1 2 3 4 5 6
	}

	public static void main14(String[] args) throws IOException {
//		IntStream.generate(new IntSupplier() {
//			boolean isEven = false;
//			int n = 0;
//
//			@Override
//			public int getAsInt() {
//				isEven = !isEven;
//				if (isEven)  ++n;
//				return n;
//			}
//		})
//			.limit(30)
//			.forEach(out::println);

		Stream<IntStream> mapToObj = IntStream.iterate(1, n -> n + 1)
			.mapToObj(n -> IntStream.of(n, n));
		Stream<Stream<Integer>> mapToObj2 = IntStream.iterate(1, n -> n + 1)
			.mapToObj(n -> Stream.of(n, n));

		iterate(1, n -> n + 1)
			.flatMap(n -> IntStream.of(n, n))
			.limit(20)
			.forEach(out::println);
	}

	public static void main13(String[] args) throws IOException {
		List<Integer> listOfIntegers = IntStream.iterate(1, n -> n + 1)
			.limit(20)
			.boxed()
//			.mapToObj(n -> n)
//			.mapToObj(n -> Integer.valueOf(n))
			.collect(Collectors.toList());

	
		List<Integer> listOfIntegers2 = Stream.of(1, 2, 3)
			.collect(Collectors.toList());

		Set<Integer> set = Stream.of(1, 2, 3)
			.collect(Collectors.toSet());

		Set<Integer> set2 = Stream.of(1, 2, 3)
			.collect(toSet());
	
		String joined1 = Stream.of("hdsjfd", "dfhsu dsfa", "greu89wooeds")
				.collect(Collectors.joining());

		System.out.println(joined1);

		String joined2 = Stream.of("hdsjfd", "dfhsu dsfa", "greu89wooeds")
			.collect(Collectors.joining(", "));
		String joined3 = Stream.of("hdsjfd", "dfhsu dsfa", "greu89wooeds")
			.collect(joining(", "));

		System.out.println(joined2);
	}

	public static void main12(String[] args) throws IOException {
		int[] array = IntStream.iterate(1, n -> n + 1)
			.limit(20)
			.toArray();

		System.out.println(array.length);
		System.out.println(array[19]);
		System.out.println(Arrays.toString(array));

//		String[] arrayStr = (String[]) Stream.of("ab", "hreis", "hogh we")
//			    .toArray();
//		Object[] arrayStr = Stream.of("ab", "hreis", "hogh we").toArray();
//
//		System.out.println(arrayStr.length);
//		System.out.println(arrayStr[2]);
//		System.out.println(((String)arrayStr[2]).charAt(0));
//		System.out.println(Arrays.toString(arrayStr));

		String[] arrayRealStr =
			Stream.of("ab", "hreis", "hogh we").toArray(n -> new String[n]);
		String[] arrayRealStr2 =
			Stream.of("ab", "hreis", "hogh we").toArray(String[]::new);
	
		System.out.println(arrayRealStr.length);
		System.out.println(arrayRealStr[2]);
		System.out.println(arrayRealStr[2].charAt(0));
		System.out.println(Arrays.toString(arrayRealStr));
	}

	public static void main11(String[] args) throws IOException {
		IntStream.iterate(1, n -> n + 1)
			.limit(20)
			.map(n -> 2 * n)  // IntStream ----> IntStream
			.boxed()          // IntStream ----> Stream<Integer>
			.forEach(out::println);

		IntStream.iterate(1, n -> n + 1)
			.limit(20)
			.map(n -> 2 * n)  // IntStream ----> IntStream
			.mapToObj(n -> n + " " + n)          // IntStream ----> Stream<String>
//			.map(txt -> txt.length())
//			.map(String::length)
			.forEach(out::println);


		Stream.of("fhhaskfgdas", "fhdsakfd", "dsga", "fyergnsa")
			.map(txt -> txt.length())   // Stream<String> -----> Stream<Integer>
			;

		Stream.of("dsga", "fhhaskfgdas", "fhdsakfd", "fyergnsa")
			.filter(txt -> txt.length() > 5)
			.forEach(out::println);
			
//		range(0, 100)
//			.takeWhile(n -> n < 47)
//			.forEach(out::println);

		System.out.println("-------------");
		
		Stream.of("dsga", "fhhaskfgdas", "fhdsakfd", "fyergnsa")
		.takeWhile(txt -> txt.length() > 5)
		.forEach(out::println);
	
		IntStream.concat(IntStream.of(9), IntStream.generate(() -> 1))
			.takeWhile(n -> n < 5)
			.forEach(out::println);

		IntStream.concat(IntStream.of(9), IntStream.generate(() -> 1))
			.dropWhile(n -> n < 5)
			.forEach(out::println);

	}

	public static void main9(String[] args) throws IOException {
		// Gauss: 1+100 + 2+99 + ... = 101 * (100/2)
		IntSummaryStatistics stats = IntStream.iterate(1, n -> n + 1)
			.limit(100)
			.summaryStatistics();

		System.out.println(stats);
		System.out.println(stats.getSum());

		long elemCount = IntStream.iterate(1, n -> n + 1)
				.limit(100)
				.count();

		OptionalInt min = IntStream.iterate(1, n -> n + 1)
				.limit(100)
				.min();

		Optional<String> opt = Optional.of("32tg");
		System.out.println(opt.get());
		
		Optional<Integer> opt2 = Optional.empty();
		Optional<Integer> opt3 = empty();
		
		if (opt.isEmpty())   System.out.println("is empty");
		if (opt.isPresent())   System.out.println("is present");

		System.out.println(IntStream.of().min());
//		System.out.println(IntStream.of().min().getAsInt());

		Optional.empty().orElse(452);
		Optional.empty().orElseGet(() -> 452);
		Optional.of(547).orElseGet(() -> 452);

		Optional.ofNullable(null);
	}

	public static void main10(String[] args) throws IOException {
		IntStream.range(0, 10);
		IntStream.rangeClosed(0, 10);
		range(0, 10);
		rangeClosed(0, 10);
		
//		Stream.iterate(null, null)

		// v, f(v), f(f(v)), f(f(f(v))), ...
		IntStream.iterate(0, n -> n + 1)
			.limit(3)
			.forEach(out::println);
	}

	public static void main8(String[] args) throws IOException {
//		byte -> short -> int -> long
		
		long max = Long.MAX_VALUE / 10000000;
		float sameThing = max;
		System.out.println(max);
		System.out.printf("%f%n", sameThing);
		System.out.printf("%f%n", Float.MAX_VALUE);
	}

	public static void main7(String[] args) throws IOException {
		byte[] byteValue = {0};
		Stream.generate(() -> ++byteValue[0])
			.forEach(out::println);

//		byteValue[0] = 35;
	}

	public static void main6(String[] args) throws IOException {
		IntStream.generate(new IntSupplier() {
			int value = 0;
			@Override
			public int getAsInt() {
				return ++value;
			}
		})
			.skip(456)
			.limit(100)
			.forEach(out::println);
	}

	public static void main5(String[] args) throws IOException {
		// Objects, Arrays, Collections
		Files.lines(Path.of("src", "Streams.java"))
			.forEach(out::println);
		lines(Path.of("src", "Streams.java"))
			.forEach(out::println);
	}

	public static void main4(String[] args) {
		ArrayList<Integer> modifiableList = new ArrayList<>(List.of(1, 2, 3));
		modifiableList.stream()
			.forEach(out::println);

		int[] elems = { 452, 1356423, -32523 };
		Arrays.stream(elems)
			.forEach(out::println);
		Arrays.<Integer>stream(elems)
			.forEach(out::println);
		stream(elems)
			.forEach(out::println);

		for (int elem : elems) {
			System.out.println(elem);
		}
	}

	public static void main2(String[] args) {
		Stream<Integer> oneTwoThree = Stream.of(1, 2, 3);
		Stream<Integer> oneTwoThree2 = of(1, 2, 3);
		Stream<String> texts = Stream.of("abc", "gdhjdaksgfhk");
		Stream<MyClass> myClasses = Stream.of(new MyClass());
		var myClasses2 = Stream.of(new MyClass());

		IntStream.of(1, 2, -453, 1)
			.forEach(out::println);
		
		// Consumer<Integer>
//		oneTwoThree.forEach(s -> System.out.println(s));
		oneTwoThree.forEach(System.out::println);

	
		List<Integer> data = List.of(1, 2, 3);
		out.println(data.size());
//		System.out.println(data.add(4));
		System.out.println(data.getClass().getName());
		data.forEach(System.out::println);

		ArrayList<Integer> arrayList = new ArrayList<>();
		arrayList.add(1);
		arrayList.add(2);
		arrayList.add(3);
		System.out.println(arrayList.add(4));
		
		ArrayList<Integer> modifiableList = new ArrayList<>(List.of(1, 2, 3));
	}
}
