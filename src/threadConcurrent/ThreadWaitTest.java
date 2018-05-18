package threadConcurrent;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


//  当在wait之前,必须要先notify,否则会出现无法唤醒其他线程的问题
//	当调用notify时,线程会释放锁,同时代码不会往下面执行,这也就是要在wait之前加上notify的原因
//	notify加在wait后面将会没有作用
public class ThreadWaitTest {
	
	private static AtomicInteger number = new AtomicInteger(0);
	public static void main(String[] args) {
		
		Queue<String> queue = new LinkedList<String>();
		Producer p1 = new Producer("zhang", queue);
		Producer p2 = new Producer("yun", queue);
		Producer p3 = new Producer("ming", queue);
		Thread t1 = new Thread(p1);
		Thread t2 = new Thread(p2);
		Thread t3 = new Thread(p3);
		t1.start();
		t2.start();
		t3.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Consumer consumer = new Consumer(queue);
		new Thread(consumer).start();
		
	}
	
	static class Producer implements Runnable 
	{
		private String name;
		private Queue<String> queue;
		Random random = new Random();
		public Producer(String name, Queue<String> queue) {
			this.queue = queue;
			this.name = name;
		}
		public void run() {
			while(true) {
				try {
					Thread.sleep(random.nextInt(3000));
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				synchronized(queue) {
					try {
						if (queue.size() > 10) {
							System.out.println("queue is full!");
							queue.wait();
						}
							
					} catch (InterruptedException e) {
						System.out.println(name + "wait is stop!");
					}
					queue.offer(name + number.incrementAndGet());
					System.out.println("queue put: " + name + number.get());
					queue.notify();
				}
			}
		}
	}
	
	static class Consumer implements Runnable
	{
		private Queue<String> queue;
		Random random = new Random();
		public Consumer(Queue<String> queue) {
			this.queue = queue;
		}
		public void run() {
			while(true) {
				try {
					Thread.sleep(random.nextInt(3000));
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				synchronized(queue) {
					if(queue.isEmpty()) {
						try {
							queue.wait();
						} catch (InterruptedException e) {
							System.out.println("comsumer wait is stop!");
						}
					}
					System.out.println(queue.poll());;
			//		queue.notify();
				}
			}
		}
	}
}
