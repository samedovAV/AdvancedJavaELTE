package threading;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.IntStream;

interface ConsumerWithException<T> {
	void accept(T t) throws InterruptedException;
}

// concurrent programming
public class Threading3b {
	public static void main(String[] args) throws InterruptedException {
		Function<String, Thread> makeThread = txt -> new Thread(() -> {
			IntStream.range(0, 100_000)
			    .mapToObj(i -> txt + " " + i)
			    .forEach(System.out::println);
		});

		int THREAD_COUNT = 20;
		Thread[] threads = IntStream.range(0, THREAD_COUNT).mapToObj(i -> makeThread.apply("Thread" + i))
			.toArray(Thread[]::new);

		Arrays.stream(threads).forEach(Thread::start);
		Arrays.stream(threads).forEach(ignoreException(Thread::join));

		System.out.println("Done (terminated)");
	}

	private static <T> Consumer<T> ignoreException(ConsumerWithException<T> cons) {
		return data -> {
			try {
				cons.accept(data);
			} catch (InterruptedException e) {
				// note: won't happen in this case
			}
		};
	}
}
