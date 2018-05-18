package lock;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {
	
	public static void main(String[] args) {
		
		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);
		Producer p1 = new Producer(queue);
		Producer p2 = new Producer(queue);
		Producer p3 = new Producer(queue);
		
		Consumer c1 = new Consumer(queue);
		Consumer c2 = new Consumer(queue);
		Consumer c3 = new Consumer(queue);
		
		new Thread(p1).start();
		new Thread(p2).start();
		new Thread(p3).start();
		
		new Thread(c1).start();
		new Thread(c2).start();
		new Thread(c3).start();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("producer start stop !!!");
		
		p1.stop();
		p2.stop();
		p3.stop();
		c1.stop();
		c2.stop();
		c3.stop();
	}
	
	static class Producer implements Runnable
	{
		private volatile boolean flag = true;
		private BlockingQueue<String> queue;
		public Producer(BlockingQueue<String> queue) {
			this.queue = queue;
		}
		public void run() {
			int i = 0;
			while(flag) {
				try {
						String str = Thread.currentThread().getName() + "  数量  " +i++;
						System.out.println("生产了" + str);
						queue.put(str);
						} catch (Exception e) {
							e.printStackTrace();
						}
						Random random = new Random();
						int time = random.nextInt(2000);
						try {
							Thread.sleep(time);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
			}
		}
		public void stop() {
			flag = false;
		}
	}
	
	static class Consumer implements Runnable {
		private volatile boolean flag = true;
		private BlockingQueue<String> queue;
		public Consumer(BlockingQueue<String> queue) {
			this.queue = queue;
		}
		public void run() {
			while(flag) {
				try {
					System.out.println("消费了" + queue.take());
						} catch (Exception e) {
							e.printStackTrace();
						}
						Random random = new Random();
						int time = random.nextInt(2000);
						try {
							Thread.sleep(time);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
			}
		}
		public void stop() {
			flag = false;
		}
	}
}
