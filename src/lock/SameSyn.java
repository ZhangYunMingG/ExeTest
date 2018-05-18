package lock;

import java.util.concurrent.TimeUnit;

//当synchronized修饰一个类中的两个方法时，这两个方法是线程安全的（不能同时执行）
//注：当synchronized修饰静态方法时，所有的对象的synchronized方法都是线程安全的,方法是属于类的
public class SameSyn {
public static void main(String[] args) {
		
		Run run = new Run();
		Thread t1 = new Thread(new T1(run));
		Thread t2 = new Thread(new T2(run));
		t1.start();
		t2.start();
	}
	
	static class T1 implements Runnable
	{
		private Run run;
		public T1(Run run) {
			this.run  = run;
		}
		public void run () {
			run.r1();
		}
	}
	static class T2 implements Runnable
	{
		private Run run;
		public T2(Run run) {
			this.run  = run;
		}
		public void run () {
			run.r2();
		}
	}
	
	static class Run
	{
		public synchronized void r1() {
			try {
				System.out.println("r1 is execute!");
				TimeUnit.MILLISECONDS.sleep(2000);
			} catch (InterruptedException e) {
			}
			System.out.println("r1 is end!");
		}
		
		public synchronized void r2() {
			try {
				System.out.println("r2 is execute!");
				TimeUnit.MILLISECONDS.sleep(2000);
			} catch (InterruptedException e) {
			}
			System.out.println("r2 is end!");
		}
	}

}
