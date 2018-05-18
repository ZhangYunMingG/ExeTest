package lock;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* 
 * javadoc @author z
 */
public class ReentrantLockTest {
	
	private static int FIVE = 5;
	private ArrayList<Integer> arrayList = new ArrayList<Integer>();
	
	public static void main(String[] args)
	{
//		ReentrantLockTest test = new ReentrantLockTest();
		Lock lock = new ReentrantLock();
		Rt rt = new Rt(lock);
		rt.reentrant();
		new Sleep(lock).start();
		
//		new Thread () {
//			@Override
//			public void run() {
//				test.insert(Thread.currentThread());
//			}
//		}.start();
//		
//		new Thread() {
//			@Override
//			public void run() {
//				test.insert(Thread.currentThread());;
//			}
//		}.start();
		
	}
	
	public void insert(Thread thread)
	{
		Lock lock = new ReentrantLock();
		lock.lock();
		try {
			System.out.println(thread.getName() + "�������");
			for(int i = 0; 0 < FIVE; i ++)
			{
				arrayList.add(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(thread.getName() + "�ͷ�����");
			lock.unlock();
		}
	}
}

class Rt
{
	private Lock lock;
	public Rt(Lock lock)
	{
		this.lock = lock;
	}
	
	public void reentrant() {
		lock.lock();
		try {
			System.out.println("一段锁");
			lock.lock();
			try {
				System.out.println("二段锁");
			} catch (Exception e) {
				
			} finally {
				lock.unlock();
				System.out.println("释放一段锁！");
			}
			Thread.sleep(5000);
			
		} catch (Exception e) {
			
		} finally {
			lock.unlock();
			System.out.println("释放二段锁！");
		//	lock.unlock();
		}
	}
}

class Sleep extends Thread
{
	private Lock lock;
	private int time;
	public Sleep(Lock lock)
	{
		this(lock, 5);
	}
	public Sleep(Lock lock, int time)
	{
		this.lock = lock;
		this.time = time;
		
	}
	@Override
	public void run() {
		lock.lock();
		try {
			System.out.println("lock start sleep !");
			Thread.sleep(time * 1000);
		} catch (Exception e) {
			System.out.println("锁中断");
		} finally {
			lock.unlock();
			System.out.println("lock end sleep !");
		}
	}
}
