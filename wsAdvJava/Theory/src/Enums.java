
// Lambdas (anonymous functions)

@FunctionalInterface
interface MyRunnable {
    // public abstract void run();
    void run();
    // void run2();
}

public class Enums {
    static void m() {}

    public static void main(String[] args) {
//        say("HW");

        // Fira Code
        MyRunnable r1 = () -> {
            System.out.println("dsafdsafa");
            System.out.println("dsafdsafa");
        };
        MyRunnable r2 = () -> System.out.println("dsafdsafa");
        MyRunnable r3 = Enums::m;
        MyRunnable r4 = () -> Enums.m();

        r1.run();
        r2.run();
        r3.run();
        r4.run();

//        ? say = txt -> System.out.println(txt);
//        say.apply("txt");

//        ? mul3add1 = n -> 3 * n + 1;

//        doItThreeTimes(System.out::println);
//        doItThreeTimes(txt -> System.out.println(txt));
    }

    void say(String txt) {
        System.out.println(txt);
    }
}
