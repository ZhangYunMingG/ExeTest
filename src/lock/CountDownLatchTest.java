package lock;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {

	public static void main(String[] args) {
		CountDownLatch count = new CountDownLatch(3);
		Thread t1 = new Thread(new Worker("张三", count));
		Thread t2 = new Thread(new Worker("李四", count));
		Thread t3 = new Thread(new Worker("王五", count));
		Thread t = new Thread(new Boss("boss", count));
		
		t1.start();
		t2.start();
		t3.start();
		t.start();
		
	}
	
	static class Worker implements Runnable
	{
		private String name;
		private CountDownLatch count;
		public Worker(String name, CountDownLatch count) {
			this.name = name;
			this.count = count;
		}
		public void run() {
			System.out.println(name + "开始工作。。。");
			Random random = new Random();
			int time = random.nextInt(10);
			try {
				System.out.println(name + "----" +time);
				TimeUnit.SECONDS.sleep(time);
				System.out.println(name + "完成工作。。。");
				count.countDown();
			} catch (Exception e) {
				
			}
		}
		
	}
	
	static class Boss implements Runnable
	{
		private String name;
		private CountDownLatch count;
		public Boss(String name, CountDownLatch count) {
			this.name = name;
			this.count = count;
		}
		public void run() {
			try {
				count.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(name + "检查工作！！！");
		}
	}
}
