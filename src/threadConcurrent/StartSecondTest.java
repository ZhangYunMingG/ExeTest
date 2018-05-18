package threadConcurrent;

public class StartSecondTest {

	public static void main(String[] args) {
		
		Thread thread = new Thread(new Run());
		try {
			thread.start();
		} catch (Exception e) {
			System.out.println("first!");
			System.out.println(e.getMessage());
		}
		try {
			thread.start();
		} catch (Exception e) {
			System.out.println("second!");
			System.out.println(e.getMessage());
			System.out.println(e);
		}
	}
	
	static class Run implements Runnable
	{
		public void run() {
			System.out.println("run()");
		}
	}
}
