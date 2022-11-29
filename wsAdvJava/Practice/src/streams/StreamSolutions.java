package streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamSolutions {
//	Given some filenames in the command line arguments,
// print the names on one line, separated with commas,

// in descending order in the number of lines in the file.
// If two files have the same number of lines,
// then the one with more words on its first line should come first.
	private static int linesInFile(String filename) {
		try {
			return (int)Files.lines(Path.of(filename)).count();
		} catch (IOException e) {
			return 0;
		}
	}

	private static int wordsOnFirstLine(String filename) {
		try {
			return Files.lines(Path.of(filename))
						.findFirst()
						.map(line -> line.split(" ").length)
						.orElse(0);
		} catch (IOException e) {
			return 0;
		}
	}

	public static void main(String[] args) {
		args = "src/lambdas/UseArgs.java src/streams/StreamSolutions.java".split(" ");
//
//		String result =
//			Arrays.stream(args)
//				.map(filename -> linesInFile(filename))
////				.map(n -> n + "")
//				.map(n -> Integer.toString(n))
////				.map(Integer::toString)
//				.collect(Collectors.joining(", "));


		String result =
			Arrays.stream(args)
				.sorted(Comparator.comparing((String filename) -> linesInFile(filename))
						.thenComparing(filename -> wordsOnFirstLine(filename)))
				.collect(Collectors.joining(", "));

		System.out.println(result);
	}
	
	static Function<Integer, List<Integer>> getPrimes =
		primeCount ->
			IntStream.iterate(2, n -> n+1)
				.filter(p -> IntStream.range(2, (int)Math.sqrt(p) + 1)
						.parallel()
						.allMatch(div -> p % div != 0))
				.limit(primeCount)
				.boxed()
				.collect(Collectors.toList());

	
	//	Make a method that generates a stream of the first n prime numbers.
	public static void main7(String[] args) {
//		IntStream.range(2, 100000)

//		IntStream.iterate(2, n -> n+1)
//			.filter(p -> IntStream.range(2, p).noneMatch(div -> p % div == 0))
//			.limit(1000)
//			.forEach(System.out::println);

	}

	public static void main6(String[] args) {
		String[] args2 = "2 3 1 654 fefdwgsah 3245 765 dsgha 2341 328 f dsf 23948 -45312 432".split(" ");
		BiFunction<Stream<String>, Predicate<Integer>, Stream<Integer>> evenTool =
			(stream, cond) ->
				stream
					.flatMap(arg -> {
						try {
							return Stream.of(Integer.parseInt(arg));
						} catch (NumberFormatException e) {
							return Stream.of();
						}
					})
					.filter(n -> n % 2 == 0)
					.filter(cond);

		evenTool.apply(Arrays.stream(args2), n -> n < 0)
			.forEach(System.out::println);
	}

	public static void main5(String[] args) {
		String[] args2 = "2 3 1 654 fefdwgsah 3245 765 dsgha 2341 328 f dsf 23948 -45312 432".split(" ");
		BiFunction<Stream<String>, Predicate<Integer>, Stream<Integer>> evenTool =
			(stream, cond) ->
				stream
					.mapMulti((String arg, Consumer<Integer> consumer) -> {
						try {
							consumer.accept(Integer.parseInt(arg));
						} catch (NumberFormatException e) {
						}
					})
					.filter(n -> n % 2 == 0)
					.filter(cond);

		evenTool.apply(Arrays.stream(args2), n -> n < 0)
			.forEach(System.out::println);
	}

	public static void main4(String[] args) {
		String[] args2 = "2 3 1 654 fefdwgsah 3245 765 dsgha 2341 328 f dsf 23948 -45312 432".split(" ");
		BiFunction<Stream<String>, Predicate<Integer>, Stream<Integer>> evenTool =
			(stream, cond) ->
				stream
					.filter(arg -> {
						try {
							Integer.parseInt(arg);
							return true;
						} catch (NumberFormatException e) {
							return false;
						}
					})
					.map(Integer::parseInt)
					.filter(n -> n % 2 == 0)
					.filter(cond);

		evenTool.apply(Arrays.stream(args2), n -> n < 0)
			.forEach(System.out::println);
	}

	public static void main3(String[] args) {
		String[] args2 = "2 3 1 654 3245 765 2341 328 23948 -45312 432".split(" ");

//		Print the sum of even numbers that are greater than 8 from the command line arguments.
		Arrays.stream(args2)
			.map(Integer::parseInt)
			.filter(n -> n % 2 == 0)
			.filter(n -> n > 8)
//			.filter(n -> n > 8 && n % 2 == 0)
			.forEach(System.out::println);

		BiFunction<Stream<String>, Predicate<Integer>, Stream<Integer>> evenTool =
			(stream, cond) ->
				stream
					.map(Integer::parseInt)
					.filter(n -> n % 2 == 0)
					.filter(cond);

		evenTool.apply(Arrays.stream(args2), n -> n < 0)
			.forEach(System.out::println);
	}

	public static void main2(String[] args) {
		String[] args2 = "2 3 1".split(" ");

//		Arrays.stream(args)
////			.reversed()
//			.forEach(System.out::println);
		
		IntStream.range(0, args2.length)
			.mapToObj(idx -> args2[args2.length - 1 - idx])
			.forEach(System.out::println);

		
		//		Print the lengths of the command line arguments in reverse order.
		List<Integer> solution1 = Arrays.stream(args)
//			.map(arg -> arg.length())
			.map(String::length)         // ----> Stream<Integer>
//			.mapToInt(String::length)    // ----> IntStream
			.sorted(Comparator.reverseOrder())
			.collect(Collectors.toList());
	}
}
