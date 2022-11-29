package threading;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.IntStream;

// concurrent programming
public class Threading4 {
	public static void main(String[] args) throws InterruptedException {
		// race condition: 2+ threads access the same resource
		int[] value = { 0 };

		Function<String, Thread> makeThread = txt -> new Thread(() -> {
			for (int i = 0; i < 100_000; i++) {
				// not atomic
//				++value[0];

									//	1		2
				int tmp = value[0];	//	12      12
				++tmp;				//  13		13
				value[0] = tmp;		//
			}
		});

		int THREAD_COUNT = 10;
		Thread[] threads = IntStream.range(0, THREAD_COUNT).mapToObj(i -> makeThread.apply("Thread" + i))
			.toArray(Thread[]::new);

		Arrays.stream(threads).forEach(Thread::start);
		Arrays.stream(threads).forEach(ignoreException(Thread::join));

		System.out.println("Done (terminated) " + value[0]);
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
