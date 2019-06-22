package condition;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public class WaitSingleTest {
	public static void main(String[] args) {
		
		Queue<String> queue = new LinkedBlockingQueue<String>();
		Producer producer = new Producer(queue);
		Consumer consumer = new Consumer(queue);
		
		new Thread(producer).start();
		new Thread(consumer).start();
		
	}
	
	static class Producer implements Runnable {
		private Queue<String> queue;
		public Producer(Queue<String> queue) {
			this.queue = queue;
		}
		public void run() {
			Random random = new Random();
			int i = 0;
			while(true) {
				try {
					Thread.sleep(random.nextInt(1 * 1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized(queue) {
					if(queue.size() == 10) {
						System.out.println("queue is full!");
						try {
							queue.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println("producer -> " + ++i);
					queue.offer(i + "");
				}
			}
		}
	}
	
	static class Consumer implements Runnable {
		private Queue<String> queue;
		public Consumer(Queue<String> queue) {
			this.queue = queue;
		}
		public void run() {
			Random random = new Random();
			while(true) {
				try {
					Thread.sleep(random.nextInt(3 * 1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (queue) {
					if(queue.isEmpty()) {
						try {
							System.out.println("queue is empty!");
							queue.notify();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
						
					System.out.println("consumer -> " + queue.poll());
				}
			}
		}
	}
}
