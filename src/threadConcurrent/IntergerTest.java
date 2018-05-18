package threadConcurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

// 测试多线程对同一值的修改(AtomicInteger 和 int)
// int 不是线程安全的   ， AtomicInteger是线程安全的
public class IntergerTest {
	
	private static int num = 0;
	private static AtomicInteger atomic = new AtomicInteger(0);
	public static void main(String[] args) 
	{
		ExecutorService service = Executors.newCachedThreadPool();
		for(int i = 0; i < 20; i ++) {
			service.execute(new AtomicRun());
		}
		service.shutdown();
		// 如果线程池中的线程全部结束，返回true, 否则返回false
		System.out.println(service.isTerminated());;
		System.out.println("end!");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("num:" + num);
		System.out.println("atomic:" + atomic);
 		
	}
	
	static class Run implements Runnable {
		public void run() {
			for(int j = 0; j < 100; j ++) 
				num = num + 1;
		//	System.out.println(num);
		}
	}
	
	static class AtomicRun implements Runnable {
		public void run() {
				for(int j = 0; j < 100; j ++) 
					atomic.incrementAndGet();
		//	System.out.println(atomic);
		}
	}
	
}

