package unit_testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;

public class UnitTesting {
    // 1. Make a function summer that sums the numbers between its two arguments by adding them together one by one
    @Test
    public void testSummer() {
        Assertions.assertEquals(4, UnitTestingFunctions.summer(2,2));
    }
    // 2. Make a function shortSummer that sums the numbers between its two arguments with a formula
}
