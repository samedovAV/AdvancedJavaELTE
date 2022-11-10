package streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class StreamFunctions {
    public static void main(String[] args) {
        // 1. Print the lengths of the command line arguments in reverse order.
        List<Integer> argsLengths = Arrays.stream(args)
                .map(String::length).sorted(Comparator.reverseOrder()).collect(toList());
        System.out.println(argsLengths);

        // 2. Print the sum of even numbers that are greater than 8 from the command line arguments.
            //Variant: ignore all texts that are not numbers.
            //Variant: using a lambda, take the condition as an argument. So, instead of testing for “greater than 8”, test whatever the caller specifies.
    }
}
