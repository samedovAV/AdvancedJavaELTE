package examPreporations.threading;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InefficientFibonacciTest {

    @CsvSource({"1, 1", "2, 2", "3, 3", "10, 89"})
    @ParameterizedTest
    void test(int input, int expected) {
        assertEquals(expected, InefficientFibonacci.parFib(input));
    }
}
