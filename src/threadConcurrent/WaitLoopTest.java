package threadConcurrent;

import java.util.LinkedList;
import java.util.Queue;

public class WaitLoopTest {
	
	public static void main(String[] args) {
		
		Queue<String> queue = new LinkedList<String>();
		for(int i= 0; i < 10; i ++) {
			Thread thread = new Thread(new Run(queue));
			thread.start();
		}
		Thread thread = new Thread(new Run(queue, 8));
		thread.start();
		
	}
	
	static class Run implements Runnable
	{
		private Queue<String> queue;
		private int tmp;
		public Run(Queue<String> queue, int tmp) {
			this.queue = queue;
			this.tmp = tmp;
		}
		public Run(Queue<String> queue) {
			this.queue = queue;
		}
		public void run() {
			synchronized(queue) {
				if(tmp == 0) {
					tmp ++;
					System.out.println(Thread.currentThread().getName());
					try {
						queue.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				queue.notify();
				System.out.println("queue" + Thread.currentThread().getName());
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
