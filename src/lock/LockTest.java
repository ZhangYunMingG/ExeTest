package lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
	
	public static void main(String[] args)
	{
		Lock lock = new ReentrantLock();
		LockThread t1 = new LockThread(lock);
		LockThread t2 = new LockThread(lock);
		t1.start();
		try {
			TimeUnit.MILLISECONDS.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("----");
		t2.start();
	}
}

class LockThread extends Thread
{
	private Lock lock;
	public LockThread(Lock lock)
	{
		this.lock = lock;
	}
	
	@Override
	public void run() {
		lock.lock();
		try {
			System.out.println(Thread.currentThread().getName() + "加锁");
			Thread.sleep(2000);
		} catch (Exception e) {
			System.out.println("锁中断");
		} finally {
			System.out.println("finally��ɣ�");
			//  !!!! unlock一定要关闭(��дlock��ʱ�򣬾�Ҫдunlock,��������)
			System.out.println(Thread.currentThread().getName() + "释放锁");
			lock.unlock();
		}
	}
}