package lock;

public class BackStageThreadTest {

	public static void main(String[] args) {
		
		Thread t = new Thread(new BackStage());
		t.setDaemon(true);
		t.start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("main Thread is end !");
	}
	
	static class BackStage implements Runnable
	{
		public void run() {
			System.out.println("backStage is start!");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("backStage is end!");
		}
	}
}
