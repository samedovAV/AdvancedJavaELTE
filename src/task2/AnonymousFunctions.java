package task2;

import enums.Weekday;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Collections.reverseOrder;
import static java.util.stream.Collectors.*;

public class AnonymousFunctions {

    public static void main(String[] args) {
        Weekday firstA = Weekday.MON.oneA.apply(8);
        System.out.println(firstA);
        System.out.println("--");

        Boolean firstB = Weekday.MON.oneB.apply(Weekday.FRI);
        System.out.println(firstB);
        System.out.println("--");

        for (int i = 0; i < 10; i++) {
            Weekday firstC = Weekday.MON.nextWeekday.get();
            System.out.println(firstC);
        }

        // sort command line arguments
        List<String> strings = Arrays.stream(args)
                .sorted(Comparator.comparing(i -> {
                    int res = 0;
                    for (char c : i.toCharArray()) {
                        if (c == 'a') {
                            res++;
                        }
                    }
                    return res;
                })).collect(toList());
        System.out.println(strings);
        /* List<String> sortedWithA = Arrays.stream(args)
                .map(String::toLowerCase)
                .collect(groupingBy(Function.identity(), counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue(reverseOrder()).thenComparing(Map.Entry.comparingByKey()))
                .limit(7)
                .map(Map.Entry::getKey).toList();*/
        // createArray
        BiFunction<Integer, Integer, int[][]> createArrays = (Integer p1, Integer p2) -> {
            int[][] arrays = new int[p1][p2];
            for (int i = 0; i < p1; i++) {
                int[] innerArr = new int[p2];
                arrays[i] = innerArr;
            }
            return arrays;
        };
        createArrays.apply(10,10);

        // compose
        BiFunction<Function<String, String>, Function<String, String>, Function<String, String>> compose = (p1, p2) -> {
            return p1.apply(p2.apply());
        };
        Function<String, String> first = s -> "'" + s + "'";
        Function<String, String> second = s -> "-" + s + "-";
        compose.apply(first, second);
    }
}
