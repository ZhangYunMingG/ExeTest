package lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class tryLockTest {
	
	public static void main(String[] args) 
	{
		Lock lock  = new ReentrantLock();
//		TryLock t1 = new TryLock(lock);
//		TryLock t2 = new TryLock(lock);
//		t1.start();
//		t2.start();
		
		TryLock1 t1 = new TryLock1(lock);
		TryLock1 t2 = new TryLock1(lock);
		TryLock1 t3 = new TryLock1(lock);
		t1.start();
		t2.start();
		t3.start();
		
	}
}

class TryLock1 extends Thread
{
	private Lock lock;
	public TryLock1(Lock lock)
	{
		this.lock = lock;
	}
	@Override
	public void run() {
		
		try {
			int time = 2000;
			if(lock.tryLock(time, TimeUnit.MILLISECONDS))
			{
				System.out.println("获取锁！");
			} else {
				System.out.println("未获取锁！");
				return;
			}
		
			try {
				Thread.sleep(3000);
			} catch (Exception e) {
				System.out.println("");
			} finally {
				System.out.println("释放锁！");
			}
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
	
	
	
}

class TryLock extends Thread {
	private Lock lock;
	public TryLock(Lock lock)
	{
		this.lock = lock;
	}
	
	@Override
	public void run () {
		
		try {
			System.out.println(lock.tryLock(2000, TimeUnit.MILLISECONDS));;
			try {
				System.out.println("获取锁！");
				Thread.sleep(4000);
			} catch (Exception e) {
				System.out.println("锁中断！");
			} finally {
				System.out.println("finally 完成!");
				lock.unlock();
				System.out.println("释放锁！");
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}