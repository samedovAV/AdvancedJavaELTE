package fib;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ListTest {
	List<Integer> elems = new ArrayList<>();
	
	@Test
	void empty() {
		assertEquals(0, elems.size());
	}

	@Nested
	class Elem1 { // nested class
//		@AfterEach
//		@BeforeAll
//		@AfterAll
		@BeforeEach
		void beforeEach() {
			elems.add(1);
		}

		@Test
		void elem1() {
			assertAll(
				() -> assertEquals(1, elems.size()),
				() -> assertEquals(1, elems.get(0))
			);
		}

		@Test
		void elem1hasSize1() {
			assertEquals(1, elems.size());
		}

		@Test
		void elem1is1() {
			assertEquals(1, elems.get(0));
		}
		
		@Nested
		public class Elem2 {
			@BeforeEach
			void beforeEach() {
				elems.add(2);
			}

			@Test
			void elem2is2() {
				assertEquals(2, elems.get(1));
			}
		}
	}
}
