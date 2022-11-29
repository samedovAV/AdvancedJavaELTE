package threading;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.IntStream;

// concurrent programming
public class Threading5 {
	public static void main(String[] args) throws InterruptedException {
		// race condition: 2+ threads access the same resource
		int[] value = { 0 };

		Object myCustomLock = new Object();

		Function<String, Thread> makeThread = txt -> new Thread(() -> {
			for (int i = 0; i < 100_000; i++) {
				// critical section
//				synchronized (Threading5.class) { // lock
//				synchronized (System.out) {
//				synchronized (myCustomLock) {
				synchronized (txt) {
					++value[0];
				}
			}
		});

		int THREAD_COUNT = 1000;
		Thread[] threads = IntStream.range(0, THREAD_COUNT)
			.mapToObj(i -> makeThread.apply("Thread" + i))
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
