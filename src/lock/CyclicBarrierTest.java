package lock;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierTest {
	
	public static void main(String[] args) {
		CyclicBarrier cyclic = new CyclicBarrier(3);
		Thread t1 = new Thread(new Worker("张三", cyclic));
		Thread t2 = new Thread(new Worker("李四", cyclic));
		Thread t3 = new Thread(new Worker("王五", cyclic));
		
		t1.start();
		t2.start();
		t3.start();
	}
	
	static class Worker implements Runnable
	{
		private String name;
		private CyclicBarrier cyclic;
		public Worker(String name, CyclicBarrier cyclic) {
			this.name = name;
			this.cyclic = cyclic;
		}
		public void run() {
			System.out.println(name + "开始工作了   。。。");
			try {
				Random random = new Random();
				int time = random.nextInt(10);
				System.out.println(name +"---" +time);
				TimeUnit.SECONDS.sleep(time);
				System.out.println(name + "做完了。。。");
				cyclic.await();
				System.out.println(name + "开始下一份工作！！！");
			} catch (Exception e) {
				
			}
		}
	}

}
