public class MyCountingRunnable2
	implements Runnable
{
	int counter = 0;
	
	@Override
	public void run() {
		for (; counter < 100; counter++) {
			System.out.println(counter);
		}
	}

}
