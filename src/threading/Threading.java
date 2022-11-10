package threading;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.IntStream;

interface ConsumerWithException<T> {
    void accept(T t) throws InterruptedException;
}

public class Threading {
    public static void main(String[] args) {

        int value = 0;

        Object myCustomLock = new Object();

        // execution thread
        // thread object
        new Thread(() -> {
            for (int i = 0; i < 10_000; i++) {
                System.out.println("Hello " + i);
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10_000; i++) {
                System.out.println("World " + i);
            }
        //}).run(); // No execution thread
        }).start();

        // scheduling

        System.out.println("Done (terminated)");

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10_000; i++) {
                System.out.println("Hello " + i);
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10_000; i++) {
                System.out.println("World " + i);
            }
        });
        t2.start();

        try {
            t1.join(); // blocking operation
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Function<String, Thread> makeThread = txt -> new Thread(() -> {
            synchronized (myCustomLock) {
                IntStream.range(0, 100_000)
                        .mapToObj(i -> txt + " " + i)
                        .forEach(System.out::println);
            }
        });

        Thread[] threads = IntStream.range(0, 10).mapToObj(i -> makeThread.apply("Thread " + i))
                .toArray(Thread[]::new);

        Arrays.stream(threads).forEach(Thread::start);
        Arrays.stream(threads).forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Arrays.stream(threads).forEach(ignoreException(Thread::join));

        var thread_one = makeThread.apply("Hello");
    }

    private static <T> Consumer<T> ignoreException(ConsumerWithException<T> consumer) {
        return data -> {
            try {
                consumer.accept(data);
            } catch (InterruptedException ignored) {

            }
        };
    }
}
