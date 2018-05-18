package lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Demo class
 * 
 * @author keriezhang
 * @date 2016/10/31
 */
public class ReentrantReadWriteLockTest {
	
	public static void main(String[] args) 
	{
		ReadWriteLock rwLock = new ReentrantReadWriteLock();
		ReadThread t1 = new ReadThread(rwLock);
		ReadThread t2 = new ReadThread(rwLock);
		WriteThread t3 = new WriteThread(rwLock);
		WriteThread t4 = new WriteThread(rwLock);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
	}
}
class ReadThread extends Thread
{
	private ReadWriteLock lock;
	public ReadThread(ReadWriteLock lock)
	{
		this.lock = lock;
	}
	@Override
	public void run() {
		lock.readLock().lock();
		try {
			
			for(int i = 0; i < 100; i ++)
			{
				System.out.println(Thread.currentThread().getName() + "Ö´ÐÐ£¡£¡£¡");
			}
			
			
		} catch(Exception e) {
			System.out.println("ËøÒì³££¡");
		} finally {
			System.out.println(Thread.currentThread().getName() + "finally");
			lock.readLock().unlock();
			System.out.println(Thread.currentThread().getName() + "ËøÊÍ·Å£¡");
		}
	}
}

class WriteThread extends Thread
{
	private ReadWriteLock lock;
	public WriteThread(ReadWriteLock lock) {
		this.lock = lock;
	}
	@Override
	public void run() {
		
		lock.writeLock().lock();
		try {
			
			for(int i = 0; i < 100; i ++)
			{
				System.out.println("Write" + Thread.currentThread().getName() + "Ö´ÐÐ£¡£¡£¡");
			}
			
		} catch (Exception e) {
			System.out.println("ËøÒì³££¡");
		} finally {
			System.out.println(Thread.currentThread().getName() + "finally£¡");
			lock.writeLock().unlock();
			System.out.println(Thread.currentThread().getName() + "ËøÊÍ·Å£¡");
		}
	}
}
