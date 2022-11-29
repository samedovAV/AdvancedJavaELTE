package threading;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.IntStream;

// concurrent programming
public class Threading3 {
	public static void main(String[] args) throws InterruptedException {
		Function<String, Thread> makeThread = txt -> new Thread(() -> {
			IntStream.range(0, 100_000)
			    .mapToObj(i -> txt + " " + i)
			    .forEach(System.out::println);
		});

		Thread[] threads = IntStream.range(0, 10).mapToObj(i -> makeThread.apply("Thread" + i))
			.toArray(Thread[]::new);

		Arrays.stream(threads).forEach(Thread::start);
		Arrays.stream(threads).forEach(t -> {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		System.out.println("Done (terminated)");
	}
}
