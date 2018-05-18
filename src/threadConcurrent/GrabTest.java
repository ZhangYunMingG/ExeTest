package threadConcurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GrabTest {
	
	//  runArr	8869 	10000000
	//  lockArr 10300	10000000
	private static final int length = 10000000;
	public static void main(String[] args) {
		
		Lock lock = new ReentrantLock();
		Runnable[] lockArr = new Runnable[length];
		Runnable[] runArr = new Runnable[length];
		for(int i = 0; i < length; i ++) {
			lockArr[i] = new LockRun(lock);
		}
		for(int i = 0; i < length; i ++) {
			runArr[i] = new Run();
		}
		long start = System.currentTimeMillis();
		ExecutorService exeService = Executors.newCachedThreadPool();
		for(int i = 0; i < length; i ++) {
			exeService.execute(lockArr[i]);
		}
		exeService.shutdown();
		while(!exeService.isTerminated());
		long end = System.currentTimeMillis();
		System.out.println(end - start);
		
	}
	
	static class Run implements Runnable
	{
		public void run() {
			synchronized(GrabTest.class) {
				
			}
		}
	}
	
	static class LockRun implements Runnable
	{
		private Lock lock;
		public LockRun(Lock lock) {
			this.lock = lock;
		}
		public void run() {
			lock.lock();
			
			lock.unlock();
		}
	}

}
