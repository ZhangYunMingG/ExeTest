package lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//  关于lock中的中断锁, 通过lock所在的线程的interrupt的方法中断
//
public class InterruptedTest {
	
	private Lock lock = new ReentrantLock();
	public static void main(String[] args)
	{
		InterruptedTest test = new InterruptedTest();
		
		MyThread t1 = new MyThread(test, 1);
		MyThread t2 = new MyThread(test, 2);
		t1.start();
		t2.start();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t2.interrupt();
		
		
		
	}
	
	public void insert(Thread thread) 
	{
		try {
			lock.lockInterruptibly();
		
			try {
				System.out.println(thread.getName() + "加锁!");
				Thread.sleep(4000);
			} catch (Exception e) {
				System.out.println("异常!");
			} finally {
				System.out.println(thread.getName() + "释放锁!");
				lock.unlock();
			}
		} catch (InterruptedException e1) {
			System.out.println(thread.getName() + "锁中断！");
		}
		
	}

}

class MyThread extends Thread
{
	private InterruptedTest test;
	public int i;
	public MyThread(InterruptedTest test)
	{
		this.test = test;
	}
	public MyThread(InterruptedTest test, int i)
	{
		this.test = test;
		this.i = i;
	}
	public void run() {
			System.out.println(i);
			test.insert(Thread.currentThread());
	}
}
