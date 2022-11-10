package threading;

import java.util.function.Function;
import java.util.stream.IntStream;

public class ThreadingSolutions {

    public static void main(String[] args) {
        // Make two threads that each use System.out.println to print a given text 10000 times. (Give different texts to the threads.)
        // Notice how the printouts are interleaved

        Function<String, Thread> makeThread = name -> new Thread(() -> {
            IntStream.range(0, 10_000)
                    .mapToObj(i -> name + " " + i)
                    .forEach(System.out::println);
        });

        var firstThread = makeThread.apply("first");
        var secondThread = makeThread.apply("second");

        firstThread.start();
        secondThread.start();

        // Now, instead of println, print each text character by character (and an ending newline).
        // What happens to the printout?
        // Use synchronization to fix the issue.

        Function<String, Thread> makeThreadText = name -> new Thread(() -> {
            synchronized (ThreadingSolutions.class) {
                for (int i = 0; i < 10_000; i++) {
                    for (int j = 0; j < name.length(); j++) {
                        System.out.println(name.charAt(j) + " " + i);
                    }
                }
            }
        });

        var firstThreadText = makeThreadText.apply("first");
        var secondThreadText = makeThreadText.apply("second");

        firstThreadText.start();
        secondThreadText.start();
    }
}
