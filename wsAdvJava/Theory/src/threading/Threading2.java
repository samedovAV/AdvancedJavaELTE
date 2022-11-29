package threading;

// concurrent programming
public class Threading2 {
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(() -> {
			for (int i = 0; i < 100_000; i++) {
				System.out.println("Hello " + i);
			}
		});
		Thread t2 = new Thread(() -> {
			for (int i = 0; i < 100_000; i++) {
				System.out.println("World " + i);
			}
		});


		t1.start();
		t2.start();

		t1.join(); // blocking operation
		t2.join();

		System.out.println("Done (terminated)");
	}
}
