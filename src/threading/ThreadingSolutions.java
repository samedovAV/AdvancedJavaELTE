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

        // Two goats are trying to cross a narrow bridge from opposite sides.
        // They meet in the middle, and they start pushing each other.
        // Independently of each other, the goats take some time, and then push the other goat one step.
        // Whenever one of the goats is pushed off of the bridge, the game ends.
        // Take the speed of the goats (how much they wait between pushes) and the length of the bridge as parameters;
        // make the program log important actions.
    }

    private String whoWinsTheGame(int speedOfFirst, int speedOfSecond, int bridgeLength) {
        Function<String, Thread> makeThread = name -> new Thread(() -> {
            IntStream.range(0, 10_000)
                    .mapToObj(i -> name + " " + i)
                    .forEach(System.out::println);
        });
        var firstGoat = makeThread.apply("First Goat");
        var secondGoat = makeThread.apply("Second Goat");
        firstGoat.start();
        secondGoat.start();

        return "";
    }
}
