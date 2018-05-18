package threadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorScheduleTest {
	
	public static void main(String[] args) {
		
//		ExecutorService service = Executors.newScheduledThreadPool(5);
//		ExecutorService service = Executors.newCachedThreadPool();
//		ExecutorService service = Executors.newFixedThreadPool(5);
//		for(int i = 0; i < 10; i ++) {
//			service.execute(new Run());
//		}
//		service.shutdown();
		
		ScheduledExecutorService exeService = Executors.newScheduledThreadPool(5);
		exeService.scheduleAtFixedRate(new Run(), 1, 3, TimeUnit.SECONDS);
		try {
			Thread.sleep(90000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		exeService.shutdown();
	}
	
	static class Run implements Runnable
	{
		public void run() {
			System.out.println(Thread.currentThread().getName() + "run is start!");
		}
	}

}
