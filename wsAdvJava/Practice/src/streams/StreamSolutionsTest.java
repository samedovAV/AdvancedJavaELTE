package streams;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

class StreamSolutionsTest {

	@Test
	void first5primes() {
		assertEquals(List.of(2,3,5,7,11), StreamSolutions.getPrimes.apply(5));
	}

}
