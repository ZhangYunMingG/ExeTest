package threadConcurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadInterupTest {
	
	public static void main(String[] args) throws Exception {
		try {
			final Lock lock=new ReentrantLock();
		    lock.lock();
		    Thread.sleep(500);
		    Thread t1=new Thread(new Runnable(){
		        @Override
		        public void run() {
		        	try {
		        		 lock.lock();
				            System.out.println(Thread.currentThread().getName()+" interrupted.");
		        	} catch (Exception e) {
		        		System.out.println("---");
		        	}
		           
		        }
		    });
		    t1.start();
		    Thread.sleep(500);
		    t1.interrupt();
		} catch (Exception e) {
			System.out.println("----");
		}
		 
		
//		 final Lock lock=new ReentrantLock();
//		    lock.lock();
//		    Thread.sleep(1000);
//		    Thread t1=new Thread(new Runnable(){
//		        @Override
//		        public void run() {
//		            try {
//		                lock.lockInterruptibly();
//		            } catch (InterruptedException e) {
//		                        System.out.println(Thread.currentThread().getName()+" interrupted.");
//		            }
//		        }
//		    });
//		    t1.start();
//		    Thread.sleep(1000);
//		    t1.interrupt();
		
//		Lock lock = new ReentrantLock();
//		Thread thread = new Thread(new Run(lock));
//		thread.start();
//		System.out.println("sleep");
//		thread.interrupt();
		
	}
	
	static class Run implements Runnable
	{
		private Lock lock;
		public Run(Lock lock) {
			this.lock = lock;
		}
		public void run() {
			try {
				lock.lock();
				System.out.println("run is start !!");
				Thread.sleep(100);
				System.out.println("end!");
				
			} catch (Exception e) {
				System.out.println("锁中断！");
			}
			
		}
	}
}
