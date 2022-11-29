package fib;

import static fib.Fib.fib;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class FibTest {

//	@Test
//	void test() {
////		org.junit.jupiter.api.Assertions.fail("Not yet implemented");
////		Assertions.fail("Not yet implemented");
//		fail("Not yet implemented");
//	}

	@Test
	void trivial() {
		// wrong order
//		assertEquals(2+5, 4);
		assertEquals(4, 2+2);
	}

	@Test
	void trivial2() {
		assertEquals(4, 2+2, "explanation...");
	}

	@Test
	void trivial3() {
		assertEquals(4, 2+2, () -> "explanation...");
	}


	@Test
	void fib0() {
		assertEquals(1, Fib.fib(0));
	}

	// 1. test
	// 2. doc
	// 3. future-proof
	@Test
	void fib0V2() {
		assertEquals(1, fib(0));
//		assertEquals(1654, fib(1));
	}

	@Test
	void fibMulti() {
		assertAll(
			() -> assertEquals(1, fib(0)),
			() -> assertEquals(1, fib(1))
		);
	}

	@Test
	void fibException() {
		IllegalArgumentException exc =
			assertThrows(IllegalArgumentException.class,
				() -> fib(-1));
		assertEquals("Negative...", exc.getMessage());
	}

//	@Test
//	void fibException2() {
//		assertThrows(IllegalArgumentException.class,
//			() -> fib(0));
//	}

	@ParameterizedTest(name = "fib({0}) = {1}")
	@CsvSource({"0,1", "1,1", "5,8"})
	void fibPar(int n, int expected) {
		assertEquals(expected, fib(n));
	}

	@Test
	void fibs() {
//		assertEquals(5, Fib.fibs(5)[4]);
//		assertEquals(new int[] {1,1,2,3,5}, Fib.fibs(5));
		assertArrayEquals(new int[] {1,1,2,3,5}, Fib.fibs(5));
	}
}
