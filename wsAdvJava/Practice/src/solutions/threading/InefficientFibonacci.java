package solutions.threading;

public class InefficientFibonacci {
	static int fib(int n) {
		if (n == 0)   return 1;
		if (n == 1)   return 1;
		return fib(n-1) + fib(n-2);
	}

	static int parFib(int n) {
		try {
			if (n == 0)   return 1;
			if (n == 1)   return 1;

			int[] results = { 0, 0 };

			Thread t1 = new Thread(() -> results[0] = parFib(n-1));
			Thread t2 = new Thread(() -> results[1] = parFib(n-2));

			t1.start();
			t2.start();
			t1.join();
			t2.join();

			return results[0] + results[1];
		} catch (InterruptedException e) {
			// won't happen
			return Integer.MIN_VALUE;
		}
	}

	public static void main(String[] args) {
		System.out.println(fib(10));
		System.out.println(parFib(10));
	}
}
