package threadConcurrent;

import java.util.Random;

public class ThreadLocalTest {

	public static void main(String[] args) {
		
		ThreadLocal<String> threadLocal = new ThreadLocal<String>();
		for(int i = 0; i < 10; i ++) {
			new Thread(new Run(threadLocal)).start();
		}
		System.out.println(threadLocal);
	//	threadLocal.set("");
		System.out.println("");
		
	}
	
	static class Run implements Runnable
	{
		Random random = new Random();
		private ThreadLocal<String> local;
		public Run(ThreadLocal<String> local) {
			this.local = local;
		}
		public void run() {
			int t = random.nextInt(10);
			System.out.println(Thread.currentThread().getName() + "sleep:" + t);
			local.set(Thread.currentThread().getName() + "sleep:" + t);
			try {
		//		Thread.sleep(t * 1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
