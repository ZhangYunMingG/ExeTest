package lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Demo class
 * 
 * @author Ming
 * @date 2016/10/31
 */

//	关于读写锁重入问题：写锁可以重入读锁，然后释放写锁，但是反之，读锁不得重入写锁
public class ReentranWriteTest {
	
	public static void main(String[] args) {
		
		ReadWriteLock lock = new ReentrantReadWriteLock();
	//	Write t = new Write(lock);
		ReadW t = new ReadW(lock);
		t.start();
		
	}
}
class Write extends Thread
{
	private ReadWriteLock lock;
	public Write(ReadWriteLock lock) {
		this.lock = lock;
	}
	@Override
	public void run() {
		lock.writeLock().lock();;
		try {
			System.out.println("��ȡһ������");
			lock.writeLock().lock();
			try {
				System.out.println("��ȡ��������");
			} catch (Exception e) {
				System.out.println();
			} finally {
				System.out.println("finally is finish!");
				lock.writeLock().unlock();
				System.out.println("�ͷŶ�����");
			}
			
		} catch (Exception e) {
			System.out.println("");
		} finally {
			System.out.println("finally is finish!");
			lock.writeLock().unlock();
			System.out.println("�ͷ�һ����");
		}
	}
}

class Read extends Thread
{
	private ReadWriteLock lock;
	public Read(ReadWriteLock lock) {
		this.lock = lock;
	}
	@Override
	public void run() {
		lock.readLock().lock();;
		try {
			System.out.println("��ȡһ������");
			lock.readLock().lock();
			try {
				System.out.println("��ȡ��������");
			} catch (Exception e) {
				System.out.println();
			} finally {
				System.out.println("finally is finish!");
				lock.readLock().unlock();
				System.out.println("�ͷŶ�����");
			}
			
		} catch (Exception e) {
			System.out.println("");
		} finally {
			System.out.println("finally is finish!");
			lock.readLock().unlock();
			System.out.println("�ͷ�һ����");
		}
	}
}

class ReadW extends Thread
{
	private ReadWriteLock lock;
	public ReadW(ReadWriteLock lock) {
		this.lock = lock;
	}
	@Override
	public void run() {
		lock.readLock().lock();;
		try {
			System.out.println("��ȡһ������");
			if(lock.writeLock().tryLock(5000, TimeUnit.MILLISECONDS))
			{
				System.out.println("��ȡ��������");
			} else {
				System.out.println("�ȴ���ʱ��");
				return;
			}
				
			try {
				
			} catch (Exception e) {
				System.out.println();
			} finally {
				System.out.println("finally is finish!");
				lock.writeLock().unlock();
				System.out.println("�ͷŶ�����");
			}
			
		} catch (Exception e) {
			System.out.println("");
		} finally {
			System.out.println("finally is finish!");
			lock.readLock().unlock();
			System.out.println("�ͷ�һ����");
		}
	}
}

class WriteR extends Thread
{
	private ReadWriteLock lock;
	public WriteR(ReadWriteLock lock) {
		this.lock = lock;
	}
	@Override
	public void run() {
		lock.writeLock().lock();;
		try {
			System.out.println("��ȡһ������");
			if(lock.readLock().tryLock(5000, TimeUnit.MILLISECONDS))
			{
				System.out.println("��ȡ��������");
			} else {
				System.out.println("�ȴ���ʱ��");
				return;
			}
				
			try {
				
			} catch (Exception e) {
				System.out.println();
			} finally {
				System.out.println("finally is finish!");
				lock.readLock().unlock();
				System.out.println("�ͷŶ�����");
			}
			
		} catch (Exception e) {
			System.out.println("");
		} finally {
			System.out.println("finally is finish!");
			lock.writeLock().unlock();
			System.out.println("�ͷ�һ����");
		}
	}
}