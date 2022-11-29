package threading;

public class Threading {
	// JVM creates an execution thread that starts runnig main
	public static void main(String[] args) {
		// thread object
		new Thread(() -> {
			for (int i = 0; i < 100_000; i++) {
				System.out.println("Hello " + i);
			}
//		}).run(); 		// NO execution thread
		}).start();		// execution thread

		new Thread(() -> {
			for (int i = 0; i < 100_000; i++) {
				System.out.println("World " + i);
			}
		}).start();

		// scheduling

		
		
		System.out.println("Done (terminated)");
	}
}
