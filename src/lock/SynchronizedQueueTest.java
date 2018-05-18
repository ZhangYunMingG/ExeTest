package lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

public class SynchronizedQueueTest {
	
	public static void main(String[] args) throws Exception {
		
		ExecutorService exe = Executors.newFixedThreadPool(5);
		
		SynchronousQueue<String> queue = new SynchronousQueue<String>();
		Producer producer = new Producer(queue);
		Consumer consumer = new Consumer(queue);
		exe.execute(producer);
		Thread.sleep(3000);
		System.out.println("-----");
		exe.execute(consumer);
		Thread.sleep(3000);
		producer.stop();
		consumer.stop();
		exe.shutdown();
	}
	
	static class Producer implements Runnable
	{
		private volatile boolean isRunning = true;
		private SynchronousQueue<String> queue;
		public Producer(SynchronousQueue<String> queue) {
			this.queue = queue;
		}
		public void run() {
			int i = 0;
			System.out.println("Producer is start working !!!");
			while(isRunning) {
				String str = Thread.currentThread().getName() + "is start producer !  " + i++ ;
				System.out.println(str);
				try {
					queue.put(str);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(i + "  put is success ! ");
				
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
		public void stop() {
			isRunning = false;
		}
	}
	
	static class Consumer implements Runnable
	{
		private volatile boolean isRunning = true;
		private SynchronousQueue<String> queue;
		public Consumer(SynchronousQueue<String> queue) {
			this.queue = queue;
		}
		public void run() {
			int i = 0;
			System.out.println("Consumer is start working !!!");
			while(isRunning) {
				String str = Thread.currentThread().getName() + "is start Consumer !  " + i++ ;
				System.out.println(str);
				try {
					queue.take();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(i + "  get is success ! ");
				
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
		public void stop() {
			isRunning = false;
		}
	}

}
