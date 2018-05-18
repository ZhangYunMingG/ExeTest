package collection;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
	
	public static void main(String[] args) {
		
		ExecutorService exeService = Executors.newCachedThreadPool();
		SemapLock semapLock = new SemapLock(new Semaphore(3));
		for(int i = 0; i < 10; i ++) {
			exeService.execute(new Car(semapLock));
		}
		exeService.shutdown();
		
	}
	
	static class Car implements Runnable
	{
		private SemapLock semapLock;
		public Car(SemapLock semapLock) {
			this.semapLock = semapLock;
		}
		public void run () {
			semapLock.fun();
		}
	}
	
	static class SemapLock
	{
		private static int i;
		private Semaphore semap = null;
		public SemapLock(Semaphore sem) {
			semap = sem;
		}
		public void fun() {
			try {
				semap.acquire();
				int k = i ++;
				System.out.println("start : " + k);
				Thread.sleep(1000);
				System.out.println("end:" + k);
				semap.release();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
